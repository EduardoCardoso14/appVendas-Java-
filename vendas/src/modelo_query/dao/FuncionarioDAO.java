/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo_query.dao;

import conexao.Banco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Funcionario;

/**
 *
 * @author 962670
 */
public class FuncionarioDAO {

    public void create(Funcionario f) {

        Connection con = Banco.getConnection();
        Connection abc = Banco.getConnection();
        PreparedStatement stmt = null;
        PreparedStatement s2 = null;

        try {
            stmt = con.prepareStatement("INSERT INTO funcionario (nome, email, celular, cep, endereco, numero, bairro, cidade, complemento, rg, cpf, cargo, senha) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getEmail());
            stmt.setString(3, f.getCelular());
            stmt.setInt(4, f.getCep());
            stmt.setString(5, f.getEndereco());
            stmt.setInt(6, f.getNumero());
            stmt.setString(7, f.getBairro());
            stmt.setString(8, f.getCidade());
            stmt.setString(9, f.getComplemento());
            stmt.setInt(10, f.getDocRg());
            stmt.setInt(11, f.getCpf());
            stmt.setString(12, f.getCargo());
            stmt.setString(13, f.getSenha());
            stmt.executeUpdate();

            s2 = abc.prepareStatement("INSERT INTO usuario (login, senha) VALUES (?,?)");
            s2.setString(1, f.getNome());
            s2.setString(2, f.getSenha());

            JOptionPane.showMessageDialog(null, "Salvo com sucesso, " + f.getNome() + "!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            Banco.closeConnection(con, stmt);
        }

    }

    public void createuser(Funcionario f) {

        Connection con = Banco.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO usuario (login, senha) VALUES (?,?)");
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getSenha());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            Banco.closeConnection(con, stmt);
        }

    }

}
