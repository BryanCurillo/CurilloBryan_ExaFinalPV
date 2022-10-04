
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
}