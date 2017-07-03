package SRC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;




public class Conexion {
    
    private static Conexion instancia;
    private Connection conec;
    private String driver="com.mysql.jdbc.Driver";
    
    
    private Conexion() {
        try
        {   
            Class.forName(driver);
            this.conec = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_master", "root", "");
            System.out.println("conexion realizada con exito");
        }catch(Exception ee){
            System.out.println(ee.toString()+" conexion fallida");
        }   
    }
    
    
    public void cerrarConexion(){
        try {
            conec.close();
             System.out.println("Cerrando conexion a ");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        
    }
    
    public static Conexion getInstancia(){
        if (instancia == null) {
             instancia=new Conexion();
        }
        return instancia;  
    }
    
    public Connection getConexion(){
        return this.conec;
    }
}
