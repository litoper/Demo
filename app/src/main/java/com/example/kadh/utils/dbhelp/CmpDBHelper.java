package com.example.kadh.utils.dbhelp;

import android.content.Context;

import com.example.kadh.app.App;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/5/28
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */

public class CmpDBHelper extends DataBaseHelper {
    public static final String DBNAME = "djoa.db";
    public static final String DBVERSION = "dbversion";
    public static final String TABLE_USER = "cmp_user";
    public static final String TABLE_ROLE = "cmp_role";
    public static final String TABLE_ASS = "cmp_user_role_ass";
    public static final String TABLE_ORG = "cmp_org";

    private static CmpDBHelper mCmpDBHelper;
    private Context mContext;

    private CmpDBHelper(Context context) {
        super(context);
        mContext = context;
    }

    public static CmpDBHelper getInstance() {
        if (mCmpDBHelper == null) {
            synchronized (DataBaseHelper.class) {
                if (mCmpDBHelper == null) {
                    mCmpDBHelper = new CmpDBHelper(App.getApp());
                    if (mCmpDBHelper.getDB() == null || !mCmpDBHelper.getDB().isOpen()) {
                        mCmpDBHelper.open();
                    }
                }
            }
        }
        return mCmpDBHelper;
    }


    @Override
    protected int getDbVersion(Context context) {
        return 1;
    }

    @Override
    protected String getDbName(Context context) {
        return "djoa.db";
    }

    @Override
    protected String[] getDbCreateSql(Context context) {
        String[] a = new String[4];
        a[0] = "CREATE TABLE " + TABLE_USER + " (" +
                "pid INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id TEXT," +
                "uids TEXT," +
                "uname TEXT," +
                "ushort_phone TEXT," +
                "uposition TEXT," +
                "ujob TEXT," +
                "ufirstspell TEXT," +
                "uwholespell TEXT," +
                "uphone TEXT," +
                "uimage TEXT," +
                "ufdepartment_id TEXT," +
                "ufdepartment TEXT," +
                "create_time TEXT," +
                "update_time TEXT," +
                "isdel TEXT)";

        a[1] = "CREATE TABLE " + TABLE_ASS + " (" +
                "pid INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id TEXT," +
                "cmp_role_id TEXT," +
                "cmp_user_id TEXT," +
                "rdefault TEXT," +
                "create_time TEXT," +
                "update_time TEXT," +
                "isdel TEXT)";

        a[2] = "CREATE TABLE " + TABLE_ROLE + " (" +
                "pid INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id TEXT," +
                "rname TEXT," +
                "rdesc TEXT," +
                "cmp_org_id TEXT," +
                "create_time TEXT," +
                "update_time TEXT," +
                "isdel TEXT)";

        a[3] = "CREATE TABLE " + TABLE_ORG + " (" +
                "pid INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id TEXT," +
                "oname TEXT," +
                "olayer TEXT," +
                "ofather TEXT," +
                "otype TEXT," +
                "create_time TEXT," +
                "update_time TEXT," +
                "isdel TEXT)";
        return a;
    }

    @Override
    protected String[] getDbUpdateSql(Context context) {
        return new String[0];
    }


    @Override
    public void rebuildAllTable() {
        mCmpDBHelper.delete(CmpDBHelper.TABLE_ORG);
        mCmpDBHelper.delete(CmpDBHelper.TABLE_USER);
        mCmpDBHelper.delete(CmpDBHelper.TABLE_ASS);
        mCmpDBHelper.delete(CmpDBHelper.TABLE_ROLE);
        this.open();
    }
}
