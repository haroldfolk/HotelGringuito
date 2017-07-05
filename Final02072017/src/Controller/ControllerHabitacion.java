package Controller;

import Model.ModelHabitacion;
import Model.ModelTipo;
import View.ViewHabitacion;
import View.ViewTipo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Harold Salces
 * @version 1.0
 * @created 02-jul.-2017 19:16:35
 */
public class ControllerHabitacion implements ActionListener {

    public ViewHabitacion m_ViewHabitacion;
    public ModelHabitacion m_ModelHabitacion;

    public ControllerHabitacion(ViewHabitacion v, ModelHabitacion m) {
        this.m_ModelHabitacion = m;
        this.m_ViewHabitacion = v;
        initComponent();
    }

    public void finalize() throws Throwable {

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
//                System.out.println("fdaada");
                m_ModelHabitacion.setHabitacion(0, m_ViewHabitacion.descripcionTxt.getText(),
                        m_ViewHabitacion.ubicacionTxt.getText(), 
                        Integer.parseInt(m_ViewHabitacion.tipoCmb.getSelectedItem().toString()));
                m_ModelHabitacion.registrar();
                m_ViewHabitacion.actualizate();
                break;
            case modificar:
                m_ModelHabitacion.setHabitacion(Integer.parseInt(m_ViewHabitacion.idHabTxt.getText()), m_ViewHabitacion.descripcionTxt.getText(),
                        m_ViewHabitacion.ubicacionTxt.getText(), 
                        Integer.parseInt(m_ViewHabitacion.tipoCmb.getSelectedItem().toString()));
                m_ModelHabitacion.modificar();
                m_ViewHabitacion.actualizate();

                break;
            case eliminar:
                m_ModelHabitacion.setHabitacion(Integer.parseInt(m_ViewHabitacion.idHabTxt.getText()), m_ViewHabitacion.descripcionTxt.getText(),
                        m_ViewHabitacion.ubicacionTxt.getText(), 
                        Integer.parseInt(m_ViewHabitacion.tipoCmb.getSelectedItem().toString()));
                m_ModelHabitacion.eliminar();
                m_ViewHabitacion.actualizate();


                break;

        }
    }

    public static void main(String[] args) {
        ModelHabitacion newM = new ModelHabitacion();
        ViewHabitacion newV = new ViewHabitacion();
        ControllerHabitacion newC = new ControllerHabitacion(newV, newM);
        newV.setVisible(true);
    }

    public void initComponent() {

        this.m_ViewHabitacion.registrarBtn.setActionCommand("registrar");
        this.m_ViewHabitacion.registrarBtn.addActionListener(this);
        this.m_ViewHabitacion.modificarBtn.setActionCommand("modificar");
        this.m_ViewHabitacion.modificarBtn.addActionListener(this);
        this.m_ViewHabitacion.eliminarBtn.setActionCommand("eliminar");
        this.m_ViewHabitacion.eliminarBtn.addActionListener(this);
    }
}
