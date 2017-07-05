package Model;

import javax.swing.JOptionPane;

/**
 * @author Harold Salces
 * @version 1.0
 * @created 03-jul.-2017 12:46:40
 */
public class Terminada extends EstadoReserva {

    public Terminada() {

    }

    @Override
    public void ingresarReserva(ModelReserva mR) {
        JOptionPane.showMessageDialog(null, "NO,Ya termino la reserva");
    }

    @Override
    public void confirmarReserva(ModelReserva mR) {
        JOptionPane.showMessageDialog(null, "NO,Ya termino la reserva");
    }

    @Override
    public void cancelarReserva(ModelReserva mR) {
        JOptionPane.showMessageDialog(null, "NO,Ya termino la reserva");
    }

    /**
     *
     * @param mR
     */
    @Override
    public void terminarReserva(ModelReserva mR) {
        JOptionPane.showMessageDialog(null, "NO,Ya termino la reserva");
    }

}
