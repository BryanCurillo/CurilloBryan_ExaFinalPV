
package controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class validaciones {

    public boolean validarMayorEdad(Date fechanacim) {
        boolean ban = false;
        LocalDate fechaHoy = LocalDate.now();//fecha actual

        Date date = fechanacim;//fecha naciemiento

        Calendar calendar = Calendar.getInstance();//creamos una intancia calendar
        calendar.setTime(date);//asignamos nuestra fecha
        int anio = calendar.get(Calendar.YEAR),
                mes = calendar.get(Calendar.MONTH) + 1,
                dia = calendar.get(Calendar.DAY_OF_MONTH);
        LocalDate fechaNacimiento = LocalDate.of(anio, mes, dia); // transformamos a un LocalDate

        Period periodo = Period.between(fechaNacimiento, fechaHoy);// Calculamos la edad

        if (periodo.getYears() >= 18) {
            ban = true;
        }
        return ban;
    }

    public double validarDouble(String decimal) {
        double num = 0;
        decimal = decimal.replaceAll("\\s", "");
        decimal = decimal.replace(",", ".");
        if (!decimal.isEmpty()) {
            if (decimal.matches("([0-9]+\\.*)*{1,}")) {
                num = Double.parseDouble(decimal);
                if (num <= 0) {
                    num = 0;
                }
            }
        }
        return num;
    }

    public boolean validarLetNum(String cadena) {
        boolean validar = cadena.matches("^[a-zA-Z]*[\\d]*$");

        return validar;
    }

//    public boolean validarFoto(String cadena) {
//        boolean validar = cadena.matches("([^\\ s] + (\\. (? i) (jpe? g | png | gif | bmp)) $)");
//
//        return validar;
//    }

    public boolean validarCedula(String cedula) {
        boolean val = false;
//        boolean val = true;
        //Divide la cadena en los 10 numeros
        //Integer.parseInt sirve para transformar una cadena a entero. 
        //subString es un metodo de string(Desde, hasta)
        if (cedula.matches("\\d{10}")) {
            int d1 = Integer.parseInt(cedula.substring(0, 1));
            int d2 = Integer.parseInt(cedula.substring(1, 2));
            int d3 = Integer.parseInt(cedula.substring(2, 3));
            int d4 = Integer.parseInt(cedula.substring(3, 4));
            int d5 = Integer.parseInt(cedula.substring(4, 5));
            int d6 = Integer.parseInt(cedula.substring(5, 6));
            int d7 = Integer.parseInt(cedula.substring(6, 7));
            int d8 = Integer.parseInt(cedula.substring(7, 8));
            int d9 = Integer.parseInt(cedula.substring(8, 9));
            int d10 = Integer.parseInt(cedula.substring(9));

            //Multiplica todas la posciones impares * 2 y las posiciones pares se multiplica 1
            d1 = d1 * 2;
            if (d1 > 9) {
                d1 = d1 - 9;
            }

            d3 = d3 * 2;
            if (d3 > 9) {
                d3 = d3 - 9;
            }

            d5 = d5 * 2;
            if (d5 > 9) {
                d5 = d5 - 9;
            }

            d7 = d7 * 2;
            if (d7 > 9) {
                d7 = d7 - 9;
            }

            d9 = d9 * 2;
            if (d9 > 9) {
                d9 = d9 - 9;
            }

            // SUMA TODOS LOS  NUMEROS PARES E IMPARES
            int sumpar = d2 + d4 + d6 + d8;
            int sumimp = d1 + d3 + d5 + d7 + d9;
            int total = sumpar + sumimp;

            //DIVIDO MI DECENA SUPERIRO PARA 10 Y SI EL RESULTADO ES DIFERENTE DE 0 SUMA 1
            double decenasuperior = total;
            while (decenasuperior % 10 != 0) {
                decenasuperior = decenasuperior + 1;
            }

            if ((decenasuperior - total) == d10) {
                val = true;
            }
        }

        return val;
    }

//    public boolean validar_nombre_apellido(String aux) {
//        return aux.matches("^[a-zA-Z]{3,20}");
//    }
    public boolean validarCorreo(String mail) {
        boolean val = false;
        // Patrón para validar el email
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{1,30})$");

        // El email a validar
        Matcher mather = pattern.matcher(mail);
        val = mather.find();

        return val;
    }

    public boolean validarDireccion(String direccion) {
        direccion = direccion.trim();//trim()
        boolean validar = direccion.matches("([\\w\\s]+\\-*+\\#*+\\.*)*");
        return validar;
    }

    public boolean validarNombApeEspacios(String cadena) {
        cadena = cadena.trim();//trim()
        boolean validar = cadena.matches("[A-Za-z\\s]*");
        return validar;
    }

    public boolean validarTelefono(String telefono) {
        boolean validar = false;
        if (telefono.matches("[0-9]{10}")) {
            validar = true;
        }
        return validar;
    }

    public boolean validarContrasena(String clave) {
        boolean validar = false;
//        String expreg="(\"^[A-Z]{1}[\\\\d]{3}[a-z]{2}[^A-ZA-Z0-9]{1}$\")";
        String expreg = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%.!%-_^*&+=()])(?=\\S+$).{5,10}$";//MINIMO 1 mayus y 1 minus , 1 caract especial, minimo 8 y max 20
        //min 1 letra mayus | min 1 letra minus | min 1 caract especial | min 1 numero | minimo 5 caracteres max 20
        validar = clave.matches(expreg);
        return validar;
    }

    public boolean validarUsuario(String usuario) {
        boolean validar = usuario.matches("^[a-zA-Z]{3,10}[\\d]*$");

        return validar;
    }

