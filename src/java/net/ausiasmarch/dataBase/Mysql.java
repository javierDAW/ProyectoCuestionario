package net.ausiasmarch.dataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Mysql {

    protected Connection con = null;

    public Connection AbrirConexion(String urlOdbc, String usuario, String password) throws Exception {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //String urlOdbc = "jdbc:mysql://localhost:3306/Biblioteca";
            con = (java.sql.DriverManager.getConnection(urlOdbc, usuario, password));
            this.con = con;
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            //return false;
            throw new Exception("No se ha podido establecer la conexion" + e.getMessage());
        }
    }

    public void CerrarConexion() throws Exception {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("No se ha podido cerrar la conexion" + e.getMessage());
        }
    }

    public void InitTrans() throws Exception {
        try {
            if (con != null) {
                con.setAutoCommit(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("No se ha podido iniciar la transaccion" + e.getMessage());
        }
    }

    public void CommitTrans() throws Exception {
        try {
            if (con != null) {
                con.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("No se ha podido realizar el commit" + e.getMessage());
        }
    }

    public void RollbackTrans() throws Exception {
        try {
            if (con != null) {
                con.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("No se ha podido realizar el rollback" + e.getMessage());
        }
    }

    public void DeleteOne(String tabla, String id) throws Exception {
        try {
            if (con != null) {
                Statement statement = null;
                statement = con.createStatement();
                String deleteQuery = "DELETE FROM " + tabla + " WHERE id='" + id + "'";
                int result = statement.executeUpdate(deleteQuery);
                if (result == 1) {
                    System.out.println("borrado correcto!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("No se ha podido borrar" + e.getMessage());
        }
    }

    public void insertOne2(String tabla, List<String> nombreCampo, List<String> valorCampo) throws Exception {
        try {
            if (con != null) {
                Statement statement = null;
                statement = con.createStatement();
                String nombres = "";
                String valores = "";

                for (int i = 0; i < nombreCampo.size(); i++) {
                    nombres += nombreCampo.get(i) + ",";
                    valores += "'" + valorCampo.get(i) + "',";
                }
                nombres = nombres.substring(0, nombres.length() - 1);
                valores = valores.substring(0, valores.length() - 1);
                String insertQuery = "INSERT INTO " + tabla + " ( " + nombres + " ) VALUES( " + valores + ")";
                int result = statement.executeUpdate(insertQuery);
                if (result == 1) {
                    System.out.println("insertado correcto!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("No se ha podido actualizar" + e.getMessage());
        }
    }

    public void insertOne(String tabla, String nombres, String valores) throws Exception {
        try {
            if (con != null) {
                Statement statement = null;
                statement = con.createStatement();
                
               
                String insertQuery = "INSERT INTO " + tabla + " ( " + nombres + " ) VALUES( " + valores + ")";
                int result = statement.executeUpdate(insertQuery);
                if (result == 1) {
                    System.out.println("insertado correcto!");
                }else{
                    System.out.println("insertado fallido!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("No se ha podido actualizar" + e.getMessage());
        }
    }
    public void UpdateOne(String tabla, String nombreCampo, String valor, String id) throws Exception {
        try {
            if (con != null) {
                Statement statement = null;
                statement = con.createStatement();
                String deleteQuery = "UPDATE " + tabla + " SET " + nombreCampo + " ='" + valor + "' WHERE id='" + id + "'";
                int result = statement.executeUpdate(deleteQuery);
                if (result == 1) {
                    System.out.println("actualizado correcto!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("No se ha podido actualizar" + e.getMessage());
        }
    }

    public ResultSet GetResultSet(String tabla, String where) throws Exception {
        try {
            if (con != null) {
                Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                String sql = "SELECT * FROM " + tabla;
                if (where != "") {
                    sql += " WHERE 1=1 AND " + where;
                }
                System.out.println(sql);
                ResultSet rs = st.executeQuery(sql);
                if (rs.next()) {
                    return rs;
                }
                return null;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("No se ha podido actualizar" + e.getMessage());

        }
    }
    
    public int GetCount(String tabla, String where) throws Exception {
        int num=0;
        try {
            if (con != null) {
                Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                String sql = "SELECT * FROM " + tabla;
                if (where != "") {
                    sql += " WHERE " + where;
                }
                ResultSet rs = st.executeQuery(sql);
                if (rs.next()) {
                    rs.first();
                    do{
                        num++;
                    }while(rs.next());
                    return num;
                }
                return 0;
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("No se ha podido actualizar" + e.getMessage());

        }
    }

    public int GetOne(String tabla, String where) throws Exception {
        try {
            if (con != null) {
                Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                String sql = "SELECT * FROM " + tabla;
                if (where != "") {
                    sql += " WHERE " + where;
                }
                ResultSet rs = st.executeQuery(sql);
                if (rs.next()) {
                    return 1;
                }
                return 0;
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("No se ha podido actualizar" + e.getMessage());

        }
    }

    public int GetPages(String tabla, String where, String order, int RegPorPag) throws Exception {
        try {
            if (con != null) {
                Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                String sql = "SELECT count(*) FROM " + tabla;
                if (where != "") {
                    sql += " WHERE " + where;
                }
                if (order != "") {
                    sql += " ORDER BY " + order;
                }
                ResultSet rs = st.executeQuery(sql);
                if (rs.next()) {
                    int paginas = (rs.getInt(1) / RegPorPag);
                    if ((rs.getInt(1) % RegPorPag) != 0) {
                        paginas++;
                    }
                    return paginas;
                }
                return 0;
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("No se ha podido actualizar" + e.getMessage());

        }
    }

    public ResultSet GetPage(String tabla, String where, String order, int regPorPag, int pagina) throws Exception {

        ArrayList paginas = new ArrayList<String>();
        ResultSet rs = null;
        try {
            if (con != null) {

                Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                String sql = "SELECT * FROM " + tabla;
                if (where != "") {
                    sql += " WHERE " + where;
                }
                if (order != "") {
                    sql += " ORDER BY " + order;
                }
                int offset = Math.max((pagina - 1) * regPorPag, 0);
                sql += " LIMIT " + offset + "," + regPorPag;
                System.out.println(sql);
                rs = st.executeQuery(sql);
                

//                while (rs.next()) {
//                    paginas.add(rs.getString(2));
//                    System.out.println("hola!!!!");
//                    System.out.println(rs.getString(2));
//                }
//                for (int i = 0; i < paginas.size(); i++) {
//                    System.out.println(paginas.get(i));
//                }
                if(rs.next()){
                return rs;
                }
            }
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("No se ha podido actualizar" + e.getMessage());

        }
    }
}
