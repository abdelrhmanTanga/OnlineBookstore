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
import websitemodel.databaseDTO.OrderHistory;

/**
 *
 * @author abdelrhman galal
 */
public class OrderHistoryDAO {
    
    
    Connection connection;
    PreparedStatement pst;
    ResultSet rs;

    public OrderHistoryDAO(Connection connection) {
        this.connection = connection;
    }

    //mohamed ali start
    
    public List<OrderHistory> getAllOrders(String userMail)
    {
        List<OrderHistory> orderHistrories = new ArrayList<>();
        try {
            pst = connection.prepareStatement("SELECT * FROM order_history WHERE email=?");
            pst.setString(1, userMail);
            rs = pst.executeQuery();
            while (rs.next()) {
                OrderHistory orderHistory = new OrderHistory(rs.getInt(1),rs.getString(2));
                orderHistrories.add(orderHistory);
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(OrderHistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderHistrories;
    }
    
    //mohamed ali end
    
}
