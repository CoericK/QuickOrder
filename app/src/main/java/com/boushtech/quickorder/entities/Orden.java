package com.boushtech.quickorder.entities;

import java.sql.Timestamp;

public class Orden {

    private String id;
    private String colaborador;
    private String codigo;
    private String tipoConsumo;
    private String estado;
    private String fechaRegistro;


    public Orden() {
    }

    public Orden(String id, String colaborador, String codigo, String tipoConsumo, String estado, String fechaRegistro) {
        this.id = id;
        this.colaborador = colaborador;
        this.codigo = codigo;
        this.tipoConsumo = tipoConsumo;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipoConsumo() {
        return tipoConsumo;
    }

    public void setTipoConsumo(String tipoConsumo) {
        this.tipoConsumo = tipoConsumo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
