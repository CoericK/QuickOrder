package com.boushtech.quickorder.entities;

public class Producto {

    private String id;
    private String familiaProductoId;
    private String familiaProducto;
    private String codigo;
    private String nombre;
    private String descripcion;
    private String imagenUri;
    private String precioCosto;
    private String precioVentaUnidad;
    private String afectaIgv;
    private String activo;
    private String fechaRegistro;
    private String fechaModificacion;

    public Producto() {
    }

    public Producto(String id, String familiaProductoId, String familiaProducto, String codigo, String nombre, String descripcion, String imagenUri, String precioCosto, String precioVentaUnidad, String afectaIgv, String activo, String fechaRegistro, String fechaModificacion) {
        this.id = id;
        this.familiaProductoId = familiaProductoId;
        this.familiaProducto = familiaProducto;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagenUri = imagenUri;
        this.precioCosto = precioCosto;
        this.precioVentaUnidad = precioVentaUnidad;
        this.afectaIgv = afectaIgv;
        this.activo = activo;
        this.fechaRegistro = fechaRegistro;
        this.fechaModificacion = fechaModificacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFamiliaProductoId() {
        return familiaProductoId;
    }

    public void setFamiliaProductoId(String familiaProductoId) {
        this.familiaProductoId = familiaProductoId;
    }

    public String getFamiliaProducto() {
        return familiaProducto;
    }

    public void setFamiliaProducto(String familiaProducto) {
        this.familiaProducto = familiaProducto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagenUri() {
        return imagenUri;
    }

    public void setImagenUri(String imagenUri) {
        this.imagenUri = imagenUri;
    }

    public String getPrecioCosto() {
        return precioCosto;
    }

    public void setPrecioCosto(String precioCosto) {
        this.precioCosto = precioCosto;
    }

    public String getPrecioVentaUnidad() {
        return precioVentaUnidad;
    }

    public void setPrecioVentaUnidad(String precioVentaUnidad) {
        this.precioVentaUnidad = precioVentaUnidad;
    }

    public String getAfectaIgv() {
        return afectaIgv;
    }

    public void setAfectaIgv(String afectaIgv) {
        this.afectaIgv = afectaIgv;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
}
