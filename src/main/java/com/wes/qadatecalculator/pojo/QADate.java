/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wes.qadatecalculator.pojo;

import com.wes.qadatecalculator.exception.InvalidQADateException;
import com.wes.qadatecalculator.utils.QADateUtil;

/**
 *
 * @author wesjordan
 */
public class QADate implements Comparable<QADate>{
    private int day;
    private int month;
    private int year;
    
    private final int MIN_YEAR = 1901;
    private final int MAX_YEAR = 2999;
    
    private final int MIN_MONTH = 1;
    private final int MAX_MONTH = 12;
    
    private final int MIN_DAY = 1;
    private final int MAX_DAY = 31;
    
    
    private final int[] MONTHLY_DAY_COUNT_LIST = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
    private final int[] LEAP_YEAR_MONTHLY_DAY_COUNT_LIST = new int[]{31,29,31,30,31,30,31,31,30,31,30,31};
    
    public QADate(){
        day = MIN_DAY;
        month = MIN_MONTH;
        year = MIN_YEAR;
    }
    
    public void setDate(String date) throws InvalidQADateException{
        String[] tokens = date.split("-");
        if(tokens.length != 3){
            throw new InvalidQADateException("Invalid date format");
        }
        
        try{
            year = Integer.parseInt(tokens[0].trim());
            month = Integer.parseInt(tokens[1].trim());
            day = Integer.parseInt(tokens[2].trim());
        }catch(NumberFormatException nfe){
            throw new InvalidQADateException("Invalid date format");
        }
        
        validate();
    }
    
    public void validate() throws InvalidQADateException{
        if(year < MIN_YEAR || year > MAX_YEAR){
            throw new InvalidQADateException("Invalid year supplied");
        }
        
        if(month < MIN_MONTH || month > MAX_MONTH){
            throw new InvalidQADateException("Invalid month supplied");
        }
        
        if(day < MIN_DAY || day > MAX_DAY){
            throw new InvalidQADateException("Invalid day supplied");
        }
        
        if(QADateUtil.isLeapYear(year)){
            if(day > LEAP_YEAR_MONTHLY_DAY_COUNT_LIST[month - 1]){
               throw new InvalidQADateException("Invalid month-day combination supplied"); 
            }
        }else{
            if(day > MONTHLY_DAY_COUNT_LIST[month - 1]){
               throw new InvalidQADateException("Invalid month-day combination supplied"); 
            }
        }
        
    }

    //<editor-fold defaultstate="collapsed" desc="getters and setters. note: setters call validate() method to ensure date integrity">    
    public int getDay() {
        return day;
    }

    public void setDay(int day) throws InvalidQADateException{
        this.day = day;
        validate();
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) throws InvalidQADateException{
        this.month = month;
        validate();
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) throws InvalidQADateException{
        this.year = year;
        validate();
    }
    //</editor-fold>    
    
    
    @Override
    public String toString(){
        return "QADate: " + year + "-" + month + "-" + day;
    }

    @Override
    public int compareTo(QADate date2) {
        if(this.getYear() > date2.getYear()){
            return 1;
        }else{
            if(this.getMonth() > date2.getMonth()){
                return 1;
            }else{
                if(this.getDay() > date2.getDay()){
                    return 1;
                }else if(this.getDay() == date2.getDay()){
                    return 0;
                }else{
                    return -1;
                }
            }
        }
    }
}
