/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Connect;
import Model.Vehiculo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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

/**
 *
 * @author fegobe
 */
@Controller
public class VehiculoController {

    private JdbcTemplate jdbc;

    public VehiculoController() {
        Connect con = new Connect();
        this.jdbc = new JdbcTemplate(con.GetConnect());
    }
//cargar vista consultar vehiculo

    @RequestMapping(method = RequestMethod.GET, value = "ConsultVeh.htm")
    public ModelAndView form(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String id = request.getParameter("id");
        Vehiculo v = this.Selectvehiculo(id);
        mv.setViewName("ConsultVeh");
        mv.addObject("vehiculos", new Vehiculo(v.getPlaca(), v.getMarca(), v.getModelo(), v.getMotor(), v.getFecha_ingreso(), v.getFecha_soat(), v.getFoto()));
        return mv;
    }
//editar vehiculo
    //cargar vista editar

    @RequestMapping(method = RequestMethod.GET, value = "EditVeh.htm")
    public ModelAndView formeEdit(HttpServletRequest request) {

        ModelAndView mv = new ModelAndView();
        String id = request.getParameter("id");
        Vehiculo v = this.Selectvehiculo(id);
        mv.setViewName("EditVeh");
        mv.addObject("vehiculos", new Vehiculo(v.getPlaca(), v.getMarca(), v.getModelo(), v.getMotor(), v.getFecha_ingreso(), v.getFecha_soat(), v.getFoto()));
        return mv;
    }

    //editar post
    @RequestMapping(method = RequestMethod.POST, value = "EditVeh.htm")
    public ModelAndView form(
            @ModelAttribute("vehiculos") Vehiculo v,
            BindingResult result,
            SessionStatus status,
            HttpServletRequest request
    ) {

        ModelAndView mv;
        if (result.hasErrors()) {
            mv = new ModelAndView();
            String id = request.getParameter("id");
            System.out.println("mi id if " + id);
            Vehiculo tmp = this.Selectvehiculo(id);
            mv.setViewName("EditVeh");
            mv.addObject("vehiculos", new Vehiculo(v.getPlaca(), v.getMarca(), v.getModelo(), v.getMotor(), v.getFecha_ingreso(), v.getFecha_soat(), v.getFoto()));
        } else {
            String id = request.getParameter("id");
            System.out.println("mi id else " + id);

            String sql = "UPDATE vehiculo SET "
                    + "marca=? ,"
                    + "modelo=?,"
                    + "motor=?,"
                    + "fecha_ingreso=?,"
                    + "foto=? "
                    + "WHERE placa=?;";
            this.jdbc.update(sql, v.getMarca(), v.getModelo(),
                    v.getMotor(), v.getFecha_ingreso(), v.getFoto(), id);

            mv = new ModelAndView("redirect:/vehiculo.htm");
        }
        return mv;
    }

    //añadir vehiculo
    @RequestMapping(method = RequestMethod.GET, value = "AddVeh.htm")
    public ModelAndView formAdd() {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("AddVeh");
        mv.addObject("vehiculos", new Vehiculo());
        return mv;
    }

    @RequestMapping(method = RequestMethod.POST, value = "AddVeh.htm")
    public ModelAndView form(
            @ModelAttribute("vehiculos") Vehiculo v,
            BindingResult result,
            SessionStatus status
    ) {
        ModelAndView mv;
        if (result.hasErrors()) {
            mv = new ModelAndView();
            mv.setViewName("AddVeh");
            mv.addObject("vehiculos", new Vehiculo());
        } else {
            String sql = "Insert into vehiculo(placa,marca,modelo,motor,fecha_ingreso,foto)"
                    + " VALUES (?,?,?,?,?,?)";
            this.jdbc.update(sql, v.getPlaca(), v.getMarca(), v.getModelo(),
                    v.getMotor(), v.getFecha_ingreso(), v.getFoto());

            mv = new ModelAndView("redirect:/vehiculo.htm");
        }

        return mv;
    }

    //borrar vehiculo
    @RequestMapping("DeleteVeh.htm")
    public ModelAndView formDelete(HttpServletRequest request) {
        String id = request.getParameter("id");
        String sql = "DELETE FROM vehiculo WHERE placa=?";
        this.jdbc.update(sql, id);
        return new ModelAndView("redirect:/vehiculo.htm");
    }

    /**
     * retorna el objeto vehiculo buscado por su placa
     *
     * @param placa placa del vehiculo a buscar
     * @return objeto vehiculo asociado a la placa buscada
     */
    private Vehiculo Selectvehiculo(String placa) {
        final Vehiculo veh = new Vehiculo();
        String query = "SELECT * FROM vehiculo WHERE placa='" + placa + "';";
        return (Vehiculo) jdbc.query(query, new ResultSetExtractor<Vehiculo>() {
            @Override
            public Vehiculo extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    veh.setPlaca(rs.getString("placa"));
                    veh.setMarca(rs.getString("marca"));
                    veh.setModelo(rs.getString("modelo"));
                    veh.setMotor(rs.getString("motor"));
                    veh.setFecha_ingreso(rs.getString("fecha_ingreso"));
                    veh.setFoto(rs.getString("foto"));

                }
                return veh;
            }
        });
    }

    // cargar vista principal de vehiculo
    @RequestMapping("vehiculo.htm")
    public ModelAndView form() {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("vehiculo");
        String SQL = "select * from vehiculo;";
        List l;
        l = this.jdbc.queryForList(SQL);
        System.out.println(l.toString());
        mv.addObject("datos", l);
        return mv;
    }
}
