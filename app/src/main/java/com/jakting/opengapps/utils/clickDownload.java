package com.jakting.opengapps.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;

import com.jakting.opengapps.GuideActivity;
import com.jakting.opengapps.R;


public class ClickDownload {
    public static void ClickDownload(final Context context, final SharedPreferences data_of_download){
        /**
         * 性感下载，在线猛击。
         *
         * @param context,view
         * @return
         */

        final String date = data_of_download.getString("date","");
        final String a_v = data_of_download.getString("android_version","");
        final String c_u = data_of_download.getString("cpu","");
        final String v_r = data_of_download.getString("var","");
        AlertDialog.Builder Aldialog = new AlertDialog.Builder(context);
        Aldialog.setTitle(context.getString(R.string.download_confirm));
        Aldialog.setMessage(context.getString(R.string.download_confirm_1)+a_v+context.getString(R.string.download_confirm_2)+c_u+context.getString(R.string.download_confirm_3)+v_r+context.getString(R.string.download_confirm_4));
        Aldialog.setPositiveButton(R.string.download, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Uri download_url = Uri.parse("https://github.com/opengapps/"+c_u+"/releases/download/"+date+"/open_gapps-"+c_u+"-"+a_v+"-"+v_r+"-"+date+".zip");
                context.startActivity(new Intent(Intent.ACTION_VIEW,download_url));
            }
        });
        Aldialog.setNeutralButton(R.string.correct, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferences.Editor firstRun = context.getSharedPreferences("data", Context.MODE_PRIVATE).edit();
                firstRun.putBoolean("firstRun",false);
                Intent resetWizard = new Intent(context,GuideActivity.class);
                context.startActivity(resetWizard);
            }
        });
        AlertDialog al = Aldialog.create();
        al.show();
    }
}
