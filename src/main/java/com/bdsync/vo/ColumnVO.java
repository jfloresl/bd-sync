package com.bdsync.vo;

public class ColumnVO {
    String name;
    String tipo;
    String size;
    String nullable;

    public ColumnVO(String name, String tipo, String size, String nullable) {
        this.name = name;
        this.tipo = tipo;
        this.size = size;
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

    public String getNullable() {
        return nullable;
    }

    public void setNullable(String nullable) {
        this.nullable = nullable;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
