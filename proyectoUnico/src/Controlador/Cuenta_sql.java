/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Entidad.Cuenta;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Byrich
 */
public class Cuenta_sql {
    
    public int agregarCuenta(String nombre, int tipoCuenta){
        conexion global = conexion.getInstance();
        try {
            // llamada a la funcion
            CallableStatement cstmt = global.conn.prepareCall("{ ? = call nuevaCuenta(?,?)}");
            // parametros de entrada
            cstmt.setString(2, nombre);
            cstmt.setInt(3, tipoCuenta);
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
    
    public int buscarCliente(String dpi){
        conexion global = conexion.getInstance();
        try {
            // llamada a la funcion
            CallableStatement cstmt = global.conn.prepareCall("{ ? = call buscarCliente(?)}");
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
            System.out.println(ex);
            return -2;
        }
    }
    
    public int depositar(String noCuenta,String operador,String cantidad){
        conexion global = conexion.getInstance();
        try {
            // llamada a la funcion
            CallableStatement cstmt = global.conn.prepareCall("{ ? = call depositarEfectivo(?,?,?)}");
            // parametros de entrada
            cstmt.setString(2, noCuenta);
            cstmt.setString(3, operador);
            cstmt.setString(4, cantidad);
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
    
    
    public int bloquearCuenta(String numCuenta){
        conexion global = conexion.getInstance();
        try {
            // llamada a la funcion
            CallableStatement cstmt = global.conn.prepareCall("{ ? = call bloquearCuenta(?)}");
            // parametros de entrada
            cstmt.setString(2, numCuenta);
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
    
    
    public int cancelCuenta(String numCuenta){
        conexion global = conexion.getInstance();
        try {
            // llamada a la funcion
            CallableStatement cstmt = global.conn.prepareCall("{ ? = call deletCuenta(?)}");
            // parametros de entrada
            cstmt.setString(2, numCuenta);
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
    
    public Object[] getCuenta(String dpi, int tipoCuenta){
        conexion global = conexion.getInstance();
        DefaultTableModel tabla = new DefaultTableModel();
        Cuenta cuent = null;
        Object retorno[] = {cuent,tabla};
        tabla.addColumn("No. Cuenta");
        tabla.addColumn("Saldo");
        try {
            // llamada a la funcion
            CallableStatement cstmt = global.conn.prepareCall("{ ? = call getCuenta(?,?)}");
            // parametros de entrada
            cstmt.setString(2, dpi);
            cstmt.setInt(3, tipoCuenta);
            //retorno 
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            //ejecutamos...
            cstmt.execute();
            //capturamos resultado (1 logro registrar, -1 el usuario ya existe)
            ResultSet rs = ((OracleCallableStatement)cstmt).getCursor(1);
            //llenamos defaulttable
            String datos[] = new String[2];
            while(rs.next()){
                
                retorno[0] = new Cuenta(rs.getInt(1),rs.getDouble(2),rs.getLong(3));
                datos[0]= rs.getInt(1) +"";
                datos[1]=  rs.getDouble(2)+"";
                tabla.addRow(datos);
            }
        } catch (SQLException ex) {
            // error dentro de la DB
            System.out.println(ex);
        }
        return retorno;
    }
    
    public int pedirChequera(String numCuenta){
        conexion global = conexion.getInstance();
        try {
            // llamada a la funcion
            CallableStatement cstmt = global.conn.prepareCall("{ ? = call pedirChequera(?)}");
            // parametros de entrada
            cstmt.setString(2, numCuenta);
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
            return -3;
        }
    }
    
    public int pagarCheque(String numCuenta,String numcheque,String idOperador,String canitdad){
        conexion global = conexion.getInstance();
        try {
            // llamada a la funcion
            CallableStatement cstmt = global.conn.prepareCall("{ ? = call retirarCheque(?,?,?,?)}");
            // parametros de entrada
            cstmt.setString(2, numCuenta);
            cstmt.setString(3, idOperador);
            cstmt.setString(4, numcheque);
            cstmt.setString(5, canitdad);
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
            return -1;
        }
    }
    
    
    public int transferencia(String numCuenta,String numCuenta2,String idOperador,String canitdad){
        conexion global = conexion.getInstance();
        try {
            // llamada a la funcion
            CallableStatement cstmt = global.conn.prepareCall("{ ? = call trasnferencia(?,?,?,?)}");
            // parametros de entrada
            cstmt.setString(2, numCuenta);
            cstmt.setString(3, numCuenta2);
            cstmt.setString(4, idOperador);
            cstmt.setString(5, canitdad);
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
            return -1;
        }
    }
    
    public int bloquear(String numCuenta,String numCheque,String tipo){
        conexion global = conexion.getInstance();
        try {
            // llamada a la funcion
            CallableStatement cstmt = global.conn.prepareCall("{ ? = call bloqueoCheque(?,?,?)}");
            // parametros de entrada
            cstmt.setString(2, numCuenta);
            cstmt.setString(3, numCheque);
            cstmt.setString(4, tipo);
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
            return -1;
        }
    }
}
