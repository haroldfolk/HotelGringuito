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
 * @created 04-jul.-2017 06:20:09
 */
public class ModelConsumo {

    int idConsumo;
    float monto;
    String concepto;
    int idReserva;
    public ModelReserva m_reservas;
    public EstrategiaConsumo m_EstrategiaConsumo;
    public Conexion m_Conexion;

    public ModelConsumo() {
        m_Conexion = Conexion.getInstancia();
        m_reservas = new ModelReserva();

    }

    public void setConsumo(float mont, String concept, int idRes) {
        this.monto = mont;
        this.concepto = concept;
        this.idReserva = idRes;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public void setM_EstrategiaConsumo(EstrategiaConsumo m_EstrategiaConsumo) {
        this.m_EstrategiaConsumo = m_EstrategiaConsumo;
    }

    public DefaultTableModel getConsumos(int idReserva) {
        String[] columnNames = {"idConsumo", "monto", "concepto", "idReserva"};
        DefaultTableModel tabla = new DefaultTableModel(columnNames, 0);
        Statement cons;
        ResultSet rs = null;
        String sql = "select idConsumo,monto,concepto,idReserva from consumo where idreserva=" + idReserva;

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

    public DefaultTableModel getReservas() {
        return m_reservas.getReservas();
    }

    public float consultarConsumo(EstrategiaConsumo estrategia, int idReser) {
        this.setM_EstrategiaConsumo(estrategia);

       
        return this.m_EstrategiaConsumo.calcularConsumo(this.getConsumos(idReser));

    }

    public void registrarConsumo() {
        try {

            String sql = "insert into consumo (monto,concepto,idreserva) values (?,?,?)";
            int generate_Id = 0;
            PreparedStatement ps = m_Conexion.getConexion().prepareStatement(sql);

            ps.setFloat(1, monto);
            ps.setString(2, concepto);
            ps.setInt(3, idReserva);

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ModelCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
