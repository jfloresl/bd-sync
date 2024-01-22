package com.jfloresl.vo;

public class ColumnVO {
    String name;
    String tipo;
    String tamaño;
    String nullable;

    public ColumnVO(String name, String tipo, String tamaño, String nullable) {
        this.name = name;
        this.tipo = tipo;
        this.tamaño = tamaño;
        this.nullable = nullable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public String getNullable() {
        return nullable;
    }

    public void setNullable(String nullable) {
        this.nullable = nullable;
    }
}
