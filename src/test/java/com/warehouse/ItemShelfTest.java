package com.warehouse;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemShelfTest {
    @Test
    public void testAddItem() {
        ItemStorageInterface shelf = new ItemShelf();
        assertTrue(shelf.isEmpty());
        assertEquals(null, shelf.getContainedItemList());
    }

    @Test
    public void WhenAProductIsAdded_ShelfIsNotEmptyAnymoreAndContainsTheNumberOfProduct() {
        ItemStorageInterface shelf = new ItemShelf();
        shelf.addItem(new Item(ItemEnum.DENTIFRICE, 0, 1));
        
        assertFalse(shelf.isEmpty());
        assertTrue(shelf.contains(ItemEnum.DENTIFRICE));
        assertEquals(1, shelf.getNumberOfItemInStorage(ItemEnum.DENTIFRICE));
    }

    @Test
    public void shelfNumberOfProductShoudBeIncrementedOrDecrementedByTheNumberOfAddedProduct(){
        ItemStorageInterface shelf = new ItemShelf();
        shelf.addItem(new Item(ItemEnum.DENTIFRICE, 0, 2));
        assertEquals(2, shelf.getNumberOfItemInStorage(ItemEnum.DENTIFRICE));

        shelf.removeItem(ItemEnum.DENTIFRICE, 2);
        assertEquals(0, shelf.getNumberOfItemInStorage(ItemEnum.DENTIFRICE));
    }

    @Test
    public void WhenRemovingAllProduct_ShelfShoudBeEmptyAndCounterAt0(){
        ItemStorageInterface shelf = new ItemShelf();
        shelf.addItem(new Item(ItemEnum.DENTIFRICE, 0, 2));
        shelf.removeItem(ItemEnum.DENTIFRICE, 2);

        assertEquals(0, shelf.getNumberOfItemInStorage(ItemEnum.DENTIFRICE));
        assertTrue(shelf.isEmpty());
        assertNull(shelf.getContainedItemList());

        assertThrows(IllegalStateException.class, () -> shelf.removeItem(ItemEnum.DENTIFRICE, 1));
    }

    @Test
    public void WhenRemovingProductFromEmptyShelf_ShouldThrow() {
        ItemStorageInterface shelf = new ItemShelf();
        assertThrows(IllegalStateException.class, () -> shelf.removeItem(ItemEnum.DENTIFRICE, 1));
    }

    @Test
    public void testThrowIfProductTypeIsNotValid() {
        // Test with initial null product
        ItemStorageInterface shelf = new ItemShelf();
        shelf.addItem(new Item(ItemEnum.DENTIFRICE, 0, 1));
        assertTrue(shelf.contains(ItemEnum.DENTIFRICE));
        
        // Test with same product type - should not throw
        assertDoesNotThrow(() -> shelf.addItem(new Item(ItemEnum.DENTIFRICE, 0, 1)));
        
        // Test with different product type - should throw
        assertThrows(IllegalArgumentException.class, () -> shelf.addItem(new Item(ItemEnum.CLOTHES, 0, 1)));
        
        // Test with null product after having a product
        assertThrows(NullPointerException.class, () -> shelf.addItem(null));
        
        // Test empty shelf accepting any product
        Shelf emptyShelf = new Shelf();
        assertDoesNotThrow(() -> emptyShelf.addProduct(ProductEnum.PAIN));
    }
    @Test
    public void testRemoveProductWithPartialRemoval() {
        ItemStorageInterface shelf = new ItemShelf();
        // Setup shelf with multiple products
        shelf.addItem(new Item(ItemEnum.DENTIFRICE, 0, 5));
        
        // Remove some but not all products
        shelf.removeItem(ItemEnum.DENTIFRICE, 3);
        
        // Verify partial removal
        assertEquals(2, shelf.getNumberOfItemInStorage(ItemEnum.DENTIFRICE));
        assertTrue(shelf.contains(ItemEnum.DENTIFRICE));
        assertFalse(shelf.isEmpty());
        
        // Remove remaining products
        shelf.removeItem(ItemEnum.DENTIFRICE, 2);
        
        // Verify complete removal
        assertEquals(0, shelf.getNumberOfItemInStorage(ItemEnum.DENTIFRICE));
        assertNull(shelf.getContainedItemList());
        assertTrue(shelf.isEmpty());
    }
}
