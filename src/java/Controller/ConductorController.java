/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Connect;
import Model.Vehiculo;
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
public class ConductorController {
   private JdbcTemplate jdbc;

    public ConductorController() {
       Connect con = new Connect();
        this.jdbc = new JdbcTemplate(con.GetConnect());
    }
   
 // cargar vista principal de conductor
    @RequestMapping("conductor.htm")
    public ModelAndView form() {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("conductor");
        String SQL = "select * from conductor;";
        List l;
        l = this.jdbc.queryForList(SQL);
        System.out.println(l.toString());
        mv.addObject("datos", l);
        return mv;
    }
    
    @RequestMapping("DeleteCon.htm")
    public ModelAndView form(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String sql = "DELETE FROM conductor WHERE cedula=?";
        this.jdbc.update(sql, id);
        return new ModelAndView("redirect:/conductor.htm");
    }
    
   
}
