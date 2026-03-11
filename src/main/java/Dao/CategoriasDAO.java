/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Conexao.Conexao;
import Model.Categorias;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author Cauã Naves
 */
public class CategoriasDAO {
    static Connection conn;
    static PreparedStatement stmt;
    
    
    public static void inserir(Categorias c) {

        String sql = "INSERT INTO categorias(nome_categoria) VALUES (?)";

        try {

            conn = Conexao.conectar();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, c.getNome_categoria());
            
            stmt.execute();

            JOptionPane.showMessageDialog(null, "Categoria inserida com sucesso");
            
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

    }

}
