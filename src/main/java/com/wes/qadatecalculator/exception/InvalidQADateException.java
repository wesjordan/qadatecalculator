/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wes.qadatecalculator.exception;

/**
 *
 * @author wesjordan
 */
public class InvalidQADateException extends Exception{
    public InvalidQADateException(String message){
        super(message);
    }
}
