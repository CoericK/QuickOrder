package com.boushtech.quickorder.entities;

public class DetalleOrden {


    private String id;
    private String colaborador;
    private String codigoProducto;
    private String producto;
    private String precioUnitario;
    private String cantidad;
    private String total;
    private String notaPedido;
    private String estado;


    public DetalleOrden() {
    }

    public DetalleOrden(String id, String colaborador, String codigoProducto, String producto, String precioUnitario, String cantidad, String total, String notaPedido, String estado) {
        this.id = id;
        this.colaborador = colaborador;
        this.codigoProducto = codigoProducto;
        this.producto = producto;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
        this.total = total;
        this.notaPedido = notaPedido;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColaborador() {
        return colaborador;
    }

    public void setColaborador(String colaborador) {
        this.colaborador = colaborador;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(String precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getNotaPedido() {
        return notaPedido;
    }

    public void setNotaPedido(String notaPedido) {
        this.notaPedido = notaPedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
