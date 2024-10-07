package com.example.controleprodutospadaria.modelo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Comparator;

@Entity
public class Produto {

    public static Comparator ordenacaoCrescente = new Comparator<Produto>() {
        @Override
        public int compare(Produto o1, Produto o2) {
            return o1.getNomeProduto().compareToIgnoreCase(o2.getNomeProduto());
        }
    };

    public static Comparator ordenacaoDecrecente = new Comparator<Produto>() {
        @Override
        public int compare(Produto o1, Produto o2) {
            return -1 * o1.getNomeProduto().compareToIgnoreCase(o2.getNomeProduto());
        }
    };

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String nomeProduto;
    private String localProduto;
    private String tipoProduto;
    private int dataValidade;

    public Produto(String nomeProduto, String localProduto, String tipoProduto, int dataValidade) {
        this.nomeProduto = nomeProduto;
        this.localProduto = localProduto;
        this.tipoProduto = tipoProduto;
        this.dataValidade = dataValidade;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getLocalProduto() {
        return localProduto;
    }

    public void setLocalProduto(String localProduto) {
        this.localProduto = localProduto;
    }

    public String getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public int getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(int dataValidade) {
        this.dataValidade = dataValidade;
    }

    @Override
    public String toString() {
        return nomeProduto + "\n" + localProduto + tipoProduto +
                "\n" + dataValidade + "\n";
    }
}
