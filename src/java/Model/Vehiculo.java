/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author fegobe
 */
public class Vehiculo {

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
     */
    /**
     *
     * @author root
     */
    private String placa;
    private String marca;
    private String modelo;
    private String motor;
    private Date fecha_ingreso;
    private Date fecha_soat;
    private String foto;

    public Vehiculo(String placa, String marca, String modelo, String motor, Date fecha_ingreso, Date fecha_soat, String foto) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.motor = motor;
        this.fecha_ingreso = fecha_ingreso;
        this.fecha_soat = fecha_soat;
        this.foto = foto;
    }

    public Vehiculo() {
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

    public void setFecha_soat(String fecha_soat) {
        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-mm-dd");
            java.util.Date date = sdf1.parse(fecha_soat);
            this.fecha_soat = new java.sql.Date(date.getTime());
        } catch (ParseException e) {
            System.out.println("error al convertir");
        }
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public Date getFecha_soat() {
        return fecha_soat;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
/**
 * @param foto the foto to set
 */
