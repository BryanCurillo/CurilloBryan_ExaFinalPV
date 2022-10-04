/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Image;
import java.io.FileInputStream;

/**
 *
 * @author Bryan
 */
public class usuario {

    private int id_Usu;
    private String nombre_Usu;
    private String contra_Usu;
    private String permiso_Usu;
    private boolean estado_Usu;

    //foto
    private Image foto;
    //guardar foto
    private FileInputStream imageFile;
    private int tamano;

    public usuario() {
    }

    public usuario(int id_Usu, String nombre_Usu, String contra_Usu, String permiso_Usu, boolean estado_Usu, Image foto, FileInputStream imageFile, int tamano) {
        this.id_Usu = id_Usu;
        this.nombre_Usu = nombre_Usu;
        this.contra_Usu = contra_Usu;
        this.permiso_Usu = permiso_Usu;
        this.estado_Usu = estado_Usu;
        this.foto = foto;
        this.imageFile = imageFile;
        this.tamano = tamano;
    }

    public boolean isEstado_Usu() {
        return estado_Usu;
    }

    public void setEstado_Usu(boolean estado_Usu) {
        this.estado_Usu = estado_Usu;
    }


    public int getId_Usu() {
        return id_Usu;
    }

    public void setId_Usu(int id_Usu) {
        this.id_Usu = id_Usu;
    }

    public String getNombre_Usu() {
        return nombre_Usu;
    }

    public void setNombre_Usu(String nombre_Usu) {
        this.nombre_Usu = nombre_Usu;
    }

    public String getContra_Usu() {
        return contra_Usu;
    }

    public void setContra_Usu(String contra_Usu) {
        this.contra_Usu = contra_Usu;
    }

    public String getPermiso_Usu() {
        return permiso_Usu;
    }

    public void setPermiso_Usu(String permiso_Usu) {
        this.permiso_Usu = permiso_Usu;
    }

    public Image getFoto() {
        return foto;
    }

    public void setFoto(Image foto) {
        this.foto = foto;
    }

    public FileInputStream getImageFile() {
        return imageFile;
    }

    public void setImageFile(FileInputStream imageFile) {
        this.imageFile = imageFile;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }
    
    

}
