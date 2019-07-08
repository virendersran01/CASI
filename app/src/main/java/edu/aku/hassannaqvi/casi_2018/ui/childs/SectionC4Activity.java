package edu.aku.hassannaqvi.casi_2018.ui.childs;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Timer;

import edu.aku.hassannaqvi.casi_2018.R;
import edu.aku.hassannaqvi.casi_2018.contracts.FamilyMembersContract;
import edu.aku.hassannaqvi.casi_2018.core.DatabaseHelper;
import edu.aku.hassannaqvi.casi_2018.core.MainApp;
import edu.aku.hassannaqvi.casi_2018.databinding.ActivitySectionC4Binding;
import edu.aku.hassannaqvi.casi_2018.ui.mainUI.Menu2Activity;
import edu.aku.hassannaqvi.casi_2018.ui.viewMem.ViewMemberActivity;
import edu.aku.hassannaqvi.casi_2018.validation.ClearClass;
import edu.aku.hassannaqvi.casi_2018.validation.ValidatorClass;

public class SectionC4Activity extends Menu2Activity {

    private final long DELAY = 1000;
    ActivitySectionC4Binding binding;
    DatabaseHelper db;
    FamilyMembersContract selectedChild;
    Boolean backPressed = false;
    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_c4);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_section_c4);
        db = new DatabaseHelper(this);

        this.setTitle(getResources().getString(R.string.nc4heading));

