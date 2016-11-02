package com.loterias.caixa.megasena.Acitivity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

/**
 * Created by Marcos on 20/10/2016.
 */
public class ViewUtil extends View {

    public ViewUtil(Context context) {
        super(context);
    }

    public void abrirTela(Class classe) {
        Intent menuPrincipalView =
                new Intent(this.getContext(), classe);
        menuPrincipalView.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TASK);
        getContext().startActivity(menuPrincipalView);
    }
}
