package controllers;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import utils.ConnectionDB;
import views.GUIConnect;
import views.GUILogin;

public class ControllerConnect {
    GUIConnect connect;

    public ControllerConnect(GUIConnect connect) {
        this.connect = connect;
        makeConnection();

    }

    public void makeConnection(){
        try{
            ConnectionDB.connect();
            new GUILogin();
            connect.dispose();
        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(connect, "Check your connection and try again.", "Connection Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
    
}
