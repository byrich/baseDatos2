create or replace FUNCTION nuevoLote_Cheque
    (
    bancoViene int,
    referencia in varchar2,
	cuenta int,
    no_cheque int,
    monto float,
    id_lote int
    )
return number
as
total int;
begin 
    total := 0;
    SAVEPOINT inicioGuardar;
    insert into Lote_Cheque (banco,referencia,cuenta,no_cheque,monto,id_lote) values (bancoViene, referencia,cuenta,no_cheque,monto,id_lote);
    COMMIT;
    return 1;
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK to savepoint inicioGuardar;
            DBMS_OUTPUT.PUT_LINE(sqlcode);
    return -1;
end;

create or replace FUNCTION cantidadCheques
    (
    id_banco int,
    id_lote int
    )
return number
as
total int;
begin 
    total := 0;
    SELECT COUNT(NO_CHEQUE) into total FROM Lote_Cheque WHERE BANCO = id_banco and ID_LOTE = id_lote;
    return total;
end;
/
create or replace FUNCTION montoLote
    (
    id_banco int,
    id_lote int
    )
return number
as
total int;
begin 
    total := 0;
    SELECT SUM(MONTO) into total FROM Lote_Cheque WHERE BANCO = id_banco and ID_LOTE = id_lote;
    return total;
end;

CREATE or replace function getChequesLote 
    (
    id_banco int,
    id_lote int
    )
    return SYS_REFCURSOR
is 
    l_cursor SYS_REFCURSOR;
begin 
    open l_cursor for 
    select * from Lote_Cheque where banco = id_banco and id_lote = id_lote;
    return l_cursor;
end;

create or replace Procedure Grabacion_Cheques
    (
    lote int, 
    idbanco int
    ) 
as
Suma int;
/* SE OBTIENE LA INFORMACION DEL CHEQUE A COBRAR*/
CURSOR cheques_verificacion is 
SELECT cc.estado, cu.d, temp.monto, cu.cuenta, temp.no_cheque FROM cheque_chequera cc, Chequera c, Cuenta cu, 
(
SELECT cuenta, no_cheque, monto 
    FROM Lote_Cheque where id_lote = lote and banco = idbanco and estado = 'Grabando'
) temp
where cc.cheque = temp.no_cheque 
and cc.chequera_chequera = c.id_chequera and cu.cuenta = c.id_cuenta
and cu.estado = 2 and c.estado = 1 and cc.estado = 1;
begin 
    Suma := 0;
    FOR cheques in cheques_verificacion
    LOOP
    SAVEPOINT iniciotransf;
            IF cheques.d >= Suma + cheques.Monto THEN
                    Suma := Suma + cheques.monto;
	            SELECT * FROM CUENTA for update;
                    Update Cuenta cu set cu.d = (cu.d - cheques.monto) where cu.cuenta  = cheques.cuenta;
		    SELECT * FROM Lote_Cheque for update;
                    Update Lote_Cheque lq set lq.estado = 'Cobrado' where lq.cuenta = cheques.cuenta and lq.no_cheque = cheques.no_cheque and lq.id_lote = lote;
            END IF;
    COMMIT;
    
      END LOOP;
          EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK to savepoint iniciotransf;
            DBMS_OUTPUT.PUT_LINE(sqlcode);
end;
/

create or replace trigger UpdateCuenta
before update of S, D, R on Cuenta for each row
 begin
    :New.S := :new.R + :new.D;
 end UpdateCuenta;
 /