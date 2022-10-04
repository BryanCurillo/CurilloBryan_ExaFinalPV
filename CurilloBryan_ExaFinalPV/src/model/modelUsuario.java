/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
//import java.awt.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 *
 * @author Bryan
 */
public class modelUsuario extends usuario {

    modelPGconexion mpgc = new modelPGconexion();

    public boolean setUsuario() {
        String sql = "INSERT INTO usuario(us_nombre, us_contra, us_permiso,us_estado)  "
                + "	VALUES ('" + getNombre_Usu() + "', '" + getContra_Usu() + "' , '" + getPermiso_Usu() + "'," + isEstado_Usu() + ")";
        return mpgc.accion(sql);
    }

    public boolean setFotoUsuario() {
        String sql;
        sql = "INSERT INTO usuario(us_nombre, us_contra, us_permiso, us_foto,us_estado) "
                + "	VALUES (?, ?, ?, ?, ?)";

        PreparedStatement ps;
        try {
            ps = mpgc.con.prepareStatement(sql);
            ps.setString(1, getNombre_Usu());
            ps.setString(2, getContra_Usu());
            ps.setString(3, getPermiso_Usu());
            ps.setBinaryStream(4, getImageFile(), getTamano());
            ps.setBoolean(5, isEstado_Usu());

            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(modelUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean updateUsuario() {
        String sql = "UPDATE usuario  "
                + "	SET us_nombre=?, us_contra=?, us_permiso=? "
                + "	WHERE us_id=" + getId_Usu();

        return mpgc.accion(sql);
    }

    public boolean updateFotoUsuario() {
        String sql;
        sql = "UPDATE usuario  "
                + "	SET us_nombre=?, us_contra=?, us_permiso=?, us_foto=?  "
                + "	WHERE us_id=" + getId_Usu();

        PreparedStatement ps;
        try {
            ps = mpgc.con.prepareStatement(sql);
            ps.setString(1, getNombre_Usu());
            ps.setString(2, getContra_Usu());
            ps.setString(3, getPermiso_Usu());
            ps.setBinaryStream(4, getImageFile(), getTamano());

            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(modelUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            return false;
        }
    }

    private Image getImagen(byte[] bytes) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        Iterator it = ImageIO.getImageReadersByFormatName("jpeg");
        ImageReader imageReader = (ImageReader) it.next();
        Object source = bais;
        ImageInputStream fis = ImageIO.createImageInputStream(source);
        imageReader.setInput(fis, true);
        ImageReadParam param = imageReader.getDefaultReadParam();
        param.setSourceSubsampling(1, 1, 0, 0);
        return imageReader.read(0, param);
    }

    public List<usuario> getUsuarios(String busqueda) {
        List<usuario> listaUsuarios = new ArrayList<>();
        String sql = "select * from usuario  "
                + "where us_estado=true and (  "
                + "	lower(us_nombre) like'%" + busqueda + "%'  "
                + "     OR lower(us_permiso) like'%" + busqueda + "%'  "
                + ")";

        ResultSet rs = mpgc.consulta(sql);
        byte[] bytea;

        try {
            while (rs.next()) {
                usuario usu = new usuario();
                usu.setId_Usu(rs.getInt(1));
                usu.setNombre_Usu(rs.getString(2));
                usu.setContra_Usu(rs.getString(3));
                usu.setPermiso_Usu(rs.getString(4));
                usu.setEstado_Usu(rs.getBoolean(5));
                bytea = rs.getBytes(6);
                if (bytea != null) {
                    usu.setFoto(getImagen(bytea));
                }

                listaUsuarios.add(usu);
            }
        } catch (SQLException | IOException ex) {
            java.util.logging.Logger.getLogger(modelUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        try {
            rs.close();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(modelUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        return listaUsuarios;
    }

    public boolean deleteUsuario(int id) {
        String sql;
        sql = "UPDATE usuario SET us_estado=false "
                + "WHERE us_id=" + id;
        return mpgc.accion(sql);
    }

}
