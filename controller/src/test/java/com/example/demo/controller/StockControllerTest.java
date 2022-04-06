package com.example.demo.controller;

import com.example.demo.core.StockCore;
import com.example.demo.dto.out.StockDto;
import com.example.demo.dto.out.StockedShoeDto;
import com.example.demo.entity.StockedShoe;
import com.example.demo.facade.StockFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigInteger;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(StockController.class)
class StockControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    StockFacade stockFacade;

    @MockBean
    StockCore stockCore;

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


}