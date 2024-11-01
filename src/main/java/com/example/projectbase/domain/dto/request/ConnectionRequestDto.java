package com.example.projectbase.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConnectionRequestDto {
    private String mobile_nfc;
    private String sim;
    private String os;
    private String network;
    private String wlan;
    private String bluetooth;
    private String gps;
}
