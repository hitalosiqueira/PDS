/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author daniel
 */
public class Lote {

    private int codigo;
    private int codigo_produto;
    private Date dt_fabricacao;
    private Date dt_validade;
    private int qtde_inicial;
    private int qtde_atual;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo_produto() {
        return codigo_produto;
    }

    public void setCodigo_produto(int codigo_produto) {
        this.codigo_produto = codigo_produto;
    }

    public Date getDt_fabricacao() {
        return dt_fabricacao;
    }

    public void setDt_fabricacao(Date dt_fabricacao) {
        this.dt_fabricacao = dt_fabricacao;
    }

    public Date getDt_validade() {
        return dt_validade;
    }

    public void setDt_validade(Date dt_validade) {
        this.dt_validade = dt_validade;
    }

    public int getQtde_inicial() {
        return qtde_inicial;
    }

    public void setQtde_inicial(int qtde_inicial) {
        this.qtde_inicial = qtde_inicial;
    }

    public int getQtde_atual() {
        return qtde_atual;
    }

    public void setQtde_atual(int qtde_atual) {
        this.qtde_atual = qtde_atual;
    }

}
