package com.example.projectbase.service.impl;

import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.entity.*;
import com.example.projectbase.domain.entity.craw.Data;
import com.example.projectbase.domain.entity.craw.Product;
import com.example.projectbase.domain.entity.craw.SomeThing;
import com.example.projectbase.repository.*;
import com.example.projectbase.service.DataCrawlingService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static java.lang.Long.*;

@Service
@RequiredArgsConstructor
public class DataCrawlingServiceImpl implements DataCrawlingService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final PhoneRepository phoneRepository;
    private final ScreenRepository screenRepository;
    private final CameraRepository cameraRepository;
    private final ProcessorRepository processorRepository;
    private final ConnectionRepository connectionRepository;
    private final StorageRepository storageRepository;
    private final BatteryRepository batteryRepository;
    private final DesignRepository designRepository;
    private final OtherInforRepository otherInforRepository;

    @Override
    public CommonResponseDto fetchDataFromApi() {
        String url = "https://api.cellphones.com.vn/v2/graphql/query";

        HttpHeaders headers = getHttpHeaders();

        // Tạo body cho yêu cầu
        String requestBody = "{\"query\":\"\\n            query GetProductsByCateId{\\n                products(\\n                        filter: {\\n                            static: {\\n                                categories: [\\\"3\\\"],\\n                                province_id: 30, \\n                                stock: {\\n                                   from: 0\\n                                },\\n                                stock_available_id: [46, 56, 152, 4164],\\n                               filter_price: {from:0to:54990000}\\n                            },\\n                            dynamic: {                                use_nice_uri: true\\n                            }\\n                        },\\n                        size: 1146,\\n                        sort: [{view: desc}]\\n                    )\\n                {\\n                    general{\\n                        product_id\\n                        name\\n                        attributes\\n                        sku\\n                        doc_quyen\\n                        manufacturer\\n                        url_key\\n                        url_path\\n                        categories{\\n                            categoryId\\n                        }\\n                        review{\\n                            total_count\\n                            average_rating\\n                        }\\n                    },\\n                    filterable{\\n                        is_installment\\n                        stock_available_id\\n                        company_stock_id\\n                        filter {\\n                           id\\n                           Label\\n                        }\\n                        is_parent\\n                        exclusive_prices\\n                        price\\n                        prices\\n                        special_price\\n                        promotion_information\\n                        thumbnail\\n                        promotion_pack\\n                        sticker\\n                        flash_sale_types  \\n                    },\\n                }\\n            }\",\"variables\":{}}";

        // Tạo đối tượng HttpEntity với headers và body
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        // Gửi yêu cầu POST và nhận phản hồi
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        // Xử lý phản hồi
        Gson gson = new Gson();
        String json = response.getBody();
        SomeThing someThing = gson.fromJson(json, SomeThing.class);
        Data data =someThing.getData();
        String mess= "";
        for(Product product : data.getProducts()) {
            Phone phone = getPhone(product);
            phoneRepository.save(phone);
            mess+= phone.toString() +'\n';
        }
        return new CommonResponseDto(true,mess);
    }

    private static HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept", "application/json");
        headers.set("accept-language", "vi,en;q=0.9,en-US;q=0.8");
        headers.set("content-type", "application/json");
        headers.set("origin", "https://cellphones.com.vn");
        headers.set("priority", "u=1, i");
        headers.set("referer", "https://cellphones.com.vn/");
        headers.set("sec-ch-ua", "\"Microsoft Edge\";v=\"129\", \"Not=A?Brand\";v=\"8\", \"Chromium\";v=\"129\"");
        headers.set("sec-ch-ua-mobile", "?1");
        headers.set("sec-ch-ua-platform", "\"Android\"");
        headers.set("sec-fetch-dest", "empty");
        headers.set("sec-fetch-mode", "cors");
        headers.set("sec-fetch-site", "same-site");
        headers.set("user-agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.0.0 Mobile Safari/537.36 Edg/129.0.0.0");
        return headers;
    }

    private Phone getPhone(Product product) {
        Phone phone = new Phone();
        phone.setName(product.getGeneral().getName());
        phone.setBrand(product.getGeneral().getManufacturer());
        phone.setReleaseDate(product.getGeneral().getAttributes().getMobile_ra_mat());
        if(product.getFilterable().getPrices() != null) {
            phone.setCost(product.getFilterable().getPrices().getRoot().getValue());
        }
        else if(product.getFilterable().getPrice()!= 0) {
            phone.setCost((int)product.getFilterable().getPrice());
        }
        else {
            phone.setCost(-1);
        }
        phone.setImg(product.getGeneral().getAttributes().getAds_base_image());
        phone.setColor(product.getGeneral().getAttributes().getIphone_chat_lieu());

        Screen screen = new Screen();
        screen.setResolution(product.getGeneral().getAttributes().getDisplay_resolution());
        screen.setSize(product.getGeneral().getAttributes().getDisplay_size());
        screen.setScreen(product.getGeneral().getAttributes().getMobile_type_of_display());
        screen.setFeatures(product.getGeneral().getAttributes().getMobile_display_features());
        screen.setScanFrequency(product.getGeneral().getAttributes().getMobile_tan_so_quet());
        screen.setType(product.getGeneral().getAttributes().getMobile_kieu_man_hinh());
        screenRepository.save(screen);

        Camera camera = new Camera();
        camera.setMainCamera(product.getGeneral().getAttributes().getCamera_primary());
        camera.setSelfieCamera(product.getGeneral().getAttributes().getCamera_secondary());
        cameraRepository.save(camera);

        Processor processor = new Processor();
        processor.setChipset(product.getGeneral().getAttributes().getChipset());
        processor.setCpu(product.getGeneral().getAttributes().getCpu());
        processor.setGpu(product.getGeneral().getAttributes().getGpu());
        processorRepository.save(processor);

        Connection connection = new Connection();
        connection.setMobile_nfc(product.getGeneral().getAttributes().getMobile_nfc());
        connection.setSim(product.getGeneral().getAttributes().getSim());
        connection.setOs(product.getGeneral().getAttributes().getOs_version());
        connection.setNetwork(product.getGeneral().getAttributes().getLoai_mang());
        connection.setWlan(product.getGeneral().getAttributes().getWlan());
        connection.setBluetooth(product.getGeneral().getAttributes().getBluetooth());
        connection.setGps(product.getGeneral().getAttributes().getGps());
        connectionRepository.save(connection);

        Storage storage = new Storage();
        storage.setRam(product.getGeneral().getAttributes().getMemory_internal());
        storage.setInternalMemory(product.getGeneral().getAttributes().getStorage());
        storage.setMemoryCardSlot(product.getGeneral().getAttributes().getMemory_card_slot());
        storageRepository.save(storage);

        Battery battery = new Battery();
        battery.setBattery(product.getGeneral().getAttributes().getBattery());
        battery.setCharginTechnology(product.getGeneral().getAttributes().getMobile_cong_nghe_sac());
        battery.setPort(product.getGeneral().getAttributes().getMobile_cong_sac());
        batteryRepository.save(battery);

        Design design = new Design();
        design.setSize(product.getGeneral().getAttributes().getDimensions());
        design.setWeight(product.getGeneral().getAttributes().getProduct_weight());
        design.setMaterial(product.getGeneral().getAttributes().getMobile_chat_lieu_mat_lung() +", "+ product.getGeneral().getAttributes().getMobile_chat_lieu_khung_vien());
        designRepository.save(design);

        OtherInfor otherInfor = new OtherInfor();
        otherInfor.setCooler(product.getGeneral().getAttributes().getMobile_ht_lam_mat());
        otherInfor.setResistanceIndex(product.getGeneral().getAttributes().getMobile_khang_nuoc_bui());
        otherInfor.setTech(product.getGeneral().getAttributes().getHub_cong_nghe());
        otherInfor.setSoundTech(product.getGeneral().getAttributes().getMobile_am_thanh());
        otherInfor.setUtilities(product.getGeneral().getAttributes().getTablet_tien_ich());
        otherInfor.setSensor(
                product.getGeneral().getAttributes().getMobile_cam_bien_van_tay() + ", " +
                        product.getGeneral().getAttributes().getMobile_cam_bien() + ", " +
                        product.getGeneral().getAttributes().getMobile_tinh_nang_dac_biet()
        );
        otherInforRepository.save(otherInfor);

        phone.setScreen(screen);
        phone.setCamera(camera);
        phone.setProcessor(processor);
        phone.setConnection(connection);
        phone.setStorage(storage);
        phone.setBattery(battery);
        phone.setDesign(design);
        phone.setOtherInfor(otherInfor);
        return phone;
    }
}
