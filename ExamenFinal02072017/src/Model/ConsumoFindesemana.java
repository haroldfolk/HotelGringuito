package Model;

import javax.swing.table.DefaultTableModel;

/**
 * @author Harold Salces
 * @version 1.0
 * @created 04-jul.-2017 06:20:05
 */
public class ConsumoFindesemana implements EstrategiaConsumo {

    public ConsumoFindesemana() {

    }

//Este consumo devuelve el consumo con un 20% de descuento en los consumos mayos a 100 Bs

    @Override
    public float calcularConsumo(DefaultTableModel tablaConsumo) {
        float total = 0;
        for (int i = 0; i < tablaConsumo.getRowCount(); i++) {
            float monto = Float.parseFloat(tablaConsumo.getValueAt(i, 1).toString());
            if (monto > 100) {
                monto = (float) (monto * 0.8);
            }
            total = total + monto;
        }
        return total;
    }

}
