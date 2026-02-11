package com.ndlcommerce.entity.factory.interfaces;

import com.ndlcommerce.entity.model.interfaces.Category;

public interface CategoryFactory {
    Category create(String name);
}
