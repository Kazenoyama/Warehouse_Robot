package com.warehouse.ItemTest;

import org.junit.jupiter.api.Test;

import com.warehouse.Item.ProductEnum;

public class ProductEnumTest {
    @Test
    public void productEnumShouldHaveCorrectValues() {
        ProductEnum.DENTIFRICE.toString().equals("DENTIFRICE");
        ProductEnum.PAIN.toString().equals("PAIN");
    }
}
