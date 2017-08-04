package com.mam.shapp.presentation.widget.dialog;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;
import com.mam.shapp.R;

public class DialogAbstractImpl implements DialogAbstract {

    MaterialDialog.Builder dialog;

    public DialogAbstractImpl() {
    }

    @Override
    public void showSimple(Context context, String title, String description, String textOption) {
        dialog = new MaterialDialog.Builder(context)
                .title(title)
                .content(description)
                .positiveText(textOption)
                .positiveColorRes(R.color.colorPrimary);
        dialog.show();
    }
}