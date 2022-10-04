package controller;

import java.awt.Image;
import java.io.FileInputStream;
import java.io.IOException;
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
import view.viewUsuario;
import model.modelUsuario;
import model.usuario;

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
    private viewUsuario vistaUsu;

    public controllerRegistroUsu(viewRegistroUsu vistaReg, modelUsuario modelUsu) {
        this.vistaReg = vistaReg;
        this.modelUsu = modelUsu;
        vistaReg.toFront();
        vistaReg.setVisible(true);
        banvista = false;
    }

    public controllerRegistroUsu(viewRegistroUsu vistaReg, modelUsuario modelUsu, viewUsuario vistaUsu) {
        this.vistaReg = vistaReg;
        this.modelUsu = modelUsu;
        this.vistaUsu = vistaUsu;
        vistaReg.toFront();
        vistaReg.setVisible(true);
        banvista = true;
    }

    public void inicialControl() {
        vistaReg.getBtnRegistro().addActionListener(l -> registrarActualizar());
        vistaReg.getBtnExaminarFoto().addActionListener(l -> examinarFoto());
        vistaReg.getBtnCancelar().addActionListener(l -> vistaReg.dispose());

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
            } catch (NullPointerException n) {
                JOptionPane.showMessageDialog(vistaReg, "El archivo de imagen esta corrupto", "Ha ocurrido un error", 2);
            }
        }
    }

    public void abrirRegistro(int op) {
        vistaReg.toFront();
        vistaReg.getLblFoto().setIcon(null);
        String titulo;
        if (op == 1) {
            limpiarDatos();
            titulo = "Crear";
            vistaReg.setName("Registro");
            vistaReg.getBtnRegistro().setText("REGISTRAR");
            vistaReg.setVisible(true);
            this.inicialControl();
        } else {
            titulo = "Editar";
            if (llenarDatos()) {
                vistaReg.setName("Editar");
                vistaReg.getBtnRegistro().setText("ACTUALIZAR");
                vistaReg.setVisible(true);
                this.inicialControl();
            }
        }
    }

    public void registrarActualizar() {
        boolean ban = false;

        validaciones mivalidacion = new validaciones();

        if (validar()) {
            //ALIMENTO
            String nombre = vistaReg.getTxtNombre().getText().toUpperCase(),
                    contra = vistaReg.getTxtcontrasena().getText(),
                    permiso = vistaReg.getComboPermisos().getSelectedItem().toString().toUpperCase();

            int colorAux = vistaReg.getLblFoto().getBackground().hashCode(),
                    colorAux2 = 0;

            modelUsuario usu = new modelUsuario();
            usu.setNombre_Usu(nombre);
            usu.setContra_Usu(contra);
            usu.setPermiso_Usu(permiso);
            usu.setEstado_Usu(true);

            try {
                FileInputStream img = new FileInputStream(jfc.getSelectedFile());
                int largo = (int) jfc.getSelectedFile().length();
                usu.setImageFile(img);
                usu.setTamano(largo);

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
                        if (usu.setFotoUsuario()) {
                            System.out.println("CON FOTO");
                            ban = true;
                        }
                    } else {
                        if (usu.setUsuario()) {
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
                int id = Integer.parseInt(vistaReg.getTxtidUsu().getText());
                usu.setId_Usu(id);
                int response = JOptionPane.showConfirmDialog(vistaReg, "¿Seguro que desea actualizar los datos del usuario?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    if (colorAux != colorAux2) {
                        System.out.println("registro1");
                        if (usu.updateFotoUsuario()) {
                            System.out.println("CON FOTO");
                            ban = true;
                        }
                    } else {
                        if (usu.updateUsuario()) {
                            System.out.println("SIN FOTO");
                            ban = true;
                        }
                    }
                    if (ban) {//Grabamos
                        JOptionPane.showMessageDialog(vistaReg, "Usuario actualizado correctamente");
                        vistaReg.dispose();
                    } else {
                        JOptionPane.showMessageDialog(vistaReg, "No se pudo actualizar a los datos del usuario");
                    }
                }

            }
            if (banvista) {
                ControllerVistaUsuarios controlAni = new ControllerVistaUsuarios(vistaUsu, modelUsu);
                controlAni.cargarDatos(1);
            }
        }
    }

    public boolean llenarDatos() {
        int fila = vistaUsu.getTblUsuarios().getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vistaUsu, "Seleccione el usuario a modificar");
            return false;
        } else {
            int id = Integer.parseInt(vistaUsu.getTblUsuarios().getValueAt(fila, 0).toString());
            System.out.println("llenar= " + id);
            List<usuario> listap = modelUsu.getUsuarios("");
            listap.stream().forEach(usu -> {
                if (id == usu.getId_Usu()) {
                    vistaReg.getTxtidUsu().setText(String.valueOf(usu.getId_Usu()));
                    vistaReg.getTxtNombre().setText(usu.getNombre_Usu());
                    vistaReg.getTxtcontrasena().setText(usu.getContra_Usu());
                    for (int j = 0; j < vistaReg.getComboPermisos().getItemCount(); j++) {
                        if (vistaReg.getComboPermisos().getItemAt(j).equalsIgnoreCase(usu.getPermiso_Usu())) {
                            vistaReg.getComboPermisos().setSelectedIndex(j);
                        }
                    }
                    Image foto = usu.getFoto();
                    if (foto != null) {
                        foto = foto.getScaledInstance(94, 101, Image.SCALE_SMOOTH);
                        ImageIcon icono = new ImageIcon(foto);
                        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
                        dtcr.setIcon(icono);
                        vistaReg.getLblFoto().setIcon(icono);
                    } else {
                        vistaReg.getLblFoto().setIcon(null);
                    }

                }
            });
            return true;
        }
    }

    public void limpiarDatos() {
        vistaReg.getTxtNombre().setText("");
        vistaReg.getTxtcontrasena().setText("");
        vistaReg.getComboPermisos().setSelectedIndex(0);
    }

    public boolean validar() {
        boolean ban = true;
        validaciones mivalidacion = new validaciones();

        if (vistaReg.getComboPermisos().getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(vistaReg, "SELECCIONE UN PERMISO");
            ban = false;
        }

        if (vistaReg.getTxtNombre().getText().isEmpty()) {
            JOptionPane.showMessageDialog(vistaReg, "INGRESE UN NOMBRE CORTO");
            ban = false;
        }

        if (vistaReg.getTxtcontrasena().getText().isEmpty()) {
            JOptionPane.showMessageDialog(vistaReg, "INGRESE UNA CONTRASEÑA");
            ban = false;
        }

        return ban;
    }
}
