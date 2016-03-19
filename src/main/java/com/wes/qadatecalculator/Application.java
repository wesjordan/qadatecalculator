/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wes.qadatecalculator;

import com.wes.qadatecalculator.exception.InvalidQADateException;
import com.wes.qadatecalculator.pojo.QADate;
import com.wes.qadatecalculator.utils.QADateUtil;

/**
 *
 * @author wesjordan
 */
public class Application {
    
    
    public static void main(String[] args){
        
        if(args.length != 2){
            System.out.println("Please provide 2 dates in the format 'yyyy-mm-dd yyyy-mm-dd'");
        }else{
            QADate date1 = new QADate();
            QADate date2 = new QADate();

            try {

                date1.setDate(args[0]);
                date2.setDate(args[1]);

                System.out.println("Days between supplied dates: " + QADateUtil.interval(date1, date2));

            } catch (InvalidQADateException iqade) {
                System.out.println(iqade.getMessage());
            }
        }        
    }
    
}
