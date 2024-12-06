package com.example.projectbase.service;

import com.example.projectbase.domain.dto.request.PhoneCreateDto;
import com.example.projectbase.domain.dto.request.PhoneUpdateDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.dto.response.PhoneResponseDto;
import com.example.projectbase.domain.entity.Phone;

import java.util.List;
import java.util.Map;

public interface PhoneService {
    PhoneResponseDto createPhone(PhoneCreateDto phoneCreateDto);
    List<PhoneResponseDto> getAllPhones();
    List<PhoneResponseDto> getFilteredPhones(Map<String, Object> filters);
    List<PhoneResponseDto> getPhonesByBrand(String brand);
    List<PhoneResponseDto> getPhonesByName(String name);
    List<PhoneResponseDto> getPhoneByPriceRange(int from, int to);
    List<PhoneResponseDto> getPhoneByRAM(String ram);
    List<PhoneResponseDto> getPhoneByROM(String rom);
    List<PhoneResponseDto> getPhoneByScreen(String screen);
    Phone getPhoneById(String id);
    PhoneResponseDto updatePhone(PhoneUpdateDto phoneUpdateDto);
    CommonResponseDto deletePhone(String phoneId);

}
