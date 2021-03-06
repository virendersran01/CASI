package edu.aku.hassannaqvi.casi_2019.core;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import edu.aku.hassannaqvi.casi_2019.contracts.AnthrosMembersContract;
import edu.aku.hassannaqvi.casi_2019.contracts.AnthrosMembersContract.AnthrosMembers;
import edu.aku.hassannaqvi.casi_2019.contracts.BLRandomContract;
import edu.aku.hassannaqvi.casi_2019.contracts.BLRandomContract.singleRandomHH;
import edu.aku.hassannaqvi.casi_2019.contracts.ChildContract;
import edu.aku.hassannaqvi.casi_2019.contracts.ChildContract.ChildTable;
import edu.aku.hassannaqvi.casi_2019.contracts.D4WRAContract;
import edu.aku.hassannaqvi.casi_2019.contracts.D4WRAContract.D4WRATable;
import edu.aku.hassannaqvi.casi_2019.contracts.D6AdolesContract;
import edu.aku.hassannaqvi.casi_2019.contracts.D6AdolesContract.D6AdolesTable;
import edu.aku.hassannaqvi.casi_2019.contracts.DeceasedContract;
import edu.aku.hassannaqvi.casi_2019.contracts.DeviceContract;
import edu.aku.hassannaqvi.casi_2019.contracts.EnumBlockContract;
import edu.aku.hassannaqvi.casi_2019.contracts.EnumBlockContract.EnumBlockTable;
import edu.aku.hassannaqvi.casi_2019.contracts.FamilyMembersContract;
import edu.aku.hassannaqvi.casi_2019.contracts.FamilyMembersContract.familyMembers;
import edu.aku.hassannaqvi.casi_2019.contracts.FormsContract;
import edu.aku.hassannaqvi.casi_2019.contracts.FormsContract.FormsTable;
import edu.aku.hassannaqvi.casi_2019.contracts.MWRAContract;
import edu.aku.hassannaqvi.casi_2019.contracts.MWRAContract.MWRATable;
import edu.aku.hassannaqvi.casi_2019.contracts.MicroContract;
import edu.aku.hassannaqvi.casi_2019.contracts.MicroContract.MicroTable;
import edu.aku.hassannaqvi.casi_2019.contracts.NutritionContract;
import edu.aku.hassannaqvi.casi_2019.contracts.NutritionContract.NutritionTable;
import edu.aku.hassannaqvi.casi_2019.contracts.OutcomeContract;
import edu.aku.hassannaqvi.casi_2019.contracts.OutcomeContract.outcomeTable;
import edu.aku.hassannaqvi.casi_2019.contracts.RecipientsContract;
import edu.aku.hassannaqvi.casi_2019.contracts.RecipientsContract.RecipientsTable;
import edu.aku.hassannaqvi.casi_2019.contracts.SerialContract;
import edu.aku.hassannaqvi.casi_2019.contracts.SerialContract.singleSerial;
import edu.aku.hassannaqvi.casi_2019.contracts.SignupContract;
import edu.aku.hassannaqvi.casi_2019.contracts.SignupContract.SignUpTable;
import edu.aku.hassannaqvi.casi_2019.contracts.SpecimenContract;
import edu.aku.hassannaqvi.casi_2019.contracts.SpecimenContract.SpecimenTable;
import edu.aku.hassannaqvi.casi_2019.contracts.SummaryContract;
import edu.aku.hassannaqvi.casi_2019.contracts.SummaryContract.singleSum;
import edu.aku.hassannaqvi.casi_2019.contracts.UCsContract;
import edu.aku.hassannaqvi.casi_2019.contracts.UCsContract.UCsTable;
import edu.aku.hassannaqvi.casi_2019.contracts.UsersContract;
import edu.aku.hassannaqvi.casi_2019.contracts.UsersContract.UsersTable;
import edu.aku.hassannaqvi.casi_2019.contracts.VersionAppContract;
import edu.aku.hassannaqvi.casi_2019.contracts.VersionAppContract.VersionAppTable;
import edu.aku.hassannaqvi.casi_2019.contracts.WaterSpecimenContract;
import edu.aku.hassannaqvi.casi_2019.contracts.WaterSpecimenContract.WaterSpecimenTable;
import edu.aku.hassannaqvi.casi_2019.other.Summary;


