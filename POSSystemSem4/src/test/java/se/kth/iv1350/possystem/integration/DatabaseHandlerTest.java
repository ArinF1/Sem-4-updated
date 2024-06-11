package se.kth.iv1350.possystem.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseHandlerTest {
    
    private DatabaseHandler instance;
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        instance = new DatabaseHandler();
    }
    
    @AfterEach
    public void tearDown() {
        instance = null;
    }

    @Test
    public void testDatabaseOperationSuccess() throws Exception {
        System.out.println("databaseOperation - success case");
        assertDoesNotThrow(() -> instance.databaseOperation());
    }
    
    @Test
    public void testDatabaseOperationFailure() {
        System.out.println("databaseOperation - failure case");
        instance = new DatabaseHandler() {
        protected boolean checkDatabaseStatus() {
                return true;
            }
        };
    }
}
