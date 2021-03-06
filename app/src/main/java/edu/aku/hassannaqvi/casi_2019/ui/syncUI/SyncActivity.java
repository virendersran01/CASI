package edu.aku.hassannaqvi.casi_2019.ui.syncUI;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import edu.aku.hassannaqvi.casi_2019.R;
import edu.aku.hassannaqvi.casi_2019.adapters.SyncListAdapter;
import edu.aku.hassannaqvi.casi_2019.adapters.Upload_list_adapter;
import edu.aku.hassannaqvi.casi_2019.contracts.AnthrosMembersContract;
import edu.aku.hassannaqvi.casi_2019.contracts.ChildContract;
import edu.aku.hassannaqvi.casi_2019.contracts.D4WRAContract;
import edu.aku.hassannaqvi.casi_2019.contracts.D6AdolesContract;
import edu.aku.hassannaqvi.casi_2019.contracts.FamilyMembersContract;
import edu.aku.hassannaqvi.casi_2019.contracts.FormsContract;
import edu.aku.hassannaqvi.casi_2019.contracts.MWRAContract;
import edu.aku.hassannaqvi.casi_2019.contracts.RecipientsContract;
import edu.aku.hassannaqvi.casi_2019.contracts.SignupContract;
import edu.aku.hassannaqvi.casi_2019.contracts.SpecimenContract;
import edu.aku.hassannaqvi.casi_2019.core.DatabaseHelper;
import edu.aku.hassannaqvi.casi_2019.core.MainApp;
import edu.aku.hassannaqvi.casi_2019.databinding.ActivitySyncBinding;
import edu.aku.hassannaqvi.casi_2019.get.GetAllData;
import edu.aku.hassannaqvi.casi_2019.other.SyncModel;
import edu.aku.hassannaqvi.casi_2019.sync.SyncAllData;
import edu.aku.hassannaqvi.casi_2019.sync.SyncDevice;

