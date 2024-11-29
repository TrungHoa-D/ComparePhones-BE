package com.example.projectbase.domain.mapper;

import com.example.projectbase.domain.dto.response.PhoneResponseDto;
import com.example.projectbase.domain.entity.Phone;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-29T11:38:01+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.13 (Ubuntu)"
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

        return phoneResponseDto;
    }

    @Override
    public List<PhoneResponseDto> toPhoneResponseDtoList(List<Phone> phones) {
        if ( phones == null ) {
            return null;
        }

        List<PhoneResponseDto> list = new ArrayList<PhoneResponseDto>( phones.size() );
        for ( Phone phone : phones ) {
            list.add( toPhoneResponseDto( phone ) );
        }

        return list;
    }
}
