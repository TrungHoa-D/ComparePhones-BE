package com.example.projectbase.service.impl;

import com.example.projectbase.constant.CommonConstant;
import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.domain.dto.request.PhoneCreateDto;
import com.example.projectbase.domain.dto.request.PhoneUpdateDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.dto.response.PhoneResponseDto;
import com.example.projectbase.domain.entity.*;
import com.example.projectbase.domain.mapper.PhoneMapper;
import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.repository.*;
import com.example.projectbase.service.PhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        Screen screen = getScreen(phoneCreateDto);
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

        Connection connection = getConnection(phoneCreateDto);
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

        OtherInfor otherInfor = getOtherInfor(phoneCreateDto);
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

    private static OtherInfor getOtherInfor(PhoneCreateDto phoneCreateDto) {
        OtherInfor otherInfor = new OtherInfor();
        otherInfor.setCooler(phoneCreateDto.getOtherInforRequestDto().getCooler());
        otherInfor.setResistanceIndex(phoneCreateDto.getOtherInforRequestDto().getResistanceIndex());
        otherInfor.setTech(phoneCreateDto.getOtherInforRequestDto().getTech());
        otherInfor.setSoundTech(phoneCreateDto.getOtherInforRequestDto().getSoundTech());
        otherInfor.setUtilities(phoneCreateDto.getOtherInforRequestDto().getUtilities());
        otherInfor.setSensor(phoneCreateDto.getOtherInforRequestDto().getSensor());
        return otherInfor;
    }

    private static Screen getScreen(PhoneCreateDto phoneCreateDto) {
        Screen screen = new Screen();
        screen.setResolution(phoneCreateDto.getScreenRequestDto().getResolution());
        screen.setSize(phoneCreateDto.getScreenRequestDto().getSize());
        screen.setScreen(phoneCreateDto.getScreenRequestDto().getScreen());
        screen.setFeatures(phoneCreateDto.getScreenRequestDto().getFeatures());
        screen.setScanFrequency(phoneCreateDto.getScreenRequestDto().getScanFrequency());
        screen.setType(phoneCreateDto.getScreenRequestDto().getType());
        return screen;
    }

    private static Connection getConnection(PhoneCreateDto phoneCreateDto) {
        Connection connection = new Connection();
        connection.setMobile_nfc(phoneCreateDto.getConnectionRequestDto().getMobile_nfc());
        connection.setSim(phoneCreateDto.getConnectionRequestDto().getSim());
        connection.setOs(phoneCreateDto.getConnectionRequestDto().getOs());
        connection.setNetwork(phoneCreateDto.getConnectionRequestDto().getNetwork());
        connection.setWlan(phoneCreateDto.getConnectionRequestDto().getWlan());
        connection.setBluetooth(phoneCreateDto.getConnectionRequestDto().getBluetooth());
        connection.setGps(phoneCreateDto.getConnectionRequestDto().getGps());
        return connection;
    }

    @Override
    public List<PhoneResponseDto> getAllPhones() {
        return phoneRepository.findAllPhonesResponse();
    }

    @Override
    public List<PhoneResponseDto> getFilteredPhones(Map<String, Object> filters) {
        Specification<Phone> spec = buildSpecification(filters);
        List<Phone> phones = phoneRepository.findAll(spec);
        return phoneMapper.toPhoneResponseDtoList(phones);
    }

    public Specification<Phone> buildSpecification(Map<String, Object> filters) {
        return (Root<Phone> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                String field = entry.getKey();
                Object value = entry.getValue();

                if (field != null && value != null) {
                    try {
                        // Xử lý String: tìm kiếm với like, không phân biệt chữ hoa chữ thường
                        if (value instanceof String) {
                            predicates.add(cb.like(cb.lower(root.get(field)), "%" + ((String) value).toLowerCase() + "%"));
                        }
                        // Xử lý Integer, Long, Double, Float, Boolean: so sánh trực tiếp
                        else if (value instanceof Integer) {
                            predicates.add(cb.equal(root.get(field), value));
                        } else if (value instanceof Long) {
                            predicates.add(cb.equal(root.get(field), value));
                        } else if (value instanceof Double) {
                            predicates.add(cb.greaterThanOrEqualTo(root.get(field).as(Double.class), (Double) value));
                        } else if (value instanceof Float) {
                            predicates.add(cb.greaterThanOrEqualTo(root.get(field).as(Float.class), (Float) value));
                        } else if (value instanceof Boolean) {
                            predicates.add(cb.equal(root.get(field), value));
                        }
                        // Xử lý Object: có thể là các trường hợp khác, ví dụ như List, Map hoặc các kiểu dữ liệu phức tạp
                        else {
                            // Đây có thể là các kiểu dữ liệu phức tạp khác
                            // Ví dụ: xử lý Object tùy chỉnh hoặc các giá trị khác
                            System.err.println("Chưa xử lý kiểu Object cho field: " + field);
                        }
                    } catch (IllegalArgumentException e) {
                        System.err.println("Field không tồn tại hoặc không tương thích: " + field);
                    }
                }
            }

            return predicates.isEmpty() ? cb.conjunction() : cb.and(predicates.toArray(new Predicate[0]));
        };
    }



    @Override
    public List<PhoneResponseDto> getPhonesByBrand(String brand) {
        return phoneRepository.findAllByBrandIgnoreCase(brand);
    }

    @Override
    public List<PhoneResponseDto> getPhonesByName(String name) {
        return phoneRepository.findAllByNameContainingIgnoreCase(name);
    }

    @Override
    public List<PhoneResponseDto> getPhoneByPriceRange(String from, String to) {
        return phoneRepository.findPhonesWithinPriceRange(from,to);
    }

    @Override
    public List<PhoneResponseDto> getPhoneByRAM(String ram) {
        return phoneRepository.findPhonesByRamContainingIgnoreCase(ram);
    }

    @Override
    public List<PhoneResponseDto> getPhoneByROM(String rom) {
        return phoneRepository.findPhonesByInternalMemoryContainingIgnoreCase(rom);
    }

    @Override
    public List<PhoneResponseDto> getPhoneByScreen(String screen) {
        return phoneRepository.findPhonesByScreenContainingIgnoreCase(screen);
    }

    @Override
    public Phone getPhoneById(String id) {
        return phoneRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.Phone.ERR_NOT_FOUND_ID));
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
