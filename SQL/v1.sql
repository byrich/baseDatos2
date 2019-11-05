CREATE TABLE chequera (
	id_chequera INT NOT NULL,
	estado INT NOT NULL, /* 0:no entregado, 1:entregado*/
	PRIMARY KEY(id_chequera)
);

CREATE TABLE Cliente_cuenta (
	Cuenta_cuenta INT NOT NULL,
	id_cliente_cuenta INT NOT NULL,
	Cliente_dpi INT NOT NULL,
	PRIMARY KEY(id_cliente_cuenta)
);

CREATE TABLE Cliente (
	dpi INT NOT NULL,
	nombre VARCHAR2(100) NOT NULL,	
	firma BLOB NULL, /* imagen */
	foto BLOB NULL, /* imagen */
	fecha_nacimiento DATE NOT NULL,
	estado INT NOT NULL, /* 1:activo, 0:eliminado */
	PRIMARY KEY(dpi)
);

CREATE TABLE Cuenta (
	cuenta INT NOT NULL,
	estado INT NOT NULL, /* 2:activo, 1:bloqueado, 0:cancelado */
	tipo INT NOT NULL, /* 1:monetaria, 0:ahorro */
	S VARCHAR2(30) NULL,
	R VARCHAR2(30) NULL,
	D VARCHAR2(30) NULL,
	Cantidad FLOAT check (valor > 0) NOT NULL, /* dinero disponible */
	saldopendiente FLOAT check (valor > 0) NOT NULL, /* saldo de cheques */
	PRIMARY KEY(cuenta)
);

CREATE TABLE Cheque (
	cheque INT NOT NULL, 
	fecha DATE NOT NULL,
	monto FLOAT NOT NULL,
	estado INT NOT NULL, /* 2: robado, 1:activo, 0:extraviado */
	Cuenta_cuenta INT NOT NULL,
	Lote_lote INT NOT NULL,
	PRIMARY KEY(cheque)
);

CREATE TABLE Lote (
	lote INT NOT NULL,
	fecha DATE NOT NULL,
	de_banco VARCHAR2(30) NOT NULL,
	cantidad FLOAT NOT NULL,
	total FLOAT NOT NULL,
	estado VARCHAR2(30) NOT NULL,
	PRIMARY KEY(lote)
);

CREATE TABLE Transaccion (
	numero INT NOT NULL,
	id_cuenta INT NOT NULL,
	id_agencia INT NOT NULL,
	fecha DATE NOT NULL,
	tipo VARCHAR2(30) NOT NULL,--retiro o deposito
	tipo_detalle VARCHAR2(30) NOT NULL,--cheque, efectivo, transferencia
	detalle VARCHAR2(100) NOT NULL,
	valor FLOAT NOT NULL,
	Operador_id_operador INT NOT NULL,
	PRIMARY KEY(numero)
);

CREATE TABLE Agencia (
	id_agencia INT NOT NULL,
	nombre VARCHAR2(100) NOT NULL,
	estado INT NOT NULL, /* 1:activo, 0:eliminado */
	PRIMARY KEY(id_agencia)
);
CREATE TABLE Operador (
	id_operador INT NOT NULL,
	nombre VARCHAR2(100) NOT NULL,
	estado INT NOT NULL, /* 1:activo, 0:eliminado*/
	password VARCHAR2(20) NOT NULL,
	Agencia_id_agencia INT NOT NULL,
	Terminal_id_terminal INT NOT NULL,
	Rol_id_rol INT NOT NULL,
	PRIMARY KEY(id_operador)
);
CREATE TABLE Terminal (
	id_terminal INT NOT NULL,
	nombre VARCHAR2(100) NOT NULL,
	estado INT NOT NULL, /* 1:activo, 0:eliminado */
	PRIMARY KEY(id_terminal)
);
CREATE TABLE Rol (
	id_rol INT NOT NULL,
	nombre VARCHAR2(100) NOT NULL,
	estado INT NOT NULL, /* 1:activo, 0:eliminado */
	PRIMARY KEY(id_rol)
);

CREATE SEQUENCE agencia_seq START WITH 1000;
CREATE SEQUENCE cuenta_seq START WITH 1000;
CREATE SEQUENCE oper_seq START WITH 1000;   
CREATE SEQUENCE cuenta_cuenta_seq START WITH 1000;
CREATE SEQUENCE transaccion_seq START WITH 1000;


CREATE OR REPLACE TRIGGER cuenta_cuenta_seq_trig 
BEFORE INSERT ON cliente_cuenta 
FOR EACH ROW
BEGIN
  SELECT agencia_seq.NEXTVAL
  INTO   :new.id_cliente_cuenta
  FROM   dual;
END;

select cuenta_seq.nextval from dual;


CREATE OR REPLACE TRIGGER agencia_seq_trig 
BEFORE INSERT ON agencia 
FOR EACH ROW
BEGIN
  SELECT agencia_seq.NEXTVAL
  INTO   :new.id_agencia
  FROM   dual;
END;
/

CREATE OR REPLACE TRIGGER transaccion_seq_trig 
BEFORE INSERT ON transaccion 
FOR EACH ROW
BEGIN
  SELECT transaccion_seq.NEXTVAL
  INTO   :new.numero
  FROM   dual;
END;


CREATE OR REPLACE TRIGGER oper_seq_trig 
BEFORE INSERT ON operador 
FOR EACH ROW
BEGIN
  SELECT oper_seq.NEXTVAL
  INTO   :new.id_operador
  FROM   dual;
END;	
/

