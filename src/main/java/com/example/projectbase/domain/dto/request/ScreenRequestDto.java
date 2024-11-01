package com.example.projectbase.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ScreenRequestDto {
    private String resolution;
    private String size;
    private String screen;
    private String features;
    private String scanFrequency;
    private String type;
}