/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "casi_2019.db";
    public static final String PROJECT_NAME = "casi_2019";
    private static final int DATABASE_VERSION = 1;
    public static final String DB_NAME = DATABASE_NAME.replace(".", "_" + MainApp.versionName + "_" + DATABASE_VERSION + "_copy.");

    public static final String SQL_CREATE_USERS = "CREATE TABLE " + UsersContract.UsersTable.TABLE_NAME + "("
            + UsersTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + UsersTable.ROW_USERNAME + " TEXT,"
            + UsersTable.ROW_PASSWORD + " TEXT,"
            + UsersTable.COUNTRY_ID + " TEXT"
            + " );";
    public static final String SQL_CREATE_BL_RANDOM = "CREATE TABLE " + singleRandomHH.TABLE_NAME + "("
            + singleRandomHH.COLUMN_ID + " TEXT,"
            + singleRandomHH.COLUMN_ENUM_BLOCK_CODE + " TEXT,"
            + singleRandomHH.COLUMN_LUID + " TEXT,"
            + singleRandomHH.COLUMN_HH + " TEXT,"
            + singleRandomHH.COLUMN_STRUCTURE_NO + " TEXT,"
            + singleRandomHH.COLUMN_FAMILY_EXT_CODE + " TEXT,"
            + singleRandomHH.COLUMN_HH_HEAD + " TEXT,"
            + singleRandomHH.COLUMN_CONTACT + " TEXT,"
            + singleRandomHH.COLUMN_HH_SELECTED_STRUCT + " TEXT,"
            + singleRandomHH.COLUMN_RANDOMDT + " TEXT,"
            + singleRandomHH.COLUMN_RANDOM_TYPE + " TEXT,"
            + singleRandomHH.COLUMN_ASSIGNED_HH + " TEXT,"
            + singleRandomHH.COLUMN_SNO_HH + " TEXT );";


    public static final String SQL_CREATE_SIGNUP = "CREATE TABLE " + SignUpTable.TABLE_NAME + "("
            + SignUpTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + SignUpTable.FULLNAME + " TEXT,"
            + SignUpTable.USERNAME + " TEXT,"
            + SignUpTable.DESIGNATION + " TEXT,"
            + SignUpTable.PASSWORD + " TEXT,"
            + SignUpTable.COUNTRY_ID + " TEXT, "
            + SignUpTable.COLUMN_DEVICEID + " TEXT, "
            + SignUpTable.COLUMN_FORMDATE + " TEXT, "
            + SignUpTable.COLUMN_SYNCED + " TEXT, "
            + SignUpTable.COLUMN_SYNCED_DATE + " TEXT " +
            ");";


    private static final String SQL_CREATE_FORMS = "CREATE TABLE "
            + FormsTable.TABLE_NAME + "("
            + FormsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + FormsTable.COLUMN_PROJECT_NAME + " TEXT,"
            + FormsTable.COLUMN_UID + " TEXT," +
            FormsTable.COLUMN_FORMDATE + " TEXT," +
            FormsTable.COLUMN_USER + " TEXT," +
            FormsTable.COLUMN_RESP_LNO + " TEXT," +
            FormsTable.COLUMN_HH_NO + " TEXT," +
            FormsTable.COLUMN_CLUSTER_NO + " TEXT," +
            FormsTable.COLUMN_GPSELEV + " TEXT," +
            FormsTable.COLUMN_SA1 + " TEXT," +
            FormsTable.COLUMN_SA4 + " TEXT," +
            FormsTable.COLUMN_SA402 + " TEXT," +
            FormsTable.COLUMN_SA5 + " TEXT," +
            FormsTable.COLUMN_SA7 + " TEXT," +
            FormsTable.COLUMN_END_TIME + " TEXT," +
            FormsTable.COLUMN_ISTATUS + " TEXT," +
            FormsTable.COLUMN_ISTATUS88x + " TEXT," +
            FormsTable.COLUMN_ISTATUSHH + " TEXT," +
            FormsTable.COLUMN_COUNT + " TEXT," +
            FormsTable.COLUMN_GPSLAT + " TEXT," +
            FormsTable.COLUMN_GPSLNG + " TEXT," +
            FormsTable.COLUMN_GPSDATE + " TEXT," +
            FormsTable.COLUMN_GPSACC + " TEXT," +
            FormsTable.COLUMN_DEVICEID + " TEXT," +
            FormsTable.COLUMN_DEVICETAGID + " TEXT," +
            FormsTable.COLUMN_APP_VERSION + " TEXT," +
            FormsTable.COLUMN_SYNCED + " TEXT," +
            FormsTable.COLUMN_SYNCED_DATE + " TEXT"
            + " );";

    private static final String SQL_CREATE_FAMILY_MEMEBERS = "CREATE TABLE "
            + familyMembers.TABLE_NAME + "("
            + familyMembers.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + familyMembers.COLUMN_PROJECT_NAME + " TEXT,"
            + familyMembers.COLUMN_UID + " TEXT UNIQUE," +
            familyMembers.COLUMN_UUID + " TEXT," +
            familyMembers.COLUMN_FORMDATE + " TEXT," +
            familyMembers.COLUMN_USER + " TEXT," +
            familyMembers.COLUMN_SA2 + " TEXT," +
            familyMembers.COLUMN_ENM_NO + " TEXT," +
            familyMembers.COLUMN_HH_NO + " TEXT," +
            familyMembers.COLUMN_AV + " TEXT," +
            familyMembers.COLUMN_KISH_SELECTED + " TEXT," +
            familyMembers.COLUMN_KISH_SELECTED_MWRA_D + " TEXT," +
            familyMembers.COLUMN_KISH_SELECTED_ADOLESCENT + " TEXT," +
            familyMembers.COLUMN_DEVICEID + " TEXT," +
            familyMembers.COLUMN_DEVICETAGID + " TEXT," +
            familyMembers.COLUMN_APP_VERSION + " TEXT," +
            familyMembers.COLUMN_SYNCED + " TEXT," +
            familyMembers.COLUMN_SYNCED_DATE + " TEXT," +
            familyMembers.COLUMN_FLAG + " TEXT"
            + " );";
    private static final String SQL_CREATE_DWRA = "CREATE TABLE "
            + D4WRATable.TABLE_NAME + "("
            + D4WRATable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + D4WRATable.COLUMN_PROJECTNAME + " TEXT,"
            + D4WRATable.COLUMN_UID + " TEXT UNIQUE," +
            D4WRATable.COLUMN_UUID + " TEXT," +
            D4WRATable.COLUMN_FM_UID + " TEXT," +
            D4WRATable.COLUMN_DSERIALNO + " TEXT," +
            D4WRATable.COLUMN_FTYPE + " TEXT," +
            D4WRATable.COLUMN_FORMDATE + " TEXT," +
            D4WRATable.COLUMN_USER + " TEXT," +
            D4WRATable.COLUMN_SD1 + " TEXT," +
            D4WRATable.COLUMN_DEVICEID + " TEXT," +
            D4WRATable.COLUMN_DEVICETAGID + " TEXT," +
            D4WRATable.COLUMN_APP_VER + " TEXT," +
            D4WRATable.COLUMN_SYNCED + " TEXT," +
            D4WRATable.COLUMN_SYNCEDDATE + " TEXT"
            + " );";
    private static final String SQL_CREATE_DADOLES = "CREATE TABLE "
            + D6AdolesTable.TABLE_NAME + "("
            + D6AdolesTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + D6AdolesTable.COLUMN_PROJECTNAME + " TEXT,"
            + D6AdolesTable.COLUMN_UID + " TEXT UNIQUE," +
            D6AdolesTable.COLUMN_UUID + " TEXT," +
            D6AdolesTable.COLUMN_FM_UID + " TEXT," +
            D6AdolesTable.COLUMN_FMSERIALNO + " TEXT," +
            D6AdolesTable.COLUMN_FORMDATE + " TEXT," +
            D6AdolesTable.COLUMN_USER + " TEXT," +
            D6AdolesTable.COLUMN_SD6 + " TEXT," +
            D6AdolesTable.COLUMN_DEVICEID + " TEXT," +
            D6AdolesTable.COLUMN_DEVICETAGID + " TEXT," +
            D6AdolesTable.COLUMN_APP_VER + " TEXT," +
            D6AdolesTable.COLUMN_SYNCED + " TEXT," +
            D6AdolesTable.COLUMN_SYNCEDDATE + " TEXT"
            + " );";

    private static final String SQL_CREATE_CHILD_FORMS = "CREATE TABLE "
            + ChildTable.TABLE_NAME + "("
            + ChildTable.COLUMN__ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ChildTable.COLUMN_PROJECTNAME + " TEXT," +
            ChildTable.COLUMN__UID + " TEXT," +
            ChildTable.COLUMN__UUID + " TEXT," +
            ChildTable.COLUMN_FM_UID + " TEXT," +
            ChildTable.COLUMN_MUID + " TEXT," +
            ChildTable.COLUMN_FORMDATE + " TEXT," +
            ChildTable.COLUMN_USER + " TEXT," +
            ChildTable.COLUMN_C1SERIALNO + " TEXT," +
            ChildTable.COLUMN_SC1 + " TEXT," +
            ChildTable.COLUMN_SC2 + " TEXT," +
            ChildTable.COLUMN_SC3 + " TEXT," +
            ChildTable.COLUMN_SC4 + " TEXT," +
            ChildTable.COLUMN_SC5 + " TEXT," +
            ChildTable.COLUMN_SC6 + " TEXT," +
            ChildTable.COLUMN_DEVICEID + " TEXT," +
            ChildTable.COLUMN_DEVICETAGID + " TEXT," +
            ChildTable.COLUMN_SYNCED + " TEXT," +
            ChildTable.COLUMN_SYNCED_DATE + " TEXT," +
            ChildTable.COLUMN_CSTATUS + " TEXT," +
            ChildTable.COLUMN_CSTATUS88x + " TEXT," +
            ChildTable.COLUMN_APPVERSION + " TEXT " + " );";
    final String SQL_CREATE_SERIAL = "CREATE TABLE " + singleSerial.TABLE_NAME + " (" +
            singleSerial._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            singleSerial.COLUMN_DEVICE_ID + " TEXT, " +
            singleSerial.COLUMN_DATE + " TEXT, " +
            singleSerial.COLUMN_SERIAL_NO + " TEXT, " +
            singleSerial.COLUMN_SYNCED + " TEXT, " +
            singleSerial.COLUMN_SYNCED_DATE + " TEXT " +
            ");";
    final String SQL_CREATE_TALUKA = "CREATE TABLE " + EnumBlockTable.TABLE_NAME + " (" +
            EnumBlockTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            EnumBlockTable.COLUMN_COUNTRY_ID + " TEXT, " +
            EnumBlockTable.COLUMN_GEO_AREA + " TEXT, " +
            EnumBlockTable.COLUMN_CLUSTER_AREA + " TEXT " +
            ");";
    final String SQL_CREATE_UC = "CREATE TABLE " + UCsTable.TABLE_NAME + " (" +
            UCsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            UCsTable.COLUMN_UCCODE + " TEXT, " +
            UCsTable.COLUMN_UCS_NAME + " TEXT, " +
            UCsTable.COLUMN_TALUKA_CODE + " TEXT " +
            ");";
    final String SQL_CREATE_MWRAS = "CREATE TABLE " + MWRATable.TABLE_NAME + " (" +
            MWRATable.COLUMN__ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            MWRATable.COLUMN_PROJECTNAME + " TEXT," +
            MWRATable.COLUMN_UID + " TEXT," +
            MWRATable.COLUMN_UUID + " TEXT," +
            MWRATable.COLUMN_FM_UID + " TEXT," +
            MWRATable.COLUMN_FORMDATE + " TEXT," +
            MWRATable.COLUMN_UPDATEDATE + " TEXT," +
            MWRATable.COLUMN_DEVICEID + " TEXT," +
            MWRATable.COLUMN_DEVICETAGID + " TEXT," +
            MWRATable.COLUMN_USER + " TEXT," +
            MWRATable.COLUMN_APP_VER + " TEXT," +
            MWRATable.COLUMN_B1SERIALNO + " TEXT," +
            MWRATable.COLUMN_SB1 + " TEXT," +
            MWRATable.COLUMN_SB2 + " TEXT," +
            MWRATable.COLUMN_SB3 + " TEXT," +
            MWRATable.COLUMN_SB4 + " TEXT," +
            MWRATable.COLUMN_SB5 + " TEXT," +
            MWRATable.COLUMN_SB6 + " TEXT," +
            MWRATable.COLUMN_SB7 + " TEXT," +
            MWRATable.COLUMN_SB8 + " TEXT," +
            MWRATable.COLUMN_SB9 + " TEXT," +
            MWRATable.COLUMN_SB10 + " TEXT," +
            MWRATable.COLUMN_SB11 + " TEXT," +
            MWRATable.COLUMN_SB2FLAG + " TEXT," +
            MWRATable.COLUMN_SYNCED + " TEXT," +
            MWRATable.COLUMN_MSTATUS + " TEXT," +
            MWRATable.COLUMN_MSTATUS88x + " TEXT," +
            MWRATable.COLUMN_SYNCEDDATE + " TEXT " +
            ");";

    final String SQL_CREATE_OUTCOME = "CREATE TABLE " + outcomeTable.TABLE_NAME + " (" +
            outcomeTable.COLUMN__ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            outcomeTable.COLUMN_PROJECTNAME + " TEXT," +
            outcomeTable.COLUMN_UID + " TEXT," +
            outcomeTable.COLUMN_UUID + " TEXT," +
            outcomeTable.COLUMN_FM_UID + " TEXT," +
            outcomeTable.COLUMN_MUID + " TEXT," +
            outcomeTable.COLUMN_FORMDATE + " TEXT," +
            outcomeTable.COLUMN_UPDATEDATE + " TEXT," +
            outcomeTable.COLUMN_DEVICEID + " TEXT," +
            outcomeTable.COLUMN_DEVICETAGID + " TEXT," +
            outcomeTable.COLUMN_USER + " TEXT," +
            outcomeTable.COLUMN_APP_VER + " TEXT," +
            outcomeTable.COLUMN_B1APregSNO + " TEXT," +
            outcomeTable.COLUMN_SB1A + " TEXT," +
            outcomeTable.COLUMN_SYNCED + " TEXT," +
            outcomeTable.COLUMN_SYNCEDDATE + " TEXT " +
            ");";

    final String SQL_CREATE_RECIPIENTS = "CREATE TABLE " + RecipientsTable.TABLE_NAME + " (" +
            RecipientsTable.COLUMN__ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            RecipientsTable.COLUMN_PROJECTNAME + " TEXT," +
            RecipientsTable.COLUMN_UID + " TEXT," +
            RecipientsTable.COLUMN_UUID + " TEXT," +
            RecipientsTable.COLUMN_FM_UID + " TEXT," +
            RecipientsTable.COLUMN_FORMDATE + " TEXT," +
            RecipientsTable.COLUMN_DEVICEID + " TEXT," +
            RecipientsTable.COLUMN_DEVICETAGID + " TEXT," +
            RecipientsTable.COLUMN_USER + " TEXT," +
            RecipientsTable.COLUMN_APP_VER + " TEXT," +
            RecipientsTable.COLUMN_A8ASNO + " TEXT," +
            RecipientsTable.COLUMN_SA8A + " TEXT," +
            RecipientsTable.COLUMN_SYNCED + " TEXT," +
            RecipientsTable.COLUMN_SYNCEDDATE + " TEXT " +
            ");";

    final String SQL_CREATE_NUTRITION = "CREATE TABLE " + NutritionTable.TABLE_NAME + " (" +
            NutritionTable.COLUMN__ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            NutritionTable.COLUMN_PROJECTNAME + " TEXT," +
            NutritionTable.COLUMN_UID + " TEXT," +
            NutritionTable.COLUMN_UUID + " TEXT," +
            NutritionTable.COLUMN_FM_UID + " TEXT," +
            NutritionTable.COLUMN_MUID + " TEXT," +
            NutritionTable.COLUMN_FORMDATE + " TEXT," +
            NutritionTable.COLUMN_UPDATEDATE + " TEXT," +
            NutritionTable.COLUMN_DEVICEID + " TEXT," +
            NutritionTable.COLUMN_DEVICETAGID + " TEXT," +
            NutritionTable.COLUMN_USER + " TEXT," +
            NutritionTable.COLUMN_APP_VER + " TEXT," +
            NutritionTable.COLUMN_SB6 + " TEXT," +
            NutritionTable.COLUMN_SYNCED + " TEXT," +
            NutritionTable.COLUMN_SYNCEDDATE + " TEXT " +

            ");";

    final String SQL_CREATE_DECEASED = "CREATE TABLE " + DeceasedContract.DeceasedTable.TABLE_NAME + " (" +
            DeceasedContract.DeceasedTable.COLUMN__ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            DeceasedContract.DeceasedTable.COLUMN_PROJECTNAME + " TEXT," +
            DeceasedContract.DeceasedTable.COLUMN__UID + " TEXT," +
            DeceasedContract.DeceasedTable.COLUMN__UUID + " TEXT," +
            DeceasedContract.DeceasedTable.COLUMN_FORMDATE + " TEXT," +
            DeceasedContract.DeceasedTable.COLUMN_DEVICEID + " TEXT," +
            DeceasedContract.DeceasedTable.COLUMN_DEVICETAGID + " TEXT," +
            DeceasedContract.DeceasedTable.COLUMN_USER + " TEXT," +
            DeceasedContract.DeceasedTable.COLUMN_APPVERSION + " TEXT," +
            DeceasedContract.DeceasedTable.COLUMN_SH8 + " TEXT," +
            DeceasedContract.DeceasedTable.COLUMN_SYNCED + " TEXT," +
            DeceasedContract.DeceasedTable.COLUMN_SYNCED_DATE + " TEXT " +

            ");";
    final String SQL_CREATE_SUMMARY = "CREATE TABLE " + singleSum.TABLE_NAME + " (" +
            singleSum.COLUMN_ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            singleSum.COLUMN_PROJECTNAME + " TEXT," +
            singleSum.COLUMN__UID + " TEXT," +
            singleSum.COLUMN_CLUSTERNO + " TEXT," +
            singleSum.COLUMN_HHNO + " TEXT," +
            singleSum.COLUMN_HH + " TEXT," +
            singleSum.COLUMN_WOMEN + " TEXT," +
            singleSum.COLUMN_CHILD + " TEXT," +
            singleSum.COLUMN_ANTHRO + " TEXT," +
            singleSum.COLUMN_SPECIMEN + " TEXT," +
            singleSum.COLUMN_WATER + " TEXT," +
            singleSum.COLUMN_SYNCED + " TEXT," +
            singleSum.COLUMN_SYNCEDDATE + " TEXT," +
            singleSum.COLUMN_USER + " TEXT," +
            singleSum.COLUMN_FORMDATE + " TEXT," +
            singleSum.COLUMN_DEVICEID + " TEXT," +
            singleSum.COLUMN_DEVICETAGID + " TEXT," +
            singleSum.COLUMN_APPVERSION + " TEXT);";

    final String SQL_CREATE_VERSIONAPP = "CREATE TABLE " + VersionAppTable.TABLE_NAME + " (" +
            VersionAppTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            VersionAppTable.COLUMN_VERSION_CODE + " TEXT, " +
            VersionAppTable.COLUMN_VERSION_NAME + " TEXT, " +
            VersionAppTable.COLUMN_PATH_NAME + " TEXT " +
            ");";

    private static final String SQL_DELETE_ANTHROS_MEMBERS = "DROP TABLE IF EXISTS " + AnthrosMembers.TABLE_NAME;


    final String SQL_CREATE_SPECIMEN_MEMBERS = "CREATE TABLE " + SpecimenTable.TABLE_NAME + " (" +
            SpecimenTable.COLUMN__ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            SpecimenTable.COLUMN__UID + " TEXT," +
            SpecimenTable.COLUMN__UUID + " TEXT," +
            SpecimenTable.COLUMN_FM_UID + " TEXT," +
            SpecimenTable.COLUMN_LINENO + " TEXT," +
            SpecimenTable.COLUMN_PROJECTNAME + " TEXT," +
            SpecimenTable.COLUMN_FORMDATE + " TEXT," +
            SpecimenTable.COLUMN_DEVICEID + " TEXT," +
            SpecimenTable.COLUMN_DEVICETAGID + " TEXT," +
            SpecimenTable.COLUMN_USER + " TEXT," +
            SpecimenTable.COLUMN_APPVERSION + " TEXT," +
            SpecimenTable.COLUMN_CLUSTER + " TEXT," +
            SpecimenTable.COLUMN_HH + " TEXT," +
            SpecimenTable.COLUMN_SE1 + " TEXT," +
            SpecimenTable.COLUMN_SYNCED + " TEXT," +
            SpecimenTable.COLUMN_SYNCED_DATE + " TEXT" +

            ");";


    final String SQL_CREATE_WATER_SPECIMEN_MEMBERS = "CREATE TABLE " + WaterSpecimenTable.TABLE_NAME + " (" +
            WaterSpecimenTable.COLUMN__ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            WaterSpecimenTable.COLUMN__UID + " TEXT," +
            WaterSpecimenTable.COLUMN__UUID + " TEXT," +
            WaterSpecimenTable.COLUMN_PROJECTNAME + " TEXT," +
            WaterSpecimenTable.COLUMN_FORMDATE + " TEXT," +
            WaterSpecimenTable.COLUMN_DEVICEID + " TEXT," +
            WaterSpecimenTable.COLUMN_DEVICETAGID + " TEXT," +
            WaterSpecimenTable.COLUMN_USER + " TEXT," +
            WaterSpecimenTable.COLUMN_APPVERSION + " TEXT," +
            WaterSpecimenTable.COLUMN_CLUSTER + " TEXT," +
            WaterSpecimenTable.COLUMN_HH + " TEXT," +
            WaterSpecimenTable.COLUMN_SE2 + " TEXT," +
            WaterSpecimenTable.COLUMN_SYNCED + " TEXT," +
            WaterSpecimenTable.COLUMN_SYNCED_DATE + " TEXT" +

            ");";

    final String SQL_CREATE_MICRO = "CREATE TABLE " + MicroTable.TABLE_NAME + " (" +
            MicroTable.COLUMN__ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            MicroTable.COLUMN__UID + " TEXT," +
            MicroTable.COLUMN__UUID + " TEXT," +
            MicroTable.COLUMN_WUID + " TEXT," +
            MicroTable.COLUMN_PROJECTNAME + " TEXT," +
            MicroTable.COLUMN_FORMDATE + " TEXT," +
            MicroTable.COLUMN_DEVICEID + " TEXT," +
            MicroTable.COLUMN_DEVICETAGID + " TEXT," +
            MicroTable.COLUMN_USER + " TEXT," +
            MicroTable.COLUMN_APPVERSION + " TEXT," +
            MicroTable.COLUMN_CLUSTER + " TEXT," +
            MicroTable.COLUMN_HH + " TEXT," +
            MicroTable.COLUMN_SM + " TEXT," +
            MicroTable.COLUMN_SYNCED + " TEXT," +
            MicroTable.COLUMN_SYNCED_DATE + " TEXT" +

            ");";

    final String SQL_CREATE_DEVICE = "CREATE TABLE " + DeviceContract.DeviceTable.TABLE_NAME + " (" +
            DeviceContract.DeviceTable.COLUMN__ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            DeviceContract.DeviceTable.COLUMN_IMEI + " TEXT," +
            DeviceContract.DeviceTable.COLUMN_APPVERSION + " TEXT," +
            DeviceContract.DeviceTable.COLUMN_TAGID + " TEXT" + ");";

    final String SQL_SUMMARY_JOIN =
            "SELECT f.formdate, f.cluster_no, f.hh_no, f.istatus, f.istatusHH, f.user,\n" +
                    "count(distinct fm.uid) member, " +
                    "count(distinct m.uid) wra,\n" +
                    "count(distinct c._uid) child,\n" +
                    "CASE WHEN count(distinct s._uid) > 0 THEN 'YES' ELSE 'NO' END hb\n" +
                    "FROM forms f\n" +
                    "LEFT JOIN mwra m\n" +
                    "ON f._uid = m.uuid\n" +
                    "LEFT JOIN child c\n" +
                    "ON f._uid = c._uuid\n" +
                    "LEFT JOIN familymembers fm\n" +
                    "ON f._uid = fm.uuid\n" +
                    "LEFT JOIN specimen s\n" +
                    "ON f._uid = s._uuid\n" +
                    "LEFT JOIN water_specimen w\n" +
                    "ON f._uid = w._uuid group by f._uid order by f.formdate DESC;";

    private static final String SQL_DELETE_USERS = "DROP TABLE IF EXISTS " + UsersContract.UsersTable.TABLE_NAME;
    private static final String SQL_SIGNUP_TABLE = "DROP TABLE IF EXISTS " + SignUpTable.TABLE_NAME;
    private static final String SQL_DELETE_FORMS = "DROP TABLE IF EXISTS " + FormsTable.TABLE_NAME;
    private static final String SQL_DELETE_CHILD_FORMS = "DROP TABLE IF EXISTS " + ChildContract.ChildTable.TABLE_NAME;
    private static final String SQL_DELETE_SERIAL = "DROP TABLE IF EXISTS " + singleSerial.TABLE_NAME;
    private static final String SQL_DELETE_TALUKAS = "DROP TABLE IF EXISTS " + EnumBlockTable.TABLE_NAME;
    private static final String SQL_DELETE_UCS = "DROP TABLE IF EXISTS " + UCsTable.TABLE_NAME;
    private static final String SQL_DELETE_D4WRA = "DROP TABLE IF EXISTS " + D4WRATable.TABLE_NAME;
    private static final String SQL_DELETE_MWRAS = "DROP TABLE IF EXISTS " + MWRATable.TABLE_NAME;
    private static final String SQL_DELETE_OUTCOME = "DROP TABLE IF EXISTS " + outcomeTable.TABLE_NAME;
    private static final String SQL_DELETE_FAMILYMEMBERS = "DROP TABLE IF EXISTS " + familyMembers.TABLE_NAME;
    private static final String SQL_DELETE_RECIENPTS = "DROP TABLE IF EXISTS " + RecipientsTable.TABLE_NAME;
    private static final String SQL_DELETE_NUTRITION = "DROP TABLE IF EXISTS " + NutritionTable.TABLE_NAME;
    private static final String SQL_DELETE_DECEASED = "DROP TABLE IF EXISTS " + DeceasedContract.DeceasedTable.TABLE_NAME;
    private static final String SQL_DELETE_BLRANDOM = "DROP TABLE IF EXISTS " + BLRandomContract.singleRandomHH.TABLE_NAME;
    private static final String SQL_DELETE_VERSIONAPP = "DROP TABLE IF EXISTS " + VersionAppTable.TABLE_NAME;
    final String SQL_CREATE_ANTHROS_MEMBERS = "CREATE TABLE " + AnthrosMembers.TABLE_NAME + " (" +
            AnthrosMembers.COLUMN__ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            AnthrosMembers.COLUMN_UID + " TEXT," +
            AnthrosMembers.COLUMN_UUID + " TEXT," +
            AnthrosMembers.COLUMN_FM_UID + " TEXT," +
            AnthrosMembers.COLUMN_PROJECTNAME + " TEXT," +
            AnthrosMembers.COLUMN_FORMDATE + " TEXT," +
            AnthrosMembers.COLUMN_DEVICEID + " TEXT," +
            AnthrosMembers.COLUMN_DEVICETAGID + " TEXT," +
            AnthrosMembers.COLUMN_USER + " TEXT," +
            AnthrosMembers.COLUMN_APPVERSION + " TEXT," +
            AnthrosMembers.COLUMN_ENM_NO + " TEXT," +
            AnthrosMembers.COLUMN_HH_NO + " TEXT," +
            AnthrosMembers.COLUMN_DOB + " TEXT," +
            AnthrosMembers.COLUMN_AGE + " TEXT," +
            AnthrosMembers.COLUMN_na204 + " TEXT," +
            AnthrosMembers.COLUMN_SA3 + " TEXT," +
            AnthrosMembers.COLUMN_ISTATUS + " TEXT," +
            AnthrosMembers.COLUMN_ISTATUS88x + " TEXT," +
            AnthrosMembers.COLUMN_END_TIME + " TEXT," +
            AnthrosMembers.COLUMN_SYNCED + " TEXT," +
            AnthrosMembers.COLUMN_SYNCEDDATE + " TEXT" +

            ");";

    private final String TAG = "DatabaseHelper";
    public String spDateT = new SimpleDateFormat("dd-MM-yy").format(new Date().getTime());

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USERS);
        db.execSQL(SQL_CREATE_FORMS);
        db.execSQL(SQL_CREATE_CHILD_FORMS);
        db.execSQL(SQL_CREATE_SERIAL);
        db.execSQL(SQL_CREATE_TALUKA);
        db.execSQL(SQL_CREATE_UC);
        db.execSQL(SQL_CREATE_ANTHROS_MEMBERS);
        db.execSQL(SQL_CREATE_MWRAS);
        db.execSQL(SQL_CREATE_FAMILY_MEMEBERS);
        db.execSQL(SQL_CREATE_VERSIONAPP);
        db.execSQL(SQL_CREATE_BL_RANDOM);
        db.execSQL(SQL_CREATE_SPECIMEN_MEMBERS);
        db.execSQL(SQL_CREATE_WATER_SPECIMEN_MEMBERS);
        db.execSQL(SQL_CREATE_MICRO);
        db.execSQL(SQL_CREATE_SUMMARY);
        db.execSQL(SQL_CREATE_SIGNUP);
        db.execSQL(SQL_CREATE_DWRA);
        db.execSQL(SQL_CREATE_DADOLES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_USERS);
        db.execSQL(SQL_DELETE_FORMS);
        db.execSQL(SQL_DELETE_CHILD_FORMS);
        db.execSQL(SQL_DELETE_SERIAL);
        db.execSQL(SQL_DELETE_TALUKAS);
        db.execSQL(SQL_DELETE_UCS);
        db.execSQL(SQL_DELETE_ANTHROS_MEMBERS);
        db.execSQL(SQL_DELETE_MWRAS);
        db.execSQL(SQL_DELETE_FAMILYMEMBERS);
        db.execSQL(SQL_DELETE_VERSIONAPP);
        db.execSQL(SQL_DELETE_BLRANDOM);
        db.execSQL(SQL_DELETE_D4WRA);
    }

    public void syncEnumBlocks(JSONArray Enumlist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(EnumBlockTable.TABLE_NAME, null, null);
        try {
            JSONArray jsonArray = Enumlist;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectCC = jsonArray.getJSONObject(i);

                EnumBlockContract Vc = new EnumBlockContract();
                Vc.Sync(jsonObjectCC);

                ContentValues values = new ContentValues();

                values.put(EnumBlockTable.COLUMN_COUNTRY_ID, Vc.getEbcode());
                values.put(EnumBlockTable.COLUMN_GEO_AREA, Vc.getGeoarea());
                values.put(EnumBlockTable.COLUMN_CLUSTER_AREA, Vc.getCluster());

                db.insert(EnumBlockTable.TABLE_NAME, null, values);
            }
        } catch (Exception e) {
        } finally {
            db.close();
        }
    }

    public void syncUCs(JSONArray UCslist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(UCsTable.TABLE_NAME, null, null);
        try {
            JSONArray jsonArray = UCslist;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectCC = jsonArray.getJSONObject(i);

                UCsContract Vc = new UCsContract();
                Vc.Sync(jsonObjectCC);

                ContentValues values = new ContentValues();

                values.put(UCsTable.COLUMN_UCCODE, Vc.getUccode());
                values.put(UCsTable.COLUMN_UCS_NAME, Vc.getUcsName());
                values.put(UCsTable.COLUMN_TALUKA_CODE, Vc.getTaluka_code());

                db.insert(UCsTable.TABLE_NAME, null, values);
            }
        } catch (Exception e) {
        } finally {
            db.close();
        }
    }

    public void syncVersionApp(JSONArray Versionlist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(VersionAppTable.TABLE_NAME, null, null);
        try {
            JSONArray jsonArray = Versionlist;
            JSONObject jsonObjectCC = jsonArray.getJSONObject(0);

            VersionAppContract Vc = new VersionAppContract();
            Vc.Sync(jsonObjectCC);

            ContentValues values = new ContentValues();

            values.put(VersionAppTable.COLUMN_PATH_NAME, Vc.getPathname());
            values.put(VersionAppTable.COLUMN_VERSION_CODE, Vc.getVersioncode());
            values.put(VersionAppTable.COLUMN_VERSION_NAME, Vc.getVersionname());

            db.insert(VersionAppTable.TABLE_NAME, null, values);
        } catch (Exception e) {
        } finally {
            db.close();
        }
    }

    public void syncFamilyMembers(JSONArray FamilyMembers) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(VersionAppTable.TABLE_NAME, null, null);
        try {
            JSONArray jsonArray = FamilyMembers;
            JSONObject jsonObjectCC = jsonArray.getJSONObject(0);

            FamilyMembersContract fm = new FamilyMembersContract();
            fm.Sync(jsonObjectCC);

            ContentValues values = new ContentValues();

            values.put(familyMembers.COLUMN_UID, fm.get_UID());
            values.put(familyMembers.COLUMN_UUID, fm.get_UUID());
            values.put(familyMembers.COLUMN_FORMDATE, fm.getFormDate());
            values.put(familyMembers.COLUMN_DEVICEID, fm.getDeviceId());
            values.put(familyMembers.COLUMN_DEVICETAGID, fm.getDevicetagID());
            values.put(familyMembers.COLUMN_USER, fm.getUser());
            values.put(familyMembers.COLUMN_APP_VERSION, fm.getApp_ver());
            values.put(familyMembers.COLUMN_SA2, fm.getsA2());
            values.put(familyMembers.COLUMN_ENM_NO, fm.getEnmNo());
            values.put(familyMembers.COLUMN_HH_NO, fm.getHhNo());
            values.put(familyMembers.COLUMN_AV, fm.getAv());
            values.put(familyMembers.COLUMN_FLAG, fm.getDelflag());
            values.put(familyMembers.COLUMN_KISH_SELECTED, fm.getKishSelected());
            values.put(familyMembers.COLUMN_KISH_SELECTED_MWRA_D, fm.getKishMWRASelected());
            values.put(familyMembers.COLUMN_KISH_SELECTED_ADOLESCENT, fm.getKishAdolsSelected());
            values.put(familyMembers.COLUMN_SYNCED, fm.getSynced());
            values.put(familyMembers.COLUMN_SYNCED_DATE, fm.getSyncedDate());

            db.insert(familyMembers.TABLE_NAME, null, values);
        } catch (Exception e) {
        } finally {
            db.close();
        }
    }

    public void syncBLRandom(JSONArray BLlist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(singleRandomHH.TABLE_NAME, null, null);
        try {
            JSONArray jsonArray = BLlist;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectCC = jsonArray.getJSONObject(i);

                BLRandomContract Vc = new BLRandomContract();
                Vc.Sync(jsonObjectCC);

                ContentValues values = new ContentValues();

                values.put(singleRandomHH.COLUMN_ID, Vc.get_ID());
                values.put(singleRandomHH.COLUMN_LUID, Vc.getLUID());
                values.put(singleRandomHH.COLUMN_STRUCTURE_NO, Vc.getStructure());
                values.put(singleRandomHH.COLUMN_FAMILY_EXT_CODE, Vc.getExtension());
                values.put(singleRandomHH.COLUMN_HH, Vc.getHh());
                values.put(singleRandomHH.COLUMN_ENUM_BLOCK_CODE, Vc.getSubVillageCode());
                values.put(singleRandomHH.COLUMN_RANDOMDT, Vc.getRandomDT());
                values.put(singleRandomHH.COLUMN_HH_HEAD, Vc.getHhhead());
                values.put(singleRandomHH.COLUMN_CONTACT, Vc.getContact());
                values.put(singleRandomHH.COLUMN_HH_SELECTED_STRUCT, Vc.getSelStructure());
                values.put(singleRandomHH.COLUMN_SNO_HH, Vc.getSno());

                db.insert(singleRandomHH.TABLE_NAME, null, values);
            }
        } catch (Exception e) {
        } finally {
            db.close();
        }
    }

    public FormsContract getPartialForms(String subAreaCode, String hh, String status) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_USER,
                FormsTable.COLUMN_RESP_LNO,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_ISTATUS88x,
                FormsTable.COLUMN_ISTATUSHH,
                FormsTable.COLUMN_GPSELEV,
                FormsTable.COLUMN_HH_NO,
                FormsTable.COLUMN_CLUSTER_NO,
                FormsTable.COLUMN_SA1,
                FormsTable.COLUMN_SA4,
                FormsTable.COLUMN_SA402,
                FormsTable.COLUMN_SA5,
                FormsTable.COLUMN_SA7,
                FormsTable.COLUMN_END_TIME,
                FormsTable.COLUMN_COUNT,
                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDATE,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_SYNCED,
                FormsTable.COLUMN_SYNCED_DATE,
                FormsTable.COLUMN_APP_VERSION
        };
        String whereClause = FormsTable.COLUMN_CLUSTER_NO + " =? AND " + FormsTable.COLUMN_HH_NO + " =? AND " + FormsTable.COLUMN_ISTATUS + " =?";
        String[] whereArgs = {subAreaCode, hh, status};
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable.COLUMN_HH_NO + " DESC LIMIT 1";

        FormsContract allFC = null;
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allFC = new FormsContract().Hydrate(c);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }


    public String getUIDByHH(String cluster, String hhno) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
        };

        String whereClause = FormsTable.COLUMN_CLUSTER_NO + " =? AND " + FormsTable.COLUMN_HH_NO + " =? AND ("
                + FormsTable.COLUMN_SYNCED + " is null OR " + FormsTable.COLUMN_SYNCED + " = '') AND ("
                + FormsTable.COLUMN_ISTATUS + " =? OR " + FormsTable.COLUMN_ISTATUS + " =? OR " + FormsTable.COLUMN_ISTATUS + " = '' OR " + FormsTable.COLUMN_ISTATUS + " is null)";
        String[] whereArgs = new String[]{cluster, hhno, "1", "7"};

        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable._ID + "  DESC LIMIT 1";

        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext() && c.getCount() == 1) {
                return (c.getString(c.getColumnIndex(FormsTable.COLUMN_UID)));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return null;
    }

    public Collection<SignupContract> getUnsyncedSignups() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                SignUpTable._ID,
                SignUpTable.FULLNAME,
                SignUpTable.DESIGNATION,
                SignUpTable.USERNAME,
                SignUpTable.PASSWORD,
                SignUpTable.COUNTRY_ID,
                SignUpTable.COLUMN_DEVICEID,
                SignUpTable.COLUMN_FORMDATE,
        };

        String whereClause = SignUpTable.COLUMN_SYNCED + " is null OR " + SignUpTable.COLUMN_SYNCED + " = '' ";
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;

        Collection<SignupContract> allLC = new ArrayList<>();
        try {
            c = db.query(
                    SignUpTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allLC.add(new SignupContract().Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allLC;
    }

    public ArrayList<FamilyMembersContract> getAllHHforAnthro(String cluster, String hh) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {

                familyMembers.COLUMN_ID,
                familyMembers.COLUMN_UID,
                familyMembers.COLUMN_UUID,
                familyMembers.COLUMN_FORMDATE,
                familyMembers.COLUMN_USER,
                familyMembers.COLUMN_HH_NO,
                familyMembers.COLUMN_ENM_NO,
                familyMembers.COLUMN_SA2,
                familyMembers.COLUMN_AV,
                familyMembers.COLUMN_DEVICETAGID,
                familyMembers.COLUMN_DEVICEID,
                familyMembers.COLUMN_SYNCED,
                familyMembers.COLUMN_SYNCED_DATE,
                familyMembers.COLUMN_FLAG,
                familyMembers.COLUMN_KISH_SELECTED,
                familyMembers.COLUMN_KISH_SELECTED_MWRA_D,
                familyMembers.COLUMN_KISH_SELECTED_ADOLESCENT,
                familyMembers.COLUMN_APP_VERSION

        };

        String whereClause = familyMembers.COLUMN_ENM_NO + "=? AND "
                + familyMembers.COLUMN_HH_NO + "=? AND " + familyMembers.COLUMN_AV + "=?";
        String[] whereArgs = new String[]{cluster, hh, "1"};
        String groupBy = familyMembers.COLUMN_FORMDATE;

        String having = null;

        String orderBy =
                familyMembers.COLUMN_ID + " ASC";

        ArrayList<FamilyMembersContract> allBL = new ArrayList<>();
        try {
            c = db.query(
                    familyMembers.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allBL.add(new FamilyMembersContract().Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allBL;
    }

    public Collection<FamilyMembersContract> getAllMembersByHHforAnthro(String cluster, String hh, String uuid, String formDate, Boolean flag) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {

                familyMembers.COLUMN_ID,
                familyMembers.COLUMN_UID,
                familyMembers.COLUMN_UUID,
                familyMembers.COLUMN_FORMDATE,
                familyMembers.COLUMN_USER,
                familyMembers.COLUMN_HH_NO,
                familyMembers.COLUMN_ENM_NO,
                familyMembers.COLUMN_SA2,
                familyMembers.COLUMN_AV,
                familyMembers.COLUMN_DEVICETAGID,
                familyMembers.COLUMN_DEVICEID,
                familyMembers.COLUMN_SYNCED,
                familyMembers.COLUMN_SYNCED_DATE,
                familyMembers.COLUMN_FLAG,
                familyMembers.COLUMN_KISH_SELECTED,
                familyMembers.COLUMN_KISH_SELECTED_MWRA_D,
                familyMembers.COLUMN_KISH_SELECTED_ADOLESCENT,
                familyMembers.COLUMN_APP_VERSION

        };

        String whereClause = familyMembers.COLUMN_ENM_NO + "=? AND "
                + familyMembers.COLUMN_HH_NO + "=? AND " + familyMembers.COLUMN_AV + "=? AND "
                + familyMembers.COLUMN_UUID + " =? AND " + familyMembers.COLUMN_FORMDATE + " =?";
        String[] whereArgs = new String[]{cluster, hh, "1", uuid, formDate};
        String groupBy = null;
        String having = null;

        String orderBy =
                familyMembers.COLUMN_ID + " ASC";

        Collection<FamilyMembersContract> allBL = new ArrayList<>();
        try {
            c = db.query(
                    familyMembers.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FamilyMembersContract dc = new FamilyMembersContract().Hydrate(c);
                if (flag) {
                    if (!getAnthroMembersExist(dc.get_UID())) {
                        allBL.add(dc);
                    }
                } else {
                    allBL.add(dc);
                }
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allBL;
    }

    public Boolean getAnthroMembersExist(String uid) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                AnthrosMembers.COLUMN__ID,
                AnthrosMembers.COLUMN_FM_UID,
        };
        String whereClause = AnthrosMembers.COLUMN_FM_UID + " =? ";
        String[] whereArgs = {uid};
        String groupBy = null;
        String having = null;

        String orderBy =
                AnthrosMembers.COLUMN_FM_UID + " DESC";

        Boolean allFC = false;
        try {
            c = db.query(
                    AnthrosMembers.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            int cursorCount = c.getCount();
            if (cursorCount > 0) {
                c.moveToFirst();
                return true;
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public Collection<WaterSpecimenContract> getMicroforresults(String cluster, String hh) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {

                WaterSpecimenTable.COLUMN__ID,
                WaterSpecimenTable.COLUMN__UID,
                WaterSpecimenTable.COLUMN__UUID,
                WaterSpecimenTable.COLUMN_FORMDATE,
                WaterSpecimenTable.COLUMN_USER,
                WaterSpecimenTable.COLUMN_HH,
                WaterSpecimenTable.COLUMN_CLUSTER,
                WaterSpecimenTable.COLUMN_SE2,
                WaterSpecimenTable.COLUMN_DEVICETAGID,
                WaterSpecimenTable.COLUMN_DEVICEID,
                WaterSpecimenTable.COLUMN_SYNCED,
                WaterSpecimenTable.COLUMN_SYNCED_DATE,
                WaterSpecimenTable.COLUMN_APPVERSION

        };

        String whereClause = WaterSpecimenTable.COLUMN_CLUSTER + "=? AND "
                + WaterSpecimenTable.COLUMN_HH + "=?";
        String[] whereArgs = new String[]{cluster, hh};
        String groupBy = null;
        String having = null;

        String orderBy =
                WaterSpecimenTable.COLUMN__ID + " DESC Limit 1";

        Collection<WaterSpecimenContract> allBL = new ArrayList<>();
        try {
            c = db.query(
                    WaterSpecimenTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                WaterSpecimenContract dc = new WaterSpecimenContract();
                allBL.add(dc.Hydrate(c, 0));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allBL;
    }


    public Collection<FamilyMembersContract> getAllMembersByHH(String uid) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {

                familyMembers.COLUMN_ID,
                familyMembers.COLUMN_UID,
                familyMembers.COLUMN_UUID,
                familyMembers.COLUMN_FORMDATE,
                familyMembers.COLUMN_USER,
                familyMembers.COLUMN_HH_NO,
                familyMembers.COLUMN_ENM_NO,
                familyMembers.COLUMN_SA2,
                familyMembers.COLUMN_AV,
                familyMembers.COLUMN_DEVICETAGID,
                familyMembers.COLUMN_DEVICEID,
                familyMembers.COLUMN_SYNCED,
                familyMembers.COLUMN_SYNCED_DATE,
                familyMembers.COLUMN_APP_VERSION,
                familyMembers.COLUMN_FLAG,
                familyMembers.COLUMN_KISH_SELECTED,
                familyMembers.COLUMN_KISH_SELECTED_MWRA_D,
                familyMembers.COLUMN_KISH_SELECTED_ADOLESCENT,

        };

        String whereClause = familyMembers.COLUMN_UUID + "=? ";
        //+ familyMembers.COLUMN_HH_NO + "=?";
        String[] whereArgs = new String[]{uid};
        String groupBy = null;
        String having = null;

        String orderBy =
                familyMembers.COLUMN_ID + " ASC";

        Collection<FamilyMembersContract> allBL = new ArrayList<>();
        try {
            c = db.query(
                    familyMembers.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FamilyMembersContract dc = new FamilyMembersContract();
                allBL.add(dc.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allBL;
    }

    public ArrayList<FamilyMembersContract> getMotherForChild(String uuid, String hhno, String enmmo) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {

                familyMembers.COLUMN_ID,
                familyMembers.COLUMN_UID,
                familyMembers.COLUMN_UUID,
                familyMembers.COLUMN_FORMDATE,
                familyMembers.COLUMN_USER,
                familyMembers.COLUMN_HH_NO,
                familyMembers.COLUMN_ENM_NO,
                familyMembers.COLUMN_SA2,
                familyMembers.COLUMN_AV,
                familyMembers.COLUMN_DEVICETAGID,
                familyMembers.COLUMN_DEVICEID,
                familyMembers.COLUMN_SYNCED,
                familyMembers.COLUMN_SYNCED_DATE,
                familyMembers.COLUMN_APP_VERSION,
                familyMembers.COLUMN_FLAG,
                familyMembers.COLUMN_KISH_SELECTED,
                familyMembers.COLUMN_KISH_SELECTED_MWRA_D,
                familyMembers.COLUMN_KISH_SELECTED_ADOLESCENT,

        };

        String whereClause = familyMembers.COLUMN_UUID + "=? AND " + familyMembers.COLUMN_HH_NO + "=? AND " + familyMembers.COLUMN_ENM_NO + "=?";
        String[] whereArgs = {uuid, hhno, enmmo};
        String groupBy = null;
        String having = null;

        String orderBy = familyMembers.COLUMN_ID + " ASC";

        ArrayList<FamilyMembersContract> allBL = new ArrayList<>();
        try {
            c = db.query(
                    familyMembers.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FamilyMembersContract dc = new FamilyMembersContract();
                allBL.add(dc.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allBL;
    }


    public Collection<BLRandomContract> getAllBLRandom(String subAreaCode, String hh) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                singleRandomHH.COLUMN_ID,
                singleRandomHH.COLUMN_LUID,
                singleRandomHH.COLUMN_STRUCTURE_NO,
                singleRandomHH.COLUMN_FAMILY_EXT_CODE,
                singleRandomHH.COLUMN_HH,
                singleRandomHH.COLUMN_ENUM_BLOCK_CODE,
                singleRandomHH.COLUMN_RANDOMDT,
                singleRandomHH.COLUMN_HH_SELECTED_STRUCT,
                singleRandomHH.COLUMN_CONTACT,
                singleRandomHH.COLUMN_HH_HEAD,
                singleRandomHH.COLUMN_SNO_HH
        };

        String whereClause = singleRandomHH.COLUMN_ENUM_BLOCK_CODE + "=? AND " +
                singleRandomHH.COLUMN_HH + "=?";
        String[] whereArgs = new String[]{subAreaCode, hh};
        String groupBy = null;
        String having = null;

        String orderBy =
                singleRandomHH.COLUMN_ID + " ASC";

        Collection<BLRandomContract> allBL = new ArrayList<>();
        try {
            c = db.query(
                    singleRandomHH.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                BLRandomContract dc = new BLRandomContract();
                allBL.add(dc.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allBL;
    }

    public EnumBlockContract getEnumBlock(String cluster) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                EnumBlockTable._ID,
                EnumBlockTable.COLUMN_COUNTRY_ID,
                EnumBlockTable.COLUMN_GEO_AREA,
                EnumBlockTable.COLUMN_CLUSTER_AREA
        };

        String whereClause = EnumBlockTable.COLUMN_CLUSTER_AREA + " =?";
        String[] whereArgs = new String[]{cluster};
        String groupBy = null;
        String having = null;

        String orderBy =
                EnumBlockTable._ID + " ASC";

        EnumBlockContract allEB = null;
        try {
            c = db.query(
                    EnumBlockTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allEB = new EnumBlockContract().HydrateEnum(c);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allEB;
    }

    public VersionAppContract getVersionApp() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                VersionAppTable._ID,
                VersionAppTable.COLUMN_VERSION_CODE,
                VersionAppTable.COLUMN_VERSION_NAME,
                VersionAppTable.COLUMN_PATH_NAME
        };

        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy = null;

        VersionAppContract allVC = new VersionAppContract();
        try {
            c = db.query(
                    VersionAppTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allVC.hydrate(c);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allVC;
    }

    public SerialContract getSerialWRTDate(String date) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                singleSerial._ID,
                singleSerial.COLUMN_DEVICE_ID,
                singleSerial.COLUMN_DATE,
                singleSerial.COLUMN_SERIAL_NO,
        };

        String whereClause = singleSerial.COLUMN_DATE + " =?";
        String[] whereArgs = new String[]{date};
        String groupBy = null;
        String having = null;

        String orderBy =
                singleSerial._ID + " ASC";

        SerialContract allDC = new SerialContract();
        try {
            c = db.query(
                    singleSerial.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allDC.hydrate(c);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allDC;
    }

    public Collection<UCsContract> getAllUCsByTalukas(String taluka_code) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                UCsTable._ID,
                UCsTable.COLUMN_UCCODE,
                UCsTable.COLUMN_UCS_NAME,
                UCsTable.COLUMN_TALUKA_CODE
        };

        String whereClause = UCsTable.COLUMN_TALUKA_CODE + " = ?";
        String[] whereArgs = {taluka_code};
        String groupBy = null;
        String having = null;

        String orderBy =
                UCsTable.COLUMN_UCS_NAME + " ASC";

        Collection<UCsContract> allPC = new ArrayList<>();
        try {
            c = db.query(
                    UCsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                UCsContract pc = new UCsContract();
                allPC.add(pc.hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allPC;
    }

    public void syncUser(JSONArray userlist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(UsersTable.TABLE_NAME, null, null);
        try {
            JSONArray jsonArray = userlist;
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObjectUser = jsonArray.getJSONObject(i);

                UsersContract user = new UsersContract();
                user.Sync(jsonObjectUser);
                ContentValues values = new ContentValues();

                values.put(UsersContract.UsersTable.ROW_USERNAME, user.getUserName());
                values.put(UsersTable.ROW_PASSWORD, user.getPassword());
                values.put(UsersTable.COUNTRY_ID, user.getCOUNTRY_ID());
                db.insert(UsersTable.TABLE_NAME, null, values);
            }


        } catch (Exception e) {
            Log.d(TAG, "syncUser(e): " + e);
        } finally {
            db.close();
        }
    }

    public void syncAnthroFromDevice(JSONArray fmlist) {
        SQLiteDatabase db = this.getWritableDatabase();
        //db.delete(UsersTable.TABLE_NAME, null, null);
        try {
            JSONArray jsonArray = fmlist;
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObjectDT = jsonArray.getJSONObject(i);

                switch (jsonObjectDT.getString("project_name")) {
                    case "Central Asia Stunting Initiative 2019 - Team Leaders":
                        BLRandomInsertion(jsonObjectDT, db);
                        break;
                    case "Central Asia Stunting Initiative 2019":
                        AntrhoInsertion(jsonObjectDT, db);
                        break;
                }

            }


        } catch (Exception e) {
            Log.d(TAG, "syncAnthro(e): " + e);
        } finally {
            db.close();
        }
    }

    public void AntrhoInsertion(JSONObject jsonObjectDT, SQLiteDatabase db) throws JSONException {
        FamilyMembersContract fmc = new FamilyMembersContract();
        fmc.Sync(jsonObjectDT);
        ContentValues values = new ContentValues();

        values.put(familyMembers.COLUMN_UID, fmc.get_UID());
        values.put(familyMembers.COLUMN_UUID, fmc.get_UUID());
        values.put(familyMembers.COLUMN_FORMDATE, fmc.getFormDate());
        values.put(familyMembers.COLUMN_USER, fmc.getUser());
        values.put(familyMembers.COLUMN_HH_NO, fmc.getHhNo());
        values.put(familyMembers.COLUMN_ENM_NO, fmc.getEnmNo());
        values.put(familyMembers.COLUMN_SA2, fmc.getsA2());
        values.put(familyMembers.COLUMN_AV, fmc.getAv());
        values.put(familyMembers.COLUMN_DEVICETAGID, fmc.getDeviceId());
        values.put(familyMembers.COLUMN_DEVICEID, fmc.getDeviceId());
        db.insert(familyMembers.TABLE_NAME, null, values);
    }

    public void BLRandomInsertion(JSONObject jsonObjectDT, SQLiteDatabase db) throws JSONException {
        BLRandomContract Vc = new BLRandomContract();
        Vc.Sync(jsonObjectDT);

        if (!CheckUIDBLRandomExist(Vc.getLUID())) {

            ContentValues values = new ContentValues();

            values.put(singleRandomHH.COLUMN_ID, Vc.get_ID());
            values.put(singleRandomHH.COLUMN_LUID, Vc.getLUID());
            values.put(singleRandomHH.COLUMN_STRUCTURE_NO, Vc.getStructure());
            values.put(singleRandomHH.COLUMN_FAMILY_EXT_CODE, Vc.getExtension());
            values.put(singleRandomHH.COLUMN_HH, Vc.getHh());
            values.put(singleRandomHH.COLUMN_ENUM_BLOCK_CODE, Vc.getSubVillageCode());
            values.put(singleRandomHH.COLUMN_RANDOMDT, Vc.getRandomDT());
            values.put(singleRandomHH.COLUMN_HH_HEAD, Vc.getHhhead());
            values.put(singleRandomHH.COLUMN_CONTACT, Vc.getContact());
            values.put(singleRandomHH.COLUMN_HH_SELECTED_STRUCT, Vc.getSelStructure());
            values.put(singleRandomHH.COLUMN_SNO_HH, Vc.getSno());

            long count = db.insert(singleRandomHH.TABLE_NAME, null, values);
        }
    }

    public boolean CheckUIDBLRandomExist(String uid) {

        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        String[] columns = {
                singleRandomHH.COLUMN_ID
        };

// Which row to update, based on the ID
        String selection = singleRandomHH.COLUMN_LUID + " =?";
        String[] selectionArgs = {uid};
        Cursor cursor = db.query(singleRandomHH.TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();
        cursor.close();
        return cursorCount > 0;
    }

    public boolean CheckBLRandomExist(String luid, String cluster, String str, String ext) {

        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        String[] columns = {
                singleRandomHH.COLUMN_ID
        };

// Which row to update, based on the ID
        String selection = singleRandomHH.COLUMN_LUID + " =? AND " + singleRandomHH.COLUMN_ENUM_BLOCK_CODE + " =? AND " + singleRandomHH.COLUMN_STRUCTURE_NO + " =? AND " + singleRandomHH.COLUMN_FAMILY_EXT_CODE + " =?";
        String[] selectionArgs = {luid, cluster, str, ext};
        Cursor cursor = db.query(singleRandomHH.TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();
        cursor.close();
        return cursorCount > 0;
    }

    public boolean Login(String username, String password) throws SQLException {

        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        String[] columns = {
                UsersTable._ID,
                UsersTable.ROW_USERNAME,
                UsersTable.COUNTRY_ID,
                UsersTable.ROW_PASSWORD
        };

// Which row to update, based on the ID
        String selection = UsersContract.UsersTable.ROW_USERNAME + " = ?" + " AND " + UsersContract.UsersTable.ROW_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(UsersContract.UsersTable.TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        /*cursor.close();
        db.close();
        return cursorCount > 0;*/

        if (cursorCount > 0) {
            cursor.moveToFirst();
            MainApp.usersContract = new UsersContract().Hydrate(cursor);
            return true;
        }

        return false;
    }

    public List<FormsContract> getFormsByDSS(String dssID) {
        List<FormsContract> formList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + FormsTable.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                FormsContract fc = new FormsContract();
                formList.add(fc.Hydrate(c));
            } while (c.moveToNext());
        }

        // return contact list
        return formList;
    }

    public Long addForm(FormsContract fc, int type) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        if (type == 0) {
            values.put(FormsTable.COLUMN_PROJECT_NAME, fc.getProjectName());
            values.put(FormsTable.COLUMN_UID, fc.getUID());
            values.put(FormsTable.COLUMN_FORMDATE, fc.getFormDate());
            values.put(FormsTable.COLUMN_USER, fc.getUser());
            values.put(FormsTable.COLUMN_RESP_LNO, fc.getRespLineNo());
            values.put(FormsTable.COLUMN_CLUSTER_NO, fc.getClusterNo());
            values.put(FormsTable.COLUMN_HH_NO, fc.getHhNo());
            values.put(FormsTable.COLUMN_GPSELEV, fc.getGpsElev());
            values.put(FormsTable.COLUMN_ISTATUS, fc.getIstatus());
            values.put(FormsTable.COLUMN_ISTATUS88x, fc.getIstatus88x());
            values.put(FormsTable.COLUMN_ISTATUSHH, fc.getIstatusHH());

            //values.put(FormsTable.COLUMN_END_TIME, fc.getEndtime());
            values.put(FormsTable.COLUMN_COUNT, fc.getCount());
            values.put(FormsTable.COLUMN_GPSLAT, fc.getGpsLat());
            values.put(FormsTable.COLUMN_GPSLNG, fc.getGpsLng());
            values.put(FormsTable.COLUMN_GPSDATE, fc.getGpsDT());
            values.put(FormsTable.COLUMN_GPSACC, fc.getGpsAcc());
            values.put(FormsTable.COLUMN_DEVICETAGID, fc.getDevicetagID());
            values.put(FormsTable.COLUMN_DEVICEID, fc.getDeviceID());
            values.put(FormsTable.COLUMN_APP_VERSION, fc.getAppversion());
        }
        if (type == 0 || type == 1) {
            values.put(FormsTable.COLUMN_SA1, fc.getsA1());
        }
        if (type == 0 || type == 4) {
            values.put(FormsTable.COLUMN_SA4, fc.getsA4());
        }
        if (type == 0 || type == 4) {
            values.put(FormsTable.COLUMN_SA402, fc.getsA402());
        }
        if (type == 0 || type == 5) {
            values.put(FormsTable.COLUMN_SA5, fc.getsA5());
        }
        if (type == 0 || type == 7) {
            values.put(FormsTable.COLUMN_SA7, fc.getsA7());
        }

        values.put(FormsTable.COLUMN_SYNCED, fc.getSynced());
        values.put(FormsTable.COLUMN_SYNCED_DATE, fc.getSynced_date());

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        if (type == 0) {
            newRowId = db.insert(
                    FormsTable.TABLE_NAME,
                    FormsTable.COLUMN_NAME_NULLABLE,
                    values);
        } else {
            newRowId = db.update(
                    FormsTable.TABLE_NAME,
                    values,
                    FormsTable.COLUMN_UID + " = ?",
                    new String[]{fc.getUID()}
            );
        }
        return newRowId;
    }

    public Long addSignUpForm(SignupContract fc) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(SignUpTable.FULLNAME, fc.getFullName());
        values.put(SignUpTable.DESIGNATION, fc.getDesignation());
        values.put(SignUpTable.USERNAME, fc.getUserName());
        values.put(SignUpTable.PASSWORD, fc.getPassword());
        values.put(SignUpTable.COUNTRY_ID, fc.getCountryId());
        values.put(SignUpTable.COLUMN_DEVICEID, fc.getDeviceID());
        values.put(SignUpTable.COLUMN_FORMDATE, fc.getFormDate());

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                SignUpTable.TABLE_NAME,
                null,
                values);
        return newRowId;
    }

    public Long addSummaryForms(SummaryContract ss, int type) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        values.put(singleSum.COLUMN_PROJECTNAME, ss.getProjectName());
        values.put(singleSum.COLUMN__UID, ss.get_uid());
        values.put(singleSum.COLUMN_CLUSTERNO, ss.getClusterno());
        values.put(singleSum.COLUMN_HHNO, ss.getHhno());
        values.put(singleSum.COLUMN_SYNCED, ss.getSynced());
        values.put(singleSum.COLUMN_SYNCEDDATE, ss.getSynceddate());
        values.put(singleSum.COLUMN_USER, ss.getUser());
        values.put(singleSum.COLUMN_FORMDATE, ss.getFormdate());
        values.put(singleSum.COLUMN_DEVICEID, ss.getDeviceid());
        values.put(singleSum.COLUMN_DEVICETAGID, ss.getDevicetagID());
        values.put(singleSum.COLUMN_APPVERSION, ss.getAppversion());

        switch (type) {
            case 1:
                values.put(singleSum.COLUMN_HH, ss.getHh());
                break;
            case 2:
                values.put(singleSum.COLUMN_WOMEN, ss.getWomen());
                break;
            case 3:
                values.put(singleSum.COLUMN_CHILD, ss.getChild());
                break;
            case 4:
                values.put(singleSum.COLUMN_ANTHRO, ss.getAnthro());
                break;
            case 5:
                values.put(singleSum.COLUMN_SPECIMEN, ss.getSpecimen());
                break;
            case 6:
                values.put(singleSum.COLUMN_WATER, ss.getWater());
                break;
        }


        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(
                singleSum.TABLE_NAME,
                singleSum.COLUMN_NAME_NULLABLE,
                values);

        return newRowId;
    }

    public Long addFamilyMembers(FamilyMembersContract fmc) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(familyMembers.COLUMN_PROJECT_NAME, fmc.getProjectName());
        values.put(familyMembers.COLUMN_UID, fmc.get_UID());
        values.put(familyMembers.COLUMN_UUID, fmc.get_UUID());
        values.put(familyMembers.COLUMN_FORMDATE, fmc.getFormDate());
        values.put(familyMembers.COLUMN_USER, fmc.getUser());
        values.put(familyMembers.COLUMN_ENM_NO, fmc.getEnmNo());
        values.put(familyMembers.COLUMN_HH_NO, fmc.getHhNo());
        values.put(familyMembers.COLUMN_DEVICETAGID, fmc.getDevicetagID());
        values.put(familyMembers.COLUMN_DEVICEID, fmc.getDeviceId());
        values.put(familyMembers.COLUMN_SYNCED, fmc.getSynced());
        values.put(familyMembers.COLUMN_SYNCED_DATE, fmc.getSyncedDate());
        values.put(familyMembers.COLUMN_APP_VERSION, fmc.getApp_ver());
        values.put(familyMembers.COLUMN_FLAG, fmc.getDelflag());
        values.put(familyMembers.COLUMN_KISH_SELECTED, fmc.getKishSelected());
        values.put(familyMembers.COLUMN_KISH_SELECTED_ADOLESCENT, fmc.getKishAdolsSelected());
        values.put(familyMembers.COLUMN_KISH_SELECTED_MWRA_D, fmc.getKishMWRASelected());


        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                familyMembers.TABLE_NAME,
                familyMembers.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
    }

    public Long addD4WRA(D4WRAContract fmc) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(D4WRATable.COLUMN_PROJECTNAME, fmc.getProjectName());
        values.put(D4WRATable.COLUMN_UID, fmc.get_UID());
        values.put(D4WRATable.COLUMN_UUID, fmc.get_UUID());
        values.put(D4WRATable.COLUMN_FM_UID, fmc.getFMUID());
        values.put(D4WRATable.COLUMN_FORMDATE, fmc.getFormDate());
        values.put(D4WRATable.COLUMN_SD1, fmc.getsD1());
        values.put(D4WRATable.COLUMN_DSERIALNO, fmc.getD1SerialNo());
        values.put(D4WRATable.COLUMN_FTYPE, fmc.getfType());
        values.put(D4WRATable.COLUMN_USER, fmc.getUser());
        values.put(D4WRATable.COLUMN_DEVICETAGID, fmc.getDevicetagID());
        values.put(D4WRATable.COLUMN_DEVICEID, fmc.getDeviceId());
        values.put(D4WRATable.COLUMN_SYNCED, fmc.getSynced());
        values.put(D4WRATable.COLUMN_SYNCEDDATE, fmc.getSyncedDate());
        values.put(D4WRATable.COLUMN_APP_VER, fmc.getApp_ver());

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                D4WRATable.TABLE_NAME,
                null,
                values);
        return newRowId;
    }

    public Long addD6Adoles(D6AdolesContract fmc) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(D6AdolesTable.COLUMN_PROJECTNAME, fmc.getProjectName());
        values.put(D6AdolesTable.COLUMN_UID, fmc.get_UID());
        values.put(D6AdolesTable.COLUMN_UUID, fmc.get_UUID());
        values.put(D6AdolesTable.COLUMN_FM_UID, fmc.getFMUID());
        values.put(D6AdolesTable.COLUMN_FORMDATE, fmc.getFormDate());
        values.put(D6AdolesTable.COLUMN_SD6, fmc.getsD6());
        values.put(D6AdolesTable.COLUMN_FMSERIALNO, fmc.getFmSerialNo());
        values.put(D6AdolesTable.COLUMN_FORMDATE, fmc.getFormDate());
        values.put(D6AdolesTable.COLUMN_USER, fmc.getUser());
        values.put(D6AdolesTable.COLUMN_DEVICETAGID, fmc.getDevicetagID());
        values.put(D6AdolesTable.COLUMN_DEVICEID, fmc.getDeviceId());
        values.put(D6AdolesTable.COLUMN_SYNCED, fmc.getSynced());
        values.put(D6AdolesTable.COLUMN_SYNCEDDATE, fmc.getSyncedDate());
        values.put(D6AdolesTable.COLUMN_APP_VER, fmc.getApp_ver());

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                D6AdolesTable.TABLE_NAME,
                null,
                values);
        return newRowId;
    }

    public Long addDevice(DeviceContract dc) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DeviceContract.DeviceTable.COLUMN_IMEI, dc.getimei());
        values.put(DeviceContract.DeviceTable.COLUMN_APPVERSION, dc.getappversion());
        values.put(DeviceContract.DeviceTable.COLUMN_TAGID, dc.gettagID());


        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                DeviceContract.DeviceTable.TABLE_NAME,
                DeviceContract.DeviceTable.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
    }

    public Long addSpecimenMembers(SpecimenContract fmc) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(SpecimenTable.COLUMN_PROJECTNAME, fmc.getProjectName());
        values.put(SpecimenTable.COLUMN__UID, fmc.getUID());
        values.put(SpecimenTable.COLUMN__UUID, fmc.getUUID());
        values.put(SpecimenTable.COLUMN_FM_UID, fmc.getFMUID());
        values.put(SpecimenTable.COLUMN_FORMDATE, fmc.getFormDate());
        values.put(SpecimenTable.COLUMN_USER, fmc.getUser());
        values.put(SpecimenTable.COLUMN_LINENO, fmc.getLineNo());
        values.put(SpecimenTable.COLUMN_CLUSTER, fmc.getClusterno());
        values.put(SpecimenTable.COLUMN_HH, fmc.getHhno());
        values.put(SpecimenTable.COLUMN_SE1, fmc.getsE1());
        values.put(SpecimenTable.COLUMN_DEVICETAGID, fmc.getDevicetagID());
        values.put(SpecimenTable.COLUMN_DEVICEID, fmc.getDeviceID());
        values.put(SpecimenTable.COLUMN_SYNCED, fmc.getSynced());
        values.put(SpecimenTable.COLUMN_SYNCED_DATE, fmc.getSynced_date());
        values.put(SpecimenTable.COLUMN_APPVERSION, fmc.getAppversion());


        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                SpecimenTable.TABLE_NAME,
                SpecimenTable.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
    }

    public Long addWaterSpecimenForm(WaterSpecimenContract fmc) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(WaterSpecimenTable.COLUMN_PROJECTNAME, fmc.getProjectName());
        values.put(WaterSpecimenTable.COLUMN__UID, fmc.getUID());
        values.put(WaterSpecimenTable.COLUMN__UUID, fmc.getUUID());
        //values.put(MicroTable.COLUMN_FM_UID, fmc.getFMUID());
        values.put(WaterSpecimenTable.COLUMN_FORMDATE, fmc.getFormDate());
        values.put(WaterSpecimenTable.COLUMN_USER, fmc.getUser());
        //values.put(SpecimenTable.COLUMN_WUID, fmc.getLineNo());
        values.put(WaterSpecimenTable.COLUMN_CLUSTER, fmc.getClusterno());
        values.put(WaterSpecimenTable.COLUMN_HH, fmc.getHhno());
        values.put(WaterSpecimenTable.COLUMN_SE2, fmc.getsE2());
        values.put(WaterSpecimenTable.COLUMN_DEVICETAGID, fmc.getDevicetagID());
        values.put(WaterSpecimenTable.COLUMN_DEVICEID, fmc.getDeviceID());
        values.put(WaterSpecimenTable.COLUMN_SYNCED, fmc.getSynced());
        values.put(WaterSpecimenTable.COLUMN_SYNCED_DATE, fmc.getSynced_date());
        values.put(WaterSpecimenTable.COLUMN_APPVERSION, fmc.getAppversion());


        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                WaterSpecimenTable.TABLE_NAME,
                WaterSpecimenTable.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
    }


    public Long addMicroForm(MicroContract fmc) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(MicroTable.COLUMN_PROJECTNAME, fmc.getProjectName());
        values.put(MicroTable.COLUMN__UID, fmc.getUID());
        values.put(MicroTable.COLUMN__UUID, fmc.getUUID());
        values.put(MicroTable.COLUMN_WUID, fmc.getWUID());
        values.put(MicroTable.COLUMN_FORMDATE, fmc.getFormDate());
        values.put(MicroTable.COLUMN_USER, fmc.getUser());
        values.put(MicroTable.COLUMN_CLUSTER, fmc.getClusterno());
        values.put(MicroTable.COLUMN_HH, fmc.getHhno());
        values.put(MicroTable.COLUMN_SM, fmc.getsM());
        values.put(MicroTable.COLUMN_DEVICETAGID, fmc.getDevicetagID());
        values.put(MicroTable.COLUMN_DEVICEID, fmc.getDeviceID());
        values.put(MicroTable.COLUMN_SYNCED, fmc.getSynced());
        values.put(MicroTable.COLUMN_SYNCED_DATE, fmc.getSynced_date());
        values.put(MicroTable.COLUMN_APPVERSION, fmc.getAppversion());


        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                MicroTable.TABLE_NAME,
                MicroTable.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
    }


    public int updateSpecimenMemberID() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(SpecimenTable.COLUMN__UID, MainApp.smc.getUID());

