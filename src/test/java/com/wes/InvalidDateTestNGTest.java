/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wes;


import com.wes.qadatecalculator.Application;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/**
 *
 * @author wesjordan
 */
public class InvalidDateTestNGTest {
    
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    
    public InvalidDateTestNGTest() {
    }
    
     
    @Test
    public void invalidYear(){
        Application.main(new String[]{"1800-11-07","1972-11-08"});
        assertEquals("Invalid year supplied", outContent.toString().trim());
    }
    
    @Test
    public void invalidMonth(){
        Application.main(new String[]{"1905-14-07","1972-11-08"});
        assertEquals("Invalid month supplied", outContent.toString().trim());
    }
    
    @Test
    public void invalidDay(){
        Application.main(new String[]{"1905-11-45","1972-11-08"});
        assertEquals("Invalid day supplied", outContent.toString().trim());
    }
    
    @Test
    public void invalidLeapDay(){
        Application.main(new String[]{"1905-02-29","1972-11-08"});
        assertEquals("Invalid month-day combination supplied", outContent.toString().trim());
    }
    
    @Test
    public void invalidDayInLeapYear(){
        Application.main(new String[]{"1904-02-30","1972-11-08"});
        assertEquals("Invalid month-day combination supplied", outContent.toString().trim());
    }
    
    @Test
    public void singleDate(){
        Application.main(new String[]{"1904-02-30"});
        assertEquals("Please provide 2 dates in the format 'yyyy-mm-dd yyyy-mm-dd'", outContent.toString().trim());
    }
    
    @Test
    public void alphanumericValue(){
        Application.main(new String[]{"1904-02-20","1904-hello-30"});
        assertEquals("Invalid date format", outContent.toString().trim());
    }
    
    @Test
    public void onlyDateTokenizers(){
        Application.main(new String[]{"---","1904-01-30"});
        assertEquals("Invalid date format", outContent.toString().trim());
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        outContent.reset();
        
        System.setOut(new PrintStream(outContent));
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        System.setOut(null);
    }
}
