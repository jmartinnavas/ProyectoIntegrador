/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.File;
import java.io.FileOutputStream;
import Model.Connect;
import Model.InciCon;
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
        String SQL = "select * from incidencias_vehiculos where vehiculo=" + "'" + id + "'";
        List l;
        l = this.jdbc.queryForList(SQL);
        System.out.println(l.toString());
        mv.addObject("datos", l);
        mv.addObject("placa", id);
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

    //añadir incidencia vehiculo
    @RequestMapping(method = RequestMethod.GET, value = "AddInciVeh.htm")
    public ModelAndView formAdd(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String placa = request.getParameter("placa");

        mv.setViewName("AddInciVeh");

        mv.addObject("InciVehiculos", new InciVeh());
        mv.addObject("placa", placa);

        return mv;
    }

    @RequestMapping(method = RequestMethod.POST, value = "AddInciVeh.htm")
    public ModelAndView formAddPost(
            @RequestParam("documento_soporte") MultipartFile file,
            @ModelAttribute("InciVehiculos") InciVeh iv,
            BindingResult result,
            SessionStatus status,
            HttpServletRequest request
    ) {

        ModelAndView mv;
        System.out.println("addinciveh post");
        String path = request.getServletContext().getRealPath("/PUBLIC") + "/resources/documents/vehiculos";
        System.out.println(path);
        String sql = "Insert into incidencias_vehiculos(fecha_inicio,fecha_fin,observacion,costo,"
                + "documento_soporte,tipo_falla ,vehiculo)"
                + " VALUES (?,?,?,?,?,?,?)";
        this.jdbc.update(sql, iv.getFecha_inicio(), iv.getFecha_fin(), iv.getObservacion(), iv.getCosto(),
                "/PUBLIC/resources/documents/vehiculos" + iv.getVehiculo() + iv.getFecha_inicio() + "." + file.getContentType().split("/")[1],
                iv.getTipo_falla(), iv.getVehiculo()
        );

        InputStream is;
        try {
            System.out.println("subir archivo");

            is = file.getInputStream();

            File f = new File(path + iv.getVehiculo() + iv.getFecha_inicio() + "." + file.getContentType().split("/")[1]);
            FileOutputStream ous = new FileOutputStream(f);
            int dato = is.read();

            while (dato != -1) {
                System.out.println("subiendo");

                ous.write(dato);
                dato = is.read();
            }
        } catch (IOException ex) {
            Logger.getLogger(VehiculoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("vista inciveh");

        mv = new ModelAndView("redirect:/inciVeh.htm?id=" + iv.getVehiculo());

        return mv;
    }

    //EDITAR incidencia Vehiculo
    @RequestMapping(method = RequestMethod.GET, value = "EditInciVeh.htm")
    public ModelAndView formeEdit(HttpServletRequest request) {

        ModelAndView mv = new ModelAndView();
        String id = request.getParameter("id");
        InciVeh iv = this.SelectIncivehiculo(id);
        mv.setViewName("EditInciVeh");
        mv.addObject("inciVehiculos", new InciVeh(Integer.parseInt(id), iv.getFecha_inicio(), iv.getFecha_fin(), iv.getObservacion(),
                iv.getCosto(), iv.getDocumento_soporte(), iv.getTipo_falla(), iv.getVehiculo()));
        mv.addObject("placa", iv.getVehiculo());

        return mv;
    }

    //editar post
    @RequestMapping(method = RequestMethod.POST, value = "EditInciVeh.htm")
    public ModelAndView form(
            @RequestParam("documento_soporte") MultipartFile file,
            @ModelAttribute("inciVehiculos") InciVeh iv,
            BindingResult result,
            SessionStatus status,
            HttpServletRequest request
    ) {

        ModelAndView mv;
        String path = request.getServletContext().getRealPath("/PUBLIC") + "/resources/documents/vehiculos";
        mv = new ModelAndView();
        String id = request.getParameter("id");
        Integer.parseInt(id);

        String sql = "UPDATE incidencias_vehiculos SET "
                + "fecha_inicio=? ,"
                + "fecha_fin=?,"
                + "observacion=?,"
                + "costo=?,"
                + "documento_soporte=?,"
                + "tipo_falla=?"
                + " WHERE id=?;";
        this.jdbc.update(sql, iv.getFecha_inicio(), iv.getFecha_fin(), iv.getObservacion(), iv.getCosto(),
                "/PUBLIC/resources/documents/vehiculos" + iv.getVehiculo() + iv.getFecha_inicio() + "." + file.getContentType().split("/")[1],
                iv.getTipo_falla(), Integer.parseInt(id));

        InputStream is;
        try {
            System.out.println("subir archivo");

            is = file.getInputStream();

            File f = new File(path + iv.getVehiculo() + iv.getFecha_inicio() + "." + file.getContentType().split("/")[1]);
            FileOutputStream ous = new FileOutputStream(f);
            int dato = is.read();

            while (dato != -1) {
                System.out.println("subiendo");

                ous.write(dato);
                dato = is.read();
            }
        } catch (IOException ex) {
            Logger.getLogger(ConductorController.class.getName()).log(Level.SEVERE, null, ex);
        }

        mv = new ModelAndView("redirect:/inciVeh.htm?id=" + iv.getVehiculo());

        return mv;
    }

    //Eliminar incidencia Vehicuo
    @RequestMapping("DeleteInciVeh.htm")
    public ModelAndView formDelete(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String sql = "DELETE FROM incidencias_vehiculos WHERE id=?";
        this.jdbc.update(sql, id);
        String placa = SelectIncivehiculo(request.getParameter("id")).getVehiculo();
        return new ModelAndView("redirect:/inciVeh.htm?id=" + placa);

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
                    Inciveh.setDocumento_soporte(rs.getString("documento_soporte"));
                    Inciveh.setTipo_falla(rs.getString("tipo_falla"));
                    Inciveh.setVehiculo(rs.getString("vehiculo"));
                }
                return Inciveh;
            }
        });
    }

}
