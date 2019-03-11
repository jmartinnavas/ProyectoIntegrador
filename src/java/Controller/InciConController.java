/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Conductor;
import Model.Connect;
import Model.InciCon;
import Model.InciVeh;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author JMartinNavas
 */
@Controller
public class InciConController {

    private JdbcTemplate jdbc;

    public InciConController() {
        Connect con = new Connect();
        this.jdbc = new JdbcTemplate(con.GetConnect());

    }
    
    // cargar vista principal de incidencia conductor
    @RequestMapping("inciCon.htm")
    public ModelAndView form(HttpServletRequest request) {
        String id = request.getParameter("id");
        System.out.println("carga de datos");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("inciCon");
        String SQL = "select * from incidencias_conductores where conductor=" + Integer.parseInt(id) + ";";
        List l;
        l = this.jdbc.queryForList(SQL);
        System.out.println(l.toString());
        mv.addObject("datos", l);
        mv.addObject("cedula", id);
        return mv;
    }
    
    //añadir Conductor
    @RequestMapping(method = RequestMethod.GET, value = "AddInciCon.htm")
    public ModelAndView formAdd(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String cedula = request.getParameter("cedula");

        mv.setViewName("AddInciCon");

        mv.addObject("InciConductores", new InciCon());
        mv.addObject("cedula", cedula);

        return mv;
    }
    
     @RequestMapping(method = RequestMethod.POST, value = "AddInciCon.htm")
    public ModelAndView formAddPost(
            @RequestParam("documento_soporte") MultipartFile file,
            @ModelAttribute("InciConductores") InciCon ic,
            BindingResult result,
            SessionStatus status,
            HttpServletRequest request
    ) {

        ModelAndView mv;
        System.out.println("addincicon post");
        String path = request.getServletContext().getRealPath("/PUBLIC") + "/resources/documents/conductores";
        System.out.println(path);
        String sql = "Insert into incidencias_conductores(fecha_inicio,fecha_fin,observacion,costo,"
                + "documento_soporte ,conductor,tipo_falla)"
                + " VALUES (?,?,?,?,?,?,?)";
        this.jdbc.update(sql, ic.getFecha_inicio(), ic.getFecha_fin(), ic.getObservacion(),ic.getCosto(),
                "/PUBLIC/resources/documents/conductores" + ic.getConductor()+ ic.getFecha_inicio() + "." + file.getContentType().split("/")[1],
                Integer.parseInt(ic.getConductor()), ic.getTipo_falla()
        );

        InputStream is;
        try {
            System.out.println("subir archivo");

            is = file.getInputStream();

            File f = new File(path + ic.getConductor() + ic.getFecha_inicio() + "." + file.getContentType().split("/")[1]);
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
                            System.out.println("vista inciveh");

        mv = new ModelAndView("redirect:/inciCon.htm?id="+Integer.parseInt(ic.getConductor()));

        return mv;
    }
    
    
    
    
    
    
     /**
     * retorna el objeto conductor buscado por su cedula
     *
     * @param cedula cedula del conductor a buscar
     * @return objeto conductor asociado con la cedula buscada
     */
    private InciCon SelectInciConductor(String id) {
        final InciCon Incicon = new InciCon();
        String query = "SELECT * FROM incidencias_conductores WHERE id='" + id + "';";
        return (InciCon) jdbc.query(query, new ResultSetExtractor<InciCon>() {
            @Override
            public InciCon extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    Incicon.setId(Integer.parseInt(rs.getString("id")));
                    Incicon.setFecha_inicio(rs.getString("fecha_inicio"));
                    Incicon.setFecha_fin(rs.getString("fecha_fin"));
                    Incicon.setObservacion(rs.getString("observacion"));
                    Incicon.setCosto(Double.parseDouble(rs.getString("costo")));
                    Incicon.setDocumento_soporte(rs.getString("documento_soporte"));
                    Incicon.setTipo_falla(rs.getString("tipo_falla"));

                }
                return Incicon;
            }
        });
    }
    
    
       //cargar vista consultar incidencia vehiculo
    @RequestMapping(method = RequestMethod.GET, value = "ConsultInciCon.htm")
    public ModelAndView formconsulta(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String id = request.getParameter("id");
        InciCon ic = this.SelectInciConductor(id);
        mv.setViewName("ConsultInciCon");
        mv.addObject("datos", ic);
        return mv;
    }
    

    @RequestMapping("DeleteInciCon.htm")
    public ModelAndView formDelete(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String sql = "DELETE FROM incidencias_conductores WHERE id=?";
        this.jdbc.update(sql, id);
        return new ModelAndView("redirect:/conductor.htm");
    }
    
    
      //EDITAR incidencia CONDUCTOR
    @RequestMapping(method = RequestMethod.GET, value = "EditInciCon.htm")
    public ModelAndView formeEdit(HttpServletRequest request) {

        ModelAndView mv = new ModelAndView();
        String id = request.getParameter("id");
        InciCon c = this.SelectInciConductor(id);
        mv.setViewName("EditInciCon");
        mv.addObject("inciConductores", new InciCon(Integer.parseInt(id), c.getFecha_inicio(), c.getFecha_fin(), c.getObservacion(), 
                c.getCosto(), c.getDocumento_soporte(), c.getTipo_falla(), c.getConductor()));
        return mv;
    }
    //editar post
    @RequestMapping(method = RequestMethod.POST, value = "EditInciCon.htm")
    public ModelAndView form(
            @RequestParam("documento_soporte") MultipartFile file,
            @ModelAttribute("inciConductores") InciCon ic,
            BindingResult result,
            SessionStatus status,
            HttpServletRequest request
    ) {

        ModelAndView mv;
        String path = request.getServletContext().getRealPath("/PUBLIC") + "/resources/documents/conductores";
        mv = new ModelAndView();
        String id = request.getParameter("id");
        Integer.parseInt(id);
        System.out.println("mi id else " + id);

        String sql = "UPDATE incidencias_conductores SET "
                + "fecha_inicio=? ,"
                + "fecha_fin=?,"
                + "observacion=?,"
                + "costo=?,"
                + "documento_soporte=?,"
                + "tipo_falla=?"
                + " WHERE id=?;";
       this.jdbc.update(sql, ic.getFecha_inicio(), ic.getFecha_fin(), ic.getObservacion(),ic.getCosto(),
                "/PUBLIC/resources/documents/conductores" + ic.getConductor()+ ic.getFecha_inicio() + "." + file.getContentType().split("/")[1],
                 ic.getTipo_falla(),Integer.parseInt(id));

         InputStream is;
        try {
            System.out.println("subir archivo");

            is = file.getInputStream();

            File f = new File(path + ic.getConductor() + ic.getFecha_inicio() + "." + file.getContentType().split("/")[1]);
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
                            System.out.println("vista inciveh");

        mv = new ModelAndView("redirect:/conductor.htm");

        return mv;
    }
    
}
