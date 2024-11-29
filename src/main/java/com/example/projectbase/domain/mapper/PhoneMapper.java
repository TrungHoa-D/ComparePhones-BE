package com.example.projectbase.domain.mapper;

import com.example.projectbase.domain.dto.response.PhoneResponseDto;
import com.example.projectbase.domain.entity.Phone;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PhoneMapper {
    PhoneResponseDto toPhoneResponseDto(Phone phone);
    List<PhoneResponseDto> toPhoneResponseDtoList(List<Phone> phones);
}
