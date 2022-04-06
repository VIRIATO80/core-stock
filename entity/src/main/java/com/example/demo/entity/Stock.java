package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "stocks")
public class Stock {

    public static final int STOCK_MAX_CAPACITY = 30;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private State state = State.EMPTY;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<StockedShoe> shoes = new ArrayList<>();

    public enum State{
        EMPTY,
        FULL,
        SOME
    }

}
