package edu.aku.hassannaqvi.casi_2019.ui.childs;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;

import edu.aku.hassannaqvi.casi_2019.R;
import edu.aku.hassannaqvi.casi_2019.contracts.FamilyMembersContract;
import edu.aku.hassannaqvi.casi_2019.core.DatabaseHelper;
import edu.aku.hassannaqvi.casi_2019.core.MainApp;
import edu.aku.hassannaqvi.casi_2019.databinding.ActivitySectionC5Binding;
import edu.aku.hassannaqvi.casi_2019.ui.viewMem.ViewMemberActivity;
import edu.aku.hassannaqvi.casi_2019.ui.wra.SectionB1Activity;
import edu.aku.hassannaqvi.casi_2019.validation.ClearClass;
import edu.aku.hassannaqvi.casi_2019.validation.ValidatorClass;

public class SectionC5Activity extends AppCompatActivity {

    ActivitySectionC5Binding bi;

    Boolean backPressed = false;

    FamilyMembersContract selectedChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_c5);
        bi.setCallback(this);

        this.setTitle(R.string.sectionC3);
        //Get Intent
        selectedChild = (FamilyMembersContract) getIntent().getSerializableExtra("selectedChild");

        setUIContent();
    }

    private void setUIContent() {

        bi.cic5030198.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    ClearClass.ClearAllFields(bi.fldGrpcic50301, null);
                    bi.fldGrpcic50301.setVisibility(View.GONE);

                } else {
                    bi.fldGrpcic50301.setVisibility(View.VISIBLE);
                }
            }
        });
        bi.cic5030298.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    ClearClass.ClearAllFields(bi.fldGrpcic50302, null);
                    bi.fldGrpcic50302.setVisibility(View.GONE);

                } else {

                    bi.fldGrpcic50302.setVisibility(View.VISIBLE);
                }
            }
        });
        bi.cic5030398.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    ClearClass.ClearAllFields(bi.fldGrpcic50303, null);
                    bi.fldGrpcic50303.setVisibility(View.GONE);

                } else {
                    bi.fldGrpcic50303.setVisibility(View.VISIBLE);
                }
            }
        });
        bi.cic5030498.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    ClearClass.ClearAllFields(bi.fldGrpcic50304, null);
                    bi.fldGrpcic50304.setVisibility(View.GONE);

                } else {

                    bi.fldGrpcic50304.setVisibility(View.VISIBLE);
                }
            }
        });
        bi.cic5030598.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    ClearClass.ClearAllFields(bi.fldGrpcic50305, null);
                    bi.fldGrpcic50305.setVisibility(View.GONE);

                } else {

                    bi.fldGrpcic50305.setVisibility(View.VISIBLE);
                }
            }
        });
        bi.cic5030698.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    ClearClass.ClearAllFields(bi.fldGrpcic50306, null);
                    bi.fldGrpcic50306.setVisibility(View.GONE);

                } else {

                    bi.fldGrpcic50306.setVisibility(View.VISIBLE);
                }
            }
        });
        bi.cic5030798.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    ClearClass.ClearAllFields(bi.fldGrpcic50307, null);
                    bi.fldGrpcic50307.setVisibility(View.GONE);

                } else {

                    bi.fldGrpcic50307.setVisibility(View.VISIBLE);
                }
            }
        });
        bi.cic5030898.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    ClearClass.ClearAllFields(bi.fldGrpcic50308, null);
                    bi.fldGrpcic50308.setVisibility(View.GONE);

                } else {

                    bi.fldGrpcic50308.setVisibility(View.VISIBLE);
                }
            }
        });


    }

    public void BtnContinue() {

//        Validation Boolean
        MainApp.validateFlag = true;

        if (formValidation()) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {

//                finish();
                backPressed = true;
                startActivity(new Intent(this, SectionC3Activity.class)
                        .putExtra("selectedChild", selectedChild));

            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void BtnEnd() {
        if (SectionB1Activity.editWRAFlag) {
            finish();
            startActivity(new Intent(this, ViewMemberActivity.class)
                    .putExtra("flagEdit", false)
                    .putExtra("comingBack", true)
                    .putExtra("cluster", MainApp.mc.getCluster())
                    .putExtra("hhno", MainApp.mc.getHhno())
            );
        } else {
            MainApp.endActivityMother(this, this, false);
        }
    }

    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bi.fldGrpSectionB6);
    }

    private void SaveDraft() throws JSONException {

        JSONObject sB6 = new JSONObject();

        if (backPressed) {
            sB6.put("updatedate_cic6", new SimpleDateFormat("dd-MM-yyyy HH:mm").format(System.currentTimeMillis()));
        }

        sB6.put("cic601", bi.cic501.getText().toString());
        sB6.put("cic602", bi.cic502a.isChecked() ? "1"
                : bi.cic502b.isChecked() ? "2"
                : bi.cic502c.isChecked() ? "3"
                : "0");

        sB6.put("cic6030101", bi.cic5030101.getText().toString());
        sB6.put("cic6030198", bi.cic5030198.isChecked() ? "98" : "0");
        sB6.put("cic6030102", bi.cic5030102a.isChecked() ? "1"
                : bi.cic5030102b.isChecked() ? "2"
                : bi.cic5030102c.isChecked() ? "3"
                : bi.cic5030102d.isChecked() ? "4"
                : bi.cic5030102e.isChecked() ? "5"
                : bi.cic5030102f.isChecked() ? "6"
                : bi.cic5030102g.isChecked() ? "7"
                : bi.cic5030102h.isChecked() ? "8"
                : bi.cic5030102i.isChecked() ? "9"
                : "0");
        sB6.put("cic6030103", bi.cic5030103a.isChecked() ? "1"
                : bi.cic5030103b.isChecked() ? "2"
                : "0");


        sB6.put("cic6030201", bi.cic5030201.getText().toString());
        sB6.put("cic6030298", bi.cic5030298.isChecked() ? "98" : "0");
        sB6.put("cic6030202", bi.cic5030202a.isChecked() ? "1"
                : bi.cic5030202b.isChecked() ? "2"
                : bi.cic5030202c.isChecked() ? "3"
                : bi.cic5030202d.isChecked() ? "4"
                : bi.cic5030202e.isChecked() ? "5"
                : bi.cic5030202f.isChecked() ? "6"
                : bi.cic5030202g.isChecked() ? "7"
                : bi.cic5030202h.isChecked() ? "8"
                : bi.cic5030202i.isChecked() ? "9"
                : "0");
        sB6.put("cic6030203", bi.cic5030203a.isChecked() ? "1"
                : bi.cic5030203b.isChecked() ? "2"
                : "0");
        sB6.put("cic6030301", bi.cic5030301.getText().toString());
        sB6.put("cic6030398", bi.cic5030398.isChecked() ? "98" : "0");
        sB6.put("cic6030302", bi.cic5030302a.isChecked() ? "1"
                : bi.cic5030302b.isChecked() ? "2"
                : bi.cic5030302c.isChecked() ? "3"
                : bi.cic5030302d.isChecked() ? "4"
                : bi.cic5030302e.isChecked() ? "5"
                : bi.cic5030302f.isChecked() ? "6"
                : bi.cic5030302g.isChecked() ? "7"
                : bi.cic5030302h.isChecked() ? "8"
                : bi.cic5030302i.isChecked() ? "9"
                : "0");
        sB6.put("cic6030303", bi.cic5030303a.isChecked() ? "1"
                : bi.cic5030303b.isChecked() ? "2"
                : "0");


        sB6.put("cic6030401", bi.cic5030401.getText().toString());
        sB6.put("cic6030498", bi.cic5030498.isChecked() ? "98" : "0");
        sB6.put("cic6030402", bi.cic5030402a.isChecked() ? "1"
                : bi.cic5030402b.isChecked() ? "2"
                : bi.cic5030402c.isChecked() ? "3"
                : bi.cic5030402d.isChecked() ? "4"
                : bi.cic5030402e.isChecked() ? "5"
                : bi.cic5030402f.isChecked() ? "6"
                : bi.cic5030402g.isChecked() ? "7"
                : bi.cic5030402h.isChecked() ? "8"
                : bi.cic5030402i.isChecked() ? "9"
                : "0");
        sB6.put("cic6030403", bi.cic5030403a.isChecked() ? "1"
                : bi.cic5030403b.isChecked() ? "2"
                : "0");


        sB6.put("cic6030501", bi.cic5030501.getText().toString());
        sB6.put("cic6030598", bi.cic5030598.isChecked() ? "98" : "0");
        sB6.put("cic6030502", bi.cic5030502a.isChecked() ? "1"
                : bi.cic5030502b.isChecked() ? "2"
                : bi.cic5030502c.isChecked() ? "3"
                : bi.cic5030502d.isChecked() ? "4"
                : bi.cic5030502e.isChecked() ? "5"
                : bi.cic5030502f.isChecked() ? "6"
                : bi.cic5030502g.isChecked() ? "7"
                : bi.cic5030502h.isChecked() ? "8"
                : bi.cic5030502i.isChecked() ? "9"
                : "0");
        sB6.put("cic6030503", bi.cic5030503a.isChecked() ? "1"
                : bi.cic5030503b.isChecked() ? "2"
                : "0");


        sB6.put("cic6030601", bi.cic5030601.getText().toString());
        sB6.put("cic6030698", bi.cic5030698.isChecked() ? "98" : "0");
        sB6.put("cic6030602", bi.cic5030602a.isChecked() ? "1"
                : bi.cic5030602b.isChecked() ? "2"
                : bi.cic5030602c.isChecked() ? "3"
                : bi.cic5030602d.isChecked() ? "4"
                : bi.cic5030602e.isChecked() ? "5"
                : bi.cic5030602f.isChecked() ? "6"
                : bi.cic5030602g.isChecked() ? "7"
                : bi.cic5030602h.isChecked() ? "8"
                : bi.cic5030602i.isChecked() ? "9"
                : "0");
        sB6.put("cic6030603", bi.cic5030603a.isChecked() ? "1"
                : bi.cic5030603b.isChecked() ? "2"
                : "0");

        sB6.put("cic6030701", bi.cic5030701.getText().toString());
        sB6.put("cic6030798", bi.cic5030798.isChecked() ? "98" : "0");
        sB6.put("cic6030702", bi.cic5030702a.isChecked() ? "1"
                : bi.cic5030702b.isChecked() ? "2"
                : bi.cic5030702c.isChecked() ? "3"
                : bi.cic5030702d.isChecked() ? "4"
                : bi.cic5030702e.isChecked() ? "5"
                : bi.cic5030702f.isChecked() ? "6"
                : bi.cic5030702g.isChecked() ? "7"
                : bi.cic5030702h.isChecked() ? "8"
                : bi.cic5030702i.isChecked() ? "9"
                : "0");
        sB6.put("cic6030703", bi.cic5030703a.isChecked() ? "1"
                : bi.cic5030703b.isChecked() ? "2"
                : "0");

        sB6.put("cic6030801", bi.cic5030801.getText().toString());
        sB6.put("cic6030898", bi.cic5030898.isChecked() ? "98" : "0");
        sB6.put("cic6030802", bi.cic5030802a.isChecked() ? "1"
                : bi.cic5030802b.isChecked() ? "2"
                : bi.cic5030802c.isChecked() ? "3"
                : bi.cic5030802d.isChecked() ? "4"
                : bi.cic5030802e.isChecked() ? "5"
                : bi.cic5030802f.isChecked() ? "6"
                : bi.cic5030802g.isChecked() ? "7"
                : bi.cic5030802h.isChecked() ? "8"
                : bi.cic5030802i.isChecked() ? "9"
                : "0");
        sB6.put("cic6030803", bi.cic5030803a.isChecked() ? "1"
                : bi.cic5030803b.isChecked() ? "2"
                : "0");

        MainApp.cc.setsC6(String.valueOf(sB6));

    }

    private boolean UpdateDB() {

        //Long rowId;
        DatabaseHelper db = new DatabaseHelper(this);

        int updcount = db.updateSC6();

        if (updcount == 1) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

}
