package com.example.projectbase.service;

import com.example.projectbase.domain.dto.response.CommonResponseDto;

public interface DataCrawlingService {
    CommonResponseDto fetchDataFromApi();
}
