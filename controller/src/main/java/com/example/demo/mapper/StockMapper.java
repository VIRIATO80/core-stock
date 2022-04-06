package com.example.demo.mapper;

import com.example.demo.dto.out.StockDto;
import com.example.demo.entity.Stock;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StockMapper {

    StockMapper INSTANCE = Mappers.getMapper(StockMapper.class);

    StockDto stockToDto(Stock stock);

    Stock stockToEntity(StockDto stock);
}
