package com.warehouse;

import org.junit.jupiter.api.Test;

public class ProductEnumTest {
    @Test
    public void productEnumShouldHaveCorrectValues() {
        ProductEnum.DENTIFRICE.toString().equals("DENTIFRICE");
        ProductEnum.PAIN.toString().equals("PAIN");
    }
}
