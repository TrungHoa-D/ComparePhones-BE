package com.example.projectbase.service;

import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationResponseDto;
import com.example.projectbase.domain.dto.request.PhoneCreateDto;
import com.example.projectbase.domain.dto.request.PhoneUpdateDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.dto.response.PhoneResponseDto;

public interface PhoneService {
    PhoneResponseDto createPhone(PhoneCreateDto phoneCreateDto);
    PaginationResponseDto<PhoneResponseDto> getAllPhones(PaginationFullRequestDto paginationFullRequestDto);
    PaginationResponseDto<PhoneResponseDto> getPhonesByBrand(String brand,PaginationFullRequestDto request);
    PaginationResponseDto<PhoneResponseDto> getPhonesByName(String name, PaginationFullRequestDto request);
    PhoneResponseDto updatePhone(PhoneUpdateDto phoneUpdateDto);
    CommonResponseDto deletePhone(String phoneId);

}
