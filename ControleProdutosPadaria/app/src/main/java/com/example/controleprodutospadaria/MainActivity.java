package com.example.controleprodutospadaria;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.controleprodutospadaria.modelo.Produto;
import com.example.controleprodutospadaria.persistencia.ProdutoDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String MODO = "MODO";

    public static final String ID = "ID";

    public static final int NOVO = 1;
    public static final int EDITAR = 2;
    private int modo;
    private Produto produtoOriginal;
    private EditText editTextProduto;
    private CheckBox cbMesa, cbVitGelada, cbVitQuente;
    private RadioGroup radioGroup;
    private Spinner spinner;

    public static void novoProduto(AppCompatActivity activity, ActivityResultLauncher<Intent> launcher) {
        Intent intent = new Intent(activity, MainActivity.class);

        intent.putExtra(MODO, NOVO);

        launcher.launch(intent);
    }

    public static void editarProduto(AppCompatActivity activity, ActivityResultLauncher<Intent> launcher, Produto produto) {
        Intent intent = new Intent(activity, MainActivity.class);

        intent.putExtra(MODO, EDITAR);
        intent.putExtra(ID, produto.getId());

        launcher.launch(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextProduto = findViewById(R.id.editTextProduto);
        radioGroup = findViewById(R.id.radioGroup);
        cbMesa = findViewById(R.id.checkBoxMesa);
        cbVitGelada = findViewById(R.id.checkBoxVitGelada);
        cbVitQuente = findViewById(R.id.checkBoxVitQuente);
        spinner = findViewById(R.id.spinner);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        popularSpinner();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            modo = bundle.getInt(MODO, NOVO);
            if (modo == NOVO) {
                setTitle(getString(R.string.novo_produto));
                produtoOriginal = new Produto("", "", "", 0);

            } else if (modo == EDITAR) {
                setTitle(getString(R.string.editar_produto));

                long id = bundle.getLong(ID);

                ProdutoDatabase database = ProdutoDatabase.getDatabase(this);

                produtoOriginal = database.getProdutoDao().queryForId(id);

                editTextProduto.setText(produtoOriginal.getNomeProduto());
                radioGroup.setActivated(Boolean.parseBoolean(produtoOriginal.getTipoProduto()));
                spinner.setSelection(produtoOriginal.getDataValidade());
                cbMesa.setChecked(Boolean.parseBoolean(produtoOriginal.getLocalProduto()));
                cbVitQuente.setChecked(Boolean.parseBoolean(produtoOriginal.getLocalProduto()));
                cbVitGelada.setChecked(Boolean.parseBoolean(produtoOriginal.getLocalProduto()));

                editTextProduto.setSelection(editTextProduto.getText().length());


            }
        }
        selecionarItemSpinner(produtoOriginal.getDataValidade());
    }

    private void popularSpinner() {
        ArrayList<Integer> lista = new ArrayList<>();

        lista.add(3);
        lista.add(7);
        lista.add(12);
        lista.add(21);

        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);

        spinner.setAdapter(adapter);

    }

    private void selecionarItemSpinner(int valor) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(valor)) {
                spinner.setSelection(i);
                break;
            }
        }
    }

    public void limparFormulario(MenuItem item) {
        editTextProduto.setText(null);
        cbMesa.setChecked(false);
        cbVitGelada.setChecked(false);
        cbVitQuente.setChecked(false);
        radioGroup.clearCheck();
        spinner.setSelection(0);


        editTextProduto.requestFocus();
        Toast.makeText(this, R.string.campos_limpos, Toast.LENGTH_SHORT).show();
    }

    public void adicionarProduto(MenuItem item) {


        String mensagem = "";
        String mensagemRadio;
        Integer mensagemSpinner = (Integer) spinner.getSelectedItem();
        String produto = editTextProduto.getText().toString();

        if (produto == null || produto.trim().isEmpty()) {
            Toast.makeText(this, R.string.erro_nome, Toast.LENGTH_SHORT).show();
            editTextProduto.requestFocus();
            return;
        }


        if (cbMesa.isChecked()) {
            mensagem += getString(R.string.mesa) + "\n";
        }
        if (cbVitGelada.isChecked()) {
            mensagem += getString(R.string.vitrineGelada) + "\n";
        }
        if (cbVitQuente.isChecked()) {
            mensagem += getString(R.string.vitrineQuente) + "\n";
        }
        if (mensagem.equals("")) {
            mensagem = getString(R.string.selecioneCampo);
            Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
            return;

        }
        int opcao = radioGroup.getCheckedRadioButtonId();

        if (opcao == R.id.radioButtonDoces) {
            mensagemRadio = getString(R.string.doces);
        } else if (opcao == R.id.radioButtonSalgados) {
            mensagemRadio = getString(R.string.salgados);
        } else if (opcao == R.id.radioButtonBolos) {
            mensagemRadio = getString(R.string.bolos);
        } else if (opcao == R.id.radioButtonPaes) {
            mensagemRadio = getString(R.string.paes);
        } else {
            mensagemRadio = getString(R.string.selecione_uma_categoria_de_produto);
            Toast.makeText(this, mensagemRadio, Toast.LENGTH_SHORT).show();
            radioGroup.requestFocus();
            return;
        }


        Toast.makeText(this, produto.trim() + getString(R.string.foi_adicionado) + "\n\n" + getString(R.string.localProduto) + mensagem + "\n" + getString(R.string.tipo_do_produto) + mensagemRadio + "\n\n" + getString(R.string.validade) + mensagemSpinner + getString(R.string.diaas), Toast.LENGTH_LONG).show();
        editTextProduto.setText(null);

        Intent intent = new Intent();

        ProdutoDatabase database = ProdutoDatabase.getDatabase(this);

        if (modo == NOVO){

            Produto produto1 = new Produto(produto,mensagem,mensagemRadio,mensagemSpinner);

            long novoID = database.getProdutoDao().insert(produto1);

            if (novoID <=0){
                Toast.makeText(this, R.string.erro_ao_tentar_inserir,Toast.LENGTH_LONG).show();
                return;
            }

            produto1.setId(novoID);


            intent.putExtra(ID, produto1.getId());

        }else{

            Produto produtoAlterado = new Produto(produto,mensagem,mensagemRadio,mensagemSpinner);

            produtoAlterado.setId(produtoOriginal.getId());

            int quantidadeAlterada = database.getProdutoDao().update(produtoOriginal);

            if(quantidadeAlterada == 0){
                Toast.makeText(this, R.string.erro_ao_tentar_alterar,Toast.LENGTH_LONG).show();
                return;
            }

            intent.putExtra(ID, produtoAlterado.getId());
        }

        setResult(Activity.RESULT_OK, intent);
        finish();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.produto_opcoes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);

        }
    }
}