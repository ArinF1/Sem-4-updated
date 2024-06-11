package se.kth.iv1350.possystem.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LogWriterTest {
    
    private static final String TEST_LOG_FILE = "test_log.txt";
    private LogWriter logWriter;
    
    public LogWriterTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        
    }
    
    @AfterAll
    public static void tearDownClass() {
        
    }
    
    @BeforeEach
    public void setUp() {
        logWriter = new LogWriter(TEST_LOG_FILE);
    }
    
    @AfterEach
    public void tearDown() {
        
        new File(TEST_LOG_FILE).delete();
    }

    @Test
    public void testLog() {
        System.out.println("log");
        String message = "Test log message";
        logWriter.log(message);
        
        try (Scanner scanner = new Scanner(new File(TEST_LOG_FILE))) {
            assertTrue(scanner.hasNextLine(), "Log file is empty.");
            assertEquals(message, scanner.nextLine(), "Logged message does not match.");
        } catch (FileNotFoundException e) {
            fail("Log file not found.");
        }
    }
}
