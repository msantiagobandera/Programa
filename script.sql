CREATE TABLE Clientes(id_cliente int NOT NULL AUTO_INCREMENT,nombre varchar(255), PRIMARY KEY (id_cliente));
CREATE TABLE Proveedores(id_proveedor int NOT NULL AUTO_INCREMENT,nombre varchar(255),fecha_de_alta varchar(255),id_cliente int, PRIMARY KEY (id_proveedor));

INSERT INTO Proveedores (nombre, fecha_de_alta, id_cliente) VALUES("Coca-cola","14/11/1998", 1);
INSERT INTO Proveedores (nombre, fecha_de_alta) VALUES("Pepsi",'14/11/1998');
INSERT INTO Proveedores ((nombre, fecha_de_alta) VALUES("Redbul","14/11/1998");
INSERT INTO Proveedores (nombre, fecha_de_alta, id_cliente) VALUES("Monster","14/11/1998", 4);
INSERT INTO Proveedores (nombre, fecha_de_alta, id_cliente) VALUES("Nestea","14/11/1998", 4);

INSERT INTO Clientes (nombre) VALUES('Juan');
INSERT INTO Clientes (nombre) VALUES('Paco');
INSERT INTO Clientes (nombre) VALUES('Pepe');
INSERT INTO Clientes (nombre) VALUES('Miguel');