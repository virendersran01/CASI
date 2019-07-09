package edu.aku.hassannaqvi.casi_2018.ui.household;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

import edu.aku.hassannaqvi.casi_2018.JSONModels.JSONA5ModelClass;
import edu.aku.hassannaqvi.casi_2018.R;
import edu.aku.hassannaqvi.casi_2018.contracts.FormsContract;
import edu.aku.hassannaqvi.casi_2018.core.DatabaseHelper;
import edu.aku.hassannaqvi.casi_2018.core.MainApp;
import edu.aku.hassannaqvi.casi_2018.databinding.ActivitySectionA5Binding;
import edu.aku.hassannaqvi.casi_2018.other.JSONUtilClass;
import edu.aku.hassannaqvi.casi_2018.ui.mainUI.Menu2Activity;
import edu.aku.hassannaqvi.casi_2018.ui.viewMem.ViewMemberActivity;
import edu.aku.hassannaqvi.casi_2018.validation.ClearClass;
import edu.aku.hassannaqvi.casi_2018.validation.ValidatorClass;

public class SectionA5Activity extends Menu2Activity implements TextWatcher, RadioGroup.OnCheckedChangeListener {

    //    static int deceasedCounter = 0;
    private final long DELAY = 1000;
    ActivitySectionA5Binding binding;
    public CheckBox.OnCheckedChangeListener check = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (binding.cih403a.isChecked() || binding.cih403b.isChecked() || binding.cih403c.isChecked()) {
                ClearClass.ClearAllFields(binding.fldGrcih404, false);
                ClearClass.ClearAllFields(binding.fldGrpcih405, false);
            } else {
                ClearClass.ClearAllFields(binding.fldGrcih404, true);
                ClearClass.ClearAllFields(binding.fldGrpcih405, true);
            }
        }
    };
    DatabaseHelper db;
    int recipientCounter = 0;
    int prevRecipientCounter = 0;
    Boolean backPressed = false;
    int prevDeceasedCounter = 0;
    String cih801, cih802;
    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_section_a5);
        db = new DatabaseHelper(this);

//        Assigning data to UI binding
        binding.setCallback(this);

        this.setTitle(getResources().getString(R.string.na5heading));

        binding.cih401.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //formValidation();
                if (!(checkedId == R.id.cih401a)) {

                    ClearClass.ClearAllFields(binding.fldGrpcih402, false);

                } else {
                    formValidation();
                    ClearClass.ClearAllFields(binding.fldGrpcih402, true);
                }
            }
        });

        binding.cih403a.setOnCheckedChangeListener(check);
        binding.cih403b.setOnCheckedChangeListener(check);
        binding.cih403c.setOnCheckedChangeListener(check);

        binding.cih403e.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    binding.cih403a.setEnabled(false);
                    binding.cih403b.setEnabled(false);
                    binding.cih403c.setEnabled(false);
                    binding.cih403d.setEnabled(false);

                    binding.cih403a.setChecked(false);
                    binding.cih403b.setChecked(false);
                    binding.cih403c.setChecked(false);
                    binding.cih403d.setChecked(false);

                } else {


                    binding.cih403a.setEnabled(true);
                    binding.cih403b.setEnabled(true);
                    binding.cih403c.setEnabled(true);
                    binding.cih403d.setEnabled(true);
                }
            }
        });

        binding.cih404.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                formValidation();
                if (checkedId == R.id.cih404b) {
                    ClearClass.ClearAllFields(binding.fldGrpcih405, false);

                } else {
                    ClearClass.ClearAllFields(binding.fldGrpcih405, true);
                }
            }
        });
        binding.cih405e.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    binding.cih405a.setEnabled(false);
                    binding.cih405b.setEnabled(false);
                    binding.cih405c.setEnabled(false);
                    binding.cih405d.setEnabled(false);

                    binding.cih405a.setChecked(false);
                    binding.cih405b.setChecked(false);
                    binding.cih405c.setChecked(false);
                    binding.cih405d.setChecked(false);

                } else {
                    binding.cih405a.setEnabled(true);
                    binding.cih405b.setEnabled(true);
                    binding.cih405c.setEnabled(true);
                    binding.cih405d.setEnabled(true);
                }
            }
        });

        binding.cih501.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                formValidation();
                if (checkedId == R.id.cih501d || checkedId == R.id.cih50196) {
                    ClearClass.ClearAllFields(binding.fldGrcih602, false);
                    ClearClass.ClearAllFields(binding.fldGrnc502, false);

                } else {
                    ClearClass.ClearAllFields(binding.fldGrcih602, true);
                    ClearClass.ClearAllFields(binding.fldGrnc502, true);

                }
            }
        });

