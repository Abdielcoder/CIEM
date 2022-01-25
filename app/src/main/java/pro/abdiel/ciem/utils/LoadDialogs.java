package pro.abdiel.ciem.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;

import pro.abdiel.ciem.R;

public class LoadDialogs {
    Activity activity;
    AlertDialog alertDialog;

    public LoadDialogs(Activity myactivity) {
       activity = myactivity;
    }

    public void startLoading() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_dialog, null));
        builder.setCancelable(false);
        alertDialog = builder.create();
        alertDialog.show();
    }

    public void desmissionDialog(){
        alertDialog.dismiss();
    }


}