// Which row to update, based on the ID
        String selection = SpecimenTable.COLUMN__ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.smc.get_ID())};

        int count = db.update(SpecimenTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateWaterSpecimenMemberID() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(WaterSpecimenTable.COLUMN__UID, MainApp.wsc.getUID());

// Which row to update, based on the ID
        String selection = WaterSpecimenTable.COLUMN__ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.wsc.get_ID())};

        int count = db.update(WaterSpecimenTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }


    public int updateMicroID() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(MicroTable.COLUMN__UID, MainApp.msc.getUID());

// Which row to update, based on the ID
        String selection = MicroTable.COLUMN__ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.msc.get_ID())};

        int count = db.update(MicroTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }


    public Long addDeceasedMembers(DeceasedContract dc, int type) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        long newRowId;
        values.put(DeceasedContract.DeceasedTable.COLUMN_SH8, dc.getsH8());

        if (type == 0) {
            values.put(DeceasedContract.DeceasedTable.COLUMN_PROJECTNAME, dc.getProjectName());
            values.put(DeceasedContract.DeceasedTable.COLUMN__UID, dc.getUID());
            values.put(DeceasedContract.DeceasedTable.COLUMN__UUID, dc.getUUID());
            values.put(DeceasedContract.DeceasedTable.COLUMN_FORMDATE, dc.getFormDate());
            values.put(DeceasedContract.DeceasedTable.COLUMN_USER, dc.getUser());

            values.put(DeceasedContract.DeceasedTable.COLUMN_DEVICETAGID, dc.getDevicetagID());
            values.put(DeceasedContract.DeceasedTable.COLUMN_DEVICEID, dc.getDeviceID());
            values.put(DeceasedContract.DeceasedTable.COLUMN_SYNCED, dc.getSynced());
            values.put(DeceasedContract.DeceasedTable.COLUMN_SYNCED_DATE, dc.getSynced_date());
            values.put(DeceasedContract.DeceasedTable.COLUMN_APPVERSION, dc.getAppversion());

            // Insert the new row, returning the primary key value of the new row
            newRowId = db.insert(
                    DeceasedContract.DeceasedTable.TABLE_NAME,
                    DeceasedContract.DeceasedTable.COLUMN_NAME_NULLABLE,
                    values);
        } else {
            newRowId = db.update(
                    DeceasedContract.DeceasedTable.TABLE_NAME,
                    values,
                    DeceasedContract.DeceasedTable.COLUMN__UID + " = ?",
                    new String[]{MainApp.dc.getUID()}
            );
        }
        return newRowId;
    }


    public Long addRecipient(RecipientsContract rc, int type) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        long newRowId;

        values.put(RecipientsTable.COLUMN_SA8A, rc.getsA8A());

        if (type == 0) {
            values.put(RecipientsTable.COLUMN_PROJECTNAME, rc.getProjectName());
            values.put(RecipientsTable.COLUMN_UID, rc.get_UID());
            values.put(RecipientsTable.COLUMN_UUID, rc.get_UUID());
            values.put(RecipientsTable.COLUMN_FM_UID, rc.getFMUID());
            values.put(RecipientsTable.COLUMN_FORMDATE, rc.getFormDate());
            values.put(RecipientsTable.COLUMN_USER, rc.getUser());
            values.put(RecipientsTable.COLUMN_A8ASNO, rc.getA8aSNo());
            values.put(RecipientsTable.COLUMN_DEVICETAGID, rc.getDevicetagID());
            values.put(RecipientsTable.COLUMN_DEVICEID, rc.getDeviceId());
            values.put(RecipientsTable.COLUMN_SYNCED, rc.getSynced());
            values.put(RecipientsTable.COLUMN_SYNCEDDATE, rc.getSyncedDate());
            values.put(RecipientsTable.COLUMN_APP_VER, rc.getApp_ver());


            // Insert the new row, returning the primary key value of the new row
            newRowId = db.insert(
                    RecipientsTable.TABLE_NAME,
                    RecipientsTable.COLUMN_NAME_NULLABLE,
                    values);
        } else {
            newRowId = db.update(
                    RecipientsTable.TABLE_NAME,
                    values,
                    RecipientsTable.COLUMN_UID + " = ?",
                    new String[]{MainApp.rc.get_UID()}
            );
        }
        return newRowId;
    }

    public Long addChildForm(ChildContract cc, int type) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        if (type == 0) {
            values.put(ChildTable.COLUMN_PROJECTNAME, cc.getProjectName());
            //values.put(ChildTable.COLUMN__ID, cc.get_ID());
            values.put(ChildTable.COLUMN__UID, cc.getUID());
            values.put(ChildTable.COLUMN__UUID, cc.getUUID());
            values.put(ChildTable.COLUMN_FM_UID, cc.getFMUID());
            values.put(ChildTable.COLUMN_MUID, cc.getMUID());
            values.put(ChildTable.COLUMN_FORMDATE, cc.getFormDate());
            values.put(ChildTable.COLUMN_USER, cc.getUser());
            values.put(ChildTable.COLUMN_C1SERIALNO, cc.getC1SerialNo());

            values.put(ChildTable.COLUMN_SC2, cc.getsC2());
            values.put(ChildTable.COLUMN_SC3, cc.getsC3());
            values.put(ChildTable.COLUMN_SC4, cc.getsC4());
            values.put(ChildTable.COLUMN_SC5, cc.getsC5());
            values.put(ChildTable.COLUMN_SC6, cc.getsC6());
            values.put(ChildTable.COLUMN_DEVICEID, cc.getDeviceID());
            values.put(ChildTable.COLUMN_DEVICETAGID, cc.getDevicetagID());
            values.put(ChildTable.COLUMN_SYNCED, cc.getSynced());
            values.put(ChildTable.COLUMN_SYNCED_DATE, cc.getSynced_date());
            values.put(ChildTable.COLUMN_APPVERSION, cc.getAppversion());
            values.put(ChildTable.COLUMN_CSTATUS, cc.getCstatus());
            values.put(ChildTable.COLUMN_CSTATUS88x, cc.getCstatus88x());
        }

        values.put(ChildTable.COLUMN_SC1, cc.getsC1());

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        if (type == 0) {
            newRowId = db.insert(
                    ChildTable.TABLE_NAME,
                    ChildTable.COLUMN_NAME_NULLABLE,
                    values);
        } else {
            newRowId = db.update(
                    ChildTable.TABLE_NAME,
                    values,
                    ChildTable.COLUMN__UID + " = ?",
                    new String[]{cc.getUID()}
            );
        }
        return newRowId;
    }


    public Long addEligibleMember(AnthrosMembersContract ec) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(AnthrosMembers.COLUMN_PROJECTNAME, ec.getProjectName());
        //values.put(AnthrosMembers.COLUMN__ID, ec.get_ID());
        values.put(AnthrosMembers.COLUMN_UID, ec.get_UID());
        values.put(AnthrosMembers.COLUMN_UUID, ec.get_UUID());
        values.put(AnthrosMembers.COLUMN_FM_UID, ec.getFmuid());
        values.put(AnthrosMembers.COLUMN_FORMDATE, ec.getFormDate());
        values.put(AnthrosMembers.COLUMN_DEVICEID, ec.getDeviceId());
        values.put(AnthrosMembers.COLUMN_DEVICETAGID, ec.getDevicetagID());
        values.put(AnthrosMembers.COLUMN_USER, ec.getUser());
        values.put(AnthrosMembers.COLUMN_APPVERSION, ec.getApp_ver());
        values.put(AnthrosMembers.COLUMN_ENM_NO, ec.getEnm_no());
        values.put(AnthrosMembers.COLUMN_HH_NO, ec.getHh_no());
        values.put(AnthrosMembers.COLUMN_DOB, ec.getDob());
        values.put(AnthrosMembers.COLUMN_AGE, ec.getAge());
        values.put(AnthrosMembers.COLUMN_na204, ec.getna204());
        values.put(AnthrosMembers.COLUMN_SA3, ec.getsA3());
        values.put(AnthrosMembers.COLUMN_ISTATUS, ec.getIstatus());
        values.put(AnthrosMembers.COLUMN_ISTATUS88x, ec.getIstatus88x());
        values.put(AnthrosMembers.COLUMN_SYNCED, ec.getSynced());
        values.put(AnthrosMembers.COLUMN_SYNCEDDATE, ec.getSyncedDate());
        values.put(AnthrosMembers.COLUMN_END_TIME, ec.getEnd_time());


        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                AnthrosMembers.TABLE_NAME,
                AnthrosMembers.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
    }


    public Long addMWRA(MWRAContract mc, int type) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        if (type == 0) {
            values.put(MWRATable.COLUMN_PROJECTNAME, mc.getProjectName());
            //values.put(MWRATable.COLUMN__ID, mc.get_ID());
            values.put(MWRATable.COLUMN_UID, mc.get_UID());
            values.put(MWRATable.COLUMN_UUID, mc.get_UUID());
            values.put(MWRATable.COLUMN_FM_UID, mc.getFMUID());
            values.put(MWRATable.COLUMN_FORMDATE, mc.getFormDate());
            values.put(MWRATable.COLUMN_DEVICEID, mc.getDeviceId());
            values.put(MWRATable.COLUMN_DEVICETAGID, mc.getDevicetagID());
            values.put(MWRATable.COLUMN_USER, mc.getUser());
            values.put(MWRATable.COLUMN_APP_VER, mc.getApp_ver());
            values.put(MWRATable.COLUMN_B1SERIALNO, mc.getB1SerialNo());

            values.put(MWRATable.COLUMN_SB2, mc.getsB2());
            values.put(MWRATable.COLUMN_SB3, mc.getsB3());
            values.put(MWRATable.COLUMN_SB4, mc.getsB4());
            values.put(MWRATable.COLUMN_SB5, mc.getsB5());
            values.put(MWRATable.COLUMN_SB6, mc.getsB6());
            values.put(MWRATable.COLUMN_SB7, mc.getsB6());
            values.put(MWRATable.COLUMN_SB8, mc.getsB6());
            values.put(MWRATable.COLUMN_SB9, mc.getsB6());
            values.put(MWRATable.COLUMN_SB10, mc.getsB6());
            values.put(MWRATable.COLUMN_SB11, mc.getsB6());
            values.put(MWRATable.COLUMN_SB2FLAG, mc.getSb2flag());
            values.put(MWRATable.COLUMN_SYNCED, mc.getSynced());
            values.put(MWRATable.COLUMN_SYNCEDDATE, mc.getSyncedDate());
            values.put(MWRATable.COLUMN_MSTATUS, mc.getMstatus());
            values.put(MWRATable.COLUMN_MSTATUS88x, mc.getMstatus88x());
        }

        values.put(MWRATable.COLUMN_SB1, mc.getsB1());