//    public boolean validarLogin(String usuario, String contrasena) {
////        System.out.println(usuario);
////        System.out.println(contrasena);
//        boolean ban = false;
//        modelLogin miLogin = new modelLogin();
//        if (usuario.isEmpty()) {
//            JOptionPane.showMessageDialog(null, "Ingrese el usuario");
//        } else {
//            if (miLogin.comprobarUsuario(usuario)) {
//                if (contrasena.isEmpty()) {
//                    JOptionPane.showMessageDialog(null, "Ingrese la contraseñá");
//                } else {
//                    if (miLogin.comprobarLogin(usuario, contrasena)) {
//                        ban = true;
//                    } else {
//                        JOptionPane.showMessageDialog(null, "Contraseñá incorrecta");
//                    }
//                }
//
//            } else {
//                JOptionPane.showMessageDialog(null, "Usuario incorrecto");
//            }
//        }
//
//        return ban;
//    }
}//final de clase



////////CONEXION
//   String cadenaConexion = "jdbc:postgresql://localhost:5432/ZOO";
//    String pgUsuario = "postgres";
//    String pgPassword = "Bryan.2002";
//    Connection con;
//
//    public modelPGconexion() {
//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(modelPGconexion.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        try {
//            con = DriverManager.getConnection(cadenaConexion, pgUsuario, pgPassword);
//        } catch (SQLException ex) {
//            Logger.getLogger(modelPGconexion.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public ResultSet consulta(String sql) {
//        try {
//            Statement st = con.createStatement();
//            return st.executeQuery(sql);
//
//        } catch (SQLException ex) {
//            Logger.getLogger(modelPGconexion.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//
//        }
//    }
//
//    public boolean accion(String sql) {
//        //INSERT-UPDATE-DELETE
//
//        boolean correcto;
//
//        try {
//            Statement at = con.createStatement();
//            at.execute(sql);
//            at.close();//Cierro la conexion
//            correcto = true;
//
//        } catch (Exception e) {
//            Logger.getLogger(modelPGconexion.class.getName()).log(Level.SEVERE, null, e);
//            correcto = false;
//        }
//        return correcto;
//    }
//
//    public Connection getCon() {
//        return con;
//    }
//
//    public void setCon(Connection con) {
//        this.con = con;
//    }



