package com.example.controleprodutospadaria.utils;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.example.controleprodutospadaria.R;

public class UtilsGUI {

    public static void confirmarAcao(Context contexto, String mensagem, DialogInterface.OnClickListener listener){

        AlertDialog.Builder builder =  new AlertDialog.Builder(contexto);

        builder.setTitle(R.string.confirmacao);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setMessage(mensagem);

        builder.setPositiveButton(R.string.sim, listener);
        builder.setNegativeButton(R.string.nao, listener);

        AlertDialog alert = builder.create();
        alert.show();
    }

}
