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
import java.sql.Statement;
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
        
        Skill skl = new Skill(id, name);
        return skl;
        
    }

    @Override
    public List<Skill> getAllSkill() {
        List<Skill> result = new ArrayList<>();
        try (Connection c = connect()) {
            
            Statement stmt = c.createStatement();
            
            stmt.execute("select * from skill");
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
    
    @Override
    public boolean insertSkill(Skill skl) {
        try (Connection c = connect()) {
            
            PreparedStatement stmt = c.prepareStatement("insert into skill(name) values(?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, skl.getName());
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            
            if (generatedKeys.next()) {
                skl.setId(generatedKeys.getInt(1));
            }
            
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
}
