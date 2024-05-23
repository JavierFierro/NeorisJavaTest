CREATE DATABASE db_neoris_test
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;
	
CREATE TABLE IF NOT EXISTS public.cliente (
  cliente_id SERIAL PRIMARY KEY,
  edad VARCHAR(50),
  genero VARCHAR(20),
  nombre VARCHAR(255),
  contrasena VARCHAR(255),
  estado BOOLEAN,
  direccion VARCHAR(255) ,
  identificacion VARCHAR(50),
  telefono VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS public.cuenta
(
    numero_cuenta character varying(255) NOT NULL PRIMARY KEY,
    estado boolean,
    saldo_inicial numeric(10,2),
    tipo character varying(255),
    cliente_id integer
)

CREATE TABLE IF NOT EXISTS public.movimiento
(
    movimiento_id character varying(255) NOT NULL PRIMARY KEY,
    fecha character varying(255),
    numero_cuenta character varying(255),
    saldo numeric(10,2),
    tipo_movimiento character varying(255),
    valor numeric(10,2),
)

INSERT INTO public.cliente(
	cliente_id, edad, genero, nombre, contrasena, estado, direccion, identificacion, telefono)
	VALUES 
('22'    ,'Masculino'    ,'Jose Lema'    		,'1234'		,true    ,'Otavalo sn y principal'    ,'092123456'   ,'098254785'),
('22'    ,'Femenino'     ,'Marianela Montalvo'  ,'5678'		,true    ,'Amazonas y NNUU'    		  ,'092123369'   ,'097548965'),
('22'    ,'Masculino'    ,'Juan Osorio'    		,'1245'		,true    ,'13 junio y Equinoccial'    ,'09217894'    ,'098874587');

INSERT INTO public.cuenta(
	numero_cuenta, estado, saldo_inicial, tipo, cliente_id)
	VALUES 
('225487'	,true	,100.00			,'Corriente'		,7),
('478758'	,true	,2000.00		,'Ahorro'			,6),
('495878'	,true	,0.00			,'Ahorro'			,8),
('496825'	,true	,540.00			,'Ahorro'			,7),
('585545'	,true	,2000.00		,'Corriente'		,6);

INSERT INTO public.movimiento(
	movimiento_id, fecha, numero_cuenta, saldo, tipo_movimiento, valor)
	VALUES 
('134'	,'15/02/2023 10:34:20'	,'495878'	,150.00		,'deposito'		,150.00),
('364'	,'02/06/2023 10:34:20'	,'496825'	,0.00		,'retiro'		,-540.00),
('477'	,'23/10/2022 12:34:20'	,'478758'	,1425.00	,'retiro'		,-575.00),
('700'	,'02/06/2023 20:34:20'	,'478758'	,885.00		,'retiro'		,-540.00),
('773'	,'20/12/2022 23:34:20'	,'225487'	,700.00		,'deposito'		,600.00);


