/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Entidad.Cliente;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
public class Cliente_sql {
    
    public int agregarCliente(String dpi,String nombre, String fecha,File foto, File firma){
        conexion global = conexion.getInstance();
        try {
            FileInputStream fotoIn = new FileInputStream(foto);
            FileInputStream firmaIn = new FileInputStream(firma);
            // llamada a la funcion
            CallableStatement cstmt = global.conn.prepareCall("{ ? = call nuevoCliente2(?,?,?,?,?)}");
            // parametros de entrada
            
            cstmt.setString(2, dpi);
            cstmt.setString(3, nombre);
            cstmt.setString(4, fecha);
            cstmt.setBinaryStream(5, fotoIn);
            cstmt.setBinaryStream(6, firmaIn);
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
        } catch (FileNotFoundException ex) {
            return -3;
        }
    }
    
    public Object[] getClientes(){
        conexion global = conexion.getInstance();
        ArrayList<Cliente> cli = new ArrayList();
        DefaultTableModel tabla = new DefaultTableModel();
        Object retorno[] = {cli,tabla};
        tabla.addColumn("Nombre");
        tabla.addColumn("Fecha nacimiento");
        try {
            // llamada a la funcion
            CallableStatement cstmt = global.conn.prepareCall("{ ? = call getClientes}");
            //retorno 
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            //ejecutamos...
            cstmt.execute();
            //capturamos resultado (1 logro registrar, -1 el usuario ya existe)
            ResultSet rs = ((OracleCallableStatement)cstmt).getCursor(1);
            //llenamos defaulttable
            String datos[] = new String[2];
            while(rs.next()){
                Cliente clie = new Cliente(rs.getLong(1),rs.getString(2),rs.getBinaryStream(3),rs.getBinaryStream(4),rs.getDate(5).toString());
                cli.add(clie);
                datos[0]= clie.nombre;
                datos[1]= clie.fec_nac;
                tabla.addRow(datos);
            }
        } catch (SQLException ex) {
            // error dentro de la DB
        }
        return retorno;
    }
    
    public Object[] getClientesB(){
        conexion global = conexion.getInstance();
        ArrayList<Cliente> cli = new ArrayList();
        DefaultTableModel tabla = new DefaultTableModel();
        Object retorno[] = {cli,tabla};
        tabla.addColumn("Nombre");
        tabla.addColumn("Fecha nacimiento");
        try {
            // llamada a la funcion
            CallableStatement cstmt = global.conn.prepareCall("{ ? = call getClientesB}");
            //retorno 
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            //ejecutamos...
            cstmt.execute();
            //capturamos resultado (1 logro registrar, -1 el usuario ya existe)
            ResultSet rs = ((OracleCallableStatement)cstmt).getCursor(1);
            //llenamos defaulttable
            String datos[] = new String[2];
            while(rs.next()){
                Cliente clie = new Cliente(rs.getLong(1),rs.getString(2),rs.getBinaryStream(3),rs.getBinaryStream(4),rs.getDate(5).toString());
                cli.add(clie);
                datos[0]= clie.nombre;
                datos[1]= clie.fec_nac;
                tabla.addRow(datos);
            }
        } catch (SQLException ex) {
            // error dentro de la DB
        }
        return retorno;
    }

    public int delCliente(String dpi){
        conexion global = conexion.getInstance();
        try {
            // llamada a la funcion
            CallableStatement cstmt = global.conn.prepareCall("{ ? = call deletCliente(?)}");
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
    
    public int upCliente(String dpi){
        conexion global = conexion.getInstance();
        try {
            // llamada a la funcion
            CallableStatement cstmt = global.conn.prepareCall("{ ? = call upCliente(?)}");
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
    
    public int editCliente(String dpi, String nombre, String fecha){
        conexion global = conexion.getInstance();
        try {
            // llamada a la funcion
            CallableStatement cstmt = global.conn.prepareCall("{ ? = call editCliente(?,?,?)}");
            // parametros de entrada
            cstmt.setString(2, dpi);
            cstmt.setString(3, nombre);
            cstmt.setString(4, fecha);
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
}
