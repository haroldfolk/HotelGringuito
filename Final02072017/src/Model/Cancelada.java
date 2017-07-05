package Model;

import javax.swing.JOptionPane;

/**
 * @author Harold Salces
 * @version 1.0
 * @created 03-jul.-2017 12:46:36
 */
public class Cancelada extends EstadoReserva {

    public Cancelada() {

    }

    @Override
    public void ingresarReserva(ModelReserva mR) {
        JOptionPane.showMessageDialog(null, "No, ya esta cancelado");
        throw new ExceptionInInitializerError("No, ya esta cancelado");

    }

    @Override
    public void confirmarReserva(ModelReserva mR) {
        JOptionPane.showMessageDialog(null, "No, ya esta cancelado");
        throw new ExceptionInInitializerError("No, ya esta cancelado");
    }

    @Override
    public void cancelarReserva(ModelReserva mR) {
        JOptionPane.showMessageDialog(null, "No, ya esta cancelado");
        throw new ExceptionInInitializerError("No, ya esta cancelado");
    }

    @Override
    public void terminarReserva(ModelReserva mR) {
        JOptionPane.showMessageDialog(null, "No, ya esta cancelado");
        throw new ExceptionInInitializerError("No, ya esta cancelado");
    }

}
