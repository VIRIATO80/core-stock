package com.example.demo.dto.in;

import com.example.demo.dto.out.StockedShoeDto;
import com.sun.istack.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class PatchAllStoreRequest {

    @NotNull
    List<StockedShoeDto> shoes;
}
