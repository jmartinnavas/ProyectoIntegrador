/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author fegobe
 */
public class Connect {

    public DriverManagerDataSource GetConnect() {
        DriverManagerDataSource datos = new DriverManagerDataSource();
        datos.setDriverClassName("org.postgresql.Driver");
        datos.setUrl("jdbc:postgresql://localhost:5432/proyectointegrador");
        datos.setUsername("postgres");
       datos.setPassword("federico");
       // datos.setPassword("manizales11");
        return datos;
    }
}
