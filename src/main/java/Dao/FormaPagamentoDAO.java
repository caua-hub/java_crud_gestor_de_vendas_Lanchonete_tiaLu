package Dao;


import Conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Vinicius
 */
public class FormaPagamentoDAO {
    
    static Connection conn;
    static PreparedStatement stmt;
    static ResultSet rs;

    public static void consultarFormasPagamento(javax.swing.JTable tabela) {
        String sql = "SELECT id_form_pagamento, nome_forn_pag, taxa FROM forma_pagamento";

        javax.swing.table.DefaultTableModel modelo =
                (javax.swing.table.DefaultTableModel) tabela.getModel();

        modelo.setNumRows(0);

        try {
            conn = Conexao.conectar();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getString("nome_forn_pag"),
                    rs.getDouble("taxa"),
                    rs.getInt("id_form_pagamento")
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar formas de pagamento: " + e.getMessage());
        }
    }
    
    public static void excluirFormaPagamento(int idFormaPagamento) {
    String sql = "DELETE FROM forma_pagamento WHERE id_form_pagamento = ?";

    try {
        conn = Conexao.conectar();
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idFormaPagamento);

        int linhasAfetadas = stmt.executeUpdate();

        if (linhasAfetadas > 0) {
            JOptionPane.showMessageDialog(null, "Forma de pagamento excluída com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma forma de pagamento foi encontrada para excluir.");
        }

        } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao excluir forma de pagamento: " + e.getMessage());
        }
    }
    
    public static void atualizarFormaPagamento(int idFormaPagamento, String nome, double taxa) {
    String sql = "UPDATE forma_pagamento SET nome_forn_pag = ?, taxa = ? WHERE id_form_pagamento = ?";

    try {
        conn = Conexao.conectar();
        stmt = conn.prepareStatement(sql);

        stmt.setString(1, nome);
        stmt.setDouble(2, taxa);
        stmt.setInt(3, idFormaPagamento);

        int linhasAfetadas = stmt.executeUpdate();

        if (linhasAfetadas > 0) {
            JOptionPane.showMessageDialog(null, "Forma de pagamento atualizada com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma forma de pagamento foi encontrada para atualizar.");
        }

        } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao atualizar forma de pagamento: " + e.getMessage());
        }
    }
    
}
