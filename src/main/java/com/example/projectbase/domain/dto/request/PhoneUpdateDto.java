package com.example.projectbase.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PhoneUpdateDto {
    private String id;
    private String name;
    private String brand;
    private String releaseDate;
    private String cost;
    private String img;
    private String color;
}
