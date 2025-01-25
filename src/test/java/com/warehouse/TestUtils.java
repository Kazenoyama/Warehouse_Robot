package com.warehouse;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {
    public static boolean areTwoPositionListsEquals(List<Pos> list1, List<Pos> list2) {
        if (list1 == null || list2 == null) {
            throw new NullPointerException("One or both lists are null");
        }
        if (list1.size() != list2.size()) {
            return false;
        }

        ArrayList<Pos> list1Copy = new ArrayList<Pos>(list1);	
        for (Pos positionInList1 : list1) {
            for (Pos positionInList2 : list2) {
                if (positionInList1.equals(positionInList2)) {
                    list1Copy.remove(positionInList1);
                    break;
                }
            }
            
        }
        return list1Copy.isEmpty();
    }

}
