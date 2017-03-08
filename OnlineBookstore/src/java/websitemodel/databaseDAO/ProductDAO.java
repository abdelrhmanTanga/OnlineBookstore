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
import websitemodel.databaseDTO.Product;

/**
 *
 * @author abdelrhman galal
 */
public class ProductDAO {
    Connection connection;
    PreparedStatement pst;
    ResultSet rs;
    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean addBook(Product newBook) {
        try {
            pst = connection.prepareStatement("insert into product values (productid.nextval,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, newBook.getName());
            pst.setInt(2, newBook.getQuantity());
            pst.setString(3, newBook.getAuthor());
            pst.setLong(4, newBook.getISBN());
            pst.setString(5, newBook.getDescription());
            pst.setString(6, "gfgbgfdb");
            pst.setInt(7, newBook.getPrice());
            pst.setInt(8, newBook.getCategory());
            pst.setString(9, newBook.getImage());
            pst.executeUpdate();
            pst.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
