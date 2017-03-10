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

    //mohamed ali start
    public int getProductsCount() {
        int number = 0;
        try {
            pst = connection.prepareStatement("SELECT count(id) FROM product");
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

    public List<Product> getAllProducts(int pageNumber) {
        List<Product> products = new ArrayList<>();
        try {
            pst = connection.prepareStatement("SELECT * FROM (select p.*, rownum r from product p) where r > ? and r <= ?");
            pst.setInt(1, (pageNumber * 10) - 10);
            pst.setInt(2, (pageNumber * 10) );
            rs = pst.executeQuery();
            while (rs.next()) {
                Product product;
                product = new Product(rs.getInt("id"), rs.getString("name"), rs.getInt("quantity"),
                        rs.getString("author"), rs.getLong("isbn"), rs.getString("description"), rs.getInt("category"), rs.getString("reviews"), rs.getInt("price"), rs.getString("image"));
                products.add(product);
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    public int getProductsCountBySearch(String keyword) {
        int number = 0;
        try {
            pst = connection.prepareStatement("SELECT count(id) FROM product WHERE name LIKE '%" + keyword + "%' ");
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

    public List<Product> searchForProductsByName(String keyword) {
        List<Product> products = new ArrayList<>();
        try {
            pst = connection.prepareStatement("SELECT * FROM product WHERE name LIKE '%" + keyword + "%' ");
            rs = pst.executeQuery();
            while (rs.next()) {
                Product product;
                product = new Product(rs.getInt("id"), rs.getString("name"), rs.getInt("quantity"),
                        rs.getString("author"), rs.getLong("isbn"), rs.getString("description"), rs.getInt("category"), rs.getString("reviews"), rs.getInt("price"), rs.getString("image"));
                products.add(product);
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }


    // public String addProduct(Product product)
    public boolean checkISBN(int isbn) {
        boolean check = false;
        try {
            pst = connection.prepareStatement("SELECT * FROM product WHERE isbn=? ");
            pst.setInt(1, isbn);
            rs = pst.executeQuery();
            if (rs.next()) {
                check = true;
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }

    public boolean deleteProduct(int id) {
        boolean check = false;
        try {
            pst = connection.prepareStatement("DELETE FROM product WHERE ID=? ");
            pst.setInt(1, id);
            int count = pst.executeUpdate();
            if (count > 0) {
                check = true;
            }
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }

    boolean updateProduct(Product product) {
        boolean check = false;

        try {
            pst = connection.prepareStatement("UPDATE product SET name=?,quantity=?,author=?,isbn=?,description=?,price=?,category=?,image=? WHERE id=?");
            pst.setString(1, product.getName());
            pst.setInt(2, product.getQuantity());
            pst.setString(3, product.getAuthor());
            pst.setLong(4, product.getISBN());
            pst.setString(5, product.getDescription());
            pst.setInt(6, product.getPrice());
            pst.setInt(7, product.getCategory());
            pst.setString(8, product.getImage());
            pst.setInt(9, product.getId());

            int count = pst.executeUpdate();
            if (count > 0) {
                check = true;
            }
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }
    
    public int getProductPrice(int productId)
    {
        int price = 0;
        try{
             pst = connection.prepareStatement("SELECT price FROM product WHERE id=?");
             pst.setInt(1, productId);
             rs = pst.executeQuery();
             if( rs.next() )
                 price = rs.getInt(1);
        }catch(SQLException ex){
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return price;
    }

    //mohamed ali end
}
