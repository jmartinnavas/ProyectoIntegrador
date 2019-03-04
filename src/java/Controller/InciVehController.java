/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.File;
import java.io.FileOutputStream;
import Model.Connect;
import Model.InciVeh;
import Model.Vehiculo;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author fegobe
 */
@Controller
public class InciVehController {

    private JdbcTemplate jdbc;

    public InciVehController() {
        Connect con = new Connect();
        this.jdbc = new JdbcTemplate(con.GetConnect());
    }

    // cargar vista principal de incidencia vehiculo
    @RequestMapping("inciVeh.htm")
    public ModelAndView form(HttpServletRequest request) {
        String id = request.getParameter("id");
        System.out.println("carga de datos");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("inciVeh");
        String SQL = "select * from incidencias_vehiculos where vehiculo=" +"'"+ id+"'";
        List l;
        l = this.jdbc.queryForList(SQL);
        System.out.println(l.toString());
        mv.addObject("datos", l);
        return mv;
    }

    
    
    //cargar vista consultar incidencia vehiculo

    @RequestMapping(method = RequestMethod.GET, value = "ConsultInciVeh.htm")
    public ModelAndView formconsulta(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String id = request.getParameter("id");
        InciVeh iv = this.SelectIncivehiculo(id);
        mv.setViewName("ConsultInciVeh");
        mv.addObject("datos", iv);
        return mv;
    }
    
    
    
        /**
     * retorna el objeto vehiculo buscado por su placa
     *
     * @param placa placa del vehiculo a buscar
     * @return objeto vehiculo asociado a la placa buscada
     */
    private InciVeh SelectIncivehiculo(String id) {
        final InciVeh Inciveh = new InciVeh();
        String query = "SELECT * FROM incidencias_vehiculos WHERE id='" + id + "';";
        return (InciVeh) jdbc.query(query, new ResultSetExtractor<InciVeh>() {
            @Override
            public InciVeh extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    Inciveh.setId(Integer.parseInt(rs.getString("id")));
                    Inciveh.setFecha_inicio(rs.getString("fecha_inicio"));
                    Inciveh.setFecha_fin(rs.getString("fecha_fin"));
                    Inciveh.setObservacion(rs.getString("observacion"));
                    Inciveh.setCosto(Double.parseDouble(rs.getString("costo")));
                    Inciveh.setDocumentoSoporte(rs.getString("documento_soporte"));
                    Inciveh.setTipo_falla(rs.getString("tipo_falla"));

                }
                return Inciveh;
            }
        });
    }
}
