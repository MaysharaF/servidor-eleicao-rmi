package com.mycompany.servidor.eleicao.rmi;
/**
 *
 * @author Mayshara
 */
public class Candidato {
    private int quantidade;
    private int id;

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    private String nome;

    public Candidato(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void contaVoto() {
        this.quantidade += 1;
    }       
}
