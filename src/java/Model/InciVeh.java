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
public class InciVeh {

    private int id;
    private Date fecha_inicio;
    private Date fecha_fin;
    private String observacion;
    private Double costo;
    private String documento_soporte;
    private String tipo_falla;
    private String vehiculo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public String getDocumento_soporte() {
        return documento_soporte;
    }

    public void setDocumento_soporte(String documento_soporte) {
        this.documento_soporte = documento_soporte;
    }

 

    public String getTipo_falla() {
        return tipo_falla;
    }

    public void setTipo_falla(String tipo_falla) {
        this.tipo_falla = tipo_falla;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }
    public void setFecha_inicio(String fecha_inicio) {
        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-mm-dd");
            java.util.Date date = sdf1.parse(fecha_inicio);
            this.fecha_inicio = new java.sql.Date(date.getTime());
        } catch (ParseException e) {
            System.out.println("error al convertir");
        }
    }

    public void setFecha_fin(String fecha_fin) {
        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-mm-dd");
            java.util.Date date = sdf1.parse(fecha_fin);
            this.fecha_fin = new java.sql.Date(date.getTime());
        } catch (ParseException e) {
            System.out.println("error al convertir");
        }
    }
}
