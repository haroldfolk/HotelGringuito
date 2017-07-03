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
 * @created 02-jul.-2017 19:16:48
 */
public class ModelTarifario {
int idTarifa;
String nombre;
    public Conexion m_Conexion;
    public ModelTipoTarifa m_ModelTipoTarifa;

    public ModelTarifario() {
        m_Conexion = Conexion.getInstancia();
        m_ModelTipoTarifa = new ModelTipoTarifa();
    }

    public void finalize() throws Throwable {

    }

    public DefaultTableModel getTarifarios() {
        String[] columnNames = {"idTarifa", "nombre"};
        DefaultTableModel tabla = new DefaultTableModel(columnNames, 0);
        Statement cons;
        ResultSet rs = null;
        String sql = "select * from tarifa";

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
     * @param nombre
     */
    public void setTarifario(String nombre) {
        this.nombre=nombre;
        
    }

    public void registrar() {
try {
int generate_Id = 0;
            String sql = "insert into tarifa (nombre) values (?)";
            
            PreparedStatement ps = m_Conexion.getConexion().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1,nombre);
            

             int rows = ps.executeUpdate();
            if (rows != 0) {
                ResultSet generateKeys = ps.getGeneratedKeys();
                if (generateKeys.next()) {
                    generate_Id = generateKeys.getInt(1);
                }
            }
m_ModelTipoTarifa.registrarPreciosParaTarifario(generate_Id);
        } catch (SQLException ex) {
            Logger.getLogger(ModelCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param idTarifario
     */
    public DefaultTableModel getPrecios(int idTarifario) {
        return m_ModelTipoTarifa.getPrecios(idTarifario);
    }

    /**
     *
     * @param tabla
     */
    public void setPrecios(DefaultTableModel tabla) {
        m_ModelTipoTarifa.setPrecios(tabla);
    }

}
