package com.loterias.caixa.megasena.Model;

import java.io.Serializable;

/**
 * Created by Marcos on 10/10/2015.
 */
public class GanhadorSena implements Serializable {


    private Integer ganhadorSenaId;
    private Integer concursoId;
    private String cidade;
    private String estado;

    public Integer getGanhadorSenaId() {
        return ganhadorSenaId;
    }

    public void setGanhadorSenaId(Integer ganhadorSenaId) {
        this.ganhadorSenaId = ganhadorSenaId;
    }

    public Integer getConcursoId() {
        return concursoId;
    }

    public void setConcursoId(Integer concursoId) {
        this.concursoId = concursoId;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
