
package com.mycompany.main;


import com.company.dao.impl.SkillDaoImpl;
import com.company.entity.Skill;
import com.mycompany.dao.inter.SkillDaoInter;



public class Main {

 
    public static void main(String[] args) throws Exception {

//      SkillDaoInter sl =Context.instanceSkillDao();
//
//        System.out.println(sl.getAllSkill());
       
           SkillDaoInter skillDao = new SkillDaoImpl();
           Skill s = new Skill();
           s.setName("JAvaScript");
           skillDao.insertSkill(s);
   }
    


}
