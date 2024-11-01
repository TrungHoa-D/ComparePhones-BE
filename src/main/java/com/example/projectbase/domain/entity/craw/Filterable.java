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
public class Filterable {
    private int is_installment;
    private int stock_available_id;
    private int company_stock_id;
    private ArrayList<Filter> filter;
    private boolean is_parent;
    private double price;
    private Prices prices;
    private double special_price;
    private String promotion_information;
    private String thumbnail;
    private Object flash_sale_types;
}
