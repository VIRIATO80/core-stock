package com.example.demo.repository;

import com.example.demo.entity.Stock;
import com.example.demo.entity.StockedShoe;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
public class StockRepositoryTest {

    @Autowired
    StockRepository stockRepository;

    @Test
    void when_search_by_id_return_results() {
        stockRepository.save(new Stock(1L, Stock.State.EMPTY, new ArrayList<>()));
        Optional<Stock> result = stockRepository.findById(1L);
        assertTrue(result.isPresent());
    }

    @Test
    public void should_insert_shoe() {
        stockRepository.save(new Stock(1L, Stock.State.EMPTY, new ArrayList<>()));
        Optional<Stock> result = stockRepository.findById(1L);

        if (result.isPresent()) {
            List<StockedShoe> shoeList = new ArrayList<>();
            shoeList.add(new StockedShoe(BigInteger.ONE, StockedShoe.Color.BLACK, 1));
            result.get().setShoes(shoeList);
            stockRepository.save(result.get());

            Optional<Stock> stock = stockRepository.findById(1L);
            stock.ifPresent(value -> assertThat(value.getShoes()).hasSize(1));
        }
    }

}
