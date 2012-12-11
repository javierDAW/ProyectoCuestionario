/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.clasesDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import net.ausiasmarch.dataBase.Mysql;
import net.ausiasmarch.pojos.PojoCuestionario;
import net.ausiasmarch.pojos.PojoPregunta;

/**
 *
 * @author al036049
 */
public class PreguntaDao {

    int id;
    

    public PreguntaDao(int id) {

        this.id = id;

    }

    public ArrayList rellenaPreguntas(Mysql mysql) throws Exception {

        ResultSet rs=null;
       try {

            rs = mysql.GetResultSet("pregunta", "id_cuestionario=\"" + id + "\"");
            PojoPregunta pp;
            ArrayList preguntas = new ArrayList();
            do {
                pp = new PojoPregunta();
                pp.setId(rs.getInt("id"));
                pp.setDescripcion(rs.getString("descripcion"));
                pp.setId_cuestionaro(id);
                System.out.println(rs.getString("descripcion"));
                OpcionDao od = new OpcionDao(rs.getInt("id"));
                pp.setOpciones(od.rellenaOpciones(mysql));
                preguntas.add(pp);

            } while (rs.next());
            return preguntas;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("No se han podido rellenar las preguntas del cuestionario" + e.getMessage());
        }finally{
           rs.close();
       }
    }
}
