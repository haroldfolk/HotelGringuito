package Model;




/**
 * @author Harold Salces
 * @version 1.0
 * @created 03-jul.-2017 12:46:37
 */
public abstract class EstadoReserva {

	public EstadoReserva(){

	}

	public abstract void  ingresarReserva(ModelReserva mR);

	public abstract void confirmarReserva(ModelReserva mR);

	public abstract void cancelarReserva(ModelReserva mR);

	public abstract void terminarReserva(ModelReserva mR);

}