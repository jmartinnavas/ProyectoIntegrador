/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;

/**
 *
 * @author fegobe
 */
@Controller
public class EntregaController {

    private JdbcTemplate jdbc;
    private ConductorController cc;

    public EntregaController() {
        Connect con = new Connect();
        this.jdbc = new JdbcTemplate(con.GetConnect());
        cc = new ConductorController();
    }

    // cargar vista principal de entrega conductor
    @RequestMapping("entrega.htm")
    public ModelAndView form(HttpServletRequest request) {
        String cedula = request.getParameter("cedula");
        System.out.println("carga de datos");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("entrega");
        String SQL = "select * from entregas where conductor=" + Integer.parseInt(cedula) + ";";
        List l;
        l = this.jdbc.queryForList(SQL);
        System.out.println(l.toString());
        mv.addObject("datos", l);
        mv.addObject("cedula", cedula);
        return mv;
    }

    //añadir Entrega con base en la cedular que trae la ventana
    @RequestMapping(method = RequestMethod.GET, value = "AddEntrega.htm")
    public ModelAndView formAdd(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String cedula = request.getParameter("cedula");

        mv.setViewName("AddEntrega");

        mv.addObject("Entregas", new Entrega());
        mv.addObject("cedula", cedula);

        return mv;
    }

    //EDITAR Entrega Conductor
    @RequestMapping(method = RequestMethod.GET, value = "EditEntrega.htm")
    public ModelAndView formeEdit(HttpServletRequest request) {

        ModelAndView mv = new ModelAndView();
        String id = request.getParameter("id");
        Entrega e = this.SelectEntrega(id);
        mv.setViewName("EditEntrega");
        mv.addObject("Entregas", e);
        return mv;
    }

    //editar entrega  post
    //envia los cambios realizados a la base de datos y se actualiza 
    @RequestMapping(method = RequestMethod.POST, value = "EditEntrega.htm")
    public ModelAndView form(
            @ModelAttribute("Entregas") Entrega e,
            BindingResult result,
            SessionStatus status,
            HttpServletRequest request
    ) {

        ModelAndView mv;
        mv = new ModelAndView();

        String sql = "UPDATE entregas SET "
                + "fecha_entrega=? ,"
                + "valor=?,"
                + "descripcion=?"
                + " WHERE id=?;";
        this.jdbc.update(sql, e.getFecha_entrega(), e.getValor(), e.getDescripcion(), e.getId()
        );

        mv = new ModelAndView("redirect:/entrega.htm?cedula=" + e.getConductor());

        return mv;
    }

    //añadir entrega metodo post
    //los datos del formulario se envian a la base de datos y se añade la nueva entrega 
    @RequestMapping(method = RequestMethod.POST, value = "AddEntrega.htm")
    public ModelAndView formAddPost(
            @ModelAttribute("Entregas") Entrega e,
            BindingResult result,
            SessionStatus status,
            HttpServletRequest request
    ) {

        ModelAndView mv;
        System.out.println("addentrega post");
        String sql = "Insert into entregas(fecha_entrega,valor,descripcion,conductor)"
                + " VALUES (?,?,?,?)";
        this.jdbc.update(sql, e.getFecha_entrega(), e.getValor(), e.getDescripcion(),
                Integer.parseInt(e.getConductor())
        );
        Conductor c = cc.Selectconductor(e.getConductor());
        sql = "insert into ingresos_vehiculos (valor,vehiculo,fecha) values (" + e.getValor()
                + ", '" + c.getPlaca() + "', '" + e.getFecha_entrega() + "')";
        jdbc.execute(sql);
        mv = new ModelAndView("redirect:/entrega.htm?cedula=" + Integer.parseInt(e.getConductor()));

        return mv;
    }

    /**
     * retorna el objeto conductor buscado por su cedula
     *
     * @param cedula cedula del conductor a buscar
     * @return objeto conductor asociado con la cedula buscada
     */
    private Entrega SelectEntrega(String id) {
        final Entrega entrega = new Entrega();
        String query = "SELECT * FROM entregas WHERE id='" + id + "';";
        return (Entrega) jdbc.query(query, new ResultSetExtractor<Entrega>() {
            @Override
            public Entrega extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {

                    entrega.setId(Integer.parseInt(rs.getString("id")));
                    entrega.setFecha_entrega(rs.getString("fecha_entrega"));
                    entrega.setValor(Double.parseDouble(rs.getString("valor")));
                    entrega.setDescripcion(rs.getString("descripcion"));
                    entrega.setConductor((rs.getString("conductor")));

                }
                return entrega;
            }
        });
    }

}
