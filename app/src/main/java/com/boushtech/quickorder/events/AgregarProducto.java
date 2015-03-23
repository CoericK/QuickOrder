package com.boushtech.quickorder.events;

/**
 * Created by Erick on 23/03/2015.
 */
public class AgregarProducto {

    private String id;
    private String codigo;
    private String familia;
    private String producto;
    private String cantidad;
    private String precio;
    private String sub_total;

    public AgregarProducto() {
    }

    public AgregarProducto(String id, String codigo, String familia, String producto, String cantidad, String precio, String sub_total) {
        this.id = id;
        this.codigo = codigo;
        this.familia = familia;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.sub_total = sub_total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getSub_total() {
        return sub_total;
    }

    public void setSub_total(String sub_total) {
        this.sub_total = sub_total;
    }
}
