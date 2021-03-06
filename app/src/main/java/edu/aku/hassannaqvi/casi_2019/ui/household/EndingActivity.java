package edu.aku.hassannaqvi.casi_2019.ui.household;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import edu.aku.hassannaqvi.casi_2019.R;
import edu.aku.hassannaqvi.casi_2019.core.DatabaseHelper;
import edu.aku.hassannaqvi.casi_2019.core.MainApp;
import edu.aku.hassannaqvi.casi_2019.databinding.ActivityEndingBinding;
import edu.aku.hassannaqvi.casi_2019.ui.mainUI.AddMember_MenuActivity;
import edu.aku.hassannaqvi.casi_2019.ui.mainUI.MainActivity;
import edu.aku.hassannaqvi.casi_2019.ui.wra.SectionB1Activity;
import edu.aku.hassannaqvi.casi_2019.validation.ValidatorClass;

public class EndingActivity extends AddMember_MenuActivity {

    private static final String TAG = EndingActivity.class.getSimpleName();

    ActivityEndingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_ending);
        binding.setCallback(this);

        Boolean check = getIntent().getExtras().getBoolean("complete");

        if (check) {
            binding.istatusa.setEnabled(true);
            binding.istatusb.setEnabled(false);
            binding.istatusb.setEnabled(false);
            binding.istatusc.setEnabled(false);
            binding.istatusd.setEnabled(false);
            binding.istatuse.setEnabled(false);
            binding.istatusf.setEnabled(false);
            binding.istatusg.setEnabled(false);
            binding.istatus96.setEnabled(false);

            binding.btnAddMember.setVisibility(View.GONE);
        } else {
            binding.istatusa.setEnabled(false);
            binding.istatusb.setEnabled(true);
            binding.istatusc.setEnabled(true);
            binding.istatusd.setEnabled(true);
            binding.istatuse.setEnabled(true);
            binding.istatusf.setEnabled(true);
            binding.istatusg.setEnabled(true);
            binding.istatus96.setEnabled(true);
            binding.btnAddMember.setVisibility(View.GONE);
        }

        binding.istatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (binding.istatus96.isChecked()) {
                    binding.istatus96x.setVisibility(View.GONE);
                    //istatus88x.requestFocus();
                } else {
                    binding.istatus96x.setText(null);
                    binding.istatus96x.setVisibility(View.GONE);
                }
            }
        });

        MainApp.childUnder5 = new ArrayList<>();
        MainApp.childUnder5_Del = new ArrayList<>();
        MainApp.childUnder2Check = new ArrayList<>();
    }

    public void BtnEnd() {


        if (formValidation()) {
            SaveDraft();
            if (UpdateDB()) {

//                finish();

                MainApp.fc = null;
                MainApp.mc = null;
                MainApp.cc = null;
                MainApp.oc = null;
                MainApp.dc = null;
                MainApp.fmc = null;

                SectionB1Activity.WRAcounter = 1;
                SectionB1Activity.WRAsize = 0;

                Intent endSec = new Intent(this, MainActivity.class);
                startActivity(endSec);
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void SaveDraft() {


        MainApp.fc.setIstatus(
                binding.istatusa.isChecked() ? "1"
                        : binding.istatusb.isChecked() ? "2"
                        : binding.istatusc.isChecked() ? "3"
                        : binding.istatusd.isChecked() ? "4"
                        : binding.istatuse.isChecked() ? "5"
                        : binding.istatusf.isChecked() ? "6"
                        : binding.istatusg.isChecked() ? "7"
                        : binding.istatus96.isChecked() ? "96"
                        : "0");

        MainApp.fc.setIstatus88x(binding.istatus96x.getText().toString());
        MainApp.fc.setEndtime(new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime()));


        //
    }

    private boolean UpdateDB() {
        DatabaseHelper db = new DatabaseHelper(this);

        int updcount = db.updateEnding();

        if (updcount == 1) {

            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    private boolean formValidation() {
        return ValidatorClass.EmptyRadioButton(this, binding.istatus, binding.istatus96, binding.istatus96x, getString(R.string.istatus));
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "You Can't go back", Toast.LENGTH_LONG).show();
    }

    public void BtnAddMember() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                EndingActivity.this);
        alertDialogBuilder
                .setMessage("Are you sure to add missing member?")
                .setCancelable(false)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                startActivity(new Intent(EndingActivity.this, SectionA2ListActivity.class)
                                        .putExtra("reBack", true));
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

}
