package com.loterias.caixa.megasena.Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Marcos on 10/10/2015.
 */
public class Resultado implements Serializable {

    private Date dataSorteio;
    private Date proximoSorteio;
    private Integer dezena1;
    private Integer dezena2;
    private Integer dezena3;
    private Integer dezena4;
    private Integer dezena5;
    private Integer dezena6;
    private BigDecimal arrecadacaoTotal;
    private Integer ganhadoresSena;
    private Integer ganhadoresQuina;
    private Integer ganhadoresQuadra;
    private BigDecimal rateioSena;
    private BigDecimal rateioQuina;
    private BigDecimal rateioQuadra;
    private boolean acumulado;
    private BigDecimal valorAcumulado;
    private BigDecimal estimativaPremio;
    private BigDecimal acumuladoMegaVirada;

    public Resultado() {
    }

    public Integer getConcursoId() {
        return concursoId;
    }

    public void setConcursoId(Integer concursoId) {
        this.concursoId = concursoId;
    }

    private Integer concursoId;

    public Date getDataSorteio() {
        return dataSorteio;
    }

    public void setDataSorteio(Date dataSorteio) {
        this.dataSorteio = dataSorteio;
    }

    public Date getProximoSorteio() {
        return proximoSorteio;
    }

    public void setProximoSorteio(Date proximoSorteio) {
        this.proximoSorteio = proximoSorteio;
    }

    public Integer getDezena1() {
        return dezena1;
    }

    public void setDezena1(Integer dezena1) {
        this.dezena1 = dezena1;
    }

    public Integer getDezena2() {
        return dezena2;
    }

    public void setDezena2(Integer dezena2) {
        this.dezena2 = dezena2;
    }

    public Integer getDezena3() {
        return dezena3;
    }

    public void setDezena3(Integer dezena3) {
        this.dezena3 = dezena3;
    }

    public Integer getDezena4() {
        return dezena4;
    }

    public void setDezena4(Integer dezena4) {
        this.dezena4 = dezena4;
    }

    public Integer getDezena5() {
        return dezena5;
    }

    public void setDezena5(Integer dezena5) {
        this.dezena5 = dezena5;
    }

    public Integer getDezena6() {
        return dezena6;
    }

    public void setDezena6(Integer dezena6) {
        this.dezena6 = dezena6;
    }

    public BigDecimal getArrecadacaoTotal() {
        return arrecadacaoTotal;
    }

    public void setArrecadacaoTotal(BigDecimal arrecadacaoTotal) {
        this.arrecadacaoTotal = arrecadacaoTotal;
    }

    public Integer getGanhadoresSena() {
        return ganhadoresSena;
    }

    public void setGanhadoresSena(Integer ganhadoresSena) {
        this.ganhadoresSena = ganhadoresSena;
    }

    public Integer getGanhadoresQuina() {
        return ganhadoresQuina;
    }

    public void setGanhadoresQuina(Integer ganhadoresQuina) {
        this.ganhadoresQuina = ganhadoresQuina;
    }

    public Integer getGanhadoresQuadra() {
        return ganhadoresQuadra;
    }

    public void setGanhadoresQuadra(Integer ganhadoresQuadra) {
        this.ganhadoresQuadra = ganhadoresQuadra;
    }

    public BigDecimal getRateioSena() {
        return rateioSena;
    }

    public void setRateioSena(BigDecimal rateioSena) {
        this.rateioSena = rateioSena;
    }

    public BigDecimal getRateioQuina() {
        return rateioQuina;
    }

    public void setRateioQuina(BigDecimal rateioQuina) {
        this.rateioQuina = rateioQuina;
    }

    public BigDecimal getRateioQuadra() {
        return rateioQuadra;
    }

    public void setRateioQuadra(BigDecimal rateioQuadra) {
        this.rateioQuadra = rateioQuadra;
    }

    public boolean isAcumulado() {
        return acumulado;
    }

    public void setAcumulado(boolean acumulado) {
        this.acumulado = acumulado;
    }

    public BigDecimal getValorAcumulado() {
        return valorAcumulado;
    }

    public void setValorAcumulado(BigDecimal valorAcumulado) {
        this.valorAcumulado = valorAcumulado;
    }

    public BigDecimal getEstimativaPremio() {
        return estimativaPremio;
    }

    public void setEstimativaPremio(BigDecimal estimativaPremio) {
        this.estimativaPremio = estimativaPremio;
    }

    public BigDecimal getAcumuladoMegaVirada() {
        return acumuladoMegaVirada;
    }

    public void setAcumuladoMegaVirada(BigDecimal acumuladoMegaVirada) {
        this.acumuladoMegaVirada = acumuladoMegaVirada;
    }

}
