create or replace FUNCTION retirarEfectivo
    (
    noCuenta int,
    idAgencia int,
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
        SAVEPOINT inicioretiro;
        open l_cursor for 
        select * from cuenta where cuenta = noCuenta and estado = 2 for update;
        UPDATE cuenta SET cantidad = cantidad - newCantidad WHERE cuenta = noCuenta;
        insert into 
        transaccion (id_cuenta, id_agencia, fecha,tipo,tipo_detalle, detalle, valor, Operador_id_operador)
        values (noCuenta, idAgencia,SYSDATE,2,2,'Retiro en efectivo',newCantidad,idOperador);
        --2 = RETIRO, 1 = DEPOSITO
        --1 = CHEQUE, 2 = EFECTIVO, 3 = TRANSFERENCIA
        COMMIT;
        return 1;
    end if;
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK to savepoint inicioretiro;
            DBMS_OUTPUT.PUT_LINE(sqlcode);
    return -1;
end;


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
        values (noCuenta, 0,SYSDATE,1,2,'Retiro en efectivo',newCantidad,idOperador);
        
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


create or replace FUNCTION trasnferencia
    (
    noCuenta1 int,
    noCuenta2 int,
    idAgencia int,
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
            values (noCuenta1, idAgencia,SYSDATE,2,3,'Transferencia a ' || noCuenta2,newCantidad,idOperador);
            insert into 
            transaccion (id_cuenta, id_agencia, fecha,tipo,tipo_detalle, detalle, valor, Operador_id_operador)
            values (noCuenta2, idAgencia,SYSDATE,1,3,'Transferencia desde ' || noCuenta1,newCantidad,idOperador);
            --2 = RETIRO, 1 = DEPOSITO
            --1 = CHEQUE, 2 = EFECTIVO, 3 = TRANSFERENCIA
            COMMIT;
            return 1;
        end if;
        return -2;
    end if;
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK to savepoint iniciotransf;
            DBMS_OUTPUT.PUT_LINE(sqlcode);
    return -1;
end;





