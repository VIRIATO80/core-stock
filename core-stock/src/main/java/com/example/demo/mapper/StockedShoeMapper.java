package com.example.demo.mapper;

import com.example.demo.dto.out.StockedShoeDto;
import com.example.demo.entity.StockedShoe;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface StockedShoeMapper {

    StockedShoeMapper INSTANCE = Mappers.getMapper(StockedShoeMapper.class);

    StockedShoeDto stockedShoeToDto(StockedShoe shoe);

    StockedShoe stockedShoeToEntity(StockedShoeDto shoe);

    List<StockedShoe> stockedShoeToEntityList(List<StockedShoeDto> shoesList);
}