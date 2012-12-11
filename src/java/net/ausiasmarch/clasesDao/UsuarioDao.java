/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.clasesDao;

import java.sql.Connection;
import java.sql.ResultSet;
import net.ausiasmarch.dataBase.Mysql;
import net.ausiasmarch.pojos.PojoUsuario;

/**
 *
 * @author al036049
 */
public class UsuarioDao {
    
    String tablaUsuarios = "usuario";
    Mysql mysql = null;
    
    
    public UsuarioDao(String url,String userConexion, String passwdConexion) throws Exception {

        try {
            mysql = new Mysql();
            mysql.AbrirConexion(url, userConexion, passwdConexion);
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("No se ha podido establecer la conexion" + e.getMessage());
        }
    }
    
     public PojoUsuario isValidUser(String login, String password) throws Exception {

        String where = "login ='" + login + "' and password='" + password + "'";
        PojoUsuario pu = null;
        ResultSet rs = null;

        try {
            
            int id = mysql.GetOne(tablaUsuarios, where);
            if (id != 0) {
                pu = new PojoUsuario();
                rs = mysql.GetResultSet(tablaUsuarios, where);
                pu.setId(rs.getInt("id"));
                pu.setNombre(rs.getString("nombre"));
                pu.setApe1(rs.getString("ape1"));
                pu.setApe2(rs.getString("ape2"));
                pu.setTelefono(rs.getString("telefono"));
                pu.setEmail(rs.getString("email"));
                pu.setIdTipoUsuario(rs.getInt("id_tipo_usuario"));
                pu.setLogin(rs.getString("login"));
                pu.setPassword(rs.getString("password"));
                
                return pu;
            } else {
                return pu;
            }
        } catch (Exception e) {
           
            throw new Exception("No se ha podido validar el usuario" + e.getMessage());
        }finally{
            if(rs != null){
            rs.close();
            }
        }
    }
}
