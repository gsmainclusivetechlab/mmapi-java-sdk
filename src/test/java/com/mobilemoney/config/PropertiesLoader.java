/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilemoney.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/***
 * Class PropertiesLoader
 */
public class PropertiesLoader {

    Properties properties;
    
    /***
     * Constructor for loading properties file
     */
    public PropertiesLoader() {
        try (InputStream input = new FileInputStream("config.properties")) {
        	this.properties = new Properties();
        	this.properties.load(input);
        } catch (IOException io) {
//            io.printStackTrace();
        }
    }
    
    /***
     *
     * @param key
     * @return
     */
    public String get(String key){
        return this.properties.getProperty(key);
    }
}
