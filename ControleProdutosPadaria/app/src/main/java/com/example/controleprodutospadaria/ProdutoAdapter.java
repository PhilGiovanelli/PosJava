package com.example.controleprodutospadaria;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.controleprodutospadaria.modelo.Produto;

import java.util.List;

public class ProdutoAdapter extends BaseAdapter {

    private Context context;
    private List<Produto> produtos;

    public ProdutoAdapter(Context context, List<Produto> produtos) {
        this.context = context;
        this.produtos = produtos;
    }

    @Override
    public int getCount() {
        return produtos.size();
    }

    @Override
    public Object getItem(int position) {
        return produtos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ProdutoHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_lista_produto, parent, false);

            holder = new ProdutoHolder();

            holder.textViewValorProduto = convertView.findViewById(R.id.textViewValorProduto);
            holder.textViewValorTipoProduto = convertView.findViewById(R.id.textViewValorTipoProduto);
            holder.textViewValorLocalProduto = convertView.findViewById(R.id.textViewValorLocalProduto);
            holder.textViewValorDataValidade = convertView.findViewById(R.id.textViewValorDataValidade);

            convertView.setTag(holder);

        } else {

            holder = (ProdutoHolder) convertView.getTag();
        }

        holder.textViewValorProduto.setText(produtos.get(position).getNomeProduto());
        holder.textViewValorTipoProduto.setText(produtos.get(position).getTipoProduto());
        holder.textViewValorLocalProduto.setText(produtos.get(position).getLocalProduto());
        holder.textViewValorDataValidade.setText(String.valueOf(produtos.get(position).getDataValidade()));


        return convertView;
    }

    private static class ProdutoHolder {
        public TextView textViewValorProduto;
        public TextView textViewValorTipoProduto;
        public TextView textViewValorLocalProduto;
        public TextView textViewValorDataValidade;
    }
}