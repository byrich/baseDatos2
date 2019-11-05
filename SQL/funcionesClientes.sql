create or replace FUNCTION nuevoCliente2
    (
    newDpi int,
    newNombre in varchar2,
	newFecha_nacimiento DATE,
    newFoto BLOB,
    newFirma BLOB
    )
return number
as
total int;
begin 
    total := 0;
    select count(nombre) into total from cliente where dpi = newdpi;
    if total = 0 then
        insert into cliente (dpi,nombre,fecha_nacimiento,firma,foto,estado) values (newDpi,newNombre,newFecha_nacimiento,newFirma,newFoto,1);
        return 1;
    end if;
    return -1;
end;

CREATE or replace function getClientes 
    return SYS_REFCURSOR
is 
    l_cursor SYS_REFCURSOR;
begin 
    open l_cursor for select * from cliente where estado = 1;
    return l_cursor;
end;


CREATE or replace function getClientesB 
    return SYS_REFCURSOR
is 
    l_cursor SYS_REFCURSOR;
begin 
    open l_cursor for select * from cliente where estado = 0;
    return l_cursor;
end;


create or replace FUNCTION deletCliente
    (
    elDpi int
    )
return number
as
total int;
begin 
    total := 0;
    select count(nombre) into total from cliente where dpi = elDpi and estado = 1;
    if total > 0 then
        UPDATE cliente SET estado = 0 WHERE dpi = elDpi;
        return 1;
    end if;
    return -1;
end;

create or replace FUNCTION upCliente
    (
    elDpi int
    )
return number
as
total int;
begin 
    total := 0;
    select count(nombre) into total from cliente where dpi = elDpi and estado = 0;
    if total > 0 then
        UPDATE cliente SET estado = 1 WHERE dpi = elDpi;
        return 1;
    end if;
    return -1;
end;


create or replace FUNCTION editCliente
    (
    nwDpi int,
    newNombre in varchar2,
    newFecha_nacimiento DATE
    )
return number
as
total int;
begin 
    total := 0;
    select count(nombre) into total from cliente where dpi = nwDpi and estado = 1;
    if total > 0 then
        UPDATE cliente SET nombre = newNombre, fecha_nacimiento = newFecha_nacimiento 
        WHERE dpi = nwDpi;
        return 1;
    end if;
    return -1;
end;