package com.example.kadh.utils;

import android.app.Activity;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.List;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/25
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class MdAlterHelper {

    private Activity mActivity;
    private final MaterialDialog.Builder mBuilder;


    public MdAlterHelper(Activity activity) {
        mActivity = activity;
        mBuilder = new MaterialDialog.Builder(activity);
    }


    public void showConfirmDialog(String title, String content, String positiveText, int positiveColor, MaterialDialog.SingleButtonCallback buttonCallback) {
        mBuilder
                .title(title)
                .content(content)
                .positiveText(positiveText)
                .positiveColor(positiveColor)
                .onPositive(buttonCallback)
                .show();
    }

    public void showCancelDialog(String title,
                                 String content,
                                 String positiveText,
                                 String negativeText,
                                 MaterialDialog.SingleButtonCallback positiveCallback,
                                 MaterialDialog.SingleButtonCallback negativeCallback) {
        mBuilder.title(title)
                .content(content);

        mBuilder.positiveText(positiveText)
                .onPositive(positiveCallback);

        mBuilder.negativeText(negativeText)
                .onNegative(negativeCallback);

        mBuilder.show();
    }

    public void showSingleDialog(boolean cancelable,
                                 String title,
                                 String content,
                                 String positiveText,
                                 List<String> items,
                                 int selectedIndex,
                                 MaterialDialog.ListCallbackSingleChoice singleChoice) {
        mBuilder
                .canceledOnTouchOutside(cancelable)
                .title(title)
                .content(content)
                .positiveText(positiveText)
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

        mBuilder
                .canceledOnTouchOutside(cancelable)
                .title(title)
                .content(content)
                .positiveText(positiveText)
                .items(items)
                .itemsCallbackMultiChoice(selectedIndices, multiChoice)
                .show();
    }




}
