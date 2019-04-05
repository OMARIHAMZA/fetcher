package com.fetcher.core.utils;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.fetcher.R;

public class ImageDialog {

    private Dialog mDialog;

    public ImageDialog(@NonNull Activity activity, String imageUrl) {
        mDialog = new Dialog(activity);
        mDialog.setContentView(R.layout.image_dialog);
        ImageView imageView = mDialog.findViewById(R.id.dialog_imageView);
        Glide.with(activity)
                .load(RetrofitClientInstance.BASE_URL + "fetcher/public/uploads/" + imageUrl)
                .into(imageView);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public void show() {
        mDialog.show();
    }

    public void dismiss() {
        mDialog.dismiss();
    }

}
