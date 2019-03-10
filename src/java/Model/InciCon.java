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
 * @author JMartinNavas
 */
public class InciCon {

    private int id;
    private Date fecha_inicio;
    private Date fecha_fin;
    private String observacion;
    private Double costo;
    private String documento_soporte;
    private String tipo_falla;
    private String conductor;

    public InciCon(int id, Date fecha_inicio, Date fecha_fin, String observacion, Double costo, String documento_soporte, String tipo_falla, String conductor) {
        this.id = id;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.observacion = observacion;
        this.costo = costo;
        this.documento_soporte = documento_soporte;
        this.tipo_falla = tipo_falla;
        this.conductor = conductor;

    }

    public InciCon() {
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the fecha_inicio
     */
    public Date getFecha_inicio() {
        return fecha_inicio;
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

    /**
     * @return the fecha_fin
     */
    public Date getFecha_fin() {
        return fecha_fin;
    }

    /**
     * @return the observacion
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * @param observacion the observacion to set
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    /**
     * @return the costo
     */
    public Double getCosto() {
        return costo;
    }

    /**
     * @param costo the costo to set
     */
    public void setCosto(Double costo) {
        this.costo = costo;
    }

    /**
     * @return the documento_soporte
     */
    public String getDocumento_soporte() {
        return documento_soporte;
    }

    /**
     * @param documento_soporte the documento_soporte to set
     */
    public void setDocumento_soporte(String documento_soporte) {
        this.documento_soporte = documento_soporte;
    }

    /**
     * @return the tipo_falla
     */
    public String getTipo_falla() {
        return tipo_falla;
    }

    /**
     * @param tipo_falla the tipo_falla to set
     */
    public void setTipo_falla(String tipo_falla) {
        this.tipo_falla = tipo_falla;
    }

    /**
     * @return the condictor
     */
    public String getConductor() {
        return conductor;
    }

    /**
     * @param conductor the condictor to set
     */
    public void setConductor(String conductor) {
        this.conductor = conductor;
    }

}
