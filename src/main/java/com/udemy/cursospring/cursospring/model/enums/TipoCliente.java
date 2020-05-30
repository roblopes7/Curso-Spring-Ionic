package com.udemy.cursospring.cursospring.model.enums;

public enum TipoCliente {

    PESSOAFISICA(0, "Pessoa Física"),
    PESSOAJURIDICA(1, "Pessoa Jurídica");

    private int codigo;
    private String tipo;

    private TipoCliente(int codigo, String tipo) {
        this.codigo = codigo;
        this.tipo = tipo;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public static TipoCliente toEnum(Integer codigo) {
        if (codigo == null) {
            return null;
        }

        for (TipoCliente x : TipoCliente.values()) {
            if (codigo.equals(x.getCodigo())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido " + codigo);
    }
}
