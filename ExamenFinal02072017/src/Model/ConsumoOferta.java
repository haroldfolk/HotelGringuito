package Model;

import javax.swing.table.DefaultTableModel;




/**
 * @author Harold Salces
 * @version 1.0
 * @created 04-jul.-2017 06:20:03
 */
public class ConsumoOferta implements EstrategiaConsumo {

	public ConsumoOferta(){

	}

	
//Este consumo devuelve el consumo con un descuento del 30% sobre el total
        @Override
	public float calcularConsumo(DefaultTableModel tablaConsumo){
		float total = 0;
        for (int i = 0; i < tablaConsumo.getRowCount(); i++) {
            float monto = Float.parseFloat(tablaConsumo.getValueAt(i, 1).toString());
            
            total = total + monto;
        }
        return  (float) (total*0.7);
	}

}