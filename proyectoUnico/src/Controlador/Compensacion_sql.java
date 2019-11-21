/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Entidad.Lote_Cheque;
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
 * @author DELL
 */
public class Compensacion_sql {
    public int guardarLoteCheque(int bancoViene, String referencia, int cuenta, int no_cheque, float monto, int id_lote){
        conexion global = conexion.getInstance();
        try {
            // llamada a la funcion
            CallableStatement cstmt = global.conn.prepareCall("{ ? = call nuevoLote_Cheque(?,?,?,?,?,?)}");
            // parametros de entrada
            cstmt.setInt(2, bancoViene);
            cstmt.setString(3, referencia);
            cstmt.setInt(4, cuenta);
            cstmt.setInt(5, no_cheque);
            cstmt.setFloat(6, monto);
            cstmt.setInt(7, id_lote);

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
    
    public float getMonto(int bancoViene, int id_lote){
        conexion global = conexion.getInstance();
        try {
            // llamada a la funcion
            CallableStatement cstmt = global.conn.prepareCall("{ ? = call montoLote(?,?)}");
            // parametros de entrada
            cstmt.setInt(2, bancoViene);
            cstmt.setInt(3, id_lote);

            //retorno 
            cstmt.registerOutParameter(1, OracleTypes.INTEGER);
            //ejecutamos...
            cstmt.execute();
            //capturamos resultado (1 logro registrar, -1 el usuario ya existe)
            float respuesta = ((OracleCallableStatement)cstmt).getFloat(1);
            return respuesta;
        } catch (SQLException ex) {
            // error dentro de la DB
            return -2;
        }
    }
    
        public int getCantidad(int bancoViene, int id_lote){
        conexion global = conexion.getInstance();
        try {
            // llamada a la funcion
            CallableStatement cstmt = global.conn.prepareCall("{ ? = call cantidadCheques(?,?)}");
            // parametros de entrada
            cstmt.setInt(2, bancoViene);
            cstmt.setInt(3, id_lote);

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
        
    public int grabarTransacciones(int idLote, int idbanco){
        System.out.println("---* Lote: "+idLote+" idBanco: "+idbanco);
        conexion global = conexion.getInstance();
        ArrayList<Lote_Cheque> cli = new ArrayList();
        try {
            // llamada a la funcion
            CallableStatement cstmt = global.conn.prepareCall("{call Grabacion_Cheques(?,?)}");
            cstmt.setInt("lote", idLote);
            cstmt.setInt("idbanco", idbanco);
            //retorno 
           // cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            //ejecutamos...
            cstmt.executeUpdate();
            //capturamos resultado (1 logro registrar, -1 el usuario ya existe)
           // ResultSet rs = ((OracleCallableStatement)cstmt).getCursor(1);
            //llenamos defaulttable
//            String datos[] = new String[3];
//            System.out.println(rs);
//            while(rs.next()){
//                //System.out.println(rs.getInt(2));
//               Lote_Cheque clie = new Lote_Cheque(rs.getInt(1),rs.getString(2), rs.getInt(3), rs.getInt(4),rs.getFloat(5), rs.getInt(6));
//               cli.add(clie);
//                System.out.println("Cheque");
//              //  datos[1]= rs.getString(6);
//                //datos[2]= rs.getString(7);
//                //tabla.addRow(datos);
//            }
        } catch (SQLException ex) {
            // error dentro de la DB
        }
        return 2500;
    }
    
    public ArrayList<Lote_Cheque> getChequesLote(int idBanco, int idLote){
        conexion global = conexion.getInstance();
        ArrayList<Lote_Cheque> cli = new ArrayList();
        try {
            // llamada a la funcion
            CallableStatement cstmt = global.conn.prepareCall("{ ? = call getChequesLote(?,?)}");
            cstmt.setInt(2, idBanco);
            cstmt.setInt(3, idLote);
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
                //System.out.println(rs.getInt(2));
               Lote_Cheque clie = new Lote_Cheque(rs.getInt(1),rs.getString(2), rs.getInt(3), rs.getInt(4),rs.getFloat(5), rs.getInt(6));
               clie.estado = rs.getString(7);
               cli.add(clie);
                System.out.println("Cheque");
              //  datos[1]= rs.getString(6);
                //datos[2]= rs.getString(7);
                //tabla.addRow(datos);
            }
        } catch (SQLException ex) {
            // error dentro de la DB
        }
        return cli;
    }
    
}
