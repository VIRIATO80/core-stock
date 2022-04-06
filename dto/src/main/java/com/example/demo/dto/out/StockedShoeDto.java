package com.example.demo.dto.out;

import com.example.demo.dto.out.Shoe.ShoeBuilder;
import com.example.demo.entity.StockedShoe.Color;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Value;

import java.math.BigInteger;

@Value
@Builder
@JsonDeserialize(builder = ShoeBuilder.class)
public class StockedShoeDto {

    String name;
    BigInteger size;
    Color color;
    Integer quantity;

}

