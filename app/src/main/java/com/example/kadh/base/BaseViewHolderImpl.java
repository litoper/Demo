package com.example.kadh.base;

import android.support.annotation.IdRes;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/28
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class BaseViewHolderImpl extends BaseViewHolder {
    public BaseViewHolderImpl(View view) {
        super(view);
    }

    public BaseViewHolder setHint(@IdRes int viewId, CharSequence value) {
        TextView view = getView(viewId);
        view.setHint(value);
        return this;
    }
}
