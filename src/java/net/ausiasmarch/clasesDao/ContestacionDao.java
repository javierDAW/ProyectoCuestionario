/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.clasesDao;

import java.sql.SQLException;
import net.ausiasmarch.dataBase.Mysql;
import net.ausiasmarch.pojos.PojoContestacion;
import net.ausiasmarch.pojos.PojoCuestionario;
import net.ausiasmarch.pojos.PojoUsuario;

/**
 *
 * @author al036049
 */
public class ContestacionDao {

    String tabla = "contestacion";
    Mysql mysql = null;

    public ContestacionDao(String url, String userConexion, String passwdConexion) throws Exception {

        try {
            mysql = new Mysql();
            mysql.AbrirConexion(url, userConexion, passwdConexion);

        } catch (Exception e) {
            //e.printStackTrace();
            throw new Exception("No se ha podido establecer la conexion" + e.getMessage());
        }
    }

    public boolean existeRegistro(PojoUsuario pojoUsuario, PojoCuestionario pojoCuestionario) throws Exception {

        int idUsuario = pojoUsuario.getId();
        int idCuestionario = pojoCuestionario.getId();
        String tablas = "contestacion c, pregunta pr";
        String where = "pr.id_cuestionario=\"" + idCuestionario + "\" and pr.id=c.id_pregunta"
                + " and c.id_usuario=\"" + idUsuario + "\"";
        try {

            if (mysql.GetOne(tablas, where) != 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            //e.printStackTrace();
            throw new Exception("No se ha podido rellenar el cuestionario" + e.getMessage());
        }
    }

    public void insertaContestacion(PojoContestacion pc) throws Exception {
        String nombres = "id,id_usuario,id_pregunta,id_opcion";
        String valores = "'" + 0 + "',";
        valores += "'" + pc.getIdAlumno() + "',";
        valores += "'" + pc.getIdPregunta() + "',";
        valores += "'" + pc.getContestacion() + "'";

        try {
            mysql.InitTrans();
            mysql.insertOne(tabla, nombres, valores);
            mysql.CommitTrans();

        } catch (Exception e) {
            mysql.RollbackTrans();
            throw new Exception("Error al insertar en la base de datos." + e.getMessage());
        }finally{
            
        }
    }
}
