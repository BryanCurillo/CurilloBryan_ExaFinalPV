/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import view.viewRegistroUsu;
import view.viewPantallaPrincipal;
import model.modelUsuario;

/**
 *
 * @author Bryan
 */
public class controllerRegistroUsu {

    private viewRegistroUsu vistaReg;
    private modelUsuario modelUsu;
    private boolean banvista = false;
    DefaultTableModel estructuraTabla;
    private JFileChooser jfc;

    public controllerRegistroUsu(viewRegistroUsu vistaReg, modelUsuario modelUsu) {
        this.vistaReg = vistaReg;
        this.modelUsu = modelUsu;
        vistaReg.toFront();
        vistaReg.setVisible(true);
    }

    public void inicialControl() {
        vistaReg.getBtnRegistro().addActionListener(l -> registrarActualizar());
        vistaReg.getBtnExaminarFoto().addActionListener(l -> examinarFoto());

    }

    public void examinarFoto() {
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPEG,PNG y JPG", "jpeg", "png", "jpg");
        jfc = new JFileChooser();
        jfc.setFileFilter(filtro);
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int estado = jfc.showOpenDialog(vistaReg);
        if (estado == JFileChooser.APPROVE_OPTION) {
            try {
                Image imagen = ImageIO.read(jfc.getSelectedFile()).getScaledInstance(
                        vistaReg.getLblFoto().getWidth(),
                        vistaReg.getLblFoto().getHeight(),
                        Image.SCALE_DEFAULT);
                Icon icono = new ImageIcon(imagen);
                vistaReg.getLblFoto().setIcon(icono);
                vistaReg.getLblFoto().updateUI();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(vistaReg, "El archivo de imagen esta corrupto", "Ha ocurrido un error", 2);
//                Logger.getLogger(ControllerRegistroEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NullPointerException n) {
                JOptionPane.showMessageDialog(vistaReg, "El archivo de imagen esta corrupto", "Ha ocurrido un error", 2);
            }
        }
    }

    public void abrirRegistro(int op) {
        vistaReg.toFront();
        vistaReg.getLblFoto().setIcon(null);
        String titulo;
//        cargarComboRol();
        if (op == 1) {
//            limpiarCampos();
            titulo = "Crear";
            vistaReg.setName("Registro");
            vistaReg.getBtnRegistro().setText("REGISTRAR");
            vistaReg.setVisible(true);
            this.inicialControl();
//            abrirRegistroEmpleado();
        } else {
//            titulo = "Editar";
//            if (llenarDatos()) {
//                vistaReg.setName("Editar");
//                vistaReg.getBtnAgregar().setText("ACTUALIZAR");
//                vistaReg.setVisible(true);
//                this.inicialControl();
////                abrirRegistroEmpleado();
//            }
        }
    }

    public void registrarActualizar() {
        boolean ban = false;

        validaciones mivalidacion = new validaciones();

//        if (validar()) {
        //ALIMENTO
        String nombre = vistaReg.getTxtNombre().getText(),
                contra = vistaReg.getTxtcontrasena().getText(),
                permiso = vistaReg.getComboPermisos().getSelectedItem().toString();

        int colorAux = vistaReg.getLblFoto().getBackground().hashCode(),
                colorAux2 = 0;

        modelUsuario modelusu = new modelUsuario();
        modelusu.setNombre_Usu(nombre);
        modelusu.setContra_Usu(contra);
        modelusu.setPermiso_Usu(permiso);
        modelusu.setEstado_Usu(true);

        try {
            FileInputStream img = new FileInputStream(jfc.getSelectedFile());
            int largo = (int) jfc.getSelectedFile().length();
            modelusu.setImageFile(img);
            modelusu.setTamano(largo);
            
            
            colorAux2 = vistaReg.getLblFoto().getBackground().hashCode() + 2;

        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(controllerRegistroUsu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (NullPointerException e) {
            colorAux2 = vistaReg.getLblFoto().getBackground().hashCode();
        }

        if (vistaReg.getName().equals("Registro")) {
            int response = JOptionPane.showConfirmDialog(vistaReg, "¿Agregar Animal?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {

                if (colorAux != colorAux2) {
                    System.out.println("registro1");
                    if (modelusu.setFotoUsuario()) {
                        System.out.println("CON FOTO");
                        ban = true;
                    }
                } else {
                    if (modelusu.setUsuario()) {
                        System.out.println("SIN FOTO");
                        ban = true;
                    }
                }

                if (ban) {
                    JOptionPane.showMessageDialog(vistaReg, "Usuario agregado/a correctamente");
                    vistaReg.dispose();
                } else {
                    JOptionPane.showMessageDialog(vistaReg, "No se pudo agregar el Usuario");
                }
            }
        } else {
            //UPDATE
//                int id = Integer.parseInt(vistaRegAni.getTxtidAnimalNoborrar().getText());
//                System.out.println("animal id= " + vistaRegAni.getTxtidAnimalNoborrar().getText());
//                animal.setIdAnimal(id);
//                int response = JOptionPane.showConfirmDialog(vistaRegAni, "¿Seguro que desea actualizar los datos del animal?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//                if (response == JOptionPane.YES_OPTION) {
//                    if (colorAux != colorAux2) {
//                        System.out.println("registro1");
//                        if (animal.updateFotoAnimal()) {
//                            System.out.println("CON FOTO");
//                            ban = true;
//                        }
//                    } else {
//                        if (animal.updateAnimal()) {
//                            System.out.println("SIN FOTO");
//                            ban = true;
//                        }
//                    }
//                    if (ban) {//Grabamos
//                        JOptionPane.showMessageDialog(vistaRegAni, "Animal actualizado correctamente");
//                        vistaRegAni.dispose();
//                    } else {
//                        JOptionPane.showMessageDialog(vistaRegAni, "No se pudo actualizar a los datos del animal");
//                    }
//                }
//
//                if (banvista) {
//                    ControllerVistaAnimal controlAni = new ControllerVistaAnimal(vistaVani, modeloAni);
//                    controlAni.cargarDatos(1);
//                }
////            
//            }
        }
    }
    
    
    public void limpiarDatos(){
        
    }
}