//        values.put(MWRATable.COLUMN_UPDATEDATE, mc.getUpdatedate());


        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        if (type == 0) {
            newRowId = db.insert(
                    MWRATable.TABLE_NAME,
                    MWRATable.COLUMN_NAME_NULLABLE,
                    values);

        } else {
            newRowId = db.update(
                    MWRATable.TABLE_NAME,
                    values,
                    MWRATable.COLUMN_UID + " = ?",
                    new String[]{MainApp.mc.get_UID()}
            );
        }


        return newRowId;
    }


    public Long addNutrition(NutritionContract mc, int type) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(NutritionTable.COLUMN_SB6, mc.getsB6());

        if (type == 0) {
            values.put(NutritionTable.COLUMN_PROJECTNAME, mc.getProjectName());
            //values.put(MWRATable.COLUMN__ID, mc.get_ID());
            values.put(NutritionTable.COLUMN_UID, mc.get_UID());
            values.put(NutritionTable.COLUMN_UUID, mc.get_UUID());
            values.put(NutritionTable.COLUMN_FM_UID, mc.getFMUID());
            values.put(NutritionTable.COLUMN_MUID, mc.getMUID());
            values.put(NutritionTable.COLUMN_FORMDATE, mc.getFormDate());
            values.put(NutritionTable.COLUMN_DEVICEID, mc.getDeviceId());
            values.put(NutritionTable.COLUMN_DEVICETAGID, mc.getDevicetagID());
            values.put(NutritionTable.COLUMN_USER, mc.getUser());
            values.put(NutritionTable.COLUMN_APP_VER, mc.getApp_ver());
            values.put(NutritionTable.COLUMN_SYNCED, mc.getSynced());
            values.put(NutritionTable.COLUMN_SYNCEDDATE, mc.getSyncedDate());
        } else {
            values.put(NutritionTable.COLUMN_UPDATEDATE, mc.getUpdatedate());
        }
        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        if (type == 0) {
            newRowId = db.insert(
                    NutritionTable.TABLE_NAME,
                    NutritionTable.COLUMN_NAME_NULLABLE,
                    values);
        } else {
            newRowId = db.update(
                    NutritionTable.TABLE_NAME,
                    values,
                    NutritionTable.COLUMN_UID + " = ?",
                    new String[]{mc.get_UID()}
            );
        }

        return newRowId;
    }

    public Long addOutcome(OutcomeContract oc, int type) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        if (type == 0) {
            values.put(outcomeTable.COLUMN_PROJECTNAME, oc.getProjectName());
            //values.put(outcomeTable.COLUMN__ID, oc.get_ID());
            values.put(outcomeTable.COLUMN_UID, oc.get_UID());
            values.put(outcomeTable.COLUMN_UUID, oc.get_UUID());
            values.put(outcomeTable.COLUMN_FM_UID, oc.getFMUID());
            values.put(outcomeTable.COLUMN_MUID, oc.getMUID());
            values.put(outcomeTable.COLUMN_FORMDATE, oc.getFormDate());
            values.put(outcomeTable.COLUMN_DEVICEID, oc.getDeviceId());
            values.put(outcomeTable.COLUMN_DEVICETAGID, oc.getDevicetagID());
            values.put(outcomeTable.COLUMN_USER, oc.getUser());
            values.put(outcomeTable.COLUMN_APP_VER, oc.getApp_ver());
            values.put(outcomeTable.COLUMN_B1APregSNO, oc.getB1aPregSNo());
            values.put(outcomeTable.COLUMN_SB1A, oc.getsB1A());
            values.put(outcomeTable.COLUMN_SYNCED, oc.getSynced());
            values.put(outcomeTable.COLUMN_SYNCEDDATE, oc.getSyncedDate());
        } else {
            values.put(outcomeTable.COLUMN_SB1A, oc.getsB1A());
            values.put(outcomeTable.COLUMN_UPDATEDATE, oc.getUpdatedate());
            values.put(outcomeTable.COLUMN_SYNCED, oc.getSynced());
            values.put(outcomeTable.COLUMN_SYNCEDDATE, oc.getSyncedDate());
        }

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        if (type == 0) {
            newRowId = db.insert(
                    outcomeTable.TABLE_NAME,
                    outcomeTable.COLUMN_NAME_NULLABLE,
                    values);
        } else {
            newRowId = db.update(
                    outcomeTable.TABLE_NAME,
                    values,
                    outcomeTable.COLUMN_UID + " = ?",
                    new String[]{oc.get_UID()}
            );
        }

        return newRowId;
    }

    public Long addSerialForm(SerialContract sc) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(singleSerial.COLUMN_DEVICE_ID, sc.getDeviceid());
        values.put(singleSerial.COLUMN_DATE, sc.getdt());
        values.put(singleSerial.COLUMN_SERIAL_NO, sc.getSerialno());

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                singleSerial.TABLE_NAME,
                singleSerial.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
    }

    public int updateSerialWRTDate(String date, String serial) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(singleSerial.COLUMN_SERIAL_NO, serial);

        // Which row to update, based on the title
        String where = singleSerial.COLUMN_DATE + " = ?";
        String[] whereArgs = {date};

        int count = db.update(
                singleSerial.TABLE_NAME,
                values,
                where,
                whereArgs);

        return count;
    }


    public void updateSyncedForms(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SYNCED, true);
        values.put(FormsTable.COLUMN_SYNCED_DATE, new Date().toString());

