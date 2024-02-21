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
import model.bean.Produto;
import model.bean.Venda;

/**
 *
 * @author 962670
 */
public class VendaDAO {
    
    public void criarAmigo(Venda oi) {
        
        Connection con = Banco.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO vendas (valor,data,obs)VALUES(?,?,?)");
            stmt.setDouble(1, oi.getValor());
            stmt.setString(2, oi.getData());
            stmt.setString(3, oi.getObs());
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            Banco.closeConnection(con, stmt);
        }

    }

    public static double verTotal(String datezin) {
        double oi = 0;
        Connection con = Banco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select sum(valor) as total from vendas where data = ?");
            stmt.setString(1, datezin);
            rs = stmt.executeQuery();
             while (rs.next()) {
                    oi = rs.getDouble("total");
                    if (oi < 1){
                        JOptionPane.showMessageDialog(null, "Nenhuma venda encontrada!");
                    }
                }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Banco.closeConnection(con, stmt, rs);
        }

        // return oi;
        return oi;
    }
    
        public List<Venda> read() {

        Connection con = Banco.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Venda> vendas = new ArrayList<>();

        try {
            stmt = con.prepareStatement("select idvendas,valor,obs,DATE_FORMAT(data, \"%d/%m/%Y\") as data from vendas");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Venda venda = new Venda();

                venda.setId(rs.getInt("idvendas"));
                venda.setData(rs.getString("data"));
                venda.setValor(rs.getDouble("valor"));
                venda.setObs(rs.getString("obs"));
                vendas.add(venda);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Banco.closeConnection(con, stmt, rs);
        }

        return vendas;

    }
}
