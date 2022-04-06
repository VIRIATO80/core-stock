package com.example.demo.mapper;

import com.example.demo.dto.out.StockedShoeDto;
import com.example.demo.entity.StockedShoe;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

public class StockedShoeMapperTest {

    @Test
    void mapping_to_entity_ok() {
        StockedShoeDto dto = StockedShoeDto.builder()
                .quantity(1)
                .size(BigInteger.ONE)
                .color(com.example.demo.entity.StockedShoe.Color.BLUE)
                .build();

        StockedShoe entity = StockedShoeMapper.INSTANCE.stockedShoeToEntity(dto);
        Assertions.assertThat(entity.getQuantity()).isEqualTo(dto.getQuantity());
        Assertions.assertThat(entity.getSize()).isEqualTo(dto.getSize());
        Assertions.assertThat(entity.getColor()).isEqualTo(dto.getColor());
    }

    @Test
    void mapping_to_dto_ok() {
        StockedShoe entity = com.example.demo.entity.StockedShoe.builder()
                .quantity(1)
                .size(BigInteger.ONE)
                .color(com.example.demo.entity.StockedShoe.Color.BLUE)
                .build();

        StockedShoeDto dto = StockedShoeMapper.INSTANCE.stockedShoeToDto(entity);
        Assertions.assertThat(dto.getQuantity()).isEqualTo(entity.getQuantity());
        Assertions.assertThat(dto.getSize()).isEqualTo(entity.getSize());
        Assertions.assertThat(dto.getColor()).isEqualTo(entity.getColor());
    }
}