// Which row to update, based on the title
        String where = FormsTable._ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                FormsTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }

    public void updateDAdoleSyncedForms(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(D6AdolesTable.COLUMN_SYNCED, true);
        values.put(D6AdolesTable.COLUMN_SYNCEDDATE, new Date().toString());

// Which row to update, based on the title
        String where = D6AdolesTable._ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                D6AdolesTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }

    public void updateDWRASyncedForm(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(D4WRATable.COLUMN_SYNCED, true);
        values.put(D4WRATable.COLUMN_SYNCEDDATE, new Date().toString());

// Which row to update, based on the title
        String where = D4WRATable._ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                D4WRATable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }


    public void updateSyncedChildForm(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(ChildTable.COLUMN_SYNCED, true);
        values.put(ChildTable.COLUMN_SYNCED_DATE, new Date().toString());

// Which row to update, based on the title
        String where = ChildTable.COLUMN__ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                ChildTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }

    public void updateSyncedMicroForm(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(MicroTable.COLUMN_SYNCED, true);
        values.put(MicroTable.COLUMN_SYNCED_DATE, new Date().toString());

// Which row to update, based on the title
        String where = MicroTable.COLUMN__ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                MicroTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }

    public void updateSyncedMWRAForm(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(MWRATable.COLUMN_SYNCED, true);
        values.put(MWRATable.COLUMN_SYNCEDDATE, new Date().toString());

// Which row to update, based on the title
        String where = MWRATable.COLUMN__ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                MWRATable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }

    public void updateTagID(String tagID) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(DeviceContract.DeviceTable.COLUMN_TAGID, tagID);

// Which row to update, based on the title
        String where = DeviceContract.DeviceTable.COLUMN__ID + " = 1";
        String[] whereArgs = null;

        int count = db.update(
                DeviceContract.DeviceTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }

    public void updateSyncedSpecimen(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(SpecimenTable.COLUMN_SYNCED, true);
        values.put(SpecimenTable.COLUMN_SYNCED_DATE, new Date().toString());

// Which row to update, based on the title
        String where = SpecimenTable.COLUMN__ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                SpecimenTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }

    public void updateSyncedWaterSpecimen(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(WaterSpecimenTable.COLUMN_SYNCED, true);
        values.put(WaterSpecimenTable.COLUMN_SYNCED_DATE, new Date().toString());

// Which row to update, based on the title
        String where = WaterSpecimenTable.COLUMN__ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                WaterSpecimenTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }

    public void updateSyncedOutcomeForm(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(outcomeTable.COLUMN_SYNCED, true);
        values.put(outcomeTable.COLUMN_SYNCEDDATE, new Date().toString());

// Which row to update, based on the title
        String where = outcomeTable.COLUMN__ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                outcomeTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }


    public void updateSyncedRecepientsForm(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(RecipientsTable.COLUMN_SYNCED, true);
        values.put(RecipientsTable.COLUMN_SYNCEDDATE, new Date().toString());

// Which row to update, based on the title
        String where = RecipientsTable.COLUMN__ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                RecipientsTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }

    public void updateSyncedSummaryForm(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(singleSum.COLUMN_SYNCED, true);
        values.put(singleSum.COLUMN_SYNCEDDATE, new Date().toString());

// Which row to update, based on the title
        String where = singleSum.COLUMN_ROW_ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                singleSum.TABLE_NAME,
                values,
                where,
                whereArgs);
    }

    public void updateSyncedDeceasedForm(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(DeceasedContract.DeceasedTable.COLUMN_SYNCED, true);
        values.put(DeceasedContract.DeceasedTable.COLUMN_SYNCED_DATE, new Date().toString());

// Which row to update, based on the title
        String where = DeceasedContract.DeceasedTable.COLUMN__ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                DeceasedContract.DeceasedTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }

    public void updateSyncedFamilyMembers(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(familyMembers.COLUMN_SYNCED, true);
        values.put(familyMembers.COLUMN_SYNCED_DATE, new Date().toString());

// Which row to update, based on the title
        String where = familyMembers.COLUMN_ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                familyMembers.TABLE_NAME,
                values,
                where,
                whereArgs);
    }

    public void updateSyncedAnthros(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(AnthrosMembers.COLUMN_SYNCED, true);
        values.put(AnthrosMembers.COLUMN_SYNCEDDATE, new Date().toString());

// Which row to update, based on the title
        String where = AnthrosMembers.COLUMN__ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                AnthrosMembers.TABLE_NAME,
                values,
                where,
                whereArgs);
    }

    public void updateSyncedNutrition(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(NutritionTable.COLUMN_SYNCED, true);
        values.put(NutritionTable.COLUMN_SYNCEDDATE, new Date().toString());

// Which row to update, based on the title
        String where = NutritionTable.COLUMN__ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                NutritionTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }


    public void updateSyncedSerial(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(singleSerial.COLUMN_SYNCED, true);
        values.put(singleSerial.COLUMN_SYNCED_DATE, new Date().toString());

// Which row to update, based on the title
        String where = singleSerial._ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                singleSerial.TABLE_NAME,
                values,
                where,
                whereArgs);
    }

    public void updateSyncedSignup(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(SignUpTable.COLUMN_SYNCED, true);
        values.put(SignUpTable.COLUMN_SYNCED_DATE, new Date().toString());

// Which row to update, based on the title
        String where = SignUpTable._ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                SignUpTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }


    public int updateFormID() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_UID, MainApp.fc.getUID());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateFamilyMemberID() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(familyMembers.COLUMN_UID, MainApp.fmc.get_UID());

