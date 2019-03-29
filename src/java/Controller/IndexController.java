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
    @RequestMapping(method = RequestMethod.GET, value = "index.htm")
    public ModelAndView form(HttpServletRequest request) {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        String SQL = "select ((select sum(costo) from incidencias_vehiculos) + (select sum(costo) from incidencias_conductores )) AS totalGastos;";
        String SQL2 = "select count(cedula) as activos , (select count(cedula) from conductor) as total from conductor where estado = 'activo' ; ";
        String SQL3 = "select count(placa) as vencidos , (select count(placa) from vehiculo) as totalc  from vehiculo where current_date > fecha_soat ;  ";
        String SQL4 = "select sum(valor) as totaldia   from entregas where current_date = fecha_entrega ; ";
        String SQL5 = "select cedula , nombre , apellido , telefono from conductor where estado='activo' ; ";
        List l;
        l = this.jdbc.queryForList(SQL);
        List l2;
        l2 = this.jdbc.queryForList(SQL2);
        List l3;
        l3 = this.jdbc.queryForList(SQL3);
        List l4;
        l4 = this.jdbc.queryForList(SQL4);
        List l5;
        l5 = this.jdbc.queryForList(SQL5);

        mv.addObject("datos", l);
        mv.addObject("datos2", l2);
        mv.addObject("datos3", l3);
        mv.addObject("datos4", l4);
        mv.addObject("datos5", l5);

        return mv;
    }

}
