package com.example.projectbase.domain.mapper;

import com.example.projectbase.domain.dto.response.PhoneResponseDto;
import com.example.projectbase.domain.entity.Phone;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-05T01:28:38+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.13 (Amazon.com Inc.)"
)
@Component
public class PhoneMapperImpl implements PhoneMapper {

    @Override
    public PhoneResponseDto toPhoneResponseDto(Phone phone) {
        if ( phone == null ) {
            return null;
        }

        PhoneResponseDto phoneResponseDto = new PhoneResponseDto();

        phoneResponseDto.setId( phone.getId() );
        phoneResponseDto.setName( phone.getName() );
        phoneResponseDto.setBrand( phone.getBrand() );
        phoneResponseDto.setReleaseDate( phone.getReleaseDate() );
        phoneResponseDto.setCost( phone.getCost() );
        phoneResponseDto.setImg( phone.getImg() );
        phoneResponseDto.setColor( phone.getColor() );
        phoneResponseDto.setScreen( phone.getScreen() );
        phoneResponseDto.setCamera( phone.getCamera() );
        phoneResponseDto.setProcessor( phone.getProcessor() );
        phoneResponseDto.setConnection( phone.getConnection() );
        phoneResponseDto.setStorage( phone.getStorage() );
        phoneResponseDto.setBattery( phone.getBattery() );
        phoneResponseDto.setDesign( phone.getDesign() );
        phoneResponseDto.setOtherInfor( phone.getOtherInfor() );

        return phoneResponseDto;
    }
}
