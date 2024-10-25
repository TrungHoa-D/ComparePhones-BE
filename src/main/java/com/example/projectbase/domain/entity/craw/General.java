package com.example.projectbase.domain.entity.craw;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class General {
    private int product_id;
    private String name;
    private Attributes attributes;
    private String sku;
    private String doc_quyen;
    private String manufacturer;
    private String url_key;
    private String url_path;
    private ArrayList<Category> categories;
    private Review review;
}
