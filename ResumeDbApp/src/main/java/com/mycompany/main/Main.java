
package com.mycompany.main;


import com.mycompany.dao.inter.SkillDaoInter;



public class Main {

 
    public static void main(String[] args) throws Exception {

       SkillDaoInter sl =Context.instanceSkillDao();

        System.out.println(sl.getAllSkill(2));
    }
}
