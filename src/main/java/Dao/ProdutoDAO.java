/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Conexao.Conexao;
import Model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Cauã Naves
 */
public class ProdutoDAO {
    Connection conn;
    PreparedStatement stmt;

    public void inserir(Produto p) {

        String sql = "INSERT INTO produto(nome, preco, estoque) VALUES (?, ?, ?)";

        try {

            conn = Conexao.conectar();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getPreco());
            stmt.setInt(3, p.getEstoque());

            stmt.execute();

            System.out.println("produto inserido!");

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

    }

    
}
