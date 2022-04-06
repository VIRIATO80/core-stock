package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class StockedShoeId implements Serializable {
    private BigInteger size;
    private StockedShoe.Color color;
}
