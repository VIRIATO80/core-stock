package com.example.demo.dto.out;

import com.example.demo.entity.Stock.State;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class StockDto {

    State state;
    List<StockedShoeDto> shoes;

}
