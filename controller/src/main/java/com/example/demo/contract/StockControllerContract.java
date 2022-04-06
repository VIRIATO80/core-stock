package com.example.demo.contract;

import com.example.demo.dto.in.PatchAllStoreRequest;
import com.example.demo.dto.out.StockDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface StockControllerContract {

    @ApiOperation(
            nickname = "Get all shoes in the existing stock",
            tags = {"Stock"},
            response = StockDto.class,
            value = "Get all shoes in the existing stock"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = StockDto.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")
    })
    ResponseEntity<StockDto> get(Integer version);

    @ApiOperation(
            nickname = "Update whole existing stock",
            tags = {"Stock"},
            response = StockDto.class,
            value = "Update whole existing stock"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = StockDto.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")
    })
    ResponseEntity<StockDto> patch(Integer version, PatchAllStoreRequest patch);
}

