/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Cauã Naves
 */

public class Conexao {
    public static Connection conectar() {

        Connection conn = null;

        try {

            String url = "jdbc:mysql://localhost:3306/tia_lu";
            String usuario = "root";
            String senha = "1533";

            conn = DriverManager.getConnection(url, usuario, senha);

            System.out.println("Conectado!");

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return conn;
    }
}
