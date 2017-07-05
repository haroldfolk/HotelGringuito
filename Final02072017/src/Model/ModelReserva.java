package Model;

import java.sql.Date;
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
 * @created 03-jul.-2017 12:46:43
 */
public class ModelReserva {
    
    ModelCliente mClientes;
    ModelHabitacion mHabitaciones;
    ModelPersonal mPersonal;
    ModelTarifario mTarifas;
    ModelReservaHabitacion mReservasHabitaciones;
    EstadoReserva m_EstadoReserva;
    int idReserva;
    Date fechaReserva;
    Date fechaIngreso;
    Date fechaSalida;
    int estadoReserva;
    int idCliente;
    int idEmpleado;
    int idTarifa;
    Conexion m_Conexion;
    
    public ModelReserva() {
        m_Conexion = Conexion.getInstancia();
        mClientes = new ModelCliente();
        mHabitaciones = new ModelHabitacion();
        mPersonal = new ModelPersonal();
        mTarifas = new ModelTarifario();
        mReservasHabitaciones=new ModelReservaHabitacion();
    }
    
    public void setReserva(Date fechaR, int idCliente, int idEmpleado, int idTarifa) {
        this.fechaReserva = fechaR;
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
        this.idTarifa = idTarifa;
//        this.m_EstadoReserva = new Ingresada();
//        this.estadoReserva = 0;
    }
    
    public void setEstadoReserva(EstadoReserva eR, int estadoInt) {
        this.estadoReserva = estadoInt;
        this.m_EstadoReserva = eR;
    }
    
    public void cargarReserva(int idReserva) {
        try {
            String sql = "select idReserva,estadoReserva from reserva where idReserva=?";
            PreparedStatement ps = m_Conexion.getConexion().prepareStatement(sql);
            ps.setInt(1, idReserva);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.idReserva = rs.getInt("idReserva");
                this.estadoReserva = rs.getInt("estadoReserva");
                switch (this.estadoReserva) {
                    case 0:
                        this.m_EstadoReserva = new Ingresada();
                        break;
                    case 1:
                        this.m_EstadoReserva = new Confirmada();
                        break;
                    case 2:
                        this.m_EstadoReserva = new Terminada();
                        break;
                    case 3:
                        this.m_EstadoReserva = new Cancelada();
                        break;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ingresarReserva(DefaultTableModel habs,int[] indexs) {
        
        this.setEstadoReserva(new Ingresada(), 0);
        int generate_Id = 0; 
        try {
           
            String sql = "insert into reserva (fechaReserva,estadoReserva,idEmpleado,idCliente,idTarifa) values (?,?,?,?,?)";
            PreparedStatement ps = m_Conexion.getConexion().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, this.fechaReserva);
            ps.setInt(2, this.estadoReserva);
            ps.setInt(3, this.idEmpleado);
            ps.setInt(4, this.idCliente);
            ps.setInt(5, this.idTarifa);
            int rows = ps.executeUpdate();
            if (rows != 0) {
                ResultSet generateKeys = ps.getGeneratedKeys();
                if (generateKeys.next()) {
                    generate_Id = generateKeys.getInt(1);
                }
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ModelCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        mReservasHabitaciones.setReservaHabitacion(generate_Id);
        mReservasHabitaciones.registrar(habs,indexs);
//        m_EstadoReserva.ingresarReserva(this);
/////////
    }
    
    public void confirmarReserva(int idReserva, Date fechaIngreso) {
        this.cargarReserva(idReserva);
        m_EstadoReserva.confirmarReserva(this);
        
        
        
        
        
        try {
            
            String sql = "update reserva set fechaIngreso=?,estadoReserva=? where idReserva=?";
            
            PreparedStatement ps = m_Conexion.getConexion().prepareStatement(sql);
            
            ps.setDate(1, fechaIngreso);
            ps.setInt(2, this.estadoReserva);
            ps.setInt(3, idReserva);
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ModelCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void cancelarReserva(int idReserva) {
        this.cargarReserva(idReserva);
        m_EstadoReserva.cancelarReserva(this);
        
        try {
            
            String sql = "update reserva set estadoReserva=? where idReserva=?";
            
            PreparedStatement ps = m_Conexion.getConexion().prepareStatement(sql);
            
            ps.setInt(1, this.estadoReserva);
            ps.setInt(2, idReserva);
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ModelCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void terminarReserva(int idReserva, Date fechaSalida) {
        this.cargarReserva(idReserva);
        m_EstadoReserva.terminarReserva(this);
        
        try {
            
            String sql = "update reserva set fechaSalida=?,estadoReserva=? where idReserva=?";
            
            PreparedStatement ps = m_Conexion.getConexion().prepareStatement(sql);
            
            ps.setDate(1, fechaSalida);
            ps.setInt(2, this.estadoReserva);
            ps.setInt(3, idReserva);
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ModelCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public DefaultTableModel getReservas() {
        String[] columnNames = {"idReserva", "fechaReserva", "fechaIngreso", "fechaSalida", "Estado", "idEmpleado", "idCliente", "idTarifa"};
        DefaultTableModel tabla = new DefaultTableModel(columnNames, 0);
        Statement cons;
        ResultSet rs = null;
        String sql = "select * from reserva";
        
        try {
            cons = (Statement) m_Conexion.getConexion().createStatement();
            rs = cons.executeQuery(sql);
            int i = 0;
            while (rs.next()) {
                tabla.setRowCount(tabla.getRowCount() + 1);
                tabla.setValueAt(rs.getObject(1).toString(), i, 0);
                tabla.setValueAt(rs.getObject(2).toString(), i, 1);
                tabla.setValueAt(rs.getObject(3), i, 2);
                tabla.setValueAt(rs.getObject(4), i, 3);
                tabla.setValueAt(rs.getObject(5).toString(), i, 4);
                tabla.setValueAt(rs.getObject(6).toString(), i, 5);
                tabla.setValueAt(rs.getObject(7).toString(), i, 6);
                tabla.setValueAt(rs.getObject(8).toString(), i, 7);
                
                i++;
            }
            cons.close();
        } catch (SQLException e) {
            System.out.println("No se pudieron cargar los datos");
        }
        return tabla;
    }
    
    public DefaultTableModel getPersonal() {
        return mPersonal.getPersonal();
    }
    
    public DefaultTableModel getClientes() {
        return mClientes.getClientes();
    }
    
    public DefaultTableModel getHabitaciones() {
        return mHabitaciones.getHabitaciones();
    }
    
    public DefaultTableModel getTarifas() {
        return mTarifas.getTarifarios();
    }
}
