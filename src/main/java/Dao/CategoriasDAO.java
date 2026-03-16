/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Conexao.Conexao;
import Model.Categorias;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
    
    public static void consultarCategorias(JTable tabela) {
    String sql = "SELECT id_categoria, nome_categoria FROM categorias ORDER BY id_categoria";

    DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
    modelo.setRowCount(0);

    try {
        conn = Conexao.conectar();
        stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            modelo.addRow(new Object[]{
                rs.getInt("id_categoria"),
                rs.getString("nome_categoria")
            });
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao consultar categorias: " + e.getMessage());
    }
    }
    
    public static void excluirCategoria(int idCategoria) {
        String sql = "DELETE FROM categorias WHERE id_categoria = ?";

        try {
            conn = Conexao.conectar();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idCategoria);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
            JOptionPane.showMessageDialog(null, "Categoria excluída com sucesso!");
            } else {
            JOptionPane.showMessageDialog(null, "Nenhuma categoria foi encontrada para excluir.");
            }

        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Erro ao excluir categoria: " + e.getMessage());
        }
    }
    
    public static void atualizarCategoria(int idCategoria, String novoNome) {
        String sql = "UPDATE categorias SET nome_categoria = ? WHERE id_categoria = ?";

        try {
            conn = Conexao.conectar();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, novoNome);
            stmt.setInt(2, idCategoria);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
            JOptionPane.showMessageDialog(null, "Categoria atualizada com sucesso!");
            } else {
            JOptionPane.showMessageDialog(null, "Nenhuma categoria foi encontrada para atualizar.");
            }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar categoria: " + e.getMessage());
            }
    }

}
