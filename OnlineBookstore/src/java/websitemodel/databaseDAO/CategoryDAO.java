/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websitemodel.databaseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import websitemodel.databaseDTO.Category;

/**
 *
 * @author abdelrhman galal
 */
public class CategoryDAO {
    Connection connection;
    public CategoryDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean getId(Category category) {
        try {
            PreparedStatement pst = connection.prepareStatement("select id from category where name = ?");
            System.out.println(category.getName());
            pst.setString(1, category.getName());
            ResultSet rs = pst.executeQuery();
            rs.next();
            category.setId(rs.getInt(1));
            rs.close();
            pst.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    
    
}
