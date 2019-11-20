/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Byrich
 */
public class conexion {
    
    private static conexion cone;
    private ResultSet rs;
    public Connection conn;
    private Statement stmt;
    
    private conexion() {
        try {
            this.conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:byrich","byRich","24490024");
            //this.conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.43.41:1522/BASESDATOS2","bd2","Basesdatos2");
            stmt=conn.createStatement();  
        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static conexion getInstance(){
        if(cone == null){
            cone = new conexion();
        }
        return cone;
    }
    
    public ResultSet runSql(String sentencia){
        try {  
            this.rs= this.stmt.executeQuery(sentencia);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return rs;
    }
    
    // login
    public ResultSet autenticar(String id, String pass){
        String sql = String.format(
                "Select rol_id_rol from operador where id_operador = %s and password = '%s' and estado = 1"
                , id
                , pass
        );
        return runSql(sql);
    }
    
    
    // registro de agencia
    public ResultSet addAgencia(String nombre){
        String sql = String.format(
               "Insert into agencia (nombre,estado) values ('%s',1)"
                , nombre
        );
        return runSql(sql);
    }
    
    public ResultSet editAgencia(String nombre,String id){
        String sql = String.format(
               "Update agencia set nombre='%s' WHERE id_agencia = %s and estado = 1"
                , nombre
                ,id
        );
        return runSql(sql);
    }
    
    // registro de cuenta
    public boolean addCuenta(String dpi){
        String sql = "select cuenta_seq.NEXTVAL from dual";
        try {
            this.rs= this.stmt.executeQuery(sql);
            if (rs.next()){
                String id = this.rs.getLong(1)+"";
                String id2 = id+"0";
                String addCuen = String.format(
                    "Insert into cuenta (cuenta,estado,cantidad) values ('%s',1,0)"
                     , id
                );
                String addCliCuen = String.format(
                    "Insert into Cliente_cuenta (Cuenta_cuenta,id_cliente_cuenta,Cliente_dpi) values (%s,%s,%s)"
                     , id
                     , id2
                     , dpi
                );
                runSql(addCuen);
                runSql(addCliCuen);
                return true;
            }
        } catch (SQLException ex) {
            return false;
        }
        return false;
    }
    
    public ResultSet getCuentas(){
        String sql = "Select * from cuenta where estado = 1";
        return runSql(sql);
    }
    // dar de baja a cliente
    public ResultSet depositar(String monto,String id){
        String sql = String.format(
               "UPDATE cuenta SET cantidad = cantidad + %s WHERE cuenta = %s and estado = 1"
                , monto
                , id
        );
        return runSql(sql);
    }
    
    // datos del cliente* falta
    public ResultSet getAgencias(){
        String sql = "Select * from agencia where estado = 1";
        return runSql(sql);
    }
    
    public ResultSet downAgencia(String dpi){
        String sql = String.format(
               "UPDATE agencia SET estado = 0 WHERE id_agencia = %s and estado = 1"
                , dpi
        );
        return runSql(sql);
    }
    
    public boolean evaluarCadena (String campo){
        String PATRON = "[A-Za-z0-9-_? ]+$";
        Pattern pattern = Pattern.compile(PATRON);
        Matcher matcher = pattern.matcher(campo);        
        return matcher.matches();
    }
    
    public boolean evaluarPass (String campo){
        String PATRON = "[A-Za-z0-9-_?]+$";
        Pattern pattern = Pattern.compile(PATRON);
        Matcher matcher = pattern.matcher(campo);        
        return matcher.matches();
    }
    
    public boolean evaluarInt (String campo){
        String PATRON = "[0-9]+$";
        Pattern pattern = Pattern.compile(PATRON);
        Matcher matcher = pattern.matcher(campo);        
        return matcher.matches();
    }
    
    public boolean evaluarFecha (String campo){
        String PATRON = "[0-9]?[0-9][/][0-9]?[0-9][/][0-9][0-9][0-9][0-9]$";
        Pattern pattern = Pattern.compile(PATRON);
        Matcher matcher = pattern.matcher(campo);        
        return matcher.matches();
    }
    
    public int addChequeTemp(int cuenta, int cheque, float monto) throws SQLException {
        Statement statement = conn.createStatement();
        int rowsAffected = statement.executeUpdate("INSERT INTO TransaccionChequeTmp " + "VALUES (" + cuenta + ", " + cheque + ", " + monto + ")");
        statement.close();
        return rowsAffected;
    }
    
    public ResultSet getTransaccion(){
        String sql = "Select * from Transaccion";
        return runSql(sql);
    }
    
}
