package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

/**
 * @author Harold Salces
 * @version 1.0
 * @created 02-jul.-2017 19:16:49
 */
public class ModelTipo {

    private int idTipo;
    private String descripcion;
    public Conexion m_Conexion;

    public ModelTipo() {
        m_Conexion = Conexion.getInstancia();
    }

    public void finalize() throws Throwable {

    }

    public DefaultTableModel getTipos() {
        String[] columnNames = {"idTipo", "descripcion"};
        DefaultTableModel tabla = new DefaultTableModel(columnNames, 0);
        Statement cons;
        ResultSet rs = null;
        String sql = "select * from tipoHabitacion";

        try {
            cons = (Statement) m_Conexion.getConexion().createStatement();
            rs = cons.executeQuery(sql);
            int i = 0;
            while (rs.next()) {
                tabla.setRowCount(tabla.getRowCount() + 1);
                tabla.setValueAt(rs.getObject(1).toString(), i, 0);
                tabla.setValueAt(rs.getObject(2).toString(), i, 1); 
                i++;
            }
            cons.close();
        } catch (SQLException e) {
            System.out.println("No se pudieron cargar los datos");
        }
        return tabla;
    }

    /**
     *
     * @param idTipo
     * @param descripcion
     */
    public void setTipo(int idTipo, String descripcion) {
        this.idTipo = idTipo;
        this.descripcion=descripcion;
    }

    public void registrar() {
try {

            
            String sql = "insert into tipoHabitacion (descripcion) values (?)";
            
            PreparedStatement ps = m_Conexion.getConexion().prepareStatement(sql);

            ps.setString(1,descripcion);

             ps.executeUpdate();
            
           
        } catch (SQLException ex) {
            Logger.getLogger(ModelCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificar() {
        try {
            String sql="update tipoHabitacion set descripcion=? where idTipo=?";
            PreparedStatement ps=m_Conexion.getConexion().prepareStatement(sql);
            ps.setString(1, descripcion);
            ps.setInt(2, idTipo);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ModelTipo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar() {
 try {
            String sql="delete from tipoHabitacion where idTipo=?";
            PreparedStatement ps=m_Conexion.getConexion().prepareStatement(sql);
            ps.setInt(1, idTipo);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ModelCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DefaultComboBoxModel getListaTipos() {
        DefaultComboBoxModel combo = new DefaultComboBoxModel();
            Statement Consulta;
            ResultSet resultado = null;
            String dato = "select * from tipohabitacion";
             try {
            Consulta = (Statement) m_Conexion.getConexion().createStatement();
            resultado = Consulta.executeQuery(dato);
            while (resultado.next()) {
                JComboBox CB = new JComboBox();
                combo.addElement(resultado.getInt("idTipo"));
            }
            Consulta.close();

        } catch (Exception e) {
            System.out.println("no se pudo CARGAR LOS DATOS..");
        }

        return combo;
    }

}
