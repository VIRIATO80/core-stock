package com.example.demo.core;

import com.example.demo.dto.in.PatchAllStoreRequest;
import com.example.demo.dto.out.StockDto;

public interface StockCore {

    StockDto get();

    StockDto patch(PatchAllStoreRequest stock);
}