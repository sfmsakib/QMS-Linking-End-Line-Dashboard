package com.sqgc.qms_tv_dashboard_linking.model.util;

import android.app.Activity;
import android.app.Dialog;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sqgc.qms_tv_dashboard_linking.R;

public class Loader {

    Activity activity;
    Dialog dialog;

    //..we need the context else we can not create the dialog so get context in constructor
    public Loader(Activity activity) {
        this.activity = activity;
    }

    public void show() {

        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //...set cancelable false so that it's never get hidden
        dialog.setCancelable(false);
        //...that's the layout i told you will inflate later
        dialog.setContentView(R.layout.custom_loading_layout);
        dialog.setCanceledOnTouchOutside(false);

        //...initialize the imageView form infalted layout
        ImageView gifImageView = dialog.findViewById(R.id.custom_loading_imageView);


        //...now load that gif which we put inside the drawable folder here with the help of Glide

        Glide.with(activity)
                .load(R.drawable.loader)
                .placeholder(R.drawable.loader)
                .centerCrop()
                .into(gifImageView);

        //...finally show it
        dialog.show();
    }


    //..also create a method which will hide the dialog when some work is done
    public void hide() {
        try {
            if (dialog != null) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}