/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import view.viewUsuario1;
import model.modelUsuario;
import javax.swing.table.DefaultTableModel;
import model.modelPGconexion;
import model.usuario;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import view.viewPantallaPrincipal;
import view.viewRegistroUsu;
import view.viewRegistroUsu;

/**
 *
 * @author ALEJO
 */
public class ControllerVistaUsuarios {

    private viewPantallaPrincipal vistaP;
    private viewUsuario1 vistaUsu;
    private controllerPantallaPrincipal controllerpp;
    private modelUsuario modeloUsu;


    int i = 0;

    DefaultTableModel estructuraTabla;

    public ControllerVistaUsuarios() {
    }

    public ControllerVistaUsuarios(viewUsuario1 vistaUsu, modelUsuario modeloUsu) {
        this.vistaUsu = vistaUsu;
        this.modeloUsu = modeloUsu;
    }

    public ControllerVistaUsuarios(viewPantallaPrincipal vistaP, viewUsuario1 vistaUsu, modelUsuario modeloUsu) {
        this.vistaP = vistaP;
        this.vistaUsu = vistaUsu;
        this.modeloUsu = modeloUsu;
        cargarDatos(1);
        vistaUsu.setVisible(true);
    }

    public void inicialControl() {
        vistaUsu.getBtnAgregarCliente().addActionListener(l -> abrirRegistro(1));
//        vistaUsu.getjBtnModificarAnimal().addActionListener(l -> abrirRegistro(2));
//        vistaUsu.getjBtnEliminarAnimal().addActionListener(l -> eliminarAlimento());
//        vistaUsu.getTxtbuscarAnimal().addKeyListener(busquedaIncren);
    }

    public void abrirRegistro(int op) {
        modelUsuario modeloUsu = new modelUsuario();
        viewRegistroUsu vistaRegistroUsu = new viewRegistroUsu();

        if (op == 1) {

            //Agragar vista al desktop pane
            vistaP.getjDesktopPane1().add(vistaRegistroUsu);
            controllerRegistroUsu controladorUsu = new controllerRegistroUsu(vistaRegistroUsu, modeloUsu, vistaUsu);
            controladorUsu.abrirRegistro(1);

        } else {
            controllerRegistroUsu controladorUsu = new controllerRegistroUsu(vistaRegistroUsu, modeloUsu, vistaUsu);
            int fila = vistaUsu.getjTblCliente().getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Seleccione el usuario a modificar");
            } else {
                vistaP.getjDesktopPane1().add(vistaRegistroUsu);
                controladorUsu.abrirRegistro(2);
            }
            cargarDatos(1);
        }
    }

    KeyListener busquedaIncren = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
            cargarDatos(2);
        }
    };

    public void cargarDatos(int opc) {
        vistaUsu.getjTblCliente().setDefaultRenderer(Object.class, new imageTable());
        vistaUsu.getjTblCliente().setRowHeight(50);
        estructuraTabla = (DefaultTableModel) vistaUsu.getjTblCliente().getModel();
        estructuraTabla.setRowCount(0);

        List<usuario> listaUsu;
        if (opc == 1) {
            listaUsu = modeloUsu.getUsuarios("");
        } else {
            String busqueda = vistaUsu.getTxtbuscar().getText().toLowerCase().trim();
            listaUsu = modeloUsu.getUsuarios(busqueda);
        }

//        Holder<Integer> i = new Holder<>(0);
        i = 0;
        listaUsu.stream().sorted((x, y)
                -> x.getNombre_Usu().compareToIgnoreCase(y.getNombre_Usu())).forEach(emp -> {
            estructuraTabla.addRow(new Object[8]);
            vistaUsu.getjTblCliente().setValueAt(emp.getId_Usu(), i, 0);
            vistaUsu.getjTblCliente().setValueAt(emp.getNombre_Usu(), i, 1);
            vistaUsu.getjTblCliente().setValueAt(emp.getPermiso_Usu(), i, 2);

            Image foto = emp.getFoto();
            if (foto != null) {
                foto = foto.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                ImageIcon icono = new ImageIcon(foto);

                DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
                dtcr.setIcon(icono);
                vistaUsu.getjTblCliente().setValueAt(new JLabel(icono), i, 7);
            } else {
                vistaUsu.getjTblCliente().setValueAt(null, i, 7);
            }
            i++;
        });

    }

    public void eliminarAlimento() {
        modelUsuario animal = new modelUsuario();
        int fila = vistaUsu.getjTblCliente().getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione el usuario a eliminar");
        } else {
            int response = JOptionPane.showConfirmDialog(vistaUsu, "¿Esta seguro de eliminar al usuario?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                int id = Integer.parseInt(vistaUsu.getjTblCliente().getValueAt(fila, 0).toString());

                if (animal.deleteUsuario(id)) {//Grabamos
                    JOptionPane.showMessageDialog(vistaUsu, "Usuario eliminado correctamente");
                    cargarDatos(1);
                } else {
                    JOptionPane.showMessageDialog(vistaUsu, "No se pudo eliminar al Usuario");
                }
            }
        }
    }

    private void imprimeReporte() {
        //Instanciamos la conexion proyecto
        modelPGconexion con = new modelPGconexion();

        JasperReport jr;
        try {
           // jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/view/reportes/ReporteAnimales.jasper"));
           jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/view/reportes/ReporteAnimales.jasper"));
            Map<String, Object> parametros = new HashMap<String, Object>();

            parametros.put("titulo", "REPORTE DE ANIMALES");
            parametros.put("busqueda", vistaUsu.getTxtbuscar().getText().toLowerCase());

            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, con.getCon());//llena el reporte con datos.
            JasperViewer jv = new JasperViewer(jp, false);
            
            if (vistaUsu.getjTblCliente().getRowCount() != 0) {
                jv.setVisible(true);

            }
        } catch (JRException ex) {
            java.util.logging.Logger.getLogger(ControllerVistaUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}