//    public void examinarFoto() {
//        FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPEG,PNG y JPG", "jpeg","png","jpg");
//        jfc = new JFileChooser();
//        jfc.setFileFilter(filtro);
//        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
//        int estado = jfc.showOpenDialog(vistaRegAni);
//        if (estado == JFileChooser.APPROVE_OPTION) {
//            try {
//                Image imagen = ImageIO.read(jfc.getSelectedFile()).getScaledInstance(
//                        vistaRegAni.getLblFotoAnimal().getWidth(),
//                        vistaRegAni.getLblFotoAnimal().getHeight(),
//                        Image.SCALE_DEFAULT);
//                Icon icono = new ImageIcon(imagen);
//                vistaRegAni.getLblFotoAnimal().setIcon(icono);
//                vistaRegAni.getLblFotoAnimal().updateUI();
//            } catch (IOException ex) {
//                JOptionPane.showMessageDialog(vistaP, "El archivo de imagen esta corrupto","Ha ocurrido un error",2);
//                Logger.getLogger(ControllerRegistroEmpleado.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (NullPointerException n) {
//                JOptionPane.showMessageDialog(vistaP, "El archivo de imagen esta corrupto","Ha ocurrido un error",2);
//            }
//        }
//    }
//






////////////////////////////////////
//////////IMAGE TABLE 
//public class imageTable extends DefaultTableCellRenderer {
//
//    @Override
//    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//        if (value instanceof JLabel) {
//            JLabel lbl = (JLabel) value;
//            return lbl;
//        }
//
//        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
//    }
//}


/////CONTROL DE ACCESO

//    public void abrirRegistro(int op) {
//        String titulo;
//        vrc.toFront();
//        if (op == 1) {
//            limpiarCampos();
//            titulo = "Crear";
//            vrc.setName("Registro");
//            vrc.getBtregistrar().setText("REGISTRAR");
//            vrc.setVisible(true);
//            this.iniciarControl();
//        } else {
//            titulo = "Editar";
//            System.out.println(titulo);
//            if (llenarDatos()) {
//                vrc.setName("Editar");
//                vrc.getTxtcedula().setEditable(false);
//                vrc.getBtregistrar().setText("ACTUALIZAR");
//                vrc.setVisible(true);
//                this.iniciarControl();
//            }
//        }
//    }

////////////REGISTRO
//    public void registrarActualizar() {
//        boolean ban = false;
//
//        validaciones mivalidacion = new validaciones();
//
//        if (validar()) {
//            String nombre = vistaRegAni.getTxtnombreanimal().getText(),
//                    genero = "",
//                    especie = vistaRegAni.getComboEspecie().getSelectedItem().toString();
//            if (vistaRegAni.getBtnmacho().isSelected()) {
//                genero = "Macho";
//            } else {
//                if (vistaRegAni.getBtnhembra().isSelected()) {
//                    genero = "Hembra";
//                } else {
//                    JOptionPane.showMessageDialog(vistaRegAni, "Seleccione el genero del animal");
//                }
//            }
//
//            if (vistaRegAni.getComboEspecie().getSelectedIndex() == vistaRegAni.getComboEspecie().getItemCount() - 1) {
//                especie = vistaRegAni.getTxtOtraEspecie().getText();
//            } else {
//                especie = vistaRegAni.getComboEspecie().getSelectedItem().toString();
//            }

