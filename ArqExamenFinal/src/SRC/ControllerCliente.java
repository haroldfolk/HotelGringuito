/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SRC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Harold Salces
 */
public class ControllerCliente implements ActionListener,ListSelectionListener {

    public ModelCliente modeloCliente;
    public ViewCliente vistaCliente;

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            System.out.println("tabla clickeada en "+this.vistaCliente.tablaClientes.getSelectedRow());
        }
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
                modeloCliente.setCi(Integer.parseInt(vistaCliente.ciTxt.getText()));
                modeloCliente.setNombre(vistaCliente.nombreTxt.getText());
                modeloCliente.setApellidos(vistaCliente.apellidosTxt.getText());
                modeloCliente.setDireccion(vistaCliente.direccionTxt.getText());
                modeloCliente.setTelefono(Integer.parseInt(vistaCliente.telefonoTxt.getText()));
                modeloCliente.guardar();
                vistaCliente.actualizate();
                break;
            case modificar:
                modeloCliente.setCi(Integer.parseInt(vistaCliente.ciTxt.getText()));
                modeloCliente.setNombre(vistaCliente.nombreTxt.getText());
                modeloCliente.setApellidos(vistaCliente.apellidosTxt.getText());
                modeloCliente.setDireccion(vistaCliente.direccionTxt.getText());
                modeloCliente.setTelefono(Integer.parseInt(vistaCliente.telefonoTxt.getText()));
                modeloCliente.actualizar();
                vistaCliente.actualizate();
                break;
            case eliminar:
                modeloCliente.setCi(Integer.parseInt(vistaCliente.ciTxt.getText()));
                modeloCliente.eliminar();
                vistaCliente.actualizate();
                break;
        }
    }

    public void initComponent() {
        this.vistaCliente.tablaClientes.getSelectionModel().addListSelectionListener(this);
        this.vistaCliente.registrarBtn.setActionCommand("registrar");
        this.vistaCliente.registrarBtn.addActionListener(this);
        this.vistaCliente.modificarBtn.setActionCommand("modificar");
        this.vistaCliente.modificarBtn.addActionListener(this);
        this.vistaCliente.eliminarBtn.setActionCommand("eliminar");
        this.vistaCliente.eliminarBtn.addActionListener(this);
    }

    public ControllerCliente(ModelCliente modeloCliente, ViewCliente vistaCliente) {
        this.modeloCliente = modeloCliente;
        this.vistaCliente = vistaCliente;
        initComponent();
    }

    public static void main(String[] args) {
        ModelCliente newM = new ModelCliente();
        ViewCliente newV = new ViewCliente();
        ControllerCliente newC = new ControllerCliente(newM, newV);
        newV.setVisible(true);
    }

}