public class SyncActivity extends AppCompatActivity implements SyncDevice.SyncDevicInterface {
    SharedPreferences.Editor editor;
    SharedPreferences sharedPref;
    String DirectoryName;
    DatabaseHelper db;
    SyncListAdapter syncListAdapter;
    Upload_list_adapter uploadListAdapter;
    ActivitySyncBinding bi;
    SyncModel model;
    SyncModel uploadmodel;
    List<SyncModel> list;
    List<SyncModel> uploadlist;
    Boolean listActivityCreated;
    Boolean uploadlistActivityCreated;
    String dtToday = new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_sync);
        bi.setCallback(this);
        list = new ArrayList<>();
        uploadlist = new ArrayList<>();
        bi.noItem.setVisibility(View.VISIBLE);
        bi.noDataItem.setVisibility(View.VISIBLE);
        listActivityCreated = true;
        uploadlistActivityCreated = true;
        db = new DatabaseHelper(this);
        dbBackup();
        bi.btnSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(SyncActivity.this, "Start Downloading Data", Toast.LENGTH_SHORT).show();
                onSyncDataClick();
            }
        });
        bi.btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(SyncActivity.this, "Start Uploading Data", Toast.LENGTH_SHORT).show();
                syncServer();
            }
        });
        setAdapter();
        setUploadAdapter();
    }

    public void onSyncDataClick() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new SyncDevice(SyncActivity.this, true).execute();
        } else {
            Toast.makeText(this, "No network connection available.", Toast.LENGTH_SHORT).show();
        }
    }

    void setAdapter() {
        syncListAdapter = new SyncListAdapter(list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        bi.rvSyncList.setLayoutManager(mLayoutManager);
        bi.rvSyncList.setItemAnimator(new DefaultItemAnimator());
        bi.rvSyncList.setAdapter(syncListAdapter);
        syncListAdapter.notifyDataSetChanged();
        if (syncListAdapter.getItemCount() > 0) {
            bi.noItem.setVisibility(View.GONE);
        } else {
            bi.noItem.setVisibility(View.VISIBLE);
        }
    }

    void setUploadAdapter() {
        uploadListAdapter = new Upload_list_adapter(uploadlist);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getApplicationContext());
        bi.rvUploadList.setLayoutManager(mLayoutManager2);
        bi.rvUploadList.setItemAnimator(new DefaultItemAnimator());
        bi.rvUploadList.setAdapter(uploadListAdapter);
        uploadListAdapter.notifyDataSetChanged();
        if (uploadListAdapter.getItemCount() > 0) {
            bi.noDataItem.setVisibility(View.GONE);
        } else {
            bi.noDataItem.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void processFinish(boolean flag) {
        if (flag) {
            HashMap<String, String> tagVal = MainApp.getTagValues(this);
            new syncData(SyncActivity.this, tagVal.get("org") != null ? tagVal.get("org").equals("null") ? null : tagVal.get("org") : null).execute();
            MainApp.updateApp(this);
        }
    }

    public void syncServer() {

        // Require permissions INTERNET & ACCESS_NETWORK_STATE
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            DatabaseHelper db = new DatabaseHelper(this);

            new SyncDevice(this, false).execute();
//            Toast.makeText(getApplicationContext(), "Syncing Forms", Toast.LENGTH_SHORT).show();
            if (uploadlistActivityCreated) {
                uploadmodel = new SyncModel();
                uploadmodel.setstatusID(0);
                uploadlist.add(uploadmodel);
            }
            new SyncAllData(
                    this,
                    "Forms",
                    "updateSyncedForms",
                    FormsContract.class,
                    MainApp._HOST_URL + FormsContract.FormsTable._URL,
                    db.getUnsyncedForms(), this.findViewById(R.id.syncStatus), 0, uploadListAdapter, uploadlist
            ).execute();

//            Toast.makeText(getApplicationContext(), "Syncing Family Members", Toast.LENGTH_SHORT).show();
            if (uploadlistActivityCreated) {
                uploadmodel = new SyncModel();
                uploadmodel.setstatusID(0);
                uploadlist.add(uploadmodel);
            }
            new SyncAllData(
                    this,
                    "Family Members",
                    "updateSyncedFamilyMembers",
                    FamilyMembersContract.class,
                    MainApp._HOST_URL + FamilyMembersContract.familyMembers._URL,
                    db.getUnsyncedFamilyMembers(), this.findViewById(R.id.syncStatus), 1, uploadListAdapter, uploadlist
            ).execute();
            bi.noDataItem.setVisibility(View.GONE);
//            Toast.makeText(getApplicationContext(), "Syncing WRAs", Toast.LENGTH_SHORT).show();
            if (uploadlistActivityCreated) {
                uploadmodel = new SyncModel();
                uploadmodel.setstatusID(0);
                uploadlist.add(uploadmodel);
            }
            new SyncAllData(
                    this,
                    "WRAs",
                    "updateSyncedMWRAForm",
                    MWRAContract.class,
                    MainApp._HOST_URL + MWRAContract.MWRATable._URL,
                    db.getUnsyncedMWRA(), this.findViewById(R.id.syncStatus), 2, uploadListAdapter, uploadlist
            ).execute();

//            Toast.makeText(getApplicationContext(), "Syncing Children", Toast.LENGTH_SHORT).show();
            if (uploadlistActivityCreated) {
                uploadmodel = new SyncModel();
                uploadmodel.setstatusID(0);
                uploadlist.add(uploadmodel);
            }
            new SyncAllData(
                    this,
                    "Children",
                    "updateSyncedChildForm",
                    ChildContract.class,
                    MainApp._HOST_URL + ChildContract.ChildTable._URL,
                    db.getUnsyncedChildForms(), this.findViewById(R.id.syncStatus), 3, uploadListAdapter, uploadlist
            ).execute();

//            Toast.makeText(getApplicationContext(), "Syncing Eligibles", Toast.LENGTH_SHORT).show();
            if (uploadlistActivityCreated) {
                uploadmodel = new SyncModel();
                uploadmodel.setstatusID(0);
                uploadlist.add(uploadmodel);
            }
            new SyncAllData(
                    this,
                    "Anthros",
                    "updateSyncedAnthros",
                    AnthrosMembersContract.class,
                    MainApp._HOST_URL + AnthrosMembersContract.AnthrosMembers._URL,
                    db.getUnsyncedEligbleMembers(), this.findViewById(R.id.syncStatus), 4, uploadListAdapter, uploadlist
            ).execute();

//            Toast.makeText(getApplicationContext(), "Syncing New Users", Toast.LENGTH_SHORT).show();
            if (uploadlistActivityCreated) {
                uploadmodel = new SyncModel();
                uploadmodel.setstatusID(0);
                uploadlist.add(uploadmodel);
            }
            new SyncAllData(
                    this,
                    "New Users",
                    "updateSyncedSignup",
                    RecipientsContract.class,
                    MainApp._HOST_URL + SignupContract.SignUpTable._URL,
                    db.getUnsyncedSignups(), this.findViewById(R.id.syncStatus), 5, uploadListAdapter, uploadlist
            ).execute();

//            Toast.makeText(getApplicationContext(), "Syncing Blood Specimen", Toast.LENGTH_SHORT).show();
            if (uploadlistActivityCreated) {
                uploadmodel = new SyncModel();
                uploadmodel.setstatusID(0);
                uploadlist.add(uploadmodel);
            }
            new SyncAllData(
                    this,
                    "Hemoglobin",
                    "updateSyncedSpecimen",
                    SpecimenContract.class,
                    MainApp._HOST_URL + SpecimenContract.SpecimenTable._URL,
                    db.getUnsyncedSpecimenForms(), this.findViewById(R.id.syncStatus), 6, uploadListAdapter, uploadlist
            ).execute();

            HashMap<String, String> tagVal = MainApp.getTagValues(this);
            String country = tagVal.get("org") != null ? tagVal.get("org").equals("null") ? "0" : tagVal.get("org").equals("") ? "0" : tagVal.get("org") : "0";
            if (Integer.valueOf(country) == 3) return;

            if (uploadlistActivityCreated) {
                uploadmodel = new SyncModel();
                uploadmodel.setstatusID(0);
                uploadlist.add(uploadmodel);
            }
            new SyncAllData(
                    this,
                    "Adolescent",
                    "updateDAdoleSyncedForms",
                    D6AdolesContract.class,
                    MainApp._HOST_URL + D6AdolesContract.D6AdolesTable._URL,
                    db.getUnsyncedAdoles(), this.findViewById(R.id.syncStatus), 7, uploadListAdapter, uploadlist
            ).execute();

            for (int i = 0; i < MainApp.D4WRAURLS.length; i++) {
                if (uploadlistActivityCreated) {
                    uploadmodel = new SyncModel();
                    uploadmodel.setstatusID(0);
                    uploadlist.add(uploadmodel);
                }
                new SyncAllData(
                        this,
                        "DWRA_" + MainApp.D4WRATypes[i].toUpperCase(),
                        "updateDWRASyncedForm",
                        D4WRAContract.class,
                        MainApp._HOST_URL + D4WRAContract.D4WRATable.urls[i],
                        db.getUnsyncedDWRA(MainApp.D4WRATypes[i]), this.findViewById(R.id.syncStatus), 8 + i, uploadListAdapter, uploadlist
                ).execute();
            }


            uploadlistActivityCreated = false;

            SharedPreferences syncPref = getSharedPreferences("SyncInfo", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = syncPref.edit();

            editor.putString("LastUpSyncServer", dtToday);

            editor.apply();

        } else {
            Toast.makeText(this, "No network connection available.", Toast.LENGTH_SHORT).show();
        }

    }

    public void dbBackup() {

        sharedPref = getSharedPreferences("src", MODE_PRIVATE);
        editor = sharedPref.edit();

        if (sharedPref.getBoolean("flag", false)) {

            String dt = sharedPref.getString("dt", new SimpleDateFormat("dd-MM-yy").format(new Date()));

            if (dt != new SimpleDateFormat("dd-MM-yy").format(new Date())) {
                editor.putString("dt", new SimpleDateFormat("dd-MM-yy").format(new Date()));

                editor.commit();
            }

            File folder = new File(Environment.getExternalStorageDirectory() + File.separator + DatabaseHelper.PROJECT_NAME);
            boolean success = true;
            if (!folder.exists()) {
                success = folder.mkdirs();
            }
            if (success) {

                DirectoryName = folder.getPath() + File.separator + sharedPref.getString("dt", "");
                folder = new File(DirectoryName);
                if (!folder.exists()) {
                    success = folder.mkdirs();
                }
                if (success) {

                    try {
                        File dbFile = new File(this.getDatabasePath(DatabaseHelper.DATABASE_NAME).getPath());
                        FileInputStream fis = new FileInputStream(dbFile);

                        String outFileName = DirectoryName + File.separator +
                                DatabaseHelper.DB_NAME;

                        // Open the empty db as the output stream
                        OutputStream output = new FileOutputStream(outFileName);

                        // Transfer bytes from the inputfile to the outputfile
                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = fis.read(buffer)) > 0) {
                            output.write(buffer, 0, length);
                        }
                        // Close the streams
                        output.flush();
                        output.close();
                        fis.close();
                    } catch (IOException e) {
                        Log.e("dbBackup:", e.getMessage());
                    }

                }

            } else {
                Toast.makeText(this, "Not create folder", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public class syncData extends AsyncTask<String, String, String> {

        String countryID;
        private Context mContext;

        public syncData(Context mContext, String countryID) {
            this.mContext = mContext;
            this.countryID = countryID;
        }

        @Override
        protected String doInBackground(String... strings) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {

//                  getting Enum Blocks

                    if (listActivityCreated) {
                        model = new SyncModel();
                        model.setstatusID(0);
                        list.add(model);
                    }
                    new GetAllData(mContext, "EnumBlock", syncListAdapter, list).execute(countryID);
                    bi.noItem.setVisibility(View.GONE);

//                  getting Users!!
//                    Toast.makeText(SyncActivity.this, "Sync Users", Toast.LENGTH_SHORT).show();

                    if (listActivityCreated) {
                        model = new SyncModel();
                        model.setstatusID(0);
                        list.add(model);
                    }
                    new GetAllData(mContext, "User", syncListAdapter, list).execute(countryID);

//                   getting BL Random
//                    Toast.makeText(SyncActivity.this, "Sync BL Random", Toast.LENGTH_SHORT).show();
                    if (listActivityCreated) {
                        model = new SyncModel();
                        model.setstatusID(0);
                        list.add(model);
                    }
                    new GetAllData(mContext, "BLRandom", syncListAdapter, list).execute(countryID);

//                    Getting App Version
//                    Toast.makeText(SyncActivity.this, "Sync App Version", Toast.LENGTH_SHORT).show();
                    if (listActivityCreated) {
                        model = new SyncModel();
                        model.setstatusID(0);
                        list.add(model);
                    }
                    new GetAllData(mContext, "VersionApp", syncListAdapter, list).execute();

                    listActivityCreated = false;
                }
            });


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {

//                    populateSpinner(mContext);

                    editor.putBoolean("flag", true);
                    editor.commit();

                    dbBackup();

                }
            }, 1200);
        }
    }

}
