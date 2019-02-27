/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import jdk.nashorn.internal.runtime.logging.Logger;

/**
 *
 * @author JMartinNavas
 */
public class Conductor {

    private String cedula;
    private String nombre;
    private String apellido;
    private String telefono;
    private String telefono_soporte;
    private String estado;

    private String placa;
    private Date fecha_ingreso;
    private String foto;

    public Conductor(String cedula, String nombre, String apellido, String telefono, String telefono_soporte, String estado, String foto, String placa, Date fecha_ingreso) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.telefono_soporte = telefono_soporte;
        this.estado = estado;
        this.foto = foto;
        this.placa = placa;
        this.fecha_ingreso = fecha_ingreso;

    }

    public Conductor() {
    }

    /**
     * @return the cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the telefono_soporte
     */
    public String getTelefono_soporte() {
        return telefono_soporte;
    }

    /**
     * @param telefono_soporte the telefono_soporte to set
     */
    public void setTelefono_soporte(String telefono_soporte) {
        this.telefono_soporte = telefono_soporte;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the foto
     */
    public String getFoto() {
        return foto;
    }

    /**
     * @param foto the foto to set
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }

    /**
     * @return the placa
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * @param placa the placa to set
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * @return the fecha_ingreso
     */
    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    /**
     * @param fecha_ingreso the fecha_ingreso to set
     */
    public void setFecha_ingreso(String fecha_ingreso) {
        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-mm-dd");
            java.util.Date date = sdf1.parse(fecha_ingreso);
            this.fecha_ingreso = new java.sql.Date(date.getTime());
        } catch (ParseException e) {
            System.out.println("error al convertir");
        }
    }

}
