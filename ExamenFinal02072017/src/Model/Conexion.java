package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * @author Harold Salces
 * @version 1.0
 * @created 02-jul.-2017 19:16:44
 */
public class Conexion {

	private static Conexion instancia;
	private Connection conex;
	private String driver="com.mysql.jdbc.Driver";

	private Conexion(){
try
        {   
            Class.forName(driver);
            this.conex = DriverManager.getConnection("jdbc:mysql://localhost:3306/arqfinal", "root", "");
            System.out.println("conexion realizada con exito");
        }catch(Exception ee){
            System.out.println(ee.toString()+" conexion fallida");
        }   
	}

	public void finalize() throws Throwable {

	}

	public Connection getConexion(){
		  return this.conex;
	}

	public static Conexion getInstancia(){
		 if (instancia == null) {
             instancia=new Conexion();
        }
        return instancia;  
	}

	public void cerrarConexion(){
 try {
            conex.close();
             System.out.println("Cerrando conexion a ");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
	}

}