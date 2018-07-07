
package com.example.sheng.mplayerv1.callback;

import android.app.Activity;
import android.app.ProgressDialog;

import com.afollestad.materialdialogs.MaterialDialog;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.base.Request;

/**此类来自okgo网络框架的demo
 * ================================================
 * 作    者：jeasonlzy（廖子尧）
 * 版    本：1.0
 * 创建日期：2016/4/8
 * 描    述：我的Github地址  https://github.com/jeasonlzy
 * 修订历史：
 * ================================================
 */
public abstract class StringDialogCallback extends StringCallback {

    private ProgressDialog dialog;
    private MaterialDialog materialDialog;
    public StringDialogCallback(Activity activity) {
        materialDialog=new MaterialDialog.Builder(activity)
                .title("稍等片刻")
                .content("正在卖力搜索中...")
                .progress(true, 0)
                .progressIndeterminateStyle(false)
                .show();


    }

    @Override
    public void onStart(Request<String, ? extends Request> request) {
//        if (dialog != null && !dialog.isShowing()) {
//            dialog.show();
//        }

    }

    @Override
    public void onFinish() {
//        if (dialog != null && dialog.isShowing()) {
//            dialog.dismiss();
//        }
      materialDialog.dismiss();
    }
}
