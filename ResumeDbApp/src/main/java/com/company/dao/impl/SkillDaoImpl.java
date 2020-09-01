/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.entity.Skill;
import com.mycompany.dao.inter.AbstractDAO;
import static com.mycompany.dao.inter.AbstractDAO.connect;
import com.mycompany.dao.inter.SkillDaoInter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Parvin
 */
public class SkillDaoImpl extends AbstractDAO implements SkillDaoInter {

     private Skill getSkill(ResultSet rs) throws Exception {

        String name = rs.getString("name");
        int id = rs.getInt("id");
     
        Skill skl = new Skill(id,name);
        return skl;
      
    }
    @Override
    public List<Skill> getAllSkill(int id) {
        List<Skill> result = new ArrayList<>();
        try (Connection c = connect()) {

            PreparedStatement stmt = c.prepareStatement("select * from skill where id = ?");

            stmt.setInt(1, id);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {

                Skill skl = getSkill(rs);
                result.add(skl);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

}
