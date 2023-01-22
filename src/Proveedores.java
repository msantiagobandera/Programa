package programa;

import java.util.Date;

public class Proveedores {
    private int id_proveedor;
    private String nombre;
    private String fecha_de_alta;
    private int id_cliente;

    public Proveedores(int id_proveedor, String nombre, String fecha_de_alta, int id_cliente) {
        this.id_proveedor = id_proveedor;
        this.nombre = nombre;
        this.fecha_de_alta = fecha_de_alta;
        this.id_cliente = id_cliente;
    }
    public Proveedores(int id_proveedor, String nombre, String fecha_de_alta) {
        this.id_proveedor = id_proveedor;
        this.nombre = nombre;
        this.fecha_de_alta = fecha_de_alta;
    }
    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_apis(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha_de_alta() {
        return fecha_de_alta;
    }

    public void setFecha_de_alta(String fecha_de_alta) {
        this.fecha_de_alta = fecha_de_alta;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }
}
