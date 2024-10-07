package com.example.controleprodutospadaria;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;

import com.example.controleprodutospadaria.modelo.Produto;
import com.example.controleprodutospadaria.persistencia.ProdutoDatabase;
import com.example.controleprodutospadaria.utils.UtilsGUI;

import java.util.Collections;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    public static final String ARQUIVO = "com.exemple.sharedpreferences.PREFERENCIAIS";
    public static final String ORDENACAO_ASCENDENTE = "ORDENACAO_ASCENDENTE";
    private List<Produto> produtos;
    private ListView listViewProdutos;
    //    private ArrayList<Produto> listaProdutos;
    private ProdutoAdapter produtoAdapter;
    private ActionMode actionMode;
    private View viewSelecionada;
    private int posicaoSelecionada = -1;
    private boolean ordenacaoAscendente = true;
    ActivityResultLauncher<Intent> launcherNovoProduto = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {

                        Intent intent = result.getData();

                        Bundle bundle = intent.getExtras();

                        if (bundle != null) {

                           long id = bundle.getLong(MainActivity.ID);

                           ProdutoDatabase database = ProdutoDatabase.getDatabase(MainActivity2.this);

                           Produto produtoInserido = database.getProdutoDao().queryForId(id);

                            produtos.add(produtoInserido);
                            ordenarLista();
                        }
                    }
                }
            });
    ActivityResultLauncher<Intent> launcherEditarProduto = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {

                        Intent intent = result.getData();

                        Bundle bundle = intent.getExtras();

                        if (bundle != null) {

                            long id = bundle.getLong(MainActivity.ID);

                            ProdutoDatabase database = ProdutoDatabase.getDatabase(MainActivity2.this);

                            Produto produtoEditado = database.getProdutoDao().queryForId(id);

                          produtos.set(posicaoSelecionada,produtoEditado);
                            posicaoSelecionada = -1;
                            ordenarLista();


                        }
                    }
                }
            });
    private ActionMode.Callback actionCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.principal_item_selecionado, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            int idMenuItem = item.getItemId();

            if (idMenuItem == R.id.menuItemEditar) {
                editarProduto();
                mode.finish();
                return true;

            } else if (idMenuItem == R.id.menuItemExcluir) {
                excluirPessoa(mode);
                return true;

            } else {
                return false;
            }


        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

            if (viewSelecionada != null) {
                viewSelecionada.setBackgroundColor(Color.TRANSPARENT);
            }
            actionMode = null;
            viewSelecionada = null;

            listViewProdutos.setEnabled(true);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listViewProdutos = findViewById(R.id.listViewProdutos);

        listViewProdutos.setOnItemClickListener((parent, view, position, id) -> {
            Produto produto = (Produto) listViewProdutos.getItemAtPosition(position);
            String mensagem = getString(R.string.produto1) + produto.getNomeProduto() + "\n" +
                    getString(R.string.tipo_do_produto) + produto.getTipoProduto() + "\n" +
                    getString(R.string.local_do_produto) + produto.getLocalProduto() +
                    getString(R.string.validade) + produto.getDataValidade() + getString(R.string.diaas)+ "\n" +
                    getString(R.string.foi_clicado);

            Toast.makeText(MainActivity2.this, mensagem , Toast.LENGTH_SHORT).show();
        });

        listViewProdutos.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listViewProdutos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                if (actionMode != null) {
                    return false;
                }

                posicaoSelecionada = position;
                view.setBackgroundColor(Color.LTGRAY);
                viewSelecionada = view;
                listViewProdutos.setEnabled(false);

                actionMode = startSupportActionMode(actionCallback);
                return false;
            }
        });

        lerPreferenciaOrdenacaoAsc();

        popularLista();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int botao = item.getItemId();

        if (botao == R.id.menuItemOrdenacao) {
            salvarPreferenciaOrdenacaoAsc(!ordenacaoAscendente);
            atualizarIcone(item);
            ordenarLista();
            return true;

        } else {
            return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.principal_opcoes, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem menuItemOrd= menu.findItem(R.id.menuItemOrdenacao);
        atualizarIcone(menuItemOrd);
        return true;
    }

    public void sobre(MenuItem item) {
        MainActivity3.nova(this);
    }

    public void novoProduto(MenuItem item) {

        MainActivity.novoProduto(this, launcherNovoProduto);
    }


    private void popularLista() {

       ProdutoDatabase database = ProdutoDatabase.getDatabase(this);

       if (ordenacaoAscendente){
           produtos = database.getProdutoDao().queryAllAscending();

       }else{
           produtos = database.getProdutoDao().queryAllDownward();
       }

        produtoAdapter = new ProdutoAdapter(this, produtos);

        listViewProdutos.setAdapter(produtoAdapter);

    }

    private void editarProduto() {
        Produto produto = produtos.get(posicaoSelecionada);
        MainActivity.editarProduto(this, launcherEditarProduto, produto);
    }

    private void excluirPessoa( final ActionMode mode) {

       final Produto produto = produtos.get(posicaoSelecionada);
        String mensagem = getString(R.string.deseja_realmente_apagar) + "\n" + "\"" + produto.getNomeProduto() + "\"";

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which){

                    case DialogInterface.BUTTON_POSITIVE:

                        ProdutoDatabase database = ProdutoDatabase.getDatabase(MainActivity2.this);

                        int qtdeAlterada = database.getProdutoDao().delete(produto);

                        if (qtdeAlterada > 0){
                            produtos.remove(posicaoSelecionada);
                            produtoAdapter.notifyDataSetChanged();
                            mode.finish();
                        }else{
                            Toast.makeText(MainActivity2.this, R.string.erro_ao_tentar_apagar,Toast.LENGTH_LONG).show();
                        }


                        break;

                    case DialogInterface.BUTTON_NEGATIVE:

                        break;
                }
            }
        };

        UtilsGUI.confirmarAcao(this, mensagem,listener);
    }

    private void atualizarIcone(MenuItem menuItemOrdenacao){
        if (ordenacaoAscendente){
            menuItemOrdenacao.setIcon(R.drawable.ic_action_asc);
        }else {
            menuItemOrdenacao.setIcon(R.drawable.ic_action_des);
        }
    }

    private void ordenarLista() {
        if (ordenacaoAscendente) {
            Collections.sort(produtos, Produto.ordenacaoCrescente);
        } else {
            Collections.sort(produtos, Produto.ordenacaoDecrecente);
        }
        produtoAdapter.notifyDataSetChanged();

    }

    private void lerPreferenciaOrdenacaoAsc() {

        SharedPreferences shared = getSharedPreferences(ARQUIVO, Context.MODE_PRIVATE);
        ordenacaoAscendente = shared.getBoolean(ORDENACAO_ASCENDENTE, ordenacaoAscendente);
    }

    private void salvarPreferenciaOrdenacaoAsc(boolean novoValor) {

        SharedPreferences shared = getSharedPreferences(ARQUIVO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putBoolean(ORDENACAO_ASCENDENTE, novoValor);
        editor.commit();
        ordenacaoAscendente = novoValor;
    }
}
