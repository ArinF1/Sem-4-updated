package se.kth.iv1350.possystem.integration;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.possystem.model.Item;
import se.kth.iv1350.possystem.model.ItemDTO;

public class ExternalInventorySystemTest {
    
    private ExternalInventorySystem instance;
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        instance = new ExternalInventorySystem();
        instance.addItem();
    }
    
    @AfterEach
    public void tearDown() {
        instance = null;
    }

    @Test
    public void testGetStoreItems() {
        System.out.println("getStoreItems");
        List<Item> result = instance.getStoreItems();
        assertFalse(result.isEmpty(), "The list should not be empty after adding items.");
    }

    @Test
    public void testAddItem() {
        System.out.println("addItem");
        int initialSize = instance.getStoreItems().size();
        instance.addItem();
        int newSize = instance.getStoreItems().size();
        assertTrue(newSize > initialSize, "The inventory should have more items after adding.");
    }

    @Test
    public void testSearch() throws Exception {
        System.out.println("search");
        int barCode = 123;
        Item result = instance.search(barCode);
        assertNotNull(result, "Search should return a non-null item for existing barcode.");
        assertEquals(barCode, result.getBarCode(), "The barcode of the item should match the searched barcode.");
    }
    
    @Test
    public void testSearchForNonExistingItem() {
        System.out.println("searchForNonExistingItem");
        int barCode = 999;
        try{ 
         instance.search(barCode);
        }catch(BarCodeNotFoundException e){
            
        } catch (DataBaseFailureException ex) {
            Logger.getLogger(ExternalInventorySystemTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testSearchWithDatabaseFailure() {
        System.out.println("searchWithDatabaseFailure");
        int barCode = 500;
        try{
            instance.search(barCode);
        }catch(DataBaseFailureException e){
            
        } catch (BarCodeNotFoundException ex) {
            Logger.getLogger(ExternalInventorySystemTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
