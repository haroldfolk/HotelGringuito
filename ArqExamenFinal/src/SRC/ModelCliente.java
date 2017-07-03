/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SRC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Harold Salces
 */
public class ModelCliente {

    int ci, telefono;
    String nombre, apellidos, direccion;
    Conexion conexion;

    public ModelCliente() {
        conexion = Conexion.getInstancia();

    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

   

    public DefaultTableModel getClientes() {
        String[] columnNames = {"ci", "nombre", "apellidos", "direccion", "telefono"};
        DefaultTableModel tabla = new DefaultTableModel(columnNames, 0);
        Statement cons;
        ResultSet rs = null;
        String sql = "select * from cliente";

        try {
            cons = (Statement) conexion.getConexion().createStatement();
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

    public void guardar()  {
        try {
            Statement st;
            ResultSet rs = null;
            String sql = "insert into cliente (nroCarnet,nombre,apellidos,direccion,telefono) values (?,?,?,?,?)";
            PreparedStatement ps=conexion.getConexion().prepareStatement(sql);
            ps.setInt(1, ci);
            ps.setString(2,nombre );
            ps.setString(3,apellidos);
            ps.setString(4,direccion);
            ps.setInt(5, telefono);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ModelCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void actualizar() {
        try {
            Statement st;
            ResultSet rs = null;
            String sql = "update cliente set nombre='"+this.nombre+"',apellidos='"+this.apellidos+"',direccion='"+this.direccion+"',telefono='"+this.telefono+"' where nroCarnet='"+this.ci+"'";
            st=(Statement) conexion.getConexion().createStatement();
            st.execute(sql);
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ModelCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar() {
        try {
            Statement st;
            ResultSet rs = null;
            String sql = "delete from  cliente where nroCarnet="+this.ci;
            st=(Statement) conexion.getConexion().createStatement();
            st.execute(sql);
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ModelCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
