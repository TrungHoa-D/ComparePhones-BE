package com.example.projectbase.service.impl;

import com.example.projectbase.constant.CommonConstant;
import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationResponseDto;
import com.example.projectbase.domain.dto.pagination.PagingMeta;
import com.example.projectbase.domain.dto.request.PhoneCreateDto;
import com.example.projectbase.domain.dto.request.PhoneUpdateDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.dto.response.PhoneResponseDto;
import com.example.projectbase.domain.entity.*;
import com.example.projectbase.domain.mapper.PhoneMapper;
import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.repository.*;
import com.example.projectbase.service.PhoneService;
import com.example.projectbase.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PhoneServiceImpl implements PhoneService {
    private final PhoneRepository phoneRepository;
    private final ScreenRepository screenRepository;
    private final CameraRepository cameraRepository;
    private final ProcessorRepository processorRepository;
    private final ConnectionRepository connectionRepository;
    private final StorageRepository storageRepository;
    private final BatteryRepository batteryRepository;
    private final DesignRepository designRepository;
    private final OtherInforRepository otherInforRepository;

    private final PhoneMapper phoneMapper;

    @Override
    public PhoneResponseDto createPhone(PhoneCreateDto phoneCreateDto) {
        Screen screen = new Screen();
        screen.setResolution(phoneCreateDto.getScreenRequestDto().getResolution());
        screen.setSize(phoneCreateDto.getScreenRequestDto().getSize());
        screen.setScreen(phoneCreateDto.getScreenRequestDto().getScreen());
        screen.setFeatures(phoneCreateDto.getScreenRequestDto().getFeatures());
        screen.setScanFrequency(phoneCreateDto.getScreenRequestDto().getScanFrequency());
        screen.setType(phoneCreateDto.getScreenRequestDto().getType());
        screenRepository.save(screen);

        Camera camera = new Camera();
        camera.setMainCamera(phoneCreateDto.getCameraRequestDto().getMainCamera());
        camera.setSelfieCamera(phoneCreateDto.getCameraRequestDto().getSelfieCamera());
        cameraRepository.save(camera);

        Processor processor = new Processor();
        processor.setChipset(phoneCreateDto.getProcessorRequestDto().getChipset());
        processor.setCpu(phoneCreateDto.getProcessorRequestDto().getCpu());
        processor.setGpu(phoneCreateDto.getProcessorRequestDto().getGpu());
        processorRepository.save(processor);

        Connection connection = new Connection();
        connection.setMobile_nfc(phoneCreateDto.getConnectionRequestDto().getMobile_nfc());
        connection.setSim(phoneCreateDto.getConnectionRequestDto().getSim());
        connection.setOs(phoneCreateDto.getConnectionRequestDto().getOs());
        connection.setNetwork(phoneCreateDto.getConnectionRequestDto().getNetwork());
        connection.setWlan(phoneCreateDto.getConnectionRequestDto().getWlan());
        connection.setBluetooth(phoneCreateDto.getConnectionRequestDto().getBluetooth());
        connection.setGps(phoneCreateDto.getConnectionRequestDto().getGps());
        connectionRepository.save(connection);

        Storage storage = new Storage();
        storage.setRam(phoneCreateDto.getStorageRequestDto().getRam());
        storage.setInternalMemory(phoneCreateDto.getStorageRequestDto().getInternalMemory());
        storage.setMemoryCardSlot(phoneCreateDto.getStorageRequestDto().getMemoryCardSlot());
        storageRepository.save(storage);

        Battery battery = new Battery();
        battery.setBattery(phoneCreateDto.getBatteryRequestDto().getBattery());
        battery.setCharginTechnology(phoneCreateDto.getBatteryRequestDto().getCharginTechnology());
        battery.setPort(phoneCreateDto.getBatteryRequestDto().getPort());
        batteryRepository.save(battery);

        Design design = new Design();
        design.setSize(phoneCreateDto.getDesignRequestDto().getSize());
        design.setWeight(phoneCreateDto.getDesignRequestDto().getWeight());
        design.setMaterial(phoneCreateDto.getDesignRequestDto().getMaterial());
        designRepository.save(design);

        OtherInfor otherInfor = new OtherInfor();
        otherInfor.setCooler(phoneCreateDto.getOtherInforRequestDto().getCooler());
        otherInfor.setResistanceIndex(phoneCreateDto.getOtherInforRequestDto().getResistanceIndex());
        otherInfor.setTech(phoneCreateDto.getOtherInforRequestDto().getTech());
        otherInfor.setSoundTech(phoneCreateDto.getOtherInforRequestDto().getSoundTech());
        otherInfor.setUtilities(phoneCreateDto.getOtherInforRequestDto().getUtilities());
        otherInfor.setSensor(phoneCreateDto.getOtherInforRequestDto().getSensor());
        otherInforRepository.save(otherInfor);

        Phone phone = Phone.builder()
                .name(phoneCreateDto.getName())
                .brand(phoneCreateDto.getBrand())
                .releaseDate(phoneCreateDto.getReleaseDate())
                .cost(phoneCreateDto.getCost())
                .img(phoneCreateDto.getImg())
                .color(phoneCreateDto.getColor())
                .screen(screen)
                .camera(camera)
                .processor(processor)
                .connection(connection)
                .storage(storage)
                .battery(battery)
                .design(design)
                .otherInfor(otherInfor)
                .build();
        return phoneMapper.toPhoneResponseDto(phoneRepository.save(phone));
    }

    @Override
    public PaginationResponseDto<PhoneResponseDto> getAllPhones(PaginationFullRequestDto paginationRequestDto) {
        Pageable pageable = PaginationUtil.buildPageable(paginationRequestDto);
        Page<Phone> phonesPage = phoneRepository.findAll(pageable);
        PagingMeta pagingMeta = new PagingMeta(
                phonesPage.getTotalElements(),
                phonesPage.getTotalPages(),
                phonesPage.getNumber(),
                phonesPage.getSize(),
                paginationRequestDto.getSortBy(),
                paginationRequestDto.getIsAscending().toString()
        );
        List<PhoneResponseDto> phonesResponseDtoList= phonesPage.stream().map(phoneMapper::toPhoneResponseDto).collect(Collectors.toList());
        return new PaginationResponseDto<>(pagingMeta, phonesResponseDtoList);
    }

    @Override
    public PaginationResponseDto<PhoneResponseDto> getPhonesByBrand(String brand, PaginationFullRequestDto request) {
        Pageable pageable = PaginationUtil.buildPageable(request);
        Page<Phone> phonesPage = phoneRepository.findAllByBrandIgnoreCase(brand,pageable);
        PagingMeta pagingMeta = new PagingMeta(
                phonesPage.getTotalElements(),
                phonesPage.getTotalPages(),
                phonesPage.getNumber(),
                phonesPage.getSize(),
                request.getSortBy(),
                request.getIsAscending().toString()
        );
        List<PhoneResponseDto> phonesResponseDtoList= phonesPage.stream().map(phoneMapper::toPhoneResponseDto).collect(Collectors.toList());
        return new PaginationResponseDto<>(pagingMeta, phonesResponseDtoList);
    }

    @Override
    public PaginationResponseDto<PhoneResponseDto> getPhonesByName(String name, PaginationFullRequestDto request) {
        Pageable pageable = PaginationUtil.buildPageable(request);
        Page<Phone> phonesPage = phoneRepository.findAllByNameContainingIgnoreCase(name, pageable);
        PagingMeta pagingMeta = new PagingMeta(
                phonesPage.getTotalElements(),
                phonesPage.getTotalPages(),
                phonesPage.getNumber(),
                phonesPage.getSize(),
                request.getSortBy(),
                request.getIsAscending().toString()
        );
        List<PhoneResponseDto> phonesResponseDtoList= phonesPage.stream().map(phoneMapper::toPhoneResponseDto).collect(Collectors.toList());
        return new PaginationResponseDto<>(pagingMeta, phonesResponseDtoList);
    }

    @Override
    public PhoneResponseDto updatePhone(PhoneUpdateDto phoneUpdateDto) {
        Phone phone = phoneRepository.findById(phoneUpdateDto.getId())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Phone.ERR_NOT_FOUND_ID));
        phone.setName(phoneUpdateDto.getName());
        phone.setBrand(phoneUpdateDto.getBrand());
        phone.setReleaseDate(phoneUpdateDto.getReleaseDate());
        phone.setCost(phoneUpdateDto.getCost());
        phone.setImg(phoneUpdateDto.getImg());
        phone.setColor(phoneUpdateDto.getColor());
        return phoneMapper.toPhoneResponseDto(phoneRepository.save(phone));
    }

    @Override
    public CommonResponseDto deletePhone(String phoneId) {
        Phone phone = phoneRepository.findById(phoneId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Phone.ERR_NOT_FOUND_ID));
        phoneRepository.delete(phone);
        return new CommonResponseDto(true, CommonConstant.SERVICE_SUCCESS);
    }
}
