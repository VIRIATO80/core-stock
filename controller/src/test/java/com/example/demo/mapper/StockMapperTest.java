package com.example.demo.mapper;

import com.example.demo.dto.out.StockDto;
import com.example.demo.dto.out.StockedShoeDto;
import com.example.demo.entity.Stock;
import com.example.demo.entity.StockedShoe;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.List;


public class StockMapperTest {

    @Test
    void stock_to_entity_mapping_ok() {
        StockedShoeDto shoe = StockedShoeDto.builder()
                .quantity(1)
                .size(BigInteger.ONE)
                .color(com.example.demo.entity.StockedShoe.Color.BLUE)
                .build();
        StockDto dto = StockDto.builder()
                .shoes(List.of(shoe))
                .state(com.example.demo.entity.Stock.State.SOME)
                .build();

        Stock entity = StockMapper.INSTANCE.stockToEntity(dto);
        Assertions.assertThat(entity.getShoes().get(0).getColor()).isEqualTo(dto.getShoes().get(0).getColor());
        Assertions.assertThat(entity.getShoes().get(0).getSize()).isEqualTo(dto.getShoes().get(0).getSize());
        Assertions.assertThat(entity.getShoes().get(0).getQuantity()).isEqualTo(dto.getShoes().get(0).getQuantity());
        Assertions.assertThat(entity.getState()).isEqualTo(dto.getState());
    }

    @Test
    void stock_to_dto_mapping_ok() {
        StockedShoe shoe = StockedShoe.builder()
                .quantity(1)
                .size(BigInteger.ONE)
                .color(com.example.demo.entity.StockedShoe.Color.BLUE)
                .build();
        Stock entity = Stock.builder()
                .shoes(List.of(shoe))
                .state(com.example.demo.entity.Stock.State.SOME)
                .build();


        StockDto dto = StockMapper.INSTANCE.stockToDto(entity);
        Assertions.assertThat(dto.getShoes().get(0).getColor()).isEqualTo(entity.getShoes().get(0).getColor());
        Assertions.assertThat(dto.getShoes().get(0).getSize()).isEqualTo(entity.getShoes().get(0).getSize());
        Assertions.assertThat(dto.getShoes().get(0).getQuantity()).isEqualTo(entity.getShoes().get(0).getQuantity());
        Assertions.assertThat(dto.getState()).isEqualTo(entity.getState());
    }
}
