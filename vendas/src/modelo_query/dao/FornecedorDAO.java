/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo_query.dao;

import conexao.Banco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.bean.Fornecedor;

/**
 *
 * @author 962670
 */
public class FornecedorDAO {

    public void create(Fornecedor f) {

        Connection con = Banco.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO fornecedor (nome, email, celular, cep, endereco, numero, bairro, cidade, complemento, cnpj) VALUES (?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getEmail());
            stmt.setString(3, f.getCelular());
            stmt.setInt(4, f.getCep());
            stmt.setString(5, f.getEndereco());
            stmt.setInt(6, f.getNumero());
            stmt.setString(7, f.getBairro());
            stmt.setString(8, f.getCidade());
            stmt.setString(9, f.getComplemento());
            stmt.setString(10, f.getCnpj());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso, forn: " + f.getNome() + "!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            Banco.closeConnection(con, stmt);
        }

    }
}
