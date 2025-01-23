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

        assertThrows(IllegalStateException.class, () -> shelf.removeProduct(1));

    }

    @Test
    public void WhenRemovingProductFromEmptyShelf_ShouldThrow() {
        assertThrows(IllegalStateException.class, () -> shelf.removeProduct(1));
    }

    @Test
    public void testThrowIfProductTypeIsNotValid() {
        // Test with initial null product
        shelf.addProduct(ProductEnum.DENTIFRICE);
        assertEquals(ProductEnum.DENTIFRICE, shelf.getProduct());
        
        // Test with same product type - should not throw
        assertDoesNotThrow(() -> shelf.addProduct(ProductEnum.DENTIFRICE));
        
        // Test with different product type - should throw
        assertThrows(IllegalArgumentException.class, () -> shelf.addProduct(ProductEnum.PAIN));
        
        // Test with null product after having a product
        assertThrows(IllegalArgumentException.class, () -> shelf.addProduct(null));
        
        // Test empty shelf accepting any product
        Shelf emptyShelf = new Shelf();
        assertDoesNotThrow(() -> emptyShelf.addProduct(ProductEnum.PAIN));
    }
    @Test
    public void testRemoveProductWithPartialRemoval() {
        // Setup shelf with multiple products
        shelf.addMultipleProduct(ProductEnum.DENTIFRICE, 5);
        
        // Remove some but not all products
        shelf.removeProduct(3);
        
        // Verify partial removal
        assertEquals(2, shelf.getNumberOfProduct());
        assertEquals(ProductEnum.DENTIFRICE, shelf.getProduct());
        assertFalse(shelf.isEmpty());
        
        // Remove remaining products
        shelf.removeProduct(2);
        
        // Verify complete removal
        assertEquals(0, shelf.getNumberOfProduct());
        assertNull(shelf.getProduct());
        assertTrue(shelf.isEmpty());
    }
}
