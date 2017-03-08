/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import adminmodel.AdminViewProduct;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import websitemodel.ConnectionPool;
import websitemodel.databaseDAO.ProductDAO;
import websitemodel.databaseDTO.Product;

/**
 *
 * @author abdelrhman galal
 */
public class AdminFacadeHandler {

    public AdminFacadeHandler() {

    }

    public boolean addBook(AdminViewProduct product) {
        try {
            Product newBook = new Product();

////////////////////////////////// adapter to the database DTO
            Connection connection = ConnectionPool.getInstance().getConnection();
            return new ProductDAO(ConnectionPool.getInstance().getConnection()).addBook(newBook);

        } catch (SQLException ex) {
            Logger.getLogger(AdminFacadeHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
