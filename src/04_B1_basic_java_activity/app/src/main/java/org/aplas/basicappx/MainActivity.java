package org.aplas.basicappx;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    String typeStr;
    double valuee;
    private AlertDialog startDialog;
    private Distance dist = new Distance();
    private Weight weight = new Weight();
    private Temperature temp = new Temperature();
    private Button convertBtn;
    private EditText inputTxt, outputTxt;
    private Spinner unitOri, unitConv;
    private RadioGroup unitType;
    private CheckBox roundBox, formBox;
    private ImageView imgView, imgFormula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        convertBtn = findViewById(R.id.convertButton);
        inputTxt = findViewById(R.id.inputText);
        outputTxt = findViewById(R.id.outputText);
        unitOri = findViewById(R.id.oriList);
        unitConv = findViewById(R.id.convList);
        unitType = findViewById(R.id.radioGroup);
        roundBox = findViewById(R.id.chkRounded);
        formBox = findViewById(R.id.chkFormula);
        imgView = findViewById(R.id.img);
        imgFormula = findViewById(R.id.imgFormula);


        unitType.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        RadioButton selectedRB = findViewById(checkedId);
                        typeStr = selectedRB.getText().toString();
                        switch (typeStr) {
                            case "Temperature":
                                ArrayAdapter<CharSequence> adpTemp = ArrayAdapter.createFromResource(unitType.getContext(),
                                        R.array.tempList, android.R.layout.simple_spinner_item);
                                imgView.setImageResource(R.drawable.temperature);
                                adpTemp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                unitOri.setAdapter(adpTemp);
                                unitConv.setAdapter(adpTemp);
                                inputTxt.setText("0");
                                outputTxt.setText("0");
                                break;
                            case "Distance":
                                ArrayAdapter<CharSequence> adpDist = ArrayAdapter.createFromResource(unitType.getContext(),
                                        R.array.distList, android.R.layout.simple_spinner_item);
                                imgView.setImageResource(R.drawable.distance);
                                adpDist.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                unitOri.setAdapter(adpDist);
                                unitConv.setAdapter(adpDist);
                                inputTxt.setText("0");
                                outputTxt.setText("0");
                                break;
                            case "Weight":
                                ArrayAdapter<CharSequence> adpWeight = ArrayAdapter.createFromResource(unitType.getContext(),
                                        R.array.weightList, android.R.layout.simple_spinner_item);
                                imgView.setImageResource(R.drawable.weight);
                                adpWeight.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                unitOri.setAdapter(adpWeight);
                                unitConv.setAdapter(adpWeight);
                                inputTxt.setText("0");
                                outputTxt.setText("0");
                                break;
                        }
                    }
                });

        convertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doConvert();
            }
        });

        unitOri.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        doConvert();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

        unitConv.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        doConvert();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                }
        );

        roundBox.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        doConvert();
                    }
                }
        );

        formBox.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            imgFormula.setVisibility(View.VISIBLE);
                        } else {
                            imgFormula.setVisibility(View.INVISIBLE);
                        }
                    }
                }
        );
    }

    @Override
    protected void onStart() {
        super.onStart();
        startDialog = new AlertDialog.Builder(MainActivity.this).create();
        startDialog.setTitle("Application started");
        startDialog.setMessage("This app can use to convert any units");
        startDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        startDialog.show();
    }

    protected double convertUnit(String unit, String ori, String conv, double value) {
        if (unit.equals("Temperature") && ori.equals("°F") && conv.equals("K")) {
            temp.setFahrenheit(value);
            return temp.getKelvins();
        } else if (unit.equals("Temperature") && ori.equals("K") && conv.equals("°C")) {
            temp.setKelvins(value);
            return temp.getCelcius();
        } else if (unit.equals("Distance") && ori.equals("Mtr") && conv.equals("Mil")) {
            dist.setMeter(value);
            return dist.getMile();
        } else if (unit.equals("Distance") && ori.equals("Mil") && conv.equals("Ft")) {
            dist.setMile(value);
            return dist.getFoot();
        } else if (unit.equals("Distance") && ori.equals("Inc") && conv.equals("Mtr")) {
            dist.setInch(value);
            return dist.getMeter();
        } else if (unit.equals("Weight") && ori.equals("Grm") && conv.equals("Pnd")) {
            weight.setGram(value);
            return weight.getPound();
        } else if (unit.equals("Weight") && ori.equals("Pnd") && conv.equals("Onc")) {
            weight.setPound(value);
            return weight.getOunce();
        }
        return value;
    }

    protected String strResult(double val, boolean rounded) {
        if (rounded) {
            DecimalFormat df = new DecimalFormat("#.##");
            return df.format(val);
        } else {
            DecimalFormat dff = new DecimalFormat("#.#####");
            return dff.format(val);
        }
    }

    void doConvert() {
        RadioButton selectedRB = findViewById(unitType.getCheckedRadioButtonId());
        typeStr = selectedRB.getText().toString();
        String ori = unitOri.getSelectedItem().toString();
        String conv = unitConv.getSelectedItem().toString();
        double input = Double.parseDouble(inputTxt.getText().toString());
        valuee = convertUnit(typeStr, ori, conv, input);
        outputTxt.setText(strResult(valuee, roundBox.isChecked()));
    }
}
