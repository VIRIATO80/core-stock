package com.example.demo.controller;

import com.example.demo.contract.StockControllerContract;
import com.example.demo.dto.in.PatchAllStoreRequest;
import com.example.demo.dto.out.StockDto;
import com.example.demo.facade.StockFacade;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/stock")
@RequiredArgsConstructor
@Api
public class StockController implements StockControllerContract {

    private final StockFacade stockFacade;

    @GetMapping
    @Override
    public ResponseEntity<StockDto> get(@RequestHeader Integer version) {

        return ResponseEntity.ok(stockFacade.get(version).get());

    }

    @PatchMapping
    @Override
    public ResponseEntity<StockDto> patch(@RequestHeader Integer version, @RequestBody PatchAllStoreRequest patch) {
        return ResponseEntity.ok(stockFacade.get(version).patch(patch));
    }

}
