/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;

import Controlador.Compensacion_sql;

/**
 *
 * @author DELL
 */
public class Lote_Cheque {
    public int bancoViene;
    public String referencia;
    public int cuenta;
    public int no_cheque;
    public Float monto;
    public int idLote;
    public String estado;
     public Lote_Cheque(int banco, String ref, int cuenta, int nocheque, Float monto, int idLote){
        this.bancoViene = banco;
        this.referencia = ref;
        this.cuenta = cuenta;
        this.no_cheque = nocheque;
        this.monto = monto;
        this.idLote = idLote;
    }
     
     public int compensar(int n, Compensacion_sql api){
         return api.guardarLoteCheque(bancoViene,referencia,cuenta,no_cheque,monto,idLote);
     }
}
