package com.warehouse;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemEnumTest {

    @Test
    public void itemEnumShouldHaveCorrectValues() {
        ItemEnum.FOOD.toString().equals("FOOD");
        ItemEnum.DRINK.toString().equals("DRINK");
        ItemEnum.ELECTRONICS.toString().equals("ELECTRONICS");
        ItemEnum.CLOTHES.toString().equals("CLOTHES");
        ItemEnum.TOYS.toString().equals("TOYS");
        ItemEnum.TOOLS.toString().equals("TOOLS");
        ItemEnum.FURNITURE.toString().equals("FURNITURE");
        ItemEnum.COMPUTER.toString().equals("COMPUTER");
        ItemEnum.OTHER.toString().equals("OTHER");
    }
    
}
