create or replace FUNCTION depositarEfectivo
    (
    noCuenta int,
    idOperador int,
    newCantidad float
    )
return number
as
total int;
l_cursor SYS_REFCURSOR;
begin
    total := 0;
    select count(cl.nombre) into total from cuenta c
    inner join cliente_cuenta cc on cc.cuenta_cuenta = c.cuenta
    inner join cliente cl on cl.dpi = cc.Cliente_dpi
    where c.estado = 2 and c.cuenta = noCuenta and cl.estado = 1;
    if total > 0 then --existe la cuenta
        SAVEPOINT iniciodeposito;
        open l_cursor for 
        select * from cuenta where cuenta = noCuenta and estado = 2 for update;
        UPDATE cuenta SET cantidad = cantidad + newCantidad WHERE cuenta = noCuenta;
        insert into 
        transaccion (id_cuenta, id_agencia, fecha,tipo,tipo_detalle, detalle, valor, Operador_id_operador)
        values (noCuenta, 0,SYSDATE,1,2,'Deposito en efectivo',newCantidad,idOperador);
        
        --2 = RETIRO, 1 = DEPOSITO
        --1 = CHEQUE, 2 = EFECTIVO, 3 = TRANSFERENCIA
        COMMIT;
        
        return 1;
    end if;
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK to savepoint iniciodeposito;
            DBMS_OUTPUT.PUT_LINE(sqlcode);
    return -1;
end;

create or replace FUNCTION retirarCheque
    (
    noCuenta int,
    idOperador int,
    noCheque int,
    newCantidad float
    )
return number
as
total int;
l_cursor SYS_REFCURSOR;
l_cursor2 SYS_REFCURSOR;
begin
    total := 0;
    select count(tipo) into total from cuenta c
    where c.estado = 2 and c.cuenta = noCuenta;
    if total > 0 then --existe la cuenta
        total := 0;
        select count(c.id_chequera) into total from chequera c
        inner join cheque_chequera cc on cc.chequera_chequera = c.id_chequera
        where c.id_cuenta = noCuenta and cc.cheque = noCheque and cc.estado = 3;
        if total > 0 then --existe el cheque
            SAVEPOINT inicioretiro;
            open l_cursor for 
            select * from cuenta where cuenta = noCuenta for update;
            UPDATE cuenta SET cantidad = cantidad - newCantidad WHERE cuenta = noCuenta;
            insert into transaccion (id_cuenta, id_agencia, fecha,tipo,tipo_detalle, detalle, valor, Operador_id_operador)
            values (noCuenta, 0,SYSDATE,2,1,('Retiro en con cheque No.' || noCheque),newCantidad,idOperador);
            --2 = RETIRO, 1 = DEPOSITO
            --1 = CHEQUE, 2 = EFECTIVO, 3 = TRANSFERENCIA
            open l_cursor2 for 
            select * from cheque_chequera c where c.cheque = noCheque for update;
            UPDATE cheque_chequera SET monto = newCantidad, estado = 4 WHERE cheque = noCheque and estado = 3;
            COMMIT;
            return 1;
        end if;
        return -2;--no existe cheque
    end if;
    return -3;--cuenta no exsite
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK to savepoint inicioretiro;
            DBMS_OUTPUT.PUT_LINE(sqlcode);
    return -1;
end;




create or replace FUNCTION pedirChequera
    (
    noCuenta int
    )
return number
as
total int;
indice int;
begin
    total := 0;
    select count(c.tipo) into total from cuenta c
    where c.estado = 2 and c.cuenta = noCuenta and tipo = 0;--ahorro
    if total > 0 then --existe la cuenta
        SAVEPOINT iniciopeticion;
        select chequera_seq.nextval into indice from dual;
        insert into chequera (id_chequera,estado, id_cuenta) values (indice,1,noCuenta);
        FOR Lcntr IN 0..24
        LOOP
           insert into cheque_chequera (cheque, estado,chequera_chequera) 
           values ((indice+Lcntr),3,indice);
        END LOOP;  
        return 1;
    end if;
    return -2;--cuenta no exsite
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK to savepoint iniciopeticion;
            DBMS_OUTPUT.PUT_LINE(sqlcode);
    return -1;
end;



create or replace FUNCTION trasnferencia
    (
    noCuenta1 int,
    noCuenta2 int,
    idOperador int,
    newCantidad float
    )
return number
as
total int;
total2 int;
l_cursor SYS_REFCURSOR;
l_cursor2 SYS_REFCURSOR;
begin
    total := 0;
    total2 := 0;
    select count(cl.nombre) into total from cuenta c
    inner join cliente_cuenta cc on cc.cuenta_cuenta = c.cuenta
    inner join cliente cl on cl.dpi = cc.Cliente_dpi
    where c.estado = 2 and c.cuenta = noCuenta1 and cl.estado = 1;
    select count(cl.nombre) into total2 from cuenta c
    inner join cliente_cuenta cc on cc.cuenta_cuenta = c.cuenta
    inner join cliente cl on cl.dpi = cc.Cliente_dpi
    where c.estado = 2 and c.cuenta = noCuenta2 and cl.estado = 1;
    if total > 0 then --existe la cuenta
        if total2 > 0 then --existe la cuenta
            SAVEPOINT iniciotransf;
            open l_cursor for --cuenta donde sale el dinero
            select * from cuenta where cuenta = noCuenta1 and estado = 2 for update;
            UPDATE cuenta SET cantidad = cantidad - newCantidad WHERE cuenta = noCuenta1;
            open l_cursor2 for --cuenta donde entra el dinero
            select * from cuenta where cuenta = noCuenta2 and estado = 2 for update;
            UPDATE cuenta SET cantidad = cantidad + newCantidad WHERE cuenta = noCuenta2;
            insert into 
            transaccion (id_cuenta, id_agencia, fecha,tipo,tipo_detalle, detalle, valor, Operador_id_operador)
            values (noCuenta1, 1,SYSDATE,2,3,'Transferencia a ' || noCuenta2,newCantidad,idOperador);
            insert into 
            transaccion (id_cuenta, id_agencia, fecha,tipo,tipo_detalle, detalle, valor, Operador_id_operador)
            values (noCuenta2, 1,SYSDATE,1,3,'Transferencia desde ' || noCuenta1,newCantidad,idOperador);
            --2 = RETIRO, 1 = DEPOSITO
            --1 = CHEQUE, 2 = EFECTIVO, 3 = TRANSFERENCIA
            COMMIT;
            return 1;
        end if;
        return -2;
    end if;
    return -3;
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK to savepoint iniciotransf;
            DBMS_OUTPUT.PUT_LINE(sqlcode);
    return -1;
end;





