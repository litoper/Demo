package com.example.kadh.utils.RxJava.RxApi;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/5/28
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */

public interface RxApiUrl {
    class Url {

        static final String WECHAT_APP_ID = "wx30bac70f9067a5bf";

        /**
         * 版本号
         */
        //        static String Ver = "Android(Build" + SPUtils.getVersionCode(DjorApp.mContext) + ")";
        public static String Ver = "";

        /**
         * 测试服务器地址
         */

        static final String NICAICAI = "2";
        public static final String BASE = "http://192.168.2.24/DJOA/";//蒋凡毅
        //        static final String BASE = "http://192.168.2.21/DJOA/"; //朱诚
        //        static final String BASE = "http://192.168.23.43/DJOA/"; //开发库

        /**
         * 正式服务器地址
         */

        //        static final String BASE = "http://101.37.29.29/DJOA/";
//        public static final String BASE = "https://oa.djcps.com/DJOA/";

        /**
         * 内网测试地址
         */

        //        static final String BASE = "http://192.168.10.175/DJOA/";

        /**
         * 杭州外网测试服务器地址
         */
        //        static final String BASE = "http://183.246.86.117:18080/DJOA/";

        /**
         * 登录接口
         */
        static final String LOGIN = "newlogin.do" + NICAICAI;//登录接口
        static final String GETVERCODE = "verCode_login.do" + NICAICAI;//获取登录验证码
        static final String CHECKVERSION = "checkVersionCommon.do" + NICAICAI;//校验app的版本更新

        /**
         * 首页接口
         */
        static final String GETPUBLISHLIST = "app_publishList.do";//获取所有发文的列表
        static final String GETNOTICEDETAILBYPUBLISHID = "app_getNoticeDetailByPublishId.do";//通过发文表ID获取通知公告详情页
        static final String GETNEWSDETAILBYPUBLISHID = "app_getNewsDetailByPublishId.do";//通过发文表ID获取单个新闻详情页
        static final String UPNUMBER = "app_upNumber.do";//点赞功能
        static final String GETCOMMENTLIST = "app_commentList.do";//获取评论列表
        static final String GETUPMANLIST = "app_upManList.do";//获取点赞列表
        static final String ADDCOMMENT = "app_addComment.do";//添加评论
        static final String DELCOMMENT = "app_delComment.do";//删除评论
        static final String SENDLOGINCODE = "sendLoginCodeCommon.do";//手机登录验证码
        static final String LOGINBYPHONE = "app_loginByPhoneCommon.do";//手机验证码登录
        static final String SENDFINDCODECOMMON = "sendFindCodeCommon.do";//给手机发送找回验证码
        static final String FINDPASSWORDCOMMON = "findPasswordCommon.do";//用户找回密码
        static final String LOGINBYQRCODE = "loginByQRcode.do";//用户二维码扫描登录
        static final String SHAREAPPURLCOMMON = "shareAppUrlCommon.do";//分享二维码的url地址

        /**
         * 首页拓展接口 会议
         */
        static final String GETWEATHER = "app_weather.do";//通过地点获取天气
        static final String GETMEETINGLIST = "app_meetingList.do";//通过标识获取会议列表
        static final String RELEASEMEETING = "app_releaseMeeting.do";//会议发布
        static final String MEETINGDETAIL = "app_meetingDetail.do";//会议详情
        static final String GETMEETINGBASEINFO = "app_getMeetingBaseInfo.do";//会议详情基本信息
        static final String CONFIRMMEETING = "app_confirmMeeting.do";//会议通知确认（参加或者不参加）
        static final String CANCELMEETING = "app_cancelMeeting.do";//发布的会议进行撤销操作
        static final String GETMEETINGROOM = "app_getMeetingRoom.do";//获取会议室
        static final String ENDMEETING = "app_endMeeting.do";//结束会议
        static final String MEETINGREMIND = "app_meetingRemind.do";//会议提醒

        /**
         * 首页拓展接口 知会
         */
        static final String GETINFORMEDLIST = "app_getInformedList.do";//查询流程知会列表
        static final String GETPNAMEININFORMED = "app_getPnameInInformed.do";//查询流程知会表筛选条件
        static final String EDITINFORMEDREAD = "app_editInformedRead.do";//流程知会阅读状态
        static final String EDITINFORMEXAMINE = "app_editInformExamine.do";//流程审核操作

