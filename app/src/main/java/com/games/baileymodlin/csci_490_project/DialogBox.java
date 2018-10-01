package com.games.baileymodlin.csci_490_project;


import android.app.AlertDialog;
import android.app.Dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class DialogBox extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedState){
       AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
       builder.setMessage(R.string.dialogCancel).setPositiveButton(R.string.yesButton, new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {

           }
       }).setNegativeButton(R.string.noButton, new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {

           }
       });
       return builder.create();
   }
}