//        Listeners
        binding.cih402.setOnCheckedChangeListener(this);
        binding.cih40601.setOnCheckedChangeListener(this);
        binding.cih40602.setOnCheckedChangeListener(this);
        binding.cih40603.setOnCheckedChangeListener(this);
        binding.cih40604.setOnCheckedChangeListener(this);
        binding.cih40605.setOnCheckedChangeListener(this);
        binding.cih40696.setOnCheckedChangeListener(this);
        //binding.cih501.setOnCheckedChangeListener(this);
        binding.cih502.setOnCheckedChangeListener(this);
        binding.cih503.setOnCheckedChangeListener(this);

//        Validation Boolean
        MainApp.validateFlag = false;

        if (SectionA1Activity.editFormFlag) {
            AutoPopulate();
        }
    }

    private void AutoPopulate() {
        FormsContract formContract = db.getsA5();
        if (!formContract.getsA5().equals("")) {

            JSONA5ModelClass jsonA5 = JSONUtilClass.getModelFromJSON(formContract.getsA5(), JSONA5ModelClass.class);

            if (!jsonA5.getcih401().equals("0")) {
                binding.cih401.check(
                        jsonA5.getcih401().equals("1") ? binding.cih401a.getId() :
                                jsonA5.getcih401().equals("2") ? binding.cih401b.getId() :
                                        jsonA5.getcih401().equals("3") ? binding.cih401c.getId() :
                                                binding.cih401d.getId()
                );
            }
            if (!jsonA5.getcih402().equals("0")) {
                binding.cih402.check(
                        jsonA5.getcih402().equals("1") ? binding.cih402a.getId() :
                                binding.cih402b.getId()
                );
            }
            if (!jsonA5.getcih403a().equals("0")) {
                binding.cih403a.setChecked(true);
            }
            if (!jsonA5.getcih403b().equals("0")) {
                binding.cih403b.setChecked(true);
            }
            if (!jsonA5.getcih403c().equals("0")) {
                binding.cih403c.setChecked(true);
            }
            if (!jsonA5.getcih403d().equals("0")) {
                binding.cih403d.setChecked(true);
            }
            if (!jsonA5.getcih403e().equals("0")) {
                binding.cih403e.setChecked(true);
            }

            if (!jsonA5.getcih404().equals("0")) {
                binding.cih404.check(
                        jsonA5.getcih404().equals("1") ? binding.cih404a.getId() :
                                binding.cih404b.getId()
                );
            }
            if (!jsonA5.getcih405a().equals("0")) {
                binding.cih405a.setChecked(true);
            }

            if (!jsonA5.getcih405b().equals("0")) {
                binding.cih405b.setChecked(true);
            }
            if (!jsonA5.getcih405c().equals("0")) {
                binding.cih405c.setChecked(true);
            }
            if (!jsonA5.getcih405d().equals("0")) {
                binding.cih405d.setChecked(true);
            }
            if (!jsonA5.getcih405e().equals("0")) {
                binding.cih405e.setChecked(true);
            }
            if (!jsonA5.getcih40601().equals("0")) {
                binding.cih40601.check(
                        jsonA5.getcih404().equals("1") ? binding.cih40601a.getId() :
                                binding.cih40601b.getId()
                );
            }
            if (!jsonA5.getcih40602().equals("0")) {
                binding.cih40602.check(
                        jsonA5.getcih40602().equals("1") ? binding.cih40602a.getId() :
                                binding.cih40602b.getId()
                );
            }
            if (!jsonA5.getcih40603().equals("0")) {
                binding.cih40603.check(
                        jsonA5.getcih404().equals("1") ? binding.cih40603a.getId() :
                                binding.cih40603b.getId()
                );
            }
            if (!jsonA5.getcih40604().equals("0")) {
                binding.cih40604.check(
                        jsonA5.getcih404().equals("1") ? binding.cih40604a.getId() :
                                binding.cih40604b.getId()
                );
            }
            if (!jsonA5.getcih40605().equals("0")) {
                binding.cih40605.check(
                        jsonA5.getcih404().equals("1") ? binding.cih40605a.getId() :
                                binding.cih40605b.getId()
                );
            }
            if (!jsonA5.getcih40696().equals("0")) {
                binding.cih40696.check(
                        jsonA5.getcih404().equals("1") ? binding.cih40696a.getId() :
                                binding.cih40696b.getId()
                );
            }
            binding.cih40696x.setText(jsonA5.getcih40696x());

            if (!jsonA5.getcih501().equals("0")) {
                binding.cih501.check(
                        jsonA5.getcih501().equals("1") ? binding.cih501a.getId() :
                                jsonA5.getcih501().equals("2") ? binding.cih501b.getId() :
                                        jsonA5.getcih501().equals("3") ? binding.cih501c.getId() :
                                                jsonA5.getcih501().equals("4") ? binding.cih501d.getId() :
                                                        binding.cih50196.getId()
                );
            }
            binding.cih50196x.setText(jsonA5.getcih50196x());
            if (!jsonA5.getcih502().equals("0")) {
                binding.cih502.check(
                        jsonA5.getcih502().equals("1") ? binding.cih502a.getId() :
                                jsonA5.getcih502().equals("2") ? binding.cih502b.getId() :
                                        binding.cih502c.getId()
                );
            }
            if (!jsonA5.getcih503().equals("0")) {
                binding.cih503.check(
                        jsonA5.getcih503().equals("1") ? binding.cih503a.getId() :
                                jsonA5.getcih503().equals("2") ? binding.cih503b.getId() :
                                        jsonA5.getcih503().equals("3") ? binding.cih503c.getId() :
                                                binding.cih503d.getId()
                );
            }

            if (!jsonA5.getcih801().equals("0")) {
                cih801 = jsonA5.getcih801().equals("1") ? "1" :
                        "2";
            }
            if (!jsonA5.getcih802().equals("")) {
                cih802 = jsonA5.getcih802();
            }

        }
    }

    public void BtnContinue() {

//        Validation Boolean
        MainApp.validateFlag = true;

        //Toast.makeText(this, "Processing This Section", Toast.LENGTH_SHORT).show();
        if (formValidation()) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                //Toast.makeText(this, "Starting Ending Section", Toast.LENGTH_SHORT).show();

                if (recipientCounter > 0) {

                    if (recipientCounter < prevRecipientCounter) {

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                SectionA5Activity.this);
                        alertDialogBuilder
                                .setMessage("In previous you saved " + prevRecipientCounter + " Recipient.\n" +
                                        "Do you want to continue it?")
                                .setCancelable(false)
                                .setPositiveButton("Yes",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,
                                                                int id) {

                                                finish();

                                                startActivity(new Intent(SectionA5Activity.this,
                                                        SectionA7Activity.class).putExtra("recCounter", recipientCounter));
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

                    } else {
                        finish();
                        startActivity(new Intent(this, SectionA7Activity.class).putExtra("recCounter", recipientCounter));
                    }
                }
                /*
                else if (deceasedCounter > 0) {
//                    startActivity(new Intent(this, SectionH8Activity.class));

                    if (deceasedCounter < prevDeceasedCounter) {

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                SectionA5Activity.this);
                        alertDialogBuilder
                                .setMessage("In previous you saved " + prevDeceasedCounter + " Deceased.\n" +
                                        "Do you want to continue it?")
                                .setCancelable(false)
                                .setPositiveButton("Yes",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,
                                                                int id) {

                                                deceasedCounter = prevDeceasedCounter;
                                                finish();
//                                                startActivity(new Intent(SectionA5Activity.this, SectionH8Activity.class));
                                                startActivity(new Intent(SectionA5Activity.this, SectionH8infoActivity.class));
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

                    } else {
                        finish();
//                        startActivity(new Intent(SectionA5Activity.this, SectionH8Activity.class));
                        startActivity(new Intent(SectionA5Activity.this, SectionH8infoActivity.class));
                    }

                }
                */
                else {
                    finish();
                    if (SectionA1Activity.editFormFlag) {
                        startActivity(new Intent(this, SectionH8infoActivity.class));

                       /* startActivity(new Intent(this, ViewMemberActivity.class)
                                .putExtra("flagEdit", false)
                                .putExtra("comingBack", true)
                                .putExtra("cluster", MainApp.fc.getClusterNo())
                                .putExtra("hhno", MainApp.fc.getHhNo())
                        );*/
                    } else {
/*
                        if (!MainApp.UpdateSummary(this, db, 1)) {
                            Toast.makeText(this, "Summary Table not update!!", Toast.LENGTH_SHORT).show();
                        }*/

//                        startActivity(new Intent(this, ViewMemberActivity.class).putExtra("activity", 1));
                        startActivity(new Intent(this, SectionH8infoActivity.class));
                    }
                }

            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back.", Toast.LENGTH_SHORT).show();
    }

    public boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, binding.fldGrpsectionA5);
    }

    public void BtnEnd() {
        if (SectionA1Activity.editFormFlag) {
            startActivity(new Intent(this, ViewMemberActivity.class)
                    .putExtra("flagEdit", false)
                    .putExtra("comingBack", true)
                    .putExtra("cluster", MainApp.fc.getClusterNo())
                    .putExtra("hhno", MainApp.fc.getHhNo())
            );
        } else {
            MainApp.endActivity(this, this);
        }

    }


    private void SaveDraft() throws JSONException {
        //Toast.makeText(this, "Saving Draft for  This Section", Toast.LENGTH_SHORT).show();

        JSONObject sA5 = new JSONObject();
        if (SectionA1Activity.editFormFlag) {
            sA5.put("edit_updatedate_sa5", new SimpleDateFormat("dd-MM-yyyy HH:mm").format(System.currentTimeMillis()));

        }
        sA5.put("cih401", binding.cih401a.isChecked() ? "1"
                : binding.cih401b.isChecked() ? "2"
                : binding.cih401c.isChecked() ? "3"
                : binding.cih401d.isChecked() ? "4"
                : "0");

        sA5.put("cih402", binding.cih402a.isChecked() ? "1"
                : binding.cih402b.isChecked() ? "2"
                : "0");

        sA5.put("cih403a", binding.cih403a.isChecked() ? "1" : "0");
        sA5.put("cih403b", binding.cih403b.isChecked() ? "2" : "0");
        sA5.put("cih403c", binding.cih403c.isChecked() ? "3" : "0");
        sA5.put("cih403d", binding.cih403d.isChecked() ? "4" : "0");
        sA5.put("cih403e", binding.cih403e.isChecked() ? "5" : "0");

        sA5.put("cih404", binding.cih404a.isChecked() ? "1"
                : binding.cih404b.isChecked() ? "2"
                : "0");

        sA5.put("cih405a", binding.cih405a.isChecked() ? "1" : "0");
        sA5.put("cih405b", binding.cih405b.isChecked() ? "2" : "0");
        sA5.put("cih405c", binding.cih405c.isChecked() ? "3" : "0");
        sA5.put("cih405d", binding.cih405d.isChecked() ? "4" : "0");
        sA5.put("cih405e", binding.cih405e.isChecked() ? "5" : "0");


        sA5.put("cih40601", binding.cih40601a.isChecked() ? "1"
                : binding.cih40601b.isChecked() ? "2"
                : "0");


        sA5.put("cih40602", binding.cih40602a.isChecked() ? "1"
                : binding.cih40602b.isChecked() ? "2"
                : "0");

        sA5.put("cih40603", binding.cih40603a.isChecked() ? "1"
                : binding.cih40603b.isChecked() ? "2"
                : "0");

        sA5.put("cih40604", binding.cih40604a.isChecked() ? "1"
                : binding.cih40604b.isChecked() ? "2"
                : "0");

        sA5.put("cih40605", binding.cih40605a.isChecked() ? "1"
                : binding.cih40605b.isChecked() ? "2"
                : "0");

        sA5.put("cih40696", binding.cih40696a.isChecked() ? "1"
                : binding.cih40696b.isChecked() ? "2"
                : "0");

        sA5.put("cih40696x", binding.cih40696x.getText().toString());


        // Section A6

        sA5.put("cih501", binding.cih501a.isChecked() ? "1"
                : binding.cih501b.isChecked() ? "2"
                : binding.cih501c.isChecked() ? "3"
                : binding.cih501d.isChecked() ? "4"
                : binding.cih50196.isChecked() ? "96"
                : "0");

        sA5.put("cih50196x", binding.cih50196x.getText().toString());

        sA5.put("cih502", binding.cih502a.isChecked() ? "1"
                : binding.cih502b.isChecked() ? "2"
                : binding.cih502c.isChecked() ? "3"
                : "0");

        sA5.put("cih503", binding.cih503a.isChecked() ? "1"
                : binding.cih503b.isChecked() ? "2"
                : binding.cih503c.isChecked() ? "3"
                : binding.cih503d.isChecked() ? "4"
                : "0");

        //Section A6
        sA5.put("cih601", binding.cih601a.isChecked() ? "1"
                : binding.cih601b.isChecked() ? "2"
                : "0");
        sA5.put("cih602", binding.cih602a.isChecked() ? "1"
                : binding.cih602b.isChecked() ? "2"
                : binding.cih602c.isChecked() ? "3"
                : "0");
        sA5.put("cih603", binding.cih603a.isChecked() ? "1"
                : binding.cih603b.isChecked() ? "2"
                : "0");
        sA5.put("cih604", binding.cih604a.isChecked() ? "1"
                : binding.cih604b.isChecked() ? "2"
                : binding.cih604c.isChecked() ? "3"
                : "0");
        sA5.put("cih605", binding.cih605a.isChecked() ? "1"
                : binding.cih605b.isChecked() ? "2"
                : "0");
        sA5.put("cih606", binding.cih606a.isChecked() ? "1"
                : binding.cih606b.isChecked() ? "2"
                : binding.cih606c.isChecked() ? "3"
                : "0");

        sA5.put("cih607", binding.cih607a.isChecked() ? "1"
                : binding.cih607b.isChecked() ? "2"
                : "0");

        sA5.put("cih608", binding.cih608a.isChecked() ? "1"
                : binding.cih608b.isChecked() ? "2"
                : binding.cih608c.isChecked() ? "3"
                : "0");


        sA5.put("cih609", binding.cih609a.isChecked() ? "1"
                : binding.cih609b.isChecked() ? "2"
                : "0");

        sA5.put("cih610", binding.cih610a.isChecked() ? "1"
                : binding.cih610b.isChecked() ? "2"
                : binding.cih610c.isChecked() ? "3"
                : "0");
        sA5.put("cih611", binding.cih611a.isChecked() ? "1"
                : binding.cih611b.isChecked() ? "2"
                : "0");
        sA5.put("cih612", binding.cih612a.isChecked() ? "1"
                : binding.cih612b.isChecked() ? "2"
                : binding.cih612c.isChecked() ? "3"
                : "0");
        sA5.put("cih613", binding.cih613a.isChecked() ? "1"
                : binding.cih613b.isChecked() ? "2"
                : "0");
        sA5.put("cih614", binding.cih614a.isChecked() ? "1"
                : binding.cih614b.isChecked() ? "2"
                : binding.cih614c.isChecked() ? "3"
                : "0");
        sA5.put("cih615", binding.cih615a.isChecked() ? "1"
                : binding.cih615b.isChecked() ? "2"
                : "0");

        sA5.put("cih616", binding.cih616a.isChecked() ? "1"
                : binding.cih616b.isChecked() ? "2"
                : binding.cih616c.isChecked() ? "2"
                : "0");
        sA5.put("cih617", binding.cih617a.isChecked() ? "1"
                : binding.cih617b.isChecked() ? "2"
                : "0");
        sA5.put("cih618", binding.cih618a.isChecked() ? "1"
                : binding.cih618b.isChecked() ? "2"
                : binding.cih618c.isChecked() ? "3"
                : "0");

        if (SectionA1Activity.editFormFlag) {
            sA5.put("cih801", cih801);
            sA5.put("cih802", cih802);
        }

        MainApp.fc.setsA5(String.valueOf(sA5));

        // Set summary fields
        MainApp.sumc = MainApp.AddSummary(MainApp.fc, 1);

        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();

    }

    private boolean UpdateDB() {

        //Long rowId;
        DatabaseHelper db = new DatabaseHelper(this);

        int updcount = db.updateSA5();

        if (updcount == 1) {
            //Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        timer.cancel();
        timer = new Timer();
        timer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {

                        runOnUiThread(new Runnable() {
                            public void run() {
                                formValidation();
                            }
                        });

                    }
                },
                DELAY
        );
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        formValidation();
    }


}