        /**
         * 推送服务接口
         */
        static final String MQREGISTER = "app_MQRegister.do";//消息队列注册
        static final String MQLOGOUT = "app_MQLogOut.do";//消息队列注销
        static final String MQPUSHMSGLIST = "app_MQPushMsgList.do";//获取消息队列列表
        static final String ISHASUNREAD = "app_isHasUnReadMsgInMsgList.do";//app端主页消息列表中是否存在未读消息

        /**
         * 工作流程页面接口
         */
        static final String GETPROCESSSTATUS = "app_ProcessStatus.do";//获取工作当前流程状态
        static final String GETPROCESSLIST = "app_ProcessList.do";//获取流程列表
        static final String GETPROCESSMODULELIST = "app_ProcessModuleList.do";//获取流程列表
        static final String GETPROCESSDEALTLIST = "app_ProcessDealtList.do";//获取待办列表
        static final String GETPROCESSDEALTLIST1 = "app_BacklogList.do";//获取待办列表 最新

        static final String SETPROCESSREADED = "app_ProcessReaded.do";//设置为已阅
        static final String GETPROCESSFINISHLIST = "app_ProcessFinishList.do";//获取已办列表
        static final String GETPROCESSDETAILED = "app_ProcessDetailed.do";//获取待办已办详情
        static final String PUBLISHHANDLEDDETAILED = "app_publishHandledDetailed.do";//获取待办已办详情
        static final String PROCESSEXAMINE = "app_processExamine.do";//流程审核操作
        static final String GETPROCESSALREADYLIST = " app_processAlreadyList.do";//获取流程发起列别
        static final String GETPROCESSCONTENT = "app_ProcessContent.do";//获取发起流程的流程列表
        static final String SUBMITPROCESS = "app_submitProcess.do";//提交流程申请
        static final String UPLOADFIELD = "app_uploadFileCommon.do";//上传文件
        static final String UPFIELD = "app_upField.do";//头像上传
        static final String GETPROCESSSCHEDULE = "app_processScheduleFromPublish.do";//查询单个流程的进度
        static final String ISVALIDATE = "app_isValidateProcessPermit.do";//判断当前用户是否已经审核过了待办列表中的流程
        static final String PROCESSURGEDEALWITH = "app_processUrgeDealwith.do";//流程催办
        static final String PROCESSUSERDETAIL = "app_processUserDetail.do";//获取用户填写详情内容
        static final String RECALLPROCESS = "app_recallProcess.do";//流程撤回
        static final String PROCESSEXAMINEAPPROVER = "app_processExamineApprover.do";//流程撤回

        /**
         * 个人页面接口
         */
        static final String UPDATEPASSWORD = "app_updatePassword.do";//修改密码
        static final String GETUSERINFO = "app_getUserInfo.do";//GET获取个人信息
        static final String POSTUSERINFO = "app_postUserInfo.do";//POST修改个人信息
        static final String GETUSERCONFIGINFO = "app_getUserConfigInfo.do";//获取功能设置开关状态
        static final String UPDATEUSERCONFIGINFO = "app_updateUserConfigInfo.do";//修改功能设置开关状态
        static final String UPDATEUPHONE = "app_updateUphone.do";//修改联系方式
        static final String GETROLEMANAGESINGLE = "app_getRoleManageSingle.do";//修改功能设置开关状态
        static final String QUERYPROCESSBASETOTALINFO = "app_queryProcessBaseTotalInfo.do";//考勤统计
        static final String QUERYPROCESSBASETOTALINFODETAIL = "app_queryProcessBaseTotalInfoDetail.do";//日历详情
        static final String QUERYPROCESSTOTALINFOBYID = "app_queryProcessTotalInfoById.do";//考勤详情列表
        static final String GETNOTICELISTBYTYPE = "app_getNoticeListByType.do";//公司相关里的通知公告列表
        static final String GETNOTICETYPELIST = "app_getNoticeTypeList.do";//公司相关里的通知公告类型

