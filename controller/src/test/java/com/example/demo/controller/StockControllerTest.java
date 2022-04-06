package com.example.demo.controller;

import com.example.demo.core.StockCore;
import com.example.demo.dto.out.StockDto;
import com.example.demo.dto.out.StockedShoeDto;
import com.example.demo.entity.Stock;
import com.example.demo.entity.StockedShoe;
import com.example.demo.facade.StockFacade;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigInteger;
import java.nio.file.Files;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;

@WebMvcTest(StockController.class)
class StockControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    StockFacade stockFacade;

    @MockBean
    StockCore stockCore;

    @Value("classpath:integration-tests/initialShoes.json")
    Resource addShoesResource;

    @Test
    void get_stock_core_response_ok() throws Exception {
        when(stockFacade.get(1)).thenReturn(stockCore);
        when(stockCore.get()).thenReturn(StockDto.builder()
                .shoes(List.of(StockedShoeDto.builder()
                        .name("Stocked shoe")
                        .color(StockedShoe.Color.BLUE)
                        .size(BigInteger.ONE)
                        .build()))
                .build());
        mockMvc.perform(get("/stock")
                .header("version", 1))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void add_initial_shoes_to_stock() throws Exception {
        when(stockFacade.get(2)).thenReturn(stockCore);
        when(stockCore.get()).thenReturn(
                StockDto.builder()
                .state(Stock.State.EMPTY)
                .shoes(List.of())
                .build());
        mockMvc.perform(patch("/stock")
                .header("version", 2)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(Files.readString(addShoesResource.getFile().toPath())))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}