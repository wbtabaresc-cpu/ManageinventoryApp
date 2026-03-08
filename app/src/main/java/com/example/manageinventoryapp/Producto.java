package com.example.manageinventoryapp;

public class Producto {
    private String _id;

    private String id_producto;
    private String nombre;
    private String categoria;
    private String unidad_medida;
    private String ubicacion;
    private String proveedor;
    private String descripcion;

    public Producto(String id, String nom, String cat, String uni, String ubi, String prov, String desc) {
        this.id_producto = id;
        this.nombre = nom;
        this.categoria = cat;
        this.unidad_medida = uni;
        this.ubicacion = ubi;
        this.proveedor = prov;
        this.descripcion = desc;
    }

    public String get_id() { return _id; }
    public String getNombre() { return nombre; }
    public String getId_producto() { return id_producto; }
    public String getCategoria() { return categoria; }
}