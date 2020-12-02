package com.example.thepurple;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

public class StyleDialogFragment extends DialogFragment {
    private String[] items = new String[]{"生活", "学习", "工作","默认"};
    private int mWhich = 0;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("请选择分区")
                .setSingleChoiceItems(items, mWhich, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        mWhich = which;
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(getActivity(), items[mWhich], Toast.LENGTH_SHORT).show();


                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), items[mWhich], Toast.LENGTH_SHORT).show();
                    }
                });

        return builder.create();
    }
}
