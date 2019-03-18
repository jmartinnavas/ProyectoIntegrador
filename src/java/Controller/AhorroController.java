/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Ahorro;
import Model.Conductor;
import Model.Connect;
import Model.Entrega;
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
 * @author JMartinNavas
 */
@Controller
public class AhorroController {

    private JdbcTemplate jdbc;

    public AhorroController() {
        Connect con = new Connect();
        this.jdbc = new JdbcTemplate(con.GetConnect());

    }

    // cargar vista principal de ahorro conductor
    @RequestMapping("ahorro.htm")
    public ModelAndView form(HttpServletRequest request) {
        String cedula = request.getParameter("cedula");
        System.out.println("carga de datos");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("ahorro");
        String SQL = "select * from ahorros where conductor=" + Integer.parseInt(cedula) + ";";
        List l;
        l = this.jdbc.queryForList(SQL);
        System.out.println(l.toString());
        mv.addObject("datos", l);
        mv.addObject("cedula", cedula);
        return mv;
    }

    //eliminar un ahorro
    @RequestMapping("DeleteAhorro.htm")
    public ModelAndView formDelete(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String sql = "DELETE FROM ahorros WHERE id=?";
        this.jdbc.update(sql, id);
        //String cedula = Selectconductor(request.getParameter("id")).getCedula();
        //return new ModelAndView("redirect:/ahorro.htm?id=" + cedula);
        return new ModelAndView("redirect:/conductor.htm");
    }

      private Conductor Selectconductor(String cedula) {
        Integer.parseInt(cedula);
        final Conductor con = new Conductor();
        String query = "SELECT * FROM conductor WHERE cedula=" + cedula + ";";
        return (Conductor) jdbc.query(query, new ResultSetExtractor<Conductor>() {
            @Override
            public Conductor extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    con.setCedula(rs.getString("cedula"));
                    con.setNombre(rs.getString("nombre"));
                    con.setApellido(rs.getString("apellido"));
                    con.setTelefono(rs.getString("telefono"));
                    con.setTelefono_soporte(rs.getString("telefono_soporte"));
                    con.setEstado(rs.getString("estado"));
                    con.setFoto(rs.getString("foto"));
                    con.setPlaca(rs.getString("placa"));
                    con.setFecha_ingreso(rs.getString("fecha_ingreso"));

                }
                return con;
            }
        });
    }
    //añadir ahorro
    @RequestMapping(method = RequestMethod.GET, value = "AddAhorro.htm")
    public ModelAndView formAdd(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String cedula = request.getParameter("cedula");

        mv.setViewName("AddAhorro");

        mv.addObject("Ahorros", new Ahorro());
        mv.addObject("cedula", cedula);

        return mv;
    }

    @RequestMapping(method = RequestMethod.POST, value = "AddAhorro.htm")
    public ModelAndView formAddPost(
            @ModelAttribute("Ahorros") Ahorro a,
            BindingResult result,
            SessionStatus status,
            HttpServletRequest request
    ) {

        ModelAndView mv;
        System.out.println("addAhorro post");
        String sql = "Insert into ahorros(fecha,cantidad,conductor)"
                + " VALUES (?,?,?)";
        this.jdbc.update(sql, a.getFecha_entrega(), a.getCantidad(), Integer.parseInt( a.getConductor())
               
        );

        mv = new ModelAndView("redirect:/ahorro.htm?cedula=" + Integer.parseInt(a.getConductor()));

        return mv;
    }

}
