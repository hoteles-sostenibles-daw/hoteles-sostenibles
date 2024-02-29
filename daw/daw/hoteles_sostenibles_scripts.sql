CREATE DATABASE IF NOT EXISTS hoteles_sostenibles_db;
USE hoteles_sostenibles_db;

CREATE TABLE IF NOT EXISTS hotel (
  nombre VARCHAR(50) NOT NULL,
  PRIMARY KEY (nombre));

CREATE TABLE IF NOT EXISTS habitacion (
  numero SMALLINT NOT NULL,
  habitacion_ocupada CHAR(1) NOT NULL,
  PRIMARY KEY (numero));

CREATE TABLE IF NOT EXISTS huesped (
  dni VARCHAR(12) NOT NULL,
  email VARCHAR(50) NOT NULL,
  telefono VARCHAR(20) NOT NULL,
  persona_contacto VARCHAR(20) NOT NULL,
  imagen_dni BLOB,
  PRIMARY KEY (dni));
  
CREATE TABLE IF NOT EXISTS gastos (
  id VARCHAR(20) NOT NULL,
  concepto VARCHAR(50) NOT NULL,
  pagado CHAR(1) NOT NULL,
  precio SMALLINT NOT NULL,
  habitacion_numero SMALLINT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_gastos_habitacion
    FOREIGN KEY (habitacion_numero)
    REFERENCES habitacion (numero)
    ON DELETE RESTRICT
    ON UPDATE CASCADE);
    
  CREATE TABLE IF NOT EXISTS reserva (
  codigo VARCHAR(50) NOT NULL,
  fecha_entrada VARCHAR(20) NOT NULL,
  fecha_salida VARCHAR(20) NOT NULL,
  check_in CHAR(1) NOT NULL,
  check_out CHAR(1) NOT NULL,
  numero_huespedes TINYINT NOT NULL,
  hotel_nombre VARCHAR(50) NOT NULL,
  huesped_dni VARCHAR(12) NOT NULL,
  habitacion_numero SMALLINT NULL,
  PRIMARY KEY (codigo),
  
  CONSTRAINT fk_reserva_hotel
    FOREIGN KEY (hotel_nombre)
    REFERENCES hotel (nombre)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT fk_reserva_huesped
    FOREIGN KEY (huesped_dni)
    REFERENCES huesped(dni)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT fk_reserva_habitacion
    FOREIGN KEY (habitacion_numero)
    REFERENCES habitacion(numero)
    ON DELETE RESTRICT
    ON UPDATE CASCADE);

  CREATE TABLE IF NOT EXISTS personal (
  rol VARCHAR(20) NOT NULL,
  contraseña VARCHAR(45) NOT NULL,
  PRIMARY KEY (rol));

  