//        if (SectionC1Activity.editChildFlag) {
//            binding.textName.setText(SectionC1Activity.selectedChildName + " : " + getString(R.string.childname)
//                    + "\n\n" + SectionC1Activity.editMotherName + " : " + getString(R.string.nh212a));
//        } else {
//            if (!SectionC1Activity.isNA) {
//                binding.textName.setText(SectionC1Activity.selectedChildName + " : " + getString(R.string.childname)
//                        + "\n\n" + SectionB1Activity.wraName + " : " + getString(R.string.nh212a));
//            } else {
//                binding.textName.setText(SectionC1Activity.selectedChildName + " : " + getString(R.string.childname)
//                        + "\n\n" + SectionC1Activity.careTaker + " : " + getString(R.string.nh113));
//            }
//        }
//
//        binding.txtnc401.setText(binding.txtnc401.getText().toString().replace("Name", SectionC1Activity.selectedChildName));
//        binding.txtnc402.setText(binding.txtnc402.getText().toString().replace("Name", SectionC1Activity.selectedChildName));
//        binding.txtnc403.setText(binding.txtnc403.getText().toString().replace("Name", SectionC1Activity.selectedChildName));
//        binding.txtnc404.setText(binding.txtnc404.getText().toString().replace("Name", SectionC1Activity.selectedChildName));
//        binding.txtnc405.setText(binding.txtnc405.getText().toString().replace("Name", SectionC1Activity.selectedChildName));
//        binding.txtnc406.setText(binding.txtnc406.getText().toString().replace("Name", SectionC1Activity.selectedChildName));
//        binding.txtnc407.setText(binding.txtnc407.getText().toString().replace("Name", SectionC1Activity.selectedChildName));
//        binding.txtnc408.setText(binding.txtnc408.getText().toString().replace("Name", SectionC1Activity.selectedChildName));
//        binding.txtnc409.setText(binding.txtnc409.getText().toString().replace("Name", SectionC1Activity.selectedChildName));
//        binding.txtnc410.setText(binding.txtnc410.getText().toString().replace("Name", SectionC1Activity.selectedChildName));
//        binding.txtnc411.setText(binding.txtnc411.getText().toString().replace("Name", SectionC1Activity.selectedChildName));
//        binding.txtnc412.setText(binding.txtnc412.getText().toString().replace("Name", SectionC1Activity.selectedChildName));
//        binding.txtnc413.setText(binding.txtnc413.getText().toString().replace("Name", SectionC1Activity.selectedChildName));
//        binding.txtnc414.setText(binding.txtnc414.getText().toString().replace("Name", SectionC1Activity.selectedChildName));
//        binding.txtnc415.setText(binding.txtnc415.getText().toString().replace("Name", SectionC1Activity.selectedChildName));
//        binding.txtnc416.setText(binding.txtnc416.getText().toString().replace("Name", SectionC1Activity.selectedChildName));
//        binding.txtnc417.setText(binding.txtnc417.getText().toString().replace("Name", SectionC1Activity.selectedChildName));
//        binding.txtnc418.setText(binding.txtnc418.getText().toString().replace("Name", SectionC1Activity.selectedChildName));
//        binding.txtnc419.setText(binding.txtnc419.getText().toString().replace("Name", SectionC1Activity.selectedChildName));
//        binding.txtnc420.setText(binding.txtnc420.getText().toString().replace("Name", SectionC1Activity.selectedChildName));
//
//
//        //        Assigning data to UI binding
        binding.setCallback(this);


    }

    private void setupViews() {
        binding.nc401.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                formValidation();
                if (!binding.nc401a.isChecked()) {
                    ClearClass.ClearAllFields(binding.fldGrnc402, false);
                } else {
                    ClearClass.ClearAllFields(binding.fldGrnc402, true);

                }
            }
        });
        binding.nc402.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                formValidation();
                if (!binding.nc402a.isChecked()) {
                    ClearClass.ClearAllFields(binding.fldGrpnc403, false);
                    ClearClass.ClearAllFields(binding.fldGrnc404, true);
                } else {
                    ClearClass.ClearAllFields(binding.fldGrpnc403, true);
                    ClearClass.ClearAllFields(binding.fldGrnc404, false);

                }
            }
        });

        binding.nc406.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                formValidation();
                if (binding.nc406a.isChecked()) {
                    ClearClass.ClearAllFields(binding.fldGrpnc406, true);
                } else {
                    ClearClass.ClearAllFields(binding.fldGrpnc406, false);

                }
            }
        });
        binding.nc407.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                formValidation();
                if (binding.nc407a.isChecked()) {
                    ClearClass.ClearAllFields(binding.fldGrpnc408, false);
                    ClearClass.ClearAllFields(binding.fldGrpnc409, true);
                } else {
                    ClearClass.ClearAllFields(binding.fldGrpnc408, true);
                    ClearClass.ClearAllFields(binding.fldGrpnc409, false);

                }
            }
        });

        binding.nc411.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                formValidation();
                if (binding.nc411b.isChecked()) {
                    ClearClass.ClearAllFields(binding.fldGrpnc412, false);
                } else {
                    ClearClass.ClearAllFields(binding.fldGrpnc412, true);

                }
            }
        });
        binding.nc412.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                formValidation();
                if (binding.nc412a.isChecked()) {
                    ClearClass.ClearAllFields(binding.fldGrpnc413, false);
                    ClearClass.ClearAllFields(binding.fldGrpnc14, true);
                } else {
                    ClearClass.ClearAllFields(binding.fldGrpnc413, true);
                    ClearClass.ClearAllFields(binding.fldGrpnc14, false);

                }
            }
        });


        //Get Intent
        selectedChild = (FamilyMembersContract) getIntent().getSerializableExtra("selectedChild");

//        binding.nc403.setOnCheckedChangeListener(this);
//        binding.nc404a.setOnCheckedChangeListener(this);
//        binding.nc407.setOnCheckedChangeListener(this);
//        binding.nc408b.setOnCheckedChangeListener(this);
//        binding.nc411.setOnCheckedChangeListener(this);
//        binding.nc412b.setOnCheckedChangeListener(this);
//        binding.nc413.setOnCheckedChangeListener(this);
//        binding.nc414.setOnCheckedChangeListener(this);
//        //binding.nc415.setOnCheckedChangeListener(this);
//        binding.nc416.addTextChangedListener(this);
//        binding.nc417.setOnCheckedChangeListener(this);
//        //binding.nc418.setOnCheckedChangeListener(this);
//        binding.nc419.setOnCheckedChangeListener(this);
//        binding.nc420m.addTextChangedListener(this);
//        binding.nc420d.addTextChangedListener(this);

//        Validation Boolean
        MainApp.validateFlag = false;

//        autoPopulateFields();

    }

