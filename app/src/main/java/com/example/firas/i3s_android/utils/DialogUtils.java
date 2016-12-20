package com.example.firas.i3s_android.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AlignmentSpan;

import com.example.firas.i3s_android.R;

/**
 * Created by hatem.noureddine on 07/10/2015.
 */
public class DialogUtils {

    public static AlertDialog.Builder builder;

    /**
     * Shows an alert dialog with the OK button. When the user presses OK button, the dialog
     * dismisses.
     **/
    public static void showAlertDialog(Context ctx, @StringRes String title, @StringRes String body) {
        showAlertDialog(ctx, title, body, null);
    }

    /**
     * Shows an alert dialog with OK button
     **/
    public static void showAlertDialog(Context ctx, @StringRes String title, @StringRes String body, DialogInterface.OnClickListener okListener) {

        if (okListener == null) {
            okListener = new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            };
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx).setMessage(body).setPositiveButton(android.R.string.ok, okListener).setCancelable(false);

        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }

        builder.show();
    }

    /**
     * Creates a confirmation dialog with Yes-No Button. By default the buttons just dismiss the
     * dialog.
     *
     * @param ctx
     * @param message     Message to be shown in the dialog.
     * @param yesListener Yes click handler
     * @param noListener
     **/
    public static void showConfirmDialog(Context ctx, String message, DialogInterface.OnClickListener yesListener, DialogInterface.OnClickListener noListener) {
        showConfirmDialog(ctx, message, yesListener, noListener, R.string.dialog_OK, R.string.dialog_CANCEL);
    }

    /**
     * Creates a confirmation dialog with Yes-No Button. By default the buttons just dismiss the
     * dialog.
     *
     * @param ctx
     * @param message     Message to be shown in the dialog.
     * @param yesListener Yes click handler
     * @param noListener
     * @param yesLabel    Label for yes button
     * @param noLabel     Label for no button
     **/
    public static void showConfirmDialog(Context ctx, String message, DialogInterface.OnClickListener yesListener, DialogInterface.OnClickListener noListener, int yesLabel, int noLabel) {

        builder = new AlertDialog.Builder(ctx);

        if (yesListener == null) {
            yesListener = new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            };
        }

        if (noListener == null) {
            noListener = new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            };
        }

        builder.setMessage(message).setPositiveButton(yesLabel, yesListener).setNegativeButton(noLabel, noListener).show();


    }

    public static void showConfirmDialog(Context ctx, String title, String message, DialogInterface.OnClickListener yesListener
            , DialogInterface.OnClickListener noListener, int yesLabel, int noLabel) {
        builder = new AlertDialog.Builder(ctx);
        if (yesListener == null) {
            yesListener = new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            };
        }

        if (noListener == null) {
            noListener = new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            };
        }
        builder.setTitle(title);

        AlertDialog alertDialog = builder.setMessage(message).setPositiveButton(yesLabel, yesListener).setNegativeButton(noLabel, noListener).create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    public static void showConfirmDialogLongTitle(Context ctx, String title, String message, DialogInterface.OnClickListener yesListener
            , DialogInterface.OnClickListener noListener, int yesLabel, int noLabel) {
        builder = new AlertDialog.Builder(ctx);
        if (yesListener == null) {
            yesListener = new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            };
        }

        if (noListener == null) {
            noListener = new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            };
        }

        StringBuilder msg = new StringBuilder(title);
        msg.append("\n");
        msg.append("\n");
        msg.append("\n");
        msg.append(message);

        final SpannableString styledResultText = new SpannableString(msg.toString());
        styledResultText.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER), 0, title.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        AlertDialog alertDialog = builder.setMessage(styledResultText).setPositiveButton(yesLabel, yesListener).setNegativeButton(noLabel, noListener).create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    /**
     * Creates a confirmation dialog that show a pop-up with button labeled as parameters labels.
     *
     * @param ctx                 {@link android.app.Activity} {@link Context}
     * @param message             Message to be shown in the dialog.
     * @param dialogClickListener
     * @param positiveBtnLabel    For e.g. "Yes"
     * @param negativeBtnLabel    For e.g. "No"
     **/
    public static void showDialog(Context ctx, String message, String positiveBtnLabel, String negativeBtnLabel, DialogInterface.OnClickListener dialogClickListener) {
        if (dialogClickListener == null) {
            throw new NullPointerException("Action listener cannot be null");
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setMessage(message).setPositiveButton(positiveBtnLabel, dialogClickListener).setNegativeButton(negativeBtnLabel, dialogClickListener).show();
    }

}
