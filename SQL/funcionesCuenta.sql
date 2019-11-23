create or replace FUNCTION buscarCliente
    (
    newDpi int
    )
return number
as
total int;
begin 
    total := 0;
    select count(nombre) into total from cliente where dpi = newdpi and estado = 1;
    if total > 0 then
        return 1;
    end if;
    return -1;
end;
/

create or replace FUNCTION nuevaCuenta
    (
    newDpi int,
    tipoCuenta int
    )
return number
as
total int;
newNoCuenta int;
begin 
    total := 0;
    select count(c.nombre) into total from cliente c
    inner join cliente_cuenta cc on cc.cliente_dpi = c.dpi
    inner join cuenta cu on cu.cuenta = cc.cuenta_cuenta
    where c.dpi = newdpi and cu.tipo = tipoCuenta and cu.estado = 2 or cu.estado = 1;
    if total = 0 then --no tiene cuenta de ese tipo
        select cuenta_seq.nextval into newNoCuenta from dual;
        insert into cuenta (cuenta,estado,tipo,cantidad) values (newNoCuenta,2,tipoCuenta,0);
        insert into cliente_cuenta (cliente_dpi,cuenta_cuenta) values (newDpi,newNoCuenta);
        return 1;
    end if;
    return -1; -- ya tiene cuenta
end;
/

create or replace FUNCTION bloquearCuenta
    (
    noCuenta int
    )
return number
as
total int;
begin 
    total := 0;
    select count(tipo) into total from cuenta 
    where cuenta = noCuenta and estado = 2;
    if total > 0 then --existe la cuenta
        UPDATE cuenta SET estado = 1 WHERE cuenta = noCuenta;
        return 1;
    end if;
    return -1; -- la cuenta ya no existe
end;
/

create or replace FUNCTION deletCuenta
    (
    noCuenta int
    )
return number
as
total int;
begin 
    total := 0;
    select count(tipo) into total from cuenta 
    where cuenta = noCuenta and estado = 2;
    if total > 0 then --existe la cuenta
        UPDATE cuenta SET estado = 0 WHERE cuenta = noCuenta;
        return 1;
    end if;
    return -1; -- la cuenta ya no existe
end;
/

CREATE or replace function getCuenta
    (
    eldpi int,
    tipoCuenta int
    )
    return SYS_REFCURSOR
is 
    l_cursor SYS_REFCURSOR;
begin 
    open l_cursor for select c.cuenta,c.cantidad,cc.cliente_dpi
    from cuenta c
    inner join cliente_cuenta cc on cc.cuenta_cuenta = c.cuenta
    where c.estado = 2 and cc.cliente_dpi = eldpi and c.tipo = tipoCuenta;
    return l_cursor;
end;
/