package Controller;

import Model.ModelCliente;
import Model.ModelPersonal;
import View.ViewCliente;
import View.ViewPersonal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Harold Salces
 * @version 1.0
 * @created 02-jul.-2017 19:16:40
 */
public class ControllerPersonal implements ActionListener {

    public ModelPersonal m_ModelPersonal;
    public ViewPersonal m_ViewPersonal;

    public ControllerPersonal(ModelPersonal modeloPersonal, ViewPersonal vistaPersonal) {
        this.m_ModelPersonal = modeloPersonal;
        this.m_ViewPersonal = vistaPersonal;
        initComponent();
    }

    public static void main(String[] args) {
        ModelPersonal newM = new ModelPersonal();
        ViewPersonal newV = new ViewPersonal();
        ControllerPersonal newC = new ControllerPersonal(newM, newV);
        newV.setVisible(true);
    }

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
                m_ModelPersonal.setPersonal(Integer.parseInt(m_ViewPersonal.nroCarnetTxt.getText()),
                        m_ViewPersonal.nombreTxt.getText(),
                        m_ViewPersonal.apellidosTxt.getText(),
                        m_ViewPersonal.fechaTxt.getText());
                m_ModelPersonal.registrar();
                m_ViewPersonal.actualizate();
                break;
            case modificar:

               m_ModelPersonal.setPersonal(Integer.parseInt(m_ViewPersonal.nroCarnetTxt.getText()),
                        m_ViewPersonal.nombreTxt.getText(),
                        m_ViewPersonal.apellidosTxt.getText(),
                        m_ViewPersonal.fechaTxt.getText());
                m_ModelPersonal.modificar();
                m_ViewPersonal.actualizate();
                break;
            case eliminar:

                m_ModelPersonal.setPersonal(Integer.parseInt(m_ViewPersonal.nroCarnetTxt.getText()),
                        m_ViewPersonal.nombreTxt.getText(),
                        m_ViewPersonal.apellidosTxt.getText(),
                        m_ViewPersonal.fechaTxt.getText());
                m_ModelPersonal.eliminar();
                m_ViewPersonal.actualizate();
                break;
        }
    }
public void initComponent() {

        this.m_ViewPersonal.registrarBtn.setActionCommand("registrar");
        this.m_ViewPersonal.registrarBtn.addActionListener(this);
        this.m_ViewPersonal.modificarBtn.setActionCommand("modificar");
        this.m_ViewPersonal.modificarBtn.addActionListener(this);
        this.m_ViewPersonal.eliminarBtn.setActionCommand("eliminar");
        this.m_ViewPersonal.eliminarBtn.addActionListener(this);
    }
}
