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
    
    
    
    

}
