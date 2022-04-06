package com.example.demo.core;

import com.example.demo.dto.out.StockDto;
import com.example.demo.mapper.StockMapper;
import com.example.demo.repository.StockRepository;
import lombok.AllArgsConstructor;

import java.util.Optional;


@Implementation(version = 1)
@AllArgsConstructor
public class StockCoreImpl extends AbstractStockCore {

    StockRepository stockRepository;

    @Override
    public StockDto get() {
        Optional<com.example.demo.entity.Stock> response = stockRepository.findById(1L);
        if (response.isPresent()) {
            return StockMapper.INSTANCE.stockToDto(response.get());
        } else {
            throw new NullPointerException();
        }
    }

}