package com.ndlcommerce.entity.factory.implementation;

import com.ndlcommerce.entity.factory.interfaces.CategoryFactory;
import com.ndlcommerce.entity.model.implementation.CommonCategory;
import com.ndlcommerce.entity.model.interfaces.Category;

public class CommonCategoryFactoryImp implements CategoryFactory {

    @Override
    public Category create(String name) {
        return new CommonCategory(name);
    }

}
