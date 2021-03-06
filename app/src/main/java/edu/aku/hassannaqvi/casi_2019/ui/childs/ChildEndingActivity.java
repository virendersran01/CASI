package edu.aku.hassannaqvi.casi_2019.ui.childs;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import edu.aku.hassannaqvi.casi_2019.R;
import edu.aku.hassannaqvi.casi_2019.contracts.FamilyMembersContract;
import edu.aku.hassannaqvi.casi_2019.core.DatabaseHelper;
import edu.aku.hassannaqvi.casi_2019.core.MainApp;
import edu.aku.hassannaqvi.casi_2019.databinding.ActivityChildEndingBinding;
import edu.aku.hassannaqvi.casi_2019.ui.viewMem.ViewMemberActivity;
import edu.aku.hassannaqvi.casi_2019.validation.ValidatorClass;

public class ChildEndingActivity extends AppCompatActivity {

    private static final String TAG = ChildEndingActivity.class.getSimpleName();

    ActivityChildEndingBinding binding;
    Boolean flagMotherChild = false;
    Boolean flagNAChild = false;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_child_ending);
        binding.setCallback(this);

        binding.lblheaderName.setText(SectionC1Activity.selectedChildName.toUpperCase());

        db = new DatabaseHelper(this);

        Boolean check = getIntent().getExtras().getBoolean("complete");

        if (check) {
            binding.istatusa.setEnabled(true);
            binding.istatusb.setEnabled(false);
            binding.istatusc.setEnabled(false);
            binding.istatusd.setEnabled(false);
            binding.istatuse.setEnabled(false);
            binding.istatusf.setEnabled(false);
            binding.istatus96.setEnabled(false);
        } else {

            if (getIntent().getBooleanExtra("childINEligibile", false)) {
                binding.istatusa.setEnabled(false);
                binding.istatusb.setEnabled(false);
                binding.istatusc.setEnabled(false);
                binding.istatusd.setEnabled(false);
                binding.istatuse.setEnabled(false);
                binding.istatusf.setEnabled(true);
                binding.istatus96.setEnabled(false);
            } else {
                binding.istatusa.setEnabled(false);
                binding.istatusb.setEnabled(true);
                binding.istatusc.setEnabled(true);
                binding.istatusd.setEnabled(true);
                binding.istatuse.setEnabled(true);
                binding.istatusf.setEnabled(false);
                binding.istatus96.setEnabled(true);
            }
        }

    }

    public void BtnEnd() {


        if (formValidation()) {
            SaveDraft();
            if (UpdateDB()) {

//                finish();

                SectionC1Activity.childU5.remove(SectionC1Activity.selectedChildName);

                for (FamilyMembersContract fmc : MainApp.childUnder5_Del) {
                    if (MainApp.cc.getC1SerialNo().equals(fmc.getSerialNo())) {
                        MainApp.childUnder5_Del.remove(fmc);
                        break;
                    }
                }

                startActivity(new Intent(this, ViewMemberActivity.class).putExtra("activity", 4));


            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void SaveDraft() {


        MainApp.cc.setCstatus(binding.istatusa.isChecked() ? "1"
                : binding.istatusb.isChecked() ? "2"
                : binding.istatusc.isChecked() ? "3"
                : binding.istatusd.isChecked() ? "4"
                : binding.istatuse.isChecked() ? "5"
                : binding.istatusf.isChecked() ? "6"
                : binding.istatus96.isChecked() ? "96"
                : "0");

        MainApp.cc.setCstatus88x(binding.istatus96x.getText().toString());

        // Set summary fields
        MainApp.sumc = MainApp.AddSummary(MainApp.fc, 3);

        //
    }

    private boolean UpdateDB() {
        int updcount = db.updateChildEnding();
        if (updcount == 1) {

            return MainApp.UpdateSummary(this, db, 3);
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean formValidation() {


        if (!ValidatorClass.EmptyRadioButton(this, binding.istatus, binding.istatusa, getString(R.string.istatus))) {
            return false;
        }

        if (binding.istatus96.isChecked()) {

            if (binding.istatus96x.getText().toString().isEmpty()) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.other), Toast.LENGTH_SHORT).show();
                binding.istatus96x.setError("This data is Required!");    // Set Error on last radio button
                Log.i(TAG, "istatus96x: This data is Required!");
                return false;
            } else {
                binding.istatus96x.setError(null);
            }

        }


        return true;
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back.", Toast.LENGTH_SHORT).show();
    }


}
