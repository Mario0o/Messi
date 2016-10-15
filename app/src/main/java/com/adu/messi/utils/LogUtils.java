package com.adu.messi.utils;

import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.adu.messi.MyApplication;

/**
 * Created by adu on 2016/10/15.
 */

public class LogUtils {
    public static final boolean DEBUG = true;
    public static final String TAG = "+++++++++++>>>>>";
    private static Toast toast;
    public static void debug(String msg) {
        if (DEBUG) {
            System.err.println(TAG + msg);
            Log.d(TAG, msg);
        }
    }

    public static void showToast( String content) {
        if (toast == null) {  //判断Toast对象是否为空
            toast = Toast.makeText(MyApplication.getContext(), content, Toast.LENGTH_LONG);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

    public static void showSnack(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }
}
