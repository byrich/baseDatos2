create or replace FUNCTION nuevoOperador
    (
    newNombre VARCHAR2,
    newPassword VARCHAR2,
    newAgenciaId INT,
    newTerminalId INT,
    newRolId INT
    )
return number
as
begin 
    Insert into operador (nombre,estado,password,Agencia_id_agencia,Terminal_id_terminal,Rol_id_rol) 
    values (newNombre,1,newPassword,newAgenciaId,newTerminalId,newRolId);
    return 1;
end;
/

CREATE or replace function getOperadores 
    return SYS_REFCURSOR
is 
    l_cursor SYS_REFCURSOR;
begin 
    open l_cursor for 
    select o.id_operador, o.nombre, o.password, o.Agencia_id_agencia, o.Terminal_id_terminal ,a.nombre as aName, r.nombre as rName from operador o
    inner join agencia a on o.agencia_id_agencia = a.id_agencia
    inner join rol r on o.rol_id_rol = r.id_rol
    where o.estado = 1;
    return l_cursor;
end;
/

CREATE or replace function getAgencias 
    return SYS_REFCURSOR
is 
    l_cursor SYS_REFCURSOR;
begin 
    open l_cursor for select * from agencia where estado = 1;
    return l_cursor;
end;
/

CREATE or replace function getRoles 
    return SYS_REFCURSOR
is 
    l_cursor SYS_REFCURSOR;
begin 
    open l_cursor for select * from rol where estado = 1;
    return l_cursor;
end;
/

create or replace FUNCTION deletOperador
    (
    elDpi int
    )
return number
as
total int;
begin 
    total := 0;
    select count(nombre) into total from operador where id_operador = elDpi and estado = 1;
    if total > 0 then
        UPDATE operador SET estado = 0 WHERE id_operador = elDpi;
        return 1;
    end if;
    return -1;
end;
/

create or replace FUNCTION editOperador
    (
    Dpi int,
    newNombre in varchar2,
    newPassword in varchar2,
    newAgenciaId in int,
    newRol in int
    )
return number
as
total int;
begin 
    total := 0;
    select count(nombre) into total from operador where dpi = dpi and estado = 1;
    if total > 0 then
        Update operador set nombre = newNombre , password = newPassword, Agencia_id_agencia = newAgenciaId, Rol_id_rol = newRol 
        WHERE id_operador = dpi and estado = 1;
        return 1;
    end if;
    return -1;
end;
/

create or replace FUNCTION login

    (
    miId int,
    miPass varchar
    )
return number
as
total int;
mirol int;
begin 
    total := 0;
    select count(nombre) into total from operador where id_operador = miid and password = mipass and estado = 1;
    if total > 0 then
        select rol_id_rol into mirol from operador where id_operador = miid and password = mipass;
        return mirol;
    end if;
    return -1;
end;
/