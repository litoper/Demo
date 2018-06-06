package com.example.kadh.ui.company.contract;

import com.example.kadh.base.BaseContract;
import com.example.kadh.ui.company.bean.UpManListBean;
import com.example.kadh.ui.company.bean.CommentListBean;
import com.example.kadh.ui.company.bean.PublishNewDetailBean;
import com.example.kadh.ui.company.bean.UpNumberBean;

import java.util.List;

public interface NewsDetailAtyContract {

    interface View extends BaseContract.BaseView {
        void showNewsDetailByPublishId(PublishNewDetailBean publishNewDetailBean);

        void showCommentList(List<CommentListBean> data, String total);

        void showUpManList(List<UpManListBean> data, String total);

        void upNumberSuccess(UpNumberBean upNumberBean);

        void addCommentSuccess(PublishNewDetailBean detailBean);

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getNewsDetailByPublishId(String publishId);

        void getCommentList(int page, String id, String ptype);

        void getUpManList(int page, String id, String ptype);

        void addComment(String ptype, String id, String remark_comment);

        void delComment(String ptype, String remarkId);

        void upNumber(String ptype, String proPublishId, PublishNewDetailBean detailBean);
    }
}