// Which row to update, based on the ID
        String selection = familyMembers.COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.fmc.get_ID())};

        int count = db.update(familyMembers.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateDWraID() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(D4WRATable.COLUMN_UID, MainApp.d4WRAc.get_UID());

// Which row to update, based on the ID
        String selection = D4WRATable.COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.d4WRAc.get_ID())};

        int count = db.update(D4WRATable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateD6AdolesID() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(D6AdolesTable.COLUMN_UID, MainApp.d6Adolesc.get_UID());

// Which row to update, based on the ID
        String selection = D6AdolesTable.COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.d6Adolesc.get_ID())};

        int count = db.update(D6AdolesTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateFamilyMemberFLAG(String flag, String fmUID) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(familyMembers.COLUMN_FLAG, flag);

// Which row to update, based on the ID
        String selection = familyMembers.COLUMN_UID + " = ?";
        String[] selectionArgs = {fmUID};

        int count = db.update(familyMembers.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateFamilyMemberKishFlag(String uuid, String fmUID, String kishField) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(kishField, "1");

// Which row to update, based on the ID
        String selection = familyMembers.COLUMN_UUID + " = ? AND " + familyMembers.COLUMN_UID + " = ?";
        String[] selectionArgs = {uuid, fmUID};

        int count = db.update(familyMembers.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateFamilyMemberMWRAKishFlag(String uuid, String fmUID) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(familyMembers.COLUMN_KISH_SELECTED_MWRA_D, "1");

// Which row to update, based on the ID
        String selection = familyMembers.COLUMN_UUID + " = ? AND " + familyMembers.COLUMN_UID + " = ?";
        String[] selectionArgs = {uuid, fmUID};

        int count = db.update(familyMembers.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateFamilyMemberAdoleKishFlag(String uuid, String fmUID) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(familyMembers.COLUMN_KISH_SELECTED_ADOLESCENT, "1");

// Which row to update, based on the ID
        String selection = familyMembers.COLUMN_UUID + " = ? AND " + familyMembers.COLUMN_UID + " = ?";
        String[] selectionArgs = {uuid, fmUID};

        int count = db.update(familyMembers.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateDeceasedMemberID() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(DeceasedContract.DeceasedTable.COLUMN__UID, MainApp.dc.getUID());

// Which row to update, based on the ID
        String selection = DeceasedContract.DeceasedTable.COLUMN__ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.dc.get_ID())};

        int count = db.update(DeceasedContract.DeceasedTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateFamilyMember(FamilyMembersContract fmc) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(familyMembers.COLUMN_SA2, fmc.getsA2());
        values.put(familyMembers.COLUMN_AV, fmc.getAv());

// Which row to update, based on the ID
        String selection = familyMembers.COLUMN_UID + " = ?";
        String[] selectionArgs = {fmc.get_UID()};

        int count = db.update(familyMembers.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }


    public int updateMWRAID() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(MWRATable.COLUMN_UID, MainApp.mc.get_UID());

// Which row to update, based on the ID
        String selection = MWRATable.COLUMN__ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.mc.get_ID())};

        int count = db.update(MWRATable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateSummaryID() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(singleSum.COLUMN__UID, MainApp.sumc.get_uid());

// Which row to update, based on the ID
        String selection = singleSum.COLUMN_ROW_ID + " =?";
        String[] selectionArgs = {String.valueOf(MainApp.sumc.getROW_ID())};

        int count = db.update(singleSum.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateNutritionID() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(NutritionTable.COLUMN_UID, MainApp.nc.get_UID());

// Which row to update, based on the ID
        String selection = NutritionTable.COLUMN__ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.nc.get_ID())};

        int count = db.update(NutritionTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateWRAB6() {
        SQLiteDatabase db = this.getReadableDatabase();
//      New value for one column
        ContentValues values = new ContentValues();
        values.put(MWRATable.COLUMN_SB6, MainApp.mc.getsB6());

//      Which row to update, based on the ID
        String selection = MWRATable.COLUMN_UID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.mc.get_UID())};

        int count = db.update(MWRATable.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        return count;
    }

    public int updateOutcomeID() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(outcomeTable.COLUMN_UID, MainApp.oc.get_UID());

// Which row to update, based on the ID
        String selection = outcomeTable.COLUMN__ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.oc.get_ID())};

        int count = db.update(outcomeTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateRecepientID() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(RecipientsTable.COLUMN_UID, MainApp.rc.get_UID());

// Which row to update, based on the ID
        String selection = RecipientsTable.COLUMN__ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.rc.get_ID())};

        int count = db.update(RecipientsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateFormChildID() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(ChildTable.COLUMN__UID, MainApp.cc.getUID());

// Which row to update, based on the ID
        String selection = ChildTable.COLUMN__ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.cc.get_ID())};

        int count = db.update(ChildTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }


    public int updateEligibleMemberID() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(AnthrosMembers.COLUMN_UID, MainApp.emc.get_UID());

