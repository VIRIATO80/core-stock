package com.example.demo.controller;

import com.example.demo.dto.in.PatchAllStoreRequest;
import com.example.demo.dto.out.StockDto;
import com.example.demo.facade.StockFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockFacade stockFacade;

    @GetMapping
    public ResponseEntity<StockDto> get(@RequestHeader Integer version) {

        return ResponseEntity.ok(stockFacade.get(version).get());

    }

    @PatchMapping
    public ResponseEntity<StockDto> patch(@RequestHeader Integer version, @RequestBody PatchAllStoreRequest patch) {
        return ResponseEntity.ok(stockFacade.get(version).patch(patch));
    }

}
