/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import websitemodel.databaseDTO.Client;

/**
 *
 * @author abdelrhman galal
 */
public class Session {
    Client clientsDAO;
    public Session() {
        clientsDAO = new Client();
    }

    public boolean check() {
        return true;
    }

}
