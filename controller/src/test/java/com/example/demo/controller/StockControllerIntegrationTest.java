package com.example.demo.controller;

import com.example.demo.dto.in.PatchAllStoreRequest;
import com.example.demo.dto.out.StockDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class StockControllerIntegrationTest {

    @Value("classpath:integration-tests/initialShoes.json")
    Resource initialShoesResource;

    @Value("classpath:integration-tests/giantStock.json")
    Resource gigantStockResource;

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

    @Test
    void given_empty_stock_add_new_shoes() throws Exception {

        StockDto result = stockController.patch(1, objectMapper.readValue(initialShoesResource.getFile(), PatchAllStoreRequest.class)).getBody();
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getShoes().size()).isEqualTo(3);
        Assertions.assertThat(result.getState()).isEqualTo(com.example.demo.entity.Stock.State.SOME);
    }

    @Test
    void should_fail_patch_for_size_stock() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                stockController.patch(1, objectMapper.readValue(gigantStockResource.getFile(), PatchAllStoreRequest.class)).getBody());
        String expectedMessage = "The stock is greater than allowed limit";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
