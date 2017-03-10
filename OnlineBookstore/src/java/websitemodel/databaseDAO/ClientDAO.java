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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import websitemodel.databaseDTO.Client;
import websitemodel.databaseDTO.Product;

/**
 *
 * @author abdelrhman galal
 */
public class ClientDAO {

    Connection connection;
    PreparedStatement pst;
    ResultSet rs;

    public ClientDAO(Connection connection) {
        this.connection = connection;
    }
    
    
    public int getUsersCount() {
        int number = 0;
        try {
            pst = connection.prepareStatement("SELECT count(email) FROM client");
            rs = pst.executeQuery();
            rs.next();
            String count = rs.getString(1);
            number = Integer.parseInt(count);
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return number;
    }
    
    public List<Client> getAllClients(int pageNumber)
    {
        List<Client> clients = new ArrayList<>();
        
        try {
            pst = connection.prepareStatement("SELECT * FROM (select c.*, rownum r from client c) where r > ? and r <= ?");
            pst.setInt(1, (pageNumber * 10) - 10);
            pst.setInt(2, ( pageNumber * 10 ) );
            rs = pst.executeQuery();
            while (rs.next()) {
                Client client;
                client = new Client(rs.getString(1),rs.getString(2),rs.getLong(3),rs.getString(4),
                rs.getLong(5),rs.getString(6),rs.getString(7),rs.getString(8),
                rs.getString(9),rs.getString(10));
                clients.add(client);
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clients;
    }
    
}
