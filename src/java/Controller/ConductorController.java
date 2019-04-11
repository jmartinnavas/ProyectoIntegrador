/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Conductor;
import java.io.File;
import java.io.FileOutputStream;

import Model.Connect;
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
@Controller

/**
 *
 * @author JMartinNavas
 */
public class ConductorController {

    private JdbcTemplate jdbc;

    public ConductorController() {
        Connect con = new Connect();
        this.jdbc = new JdbcTemplate(con.GetConnect());
        //this.Validator = new CondValidator();
    }

    //EDITAR CONDUCTOR
    // la vista se carga con los datos del conductor que vamos a editar
    @RequestMapping(method = RequestMethod.GET, value = "EditCon.htm")
    public ModelAndView formeEdit(HttpServletRequest request) {

        ModelAndView mv = new ModelAndView();
        String id = request.getParameter("id");
        Conductor c = this.Selectconductor(id);
        mv.setViewName("EditCon");
        mv.addObject("conductores", new Conductor(id, c.getNombre(), c.getApellido(), c.getTelefono(), c.getTelefono_soporte(),
                c.getEstado(), c.getFoto(), c.getPlaca(), c.getFecha_ingreso()));
        return mv;
    }

    //editar post
    // al hacer click en enviar el formulario con las modificaciones,
    // el metodo post lleva los nuevos datos a la bd y guarda la informacion
    @RequestMapping(method = RequestMethod.POST, value = "EditCon.htm")
    public ModelAndView form(
            @RequestParam("foto") MultipartFile file,
            @ModelAttribute("conductores") Conductor c,
            BindingResult result,
            SessionStatus status,
            HttpServletRequest request
    ) {

        ModelAndView mv;
        String path = request.getServletContext().getRealPath("/PUBLIC") + "/resources/img/profilesFolder/conductores/";

        mv = new ModelAndView();
        String id = request.getParameter("id");
        Integer.parseInt(id);
        System.out.println("mi id else " + id);

        String sql = "UPDATE conductor SET "
                + "cedula=? ,"
                + "nombre=?,"
                + "apellido=?,"
                + "telefono=?,"
                + "telefono_soporte=?,"
                + "estado=?,"
                + "foto=?,"
                + "placa=?,"
                + "fecha_ingreso=?"
                + " WHERE cedula=?;";
        this.jdbc.update(sql, Integer.parseInt(c.getCedula()), c.getNombre(), c.getApellido(), Integer.parseInt(c.getTelefono()), Integer.parseInt(c.getTelefono_soporte()),
                c.getEstado(),"/PUBLIC/resources/img/profilesFolder/conductores/" + c.getCedula() + "." + file.getContentType().split("/")[1]
                , c.getPlaca(), c.getFecha_ingreso(), Integer.parseInt(id));

         InputStream is;
         try {
            is = file.getInputStream();
            File f = new File(path + c.getCedula() + "." + file.getContentType().split("/")[1]);
            FileOutputStream ous = new FileOutputStream(f);
            int dato = is.read();
            while (dato != -1) {
                ous.write(dato);
                dato = is.read();
            }
        } catch (IOException ex) {
            Logger.getLogger(ConductorController.class.getName()).log(Level.SEVERE, null, ex);

        }
         
        mv = new ModelAndView("redirect:/conductor.htm");

        return mv;
    }

    //cargar vista consultar conductor
    @RequestMapping(method = RequestMethod.GET, value = "ConsultCon.htm")
    public ModelAndView form(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String id = request.getParameter("id");
        Conductor c = this.Selectconductor(id);
        mv.setViewName("ConsultCon");
        mv.addObject("conductores",
                new Conductor(id, c.getNombre(), c.getApellido(), c.getTelefono(), c.getTelefono_soporte(),
                        c.getEstado(), c.getFoto(), c.getPlaca(), c.getFecha_ingreso()));
        return mv;
    }

    // cargar vista principal de conductor
    //en la vista se muestran todos los conductores que hay en la empresa 
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

    // eliminar un conductor 
    @RequestMapping("DeleteCon.htm")
    public ModelAndView formDelete(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String sql = "DELETE FROM conductor WHERE cedula=?";
        this.jdbc.update(sql, id);
        return new ModelAndView("redirect:/conductor.htm");
    }

    //AÑADIR CONDUCTOR
    //se cargan en la vista el formulario con todos los datos
    //necesarios para añadir un conductor
    @RequestMapping(value = "AddCon.htm", method = RequestMethod.GET)
    public ModelAndView formAddCon() {
        System.out.println("vista add conductor");

        ModelAndView mv = new ModelAndView();
        mv.setViewName("AddCon");
        mv.addObject("conductores", new Conductor());
        return mv;
    }

    @RequestMapping(value = "AddCon.htm", method = RequestMethod.POST)
    public ModelAndView formAddPost(@RequestParam("foto") MultipartFile file,
            @ModelAttribute("conductores") Conductor c,
            BindingResult result,
            SessionStatus status,
            HttpServletRequest request) {

        ModelAndView mv;
        String path = request.getServletContext().getRealPath("/PUBLIC") + "/resources/img/profilesFolder/conductores/";
        System.out.println(path);
        String sql = "Insert into conductor(cedula,nombre,apellido,telefono,telefono_soporte,estado"
                + ",foto,placa,fecha_ingreso)"
                + " VALUES (?,?,?,?,?,?,?,?,?);";
        this.jdbc.update(sql, Integer.parseInt(c.getCedula()), c.getNombre(), c.getApellido(), Integer.parseInt(c.getTelefono()),
                Integer.parseInt(c.getTelefono_soporte()), c.getEstado(),
                "/PUBLIC/resources/img/profilesFolder/conductores/" + c.getCedula() + "." + file.getContentType().split("/")[1],
                c.getPlaca(),
                c.getFecha_ingreso());

        InputStream is;
        try {
            is = file.getInputStream();
            File f = new File(path + c.getCedula() + "." + file.getContentType().split("/")[1]);
            FileOutputStream ous = new FileOutputStream(f);
            int dato = is.read();
            while (dato != -1) {
                ous.write(dato);
                dato = is.read();
            }
        } catch (IOException ex) {
            Logger.getLogger(ConductorController.class.getName()).log(Level.SEVERE, null, ex);

        }
        mv = new ModelAndView("redirect:/conductor.htm");

        return mv;
    }

    @ModelAttribute("vehiculo_placa")
    public Map<String, String> ListVeh() {
        VehiculoController vc = new VehiculoController();
        return vc.ListVeh();
    }

    /**
     * retorna el objeto conductor buscado por su cedula
     *
     * @param cedula cedula del conductor a buscar
     * @return objeto conductor asociado con la cedula buscada
     */
    public Conductor Selectconductor(String cedula) {
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
                    con.setPlaca(rs.getString("placa").trim());
                    con.setFecha_ingreso(rs.getString("fecha_ingreso"));

                }
                return con;
            }
        });
    }
}
