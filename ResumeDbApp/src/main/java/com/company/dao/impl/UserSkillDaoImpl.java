/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.entity.Country;
import com.company.entity.Skill;
import com.company.entity.User;
import com.company.entity.UserSkill;
import com.mycompany.dao.inter.AbstractDAO;
import com.mycompany.dao.inter.UserSkillDaoInter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Parvin
 */
public class UserSkillDaoImpl extends AbstractDAO implements UserSkillDaoInter {


    private UserSkill getUserSkill(ResultSet rs) throws Exception {
        Integer userId = rs.getInt("id");
        int skillId = rs.getInt("skill_id");
        
        String skillName = rs.getString("skill_name");
        int power = rs.getInt("power");

        
       return new UserSkill(null, new User(userId),new Skill(skillId, skillName),power); 
    }

    @Override
    public List<UserSkill> getAllSkillByUserId(int userId) {
        List<UserSkill> result = new ArrayList<>();
        try (Connection c = connect()) {

            PreparedStatement stmt = c.prepareStatement("select "
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

}
