package com.example.projectbase.controller;

import com.example.projectbase.base.RestApiV1;
import com.example.projectbase.base.VsResponseUtil;
import com.example.projectbase.service.DataCrawlingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@RestApiV1
public class DataCrawlingController {
    private final DataCrawlingService dataCrawlingService;

    @Tag(name = "dataCrawling-controller-admin")
    @Operation(summary = "API get data")
    @GetMapping("/data")
    public ResponseEntity<?> getData() {
        return VsResponseUtil.success(dataCrawlingService.fetchDataFromApi());
    }
}
