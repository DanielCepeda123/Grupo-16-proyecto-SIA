package com.proyectocumputacional.proyecto;

public class ClienteVIP extends Cliente {
    private String beneficios;

    private ClienteVIP(Builder builder) {
        super(builder);  // Llamada al constructor protegido de Cliente
        this.beneficios = builder.beneficios;
    }

    public static class Builder extends Cliente.Builder {
        private String beneficios;

        public Builder setBeneficios(String beneficios) {
            this.beneficios = beneficios;
            return this;
        }

        public ClienteVIP build() {
            return ClienteVIP.fromBuilder(this);
        }
    }

    public static ClienteVIP fromBuilder(Builder builder) {
        return new ClienteVIP(builder);
    }

    public String getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(String beneficios) {
        this.beneficios = beneficios;
    }

    @Override
    public String toString() {
        return super.toString() + ", beneficios='" + beneficios + '\'';
    }
}
