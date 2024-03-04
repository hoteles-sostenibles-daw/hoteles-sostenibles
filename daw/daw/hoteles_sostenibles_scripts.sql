CREATE DATABASE IF NOT EXISTS hoteles_sostenibles_db;
USE hoteles_sostenibles_db;

CREATE TABLE IF NOT EXISTS hotel (
  nombre VARCHAR(50) NOT NULL,
  habitacionesTotales SMALLINT NOT NULL DEFAULT 0,
  habitacionesOcupadas SMALLINT NOT NULL,
  PRIMARY KEY (nombre));

CREATE TABLE IF NOT EXISTS habitacion (
  numero SMALLINT NOT NULL,
  habitacionOcupada CHAR(1) NOT NULL,
  PRIMARY KEY (numero));

CREATE TABLE IF NOT EXISTS huesped (
  dni VARCHAR(12) NOT NULL,
  email VARCHAR(50) NOT NULL,
  telefono VARCHAR(20) NOT NULL,
  personaContacto VARCHAR(20) NOT NULL,
  PRIMARY KEY (dni));
  
CREATE TABLE IF NOT EXISTS gastos (
  id VARCHAR(20) NOT NULL,
  concepto VARCHAR(50) NOT NULL,
  pagado CHAR(1) NOT NULL,
  precio SMALLINT NOT NULL,
  habitacionNumero SMALLINT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_gastos_habitacion
    FOREIGN KEY (habitacionNumero)
    REFERENCES habitacion (numero)
    ON DELETE RESTRICT
    ON UPDATE CASCADE);
    
    CREATE TABLE  IF NOT EXISTS reserva (
  codigo VARCHAR(50) NOT NULL,
  fechaEntrada DATE NOT NULL,
  fechaSalida DATE NOT NULL,
  check_in CHAR(1) NOT NULL,
  check_out CHAR(1) NOT NULL,
  numeroHuespedes TINYINT NOT NULL,
  hotelNombre VARCHAR(50) NOT NULL,
  huespedDni VARCHAR(12) NOT NULL,
  habitacionNumero SMALLINT NULL,
  PRIMARY KEY (codigo),
  
  CONSTRAINT fk_reserva_hotel
    FOREIGN KEY (hotelNombre)
    REFERENCES hotel (nombre)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT fk_reserva_huesped
    FOREIGN KEY (huespedDni)
    REFERENCES huesped(dni)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT fk_reserva_habitacion
    FOREIGN KEY (habitacionNumero)
    REFERENCES habitacion(numero)
    ON DELETE RESTRICT
    ON UPDATE CASCADE);
    
    CREATE TABLE IF NOT EXISTS personal (
    rol VARCHAR(20) NOT NULL,
    contrasena VARCHAR(60) NOT NULL,
    hotel_nombre VARCHAR(45) NOT NULL,
    PRIMARY KEY (rol),
  
    CONSTRAINT fk_personal_hotel
    FOREIGN KEY (hotel_nombre)
    REFERENCES hotel (nombre)
    ON DELETE RESTRICT
    ON UPDATE CASCADE);