//            Date fechaRegistro = java.sql.Date.valueOf(LocalDate.now());
//
//            Date fechaNacimiento = vistaRegAni.getCalendarNacimiento().getDate(); //vista es la interfaz, jDate el JDatechooser
//            long d = fechaNacimiento.getTime(); //guardamos en un long el tiempo
//            java.sql.Date fechanacimientoSQL = new java.sql.Date(d);// parseamos al formato del sql  
//
//            int idCuidador = Integer.parseInt(vistaRegAni.getTxtidCuidadorNoborrar().getText()),
//                    idHabitat = Integer.parseInt(vistaRegAni.getTxtidHabitatNoborrar().getText());
//            int colorAux = vistaRegAni.getLblFotoAnimal().getBackground().hashCode(),
//                    colorAux2 = 0;
//
//            ModelAnimal animal = new ModelAnimal();
//            animal.setNombreAnimal(nombre);
//            animal.setGeneroAnimal(genero);

//            try {
//                FileInputStream img = new FileInputStream(jfc.getSelectedFile());
//                int largo = (int) jfc.getSelectedFile().length();
//                animal.setImageFile(img);
//                animal.setTamano(largo);
//                colorAux2 = vistaRegAni.getLblFotoAnimal().getBackground().hashCode() + 2;
//
//            } catch (IOException ex) {
//                java.util.logging.Logger.getLogger(ControllerRegistroEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//            } catch (NullPointerException e) {
//                colorAux2 = vistaRegAni.getLblFotoAnimal().getBackground().hashCode();
//            }
//
//            if (vistaRegAni.getName().equals("Registro")) {
//                int response = JOptionPane.showConfirmDialog(vistaRegAni, "¿Agregar Animal?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//                if (response == JOptionPane.YES_OPTION) {
//
//                    if (colorAux != colorAux2) {
//                        System.out.println("registro1");
//                        if (animal.setFotoAnimal()) {
//                            System.out.println("CON FOTO");
//                            ban = true;
//                        }
//                    } else {
//                        if (animal.setAnimal()) {
//                            System.out.println("SIN FOTO");
//                            ban = true;
//                        }
//                    }
//
////                    if (alimento.comprobarDuplicado(cedula)) {
//                    if (ban) {
//                        JOptionPane.showMessageDialog(vistaRegAni, "Animal agregado/a correctamente");
//                        vistaRegAni.dispose();
//                    } else {
//                        JOptionPane.showMessageDialog(vistaRegAni, "No se pudo agregar el Animal");
//                    }
//                }
//            } else {
//                //UPDATE
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
//        }
//    }

/////////LLENAR FATOS
//  public boolean llenarDatos() {
//        int fila = vvc.getjTblCliente().getSelectedRow();
//        if (fila == -1) {
//            JOptionPane.showMessageDialog(vvc, "Seleccione un cliente a modificar");
//            return false;
//        } else {
//            int id = Integer.parseInt(vvc.getjTblCliente().getValueAt(fila, 0).toString());
//            List<Cliente> listap = mc.getClientes();
//            listap.stream().forEach(cli -> {
//                if (id == cli.getCli_id()) {
//                    vrc.getTxtcedula().setText(cli.getCli_cedula());
//                    vrc.getTxtnombre().setText(cli.getNombre());

//                }
//            });
//            return true;
//        }
//    }

///VALIDACIONES
// public boolean validar() {
//        boolean ban = true;
//        validaciones mivalidacion = new validaciones();
//        //DNI
//        if (!vrc.getTxtcedula().getText().isEmpty()) {
//            if (!mivalidacion.validarCedula(vrc.getTxtcedula().getText())) {
//                JOptionPane.showMessageDialog(vrc, "Cedula invalida");
//                ban = false;
//            }
//        } else {
//            JOptionPane.showMessageDialog(vrc, "Ingrese la cedula");
//            ban = false;
//        }
//      
//        return ban;
//    }


///////////////////////VISTA    
//DefaultTableModel estructuraTabla;
//    SimpleDateFormat formatofecha = new SimpleDateFormat("dd-MM-yyyy");

///

