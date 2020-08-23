/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.main;

import com.company.dao.impl.UserDaoImpl;
import com.mycompany.dao.inter.UserDaoInter;

/**
 *
 * @author Parvin
 */
public class Context {
     public static UserDaoInter instanceUserDao() {
         return new UserDaoImpl();
     }
}
