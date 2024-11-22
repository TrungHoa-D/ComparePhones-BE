package com.example.projectbase.service;

import com.example.projectbase.domain.dto.request.PhoneCreateDto;
import com.example.projectbase.domain.dto.request.PhoneUpdateDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.dto.response.PhoneResponseDto;
import com.example.projectbase.domain.entity.Phone;

import java.util.List;

public interface PhoneService {
    PhoneResponseDto createPhone(PhoneCreateDto phoneCreateDto);
    List<PhoneResponseDto> getAllPhones();
    List<PhoneResponseDto> getPhonesByBrand(String brand);
    List<PhoneResponseDto> getPhonesByName(String name);
    List<PhoneResponseDto> getPhoneByPriceRange(String from, String to);
    Phone getPhoneById(String id);
    PhoneResponseDto updatePhone(PhoneUpdateDto phoneUpdateDto);
    CommonResponseDto deletePhone(String phoneId);

}
