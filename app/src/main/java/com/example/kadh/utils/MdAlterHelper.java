package com.example.kadh.utils;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.InputType;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.kadh.R;
import com.example.kadh.bean.support.IsingleChoiceBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/25
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class MdAlterHelper<T> {

    private Activity mActivity;
    private MaterialDialog mMaterialDialog;
    private MaterialDialog.Builder mBuilder;


    public MdAlterHelper(Activity activity) {
        mActivity = activity;
    }


    public void showConfirmDialog(String title, String content, String positiveText, int positiveColor, MaterialDialog.SingleButtonCallback buttonCallback) {
        new MaterialDialog
                .Builder(mActivity)
                .title(title)
                .content(content)
                .positiveText(positiveText)
                .positiveColor(positiveColor)
                .onPositive(buttonCallback)
                .show();
    }

    public void showNormalDialog(String title,
                                 String content,
                                 String positiveText,
                                 String negativeText,
                                 MaterialDialog.SingleButtonCallback positiveCallback,
                                 MaterialDialog.SingleButtonCallback negativeCallback) {
        new MaterialDialog
                .Builder(mActivity)
                .autoDismiss(false)
                .title(title)
                .content(content)
                .positiveText(positiveText)
                .positiveColor(mActivity.getResources().getColor(R.color.blue))
                .onPositive(positiveCallback)
                .negativeText(negativeText)
                .negativeColor(mActivity.getResources().getColor(R.color.light_red))
                .onNegative(negativeCallback)
                .show();
    }

    public void showSingleDialog(boolean cancelable,
                                 String title,
                                 List<String> items,
                                 int selectedIndex,
                                 MaterialDialog.ListCallbackSingleChoice singleChoice) {

        new MaterialDialog
                .Builder(mActivity)
                .canceledOnTouchOutside(cancelable)
                .title(title)
                .items(items)
                .itemsCallbackSingleChoice(selectedIndex, singleChoice)
                .show();
    }

    public void showMultiDialog(boolean cancelable,
                                String title,
                                String content,
                                String positiveText,
                                List<String> items,
                                Integer[] selectedIndices,
                                MaterialDialog.ListCallbackMultiChoice multiChoice) {
        new MaterialDialog
                .Builder(mActivity)
                .canceledOnTouchOutside(cancelable)
                .title(title)
                .content(content)
                .positiveText(positiveText)
                .items(items)
                .itemsCallbackMultiChoice(selectedIndices, multiChoice)
                .show();
    }

    public void showInputDialog(String title,
                                String content,
                                String positiveText,
                                String hintText,
                                String prefill,
                                int minLength,
                                int maxLength,
                                final IclickCallBack iclickCallBack,
                                final IshowInputCallBack inputCallBack) {


        new MaterialDialog
                .Builder(mActivity)
                .autoDismiss(false)
                .title(title)
                .content(content)
                .positiveText(positiveText)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        iclickCallBack.onClick(dialog, dialog.getInputEditText().getText().toString(), which);

                    }
                })
                .inputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME | InputType.TYPE_TEXT_FLAG_CAP_WORDS)
                .inputRange(minLength, maxLength)
                .input(hintText, prefill, false, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                        if (inputCallBack != null) {
                            inputCallBack.onInput(String.valueOf(input));
                        }
                    }
                }).show();
    }


    public boolean isShow() {
        return mMaterialDialog != null && mMaterialDialog.isShowing();
    }


    public void displaySingleDialog(boolean cancelable, String title, List<IsingleChoiceBean> items, @NonNull String matchKey, @NonNull final IdisplaySingleCallBack singleCallBack) {
        int selectedIndex = -1;

        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            stringList.add(items.get(i).displayText());
            if (matchKey.equals(items.get(i).displayText())) {
                selectedIndex = i;
            }
        }

        new MaterialDialog.Builder(mActivity)
                .canceledOnTouchOutside(cancelable)
                .title(title)
                .items(stringList)
                .itemsCallbackSingleChoice(selectedIndex, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        if (singleCallBack != null) {
                            singleCallBack.onSelecton(which, String.valueOf(text));
                        }
                        return false;
                    }
                })
                .show();
    }

    public interface IdisplaySingleCallBack {
        void onSelecton(int position, String selectText);
    }

    public interface IshowInputCallBack {
        void onInput(String input);
    }

    public interface IclickCallBack {
        void onClick(MaterialDialog dialog, String inputText, DialogAction which);
    }

}


