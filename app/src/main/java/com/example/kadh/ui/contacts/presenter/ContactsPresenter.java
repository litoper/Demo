package com.example.kadh.ui.contacts.presenter;

import com.example.kadh.base.BaseBindingImpl;
import com.example.kadh.ui.contacts.bean.ContactRecentBean;
import com.example.kadh.ui.contacts.bean.ContractDbBean;
import com.example.kadh.ui.contacts.contract.ContactsFrgContract;
import com.example.kadh.utils.NullUtils;
import com.example.kadh.utils.RxJava.BaseResponse;
import com.example.kadh.utils.RxJava.RxApi.RxApi;
import com.example.kadh.utils.RxJava.RxSubscriber.SubNextImpl;
import com.example.kadh.utils.RxJava.RxSubscriber.SubProtect;
import com.example.kadh.utils.SpUtil;
import com.example.kadh.utils.dbhelp.CmpDBHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/26
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class ContactsPresenter extends BaseBindingImpl<ContactsFrgContract.View> implements ContactsFrgContract.Presenter {

    private RxApi mRxApi;

    @Inject
    public ContactsPresenter(RxApi rxApi) {
        mRxApi = rxApi;
    }


    @Override
    public void queryDataFromDb() {
        if (!CmpDBHelper.getInstance().isDbExist()) {
            return;
        }
        String userId = SpUtil.getInstance().getString(SpUtil.LOGIN_INFO_USERID);
        List<Map> cmpList = CmpDBHelper.getInstance().queryListMap("SELECT cmp_org.id,cmp_org.oname FROM cmp_org WHERE isdel='0' and cmp_org.olayer = '1'", null);
        List<Map> depList = CmpDBHelper.getInstance().queryListMap("SELECT cmp_user.ufdepartment_id,cmp_user.ufdepartment FROM cmp_user WHERE isdel='0' and cmp_user.id= '" + userId + "' ", null);
        List<Map> groupList = CmpDBHelper.getInstance().queryListMap("select * from cmp_user_role_ass JOIN cmp_role ON cmp_role.id=cmp_user_role_ass.cmp_role_id and cmp_role.isdel='0' JOIN " + "cmp_org ON cmp_org.id=cmp_role.cmp_org_id and cmp_org.isdel='0' AND cmp_org.otype IN('0','1','3') where  cmp_user_role_ass.isdel='0' and " + "cmp_user_role_ass.cmp_user_id= '" + userId + "'", null);
        if (groupList != null && !groupList.isEmpty()) {
            checkGroup(groupList);
        }
        List<ContractDbBean> dbBeans = convertData(cmpList, depList, groupList);
        mView.showDataFromDb(dbBeans);
    }

    @Override
    public void getRecentContact() {
        mRxApi.getRecentContact(new SubProtect<BaseResponse<ContactRecentBean>>(new SubNextImpl<BaseResponse<ContactRecentBean>>() {
            @Override
            public void onSubSuccess(BaseResponse<ContactRecentBean> response) {
                mView.showRecentContact(response.data);
            }
        }), "1");

    }

    /**
     * PS:查询小组只需确定类型为1或者3是确定的小组
     * 查出来的数据进行循环便利，如果otype=1或者3那就是小组或合弄制
     * 取出除 otype =1 和 3 之外的组织的父节点(ofather)集合
     *
     * @param groupList
     */
    private void checkGroup(List<Map> groupList) {
        String id = "";
        String ofather = "";
        for (Map map : groupList) {
            id = id + ",'" + map.get("id") + "'";
            ofather = ofather + ",'" + map.get("ofather") + "'";
        }
        ofather = id + ofather;
        String subId = id.substring(1);
        String subOfather = ofather.substring(1);
        List<Map> mapList = CmpDBHelper.getInstance().queryListMap("select * from cmp_org where (otype IN ('1', '3')) AND (ofather IN(" + subId + ") OR id IN (" + subOfather + ")) and cmp_org.isdel = '0' ", null);
        groupList.clear();
        if (mapList != null && !mapList.isEmpty()) {
            groupList.addAll(mapList);
        }
    }

    private List<ContractDbBean> convertData(List<Map> cmpList, List<Map> depList, List<Map> groupList) {
        ArrayList<ContractDbBean> dbBeans = new ArrayList<>();
        if (!NullUtils.isNull(cmpList)) {
            ContractDbBean cmpBean = new ContractDbBean();
            cmpBean.setId(cmpList.get(0).get("id").toString());
            cmpBean.setTitle(cmpList.get(0).get("oname").toString());
            cmpBean.setLevel("0");
            dbBeans.add(cmpBean);
        }

        if (!NullUtils.isNull(depList)) {
            ContractDbBean depBean = new ContractDbBean();
            depBean.setId(depList.get(0).get("ufdepartment_id").toString());
            depBean.setTitle(depList.get(0).get("ufdepartment").toString());
            depBean.setLevel("1");
            dbBeans.add(depBean);
        }

        if (!NullUtils.isNull(groupList)) {
            for (Map map : groupList) {
                ContractDbBean groupBean = new ContractDbBean();
                groupBean.setId(map.get("id").toString());
                groupBean.setTitle(map.get("oname").toString());
                groupBean.setLevel("2");
                dbBeans.add(groupBean);
            }
        }
        return dbBeans;
    }

}
