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
public class Entrega {

    private int id;
    private Date fecha_entrega;
    private Double valor;
    private String descripcion;
    private String conductor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha_entrega() {
        return fecha_entrega;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getConductor() {
        return conductor;
    }

    public void setConductor(String conductor) {
        this.conductor = conductor;
    }
}
