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
import model.bean.Produto;

/**
 *
 * @author 962670
 */
public class FuncionarioDAO {

    public void create(Funcionario f) {

        Connection con = Banco.getConnection();
        PreparedStatement stmt = null;

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
            stmt.setString(1, f.getEmail());
            stmt.setString(2, f.getSenha());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            Banco.closeConnection(con, stmt);
        }

    }

    public List<Funcionario> read() {

        Connection con = Banco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Funcionario> funcionarios = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM funcionario");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Funcionario funcionario = new Funcionario();

                funcionario.setId(rs.getInt("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getInt("cpf"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setSenha(rs.getString("senha"));
                funcionarios.add(funcionario);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Banco.closeConnection(con, stmt, rs);
        }

        return funcionarios;

    }

    public List<Funcionario> readForDesc(String desc) {

        Connection con = Banco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Funcionario> funcionarios = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM funcionario WHERE nome LIKE ?");
            stmt.setString(1, "%" + desc + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Funcionario funcionario = new Funcionario();

                funcionario.setId(rs.getInt("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getInt("cpf"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setSenha(rs.getString("senha"));
                funcionarios.add(funcionario);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Banco.closeConnection(con, stmt, rs);
        }

        return funcionarios;

    }
    
    public void update(Funcionario f) {

        Connection con = Banco.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE funcionario SET nome = ? ,cpf = ?,email = ?, senha = ? WHERE id = ?");
            stmt.setString(1, f.getNome());
            stmt.setInt(2, f.getCpf());
            stmt.setString(3, f.getEmail());
            stmt.setString(4, f.getSenha());
            stmt.setInt(5, f.getId());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            Banco.closeConnection(con, stmt);
        }

    }

    public void delete(Funcionario f) {

        Connection con = Banco.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM funcionario WHERE id = ?");
            stmt.setInt(1, f.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            Banco.closeConnection(con, stmt);
        }

    }

}
