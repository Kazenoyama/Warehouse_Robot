package com.warehouse;

import java.util.List;

import com.warehouse.Map.Pos;

public class TestUtils {
    public static boolean areTwoPositionListsEquals(List<Pos> list1, List<Pos> list2) {
        return list1.containsAll(list2) && list2.containsAll(list1);
    }

}