        /**
         * 通讯录页面接口
         */
        static final String GETCONTACT = "app_contact.do"; //获取用户公司、部门、小组名称 用户最近联系人
        static final String GETATTENDEE = "app_getAttendee.do"; //获取用户公司、部门、小组名称 用户最近联系人
        static final String GETQUERYCONTACT = "app_queryContact.do";//获取公司、部门、小组通讯录
        static final String GETQUERYALLDEP = "app_queryAllDep.do";//查询用户所在公司的所有部门
        static final String GETTENSIONTRANSFERDEPINFO = "app_getTensionTransferDepInfo.do";//张力转派时,查询所有部门数据,过滤自己所在部门
        static final String GETALLDEPTANDJOB = "app_getAllDeptAndJob.do";//获取所有部门和职务
        static final String GETQUERYALLGROUP = "app_queryAllGroup.do";//查询用户所在公司的所有小组
        static final String ADDRECENTCONTACT = "app_recentContact.do";//添加最近联系人
        static final String RINGPHONE = "app_ringPhone.do";//拨打短号
        static final String GETORGINFO = "app_getOrgInfo.do";//获取指定公司底下，所有的组织信息，对应cmp_org表
        static final String GETROLEINFO = "app_getRoleInfo.do";//获取指定公司底下所有的角色信息，对应于cmp_role
        static final String GETUSERINFOBYCOMPANY = "app_getUserInfoByCompany.do";//获取指定公司底下所有的员工信息，对应cmp_user表
        static final String GETUSERANDROLEINFO = "app_getUserAndRoleInfo.do";//获取指定公司底下，所有的用户角色信息，对应：cmp_user_role_ass表
        static final String GETRECENTCONTACT = "app_getRecentContact.do";//获取指定公司底下，所有的用户角色信息，对应：cmp_user_role_ass表
        static final String DELRECENTCONTACT = "app_delRecentContact.do";//获取指定公司底下，所有的用户角色信息，对应：cmp_user_role_ass表

        /**
         * 会议相关接口
         */
        static final String GETSIGNCODE = "app_getSignCode.do";//获取会议签到二维码
        static final String MEETINGSIGNIN = "app_meetingSignIn.do";//会议签到
        static final String MEETINGSIGNOUT = "app_meetingSignOut.do";//会议签退
        static final String GETSIGNNUM = "app_getSignNum.do";//会议签到人数

        /**
         * 核心价值观接口
         */
        static final String GETSUBMITTEDLIST = "app_getSubmittedList.do";//获取被提报人
        static final String GETCURRENUSER = "app_getCurrenUser.do";//获取当前提报人
        static final String GETCOREVALUES = "app_getCoreValues.do";//获取核心价值观内容
        static final String GETCOOPERATIVEGROUP = "app_getCooperativeGroup.do";//获取合弄制小组列表
        static final String ADDCOREWORTHNEW = "app_addCoreWorthNew.do";//添加核心价值观
        static final String EDITCOREVALUESEXAMINE = "app_editCoreValuesExamine.do";//添加核心价值观
        static final String GETCOREVALUESDETAILED = "app_getCoreValuesDetailed.do";//获取核心价值观详情
        static final String GETUSERCOREVALUESDETAILED = "app_getUserCoreValuesDetailed.do";//获取核心价值观详情
        static final String GETCORELIST = "app_getCoreList.do";//获取核心价值观列表
        static final String GETCORELISTBYUSERID = "app_getCoreListByUserid.do";//获取核心价值观列表
        static final String GETWORTHRANKINGLISTBYPAGE = "app_getWorthRankingListByPage.do";//获取排行榜列表
        static final String GETDEPINFOBYUSERJOB = "app_getDepInfoByUserJob.do";//获取个人部门信息
        static final String GETWORTHRANKINGLISTTOPTHREE = "app_getWorthRankingListTopThree.do";//获取个人部门信息
        static final String GETCOREDETAIL = "app_getCoreDetail.do";//获取个人部门信息
        static final String RECALLCOREVALUES = "app_recallCoreValues.do";//核心价值观撤回
        static final String GETSUBITEDMANROLE = "app_getSubitedManRole.do";//获取被提报人角色列表
        static final String GETGROUP = "app_getGroup.do";//获取价值观被提报人所在部门的全部小组
        static final String GETWORTHRANKINGLISTNEW = "app_getWorthRankingListNew.do";//获取价值观部门排行榜