//        public void abrirRegistro(int op) {
//        ModelAnimal modeloAni = new ModelAnimal();
//        viewRegistroAnimal vistaRegistroAni = new viewRegistroAnimal();
//        modelCuidador modeloCui = new modelCuidador();
//        ModelHabitad modeloHab = new ModelHabitad();
//        if (op == 1) {
//
//            //Agragar vista al desktop pane
//            vistaP.getjDPprincipal().add(vistaRegistroAni);
//            ControllerRegistrarAnimal controladorAni = new ControllerRegistrarAnimal(modeloAni, modeloHab, modeloCui, vistaRegistroAni, vistaAni);
//            controladorAni.abrirRegistro(1);
//
//        } else {
//            ControllerRegistrarAnimal controladorAni = new ControllerRegistrarAnimal(modeloAni, modeloHab, modeloCui, vistaRegistroAni, vistaAni);
//            int fila = vistaAni.getjTblAnimal().getSelectedRow();
//            if (fila == -1) {
//                JOptionPane.showMessageDialog(null, "Seleccione el animal a modificar");
//            } else {
//                vistaP.getjDPprincipal().add(vistaRegistroAni);
//                controladorAni.abrirRegistro(2);
//            }
//            cargarDatos(1);
//        }
//    }

//KeyListener busquedaIncren = new KeyListener()

//////CARGARDATOS
//   public void cargarDatos(int opc) {
//        vistaAni.getjTblAnimal().setDefaultRenderer(Object.class, new imageTable());
//        vistaAni.getjTblAnimal().setRowHeight(50);
//        estructuraTabla = (DefaultTableModel) vistaAni.getjTblAnimal().getModel();
//        estructuraTabla.setRowCount(0);
//
//        List<Animales> listaAli;
//        if (opc == 1) {
//            listaAli = modeloAni.getAnimal();
//        } else {
//            String busqueda = vistaAni.getTxtbuscarAnimal().getText().toLowerCase().trim();
//            listaAli = modeloAni.busquedaIncremental(busqueda);
//        }
//
////        Holder<Integer> i = new Holder<>(0);
//        i = 0;
//        listaAli.stream().sorted((x, y)
//                -> x.getEspecieAnimal().compareToIgnoreCase(y.getEspecieAnimal())).forEach(emp -> {
//            estructuraTabla.addRow(new Object[8]);
//            vistaAni.getjTblAnimal().setValueAt(emp.getIdAnimal(), i, 0);
//            vistaAni.getjTblAnimal().setValueAt(emp.getNombreAnimal(), i, 1);
//            vistaAni.getjTblAnimal().setValueAt(emp.getGeneroAnimal(), i, 2);
//            vistaAni.getjTblAnimal().setValueAt(emp.getEspecieAnimal(), i, 3);
//            vistaAni.getjTblAnimal().setValueAt(formatofecha.format(emp.getFecha_nacimientoAnimal()), i, 4);
//            vistaAni.getjTblAnimal().setValueAt(emp.getTipoHabitat(), i, 5);
//            vistaAni.getjTblAnimal().setValueAt(emp.getNombreCuidador(), i, 6);
//            Image foto = emp.getFoto();
//            if (foto != null) {
//                foto = foto.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
//                ImageIcon icono = new ImageIcon(foto);
//
//                DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
//                dtcr.setIcon(icono);
//                vistaAni.getjTblAnimal().setValueAt(new JLabel(icono), i, 7);
//            } else {
//                vistaAni.getjTblAnimal().setValueAt(null, i, 7);
//            }
//            i++;
//        });
//
//    }



//    public void eliminarAlimento() {
//        ModelAnimal animal = new ModelAnimal();
//        int fila = vistaAni.getjTblAnimal().getSelectedRow();
//        if (fila == -1) {
//            JOptionPane.showMessageDialog(null, "Seleccione el alimento a eliminar");
//        } else {
//            int response = JOptionPane.showConfirmDialog(vistaAni, "¿Esta seguro de eliminar el alimento?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//            if (response == JOptionPane.YES_OPTION) {
//                int id = Integer.parseInt(vistaAni.getjTblAnimal().getValueAt(fila, 0).toString());
//
//                if (animal.deleteAnimal(id)) {//Grabamos
//                    JOptionPane.showMessageDialog(vistaAni, "Alimento eliminado correctamente");
//                    cargarDatos(1);
//                } else {
//                    JOptionPane.showMessageDialog(vistaAni, "No se pudo eliminar el alimento");
//                }
//            }
//        }
//    }




