/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Harold Salces
 */
public class ModelReservaHabitacion {

    int idReserva;
    int idHabitacion;
    Conexion m_Conexion;

    public ModelReservaHabitacion() {
        m_Conexion = Conexion.getInstancia();
    }
    
    public void setReservaHabitacion(int idReser){

        this.idReserva=idReser;
    }
    public void registrar(DefaultTableModel habs,int[] indexs){
        for (int i = 0; i < indexs.length; i++) {
            int index = indexs[i];
            this.idHabitacion=Integer.parseInt(habs.getValueAt(index, 0).toString());
            try {
            
            String sql = "insert into reservahabitacion (idreserva,idhabitacion) values (?,?)";
            
            PreparedStatement ps = m_Conexion.getConexion().prepareStatement(sql);
            ps.setInt(1, this.idReserva);
            ps.setInt(2, this.idHabitacion);
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ModelCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        
    };

}