//    private void autoPopulateFields() {
//        ChildContract childContract = db.getsC4();
//
//        if (!childContract.getsC4().equals("")) {
//
//            JSONC4ModelClass jsonC4 = JSONUtilClass.getModelFromJSON(childContract.getsC4(), JSONC4ModelClass.class);
//
//            if (!jsonC4.getnc401().equals("0")) {
//                binding.nc401.check(
//                        jsonC4.getnc401().equals("1") ? binding.nc401a.getId()
//                                : binding.nc401b.getId());
//            }
//            if (!jsonC4.getnc402().equals("0")) {
//                binding.nc402.check(
//                        jsonC4.getnc402().equals("1") ? binding.nc402a.getId()
//                                : binding.nc402b.getId());
//            }
//            if (!jsonC4.getnc403().equals("0")) {
//                binding.nc403.check(
//                        jsonC4.getnc403().equals("1") ? binding.nc403a.getId()
//                                : jsonC4.getnc403().equals("2") ? binding.nc403b.getId()
//                                : jsonC4.getnc403().equals("3") ? binding.nc403c.getId()
//                                : jsonC4.getnc403().equals("4") ? binding.nc403d.getId()
//                                : jsonC4.getnc403().equals("5") ? binding.nc403e.getId()
//                                : jsonC4.getnc403().equals("961") ? binding.nc40396.getId()
//                );
//            }
//            binding.nc40396x.setText(jsonC4.getnc4039601x());
//
//
//            if (!jsonC4.getnc404a().equals("0")) {
//                binding.nc404a.setChecked(true);
//            }
//            if (!jsonC4.getnc404b().equals("0")) {
//                binding.nc404b.setChecked(true);
//            }
//            if (!jsonC4.getnc404c().equals("0")) {
//                binding.nc404c.setChecked(true);
//            }
//            if (!jsonC4.getnc404d().equals("0")) {
//                binding.nc404d.setChecked(true);
//            }
//            if (!jsonC4.getnc404e().equals("0")) {
//                binding.nc404e.setChecked(true);
//            }
//            if (!jsonC4.getnc404f().equals("0")) {
//                binding.nc404f.setChecked(true);
//            }
//            if (!jsonC4.getnc404g().equals("0")) {
//                binding.nc404g.setChecked(true);
//            }
//            if (!jsonC4.getnc404h().equals("0")) {
//                binding.nc404h.setChecked(true);
//            }
//            if (!jsonC4.getnc404i().equals("0")) {
//                binding.nc404i.setChecked(true);
//            }
//            if (!jsonC4.getnc404j().equals("0")) {
//                binding.nc404j.setChecked(true);
//            }
//            if (!jsonC4.getNc404k().equals("0")) {
//                binding.nc404k.setChecked(true);
//            }
//            if (!jsonC4.getNc404l().equals("0")) {
//                binding.nc404l.setChecked(true);
//            }
//            if (!jsonC4.getNc404m().equals("0")) {
//                binding.nc404m.setChecked(true);
//            }
//            if (!jsonC4.getnc40496().equals("0")) {
//                binding.nc4049601.setChecked(true);
//            }
//            if (!jsonC4.getNc4049602().equals("0")) {
//                binding.nc4049602.setChecked(true);
//            }
//            if (!jsonC4.getNc4049603().equals("0")) {
//                binding.nc4049603.setChecked(true);
//            }
//
//            binding.nc4049601x.setText(jsonC4.getnc40496x());
//            binding.nc4049602x.setText(jsonC4.getnc40496x());
//            binding.nc4049603x.setText(jsonC4.getnc40496x());
//
//
////            C405
//
//
//            if (!jsonC4.getnc405().equals("0")) {
//                binding.nc405.check(
//                        jsonC4.getnc405().equals("1") ? binding.nc405a.getId()
//                                : binding.nc405b.getId());
//            }
//            if (!jsonC4.getnc406().equals("0")) {
//                binding.nc406.check(
//                        jsonC4.getnc406().equals("1") ? binding.nc406a.getId()
//                                : binding.nc406b.getId());
//            }
//            if (!jsonC4.getnc407().equals("0")) {
//                binding.nc407.check(
//                        jsonC4.getnc407().equals("1") ? binding.nc407a.getId()
//                                : jsonC4.getnc407().equals("2") ? binding.nc407b.getId()
//                                : jsonC4.getnc407().equals("3") ? binding.nc407c.getId()
//                                : jsonC4.getnc407().equals("4") ? binding.nc407d.getId()
//                                : jsonC4.getnc407().equals("5") ? binding.nc407e.getId()
//                                : jsonC4.getnc407().equals("961") ? binding.nc4079601.getId()
//                                : jsonC4.getnc407().equals("6") ? binding.nc407f.getId()
//                                : jsonC4.getnc407().equals("7") ? binding.nc407g.getId()
//                                : jsonC4.getnc407().equals("8") ? binding.nc407h.getId()
//                                : jsonC4.getnc407().equals("9") ? binding.nc407i.getId()
//                                : jsonC4.getnc407().equals("10") ? binding.nc407j.getId()
//                                : jsonC4.getnc407().equals("962") ? binding.nc4079602.getId()
//                                : jsonC4.getnc407().equals("11") ? binding.nc407k.getId()
//                                : jsonC4.getnc407().equals("12") ? binding.nc407l.getId()
//                                : jsonC4.getnc407().equals("13") ? binding.nc407m.getId()
//                                : binding.nc4079603.getId());
//            }
//            binding.nc4079601x.setText(jsonC4.getnc4079601x());
//            binding.nc4079602x.setText(jsonC4.getnc4079602x());
//            binding.nc4079603x.setText(jsonC4.getnc4079603x());
//
//
//            if (!jsonC4.getnc408b().equals("0")) {
//                binding.nc408b.setChecked(true);
//            }
//
//            if (!jsonC4.getnc408e().equals("0")) {
//                binding.nc408e.setChecked(true);
//            }
//            if (!jsonC4.getnc408f().equals("0")) {
//                binding.nc408f.setChecked(true);
//            }
//            if (!jsonC4.getnc408g().equals("0")) {
//                binding.nc408g.setChecked(true);
//            }
//            if (!jsonC4.getnc408h().equals("0")) {
//                binding.nc408h.setChecked(true);
//            }
//            if (!jsonC4.getnc408i().equals("0")) {
//                binding.nc408i.setChecked(true);
//            }
//            if (!jsonC4.getnc408j().equals("0")) {
//                binding.nc408j.setChecked(true);
//            }
//            if (!jsonC4.getnc4089601().equals("0")) {
//                binding.nc4089601.setChecked(true);
//            }
//
//            binding.nc4089601x.setText(jsonC4.getnc40496x());
////            C409
//            if (!jsonC4.getnc409().equals("0")) {
//                binding.nc409.check(
//                        jsonC4.getnc409().equals("1") ? binding.nc409a.getId()
//                                : binding.nc409b.getId());
//            }
//            if (!jsonC4.getnc410().equals("0")) {
//                binding.nc410.check(
//                        jsonC4.getnc410().equals("1") ? binding.nc410a.getId()
//                                : binding.nc410b.getId());
//            }
//            if (!jsonC4.getnc411().equals("0")) {
//                binding.nc411.check(
//                        jsonC4.getnc411().equals("1") ? binding.nc411a.getId()
//                                : jsonC4.getnc411().equals("2") ? binding.nc411b.getId()
//                                : jsonC4.getnc411().equals("3") ? binding.nc411c.getId()
//                                : jsonC4.getnc411().equals("4") ? binding.nc411d.getId()
//                                : jsonC4.getnc411().equals("5") ? binding.nc411e.getId()
//                                : jsonC4.getnc411().equals("961") ? binding.nc4119601.getId()
//                                : jsonC4.getnc411().equals("6") ? binding.nc411f.getId()
//                                : jsonC4.getnc411().equals("7") ? binding.nc411g.getId()
//                                : jsonC4.getnc411().equals("8") ? binding.nc411h.getId()
//                                : jsonC4.getnc411().equals("9") ? binding.nc411i.getId()
//                                : jsonC4.getnc411().equals("10") ? binding.nc411j.getId()
//                                : jsonC4.getnc411().equals("962") ? binding.nc4119602.getId()
//                                : jsonC4.getnc411().equals("11") ? binding.nc411k.getId()
//                                : jsonC4.getnc411().equals("12") ? binding.nc411l.getId()
//                                : jsonC4.getnc411().equals("13") ? binding.nc411m.getId()
//                                : binding.nc4119603.getId());
//            }
//            binding.nc4119601x.setText(jsonC4.getnc4119601x());
//            binding.nc4119602x.setText(jsonC4.getnc4119602x());
//            binding.nc4119603x.setText(jsonC4.getnc4119603x());
//
//            if (!jsonC4.getnc412a().equals("0")) {
//                binding.nc412b.setChecked(true);
//            }
//            if (!jsonC4.getnc412b().equals("0")) {
//                binding.nc412c.setChecked(true);
//            }
//            if (!jsonC4.getnc412c().equals("0")) {
//                binding.nc412e.setChecked(true);
//            }
//            if (!jsonC4.getnc412d().equals("0")) {
//                binding.nc412f.setChecked(true);
//            }
//            if (!jsonC4.getnc412e().equals("0")) {
//                binding.nc412g.setChecked(true);
//            }
//            if (!jsonC4.getnc412f().equals("0")) {
//                binding.nc412h.setChecked(true);
//            }
//            if (!jsonC4.getnc412g().equals("0")) {
//                binding.nc412i.setChecked(true);
//            }
//            if (!jsonC4.getnc412h().equals("0")) {
//                binding.nc412j.setChecked(true);
//            }
//
//            if (!jsonC4.getnc4129601().equals("0")) {
//                binding.nc4129601.setChecked(true);
//            }
//
//            binding.nc4129601x.setText(jsonC4.getnc40496x());
//
//            if (!jsonC4.getnc413().equals("0")) {
//                binding.nc413.check(
//                        jsonC4.getnc413().equals("1") ? binding.nc413a.getId()
//                                : jsonC4.getnc413().equals("2") ? binding.nc413b.getId()
//                                : binding.nc41398.getId());
//            }
//            if (!jsonC4.getnc414().equals("0")) {
//                binding.nc414.check(
//                        jsonC4.getnc414().equals("1") ? binding.nc414a.getId()
//                                : jsonC4.getnc414().equals("2") ? binding.nc414b.getId()
//                                : binding.nc41498.getId());
//            }
//            if (!jsonC4.getnc415().equals("0")) {
//                binding.nc415.check(
//                        jsonC4.getnc415().equals("1") ? binding.nc415a.getId()
//                                : jsonC4.getnc415().equals("2") ? binding.nc415b.getId()
//                                : binding.nc41598.getId());
//            }
//            binding.nc416.setText(jsonC4.getnc416());
//
//            if (!jsonC4.getnc417().equals("0")) {
//                binding.nc417.check(
//                        jsonC4.getnc417().equals("1") ? binding.nc417a.getId()
//                                : jsonC4.getnc417().equals("2") ? binding.nc417b.getId()
//                                : jsonC4.getnc417().equals("3") ? binding.nc417c.getId()
//                                : jsonC4.getnc417().equals("4") ? binding.nc417d.getId()
//                                : binding.nc417e.getId());
//            }
//            if (!jsonC4.getnc418().equals("0")) {
//                binding.nc418.check(
//                        jsonC4.getnc418().equals("1") ? binding.nc418a.getId()
//                                : binding.nc418b.getId()
//                );
//            }
//            if (!jsonC4.getnc419().equals("0")) {
//                binding.nc419.check(
//                        jsonC4.getnc419().equals("1") ? binding.nc419a.getId()
//                                : jsonC4.getnc419().equals("2") ? binding.nc419b.getId()
//                                : jsonC4.getnc419().equals("3") ? binding.nc419c.getId()
//                                : jsonC4.getnc419().equals("4") ? binding.nc419d.getId()
//                                : binding.nc419e.getId());
//            }
//
//            binding.nc420m.setText(jsonC4.getnc420m());
//
//            binding.nc420d.setText(jsonC4.getnc420d());
//
//
//        }
//    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back.", Toast.LENGTH_SHORT).show();

        //Toast.makeText(this, "You can't go back.", Toast.LENGTH_SHORT).show();
