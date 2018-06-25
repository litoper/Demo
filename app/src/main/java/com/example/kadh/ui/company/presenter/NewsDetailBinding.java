package com.example.kadh.ui.company.presenter;

import com.example.kadh.base.BaseBindingImpl;
import com.example.kadh.ui.company.bean.CommentListBean;
import com.example.kadh.ui.company.bean.PublishNewDetailBean;
import com.example.kadh.ui.company.bean.UpManListBean;
import com.example.kadh.ui.company.bean.UpNumberBean;
import com.example.kadh.ui.company.contract.NewsDetailAtyContract;
import com.example.kadh.utils.RxJava.BaseResponse;
import com.example.kadh.utils.RxJava.RxApi.RxApi;
import com.example.kadh.utils.RxJava.RxSubscriber.SubNextImpl;
import com.example.kadh.utils.RxJava.RxSubscriber.SubProtect;

import java.util.List;

import javax.inject.Inject;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/6
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class NewsDetailBinding extends BaseBindingImpl<NewsDetailAtyContract.View> implements NewsDetailAtyContract.Presenter<NewsDetailAtyContract.View> {
    private RxApi mRxApi;

    @Inject
    public NewsDetailBinding(RxApi rxApi) {
        mRxApi = rxApi;
    }

    @Override
    public void getNewsDetailByPublishId(String publishId) {
        mRxApi.getNewsDetailByPublishId(new SubProtect<BaseResponse<List<PublishNewDetailBean>>>(new SubNextImpl<BaseResponse<List<PublishNewDetailBean>>>() {
            @Override
            public void onSubSuccess(BaseResponse<List<PublishNewDetailBean>> response) {
                mView.showNewsDetailByPublishId(response.data.get(0));
            }
        }), publishId);
    }

    @Override
    public void getCommentList(int page, String id, String ptype) {
        mRxApi.getCommentList(new SubProtect<BaseResponse<List<CommentListBean>>>(new SubNextImpl<BaseResponse<List<CommentListBean>>>() {
            @Override
            public void onSubSuccess(BaseResponse<List<CommentListBean>> response) {
                mView.showCommentList(response.data, response.total);
            }
        }), String.valueOf(page), id, ptype);
    }

    @Override
    public void getUpManList(int page, String id, String ptype) {
        mRxApi.getUpManList(new SubProtect<BaseResponse<List<UpManListBean>>>(new SubNextImpl<BaseResponse<List<UpManListBean>>>() {
            @Override
            public void onSubSuccess(BaseResponse<List<UpManListBean>> response) {
                mView.showUpManList(response.data, response.total);
            }
        }), String.valueOf(page), id, ptype);
    }

    @Override
    public void addComment(String ptype, String id, String remark_comment) {

        mRxApi.addComment(new SubProtect<BaseResponse<List<PublishNewDetailBean>>>(new SubNextImpl<BaseResponse<List<PublishNewDetailBean>>>() {
            @Override
            public void onSubSuccess(BaseResponse<List<PublishNewDetailBean>> response) {
                mView.addCommentSuccess(response.data.get(0));
            }
        }), ptype, id, remark_comment);
    }

    @Override
    public void delComment(String ptype, String remarkId) {
        mRxApi.delComment(new SubProtect<BaseResponse<String>>(new SubNextImpl<BaseResponse<String>>() {
            @Override
            public void onSubSuccess(BaseResponse<String> response) {

            }
        }), ptype, remarkId);
    }

    /**
     * @param ptype
     * @param proPublishId
     * @param detailBean
     */
    @Override
    public void upNumber(String ptype, String proPublishId, PublishNewDetailBean detailBean) {

        SubProtect<BaseResponse<List<UpNumberBean>>> upNumberSub = new SubProtect<>(new SubNextImpl<BaseResponse<List<UpNumberBean>>>() {
            @Override
            public void onSubSuccess(BaseResponse<List<UpNumberBean>> response) {
                mView.upNumberSuccess(response.data.get(0));
            }
        });

        switch (detailBean.getUped()) {
            case "0"://0:点赞
                mRxApi.upNumber(upNumberSub, ptype, proPublishId, "1");
                break;
            case "1"://取消
                mRxApi.upNumber(upNumberSub, ptype, proPublishId, "0");
                break;
            default:
                break;
        }
    }
}
