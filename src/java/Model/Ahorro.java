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
public class Ahorro {

    private int id;
    private Date fecha_entrega;
    private Double cantidad;
    private String conductor;

    public Ahorro() {
    }

    public Ahorro(int id, Date fecha_entrega, Double cantidad, String conductor ) {
        this.id = id;
        this.fecha_entrega = fecha_entrega;
        this.cantidad = cantidad;
        this.conductor = conductor;
        
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
     * @return the fecha_entrega
     */
    public Date getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(String fecha_entrega) {
        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-mm-dd");
            java.util.Date date = sdf1.parse(fecha_entrega);
            this.fecha_entrega = new java.sql.Date(date.getTime());
        } catch (ParseException e) {
            System.out.println("error al convertir");
        }
    }

    /**
     * @return the cantidad
     */
    public Double getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the conductor
     */
    public String getConductor() {
        return conductor;
    }

    /**
     * @param conductor the conductor to set
     */
    public void setConductor(String conductor) {
        this.conductor = conductor;
    }
    
    
    
}
