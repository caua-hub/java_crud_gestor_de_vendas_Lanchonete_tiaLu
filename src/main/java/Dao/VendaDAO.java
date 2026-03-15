/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vinicius
 */
public class VendaDAO {
    
    static Connection conn;
    static PreparedStatement stmt;
    static ResultSet rs;

    public static void carregarFormasPagamentoConsulta(JComboBox<String> combo) {
        String sql = "SELECT nome_forn_pag FROM forma_pagamento ORDER BY nome_forn_pag";

        combo.removeAllItems();
        combo.addItem("Selecione");
        combo.addItem("Todas");

        try {
            conn = Conexao.conectar();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                combo.addItem(rs.getString("nome_forn_pag"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar formas de pagamento: " + e.getMessage());
        }
    }

    public static void consultarVendasPorFormaPagamento(String formaPagamento, JTable tabela) {
        String sqlBase =
                "SELECT v.id_vendas, fp.nome_forn_pag, " +
                "COALESCE(SUM(iv.quantidade), 0) AS quantidade_total, v.valor_total " +
                "FROM vendas v " +
                "INNER JOIN forma_pagamento fp ON v.id_form_pag = fp.id_form_pagamento " +
                "LEFT JOIN item_venda iv ON iv.id_vend = v.id_vendas ";

        String sql;

        if (formaPagamento.equals("Todas")) {
            sql = sqlBase +
                  "GROUP BY v.id_vendas, fp.nome_forn_pag, v.valor_total " +
                  "ORDER BY v.id_vendas DESC";
        } else {
            sql = sqlBase +
                  "WHERE fp.nome_forn_pag = ? " +
                  "GROUP BY v.id_vendas, fp.nome_forn_pag, v.valor_total " +
                  "ORDER BY v.id_vendas DESC";
        }

        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setRowCount(0);

        try {
            conn = Conexao.conectar();
            stmt = conn.prepareStatement(sql);

            if (!formaPagamento.equals("Todas")) {
                stmt.setString(1, formaPagamento);
            }

            rs = stmt.executeQuery();

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getInt("id_vendas"),
                    rs.getString("nome_forn_pag"),
                    rs.getInt("quantidade_total"),
                    rs.getDouble("valor_total")
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar vendas: " + e.getMessage());
        }
    }
    
}
