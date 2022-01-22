package pro.abdiel.ciem.utils;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

public class Dialogo {
    private AlertDialog.Builder dialogo;


    public void Mensaje(Context context, String message) {
        final AlertDialog dialogo = new AlertDialog.Builder(context).create();
        dialogo.setTitle("MENSAJE DE CIEM");
        dialogo.setMessage(message);
        dialogo.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        dialogo.show();
    }
}