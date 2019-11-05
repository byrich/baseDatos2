/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Entidad.Agencia;
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
public class Agencia_sql {
    
    public int agregarAgencia(String nombre){
        conexion global = conexion.getInstance();
        try {
            // llamada a la funcion
            CallableStatement cstmt = global.conn.prepareCall("{ ? = call nuevaAgencia(?)}");
            // parametros de entrada
            cstmt.setString(2, nombre);
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
    
    public Object[] getAgencias(){
        conexion global = conexion.getInstance();
        ArrayList <Agencia> ag = new ArrayList();
        DefaultTableModel tabla = new DefaultTableModel();
        tabla.addColumn("Nombre");
        Object retorno[] = {ag,tabla};
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
            String datos[] = new String[1];
            while(rs.next()){
                Agencia clie = new Agencia(rs.getInt(1),rs.getString(2),rs.getInt(3));
                ag.add(clie);
                datos[0]= clie.nombre;
                tabla.addRow(datos);
            }
        } catch (SQLException ex) {
            // error dentro de la DB
            System.out.println(ex);
        }
        System.out.println(ag);
        return retorno;
    }
      
    public int delAgencia(String dpi){
        conexion global = conexion.getInstance();
        try {
            // llamada a la funcion
            CallableStatement cstmt = global.conn.prepareCall("{ ? = call deletAgencia(?)}");
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
    
    public int editAgencia(String dpi, String nombre){
        conexion global = conexion.getInstance();
        try {
            // llamada a la funcion
            CallableStatement cstmt = global.conn.prepareCall("{ ? = call editAgencia(?,?)}");
            // parametros de entrada
            cstmt.setString(2, dpi);
            cstmt.setString(3, nombre);
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
