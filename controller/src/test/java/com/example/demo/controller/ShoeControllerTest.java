package com.example.demo.controller;

import com.example.demo.core.ShoeCoreLegacy;
import com.example.demo.core.ShoeCoreNew;
import com.example.demo.dto.in.ShoeFilter;
import com.example.demo.dto.out.Shoe;
import com.example.demo.dto.out.Shoes;
import com.example.demo.facade.ShoeFacade;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigInteger;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(ShoeController.class)
class ShoeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ShoeFacade shoeFacade;

    @MockBean
    ShoeCoreLegacy shoeCoreLegacy;

    @MockBean
    ShoeCoreNew shoeCoreNew;

    @Test
    void get_legacy_core_response_ok() throws Exception {
        when(shoeFacade.get(1)).thenReturn(shoeCoreLegacy);
        when(shoeCoreLegacy.search(ArgumentMatchers.any(ShoeFilter.class))).thenReturn(Shoes.builder()
                .shoes(List.of(Shoe.builder()
                        .name("Legacy shoe")
                        .color(ShoeFilter.Color.BLUE)
                        .size(BigInteger.ONE)
                        .build()))
                .build());
        mockMvc.perform(get("/shoes/search")
                .header("version", 1))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void get_new_core_response_ok() throws Exception {
        when(shoeFacade.get(2)).thenReturn(shoeCoreNew);
        when(shoeCoreNew.search(ArgumentMatchers.any(ShoeFilter.class))).thenReturn(Shoes.builder()
                .shoes(List.of(Shoe.builder()
                        .name("New shoe")
                        .color(ShoeFilter.Color.BLACK)
                        .size(BigInteger.TWO)
                        .build()))
                .build());
        mockMvc.perform(get("/shoes/search")
                .header("version", 2))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
