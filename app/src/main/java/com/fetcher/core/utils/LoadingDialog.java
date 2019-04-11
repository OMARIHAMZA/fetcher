package com.fetcher.core.utils;


import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.fetcher.R;


public class LoadingDialog {

    private Dialog mDialog;
    private Activity mActivity;

    public LoadingDialog(Activity mActivity) {
        mDialog = new Dialog(mActivity);
        mDialog.setContentView(R.layout.loading_dialog);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.setCancelable(false);
    }


    public void show() {
        mDialog.show();
    }

    public void dismiss() {
        try {
            mDialog.dismiss();
        } catch (Exception ignored) {
        }
    }

}