//    private void imprimeReporte() {
//        //Instanciamos la conexion proyecto
//        modelPGconexion con = new modelPGconexion();
//
//        JasperReport jr;
//        try {
//           // jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/view/reportes/ReporteAnimales.jasper"));
//           jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/view/reportesH/ReporteAnimales.jasper"));
//            Map<String, Object> parametros = new HashMap<String, Object>();
//
//            parametros.put("titulo", "REPORTE DE ANIMALES");
//            parametros.put("busqueda", vistaAni.getTxtbuscarAnimal().getText().toLowerCase());
//
//            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, con.getCon());//llena el reporte con datos.
//            JasperViewer jv = new JasperViewer(jp, false);
//            if (vistaAni.getjTblAnimal().getRowCount() != 0) {
//                jv.setVisible(true);
//
//            }
//        } catch (JRException ex) {
//            java.util.logging.Logger.getLogger(ControllerVistaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//    }


//
//
//    public void registroDieta() {
//        //Instancio las clases del modelo y la vista        
//        ModelAlimento modelAli = new ModelAlimento();
//        ModelAnimal modelAni = new ModelAnimal();
//        viewRegistroDieta vistaRegDieta = new viewRegistroDieta();
//        ModelDieta modeloDie = new ModelDieta();
//
//        //Agragar vista al desktop pane
//        vistaRegDieta.setName("Registro");
//        vista.getjDPprincipal().add(vistaRegDieta);
//        ControllerRegistrarDieta controReglAli = new ControllerRegistrarDieta(vistaRegDieta, modeloDie, modelAli, modelAni);
//        controReglAli.iniciarControles();
//    }
//
//    public void vistaDieta() {
//        ModelDieta modeloDie = new ModelDieta();
//        viewVistaDieta vistaDie = new viewVistaDieta();
//
//        //Agragar vista al desktop pane                
//        vista.getjDPprincipal().add(vistaDie);
//        ControllerVistaDieta controllerDie = new ControllerVistaDieta(vista, vistaDie, modeloDie);
//        controllerDie.inicialControl();
//    }






//////////////MODEL
//    private Image getImagen(byte[] bytes) throws IOException {
//        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
//        Iterator it = ImageIO.getImageReadersByFormatName("jpeg");
//        ImageReader imageReader = (ImageReader) it.next();
//        Object source = bais;
//        ImageInputStream fis = ImageIO.createImageInputStream(source);
//        imageReader.setInput(fis, true);
//        ImageReadParam param = imageReader.getDefaultReadParam();
//        param.setSourceSubsampling(1, 1, 0, 0);
//        return imageReader.read(0, param);
//    }



//    public boolean setFotoAnimal() {
//        String sql;
//        sql = "INSERT INTO public.animal(ani_nombre, ani_genero, ani_especie, ani_foto, ani_fechaingreso, ani_fechanacimiento, ani_estado, ani_idhabitad, ani_idcuidador)";
//        sql += "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
//        try {
//
//            PreparedStatement ps = mpgc.con.prepareStatement(sql);
//            ps.setString(1, getNombreAnimal());
//            ps.setString(2, getGeneroAnimal());
//            ps.setString(3, getEspecieAnimal());
//            ps.setBinaryStream(4, getImageFile(), getTamano());
//            ps.setDate(5, (java.sql.Date) getFecha_ingresoAnimal());
//            ps.setDate(6, (java.sql.Date) getFecha_nacimientoAnimal());
//            ps.setBoolean(7, isEstadoAnimal());
//            ps.setInt(8, getIdhabitadAnimal());
//            ps.setInt(9, getIdcuidadorAnimal());
//
//            ps.executeUpdate();
//            return true;
//        } catch (SQLException ex) {
//            Logger.getLogger(modelEmpleado.class.getName()).log(Level.SEVERE, null, ex);
//            return false;
//        }
//    }


