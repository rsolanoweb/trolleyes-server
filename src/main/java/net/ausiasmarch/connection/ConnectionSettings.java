/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.connection;

/**
 *
 * @author Ruben
 */
public class ConnectionSettings {

    public static String getURL() {
        return "jdbc:mysql://localhost:3306/trolleyes";
    }

    public static String getUsername() {
        return "trolleyes";
    }

    public static String getPassword() {
        return "bitnami";
    }

}
