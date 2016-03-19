/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wes.qadatecalculator.utils;

import com.wes.qadatecalculator.pojo.QADate;

/**
 *
 * @author wesjordan
 */
public class QADateUtil {
    
    private static final int[] MONTHLY_DAY_COUNT_LIST = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
    private static final int[] LEAP_YEAR_MONTHLY_DAY_COUNT_LIST = new int[]{31,29,31,30,31,30,31,31,30,31,30,31};
    
    private static final int MAX_MONTH = 12;
    
    public static int interval(QADate date1, QADate date2){
        // 3 parts to the interval, if date 1 and date 2 are in different years
        // if they're in the same year we simply calculate the difference in number of days since day 1 of the year
        // 1) days remaining in year for date 1
        // 2) number of complete years between the 2 dates
        // 3) days between start of year and day for date 2
        
       if(date1.compareTo(date2) > 0){
           QADate tmp = date1;
           date1 = date2;
           date2 = tmp;
        }
        
        int totalDays = 0;
        
        if(date1.getYear() != date2.getYear()){     //dates are in different years
            int daysRemainingInYear = daysRemainingInYear(date1);
            totalDays += daysRemainingInYear;
        
            int totalDaysInInterveningYears = 0;
            for(int i = date1.getYear() + 1; i < date2.getYear(); i++){
                if(isLeapYear(i)){ //is leap year
                    totalDaysInInterveningYears += 366;
                }else{
                    totalDaysInInterveningYears += 365;
                }
            }
            totalDays += totalDaysInInterveningYears;

            int daysSinceYearStart = daysSinceYearStart(date2);
            totalDays += daysSinceYearStart;
            
            
        }else{  //dates are in same year
            int daysD1 = daysSinceYearStart(date1);
            int daysD2 = daysSinceYearStart(date2);
            
            totalDays = daysD2 - daysD1;
        }
        
        
        return totalDays - 1;
    }
    
    public static int daysRemainingInYear(QADate date){
        int totalDaysRemaining = 0;
        
        if(isLeapYear(date.getYear())){    //is leap year
            totalDaysRemaining += LEAP_YEAR_MONTHLY_DAY_COUNT_LIST[date.getMonth() - 1] - date.getDay();
            
            for(int i=(date.getMonth()); i<MAX_MONTH; i++){
                totalDaysRemaining += LEAP_YEAR_MONTHLY_DAY_COUNT_LIST[i];
            }
        }else{
            totalDaysRemaining += MONTHLY_DAY_COUNT_LIST[date.getMonth() - 1] - date.getDay();
            
            for(int i=(date.getMonth()); i<MAX_MONTH; i++){
                totalDaysRemaining += MONTHLY_DAY_COUNT_LIST[i];
            } 
        }
        
        return totalDaysRemaining;
    }
    
    public static int daysSinceYearStart(QADate date){         
        int totalDaysPassed = 0;
        
        if(isLeapYear(date.getYear())){ //is leap year
            totalDaysPassed += date.getDay();
            
            for(int i=0; i<(date.getMonth()-1);i++){
                totalDaysPassed += LEAP_YEAR_MONTHLY_DAY_COUNT_LIST[i];
            }
        }else{
            totalDaysPassed += date.getDay();
            
            for(int i=0; i<(date.getMonth()-1);i++){
                totalDaysPassed += MONTHLY_DAY_COUNT_LIST[i];
            }
        }
        
        return totalDaysPassed;
    }
    
    
    public static boolean isLeapYear(int year){
        if((year % 100 == 0)){ //centenurial year
            return ((year % 4) == 0) &&
                    ((year % 400) == 0);
        }else{  //non-centenurial year
            return year % 4 == 0;
        }
    }
}
