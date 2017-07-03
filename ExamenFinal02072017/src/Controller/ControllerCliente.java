package Controller;

import View.ViewCliente;
import Model.ModelCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Harold Salces
 * @version 1.0
 * @created 02-jul.-2017 19:16:37
 */
public class ControllerCliente implements ActionListener {

    public ModelCliente m_ModelCliente;
    public ViewCliente m_ViewCliente;

    public enum accionMVC {
        registrar,
        modificar,
        eliminar,

    }

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (accionMVC.valueOf(e.getActionCommand())) {
            case registrar:
                m_ModelCliente.setCliente(Integer.parseInt(m_ViewCliente.nroCarnetTxt.getText()),
                        m_ViewCliente.nombreTxt.getText(),
                        m_ViewCliente.apellidosTxt.getText(),
                        m_ViewCliente.daireccionTxt.getText(),
                        m_ViewCliente.telefonoTxt.getText());
                m_ModelCliente.registrar();
                m_ViewCliente.actualizate();
                break;
            case modificar:

                m_ModelCliente.setCliente(Integer.parseInt(m_ViewCliente.nroCarnetTxt.getText()),
                        m_ViewCliente.nombreTxt.getText(),
                        m_ViewCliente.apellidosTxt.getText(),
                        m_ViewCliente.daireccionTxt.getText(),
                        m_ViewCliente.telefonoTxt.getText());
                m_ModelCliente.modificar();
                m_ViewCliente.actualizate();
                break;
            case eliminar:

                m_ModelCliente.setCliente(Integer.parseInt(m_ViewCliente.nroCarnetTxt.getText()),
                        "", "", "", "");
                m_ModelCliente.eliminar();
                m_ViewCliente.actualizate();
                break;
        }
    }

    public void initComponent() {

        this.m_ViewCliente.registratBtn.setActionCommand("registrar");
        this.m_ViewCliente.registratBtn.addActionListener(this);
        this.m_ViewCliente.ModificarBtn.setActionCommand("modificar");
        this.m_ViewCliente.ModificarBtn.addActionListener(this);
        this.m_ViewCliente.eliminarBtn.setActionCommand("eliminar");
        this.m_ViewCliente.eliminarBtn.addActionListener(this);
    }

    public ControllerCliente(ModelCliente modeloCliente, ViewCliente vistaCliente) {
        this.m_ModelCliente = modeloCliente;
        this.m_ViewCliente = vistaCliente;
        initComponent();
    }

    public static void main(String[] args) {
        ModelCliente newM = new ModelCliente();
        ViewCliente newV = new ViewCliente();
        ControllerCliente newC = new ControllerCliente(newM, newV);
        newV.setVisible(true);
    }

}
