package Model;

import javax.swing.JOptionPane;

/**
 * @author Harold Salces
 * @version 1.0
 * @created 03-jul.-2017 12:46:39
 */
public class Ingresada extends EstadoReserva {

    public Ingresada() {

    }

    @Override
    public void ingresarReserva(ModelReserva mR) {
        JOptionPane.showMessageDialog(null, "ya esta ingresada");
        throw new ExceptionInInitializerError("No, ya esta ingresada");

    }

    @Override
    public void confirmarReserva(ModelReserva mR) {
        mR.setEstadoReserva(new Confirmada(), 1);
        JOptionPane.showMessageDialog(null, "Se confirmo la reserva");
    }

    @Override
    public void cancelarReserva(ModelReserva mR) {
        mR.setEstadoReserva(new Cancelada(), 3);
        JOptionPane.showMessageDialog(null, "Se cancelo la reserva");
    }

    @Override
    public void terminarReserva(ModelReserva mR) {
        JOptionPane.showMessageDialog(null, "No se puede terminar la reserva por que no esta confirmada");
        throw new ExceptionInInitializerError("No, ya esta cancelado");

    }

}
