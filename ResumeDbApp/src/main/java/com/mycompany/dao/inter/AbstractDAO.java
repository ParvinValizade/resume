/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao.inter;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Parvin
 */
public class AbstractDAO {
       public static Connection connect() throws Exception {
       
        String url = "jdbc:mysql://localhost:3306/resume";
        String username = "root";
        String password = "1234";
        Connection c = DriverManager.getConnection(url, username, password);
        return c;
    }
}
