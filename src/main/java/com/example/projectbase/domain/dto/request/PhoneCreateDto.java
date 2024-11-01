package com.example.projectbase.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PhoneCreateDto {
    private String name;
    private String brand;
    private String releaseDate;
    private String cost;
    private String img;
    private String color;
    private ScreenRequestDto screenRequestDto;
    private CameraRequestDto cameraRequestDto;
    private ProcessorRequestDto processorRequestDto;
    private ConnectionRequestDto connectionRequestDto;
    private StorageRequestDto storageRequestDto;
    private BatteryRequestDto batteryRequestDto;
    private DesignRequestDto designRequestDto;
    private OtherInforRequestDto otherInforRequestDto;
}
