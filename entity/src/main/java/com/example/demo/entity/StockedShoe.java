package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "shoes")
@IdClass(StockedShoeId.class)
public class StockedShoe {
    @Id
    private BigInteger size;
    @Id
    private Color color;
    private Integer quantity;


    public enum Color {
        BLACK,
        BLUE
    }
}