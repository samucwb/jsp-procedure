/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sschatz.jspdemo.web;



import com.sschatz.jspdemo.util.LiquibaseUpdate;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DatabaseServletListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LiquibaseUpdate.updateDatabase();
    	System.out.println("DatabaseServletListener Liquibase ok");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    	System.out.println("DatabaseServletListener context Destroyed");
    	
    }
	
}