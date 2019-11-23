DROP TABLE TransaccionChequeTmp;

CREATE TABLE TransaccionChequeTmp (
  referencia INT NOT NULL,
  cuenta INT NOT NULL,
  cheque INT NOT NULL,
  monto FLOAT NOT NULL,
  PRIMARY KEY(referencia)
);

CREATE SEQUENCE chequeTmp_seq START WITH 1000;

CREATE OR REPLACE TRIGGER chequeTmp_seq_trig
BEFORE INSERT ON TransaccionChequeTmp
FOR EACH ROW
BEGIN
  SELECT chequeTmp_seq.NEXTVAL
  INTO   :new.referencia
  FROM   dual;
END;
/