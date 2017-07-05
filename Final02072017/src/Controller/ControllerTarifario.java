package Controller;

import Model.ModelTarifario;
import View.ViewTarifario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 * @author Harold Salces
 * @version 1.0
 * @created 02-jul.-2017 19:16:41
 */
public class ControllerTarifario implements ActionListener, ListSelectionListener {

    public ViewTarifario m_ViewTarifario;
    public ModelTarifario m_ModelTarifario;

    public ControllerTarifario(ViewTarifario v, ModelTarifario m) {
        m_ViewTarifario = v;
        m_ModelTarifario = m;
        initComponent();
    }

    /**
     *
     * @param e
     */
    public static void main(String[] args) {
        ModelTarifario newM = new Model.ModelTarifario();
        ViewTarifario newV = new ViewTarifario();
        ControllerTarifario newC = new ControllerTarifario(newV, newM);
        newV.setVisible(true);
    }

    public void initComponent() {
 this.m_ViewTarifario.tarifasTbl.getSelectionModel().addListSelectionListener(this);
        this.m_ViewTarifario.registrarBtn.setActionCommand("registrar");
        this.m_ViewTarifario.registrarBtn.addActionListener(this);
        this.m_ViewTarifario.cargarpreciosBtn.setActionCommand("cambiarPrecios");
        this.m_ViewTarifario.cargarpreciosBtn.addActionListener(this);
    }

    public enum accionMVC {
        registrar,
        cambiarPrecios

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (accionMVC.valueOf(e.getActionCommand())) {
            case registrar:
                m_ModelTarifario.setTarifario(m_ViewTarifario.nombreTxt.getText());
                m_ModelTarifario.registrar();
                m_ViewTarifario.actualizate();
//                JOptionPane.showMessageDialog(null, "Se va a registrar uno nuevo");
                break;
            case cambiarPrecios:
                m_ModelTarifario.setPrecios((DefaultTableModel) m_ViewTarifario.preciosTbl.getModel());
                
//                JOptionPane.showMessageDialog(null, "Se va a cambiar los precios");
                break;
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
             int selectedRow = this.m_ViewTarifario.tarifasTbl.getSelectedRow();
            m_ViewTarifario.cargarPrecios(Integer.parseInt((String) this.m_ViewTarifario.tarifasTbl.getValueAt(selectedRow, 0)));
//            JOptionPane.showMessageDialog(null, "Se va a cargar precios de la fila: " + this.m_ViewTarifario.tarifasTbl.getSelectedRow());
//            System.out.println("tabla clickeada en "+this.m_ViewTarifario.tarifasTbl.getSelectedRow());
        }
    }
}
