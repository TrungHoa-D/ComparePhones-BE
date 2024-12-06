package com.example.projectbase.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PhoneResponseDto {
    private String id;
    private String name;
    private String brand;
    private String releaseDate;
    private int cost;
    private String img;
    private String color;
}
