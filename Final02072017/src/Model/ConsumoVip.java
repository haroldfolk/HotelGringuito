package Model;

import javax.swing.table.DefaultTableModel;

/**
 * @author Harold Salces
 * @version 1.0
 * @created 04-jul.-2017 06:19:58
 */
public class ConsumoVip implements EstrategiaConsumo {

    public ConsumoVip() {

    }

    public void finalize() throws Throwable {

    }
//Este consumo devuelve el consumo con un recargo de 10% sobre cada consumo y un recargo de 20% sobre el total si el consumo es mayor a 100Bs

    public float calcularConsumo(DefaultTableModel tablaConsumo) {
        float total = 0;
        for (int i = 0; i < tablaConsumo.getRowCount(); i++) {
            float monto = Float.parseFloat(tablaConsumo.getValueAt(i, 1).toString());

            monto = (float) (monto * 1.1);

            total = total + monto;
        }
        if (total > 100) {
            total = (float) (total *1.2);
        }
        return total;
    }

}
