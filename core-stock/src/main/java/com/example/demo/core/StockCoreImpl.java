package com.example.demo.core;

import com.example.demo.dto.in.PatchAllStoreRequest;
import com.example.demo.dto.out.StockDto;
import com.example.demo.dto.out.StockedShoeDto;
import com.example.demo.entity.Stock;
import com.example.demo.entity.Stock.State;
import com.example.demo.mapper.StockMapper;
import com.example.demo.mapper.StockedShoeMapper;
import com.example.demo.repository.StockRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;


@Implementation(version = 1)
@AllArgsConstructor
public class StockCoreImpl extends AbstractStockCore {

    StockRepository stockRepository;

    @Override
    public StockDto get() {
        Optional<Stock> response = stockRepository.findById(1L);
        if (response.isPresent()) {
            return StockMapper.INSTANCE.stockToDto(response.get());
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public StockDto patch(PatchAllStoreRequest patchStock) {

        int total = patchStock.getShoes().stream().mapToInt(StockedShoeDto::getQuantity).sum();

        if (total > Stock.STOCK_MAX_CAPACITY) {
            throw new IllegalArgumentException("The stock is greater than allowed limit");
        }
        Stock updatedStock = stockRepository.save(Stock.builder()
                .id(1L)
                .state(checkStockCapacity(patchStock.getShoes()))
                .shoes(StockedShoeMapper.INSTANCE.stockedShoeToEntityList(patchStock.getShoes()))
                .build());
        return StockMapper.INSTANCE.stockToDto(updatedStock);
    }

    private State checkStockCapacity(List<StockedShoeDto> shoes) {
        State state;
        switch (shoes.size()) {
            case 0 -> state = State.EMPTY;
            case Stock.STOCK_MAX_CAPACITY -> state = State.FULL;
            default -> state = State.SOME;
        }
        return state;
    }

}