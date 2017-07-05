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
 * @created 02-jul.-2017 19:16:50
 */
public class ModelTipoTarifa {

    public Conexion m_Conexion;

    public ModelTipoTarifa() {
        m_Conexion = Conexion.getInstancia();
    }

    /**
     *
     * @param idTarifario
     */
    public void registrarPreciosParaTarifario(int idTarifario) {
        try {

            String sql = "select * from tipohabitacion";

            PreparedStatement ps = m_Conexion.getConexion().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idTipo = rs.getInt("idTipo");
                int cero = 0;
                String sql2 = "insert into tipotarifa (idTipo,idTarifa,precio) values (?,?,?)";

                PreparedStatement ps2 = m_Conexion.getConexion().prepareStatement(sql2);
                ps2.setInt(1, idTipo);
                ps2.setInt(2, idTarifario);
                ps2.setInt(3, cero);
                ps2.executeUpdate();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ModelCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param idTarifario
     */
    public DefaultTableModel getPrecios(int idTarifario) {
        String[] columnNames = { "idTipo","idTarifario", "precio"};
        DefaultTableModel tabla = new DefaultTableModel(columnNames, 0);
        Statement cons;
        ResultSet rs = null;
        String sql = "select * from tipotarifa where idTarifa="+idTarifario;
        try {
            cons = (Statement) m_Conexion.getConexion().createStatement();
            rs = cons.executeQuery(sql);
            int i = 0;
            while (rs.next()) {
                tabla.setRowCount(tabla.getRowCount() + 1);
                tabla.setValueAt(rs.getObject(1).toString(), i, 0);
                tabla.setValueAt(rs.getObject(2).toString(), i, 1);
                tabla.setValueAt(rs.getObject(3).toString(), i, 2);

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
     * @param tabla
     */
    public void setPrecios(DefaultTableModel tabla) {
        for (int i = 0; i < tabla.getRowCount(); i++) {
            try {
                String sql = "update tipotarifa set precio=? where idTarifa=? and idTipo=?";

                PreparedStatement ps = m_Conexion.getConexion().prepareStatement(sql);

                ps.setInt(1, Integer.parseInt(tabla.getValueAt(i, 2).toString()));
                ps.setInt(2, Integer.parseInt(tabla.getValueAt(i, 1).toString()));
                ps.setInt(3, Integer.parseInt(tabla.getValueAt(i, 0).toString()));

                ps.executeUpdate();
                System.out.println("se actualizaron los precios");
            } catch (SQLException ex) {
                Logger.getLogger(ModelTipoTarifa.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}
