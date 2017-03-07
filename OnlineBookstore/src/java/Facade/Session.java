/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import websitemodel.ClientsDAO;

/**
 *
 * @author abdelrhman galal
 */
public class Session {
    ClientsDAO clientsDAO;
    public Session() {
        clientsDAO = new ClientsDAO();
    }

    public boolean check() {
        return clientsDAO.check();
    }

}
