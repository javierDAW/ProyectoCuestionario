/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.clasesDao;

import java.sql.ResultSet;
import java.text.NumberFormat;
import net.ausiasmarch.dataBase.Mysql;
import net.ausiasmarch.pojos.PojoCuestionario;
import net.ausiasmarch.pojos.PojoNota;
import net.ausiasmarch.pojos.PojoUsuario;

/**
 *
 * @author al036049
 */
public class NotaDao {

    Mysql mysql = null;

    public NotaDao(String url, String userConexion, String passwdConexion) throws Exception {


        try {
            mysql = new Mysql();
            mysql.AbrirConexion(url, userConexion, passwdConexion);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("No se ha podido establecer la conexion" + e.getMessage());
        }
    }

    public PojoNota CalculaNota(PojoUsuario pojoUsuario, PojoCuestionario pojoCuestionario) throws Exception {
        
        int idUsuario = pojoUsuario.getId();
        int idCuestionario = pojoCuestionario.getId();
        float nota;
        float numPreguntas=0;
        float correctas = 0;
        ResultSet rsContestacion = null;
        ResultSet rsOpcion = null;
        String tablas = "contestacion c, pregunta pr";
        String where = "pr.id_cuestionario=\"" + idCuestionario + "\" and pr.id=c.id_pregunta"
                + " and c.id_usuario=\""+ idUsuario + "\"";
        
        
        String tablas2 ="opcion o, pregunta pr, cuestionario c";
        
        String where2 = "c.id=\"" + idCuestionario + "\" and c.id=pr.id_cuestionario and pr.id=o.id_pregunta";



        try {
            rsContestacion = mysql.GetResultSet(tablas, where);
            rsOpcion = mysql.GetResultSet(tablas2, where2);
            
            if(rsContestacion != null && rsOpcion!= null){
                rsOpcion.first();
                rsContestacion.first();
                do{
                    numPreguntas++;
                    int idPregunta = rsOpcion.getInt("o.id_pregunta");
                    boolean opcionCorrecta = rsOpcion.getBoolean("o.correcta"); 
                    
                    do{
                        int idOpcion = rsContestacion.getInt("c.id_pregunta");
                        if(idOpcion == idPregunta){
                            int idOpcionCorrecta = rsOpcion.getInt("o.id");
                            int idOpcionRespondida = rsContestacion.getInt("c.id_opcion");
                            if(idOpcionRespondida == idOpcionCorrecta && opcionCorrecta){
                                correctas++;
                            }
                        }
                        
                    }while(rsContestacion.next());
                   rsContestacion.first(); 
                    
                }while(rsOpcion.next());
            }
            
            numPreguntas = mysql.GetCount("pregunta", "id_cuestionario=\""+idCuestionario+"\"");
            nota = ((correctas*10)/numPreguntas);
//            NumberFormat format = NumberFormat.getCurrencyInstance();
//            nota = Float.parseFloat(format.format(nota));
            System.out.println("La nota es de "+nota);
            PojoNota pojoNota = new PojoNota();
            pojoNota.setNota(nota);
            pojoNota.setIdAlumno(pojoUsuario.getId());
            pojoNota.setNombre(pojoUsuario.getNombre()+" "+pojoUsuario.getApe1()+" "+pojoUsuario.getApe2());
            return pojoNota;
            
        } catch (Exception e) {
             throw new Exception("Fallo al calcular la nota" + e.getMessage());
        }finally{
            
//            rsContestacion.close();
//            rsOpcion.close();
            mysql.CerrarConexion();
        }
        
    }
}
