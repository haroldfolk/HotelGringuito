package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 * @author Harold Salces
 * @version 1.0
 * @created 02-jul.-2017 19:16:46
 */
public class ModelHabitacion {

    private int idHabitacion;
    private String descripcion;
    private String ubicacion;
    private int idTipo;
    public Conexion m_Conexion;
    public ModelTipo m_ModelTipo;

    public ModelHabitacion() {
        m_Conexion = Conexion.getInstancia();
        m_ModelTipo = new ModelTipo();
    }

    /**
     *
     * @param idTipo
     */
    public DefaultTableModel getHabitacionesConTipo(int idTipo) {
        return null;
    }

    public DefaultTableModel getHabitaciones() {
        String[] columnNames = {"idHabitacion", "descripcion", "ubicacion", "idTipo"};
        DefaultTableModel tabla = new DefaultTableModel(columnNames, 0);
        Statement cons;
        ResultSet rs = null;
        String sql = "select * from habitacion";

        try {
            cons = (Statement) m_Conexion.getConexion().createStatement();
            rs = cons.executeQuery(sql);
            int i = 0;
            while (rs.next()) {
                tabla.setRowCount(tabla.getRowCount() + 1);
                tabla.setValueAt(rs.getObject(1).toString(), i, 0);
                tabla.setValueAt(rs.getObject(2).toString(), i, 1);
                tabla.setValueAt(rs.getObject(3).toString(), i, 2);
                tabla.setValueAt(rs.getObject(4).toString(), i, 3);

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
     * @param idHabitacion
     * @param descripcion
     * @param ubicacion
     * @param idTipo
     */
    public void setHabitacion(int idHabitacion, String descripcion, String ubicacion, int idTipo) {
        this.descripcion = descripcion;
        this.idHabitacion = idHabitacion;
        this.idTipo = idTipo;
        this.ubicacion = ubicacion;
    }

    public void registrar() {
        try {

            String sql = "insert into habitacion (descripcion,ubicacion,idTipo) values (?,?,?)";
            int generate_Id = 0;
            PreparedStatement ps = m_Conexion.getConexion().prepareStatement(sql);

            ps.setString(1, descripcion);
            ps.setString(2, ubicacion);
            ps.setInt(3, idTipo);

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ModelCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificar() {
        try {
            String sql = "update habitacion set descripcion=?,ubicacion=?,idTipo=? where idHabitacion=?";
            PreparedStatement ps = m_Conexion.getConexion().prepareStatement(sql);
            ps.setString(1, descripcion);
            ps.setString(2, ubicacion);
            ps.setInt(3, idTipo);
            ps.setInt(4, idHabitacion);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ModelHabitacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar() {
        try {
            String sql = "delete from habitacion where idHabitacion=?";
            PreparedStatement ps = m_Conexion.getConexion().prepareStatement(sql);
            ps.setInt(1, idHabitacion);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ModelHabitacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DefaultComboBoxModel getListaTipos() {
        return m_ModelTipo.getListaTipos();
    }

}
