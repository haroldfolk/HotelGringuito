/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ModelReserva;
import View.ViewReserva;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Harold Salces
 */
public class ControllerReserva implements ActionListener {

    public ModelReserva m_ModelReserva;
    public ViewReserva m_ViewReserva;

    public ControllerReserva(ModelReserva modeloReserva, ViewReserva vistaReserva) {
        this.m_ModelReserva = modeloReserva;
        this.m_ViewReserva = vistaReserva;
        initComponent();
    }

    public static void main(String[] args) {
        ModelReserva newM = new ModelReserva();
        ViewReserva newV = new ViewReserva();
        ControllerReserva newC = new ControllerReserva(newM, newV);
        newV.setVisible(true);
    }

    public enum accionMVC {
        ingresar,
        confirmar,
        terminar,
        cancelar

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (accionMVC.valueOf(e.getActionCommand())) {
            case ingresar:
        Date fechaRes = Date.valueOf(m_ViewReserva.fechaReservaTxt.getText());
        int idCLienteSelected = Integer.parseInt(m_ViewReserva.clienteTbl.getValueAt(m_ViewReserva.clienteTbl.getSelectedRow(), 0).toString());
        int idpersonalSelected = Integer.parseInt(m_ViewReserva.personalTbl.getValueAt(m_ViewReserva.personalTbl.getSelectedRow(), 0).toString());
        int idtarifaSelected = Integer.parseInt(m_ViewReserva.tarifaTbl.getValueAt(m_ViewReserva.tarifaTbl.getSelectedRow(), 0).toString());
                m_ModelReserva.setReserva(fechaRes, idCLienteSelected, idpersonalSelected, idtarifaSelected);
                
                m_ModelReserva.ingresarReserva((DefaultTableModel) m_ViewReserva.habitacionTbl.getModel(),m_ViewReserva.habitacionTbl.getSelectedRows());
                
                m_ViewReserva.actualizate();
                break;
            case confirmar:

                m_ModelReserva.confirmarReserva(Integer.parseInt(m_ViewReserva.reservasTbl.getValueAt(m_ViewReserva.reservasTbl.getSelectedRow(), 0).toString()), Date.valueOf(m_ViewReserva.fechaIngresoTxt.getText()));

                m_ViewReserva.actualizate();
                break;
            case terminar:

                m_ModelReserva.terminarReserva(Integer.parseInt(m_ViewReserva.reservasTbl.getValueAt(m_ViewReserva.reservasTbl.getSelectedRow(), 0).toString()), Date.valueOf(m_ViewReserva.fechaSalidaTxt.getText()));

                m_ViewReserva.actualizate();
                break;
            case cancelar:
                m_ModelReserva.cancelarReserva(Integer.parseInt(m_ViewReserva.reservasTbl.getValueAt(m_ViewReserva.reservasTbl.getSelectedRow(), 0).toString()));
                m_ViewReserva.actualizate();
        }
    }

    public void initComponent() {

        this.m_ViewReserva.IngresarReservaBtn.setActionCommand("ingresar");
        this.m_ViewReserva.IngresarReservaBtn.addActionListener(this);
        this.m_ViewReserva.ConfirmarBtn.setActionCommand("confirmar");
        this.m_ViewReserva.ConfirmarBtn.addActionListener(this);
        this.m_ViewReserva.TerminarReservaBtn.setActionCommand("terminar");
        this.m_ViewReserva.TerminarReservaBtn.addActionListener(this);
        this.m_ViewReserva.CancelarReservaBtn.setActionCommand("cancelar");
        this.m_ViewReserva.CancelarReservaBtn.addActionListener(this);

    }
}
