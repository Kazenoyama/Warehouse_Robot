package com.warehouse;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShelfTest {

    private Shelf shelf = null;
    
    @BeforeEach
    public void setUp() {
        shelf = new Shelf();
    }

    @Test
    public void WhenCreated_Shelf_ShouldBeEmpty() {
        assertTrue(shelf.isEmpty());
        assertEquals(0, shelf.getNumberOfProduct());
    }

    @Test
    public void WhenAProductIsAdded_ShelfIsNotEmptyAnymoreAndContainsTheNumberOfProduct() {
        shelf.addProduct(ProductEnum.DENTIFRICE);
        
        assertFalse(shelf.isEmpty());
        assertEquals(ProductEnum.DENTIFRICE, shelf.getProduct());
        assertEquals(1, shelf.getNumberOfProduct());
    }

    @Test
    public void shelfNumberOfProductShoudBeIncrementedOrDecrementedByTheNumberOfAddedProduct(){
        shelf.addMultipleProduct(ProductEnum.DENTIFRICE, 2);
        assertEquals(2, shelf.getNumberOfProduct());

        shelf.removeProduct(2);
        assertEquals(0, shelf.getNumberOfProduct());
    }

    @Test
    public void WhenRemovingAllProduct_ShelfShoudBeEmptyAndCounterAt0(){
        shelf.addMultipleProduct(ProductEnum.DENTIFRICE, 2);
        shelf.removeProduct(2);

        assertEquals(0, shelf.getNumberOfProduct());
        assertTrue(shelf.isEmpty());
        assertNull(shelf.getProduct());
    }

    @Test
    public void WhenAnotherTypeOfProductIsAdded_ShouldThrow(){
        shelf.addProduct(ProductEnum.DENTIFRICE);

        assertThrows(IllegalArgumentException.class, () -> shelf.addProduct(ProductEnum.PAIN));
        assertThrows(IllegalArgumentException.class, () -> shelf.addMultipleProduct(ProductEnum.PAIN, 2));
    }
}
