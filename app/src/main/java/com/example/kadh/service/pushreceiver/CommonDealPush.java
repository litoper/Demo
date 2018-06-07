package com.example.kadh.service.pushreceiver;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.kadh.bean.isValiDateProcessPermitBean;
import com.example.kadh.ui.main.activity.MainActivity;
import com.example.kadh.utils.NullUtils;
import com.example.kadh.utils.RxJava.BaseResponse;
import com.example.kadh.utils.RxJava.RxApi.RxManager;
import com.example.kadh.utils.RxJava.RxSubscriber.SubNextImpl;
import com.example.kadh.utils.RxJava.RxSubscriber.SubProtect;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/7
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */

public class CommonDealPush {

    private static final String TAG = "CommonDealPush";
    private Context mContext;
    private String mType;
    private String mAllowSeeId;
    private String mPublishId;
    private Intent intent;
    private isValiDateProcessPermitBean data;

    public CommonDealPush(Context context, String type, String allowSeeId, String publishId) {
        Log.d(TAG, "CommonDealPush() called with: , type = [" + type + "], allowSeeId = [" + allowSeeId + "], publishId = [" + publishId + "]");
        mContext = context;
        mType = NullUtils.filterNull(type);
        mPublishId = NullUtils.filterNull(publishId);
        mAllowSeeId = NullUtils.filterNull(allowSeeId);
    }

    public void doBusiness() {
        intent = new Intent();
        intent.setClass(mContext, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        switch (mType) {//判断消息推送类型 点击后跳转至不同的页面
            case "1"://待办详情
                RxManager.getInstant().getRxApi().isVaLiDateProcessPermit(new SubProtect<BaseResponse<isValiDateProcessPermitBean>>(new SubNextImpl<BaseResponse<isValiDateProcessPermitBean>>() {
                    @Override
                    public void onSubSuccess(BaseResponse<isValiDateProcessPermitBean> response) {
                        data = response.data;
                        switch (data.getPstatus()) {
                            case "0"://待办详情
                                intent.putExtra("allowid", mAllowSeeId);
                                intent.putExtra("id", mPublishId);
                                intent.putExtra("type", "1");
                                mContext.startActivity(intent);
                                break;
                            case "1"://已办详情
                                intent.putExtra("allowid", mAllowSeeId);
                                intent.putExtra("id", mPublishId);
                                intent.putExtra("type", "2");
                                mContext.startActivity(intent);
                                break;
                            default:
                                break;
                        }
                    }
                }), mAllowSeeId);
                break;
            case "2"://已办详情
                intent.putExtra("allowid", mAllowSeeId);
                intent.putExtra("id", mPublishId);
                intent.putExtra("type", "2");
                mContext.startActivity(intent);
                break;
            case "3"://已发起详情
                intent.putExtra("allowid", mPublishId);
                intent.putExtra("id", mAllowSeeId);
                intent.putExtra("type", "3");
                mContext.startActivity(intent);
                break;
            case "4"://收件箱详情
                intent.putExtra("id", mPublishId);
                intent.putExtra("type", "4");
                mContext.startActivity(intent);
                break;
            case "5"://首页新闻详情
                intent.putExtra("id", mPublishId);
                intent.putExtra("type", "5");
                mContext.startActivity(intent);
                break;
            case "6"://首页公告详情
                intent.putExtra("id", mPublishId);
                intent.putExtra("type", "6");
                mContext.startActivity(intent);
                break;
            case "7"://发件箱详情
                intent.putExtra("id", mPublishId);
                intent.putExtra("type", "7");
                mContext.startActivity(intent);
                break;
            case "8"://知会列表
                intent.putExtra("allowid", mAllowSeeId);
                intent.putExtra("id", mPublishId);
                intent.putExtra("type", "8");
                mContext.startActivity(intent);
                break;
            case "9"://核心价值审批页面
                intent.putExtra("allowid", mAllowSeeId);
                intent.putExtra("id", mPublishId);
                intent.putExtra("type", "9");
                mContext.startActivity(intent);
                break;
            case "10"://核心价值详情页
                intent.putExtra("allowid", mAllowSeeId);
                intent.putExtra("id", mPublishId);
                intent.putExtra("type", "10");
                mContext.startActivity(intent);
                break;
            case "11"://正常张力审批
                intent.putExtra("allowid", mAllowSeeId);
                intent.putExtra("id", mPublishId);
                intent.putExtra("type", "11");
                mContext.startActivity(intent);
                break;
            case "12"://正常张力详情页
                intent.putExtra("allowid", mAllowSeeId);
                intent.putExtra("id", mPublishId);
                intent.putExtra("type", "12");
                mContext.startActivity(intent);
                break;
            case "13"://年度目标审核
                intent.putExtra("allowid", mAllowSeeId);
                intent.putExtra("id", mPublishId);
                intent.putExtra("type", "13");
                mContext.startActivity(intent);
                break;
            case "14"://月度目标审核
                intent.putExtra("allowid", mAllowSeeId);
                intent.putExtra("id", mPublishId);
                intent.putExtra("type", "14");
                mContext.startActivity(intent);
                break;
            case "15"://季度目标审核
                intent.putExtra("allowid", mAllowSeeId);
                intent.putExtra("id", mPublishId);
                intent.putExtra("type", "15");
                mContext.startActivity(intent);
                break;
            case "16"://日目标
                intent.putExtra("allowid", mAllowSeeId);
                intent.putExtra("id", mPublishId);
                intent.putExtra("type", "16");
                mContext.startActivity(intent);
                break;
            case "17"://提示去指定月度目标（web专用）
                intent.putExtra("allowid", mAllowSeeId);
                intent.putExtra("id", mPublishId);
                intent.putExtra("type", "17");
                mContext.startActivity(intent);
                break;
            case "18"://价值张力审批
                intent.putExtra("allowid", mAllowSeeId);
                intent.putExtra("id", mPublishId);
                intent.putExtra("type", "18");
                mContext.startActivity(intent);
                break;
            case "19"://价值张力详情
                intent.putExtra("allowid", mAllowSeeId);
                intent.putExtra("id", mPublishId);
                intent.putExtra("type", "19");
                mContext.startActivity(intent);
                break;
            default:
                break;
        }
    }

//1-工作-流程审批 需验证 需设为已阅
//2-工作-已办详细
//3-工作-已发起详细
//4-工作-会议通知详细 需设为已阅
//5-首页-新闻详细
//6-首页-公告详细
//7-会议-我发起的详细
//8-工作-知会审批 需验证 需设为已阅
//9-工作-核心价值观审批 需验证 需设为已阅
//10-核心价值观 知会查看详情
//11-工作-张力审批 三合一界面 需验证 需设为已阅
//12-张力审核完毕,跳转 我的张力详情
//
//13-年度目标审核
//14-月度目标审核
//15-季度目标审核
//17-提示去指定月度目标(web端专用)
//
//16-日目标详细 需验证 需设为已阅
}
