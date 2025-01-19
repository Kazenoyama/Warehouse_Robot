package com.warehouse;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RowTest {
    @Test
    public void testRow() {
        Pos start = new Pos(0, 0);
        Pos end = new Pos(0, 10);
        Row row = new Row(start, end);
    }

    @Test
    public void testRowMustBeOnAxisOnly() {
        // Test valid X-axis row
        Pos start1 = new Pos(0, 0);
        Pos end1 = new Pos(10, 0);
        Row rowX = new Row(start1, end1);

        // Test valid Y-axis row
        Pos start2 = new Pos(0, 0); 
        Pos end2 = new Pos(0, 10);
        Row rowY = new Row(start2, end2);

        // Test invalid diagonal row should throw exception
        Pos start3 = new Pos(0, 0);
        Pos end3 = new Pos(5, 5);
        assertThrows(IllegalArgumentException.class, () -> {
            new Row(start3, end3);
        });
    }
}