//    public boolean updateAnimal() {
//        String sql;
//        sql = "UPDATE public.animal SET ani_nombre='" + getNombreAnimal() + "', ani_genero='" + getGeneroAnimal() + "', ani_especie='" + getEspecieAnimal() + "', "
//                + "ani_fechaingreso='" + getFecha_ingresoAnimal() + "', ani_fechanacimiento='" + getFecha_nacimientoAnimal() + "', "
//                + "ani_idhabitad=" + getIdhabitadAnimal() + ", ani_idcuidador=" + getIdcuidadorAnimal()
//                + "where ani_id=" + getIdAnimal();
//        return mpgc.accion(sql);
//    }


//    public boolean deleteAnimal(int id) {
//        String sql;
//        sql = "UPDATE public.animal SET ani_estado=false "
//                + "WHERE ani_id=" + id;
//        return mpgc.accion(sql);
//    }

//
//public List<Animales> busquedaIncremental(String busqueda) {
//        List<Animales> listaAnimales = new ArrayList<>();
//
//        String sql = "select a.ani_id, a.ani_nombre, a.ani_genero, a.ani_especie, a.ani_foto, "//1-6
//                + "from public.animal a join public.habitad h on (a.ani_idhabitad=h.hab_id)  "
//                + "where a.ani_estado=true  "
//                + "  and (lower(a.ani_nombre) like '%" + busqueda + "%' "
//                + "  or lower(a.ani_genero) like '%" + busqueda + "%' "
//                + "  OR to_char(a.ani_fechanacimiento,'DD-MM-YYYY') LIKE  '%" + busqueda + "%'  )";
//        ResultSet rs = mpgc.consulta(sql);
//        byte[] bytea;
//        try {
//            while (rs.next()) {
//                Animales animal = new Animales();
//                animal.setIdAnimal(rs.getInt(1));
//                animal.setNombreAnimal(rs.getString(2));

//
//                bytea = rs.getBytes(5);
//                if (bytea != null) {
//                    animal.setFoto(getImagen(bytea));
//                }
//
//                animal.setFecha_ingresoAnimal(rs.getDate(6));
//                animal.setFecha_nacimientoAnimal(rs.getDate(7));
//                animal.setEstadoAnimal(rs.getBoolean(8));

//
//                listaAnimales.add(animal);
//            }
//        } catch (IOException | SQLException e) {
//            Logger.getLogger(modelPGconexion.class.getName()).log(Level.SEVERE, null, e);
//        }
//
//        try {
//            rs.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(modelEmpleado.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return listaAnimales;
//    }


///////////////////////
//CREATE TABLE "cliente" (
//	cli_id serial PRIMARY KEY not null,
//	cli_direccion character varying(250),
//	cli_cedula character varying(10),
//	cli_estado boolean,
//	FOREIGN KEY(cli_cedula) REFERENCES "persona" ("per_cedula") ON UPDATE NO ACTION ON DELETE NO ACTION
//);


//    public void abrirPrincipal() {
////        viewPantallaPrincipal vistaP = new viewPantallaPrincipal();
//        if (mivalidacion.validarLogin(vista.getTxtusuarioingreso().getText(), vista.getTxtcontraingreso().getText())) {
//            viewPantallaPrincipal vistap = new viewPantallaPrincipal();
//            controllerPantallaprincipal controller = new controllerPantallaprincipal(vistap, vista.getTxtusuarioingreso().getText(), vista.getTxtcontraingreso().getText());
//            controller.iniciaControl();
//            vista.dispose();
//        }
//    }