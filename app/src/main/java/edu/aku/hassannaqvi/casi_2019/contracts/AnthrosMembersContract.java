package edu.aku.hassannaqvi.casi_2019.contracts;

import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class AnthrosMembersContract {

    private final String projectName = "Central Asia Stunting Initiative 2019";
    private String _ID = "";
    private String _UID = "";
    private String _UUID = "";
    private String formDate = "";
    private String deviceId = "";
    private String devicetagID = "";
    private String user = "";
    private String app_ver = "";

    private String enm_no = "";
    private String hh_no = "";
    private String dob = "";
    private String age = "";
    private String na204 = "";
    private String sA3 = "";
    private String istatus = ""; // Interview Status
    private String istatus88x = ""; // Interview Status
    private String fmuid = "";
    private String end_time = "";

    private String synced = "";
    private String syncedDate = "";

    public AnthrosMembersContract() {
    }

    public AnthrosMembersContract(AnthrosMembersContract ec) {
        this.enm_no = ec.getEnm_no();
        this.hh_no = ec.getHh_no();
        this.dob = ec.getDob();
        this.age = ec.getAge();
        this.na204 = ec.getna204();


    }


    public String getFmuid() {
        return fmuid;
    }

    public void setFmuid(String fmuid) {
        this.fmuid = fmuid;
    }

    public String getProjectName() {
        return projectName;
    }

    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }

    public String get_UID() {
        return _UID;
    }

    public void set_UID(String _UID) {
        this._UID = _UID;
    }

    public String get_UUID() {
        return _UUID;
    }

    public void set_UUID(String _UUID) {
        this._UUID = _UUID;
    }

    public String getFormDate() {
        return formDate;
    }

    public void setFormDate(String formDate) {
        this.formDate = formDate;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getHh_no() {
        return hh_no;
    }

    public void setHh_no(String hh_no) {
        this.hh_no = hh_no;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getna204() {
        return na204;
    }

    public void setna204(String na204) {
        this.na204 = na204;
    }

    public String getApp_ver() {
        return app_ver;
    }

    public void setApp_ver(String app_ver) {
        this.app_ver = app_ver;
    }

    public String getsA3() {
        return sA3;
    }

    public void setsA3(String sA3) {
        this.sA3 = sA3;
    }

    public String getSynced() {
        return synced;
    }

    public void setSynced(String synced) {
        this.synced = synced;
    }

    public String getSyncedDate() {
        return syncedDate;
    }

    public void setSyncedDate(String syncedDate) {
        this.syncedDate = syncedDate;
    }

    public String getEnm_no() {
        return enm_no;
    }

    public void setEnm_no(String enm_no) {
        this.enm_no = enm_no;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDevicetagID() {
        return devicetagID;
    }

    public void setDevicetagID(String devicetagID) {
        this.devicetagID = devicetagID;
    }

    public String getIstatus() {
        return istatus;
    }

    public void setIstatus(String istatus) {
        this.istatus = istatus;
    }

    public String getIstatus88x() {
        return istatus88x;
    }

    public void setIstatus88x(String istatus88x) {
        this.istatus88x = istatus88x;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public AnthrosMembersContract Sync(JSONObject jsonObject) throws JSONException {

        this._ID = jsonObject.getString(AnthrosMembers.COLUMN__ID);
        this._UID = jsonObject.getString(AnthrosMembers.COLUMN_UID);
        this._UUID = jsonObject.getString(AnthrosMembers.COLUMN_UUID);
        this.fmuid = jsonObject.getString(AnthrosMembers.COLUMN_FM_UID);
        this.formDate = jsonObject.getString(AnthrosMembers.COLUMN_FORMDATE);
        this.deviceId = jsonObject.getString(AnthrosMembers.COLUMN_DEVICEID);
        this.devicetagID = jsonObject.getString(AnthrosMembers.COLUMN_DEVICETAGID);
        this.user = jsonObject.getString(AnthrosMembers.COLUMN_USER);
        this.app_ver = jsonObject.getString(AnthrosMembers.COLUMN_APPVERSION);
        this.enm_no = jsonObject.getString(AnthrosMembers.COLUMN_ENM_NO);
        this.hh_no = jsonObject.getString(AnthrosMembers.COLUMN_HH_NO);
        this.dob = jsonObject.getString(AnthrosMembers.COLUMN_DOB);
        this.age = jsonObject.getString(AnthrosMembers.COLUMN_AGE);
        this.na204 = jsonObject.getString(AnthrosMembers.COLUMN_na204);
        this.sA3 = jsonObject.getString(AnthrosMembers.COLUMN_SA3);
        this.istatus = jsonObject.getString(AnthrosMembers.COLUMN_ISTATUS);
        this.istatus88x = jsonObject.getString(AnthrosMembers.COLUMN_ISTATUS88x);
        this.synced = jsonObject.getString(AnthrosMembers.COLUMN_SYNCED);
        this.syncedDate = jsonObject.getString(AnthrosMembers.COLUMN_SYNCEDDATE);
        this.end_time = jsonObject.getString(AnthrosMembers.COLUMN_END_TIME);

        return this;

    }

    public AnthrosMembersContract Hydrate(Cursor cursor) {

        this._ID = cursor.getString(cursor.getColumnIndex(AnthrosMembers.COLUMN__ID));
        this._UID = cursor.getString(cursor.getColumnIndex(AnthrosMembers.COLUMN_UID));
        this._UUID = cursor.getString(cursor.getColumnIndex(AnthrosMembers.COLUMN_UUID));
        this.fmuid = cursor.getString(cursor.getColumnIndex(AnthrosMembers.COLUMN_FM_UID));
        this.formDate = cursor.getString(cursor.getColumnIndex(AnthrosMembers.COLUMN_FORMDATE));
        this.deviceId = cursor.getString(cursor.getColumnIndex(AnthrosMembers.COLUMN_DEVICEID));
        this.devicetagID = cursor.getString(cursor.getColumnIndex(AnthrosMembers.COLUMN_DEVICETAGID));
        this.user = cursor.getString(cursor.getColumnIndex(AnthrosMembers.COLUMN_USER));
        this.app_ver = cursor.getString(cursor.getColumnIndex(AnthrosMembers.COLUMN_APPVERSION));
        this.enm_no = cursor.getString(cursor.getColumnIndex(AnthrosMembers.COLUMN_ENM_NO));
        this.hh_no = cursor.getString(cursor.getColumnIndex(AnthrosMembers.COLUMN_HH_NO));
        this.dob = cursor.getString(cursor.getColumnIndex(AnthrosMembers.COLUMN_DOB));
        this.age = cursor.getString(cursor.getColumnIndex(AnthrosMembers.COLUMN_AGE));
        this.na204 = cursor.getString(cursor.getColumnIndex(AnthrosMembers.COLUMN_na204));
        this.sA3 = cursor.getString(cursor.getColumnIndex(AnthrosMembers.COLUMN_SA3));
        this.istatus = cursor.getString(cursor.getColumnIndex(AnthrosMembers.COLUMN_ISTATUS));
        this.istatus88x = cursor.getString(cursor.getColumnIndex(AnthrosMembers.COLUMN_ISTATUS88x));
        this.synced = cursor.getString(cursor.getColumnIndex(AnthrosMembers.COLUMN_SYNCED));
        this.syncedDate = cursor.getString(cursor.getColumnIndex(AnthrosMembers.COLUMN_SYNCEDDATE));
        this.end_time = cursor.getString(cursor.getColumnIndex(AnthrosMembers.COLUMN_END_TIME));


        return this;

    }


    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();

        json.put(AnthrosMembers.COLUMN_PROJECTNAME, this.projectName == null ? JSONObject.NULL : this.projectName);
        json.put(AnthrosMembers.COLUMN__ID, this._ID == null ? JSONObject.NULL : this._ID);
        json.put(AnthrosMembers.COLUMN_UID, this._UID == null ? JSONObject.NULL : this._UID);
        json.put(AnthrosMembers.COLUMN_UUID, this._UUID == null ? JSONObject.NULL : this._UUID);
        json.put(AnthrosMembers.COLUMN_FM_UID, this.fmuid == null ? JSONObject.NULL : this.fmuid);
        json.put(AnthrosMembers.COLUMN_FORMDATE, this.formDate == null ? JSONObject.NULL : this.formDate);
        json.put(AnthrosMembers.COLUMN_DEVICEID, this.deviceId == null ? JSONObject.NULL : this.deviceId);
        json.put(AnthrosMembers.COLUMN_DEVICETAGID, this.devicetagID == null ? JSONObject.NULL : this.devicetagID);
        json.put(AnthrosMembers.COLUMN_USER, this.user == null ? JSONObject.NULL : this.user);
        json.put(AnthrosMembers.COLUMN_APPVERSION, this.app_ver == null ? JSONObject.NULL : this.app_ver);

        if (!this.sA3.equals("")) {
            json.put(AnthrosMembers.COLUMN_SA3, this.sA3.equals("") ? JSONObject.NULL : new JSONObject(this.sA3));
        }
        json.put(AnthrosMembers.COLUMN_HH_NO, this.hh_no == null ? JSONObject.NULL : this.hh_no);
        json.put(AnthrosMembers.COLUMN_ENM_NO, this.enm_no == null ? JSONObject.NULL : this.enm_no);
        json.put(AnthrosMembers.COLUMN_DOB, this.dob == null ? JSONObject.NULL : this.dob);
        json.put(AnthrosMembers.COLUMN_AGE, this.age == null ? JSONObject.NULL : this.age);
        json.put(AnthrosMembers.COLUMN_na204, this.na204 == null ? JSONObject.NULL : this.na204);
        json.put(AnthrosMembers.COLUMN_ISTATUS, this.istatus == null ? JSONObject.NULL : this.istatus);
        json.put(AnthrosMembers.COLUMN_ISTATUS88x, this.istatus88x == null ? JSONObject.NULL : this.istatus88x);
        json.put(AnthrosMembers.COLUMN_END_TIME, this.end_time == null ? JSONObject.NULL : this.end_time);

        return json;
    }

    public static abstract class AnthrosMembers implements BaseColumns {

        public static final String TABLE_NAME = "anthros";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";

        public static final String COLUMN_PROJECTNAME = "projectname";
        public static final String COLUMN__ID = "_id";
        public static final String COLUMN_UID = "uid";
        public static final String COLUMN_UUID = "uuid";
        public static final String COLUMN_FM_UID = "fmuid";
        public static final String COLUMN_FORMDATE = "formdate";
        public static final String COLUMN_DEVICEID = "deviceid";
        public static final String COLUMN_DEVICETAGID = "devicetagid";
        public static final String COLUMN_USER = "user";
        public static final String COLUMN_APPVERSION = "appversion";
        public static final String COLUMN_ENM_NO = "cluster_no";
        public static final String COLUMN_HH_NO = "hh_no";
        public static final String COLUMN_DOB = "dob";
        public static final String COLUMN_AGE = "age";
        public static final String COLUMN_na204 = "na204";
        public static final String COLUMN_SA3 = "sa3";
        public static final String COLUMN_ISTATUS = "istatus";
        public static final String COLUMN_ISTATUS88x = "istatus88x";
        public static final String COLUMN_SYNCED = "synced";
        public static final String COLUMN_SYNCEDDATE = "synceddate";
        public static final String COLUMN_END_TIME = "end_time";


        public static String _URL = "anthros.php";
    }
}
