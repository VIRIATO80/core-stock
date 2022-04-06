package com.example.demo.dto.out;

import com.example.demo.entity.StockedShoe.Color;
import lombok.Builder;
import lombok.Value;

import java.math.BigInteger;

@Value
@Builder
public class StockedShoeDto {

    String name;
    BigInteger size;
    Color color;
    Integer quantity;

}