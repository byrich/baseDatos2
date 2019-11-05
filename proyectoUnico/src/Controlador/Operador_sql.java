/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Entidad.Operador;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Byrich
 */
public class Operador_sql {
    
    public int agregarOperador(String nombre, String pass, int agencia, int terminal, int rol){
        conexion global = conexion.getInstance();
        try {
            // llamada a la funcion
            CallableStatement cstmt = global.conn.prepareCall("{ ? = call nuevoOperador(?,?,?,?,?)}");
            // parametros de entrada
            cstmt.setString(2, nombre);
            cstmt.setString(3, pass);
            cstmt.setInt(4, agencia);
            cstmt.setInt(5, terminal);
            cstmt.setInt(6, rol);
            //retorno 
            cstmt.registerOutParameter(1, OracleTypes.INTEGER);
            //ejecutamos...
            cstmt.execute();
            //capturamos resultado (1 logro registrar, -1 el usuario ya existe)
            int respuesta = ((OracleCallableStatement)cstmt).getInt(1);
            return respuesta;
        } catch (SQLException ex) {
            // error dentro de la DB
            return -2;
        }
    }
    
    public Object[] getOperadores(){
        conexion global = conexion.getInstance();
        ArrayList<Operador> cli = new ArrayList();
        DefaultTableModel tabla = new DefaultTableModel();
        Object retorno[] = {cli,tabla};
        tabla.addColumn("Nombre");
        tabla.addColumn("Agencia");
        tabla.addColumn("Rol");
        try {
            // llamada a la funcion
            CallableStatement cstmt = global.conn.prepareCall("{ ? = call getOperadores}");
            //retorno 
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            //ejecutamos...
            cstmt.execute();
            //capturamos resultado (1 logro registrar, -1 el usuario ya existe)
            ResultSet rs = ((OracleCallableStatement)cstmt).getCursor(1);
            //llenamos defaulttable
            String datos[] = new String[3];
            System.out.println(rs);
            while(rs.next()){
                Operador clie = new Operador(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5));
                cli.add(clie);
                datos[0]= clie.nombre;
                datos[1]= rs.getString(6);
                datos[2]= rs.getString(7);
                tabla.addRow(datos);
            }
        } catch (SQLException ex) {
            // error dentro de la DB
        }
        return retorno;
    }
       
    public ResultSet getAgencias(){
        conexion global = conexion.getInstance();
        ResultSet rs = null;
        try {
            // llamada a la funcion
            CallableStatement cstmt = global.conn.prepareCall("{ ? = call getAgencias}");
            //retorno 
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            //ejecutamos...
            cstmt.execute();
            //capturamos resultado (1 logro registrar, -1 el usuario ya existe)
            rs = ((OracleCallableStatement)cstmt).getCursor(1);
            //System.out.println(rs);
        } catch (SQLException ex) {
            // error dentro de la DB
        }
        System.out.println(rs);
        return rs;
    }
      
    public ResultSet getRoles(){
        conexion global = conexion.getInstance();
        ResultSet rs = null;
        try {
            // llamada a la funcion
            CallableStatement cstmt = global.conn.prepareCall("{ ? = call getRoles}");
            //retorno 
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            //ejecutamos...
            cstmt.execute();
            //capturamos resultado (1 logro registrar, -1 el usuario ya existe)
            rs = ((OracleCallableStatement)cstmt).getCursor(1);
        } catch (SQLException ex) {
            // error dentro de la DB
        }
        return rs;
    }
   
    public int delOperador(String dpi){
        conexion global = conexion.getInstance();
        try {
            // llamada a la funcion
            CallableStatement cstmt = global.conn.prepareCall("{ ? = call deletOperador(?)}");
            // parametros de entrada
            cstmt.setString(2, dpi);
            //retorno 
            cstmt.registerOutParameter(1, OracleTypes.INTEGER);
            //ejecutamos...
            cstmt.execute();
            //capturamos resultado (1 logro registrar, -1 el usuario ya existe)
            int respuesta = ((OracleCallableStatement)cstmt).getInt(1);
            return respuesta;
        } catch (SQLException ex) {
            // error dentro de la DB
            return -2;
        }
    }
    
    public int editOperador(String dpi, String nombre, String pass, int agencia, int rol){
        conexion global = conexion.getInstance();
        try {
            // llamada a la funcion
            CallableStatement cstmt = global.conn.prepareCall("{ ? = call editOperador(?,?,?,?,?)}");
            // parametros de entrada
            cstmt.setString(2, dpi);
            cstmt.setString(3, nombre);
            cstmt.setString(4, pass);
            cstmt.setInt(5, agencia);
            cstmt.setInt(6, rol);
            //retorno 
            cstmt.registerOutParameter(1, OracleTypes.INTEGER);
            //ejecutamos...
            cstmt.execute();
            //capturamos resultado (1 logro registrar, -1 el usuario ya existe)
            int respuesta = ((OracleCallableStatement)cstmt).getInt(1);
            return respuesta;
        } catch (SQLException ex) {
            // error dentro de la DB
            System.out.println(ex);
            return -2;
        }
    }
}
