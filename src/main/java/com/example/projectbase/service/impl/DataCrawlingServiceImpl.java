package com.example.projectbase.service.impl;

import com.example.projectbase.service.DataCrawlingService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
//@RequiredArgsConstructor
public class DataCrawlingServiceImpl implements DataCrawlingService {
    private final RestTemplate restTemplate;

    public DataCrawlingServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public String fetchDataFromApi() {
        String url = "https://api.cellphones.com.vn/v2/graphql/query";

        // Tạo headers cho yêu cầu
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

        // Tạo body cho yêu cầu
        String requestBody = "{\"query\":\"\\n            query GetProductsByCateId{\\n                products(\\n                        filter: {\\n                            static: {\\n                                categories: [\\\"3\\\"],\\n                                province_id: 30, \\n                                stock: {\\n                                   from: 0\\n                                },\\n                                stock_available_id: [46, 56, 152, 4164],\\n                               filter_price: {from:0to:54990000}\\n                            },\\n                            dynamic: {                                use_nice_uri: true\\n                            }\\n                        },\\n                        size: 10,\\n                        sort: [{view: desc}]\\n                    )\\n                {\\n                    general{\\n                        product_id\\n                        name\\n                        attributes\\n                        sku\\n                        doc_quyen\\n                        manufacturer\\n                        url_key\\n                        url_path\\n                        categories{\\n                            categoryId\\n                        }\\n                        review{\\n                            total_count\\n                            average_rating\\n                        }\\n                    },\\n                    filterable{\\n                        is_installment\\n                        stock_available_id\\n                        company_stock_id\\n                        filter {\\n                           id\\n                           Label\\n                        }\\n                        is_parent\\n                        exclusive_prices\\n                        price\\n                        prices\\n                        special_price\\n                        promotion_information\\n                        thumbnail\\n                        promotion_pack\\n                        sticker\\n                        flash_sale_types  \\n                    },\\n                }\\n            }\",\"variables\":{}}";

        // Tạo đối tượng HttpEntity với headers và body
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        // Gửi yêu cầu POST và nhận phản hồi
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        // Xử lý phản hồi
        return response.getBody();
    }
}
