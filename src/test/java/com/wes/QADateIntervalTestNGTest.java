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
public class QADateIntervalTestNGTest {
    
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    
    public QADateIntervalTestNGTest() {
    }

    @Test
    public void sampleTest1(){
        Application.main(new String[]{"1983-06-02","1983-06-22"});
        assertEquals("Days between supplied dates: 19", outContent.toString().trim());
    }
    
    @Test
    public void sampleTest2(){
        Application.main(new String[]{"1984-07-04","1984-12-25"});
        assertEquals("Days between supplied dates: 173", outContent.toString().trim());
    }
    
    @Test
    public void sampleTest3(){
        Application.main(new String[]{"1989-01-03","1983-08-03"});
        assertEquals("Days between supplied dates: 1979", outContent.toString().trim());
    }
    
    @Test
    public void sampleTest4(){
        Application.main(new String[]{"1972-11-07","1972-11-08"});
        assertEquals("Days between supplied dates: 0", outContent.toString().trim());
    }
    
    @Test
    public void sampleTest5(){
        Application.main(new String[]{"2000-01-01","2000-01-03"});
        assertEquals("Days between supplied dates: 1", outContent.toString().trim());
    }
    
    @Test
    public void sampleTest6(){
        Application.main(new String[]{"2016-03-01","2016-03-14"});
        assertEquals("Days between supplied dates: 12", outContent.toString().trim());
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
