package Controller;

import View.ViewTipo;
import Model.ModelTipo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Harold Salces
 * @version 1.0
 * @created 02-jul.-2017 19:16:39
 */
public class ControllerTipo implements ActionListener {

    public ViewTipo m_ViewTipo;
    public ModelTipo m_ModelTipo;

    public ControllerTipo(ViewTipo v, ModelTipo m) {
        m_ViewTipo = v;
        m_ModelTipo = m;
        initComponent();
    }

    public enum accionMVC {
        registrar,
        modificar,
        eliminar,

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (accionMVC.valueOf(e.getActionCommand())) {
            case registrar:
                m_ModelTipo.setTipo(Integer.parseInt(m_ViewTipo.idtipoTxt.getText()),
                        m_ViewTipo.descripcionTxt.getText());
                m_ModelTipo.registrar();
                m_ViewTipo.actualizate();
                break;
            case modificar:

                m_ModelTipo.setTipo(Integer.parseInt(m_ViewTipo.idtipoTxt.getText()),
                        m_ViewTipo.descripcionTxt.getText());
                m_ModelTipo.modificar();
                m_ViewTipo.actualizate();
                break;
            case eliminar:

                m_ModelTipo.setTipo(Integer.parseInt(m_ViewTipo.idtipoTxt.getText()),
                        m_ViewTipo.descripcionTxt.getText());
                m_ModelTipo.eliminar();
                m_ViewTipo.actualizate();
                break;
        }
    }

    public static void main(String[] args) {
        ModelTipo newM = new ModelTipo();
        ViewTipo newV = new ViewTipo();
        ControllerTipo newC = new ControllerTipo(newV, newM);
        newV.setVisible(true);
    }

    public void initComponent() {

        this.m_ViewTipo.registrarBtn.setActionCommand("registrar");
        this.m_ViewTipo.registrarBtn.addActionListener(this);
        this.m_ViewTipo.modificarBtn.setActionCommand("modificar");
        this.m_ViewTipo.modificarBtn.addActionListener(this);
        this.m_ViewTipo.eliminarBtn.setActionCommand("eliminar");
        this.m_ViewTipo.eliminarBtn.addActionListener(this);
    }
    /**
     *
     * @param e
     */
}
