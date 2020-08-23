
package com.mycompany.main;

import com.company.bean.User;

import com.mycompany.dao.inter.UserDaoInter;



public class Main {

 
    public static void main(String[] args) throws Exception {
         UserDaoInter userDao =Context.instanceUserDao();
//         List<User> list = userDao.getAll();
//         userDao.removeUser(1);
//         List<User> list2 = userDao.getAll();
//         
//         
//         System.out.println("list="+list);
//         System.out.println("list2="+list2);
    
 User u=userDao.getById(2);
 u.setName("Eldar");
 userDao.updateUser(u);
    }
}
