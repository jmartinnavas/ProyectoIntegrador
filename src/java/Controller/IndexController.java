/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Connect;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author JMartinNavas
 */
@Controller
public class IndexController {

    private JdbcTemplate jdbc;

    public IndexController() {
        Connect con = new Connect();
        this.jdbc = new JdbcTemplate(con.GetConnect());

    }
    
    // cargar vista principal de index
    @RequestMapping("index.htm")
    public ModelAndView form(HttpServletRequest request) {
        
        System.out.println("carga de datos");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        String SQL = "select ((select sum(costo) from incidencias_vehiculos) + (select sum(costo) from incidencias_conductores )) AS totalGastos;";
       
        List l;
        l = this.jdbc.queryForList(SQL);
        System.out.println(l.toString());
        mv.addObject("datos", l);
       
        return mv;
    }

   
    

}
