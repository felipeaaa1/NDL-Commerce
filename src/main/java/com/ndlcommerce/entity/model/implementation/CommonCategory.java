package com.ndlcommerce.entity.model.implementation;

import com.ndlcommerce.entity.model.interfaces.Category;

public class CommonCategory implements Category {

    private String name;

    public CommonCategory(String name) {
        this.name = name;
    }


    @Override
    public String getName() {
        return "";
    }

    @Override
    public boolean nameIsValid() {
        return this.name != null
                && !this.name.isBlank()
                && this.name.length() >= 3
                && this.name.length() < 200;
    }
}
