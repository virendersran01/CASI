package edu.aku.hassannaqvi.casi_2019.ui.wra.secWRAD4;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.casi_2019.R;
import edu.aku.hassannaqvi.casi_2019.core.DatabaseHelper;
import edu.aku.hassannaqvi.casi_2019.core.MainApp;
import edu.aku.hassannaqvi.casi_2019.databinding.ActivitySectionD4CBinding;
import edu.aku.hassannaqvi.casi_2019.other.JsonUtils;
import edu.aku.hassannaqvi.casi_2019.validation.ValidatorClass;

public class SectionD4CActivity extends AppCompatActivity {

    ActivitySectionD4CBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_d4_c);
        bi.setCallback(this);

        MainApp.dWraType = getIntent().getStringExtra("fType");
    }

    public void BtnContinue() {
        if (formValidation()) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                finish();
                startActivity(new Intent(this, SectionD4DActivity.class)
                        .putExtra("fType", "d4d"));
                MainApp.dwraSerial_no = 0;
            }
        }

    }

    public void BtnAddMore() {
        if (formValidation()) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                SectionD4CActivity.this);
        alertDialogBuilder
                .setMessage("Are you sure to add new Entry?")
                .setCancelable(false)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                finish();
                                if (UpdateDB()) {
                                    startActivity(new Intent(SectionD4CActivity.this, SectionD4CActivity.class)
                                            .putExtra("fType", "d4c"));
                                }
                            }
                        });
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();


    }

    private void SaveDraft() throws JSONException {

        JSONObject sB11 = new JSONObject();
        if (!MainApp.isAttitudeCheck) {
            sB11.put("cid405", bi.cid405a.isChecked() ? "1"
                    : bi.cid405b.isChecked() ? "2"
                    : "0");
            MainApp.mc.setsB11(String.valueOf(sB11));
        } else {
            sB11.put("cid40602", bi.cid40602.getText().toString());
            sB11.put("cid40603", bi.cid40603.getText().toString());
            sB11.put("cid40604", bi.cid40604.getText().toString());
            sB11.put("cid40605", bi.cid40605.getText().toString());
            JSONObject merged = JsonUtils.mergeJSONObjects(new JSONObject(MainApp.mc.getsB11()), sB11);
            MainApp.mc.setsB11(String.valueOf(merged));
        }


    }

    private boolean UpdateDB() {

        DatabaseHelper db = new DatabaseHelper(this);
        long updcount = db.addD4WRA(MainApp.d4WRAc);
        if (updcount != 0) {
            MainApp.d4WRAc.set_ID(String.valueOf(updcount));
            MainApp.d4WRAc.set_UID(
                    (MainApp.d4WRAc.getDeviceId() + MainApp.d4WRAc.get_ID()));
            db.updateDWraID();
            return true;
        } else {
            Toast.makeText(this, "Error in updating DB", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    public void BtnEnd() {
        MainApp.endActivity(this, this);
    }

    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bi.fldGrpSectionD4C);
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }
}
