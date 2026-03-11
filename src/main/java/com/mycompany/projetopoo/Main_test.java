/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetopoo;

import Dao.ProdutoDAO;
import Model.Produto;

/**
 *
 * @author Cauã Naves
 */
public class Main_test {

    public static void main(String[] args) {

        Produto p = new Produto();

        p.setNome("bolo");
        p.setPreco(25.2);
        p.setEstoque(7);

        ProdutoDAO dao = new ProdutoDAO();

        dao.inserir(p);

    }

}
