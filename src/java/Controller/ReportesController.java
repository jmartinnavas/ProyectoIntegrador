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
        String SQL = "select 'ingresos por entregas' as nombre ,sum (e.valor) as total from entregas e union"
                + " select 'egresos por incidencias de vehiculos' as nombre ,sum (iv.costo) as total from incidencias_vehiculos iv union"
                + " select 'egresos por incidencias de conductores' as nombre ,sum (ic.costo) as total from incidencias_conductores ic";
        List l;
        l = jdbc.queryForList(SQL);
        Double suma = 0.0;
        System.out.println(l);
        for (int i = 0; i < l.size(); i++) {
            Map<String, Object> m = (Map<String, Object>) l.get(i);
            String tipo = m.get("nombre").toString();
            if (tipo.contains("egresos")) {
                suma -= Double.parseDouble(m.get("total").toString());
            } else if (tipo.contains("ingresos")) {
                suma += Double.parseDouble(m.get("total").toString());
            }
        }
        mv.addObject("suma", suma);
        mv.addObject("datos", l);
        return mv;
    }

    @RequestMapping(method = RequestMethod.GET, value = "balanceGeneralConductores.htm")
    public ModelAndView generalConductores() {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("balanceGeneralConductores");
        String SQL = "select nombre,  sum(valor) as balance from (\n"
                + "select 'ingreso' as tipo, c.nombre||' '||c.apellido as nombre,sum(e.valor) as valor\n"
                + "from conductor c inner join entregas e on (c.cedula=e.conductor)\n"
                + "group by c.nombre,c.apellido\n"
                + "union\n"
                + "select 'egreso' as tipo,c.nombre ||' '||c.apellido as nombre,sum(ic.costo)*-1 as valor\n"
                + "from conductor c inner join incidencias_conductores ic on (c.cedula=ic.conductor)\n"
                + "group by c.nombre,c.apellido\n"
                + ") ivc\n"
                + "group by nombre order by balance desc";
        List l;
        l = jdbc.queryForList(SQL);
        System.out.println(l);

        mv.addObject("datos", l);
        return mv;
    }

    @RequestMapping(method = RequestMethod.GET, value = "incidenciaCritica.htm")
    public ModelAndView incidenciaCritica() {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("incidenciaCritica");
        String SQLConductores = "select sum(costo) as maximo, tipo_falla as falla from incidencias_conductores\n"
                + "group by falla order by maximo desc "
                + "FETCH FIRST 1 ROW ONLY ";
        Map< String, Object> lConductores = jdbc.queryForList(SQLConductores).get(0);
        Double valorConductor = Double.parseDouble(lConductores.get("maximo").toString());
        System.out.println(lConductores);

        String SQLVehiculo = "select sum(costo) as maximo, tipo_falla as falla from incidencias_vehiculos \n"
                + "group by falla order by maximo desc "
                + "FETCH FIRST 1 ROW ONLY";
        Map< String, Object> lVehiculos = jdbc.queryForList(SQLVehiculo).get(0);
        Double valorVehiculo = Double.parseDouble(lVehiculos.get("maximo").toString());
        System.out.println(lVehiculos);
        if (valorVehiculo > valorConductor) {
            mv.addObject("maxInci", lVehiculos.get("falla") + " De vehiculos");
        } else {
            mv.addObject("maxInci", lConductores.get("falla") + " De conductores");
        }
        mv.addObject("inciCon", lConductores.get("falla").toString() + "= " + valorConductor);
        mv.addObject("inciVeh", lVehiculos.get("falla").toString() + "= " + valorVehiculo);

        return mv;
    }
}
