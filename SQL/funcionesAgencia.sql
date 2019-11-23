create or replace FUNCTION nuevaAgencia
    (
    newNombre VARCHAR2
    )
return number
as
begin 
    Insert into agencia (nombre,estado) values (newNombre,1);
    return 1;
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

create or replace FUNCTION deletAgencia
    (
    elDpi int
    )
return number
as
total int;
begin 
    total := 0;
    select count(nombre) into total from Agencia where id_agencia = elDpi and estado = 1;
    if total > 0 then
        UPDATE Agencia SET estado = 0 WHERE id_agencia = elDpi;
        return 1;
    end if;
    return -1;
end;
/

create or replace FUNCTION editAgencia
    (
    Dpi int,
    newNombre in varchar2
    )
return number
as
total int;
begin 
    total := 0;
    select count(nombre) into total from Agencia where id_agencia = dpi and estado = 1;
    if total > 0 then
        Update Agencia set nombre = newNombre WHERE id_agencia = dpi and estado = 1;
        return 1;
    end if;
    return -1;
end;
/