// Which row to update, based on the ID
        String selection = AnthrosMembers.COLUMN__ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.emc.get_ID())};

        int count = db.update(AnthrosMembers.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public Collection<FormsContract> getUnsyncedForms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_USER,
                FormsTable.COLUMN_RESP_LNO,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_ISTATUS88x,
                FormsTable.COLUMN_ISTATUSHH,
                FormsTable.COLUMN_GPSELEV,
                FormsTable.COLUMN_HH_NO,
                FormsTable.COLUMN_CLUSTER_NO,
                FormsTable.COLUMN_SA1,
                FormsTable.COLUMN_SA4,
                FormsTable.COLUMN_SA402,
                FormsTable.COLUMN_SA5,
                FormsTable.COLUMN_SA7,
                FormsTable.COLUMN_END_TIME,
                FormsTable.COLUMN_COUNT,
                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDATE,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_SYNCED,
                FormsTable.COLUMN_SYNCED_DATE,
                FormsTable.COLUMN_APP_VERSION
        };
        String whereClause = FormsTable.COLUMN_SYNCED + " is null OR " + FormsTable.COLUMN_SYNCED + " = '' ";
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable._ID + " ASC";

        Collection<FormsContract> allFC = new ArrayList<FormsContract>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormsContract fc = new FormsContract();
                allFC.add(fc.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public Collection<SummaryContract> getUnsyncedSummary() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                singleSum.COLUMN_ROW_ID,
                singleSum.COLUMN__UID,
                singleSum.COLUMN_CLUSTERNO,
                singleSum.COLUMN_HHNO,
                singleSum.COLUMN_HH,
                singleSum.COLUMN_WOMEN,
                singleSum.COLUMN_CHILD,
                singleSum.COLUMN_ANTHRO,
                singleSum.COLUMN_SPECIMEN,
                singleSum.COLUMN_WATER,
                singleSum.COLUMN_SYNCED,
                singleSum.COLUMN_SYNCEDDATE,
                singleSum.COLUMN_USER,
                singleSum.COLUMN_FORMDATE,
                singleSum.COLUMN_DEVICEID,
                singleSum.COLUMN_DEVICETAGID,
                singleSum.COLUMN_APPVERSION
        };
        String whereClause = singleSum.COLUMN_SYNCED + " is null OR " + singleSum.COLUMN_SYNCED + " = '' ";
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                singleSum.COLUMN_ROW_ID + " ASC";

        Collection<SummaryContract> allFC = new ArrayList<>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                SummaryContract sum = new SummaryContract();
                allFC.add(sum.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public JSONArray getFormsByCluster(String cluster) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_USER,
                FormsTable.COLUMN_RESP_LNO,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_ISTATUS88x,
                FormsTable.COLUMN_ISTATUSHH,
                FormsTable.COLUMN_GPSELEV,
                FormsTable.COLUMN_HH_NO,
                FormsTable.COLUMN_CLUSTER_NO,
                FormsTable.COLUMN_SA1,
                FormsTable.COLUMN_SA4,
                FormsTable.COLUMN_SA402,
                FormsTable.COLUMN_SA5,
                FormsTable.COLUMN_END_TIME,
                FormsTable.COLUMN_COUNT,
                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDATE,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_SYNCED,
                FormsTable.COLUMN_SYNCED_DATE,
                FormsTable.COLUMN_APP_VERSION
        };
        String whereClause = FormsTable.COLUMN_CLUSTER_NO + "= ? and " + FormsTable.COLUMN_ISTATUS + " = ?";
        String[] whereArgs = new String[]{cluster, "1"};
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable._ID + " ASC";
        JSONArray jsonArray = new JSONArray();
        Collection<FormsContract> allFC = new ArrayList<FormsContract>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormsContract fc = new FormsContract();
                allFC.add(fc.Hydrate(c));
            }
            for (FormsContract fc : allFC) {
                //if (fc.getIstatus().equals("1")) {
                try {
                    jsonArray.put(fc.toJSONObject());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //}
            }

        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return jsonArray;
    }

    public FormsContract getAutoPopulateFormForWRA(String uuid) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_USER,
                FormsTable.COLUMN_RESP_LNO,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_ISTATUS88x,
                FormsTable.COLUMN_ISTATUSHH,
                FormsTable.COLUMN_GPSELEV,
                FormsTable.COLUMN_HH_NO,
                FormsTable.COLUMN_CLUSTER_NO,
                FormsTable.COLUMN_SA1,
                FormsTable.COLUMN_SA4,
                FormsTable.COLUMN_SA402,
                FormsTable.COLUMN_SA5,
                FormsTable.COLUMN_SA7,
                FormsTable.COLUMN_END_TIME,
                FormsTable.COLUMN_COUNT,
                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDATE,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_SYNCED,
                FormsTable.COLUMN_SYNCED_DATE,
                FormsTable.COLUMN_APP_VERSION
        };
        String whereClause = FormsTable.COLUMN_UID + " =?";
        String[] whereArgs = {uuid};
        String groupBy = null;
        String having = null;

        String orderBy = null;

        FormsContract allFC = null;
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allFC = new FormsContract().Hydrate(c);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public FormsContract getPressedForms(String cluster, String hhno) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_USER,
                FormsTable.COLUMN_RESP_LNO,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_ISTATUS88x,
                FormsTable.COLUMN_ISTATUSHH,
                FormsTable.COLUMN_GPSELEV,
                FormsTable.COLUMN_HH_NO,
                FormsTable.COLUMN_CLUSTER_NO,
                FormsTable.COLUMN_SA1,
                FormsTable.COLUMN_SA4,
                FormsTable.COLUMN_SA402,
                FormsTable.COLUMN_SA5,
                FormsTable.COLUMN_SA7,
                FormsTable.COLUMN_END_TIME,
                FormsTable.COLUMN_COUNT,
                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDATE,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_SYNCED,
                FormsTable.COLUMN_SYNCED_DATE,
                FormsTable.COLUMN_APP_VERSION
        };
        String whereClause = FormsTable.COLUMN_CLUSTER_NO + " =? AND " + FormsTable.COLUMN_HH_NO + " =? AND ("
                + FormsTable.COLUMN_ISTATUS + " =? OR " + FormsTable.COLUMN_ISTATUS + " =? OR " + FormsTable.COLUMN_ISTATUS + " =?)";
        String[] whereArgs = new String[]{cluster, hhno, "1", "7", ""};
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable._ID + " DESC LIMIT 1";

        FormsContract allFC = null;
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allFC = new FormsContract().Hydrate(c);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }


    public Collection<FamilyMembersContract> getUnsyncedFamilyMembers() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                familyMembers.COLUMN_ID,
                familyMembers.COLUMN_UID,
                familyMembers.COLUMN_UUID,
                familyMembers.COLUMN_FORMDATE,
                familyMembers.COLUMN_USER,
                //FormsTable.COLUMN_GPSELEV,
                familyMembers.COLUMN_HH_NO,
                familyMembers.COLUMN_ENM_NO,
                familyMembers.COLUMN_SA2,
                familyMembers.COLUMN_DEVICETAGID,
                familyMembers.COLUMN_DEVICEID,
                familyMembers.COLUMN_AV,
                familyMembers.COLUMN_SYNCED,
                familyMembers.COLUMN_SYNCED_DATE,
                familyMembers.COLUMN_APP_VERSION,
                familyMembers.COLUMN_FLAG,
                familyMembers.COLUMN_KISH_SELECTED,
                familyMembers.COLUMN_KISH_SELECTED_MWRA_D,
                familyMembers.COLUMN_KISH_SELECTED_ADOLESCENT,
        };
        String whereClause = familyMembers.COLUMN_SYNCED + " is null OR " + familyMembers.COLUMN_SYNCED + " = '' ";
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                familyMembers.COLUMN_ID + " ASC";

        Collection<FamilyMembersContract> allFC = new ArrayList<FamilyMembersContract>();
        try {
            c = db.query(
                    familyMembers.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FamilyMembersContract fc = new FamilyMembersContract();
                allFC.add(fc.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public JSONArray getAnthroFamilyMembers() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                familyMembers.COLUMN_ID,
                familyMembers.COLUMN_UID,
                familyMembers.COLUMN_UUID,
                familyMembers.COLUMN_FORMDATE,
                familyMembers.COLUMN_USER,
                //FormsTable.COLUMN_GPSELEV,
                familyMembers.COLUMN_HH_NO,
                familyMembers.COLUMN_ENM_NO,
                familyMembers.COLUMN_FLAG,
                familyMembers.COLUMN_KISH_SELECTED,
                familyMembers.COLUMN_KISH_SELECTED_MWRA_D,
                familyMembers.COLUMN_KISH_SELECTED_ADOLESCENT,
                familyMembers.COLUMN_SA2,
                familyMembers.COLUMN_DEVICETAGID,
                familyMembers.COLUMN_DEVICEID,
                familyMembers.COLUMN_AV,
                familyMembers.COLUMN_SYNCED,
                familyMembers.COLUMN_SYNCED_DATE,
                familyMembers.COLUMN_APP_VERSION
        };

        /*String selection = ChildTable.COLUMN__ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.cc.get_ID())};*/

        String whereClause = familyMembers.COLUMN_UUID + " = ?  and " + familyMembers.COLUMN_AV + " = ?";
        String[] whereArgs = {String.valueOf(MainApp.fc.getUID()), "1"};
        String groupBy = null;
        String having = null;

        String orderBy =
                familyMembers._ID + " ASC";

        Collection<FamilyMembersContract> allFC = new ArrayList<FamilyMembersContract>();
        JSONArray jsonArray = new JSONArray();
        try {
            c = db.query(
                    familyMembers.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FamilyMembersContract fc = new FamilyMembersContract();
                allFC.add(fc.Hydrate(c));
            }
            for (FamilyMembersContract fc : allFC) {
                //if (fc.getIstatus().equals("1")) {
                jsonArray.put(fc.toJSONObject());
                //}
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return jsonArray;
    }


    public Collection<DeceasedContract> getUnsyncedDeceasedMembers() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                DeceasedContract.DeceasedTable.COLUMN__ID,
                DeceasedContract.DeceasedTable.COLUMN__UID,
                DeceasedContract.DeceasedTable.COLUMN__UUID,
                DeceasedContract.DeceasedTable.COLUMN_FORMDATE,
                DeceasedContract.DeceasedTable.COLUMN_USER,
                //FormsTable.COLUMN_GPSELEV,
                DeceasedContract.DeceasedTable.COLUMN_SH8,
                DeceasedContract.DeceasedTable.COLUMN_DEVICETAGID,
                DeceasedContract.DeceasedTable.COLUMN_DEVICEID,
                DeceasedContract.DeceasedTable.COLUMN_SYNCED,
                DeceasedContract.DeceasedTable.COLUMN_SYNCED_DATE,
                DeceasedContract.DeceasedTable.COLUMN_APPVERSION
        };
        String whereClause = DeceasedContract.DeceasedTable.COLUMN_SYNCED + " is null OR " + DeceasedContract.DeceasedTable.COLUMN_SYNCED + " = '' ";
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                DeceasedContract.DeceasedTable.COLUMN__ID + " ASC";

        Collection<DeceasedContract> allFC = new ArrayList<DeceasedContract>();
        try {
            c = db.query(
                    DeceasedContract.DeceasedTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                DeceasedContract fc = new DeceasedContract();
                allFC.add(fc.Hydrate(c, 0));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public Collection<DeceasedContract> getDeceasedMembersCount(String uuid) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                DeceasedContract.DeceasedTable.COLUMN_SH8
        };
        String whereClause = DeceasedContract.DeceasedTable.COLUMN__UUID + " =?";
        String[] whereArgs = {uuid};
        String groupBy = null;
        String having = null;

        String orderBy =
                DeceasedContract.DeceasedTable.COLUMN__ID + " ASC";

        Collection<DeceasedContract> allFC = new ArrayList<DeceasedContract>();
        try {
            c = db.query(
                    DeceasedContract.DeceasedTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                DeceasedContract fc = new DeceasedContract();
                allFC.add(fc.Hydrate(c, 1));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public Collection<DeceasedContract> getPressedDeceasedMembers() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                DeceasedContract.DeceasedTable.COLUMN__ID,
                DeceasedContract.DeceasedTable.COLUMN__UID,
                DeceasedContract.DeceasedTable.COLUMN__UUID,
                DeceasedContract.DeceasedTable.COLUMN_FORMDATE,
                DeceasedContract.DeceasedTable.COLUMN_USER,
                DeceasedContract.DeceasedTable.COLUMN_SH8,
                DeceasedContract.DeceasedTable.COLUMN_DEVICETAGID,
                DeceasedContract.DeceasedTable.COLUMN_DEVICEID,
                DeceasedContract.DeceasedTable.COLUMN_SYNCED,
                DeceasedContract.DeceasedTable.COLUMN_SYNCED_DATE,
                DeceasedContract.DeceasedTable.COLUMN_APPVERSION
        };
        String whereClause = DeceasedContract.DeceasedTable.COLUMN__UUID + " =?";
        String[] whereArgs = {MainApp.fc.getUID()};
        String groupBy = null;
        String having = null;

        String orderBy =
                DeceasedContract.DeceasedTable.COLUMN__ID + " ASC";

        Collection<DeceasedContract> allFC = new ArrayList<DeceasedContract>();
        try {
            c = db.query(
                    DeceasedContract.DeceasedTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                DeceasedContract fc = new DeceasedContract();
                allFC.add(fc.Hydrate(c, 0));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }


    public Collection<ChildContract> getUnsyncedChildForms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                ChildTable.COLUMN__ID,
                ChildTable.COLUMN__UID,
                ChildTable.COLUMN__UUID,
                ChildTable.COLUMN_FM_UID,
                ChildTable.COLUMN_MUID,
                ChildTable.COLUMN_FORMDATE,
                ChildTable.COLUMN_USER,
                ChildTable.COLUMN_C1SERIALNO,
                ChildTable.COLUMN_SC1,
                ChildTable.COLUMN_SC2,
                ChildTable.COLUMN_SC3,
                ChildTable.COLUMN_SC4,
                ChildTable.COLUMN_SC5,
                ChildTable.COLUMN_SC6,
                ChildTable.COLUMN_DEVICEID,
                ChildTable.COLUMN_DEVICETAGID,
                ChildTable.COLUMN_SYNCED,
                ChildTable.COLUMN_SYNCED_DATE,
                ChildTable.COLUMN_APPVERSION,
                ChildTable.COLUMN_CSTATUS,
                ChildTable.COLUMN_CSTATUS88x,

        };
        String whereClause = ChildContract.ChildTable.COLUMN_SYNCED + " is null OR " + ChildTable.COLUMN_SYNCED + " = '' ";
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                ChildTable.COLUMN__ID + " ASC";

        Collection<ChildContract> allFC = new ArrayList<ChildContract>();
        try {
            c = db.query(
                    ChildTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                ChildContract fc = new ChildContract();
                allFC.add(fc.Hydrate(c, 0));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public Collection<SpecimenContract> getUnsyncedSpecimenForms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                SpecimenTable.COLUMN__ID,
                SpecimenTable.COLUMN__UID,
                SpecimenTable.COLUMN__UUID,
                SpecimenTable.COLUMN_FM_UID,
                SpecimenTable.COLUMN_FORMDATE,
                SpecimenTable.COLUMN_USER,
                SpecimenTable.COLUMN_LINENO,
                SpecimenTable.COLUMN_HH,
                SpecimenTable.COLUMN_CLUSTER,
                SpecimenTable.COLUMN_SE1,
                SpecimenTable.COLUMN_DEVICEID,
                SpecimenTable.COLUMN_DEVICETAGID,
                SpecimenTable.COLUMN_SYNCED,
                SpecimenTable.COLUMN_SYNCED_DATE,
                SpecimenTable.COLUMN_APPVERSION,


        };
        String whereClause = SpecimenTable.COLUMN_SYNCED + " is null OR " + SpecimenTable.COLUMN_SYNCED + " = '' ";
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                SpecimenTable.COLUMN__ID + " ASC";

        Collection<SpecimenContract> allFC = new ArrayList<SpecimenContract>();
        try {
            c = db.query(
                    SpecimenTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                SpecimenContract fc = new SpecimenContract();
                allFC.add(fc.Hydrate(c, 0));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public Collection<WaterSpecimenContract> getUnsyncedWaterSpecimenForms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                WaterSpecimenTable.COLUMN__ID,
                WaterSpecimenTable.COLUMN__UID,
                WaterSpecimenTable.COLUMN__UUID,
                //MicroTable.COLUMN_FM_UID,
                WaterSpecimenTable.COLUMN_FORMDATE,
                WaterSpecimenTable.COLUMN_USER,
                //MicroTable.COLUMN_WUID,
                WaterSpecimenTable.COLUMN_HH,
                WaterSpecimenTable.COLUMN_CLUSTER,
                WaterSpecimenTable.COLUMN_SE2,
                WaterSpecimenTable.COLUMN_DEVICEID,
                WaterSpecimenTable.COLUMN_DEVICETAGID,
                WaterSpecimenTable.COLUMN_SYNCED,
                WaterSpecimenTable.COLUMN_SYNCED_DATE,
                WaterSpecimenTable.COLUMN_APPVERSION,


        };
        String whereClause = WaterSpecimenTable.COLUMN_SYNCED + " is null OR " + WaterSpecimenTable.COLUMN_SYNCED + " = '' ";
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                WaterSpecimenTable.COLUMN__ID + " ASC";

        Collection<WaterSpecimenContract> allFC = new ArrayList<WaterSpecimenContract>();
        try {
            c = db.query(
                    WaterSpecimenTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                WaterSpecimenContract fc = new WaterSpecimenContract();
                allFC.add(fc.Hydrate(c, 0));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public Collection<MicroContract> getUnsyncedMicroForms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                MicroTable.COLUMN__ID,
                MicroTable.COLUMN__UID,
                MicroTable.COLUMN__UUID,
                MicroTable.COLUMN_WUID,
                MicroTable.COLUMN_FORMDATE,
                MicroTable.COLUMN_USER,
                MicroTable.COLUMN_HH,
                MicroTable.COLUMN_WUID,
                MicroTable.COLUMN_CLUSTER,
                MicroTable.COLUMN_SM,
                MicroTable.COLUMN_DEVICEID,
                MicroTable.COLUMN_DEVICETAGID,
                MicroTable.COLUMN_SYNCED,
                MicroTable.COLUMN_SYNCED_DATE,
                MicroTable.COLUMN_APPVERSION,


        };
        String whereClause = MicroTable.COLUMN_SYNCED + " is null OR "
                + MicroTable.COLUMN_SYNCED + " = '' ";
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                MicroTable.COLUMN__ID + " ASC";

        Collection<MicroContract> allFC = new ArrayList<MicroContract>();
        try {
            c = db.query(
                    MicroTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                MicroContract fc = new MicroContract();
                allFC.add(fc.Hydrate(c, 0));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public Boolean getChildExistanceByUid(String uuid, String fuid) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                ChildTable.COLUMN__ID,
                ChildTable.COLUMN__UID,
                ChildTable.COLUMN__UUID,
                ChildTable.COLUMN_FM_UID
        };
        String whereClause = ChildTable.COLUMN__UUID + " =? AND " + ChildTable.COLUMN_FM_UID + " =?";
        String[] whereArgs = {uuid, fuid};
        String groupBy = null;
        String having = null;

        String orderBy = null;

        Cursor cursor = db.query(ChildTable.TABLE_NAME, //Table to query
                columns,                    //columns to return
                whereClause,                  //columns for the WHERE clause
                whereArgs,              //The values for the WHERE clause
                groupBy,                       //group the rows
                having,                       //filter by row groups
                orderBy
        );                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        return cursorCount > 0;
    }

    public Collection<AnthrosMembersContract> getUnsyncedEligbleMembers() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                AnthrosMembers.COLUMN__ID,
                AnthrosMembers.COLUMN_UID,
                AnthrosMembers.COLUMN_UUID,
                AnthrosMembers.COLUMN_FM_UID,
                AnthrosMembers.COLUMN_FORMDATE,
                AnthrosMembers.COLUMN_DEVICEID,
                AnthrosMembers.COLUMN_DEVICETAGID,
                AnthrosMembers.COLUMN_USER,
                AnthrosMembers.COLUMN_APPVERSION,
                AnthrosMembers.COLUMN_ENM_NO,
                AnthrosMembers.COLUMN_HH_NO,
                AnthrosMembers.COLUMN_DOB,
                AnthrosMembers.COLUMN_AGE,
                AnthrosMembers.COLUMN_na204,
                AnthrosMembers.COLUMN_SA3,
                AnthrosMembers.COLUMN_ISTATUS,
                AnthrosMembers.COLUMN_ISTATUS88x,
                AnthrosMembers.COLUMN_SYNCED,
                AnthrosMembers.COLUMN_SYNCEDDATE,
                AnthrosMembers.COLUMN_END_TIME,

        };
        String whereClause = AnthrosMembers.COLUMN_SYNCED + " is null OR " + AnthrosMembers.COLUMN_SYNCED + " = '' ";
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                AnthrosMembers.COLUMN__ID + " ASC";

        Collection<AnthrosMembersContract> allFC = new ArrayList<AnthrosMembersContract>();
        try {
            c = db.query(
                    AnthrosMembers.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                AnthrosMembersContract fc = new AnthrosMembersContract();
                allFC.add(fc.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public Collection<MWRAContract> getUnsyncedMWRA() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                MWRATable.COLUMN__ID,
                MWRATable.COLUMN_UID,
                MWRATable.COLUMN_UUID,
                MWRATable.COLUMN_FM_UID,
                MWRATable.COLUMN_FORMDATE,
                MWRATable.COLUMN_DEVICEID,
                MWRATable.COLUMN_DEVICETAGID,
                MWRATable.COLUMN_USER,
                MWRATable.COLUMN_APP_VER,
                MWRATable.COLUMN_B1SERIALNO,
                MWRATable.COLUMN_SB1,
                MWRATable.COLUMN_SB2,
                MWRATable.COLUMN_SB3,
                MWRATable.COLUMN_SB4,
                MWRATable.COLUMN_SB5,
                MWRATable.COLUMN_SB6,
                MWRATable.COLUMN_SB7,
                MWRATable.COLUMN_SB8,
                MWRATable.COLUMN_SB9,
                MWRATable.COLUMN_SB10,
                MWRATable.COLUMN_SB11,
                MWRATable.COLUMN_SB2FLAG,
                MWRATable.COLUMN_MSTATUS,
                MWRATable.COLUMN_MSTATUS88x,
                MWRATable.COLUMN_SYNCED,
                MWRATable.COLUMN_SYNCEDDATE

        };
        String whereClause = MWRATable.COLUMN_SYNCED + " is null OR " + MWRATable.COLUMN_SYNCED + " = '' ";
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                MWRATable.COLUMN__ID + " ASC";

        Collection<MWRAContract> allFC = new ArrayList<MWRAContract>();
        try {
            c = db.query(
                    MWRATable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                MWRAContract fc = new MWRAContract();
                allFC.add(fc.Hydrate(c, 0));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public Collection<D6AdolesContract> getUnsyncedAdoles() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                D6AdolesTable.COLUMN_ID,
                D6AdolesTable.COLUMN_UID,
                D6AdolesTable.COLUMN_UUID,
                D6AdolesTable.COLUMN_FM_UID,
                D6AdolesTable.COLUMN_FORMDATE,
                D6AdolesTable.COLUMN_DEVICEID,
                D6AdolesTable.COLUMN_DEVICETAGID,
                D6AdolesTable.COLUMN_USER,
                D6AdolesTable.COLUMN_APP_VER,
                D6AdolesTable.COLUMN_FMSERIALNO,
                D6AdolesTable.COLUMN_SD6,
                D6AdolesTable.COLUMN_SYNCED,
                D6AdolesTable.COLUMN_SYNCEDDATE,

        };
        String whereClause = D6AdolesTable.COLUMN_SYNCED + " is null OR " + D6AdolesTable.COLUMN_SYNCED + " = '' ";
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                D6AdolesTable.COLUMN_ID + " ASC";

        Collection<D6AdolesContract> allFC = new ArrayList<D6AdolesContract>();
        try {
            c = db.query(
                    D6AdolesTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                D6AdolesContract fc = new D6AdolesContract();
                allFC.add(fc.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public Collection<D4WRAContract> getUnsyncedDWRA(String formType) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                D4WRATable.COLUMN_ID,
                D4WRATable.COLUMN_UID,
                D4WRATable.COLUMN_UUID,
                D4WRATable.COLUMN_FM_UID,
                D4WRATable.COLUMN_FORMDATE,
                D4WRATable.COLUMN_DEVICEID,
                D4WRATable.COLUMN_FTYPE,
                D4WRATable.COLUMN_DEVICETAGID,
                D4WRATable.COLUMN_USER,
                D4WRATable.COLUMN_APP_VER,
                D4WRATable.COLUMN_DSERIALNO,
                D4WRATable.COLUMN_SD1,
                D4WRATable.COLUMN_SYNCED,
                D4WRATable.COLUMN_SYNCEDDATE,

        };
        String whereClause = "(" + D4WRATable.COLUMN_SYNCED + " is null OR " + D4WRATable.COLUMN_SYNCED + " = '') AND " + D4WRATable.COLUMN_FTYPE + " =? ";
        String[] whereArgs = {formType};
        String groupBy = null;
        String having = null;

        String orderBy =
                D4WRATable.COLUMN_ID + " ASC";

        Collection<D4WRAContract> allFC = new ArrayList<D4WRAContract>();
        try {
            c = db.query(
                    D4WRATable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                D4WRAContract fc = new D4WRAContract();
                allFC.add(fc.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }


    public Collection<DeviceContract> getUnsyncedDeviceInfo() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                DeviceContract.DeviceTable.COLUMN__ID,
                DeviceContract.DeviceTable.COLUMN_IMEI,
                DeviceContract.DeviceTable.COLUMN_APPVERSION,
                DeviceContract.DeviceTable.COLUMN_TAGID
        };
        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                DeviceContract.DeviceTable.COLUMN__ID + " ASC";

        Collection<DeviceContract> allFC = new ArrayList<DeviceContract>();
        try {
            c = db.query(
                    DeviceContract.DeviceTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                DeviceContract fc = new DeviceContract();
                allFC.add(fc.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public JSONArray getWRAsByUUID(String uuid) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                MWRATable.COLUMN__ID,
                MWRATable.COLUMN_UID,
                MWRATable.COLUMN_UUID,
                MWRATable.COLUMN_FM_UID,
                MWRATable.COLUMN_FORMDATE,
                MWRATable.COLUMN_DEVICEID,
                MWRATable.COLUMN_DEVICETAGID,
                MWRATable.COLUMN_USER,
                MWRATable.COLUMN_APP_VER,
                MWRATable.COLUMN_B1SERIALNO,
                MWRATable.COLUMN_SB1,
                MWRATable.COLUMN_SB2,
                MWRATable.COLUMN_SB3,
                MWRATable.COLUMN_SB4,
                MWRATable.COLUMN_SB5,
                MWRATable.COLUMN_SB6,
                MWRATable.COLUMN_SB7,
                MWRATable.COLUMN_SB8,
                MWRATable.COLUMN_SB9,
                MWRATable.COLUMN_SB10,
                MWRATable.COLUMN_SB11,
                MWRATable.COLUMN_SB2FLAG,
                MWRATable.COLUMN_MSTATUS,
                MWRATable.COLUMN_MSTATUS88x,
                MWRATable.COLUMN_SYNCED,
                MWRATable.COLUMN_SYNCEDDATE

        };
        String whereClause = MWRATable.COLUMN_UUID + " = ?";
        String[] whereArgs = {uuid};
        String groupBy = null;
        String having = null;

        String orderBy =
                MWRATable.COLUMN__ID + " ASC";

        JSONArray jsonArray = new JSONArray();
        Collection<MWRAContract> allWC = new ArrayList<MWRAContract>();
        try {
            c = db.query(
                    MWRATable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                MWRAContract wc = new MWRAContract();
                allWC.add(wc.Hydrate(c, 0));
            }
            for (MWRAContract wc : allWC) {
                //if (fc.getIstatus().equals("1")) {
                try {
                    jsonArray.put(wc.toJSONObject());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //}
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return jsonArray;
    }

    public Boolean getWRAExistanceByUid(String uuid, String fuid) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                MWRATable.COLUMN__ID,
                MWRATable.COLUMN_UID,
                MWRATable.COLUMN_UUID,
                MWRATable.COLUMN_FM_UID

        };
        String whereClause = MWRATable.COLUMN_UUID + " =? AND " + MWRATable.COLUMN_FM_UID + " =?";
        String[] whereArgs = {uuid, fuid};
        String groupBy = null;
        String having = null;

        String orderBy = null;

        Cursor cursor = db.query(MWRATable.TABLE_NAME, //Table to query
                columns,                    //columns to return
                whereClause,                  //columns for the WHERE clause
                whereArgs,              //The values for the WHERE clause
                groupBy,                       //group the rows
                having,                       //filter by row groups
                orderBy
        );                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        return cursorCount > 0;
    }

    public MWRAContract getWRANameByUid(String uid, String uuid) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                MWRATable.COLUMN__ID,
                MWRATable.COLUMN_UID,
                MWRATable.COLUMN_UUID,
                MWRATable.COLUMN_SB1,
                MWRATable.COLUMN_SB6,
                MWRATable.COLUMN_APP_VER,
                MWRATable.COLUMN_DEVICEID,
                MWRATable.COLUMN_USER,
                MWRATable.COLUMN_SB2FLAG,
                MWRATable.COLUMN_FM_UID,
                MWRATable.COLUMN_FORMDATE,
                MWRATable.COLUMN_DEVICETAGID,
                MWRATable.COLUMN_B1SERIALNO

        };
        String whereClause = MWRATable.COLUMN_UID + " =? AND " + MWRATable.COLUMN_UUID + " =?";
        String[] whereArgs = {uid, uuid};
        String groupBy = null;
        String having = null;

        String orderBy = null;

        Cursor cursor = null;

        MWRAContract allFC = new MWRAContract();

        try {
            c = db.query(
                    MWRATable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allFC = new MWRAContract().Hydrate(c, 1);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }


    public Collection<NutritionContract> getUnsyncedNutrition() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                NutritionTable.COLUMN__ID,
                NutritionTable.COLUMN_UID,
                NutritionTable.COLUMN_UUID,
                NutritionTable.COLUMN_FM_UID,
                NutritionTable.COLUMN_MUID,
                NutritionTable.COLUMN_FORMDATE,
                NutritionTable.COLUMN_DEVICEID,
                NutritionTable.COLUMN_DEVICETAGID,
                NutritionTable.COLUMN_USER,
                NutritionTable.COLUMN_APP_VER,
                NutritionTable.COLUMN_SB6,

                NutritionTable.COLUMN_SYNCED,
                NutritionTable.COLUMN_SYNCEDDATE,

        };
        String whereClause = NutritionTable.COLUMN_SYNCED + " is null OR " + NutritionTable.COLUMN_SYNCED + " = '' ";
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                NutritionTable.COLUMN__ID + " ASC";

        Collection<NutritionContract> allFC = new ArrayList<NutritionContract>();
        try {
            c = db.query(
                    NutritionTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                NutritionContract fc = new NutritionContract();
                allFC.add(fc.Hydrate(c, 0));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public Collection<RecipientsContract> getUnsyncedRecipients() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                RecipientsTable.COLUMN__ID,
                RecipientsTable.COLUMN_UID,
                RecipientsTable.COLUMN_UUID,
                RecipientsTable.COLUMN_FM_UID,
                RecipientsTable.COLUMN_FORMDATE,
                RecipientsTable.COLUMN_DEVICEID,
                RecipientsTable.COLUMN_DEVICETAGID,
                RecipientsTable.COLUMN_USER,
                RecipientsTable.COLUMN_APP_VER,
                RecipientsTable.COLUMN_A8ASNO,
                RecipientsTable.COLUMN_SA8A,

                RecipientsTable.COLUMN_SYNCED,
                RecipientsTable.COLUMN_SYNCEDDATE,

        };
        String whereClause = RecipientsTable.COLUMN_SYNCED + " is null OR " + RecipientsTable.COLUMN_SYNCED + " = '' ";
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                RecipientsTable.COLUMN__ID + " ASC";

        Collection<RecipientsContract> allFC = new ArrayList<RecipientsContract>();
        try {
            c = db.query(
                    RecipientsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                RecipientsContract fc = new RecipientsContract();
                allFC.add(fc.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public Collection<RecipientsContract> getPressedRecipients() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                RecipientsTable.COLUMN__ID,
                RecipientsTable.COLUMN_UID,
                RecipientsTable.COLUMN_UUID,
                RecipientsTable.COLUMN_FM_UID,
                RecipientsTable.COLUMN_FORMDATE,
                RecipientsTable.COLUMN_DEVICEID,
                RecipientsTable.COLUMN_DEVICETAGID,
                RecipientsTable.COLUMN_USER,
                RecipientsTable.COLUMN_APP_VER,
                RecipientsTable.COLUMN_A8ASNO,
                RecipientsTable.COLUMN_SA8A,
        };
        String whereClause = RecipientsTable.COLUMN_UUID + " =?";
        String[] whereArgs = {MainApp.fc.getUID()};
        String groupBy = null;
        String having = null;

        String orderBy =
                RecipientsTable.COLUMN__ID + " ASC";

        Collection<RecipientsContract> allFC = new ArrayList<RecipientsContract>();
        try {
            c = db.query(
                    RecipientsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                RecipientsContract fc = new RecipientsContract();
                allFC.add(fc.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public Collection<OutcomeContract> getUnsyncedOutcome() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                outcomeTable.COLUMN__ID,
                outcomeTable.COLUMN_UID,
                outcomeTable.COLUMN_UUID,
                outcomeTable.COLUMN_MUID,
                outcomeTable.COLUMN_FM_UID,
                outcomeTable.COLUMN_FORMDATE,
                outcomeTable.COLUMN_DEVICEID,
                outcomeTable.COLUMN_DEVICETAGID,
                outcomeTable.COLUMN_USER,
                outcomeTable.COLUMN_APP_VER,
                outcomeTable.COLUMN_B1APregSNO,
                outcomeTable.COLUMN_SB1A,

                outcomeTable.COLUMN_SYNCED,
                outcomeTable.COLUMN_SYNCEDDATE,

        };
        String whereClause = outcomeTable.COLUMN_SYNCED + " is null OR " + outcomeTable.COLUMN_SYNCED + " = '' ";
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                outcomeTable.COLUMN__ID + " ASC";

        Collection<OutcomeContract> allFC = new ArrayList<OutcomeContract>();
        try {
            c = db.query(
                    outcomeTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                OutcomeContract fc = new OutcomeContract();
                allFC.add(fc.Hydrate(c, 0));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public Collection<OutcomeContract> getPressedOutcome() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                outcomeTable.COLUMN__ID,
                outcomeTable.COLUMN_UID,
                outcomeTable.COLUMN_UUID,
                outcomeTable.COLUMN_MUID,
                outcomeTable.COLUMN_FM_UID,
                outcomeTable.COLUMN_SB1A,
                outcomeTable.COLUMN_B1APregSNO,
        };

        String whereClause = outcomeTable.COLUMN_UUID + "=? AND " + outcomeTable.COLUMN_MUID + "=?";
        String[] whereArgs = new String[]{MainApp.mc.get_UUID(), MainApp.mc.get_UID()};
        String groupBy = null;
        String having = null;

        String orderBy =
                outcomeTable.COLUMN__ID + " ASC";

        Collection<OutcomeContract> allFC = new ArrayList<>();
        try {
            c = db.query(
                    outcomeTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                OutcomeContract fc = new OutcomeContract();
                allFC.add(fc.Hydrate(c, 1));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public Collection<NutritionContract> getPressedNutrition() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                NutritionTable.COLUMN__ID,
                NutritionTable.COLUMN_UID,
                NutritionTable.COLUMN_UUID,
                NutritionTable.COLUMN_FM_UID,
                NutritionTable.COLUMN_MUID,
                NutritionTable.COLUMN_SB6
        };

        String whereClause = NutritionTable.COLUMN_MUID + "=?";
        String[] whereArgs = new String[]{MainApp.mc.get_UID()};
        String groupBy = null;
        String having = null;

        String orderBy =
                NutritionTable.COLUMN__ID + " ASC";

        Collection<NutritionContract> allFC = new ArrayList<>();
        try {
            c = db.query(
                    NutritionTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                NutritionContract fc = new NutritionContract();
                allFC.add(fc.Hydrate(c, 1));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public Boolean getNutritionCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                NutritionTable.COLUMN__ID,
                NutritionTable.COLUMN_UID,
                NutritionTable.COLUMN_UUID,
                NutritionTable.COLUMN_FM_UID,
                NutritionTable.COLUMN_MUID,
                NutritionTable.COLUMN_SB6
        };

        String whereClause = NutritionTable.COLUMN_UUID + "=? AND " + NutritionTable.COLUMN_FM_UID + "=?";
        String[] whereArgs = new String[]{MainApp.mc.get_UUID(), MainApp.mc.getFMUID()};
        String groupBy = null;
        String having = null;

        String orderBy =
                NutritionTable.COLUMN__ID + " ASC";

        c = db.query(
                NutritionTable.TABLE_NAME,  // The table to query
                columns,                   // The columns to return
                whereClause,               // The columns for the WHERE clause
                whereArgs,                 // The values for the WHERE clause
                groupBy,                   // don't group the rows
                having,                    // don't filter by row groups
                orderBy                    // The sort order
        );

        int cursorCount = c.getCount();
        c.close();
        db.close();
        return cursorCount > 0;
    }

    public FormsContract getsA4() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_SA4
        };


        String whereClause = FormsTable.COLUMN_UID + "=?";
        String[] whereArgs = new String[]{MainApp.fc.getUID()};
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable._ID + " ASC";

        FormsContract allFC = new FormsContract();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormsContract fc = new FormsContract();
                allFC = fc.Hydrate1(c, 4);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public FormsContract getsA402() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_SA402
        };


        String whereClause = FormsTable.COLUMN_UID + "=?";
        String[] whereArgs = new String[]{MainApp.fc.getUID()};
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable._ID + " ASC";

        FormsContract allFC = new FormsContract();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormsContract fc = new FormsContract();
                allFC = fc.Hydrate1(c, 402);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public FormsContract getsA5() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_SA5
        };


        String whereClause = FormsTable.COLUMN_UID + "=?";
        String[] whereArgs = new String[]{MainApp.fc.getUID()};
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable._ID + " ASC";

        FormsContract allFC = new FormsContract();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormsContract fc = new FormsContract();
                allFC = fc.Hydrate1(c, 5);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public FormsContract getsA7() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_SA7
        };


        String whereClause = FormsTable.COLUMN_UID + "=?";
        String[] whereArgs = new String[]{MainApp.fc.getUID()};
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable._ID + " ASC";

        FormsContract allFC = new FormsContract();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormsContract fc = new FormsContract();
                allFC = fc.Hydrate1(c, 7);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public MWRAContract getsB1(String formUid, String fmUid) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                MWRATable.COLUMN__ID,
                MWRATable.COLUMN_UID,
                MWRATable.COLUMN_UUID,
                MWRATable.COLUMN_SB1,
                MWRATable.COLUMN_SB6,
                MWRATable.COLUMN_APP_VER,
                MWRATable.COLUMN_DEVICEID,
                MWRATable.COLUMN_USER,
                MWRATable.COLUMN_SB2FLAG,
                MWRATable.COLUMN_FM_UID,
                MWRATable.COLUMN_FORMDATE,
                MWRATable.COLUMN_DEVICETAGID,
                MWRATable.COLUMN_B1SERIALNO
        };

        String whereClause = MWRATable.COLUMN_UUID + "=? AND " + MWRATable.COLUMN_FM_UID + " =?";
        String[] whereArgs = {formUid, fmUid};
        String groupBy = null;
        String having = null;

        String orderBy =
                MWRATable.COLUMN__ID + " ASC";

        MWRAContract allFC = new MWRAContract();
        try {
            c = db.query(
                    MWRATable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                MWRAContract mwc = new MWRAContract();
                allFC = mwc.Hydrate(c, 1);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public MWRAContract getsB2() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                MWRATable.COLUMN__ID,
                MWRATable.COLUMN_UID,
                MWRATable.COLUMN_UUID,
                MWRATable.COLUMN_FM_UID,
                MWRATable.COLUMN_SB2,
                MWRATable.COLUMN_SB6,
                MWRATable.COLUMN_SB2FLAG
        };


        String whereClause = MWRATable.COLUMN_UID + "=?";
        String[] whereArgs = new String[]{MainApp.mc.get_UID()};
        String groupBy = null;
        String having = null;

        String orderBy =
                MWRATable.COLUMN__ID + " ASC";

        MWRAContract allFC = new MWRAContract();
        try {
            c = db.query(
                    MWRATable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                MWRAContract fc = new MWRAContract();
                allFC = fc.Hydrate(c, 2);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public MWRAContract getsB3() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                MWRATable.COLUMN__ID,
                MWRATable.COLUMN_UID,
                MWRATable.COLUMN_UUID,
                MWRATable.COLUMN_FM_UID,
                MWRATable.COLUMN_SB3
        };


        String whereClause = MWRATable.COLUMN_UID + "=?";
        String[] whereArgs = new String[]{MainApp.mc.get_UID()};
        String groupBy = null;
        String having = null;

        String orderBy =
                MWRATable.COLUMN__ID + " ASC";

        MWRAContract allFC = new MWRAContract();
        try {
            c = db.query(
                    MWRATable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                MWRAContract fc = new MWRAContract();
                allFC = fc.Hydrate(c, 3);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public MWRAContract getsB4() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                MWRATable.COLUMN__ID,
                MWRATable.COLUMN_UID,
                MWRATable.COLUMN_UUID,
                MWRATable.COLUMN_FM_UID,
                MWRATable.COLUMN_SB4
        };


        String whereClause = MWRATable.COLUMN_UID + "=?";
        String[] whereArgs = new String[]{MainApp.mc.get_UID()};
        String groupBy = null;
        String having = null;

        String orderBy =
                MWRATable.COLUMN__ID + " ASC";

        MWRAContract allFC = new MWRAContract();
        try {
            c = db.query(
                    MWRATable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                MWRAContract fc = new MWRAContract();
                allFC = fc.Hydrate(c, 4);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public MWRAContract getsB5() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                MWRATable.COLUMN__ID,
                MWRATable.COLUMN_UID,
                MWRATable.COLUMN_UUID,
                MWRATable.COLUMN_FM_UID,
                MWRATable.COLUMN_SB5
        };


        String whereClause = MWRATable.COLUMN_UID + "=?";
        String[] whereArgs = new String[]{MainApp.mc.get_UID()};
        String groupBy = null;
        String having = null;

        String orderBy =
                MWRATable.COLUMN__ID + " ASC";

        MWRAContract allFC = new MWRAContract();
        try {
            c = db.query(
                    MWRATable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                MWRAContract fc = new MWRAContract();
                allFC = fc.Hydrate(c, 5);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public ChildContract getsC1(String uuid, String uid) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                ChildTable.COLUMN__ID,
                ChildTable.COLUMN__UID,
                ChildTable.COLUMN__UUID,
                ChildTable.COLUMN_FM_UID,
                ChildTable.COLUMN_MUID,
                ChildTable.COLUMN_C1SERIALNO,
                ChildTable.COLUMN_SC1,
        };


        String whereClause = ChildTable.COLUMN__UUID + " =? AND " + ChildTable.COLUMN_FM_UID + " =?";
        String[] whereArgs = {uuid, uid};
        String groupBy = null;
        String having = null;

        String orderBy =
                ChildTable.COLUMN__ID + " ASC";

        ChildContract allFC = new ChildContract();
        try {
            c = db.query(
                    ChildTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                ChildContract fc = new ChildContract();
                allFC = fc.Hydrate(c, 1);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public ChildContract getsC2() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                ChildTable.COLUMN__ID,
                ChildTable.COLUMN__UID,
                ChildTable.COLUMN__UUID,
                ChildTable.COLUMN_FM_UID,
                ChildTable.COLUMN_MUID,
                ChildTable.COLUMN_SC2
        };


        String whereClause = ChildTable.COLUMN__UID + "=?";
        String[] whereArgs = new String[]{MainApp.cc.getUID()};
        String groupBy = null;
        String having = null;

        String orderBy =
                ChildTable.COLUMN__ID + " ASC";

        ChildContract allFC = new ChildContract();
        try {
            c = db.query(
                    ChildTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                ChildContract fc = new ChildContract();
                allFC = fc.Hydrate(c, 2);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public ChildContract getsC3() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                ChildTable.COLUMN__ID,
                ChildTable.COLUMN__UID,
                ChildTable.COLUMN__UUID,
                ChildTable.COLUMN_MUID,
                ChildTable.COLUMN_FM_UID,
                ChildTable.COLUMN_SC3
        };


        String whereClause = ChildTable.COLUMN__UID + "=?";
        String[] whereArgs = new String[]{MainApp.cc.getUID()};
        String groupBy = null;
        String having = null;

        String orderBy =
                ChildTable.COLUMN__ID + " ASC";

        ChildContract allFC = new ChildContract();
        try {
            c = db.query(
                    ChildTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                ChildContract fc = new ChildContract();
                allFC = fc.Hydrate(c, 3);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }


    public ChildContract getsC4() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                ChildTable.COLUMN__ID,
                ChildTable.COLUMN__UID,
                ChildTable.COLUMN__UUID,
                ChildTable.COLUMN_FM_UID,
                ChildTable.COLUMN_MUID,
                ChildTable.COLUMN_SC4
        };


        String whereClause = ChildTable.COLUMN__UID + "=?";
        String[] whereArgs = new String[]{MainApp.cc.getUID()};
        String groupBy = null;
        String having = null;

        String orderBy =
                ChildTable.COLUMN__ID + " ASC";

        ChildContract allFC = new ChildContract();
        try {
            c = db.query(
                    ChildTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                ChildContract fc = new ChildContract();
                allFC = fc.Hydrate(c, 4);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public ChildContract getsC5() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                ChildTable.COLUMN__ID,
                ChildTable.COLUMN__UID,
                ChildTable.COLUMN__UUID,
                ChildTable.COLUMN_MUID,
                ChildTable.COLUMN_FM_UID,
                ChildTable.COLUMN_SC5
        };


        String whereClause = ChildTable.COLUMN__UID + "=?";
        String[] whereArgs = new String[]{MainApp.cc.getUID()};
        String groupBy = null;
        String having = null;

        String orderBy =
                ChildTable.COLUMN__ID + " ASC";

        ChildContract allFC = new ChildContract();
        try {
            c = db.query(
                    ChildTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                ChildContract fc = new ChildContract();
                allFC = fc.Hydrate(c, 5);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }


    public Collection<SerialContract> getUnsyncedSerials() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                singleSerial._ID,
                singleSerial.COLUMN_DEVICE_ID,
                singleSerial.COLUMN_DATE,
                singleSerial.COLUMN_SERIAL_NO,
                singleSerial.COLUMN_SYNCED,
                singleSerial.COLUMN_SYNCED_DATE
        };

        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                singleSerial._ID + " ASC";

        Collection<SerialContract> allFC = new ArrayList<SerialContract>();
        try {
            c = db.query(
                    singleSerial.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                SerialContract fc = new SerialContract();
                allFC.add(fc.hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }


    public Collection<FormsContract> getTodayForms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_HH_NO,
                FormsTable.COLUMN_CLUSTER_NO,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_SYNCED,

        };
        String whereClause = FormsTable.COLUMN_FORMDATE + " Like ? ";
        String[] whereArgs = new String[]{"%" + spDateT.substring(0, 8).trim() + "%"};
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable._ID + " ASC";

        Collection<FormsContract> allFC = new ArrayList<>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormsContract fc = new FormsContract();
                fc.set_ID(c.getString(c.getColumnIndex(FormsTable._ID)));
                fc.setClusterNo(c.getString(c.getColumnIndex(FormsTable.COLUMN_CLUSTER_NO)));
                fc.setHhNo(c.getString(c.getColumnIndex(FormsTable.COLUMN_HH_NO)));
                fc.setFormDate(c.getString(c.getColumnIndex(FormsTable.COLUMN_FORMDATE)));
                fc.setIstatus(c.getString(c.getColumnIndex(FormsTable.COLUMN_ISTATUS)));
                fc.setSynced(c.getString(c.getColumnIndex(FormsTable.COLUMN_SYNCED)));
                allFC.add(fc);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public ArrayList<Summary> getSummary() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        ArrayList<Summary> cursorMap = new ArrayList<>();

        try {
            //execute the query results will be save in Cursor c
            c = db.rawQuery(SQL_SUMMARY_JOIN, null);
            while (c.moveToNext()) {
                cursorMap.add(new Summary(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return cursorMap;
    }

    // ANDROID DATABASE MANAGER
    public ArrayList<Cursor> getData(String Query) {
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[]{"message"};
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2 = new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try {
            String maxQuery = Query;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);

            //add value to cursor2
            Cursor2.addRow(new Object[]{"Success"});

            alc.set(1, Cursor2);
            if (null != c && c.getCount() > 0) {

                alc.set(0, c);
                c.moveToFirst();

                return alc;
            }
            return alc;
        } catch (SQLException sqlEx) {
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + sqlEx.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        } catch (Exception ex) {

            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + ex.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        }
    }

    // mwra - uPDATE
    public int updateSB2() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(MWRATable.COLUMN_SB2, MainApp.mc.getsB2());
        values.put(MWRATable.COLUMN_SB2FLAG, MainApp.mc.getSb2flag());

// Which row to update, based on the ID
        String selection = MWRATable.COLUMN__ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.mc.get_ID())};

        int count = db.update(MWRATable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }


    public int updateSB3() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(MWRATable.COLUMN_SB3, MainApp.mc.getsB3());

// Which row to update, based on the ID
        String selection = MWRATable.COLUMN__ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.mc.get_ID())};

        int count = db.update(MWRATable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }


    public int updateSB4() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(MWRATable.COLUMN_SB4, MainApp.mc.getsB4());

// Which row to update, based on the ID
        String selection = MWRATable.COLUMN__ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.mc.get_ID())};

        int count = db.update(MWRATable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateSB5() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(MWRATable.COLUMN_SB5, MainApp.mc.getsB5());

// Which row to update, based on the ID
        String selection = MWRATable.COLUMN__ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.mc.get_ID())};

        int count = db.update(MWRATable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateSB6() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(MWRATable.COLUMN_SB6, MainApp.mc.getsB6());

// Which row to update, based on the ID
        String selection = MWRATable.COLUMN__ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.mc.get_ID())};

        int count = db.update(MWRATable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateSB7() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(MWRATable.COLUMN_SB7, MainApp.mc.getsB7());

// Which row to update, based on the ID
        String selection = MWRATable.COLUMN__ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.mc.get_ID())};

        int count = db.update(MWRATable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateSB8() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(MWRATable.COLUMN_SB8, MainApp.mc.getsB8());

// Which row to update, based on the ID
        String selection = MWRATable.COLUMN__ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.mc.get_ID())};

        int count = db.update(MWRATable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateSB9() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(MWRATable.COLUMN_SB9, MainApp.mc.getsB9());

// Which row to update, based on the ID
        String selection = MWRATable.COLUMN__ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.mc.get_ID())};

        int count = db.update(MWRATable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }


    public int updateSACount() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_COUNT, MainApp.fc.getCount());
        values.put(FormsTable.COLUMN_RESP_LNO, MainApp.fc.getRespLineNo());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }


    public int updateSA4() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SA4, MainApp.fc.getsA4());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " = ?";
        String[] selectionArgs = {MainApp.fc.get_ID()};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateSA402() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SA402, MainApp.fc.getsA402());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " = ?";
        String[] selectionArgs = {MainApp.fc.get_ID()};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateSA5() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SA5, MainApp.fc.getsA5());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " = ?";
        String[] selectionArgs = {MainApp.fc.get_ID()};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateSA7() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SA7, MainApp.fc.getsA7());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " = ?";
        String[] selectionArgs = {MainApp.fc.get_ID()};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateSC1() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(ChildTable.COLUMN_SC1, MainApp.cc.getsC1());

// Which row to update, based on the ID
        String selection = ChildTable.COLUMN__ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.cc.get_ID())};

        int count = db.update(ChildTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateSC2() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(ChildTable.COLUMN_SC2, MainApp.cc.getsC2());

// Which row to update, based on the ID
        String selection = ChildTable.COLUMN__ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.cc.get_ID())};

        int count = db.update(ChildTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateSC3() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(ChildTable.COLUMN_SC3, MainApp.cc.getsC3());

// Which row to update, based on the ID
        String selection = ChildTable.COLUMN__ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.cc.get_ID())};

        int count = db.update(ChildTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateSC4() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(ChildTable.COLUMN_SC4, MainApp.cc.getsC4());

// Which row to update, based on the ID
        String selection = ChildTable.COLUMN__ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.cc.get_ID())};

        int count = db.update(ChildTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }


    public int updateSC5() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(ChildTable.COLUMN_SC5, MainApp.cc.getsC5());

// Which row to update, based on the ID
        String selection = ChildTable.COLUMN__ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.cc.get_ID())};

        int count = db.update(ChildTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateSC6() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(ChildTable.COLUMN_SC6, MainApp.cc.getsC6());

// Which row to update, based on the ID
        String selection = ChildTable.COLUMN__ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.cc.get_ID())};

        int count = db.update(ChildTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    /*public int updateSB4() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_END_TIME, MainApp.fc.getEndtime());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

*/
    public int updateCount() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SA4, MainApp.fc.getsA4());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }


    public int updateEnding() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_ISTATUS, MainApp.fc.getIstatus());
        values.put(FormsTable.COLUMN_ISTATUS88x, MainApp.fc.getIstatus88x());
        values.put(FormsTable.COLUMN_END_TIME, MainApp.fc.getEndtime());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateHHEnding() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_ISTATUSHH, MainApp.fc.getIstatusHH());
        values.put(FormsTable.COLUMN_END_TIME, MainApp.fc.getEndtime());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }


    public int updateAnthroEnding() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(AnthrosMembers.COLUMN_ISTATUS, MainApp.emc.getIstatus());
        //values.put(AnthrosMembers.COLUMN_ISTATUS88x, MainApp.emc.getIstatus88x());
        values.put(AnthrosMembers.COLUMN_END_TIME, MainApp.emc.getEnd_time());

