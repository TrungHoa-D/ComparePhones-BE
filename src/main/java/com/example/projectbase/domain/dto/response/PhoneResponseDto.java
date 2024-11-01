package com.example.projectbase.domain.dto.response;

import com.example.projectbase.domain.entity.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Setter
@Getter
public class PhoneResponseDto {
    private String id;
    private String name;
    private String brand;
    private String releaseDate;
    private String cost;
    private String img;
    private String color;
    private Screen screen;
    private Camera camera;
    private Processor processor;
    private Connection connection;
    private Storage storage;
    private Battery battery;
    private Design design;
    private OtherInfor otherInfor;
}
