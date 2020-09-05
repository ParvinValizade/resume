/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.entity.Skill;
import com.company.entity.User;
import com.company.entity.UserSkill;
import com.mycompany.dao.inter.AbstractDAO;
import static com.mycompany.dao.inter.AbstractDAO.connect;
import com.mycompany.dao.inter.UserSkillDaoInter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Parvin
 */
public class UserSkillDaoImpl extends AbstractDAO implements UserSkillDaoInter {


    private UserSkill getUserSkill(ResultSet rs) throws Exception {
        int userSkillId = rs.getInt("userSkillId");
        int userId = rs.getInt("id");
        int skillId = rs.getInt("skill_id");
        
        String skillName = rs.getString("skill_name");
        int power = rs.getInt("power");

        
       return new UserSkill(userSkillId, new User(userId),new Skill(skillId, skillName),power); 
    }

    @Override
    public List<UserSkill> getAllSkillByUserId(int userId) {
        List<UserSkill> result = new ArrayList<>();
        try (Connection c = connect()) {

            PreparedStatement stmt = c.prepareStatement("select "
            +" us.id as userSkillId, "        
            +" u.*, "
            +" us.skill_id, "
            +" s.NAME AS skill_name, "
            +" us.power "
            +" FROM "
            +" user_skill us "
            +" LEFT JOIN USER u ON us.user_id = u.id "
            +" LEFT JOIN SKILL s ON us.skill_id = s.id "
            +" WHERE "
            +" us.user_id = ?");

            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {

                UserSkill u = getUserSkill(rs);
                result.add(u);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
    @Override
    public boolean removeUserSkill(int id) {
     try (Connection c = connect()) {

            Statement stmt = c.createStatement();
            return stmt.execute("delete from user_skill where id=" +id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean insertUserSkill(UserSkill u) {
   
       try (Connection c = connect()) {

            PreparedStatement stmt = c.prepareStatement("insert into user_skill ( skill_id , user_id , power) values(? ,? ,?)");
          stmt.setInt(1, u.getUser().getId());
          stmt.setInt(2, u.getSkill().getId());
          stmt.setInt(3, u.getPower());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    
    }

    

}
