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
    List<Phone> getPhonesByBrand(String brand);
    List<Phone> getPhonesByName(String name);
    PhoneResponseDto updatePhone(PhoneUpdateDto phoneUpdateDto);
    CommonResponseDto deletePhone(String phoneId);

}
