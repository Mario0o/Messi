package com.adu.messi.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.adu.messi.R;

/**
 * 项目名：  Messi
 * 包名：    com.adu.messi.view
 * 文件名:   HintDialog
 * 创建者:   dpc
 * 创建时间:  2016/11/20 19:03
 * 描述：    自定义的仿照iOS的dialog
 */

public class HintDialog extends Dialog {

    public HintDialog(Context context) {
        super(context);
    }

    public HintDialog(Context context, int themeId) {
        super(context, themeId);
    }

    protected HintDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder {
        private Context context;
        private String message = null;
        private String title = null;
        private String cancelText = null;
        private String confirmText = null;
        private Boolean isShowConfirmButton = true;
        private Boolean isShowCancelButton = true;
        private DialogInterface.OnClickListener confirmBtnClickListener;
        private DialogInterface.OnClickListener cancelBtnClickListener;

        private int width;
        private int height;

        public Builder(Context context) {
            this.context = context;
        }

        //设置要显示的信息
        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }

        //设置要显示的标题
        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setCancelText(String cancelText) {
            this.cancelText = cancelText;
            return this;
        }

        public Builder setConfirmText(String confirmText) {
            this.confirmText = confirmText;
            return this;
        }

        //设置确定按钮的监听事件
        public Builder setConfirmBtnListener(DialogInterface.OnClickListener listener) {
            this.confirmBtnClickListener = listener;
            return this;
        }

        //设置取消按钮的监听事件
        public Builder setCancelBtnListener(DialogInterface.OnClickListener listener) {
            this.cancelBtnClickListener = listener;
            return this;
        }

        //设置确定按钮是否显示，false为不显示
        public Builder setIsShowConfirmButton(Boolean isShowConfirmButton) {
            this.isShowConfirmButton = isShowConfirmButton;
            return this;
        }

        //设置取消按钮是否显示，false为不显示
        public Builder setIsShowCancelButton(Boolean isShowCancelButton) {
            this.isShowCancelButton = isShowCancelButton;
            return this;
        }

        public HintDialog onCreate() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final HintDialog dialog = new HintDialog(context, R.style.hintDialog);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    return keyCode == KeyEvent.KEYCODE_BACK;
                }
            });
            dialog.setCancelable(false);
            View layout = inflater.inflate(R.layout.hint_dialog, null);

            if (!isShowCancelButton) {
                layout.findViewById(R.id.addview).setVisibility(View.GONE);
                layout.findViewById(R.id.cancel_btn).setVisibility(View.GONE);
                layout.findViewById(R.id.confirm_btn).setBackgroundResource(R.drawable.select_center_btn);
            }
            if (!isShowConfirmButton) {
                layout.findViewById(R.id.addview).setVisibility(View.GONE);
                layout.findViewById(R.id.confirm_btn).setVisibility(View.GONE);
                layout.findViewById(R.id.cancel_btn).setBackgroundResource(R.drawable.select_center_btn);
            }
            if (message != null) {
                ((TextView) layout.findViewById(R.id.dialog_message)).setText(message);
            }
            if (title != null) {
                ((TextView) layout.findViewById(R.id.dialog_title)).setText(title);
            }
            if (cancelText != null) {
                ((Button) layout.findViewById(R.id.cancel_btn)).setText(cancelText);
            }
            if (confirmText != null) {
                ((Button) layout.findViewById(R.id.confirm_btn)).setText(confirmText);
            }
            if (cancelBtnClickListener != null) {
                layout.findViewById(R.id.cancel_btn)
                    .setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            cancelBtnClickListener.onClick(dialog,
                                DialogInterface.BUTTON_NEGATIVE);
                        }
                    });
            } else {
                layout.findViewById(R.id.cancel_btn)
                    .setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            dialog.cancel();
                        }
                    });
            }

            if (confirmBtnClickListener != null) {
                layout.findViewById(R.id.confirm_btn)
                    .setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            confirmBtnClickListener.onClick(dialog,
                                DialogInterface.BUTTON_POSITIVE);
                        }
                    });
            } else {
                layout.findViewById(R.id.confirm_btn)
                    .setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            dialog.cancel();
                        }
                    });
            }
            displaySize();
            dialog.setContentView(layout, new ViewGroup.LayoutParams(width, height));
            return dialog;
        }

        private void displaySize() {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x;
            int height = size.y;
            this.width = (int) (width * 0.68);
            this.height = (int) (height * 0.22);
        }

    }

}


