/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Conductor;
import Model.Connect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author fegobe
 */
@Controller
public class ReportesController {

    private JdbcTemplate jdbc;

    public ReportesController() {
        Connect con = new Connect();
        this.jdbc = new JdbcTemplate(con.GetConnect());

    }
    //cargar vista consultar conductor

    @RequestMapping(method = RequestMethod.GET, value = "balanceGeneral.htm")
    public ModelAndView form() {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("balanceGeneral");
        String SQL = "select 'ingesos por entregas' as nombre ,sum (e.valor) as total from entregas e union"
                + " select 'egresos por incidencias de vehiculos' as nombre ,sum (iv.costo) as total from incidencias_vehiculos iv union"
                + " select 'egresos por incidencias de conductores' as nombre ,sum (ic.costo) as total from incidencias_conductores ic";
        List l;
        l = jdbc.queryForList(SQL);
// falta sumar la wea
        mv.addObject("datos", l);
        return mv;
    }

}