//        try {
//            SaveDraft();
//            UpdateDB();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

//        super.onBackPressed();


    }


    public void BtnContinue() {

//        Validation Boolean
        MainApp.validateFlag = true;

        //Toast.makeText(this, "Processing This Section", Toast.LENGTH_SHORT).show();
        if (formValidation()) {
//            try {
//                SaveDraft();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
            if (UpdateDB()) {
                backPressed = true;

                if (SectionC1Activity.ageInMontsbyDob > 23 && SectionC1Activity.ageInMontsbyDob < 60) {
                    startActivity(new Intent(this, SectionC5Activity.class)
                            .putExtra("selectedChild", selectedChild));
                } else {

                    if (SectionC1Activity.editChildFlag) {
                        finish();
                        startActivity(new Intent(this, ViewMemberActivity.class)
                                .putExtra("flagEdit", false)
                                .putExtra("comingBack", true)
                                .putExtra("cluster", MainApp.cc.getClusterno())
                                .putExtra("hhno", MainApp.cc.getHhno())
                        );
                    } else {
                        startActivity(new Intent(this, ChildEndingActivity.class)
                                //.putExtra("checkingFlag", false)
                                .putExtra("complete", true));
                    }
                }

            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void BtnEnd() {
        if (SectionC1Activity.editChildFlag) {
            finish();
            startActivity(new Intent(this, ViewMemberActivity.class)
                    .putExtra("flagEdit", false)
                    .putExtra("comingBack", true)
                    .putExtra("cluster", MainApp.cc.getClusterno())
                    .putExtra("hhno", MainApp.cc.getHhno())
            );
        } else {
            MainApp.endChildActivity(this, this, false);
        }
    }

    private boolean formValidation() {

        return ValidatorClass.EmptyCheckingContainer(this, binding.fldGrpSectionC4);
    }


    private void SaveDraft() throws JSONException {
        //Toast.makeText(this, "Saving Draft for  This Section", Toast.LENGTH_SHORT).show();

        JSONObject sC4 = new JSONObject();
        if (backPressed) {
            sC4.put("updatedate_nc4", new SimpleDateFormat("dd-MM-yyyy HH:mm").format(System.currentTimeMillis()));
        }

        if (SectionC1Activity.editChildFlag) {
            sC4.put("edit_updatedate_sc2", new SimpleDateFormat("dd-MM-yyyy HH:mm").format(System.currentTimeMillis()));
        }

//        nc301
        //sC4.put("nc4name", selectedChild.getName());
//        nc302
        //sC4.put("nc402Serial", selectedChild.getSerialNo());

//        nc401
        sC4.put("nc401", binding.nc401a.isChecked() ? "1"
                : binding.nc401b.isChecked() ? "2"
                : "0");

//      nc402
        sC4.put("nc402", binding.nc402a.isChecked() ? "1"
                : binding.nc402b.isChecked() ? "2"
                : "0");


//        nc403
        sC4.put("nc403", binding.nc403a.isChecked() ? "1"
                : binding.nc403b.isChecked() ? "2"
                : binding.nc403c.isChecked() ? "3"
                : binding.nc403d.isChecked() ? "4"
                : binding.nc403e.isChecked() ? "5"
                : binding.nc40396.isChecked() ? "961"
                : "0");

        sC4.put("nc40396x", binding.nc40396x.getText().toString());


//     nc404
        sC4.put("nc404", binding.nc404a.isChecked() ? "1"
                : binding.nc404b.isChecked() ? "2"
                : binding.nc404c.isChecked() ? "3"
                : binding.nc404d.isChecked() ? "4"
                : binding.nc404e.isChecked() ? "5"
                : binding.nc4049601.isChecked() ? "961"
                : binding.nc404g.isChecked() ? "6"
                : binding.nc404h.isChecked() ? "7"
                : binding.nc404i.isChecked() ? "8"
                : binding.nc404j.isChecked() ? "9"
                : binding.nc4049602.isChecked() ? "962"
                : binding.nc404l.isChecked() ? "10"
                : binding.nc404m.isChecked() ? "11"
                : binding.nc4049603.isChecked() ? "963" :
                "0");

        sC4.put("nc4049601x", binding.nc4049601x.getText().toString());
        sC4.put("nc4049602x", binding.nc4049602x.getText().toString());
        sC4.put("nc4049603x", binding.nc4049603x.getText().toString());


//        nc405
        sC4.put("nc405a", binding.nc405a.isChecked() ? "1" : "0");
        sC4.put("nc405b", binding.nc405b.isChecked() ? "2" : "0");
        sC4.put("nc405c", binding.nc405c.isChecked() ? "3" : "0");
        sC4.put("nc405d", binding.nc405d.isChecked() ? "4" : "0");
        sC4.put("nc405e", binding.nc405e.isChecked() ? "5" : "0");
        sC4.put("nc405f", binding.nc405f.isChecked() ? "6" : "0");
        sC4.put("nc405g", binding.nc405g.isChecked() ? "7" : "0");
        sC4.put("nc405h", binding.nc405h.isChecked() ? "8" : "0");
        sC4.put("nc405i", binding.nc405i.isChecked() ? "9" : "0");
        sC4.put("nc405j", binding.nc405j.isChecked() ? "10" : "0");
        sC4.put("nc40596", binding.nc40596.isChecked() ? "96" : "0");
        sC4.put("nc40596x", binding.nc40596x.getText().toString());


//        nc406
        sC4.put("nc406", binding.nc406a.isChecked() ? "1"
                : binding.nc406b.isChecked() ? "2"
                : "0");


//        nc407
        sC4.put("nc407", binding.nc407a.isChecked() ? "1"
                : binding.nc407b.isChecked() ? "2"
                : "0");


//        nc408
        sC4.put("nc408", binding.nc408a.isChecked() ? "1"
                : binding.nc408b.isChecked() ? "2"
                : binding.nc408c.isChecked() ? "3"
                : binding.nc408d.isChecked() ? "4"
                : binding.nc408e.isChecked() ? "5"
                : binding.nc40896.isChecked() ? "96"
                : "0");
        sC4.put("nc40896x", binding.nc40896x.getText().toString());


//        nc409
        sC4.put("nc409", binding.nc409a.isChecked() ? "1"
                : binding.nc409b.isChecked() ? "2"
                : binding.nc409c.isChecked() ? "3"
                : binding.nc409d.isChecked() ? "4"
                : binding.nc409e.isChecked() ? "5"
                : binding.nc4099601.isChecked() ? "961"
                : binding.nc409f.isChecked() ? "6"
                : binding.nc409g.isChecked() ? "7"
                : binding.nc409h.isChecked() ? "8"
                : binding.nc409i.isChecked() ? "9"
                : binding.nc409j.isChecked() ? "10"
                : binding.nc4099602.isChecked() ? "962"
                : binding.nc409k.isChecked() ? "11"
                : binding.nc409l.isChecked() ? "12"
                : binding.nc409m.isChecked() ? "13"
                : binding.nc4099603.isChecked() ? "963" :
                "0");

        sC4.put("nc4099601x", binding.nc4099601x.getText().toString());
        sC4.put("nc4099602x", binding.nc4099602x.getText().toString());
        sC4.put("nc4099603x", binding.nc4099603x.getText().toString());


//          nc410
        sC4.put("nc410a", binding.nc410a.isChecked() ? "1" : "0");
        sC4.put("nc410b", binding.nc410b.isChecked() ? "2" : "0");
        sC4.put("nc410c", binding.nc410c.isChecked() ? "3" : "0");
        sC4.put("nc410d", binding.nc410d.isChecked() ? "4" : "0");
        sC4.put("nc410e", binding.nc410e.isChecked() ? "5" : "0");
        sC4.put("nc410f", binding.nc410f.isChecked() ? "6" : "0");
        sC4.put("nc410g", binding.nc410g.isChecked() ? "7" : "0");
        sC4.put("nc41096", binding.nc41096.isChecked() ? "96" : "0");
        sC4.put("nc41096x", binding.nc41096x.getText().toString());


//        nc411
        sC4.put("nc411", binding.nc411a.isChecked() ? "1"
                : binding.nc411b.isChecked() ? "2"
                : "0");


//       nc412
       sC4.put("nc412", binding.nc412a.isChecked() ? "1"
                : binding.nc412b.isChecked() ? "2"
                : "0");


//        nc413
        sC4.put("nc413", binding.nc413a.isChecked() ? "1"
                : binding.nc413b.isChecked() ? "2"
                : binding.nc413c.isChecked() ? "3"
                : binding.nc413d.isChecked() ? "4"
                : binding.nc413e.isChecked() ? "5"
                : binding.nc41396.isChecked() ? "96"
                : "0");
        sC4.put("nc41396x", binding.nc41396x.getText().toString());


//      nc414
        sC4.put("nc414", binding.nc414a.isChecked() ? "1"
                : binding.nc414b.isChecked() ? "2"
                : binding.nc414c.isChecked() ? "3"
                : binding.nc414d.isChecked() ? "4"
                : binding.nc414e.isChecked() ? "5"
                : binding.nc4149601.isChecked() ? "961"
                : binding.nc414f.isChecked() ? "6"
                : binding.nc414g.isChecked() ? "7"
                : binding.nc414h.isChecked() ? "8"
                : binding.nc414i.isChecked() ? "9"
                : binding.nc414j.isChecked() ? "10"
                : binding.nc4149602.isChecked() ? "962"
                : binding.nc414k.isChecked() ? "11"
                : binding.nc414l.isChecked() ? "12"
                : binding.nc414m.isChecked() ? "13"
                : binding.nc4149603.isChecked() ? "963" :
                "0");

        sC4.put("nc4149601x", binding.nc4149601x.getText().toString());
        sC4.put("nc4149602x", binding.nc4149602x.getText().toString());
        sC4.put("nc4149603x", binding.nc4149603x.getText().toString());


//        nc415
        sC4.put("nc415a", binding.nc415a.isChecked() ? "1" : "0");
        sC4.put("nc415b", binding.nc415b.isChecked() ? "2" : "0");
        sC4.put("nc415c", binding.nc415c.isChecked() ? "3" : "0");
        sC4.put("nc415d", binding.nc415d.isChecked() ? "4" : "0");
        sC4.put("nc415e", binding.nc415e.isChecked() ? "5" : "0");
        sC4.put("nc415f", binding.nc415f.isChecked() ? "6" : "0");
        sC4.put("nc415g", binding.nc415g.isChecked() ? "7" : "0");
        sC4.put("nc415h", binding.nc415g.isChecked() ? "7" : "0");
        sC4.put("nc41596", binding.nc41596.isChecked() ? "96" : "0");
        sC4.put("nc41596x", binding.nc41596x.getText().toString());


//        nc416
        sC4.put("nc416", binding.nc416a.isChecked() ? "1"
                : binding.nc416b.isChecked() ? "2"
                : binding.nc41698.isChecked() ? "98"
                : "0");


//        nc417
        sC4.put("nc417", binding.nc417a.isChecked() ? "1"
                : binding.nc417b.isChecked() ? "2"
                : binding.nc41798.isChecked() ? "98"
                : "0");


//        nc418
        sC4.put("nc418", binding.nc418a.isChecked() ? "1"
                : binding.nc418b.isChecked() ? "2"
                : binding.nc41898.isChecked() ? "98"
                : "0");


//        nc419
        sC4.put("nc419", binding.nc419.getText().toString());


//        nc420
        sC4.put("nc420", binding.nc420a.isChecked() ? "1"
                : binding.nc420b.isChecked() ? "2"
                : binding.nc420c.isChecked() ? "3"
                : binding.nc420d.isChecked() ? "4"
                : binding.nc420e.isChecked() ? "5"
                : "0");

//        nc421
        sC4.put("nc421", binding.nc421a.isChecked() ? "1"
                : binding.nc421b.isChecked() ? "2"
                : "0");

//        nc422
        sC4.put("nc422", binding.nc422a.isChecked() ? "1"
                : binding.nc422b.isChecked() ? "2"
                : binding.nc422c.isChecked() ? "3"
                : binding.nc422d.isChecked() ? "4"
                : binding.nc422e.isChecked() ? "5"
                : "0");

//        nc423m
        sC4.put("nc423m", binding.nc423m.getText().toString());

//       nc423a
        sC4.put("nc423d", binding.nc423d.getText().toString());

        MainApp.cc.setsC4(String.valueOf(sC4));

        //Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();
    }

    private boolean UpdateDB() {

        //Long rowId;
        DatabaseHelper db = new DatabaseHelper(this);

        int updcount = db.updateSC4();

        if (updcount == 1) {
            //Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


}