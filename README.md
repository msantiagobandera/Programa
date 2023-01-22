## TABLA DE CONTENIDO
1. [Funcionamiento](#Funcionamiento)
2. [Instalación](#Instalación)
3. [Dependencias](#Dependencias)

## Funcionamiento
***
El programa se puede lanzar directamente desde el archivo registroProveedores.jar que se encuentra en el raiz del repositorio.

Este se puede lanzar usando el comando "java -jar registroProveedores.jar [idcliente]". 
Si no se indica el idecliente, el programa mostrará una serie de indicaciones en la pantalla para poder interacturar directamente.

Actualmente el sistema dispone de 3 opciones.

    1. Insertar datos de ejemplo en la base de datos.
    2. Mostrar la información del cliente que le indiquemos en pantalla.
    3. Exportar en el fichero datos.txt la información del cliente que le indiquemos.
Si en la llamada al programa le indicamos el parametro idcliente, el programa directamente descargará el archivo de datos.txt con la información.

## Instalación
***
En el raiz del repositorio podemos encontrar el fichero registroProveedores.jar. Simplemente debemos lanzarlo y ya dispondiamos de todo lo necesario para poder probar

tambien puede crear el .jar para lanzar el programa clonando el repositorio git y compilando el jar con esta estructura
```
$ git clone https://example.com
$ cd ../path/to/the/file
$ jar -cf fichero.jar fichero1.class fichero2.class fichero3.class
```

## Dependencias
***
El programa actualmente usa estas dependencias para el control de datos, lectura de datos y escritura de datos en fichero.
*  java.io.FileWriter;
*  java.io.PrintWriter;
*  java.util.Scanner;


Además de las de base de datos. Actualemente estoy usando la base de datos de tipo objetos de neodatis. Esta me permite que de manera local se almacenen los datos sin necesidad de un servidor externo.

*  org.neodatis.odb.ODB;
*  org.neodatis.odb.ODBFactory;
*  org.neodatis.odb.Objects;
*  org.neodatis.odb.core.query.IQuery;
*  org.neodatis.odb.core.query.criteria.Where;
*  org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

En la prueba se pide que la conexión sea de tipo mysql. Simplemente se modificaría la apertura de la base de datos indicando
``` 
    Connection con = null;
    String sURL = "jdbc:mysql://localhost:3306/nombreBaseDatos";
    con = DriverManager.getConnection(sURL,"usuario","password");
```

Para realizar una consulta usariamos
```
    try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM Proveedores")) 
    {
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            # Código de manejo de datos para proveedores #
        }
    } catch (SQLException sqle) {
        System.out.println("Error en la ejecución:"+ sqle.getErrorCode() + " " + sqle.getMessage());    
    }
```

Para realizar la insertar los datos usariamos

```
    sql = "insert into Proveedores ('nombre', 'fecha_de_alta', 'id_cliente')" "values('Pepsi','12/11/2006','1')";
    stmt = conn.createStatement();
    int i = stmt.executeUpdate(sql);
```

En el fichero script.sql se encuentra la estructura completa de la base de datos que usariamos para esta prueba.