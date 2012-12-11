/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.clasesDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import net.ausiasmarch.dataBase.*;
import net.ausiasmarch.pojos.PojoCuestionario;

/**
 *
 * @author al036049
 */
public class CuestionarioDao {

    Mysql mysql = null;

    public CuestionarioDao(String url, String userConexion, String passwdConexion) throws Exception {


        try {
            mysql = new Mysql();
            mysql.AbrirConexion(url, userConexion, passwdConexion);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("No se ha podido establecer la conexion" + e.getMessage());
        }
    }

    public ArrayList rellenaListaCuestionario(int pagina, int regPorPagina, String ordena) throws Exception {

        PojoCuestionario pj;
        ArrayList cuestionarios = new ArrayList();
        ResultSet rs=null; 

        try {
            rs = mysql.GetPage("cuestionario", "activo != \"0\"",ordena,regPorPagina,pagina);
            do {
                pj = new PojoCuestionario();
                pj.setId(rs.getInt("id"));
                pj.setDescripcion(rs.getString("descripcion"));
                pj.setFecha(rs.getDate("fecha"));
                pj.setEvaluacion(rs.getInt("evaluacion"));
                cuestionarios.add(pj);
            } while (rs.next());
            return cuestionarios;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("No se ha podido rellenar la lista de cuestionarios" + e.getMessage());
        }finally{
            rs.close();
        }
    }

    public int numeroPaginas(int regPorPag) throws Exception {
        try {
            int numPags = mysql.GetPages("cuestionario", "activo != \"0\"", null, regPorPag);
            return numPags;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("No se ha podido rellenar la lista de cuestionarios" + e.getMessage());
        }
    }

public PojoCuestionario rellenaCuestionario(int id) throws Exception {

        PojoCuestionario pj;

        try {
            ResultSet rs = mysql.GetResultSet("cuestionario", "id=\"" + id + "\"");

            pj = new PojoCuestionario();
            pj.setId(rs.getInt("id"));
            pj.setDescripcion(rs.getString("descripcion"));
            PreguntaDao pd = new PreguntaDao(rs.getInt("id"));
            pj.setPreguntas(pd.rellenaPreguntas(mysql));

            return pj;

        } catch (Exception e) {
            //e.printStackTrace();
            throw new Exception("No se ha podido rellenar el cuestionario" + e.getMessage());
        }
    }
}