// Which row to update, based on the ID
        String selection = AnthrosMembers.COLUMN__ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.emc.get_ID())};

        int count = db.update(AnthrosMembers.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }


    public int updateChildEnding() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(ChildTable.COLUMN_CSTATUS, MainApp.cc.getCstatus());
        values.put(ChildTable.COLUMN_CSTATUS88x, MainApp.cc.getCstatus88x());

// Which row to update, based on the ID
        String selection = ChildTable.COLUMN__ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.cc.get_ID())};

        int count = db.update(ChildTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateMotherEnding() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(MWRATable.COLUMN_MSTATUS, MainApp.mc.getMstatus());
        values.put(MWRATable.COLUMN_MSTATUS88x, MainApp.mc.getMstatus88x());

// Which row to update, based on the ID
        String selection = MWRATable.COLUMN__ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.mc.get_ID())};

        int count = db.update(MWRATable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public String getDeviceTAG() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        String tagID = null;
        try {
            String query = "SELECT " + DeviceContract.DeviceTable.COLUMN_TAGID + " FROM " + DeviceContract.DeviceTable.TABLE_NAME + " WHERE " + DeviceContract.DeviceTable.COLUMN__ID + " = 1";
            cursor = db.rawQuery(
                    query,
                    new String[]{null}
            );

            if (null != cursor)
                if (cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    tagID = cursor.getString(4);
                }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return tagID;
    }

    public int isDeviceInfoExists() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        int count = 0;
        try {
            String query = "SELECT * FROM " + DeviceContract.DeviceTable.TABLE_NAME + " WHERE " + DeviceContract.DeviceTable.COLUMN_IMEI + " != '' OR null ";
            cursor = db.rawQuery(
                    query,
                    new String[]{null}
            );
            if (null != cursor)
                if (cursor.getCount() > 0) {
                    count = cursor.getCount();
                }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return count;
    }

}