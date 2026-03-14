/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Conexao.Conexao;
import Model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Cauã Naves
 */
public class ProdutoDAO {
    static Connection conn;
    static PreparedStatement stmt;
    static ResultSet rs;

    public static void inserir(Produto p) {

        String sql = "INSERT INTO produto(nome, preco, estoque, id_cat) VALUES (?, ?, ?, ?)";

        try {
            conn = Conexao.conectar();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getPreco());
            stmt.setInt(3, p.getEstoque());
            stmt.setInt(4, p.getId_cat());

            stmt.execute();
            JOptionPane.showMessageDialog(null, "Produto inserido com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
    }

    public static void consultarProdutosPorCategoria(String categoria, javax.swing.JTable tabela) {
        String sql = "SELECT p.id AS id_produto, p.nome, p.preco, p.estoque " +
             "FROM produto p " +
             "INNER JOIN categorias c ON p.id_cat = c.id_categoria " +
             "WHERE c.nome_categoria = ?";

        javax.swing.table.DefaultTableModel modelo =
                (javax.swing.table.DefaultTableModel) tabela.getModel();

        modelo.setNumRows(0);
        
        

        try {
            conn = Conexao.conectar();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, categoria);

            rs = stmt.executeQuery();

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getString("nome"),
                    rs.getDouble("preco"),
                    rs.getInt("estoque"),
                    rs.getInt("id_produto")
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar produtos: " + e.getMessage());
        }     
    }
    
    public static void excluirProduto(int idProduto) {
    String sql = "DELETE FROM produto WHERE id = ?";

    try {
        conn = Conexao.conectar();
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idProduto);

        int linhasAfetadas = stmt.executeUpdate();

        if (linhasAfetadas > 0) {
            JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum produto foi encontrado para excluir.");
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao excluir produto: " + e.getMessage());
    }

    }
}
