/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.entity.Country;
import com.mycompany.dao.inter.AbstractDAO;
import static com.mycompany.dao.inter.AbstractDAO.connect;
import com.mycompany.dao.inter.CountryDaoInter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Parvin
 */
public class CountryDaoImpl extends AbstractDAO implements CountryDaoInter {

     private Country getCountry(ResultSet rs) throws Exception {

        String name = rs.getString("name");
        String nationality = rs.getString("nationality");
        int id = rs.getInt("id");
            
        Country cty = new Country(id,name,nationality);
        return cty;
      
    }
    @Override
    public List<Country> getAllCountry(int id) {
        List<Country> result = new ArrayList<>();
        try (Connection c = connect()) {

            PreparedStatement stmt = c.prepareStatement("select * from country where id = ?");

            stmt.setInt(1, id);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {

                Country cty = getCountry(rs);
                result.add(cty);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

}
