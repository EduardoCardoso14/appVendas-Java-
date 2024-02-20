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
import model.bean.Venda;

/**
 *
 * @author 962670
 */
public class VendaDAO {

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
                }
            if (oi > 1) {
            }else{
            JOptionPane.showMessageDialog(null, "Nenhuma venda encontrada!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Banco.closeConnection(con, stmt, rs);
        }

        // return oi;
        return oi;
    }
}
