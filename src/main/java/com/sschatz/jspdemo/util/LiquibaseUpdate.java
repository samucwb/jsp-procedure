/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sschatz.jspdemo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.h2.tools.Server;

/**
 *
 * @author sschatz
 */
public class LiquibaseUpdate {
    
    public static boolean initialized;
    public static boolean error;
    
    public static void updateDatabase() {
        try {
            if (!initialized) {
               startTCPServer();
               // clearAllObjects();
               Connection connection = openConnection(); //your openConnection logic here
               Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
               Liquibase liquibase = new liquibase.Liquibase("config/liquibase/master.xml", new ClassLoaderResourceAccessor(), database);
               liquibase.update(new Contexts(), new LabelExpression());
               initialized = true; 
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LiquibaseUpdate.class.getName()).log(Level.SEVERE, null, ex);
            error = true;
        } catch (SQLException ex) {
            Logger.getLogger(LiquibaseUpdate.class.getName()).log(Level.SEVERE, null, ex);
            error = true;
        } catch (LiquibaseException ex) {
            Logger.getLogger(LiquibaseUpdate.class.getName()).log(Level.SEVERE, null, ex);
            error = true;
        }
    }
    
    private static Connection openConnection() throws ClassNotFoundException, SQLException {
        Class.forName ("org.h2.Driver"); 
        // Connection conn1 = DriverManager.getConnection ("jdbc:h2:~/test", "sa","");
        Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost:9093/~/testdb","sa","");
        //Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb ","sa","");
        
        return conn;
    }
    
   
    public static void startTCPServer() {
        try {
            
            Server h2Server = Server.createTcpServer("-tcpAllowOthers", "-ifNotExists", "-tcpPort", "9093").start();
            if (h2Server.isRunning(true)) {
                System.out.println(h2Server.getStatus());
                System.out.println("[INFO] H2 database started in TCP server mode on port " + h2Server.getPort());
            } else {
                throw new RuntimeException("Could not start H2 server.");
            }
        } catch (SQLException e) {
            //throw new RuntimeException("Failed to start H2 server: ", e);
            System.out.println("[INFO] H2 database,port may be in use " + e.getMessage());
        }
    }
    
    private static void clearAllObjects() throws SQLException, ClassNotFoundException {
        Connection c = openConnection();
        Statement s = c.createStatement();
        s.execute("DROP ALL OBJECTS");
    }
    
    private static void clearDatabase() throws SQLException, ClassNotFoundException {
        Connection c = openConnection();
        Statement s = c.createStatement();
        // Disable FK
        s.execute("SET REFERENTIAL_INTEGRITY FALSE");

        // Find all tables and truncate them
        Set<String> tables = new HashSet<String>();
        ResultSet rs = s.executeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES  where TABLE_SCHEMA='PUBLIC'");
        while (rs.next()) {
            tables.add(rs.getString(1));
        }
        rs.close();
        for (String table : tables) {
            s.executeUpdate("TRUNCATE TABLE " + table); 
        }

        // Idem for sequences
        Set<String> sequences = new HashSet<String>();
        rs = s.executeQuery("SELECT SEQUENCE_NAME FROM INFORMATION_SCHEMA.SEQUENCES WHERE SEQUENCE_SCHEMA='PUBLIC'");
        while (rs.next()) {
            sequences.add(rs.getString(1));
        }
        rs.close();
        for (String seq : sequences) {
            s.executeUpdate("ALTER SEQUENCE " + seq + " RESTART WITH 1");
        }

        // Enable FK
        s.execute("SET REFERENTIAL_INTEGRITY TRUE");
        s.close();
        c.close();
    }
    
}
