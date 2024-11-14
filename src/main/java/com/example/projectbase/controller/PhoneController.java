package com.example.projectbase.controller;

import com.example.projectbase.base.RestApiV1;
import com.example.projectbase.base.VsResponseUtil;
import com.example.projectbase.constant.UrlConstant;
import com.example.projectbase.domain.dto.request.PhoneCreateDto;
import com.example.projectbase.domain.dto.request.PhoneUpdateDto;
import com.example.projectbase.service.PhoneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestApiV1
public class PhoneController {
    private final PhoneService phoneService;

    @Tag(name = "phone-controller-admin")
    @Operation(summary = "API create new phone")
    @PostMapping(UrlConstant.Phone.CREATE_PHONE)
    public ResponseEntity<?> createPhone(@RequestBody PhoneCreateDto phoneCreateDto) {
        return VsResponseUtil.success(phoneService.createPhone(phoneCreateDto));
    }

    @Tags({@Tag(name = "phone-controller-admin"), @Tag(name = "phone-controller")})
    @Operation(summary = "API get all phones")
    @GetMapping(UrlConstant.Phone.GET_ALL_PHONE)
    public ResponseEntity<?> getAllPhone() {
        return VsResponseUtil.success(phoneService.getAllPhones());
    }

    @Tags({@Tag(name = "phone-controller-admin"), @Tag(name = "phone-controller")})
    @Operation(summary = "API get phones by brand")
    @GetMapping(UrlConstant.Phone.GET_PHONE_BY_BRAND)
    public ResponseEntity<?> getPhonesByBrand(@RequestParam String brand) {
        return VsResponseUtil.success(phoneService.getPhonesByBrand(brand));
    }

    @Tags({@Tag(name = "phone-controller-admin"), @Tag(name = "phone-controller")})
    @Operation(summary = "API get phones by name")
    @GetMapping(UrlConstant.Phone.GET_PHONE_BY_NAME)
    public ResponseEntity<?> getPhonesByName(@RequestParam String name) {
        return VsResponseUtil.success(phoneService.getPhonesByName(name));
    }

    @Tag(name = "phone-controller-admin")
    @Operation(summary = "API update phone")
    @PutMapping(UrlConstant.Phone.UPDATE_PHONE)
    public ResponseEntity<?> updatePhone(@RequestBody PhoneUpdateDto phoneUpdateDto) {
        return VsResponseUtil.success(phoneService.updatePhone(phoneUpdateDto));
    }

    @Tag(name = "phone-controller-admin")
    @Operation(summary = "API delete phone")
    @DeleteMapping(UrlConstant.Phone.DELETE_PHONE)
    public ResponseEntity<?> deletePhone(@RequestParam String phoneId) {
        return VsResponseUtil.success(phoneService.deletePhone(phoneId));
    }
}
