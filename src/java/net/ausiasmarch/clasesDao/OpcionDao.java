/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.clasesDao;

import java.sql.ResultSet;
import java.util.ArrayList;
import net.ausiasmarch.dataBase.Mysql;
import net.ausiasmarch.pojos.PojoCuestionario;
import net.ausiasmarch.pojos.PojoOpcion;
import net.ausiasmarch.pojos.PojoPregunta;

/**
 *
 * @author al036049
 */
public class OpcionDao {

    int idPregunta;
    Mysql mysql = null;

    public OpcionDao(int id) {

        this.idPregunta = id;
    }

    public ArrayList rellenaOpciones(Mysql mysql) throws Exception {
        
        this.mysql = mysql;

        try {
            ResultSet rs = mysql.GetResultSet("opcion", "id_pregunta=\"" + idPregunta + "\"");
            PojoOpcion po;
            ArrayList opciones = new ArrayList();
            do {
                po = new PojoOpcion();
                
                po.setId(rs.getInt("id"));
                po.setDescripcion(rs.getString("descripcion"));
                po.setCorrecta(rs.getInt("correcta"));
                System.out.println(rs.getString("descripcion"));
                opciones.add(po);
            } while (rs.next());

            return opciones;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("No se ha podido rellenar el cuestionario" + e.getMessage());
        }
    }
}
