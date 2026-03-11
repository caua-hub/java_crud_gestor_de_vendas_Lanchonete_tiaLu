/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Cauã Naves
 */
public class InserirTabelas {
    
    public static void carregarTabela(javax.swing.JTable tabela, Object[] ob, String tabela_banco){

    Connection conn;
    PreparedStatement stmt;
    ResultSet rs;

    DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
    modelo.setNumRows(0); // limpa tabela

    try {

        conn = Conexao.conectar();

        stmt = conn.prepareStatement("SELECT * FROM " + tabela_banco + ";");

        rs = stmt.executeQuery();

        while(rs.next()){

            modelo.addRow(ob);

        }

    } catch(Exception e){

        System.out.println("Erro: " + e);

    }

}
}
