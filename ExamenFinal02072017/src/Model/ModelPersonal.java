package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 * @author Harold Salces
 * @version 1.0
 * @created 02-jul.-2017 19:16:43
 */
public class ModelPersonal {

    private int idEmpleado;
    private int nroCarnet;
    private String nombre;
    private String apellidos;
    private String fechaContratacion;
    public Conexion m_Conexion;

    public ModelPersonal() {
        m_Conexion = Conexion.getInstancia();
    }

    public void finalize() throws Throwable {

    }

    public DefaultTableModel getPersonal() {
        String[] columnNames = {"idPersonal", "nroCarnet", "nombre", "apellidos", "fechaContratacion"};
        DefaultTableModel tabla = new DefaultTableModel(columnNames, 0);
        Statement cons;
        ResultSet rs = null;
        String sql = "select idEmpleado,empleado.nroCarnet,nombre,apellidos,fechaContratacion from empleado,persona where persona.nroCarnet=empleado.nroCarnet";

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
                tabla.setValueAt(rs.getObject(5).toString(), i, 4);

                i++;
            }
            cons.close();
        } catch (SQLException e) {
            System.out.println("No se pudieron cargar los datos");
        }
        return tabla;
    }

    public void registrar() {
        try {

            String sql = "insert into persona (nroCarnet,nombre,apellidos) values (?,?,?)";
            int generate_Id = 0;
            PreparedStatement ps = m_Conexion.getConexion().prepareStatement(sql);

            ps.setInt(1, nroCarnet);
            ps.setString(2, nombre);
            ps.setString(3, apellidos);

            ps.executeUpdate();

            sql = "insert into empleado (fechaContratacion,nroCarnet) values (?,?)";
            ps = m_Conexion.getConexion().prepareStatement(sql);

            ps.setString(1, fechaContratacion);

            ps.setInt(2, nroCarnet);

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ModelCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificar() {
        try {
            String sql = "update persona set nombre=?,apellidos=? where nroCarnet=?";
            PreparedStatement ps = m_Conexion.getConexion().prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, apellidos);
            ps.setInt(3, nroCarnet);
            ps.execute();
            sql = "update empleado set fechaContratacion=? where nroCarnet=?";
            ps = m_Conexion.getConexion().prepareStatement(sql);

            ps.setString(1, fechaContratacion);

            ps.setInt(2, nroCarnet);

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ModelCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void eliminar() {
        try {
            String sql = "delete from empleado where nroCarnet=?";
            PreparedStatement ps = m_Conexion.getConexion().prepareStatement(sql);
            ps.setInt(1, nroCarnet);
            ps.execute();
            sql = "delete from persona where nroCarnet=?";
            ps = m_Conexion.getConexion().prepareStatement(sql);
            ps.setInt(1, nroCarnet);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ModelCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param nroCarnet
     * @param nombre
     * @param apellidos
     * @param fechaContratacion
     */
    public void setPersonal(int nroCarnet, String nombre, String apellidos, String fechaContratacion) {
        this.nroCarnet = nroCarnet;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaContratacion = fechaContratacion;
    }

}
