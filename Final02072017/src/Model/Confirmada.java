package Model;

import javax.swing.JOptionPane;

/**
 * @author Harold Salces
 * @version 1.0
 * @created 03-jul.-2017 12:46:34
 */
public class Confirmada extends EstadoReserva {

    @Override
    public void ingresarReserva(ModelReserva mR) {
        
        JOptionPane.showMessageDialog(null, "NO,Ya esta confirmada");
        throw new ExceptionInInitializerError("No, ya esta confirmada");
    }

    @Override
    public void confirmarReserva(ModelReserva mR) {
        JOptionPane.showMessageDialog(null, "No, ya esta confirmada");
        throw new ExceptionInInitializerError("No, ya esta confirmada");
    }

    @Override
    public void cancelarReserva(ModelReserva mR) {
        JOptionPane.showMessageDialog(null, "No, ya esta confirmada");
        throw new ExceptionInInitializerError("No, ya esta confirmada");
    }

    @Override
    public void terminarReserva(ModelReserva mR) {
        mR.setEstadoReserva(new Terminada(),2);
        JOptionPane.showMessageDialog(null, "SI,Se termino la reserva");
        
    }

}