        /**
         * 张力相关接口
         */
        static final String ADDTENSIONTHREE = "app_addTensionThree.do";//添加张力
        static final String GETTENSIONDETAIL = "app_getTensionDetailThree.do";//获取待办审核张力详情页
        static final String GETWORTHTENSIONPERMITDETAIL = "app_getWorthTensionPermitDetail.do";//获取张力推优审核界面详情
        static final String GETTENSIONHANDLEDATA = "app_getTensionHandleData.do";//获取张力升级、处理、转派跳转页面数据的回显
        static final String TENSIONUPGRADE = "app_tensionUpgrade.do";//张力升级提交
        static final String TENSIONHANDLE = "app_tensionHandle.do";//张力处理
        static final String GETTENSIONEXAMINEDETAIL = "app_getTensionExamineDetailNew.do";//获取张力小组审批详情
        static final String TENSIONEXAMINE = "app_tensionExamineNew.do";//获取张力小组审批提交
        static final String TENSIONAUDITING = "app_tensionAuditingNew.do";//张力小组审批驳回跟完成用
        static final String GETTENSIONCONFIRMCLASSIFY = "app_getTensionConfirmClassify.do";//张力小组审批处理获取类型
        static final String TENSIONDEPDEALWITH = "app_tensionDepDealwithNew.do";//张力小组审批提交
        static final String GETMYTENSIONLIST = "app_getMyTensionListVersionThree.do";//获取首页下方我的张力
        static final String GETTENSIONLISTDETAIL = "app_getTensionListDetailThree.do";//张力模块里面获取张力详情
        static final String GETWORTHTENSIONLISTDETAIL = "app_getWorthTensionListDetail.do";//获取价值张力详情
        static final String EVALUATETENSIONDEALRESULT = "app_evaluateTensionDealResult.do";//张力评价
        static final String GETTENSIONRANKINGLISTTOPTHREE = "app_getTensionRankingListTopThreeVersionThree.do";//获取张力前三
        static final String GETTENSIONRANKINGLISTBYPAGE = "app_getTensionRankingListByPage.do";//获取张力排行榜
        static final String GETTENSIONRANKINGLISTNEW = "app_getTensionRankingListNewVersionThree.do";//获取新版张力排行榜
        static final String TENSIONPROGRESS = "app_tensionProgress.do";//获取张力进度
        static final String GETTENSIONDETAILFORAGAIN = "app_getTensionDetailForAgain.do";//张力重新发起
        static final String QUERYDEPFORTENSION = "app_queryDepForTension.do";//内张力部门获取
        static final String GETTENSIONSUBMITROLELIST = "app_getTensionSubmitRoleList.do";//内张力部门获取
        static final String GETTENSIONTHEME = "app_getTensionTheme.do";//获取张力主题
        static final String ISSHOWINFORMORNOTICEDEP = "app_isShowInformOrNoticeDep.do";//根据角色id和部门id判断是否为内外部张力
        static final String GETVALUETENSIONVERSIONTHREE = "app_getValueTensionVersionThree.do";//获取价值张力
        static final String GETTENSIONINFORMANT = "app_getTensionInformant.do";//获取张力通知人
        static final String TENSIONSTATEMENT = "app_tensionStatement.do";//张力申诉
        static final String WORTHTENSIONPERMIT = "app_worthTensionPermit.do";//价值张力审批提交接口
        static final String GETTENSIONEXPLAIN = "app_getTensionExplain.do";//价值张力审批同意页面获取张力说明
        static final String TENSIONTRANSFER = "app_tensionTransfer.do";//张力新版转派
        static final String GETTENSIONHANDLEMANSUBORDINATE = "app_getTensionHandleManSubordinate.do";//张力成立处理,部门责任人选择

        /**
         * 个人目标
         */
        static final String GETMYGOALDAYLIST = "app_getMyGoalDayList.do";
        static final String GETGOALMONTHBYDTYPE = "app_getGoalMonthByDtype.do";
        static final String ADDGOALDAY = "app_addGoalDay.do";
        static final String GETGOALDAYDETAIL = "app_getGoalDayDetail.do";
        static final String FINISHGOALDAY = "app_finishGoalDay.do";
        static final String EDITGOALDAY = "app_editGoalDay.do";
        static final String CLOSEGOALDAY = "app_closeGoalDay.do";
        static final String DELGOALDAY = "app_delGoalDay.do";
        static final String GETMYGOALDAYCOUNTINMONTH = "app_getMyGoalDayCountInMonth.do";
        static final String GETGOALDAYTIME = "app_getGoalDayTime.do";

        /**
         * 异常上传
         */
        static final String SETEXCPTIONDETAIL = "app_setExcptionDetail.do";

        /**
         * 企业文化
         */
        static final String GETNOTICETYPELISTNEW = "app_getNoticeTypeListNew.do";
        static final String GETMANAGESYSTEMTYPEANDDETAIL = "app_getManageSystemTypeAndDetail.do";

    }
}
