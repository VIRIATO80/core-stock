package com.example.demo.controller;

import com.example.demo.dto.out.StockDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class StockControllerIntegrationTest {

    @Autowired
    StockController stockController;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void given_empty_stock_from_DB(){

        StockDto result = stockController.get(1).getBody();
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getState()).isEqualTo(com.example.demo.entity.Stock.State.EMPTY);
    }

}
