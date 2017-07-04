package Controller;

import Model.ConsumoFindesemana;
import Model.ConsumoNormal;
import Model.ConsumoOferta;
import Model.ConsumoVip;
import Model.ModelConsumo;
import View.ViewConsumo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * @author Harold Salces
 * @version 1.0
 * @created 04-jul.-2017 06:20:10
 */
public class ControllerConsumo implements ActionListener, ListSelectionListener {

    public ViewConsumo m_ViewConsumo;
    public ModelConsumo m_ModelConsumo;

    public ControllerConsumo(ViewConsumo v, ModelConsumo m) {
        this.m_ModelConsumo = m;
        this.m_ViewConsumo = v;
        initComponent();
    }

    public enum accionMVC {
        registrar,
        consultar

    }

    public void initComponent() {
        this.m_ViewConsumo.ReservasTbl.getSelectionModel().addListSelectionListener(this);
        this.m_ViewConsumo.registrarConsumoBtn.setActionCommand("registrar");
        this.m_ViewConsumo.registrarConsumoBtn.addActionListener(this);
        this.m_ViewConsumo.consultarBtn.setActionCommand("consultar");
        this.m_ViewConsumo.consultarBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (accionMVC.valueOf(e.getActionCommand())) {
            case registrar:
                float monto = Float.parseFloat(m_ViewConsumo.montoTxt.getText());
                String concepto = m_ViewConsumo.conceptoTxt.getText();
                int idR = Integer.parseInt((String) m_ViewConsumo.ReservasTbl.getValueAt(m_ViewConsumo.ReservasTbl.getSelectedRow(), 0));
                m_ModelConsumo.setConsumo(monto, concepto, idR);
                m_ModelConsumo.registrarConsumo();
//                m_ViewConsumo.actualizate();
                m_ViewConsumo.cargarConsumos(idR);
                break;
            case consultar:
                int idRes = Integer.parseInt((String) m_ViewConsumo.ReservasTbl.getValueAt(m_ViewConsumo.ReservasTbl.getSelectedRow(), 0));

                if (this.m_ViewConsumo.radioFinDeSemanaRBtn.isSelected()) {
                    m_ViewConsumo.cargarConsultaConsumo(idRes, new ConsumoFindesemana());
                } else if (this.m_ViewConsumo.radioNormalRBtn.isSelected()) {
                    m_ViewConsumo.cargarConsultaConsumo(idRes, new ConsumoNormal());
                } else if (this.m_ViewConsumo.radioOfertaRBtn.isSelected()) {
                    m_ViewConsumo.cargarConsultaConsumo(idRes, new ConsumoOferta());
                } else if (this.m_ViewConsumo.radioVipRBtn.isSelected()) {
                    m_ViewConsumo.cargarConsultaConsumo(idRes, new ConsumoVip());
                }
                break;
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            int selectedRow = this.m_ViewConsumo.ReservasTbl.getSelectedRow();
            m_ViewConsumo.cargarConsumos(Integer.parseInt((String) this.m_ViewConsumo.ReservasTbl.getValueAt(selectedRow, 0)));
//            JOptionPane.showMessageDialog(null, "Se va a cargar precios de la fila: " + this.m_ViewTarifario.tarifasTbl.getSelectedRow());
//            System.out.println("tabla clickeada en "+this.m_ViewTarifario.tarifasTbl.getSelectedRow());
        }
    }

    public static void main(String[] args) {
        ModelConsumo newM = new ModelConsumo();
        ViewConsumo newV = new ViewConsumo();
        ControllerConsumo newC = new ControllerConsumo(newV, newM);
        newV.setVisible(true);
    }

}
