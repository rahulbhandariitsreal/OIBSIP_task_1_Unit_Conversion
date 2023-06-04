package com.example.unitconverterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.unitconverterapplication.databinding.ActivityMainBinding;
import com.example.unitconverterapplication.model.Unit_holder;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;

    private ArrayList<String> volumeUnits = new ArrayList<>();
    private ArrayList<Unit_holder> volume_units_details = new ArrayList<>();

    private ArrayList<String> massUnits = new ArrayList<>();
    private ArrayList<Unit_holder> mass_units_details = new ArrayList<>();


    private ArrayList<String> lengthUnits = new ArrayList<>();

    private ArrayList<Unit_holder> length_units_details = new ArrayList<>();

    private double givenvalue;

    private int selectedmeasure_type;


    private int pos;
    private int fpostion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setlist();

        binding.caculateResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(binding.quant1.getText().toString()) && TextUtils.isEmpty(binding.quant2.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Empty field", Toast.LENGTH_SHORT).show();

                } else {
                    getresult();
                }
            }
        });

        binding.quant1Type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fpostion = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.quant2Type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        binding.mainSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String name = (String) parent.getItemAtPosition(position);
                switch (name) {
                    case "Volume":
                        //volume
                        //lenght
                        //mass
                        ArrayAdapter<String> adapter;
                        adapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, volumeUnits);
                        binding.quant1Type.setAdapter(adapter);
                        binding.quant2Type.setAdapter(adapter);
                        selectedmeasure_type = 0;
                        break;

                    case "Length":

                        selectedmeasure_type = 1;
                        ArrayAdapter<String> ladapter;

                        ladapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, lengthUnits);
                        binding.quant1Type.setAdapter(ladapter);
                        binding.quant2Type.setAdapter(ladapter);


                        break;

                    case "Mass":

                        selectedmeasure_type = 2;
                        ArrayAdapter<String> madapter;
                        madapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, massUnits);
                        binding.quant1Type.setAdapter(madapter);
                        binding.quant2Type.setAdapter(madapter);
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "not selected", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void getresult() {
        double finalquant;

        if(selectedmeasure_type==0){
            givenvalue = Double.parseDouble(binding.quant1.getText().toString());
            givenvalue = volume_units_details.get(fpostion).getSecondary_unit_value() * givenvalue;//converting to metre
            finalquant = givenvalue / volume_units_details.get(pos).getSecondary_unit_value();
            //comvertig to the required result
            binding.quant2.setText("" + finalquant);

        } else if (selectedmeasure_type==1) {

            givenvalue = Double.parseDouble(binding.quant1.getText().toString());
            givenvalue = length_units_details.get(fpostion).getSecondary_unit_value() * givenvalue;//converting to metre
            finalquant = givenvalue / length_units_details.get(pos).getSecondary_unit_value();
            //comvertig to the required result
            binding.quant2.setText("" + finalquant);

        }else{
            givenvalue = Double.parseDouble(binding.quant1.getText().toString());
            givenvalue = mass_units_details.get(fpostion).getSecondary_unit_value() * givenvalue;//converting to metre
            finalquant = givenvalue / mass_units_details.get(pos).getSecondary_unit_value();
            //comvertig to the required result
            binding.quant2.setText("" + finalquant);

        }

    }

    private void setlist() {

        lengthUnits.add("Meter (m)");
        lengthUnits.add("Kilometer (km)");
        lengthUnits.add("Centimeter (cm)");
        lengthUnits.add("Millimeter (mm)");
        lengthUnits.add("Micrometer (μm)");
        lengthUnits.add("Nanometer (nm)");
        lengthUnits.add("Mile (mi)");
        lengthUnits.add("Yard (yd)");
        lengthUnits.add("Foot (ft)");
        lengthUnits.add("Inch (in)");

        length_units_details.add(new Unit_holder("m", 1.0, 1.0));
        length_units_details.add(new Unit_holder("km", 1.0, 1000.0));
        length_units_details.add(new Unit_holder("cm", 1.0, 0.01));
        length_units_details.add(new Unit_holder("mm", 1.0, 0.001));
        length_units_details.add(new Unit_holder("um", 1.0, 0.000001));
        length_units_details.add(new Unit_holder("nm", 1.0, 0.00000001));
        length_units_details.add(new Unit_holder("mi", 1.0, 1609.34));
        length_units_details.add(new Unit_holder("yd", 1.0, 0.9144));
        length_units_details.add(new Unit_holder("ft", 1.0, 0.3048));
        length_units_details.add(new Unit_holder("in", 1.0, 0.0254));


        volumeUnits.add("Cubic meter (m³)");
        volumeUnits.add("Cubic centimeter (cm³)");
        volumeUnits.add("Liter (L)");
        volumeUnits.add("Milliliter (mL)");
        volumeUnits.add("Cubic foot (ft³)");
        volumeUnits.add("Cubic inch (in³)");
        volumeUnits.add("US gallon (gal)");
        volumeUnits.add("US fluid ounce (fl oz)");
        volumeUnits.add("Imperial gallon");
        volumeUnits.add("Imperial fluid ounce");

        volume_units_details.add(new Unit_holder("m3", 1.0, 1.0));
        volume_units_details.add(new Unit_holder("cm3", 1.0, 0.000001));
        volume_units_details.add(new Unit_holder("l", 1.0, 0.001));
        volume_units_details.add(new Unit_holder("ml", 1.0, 0.000001));
        volume_units_details.add(new Unit_holder("ft3", 1.0, 0.0283168));
        volume_units_details.add(new Unit_holder("in3", 1.0, 0.0000163871));
        volume_units_details.add(new Unit_holder("gal", 1.0, 0.00378541));
        volume_units_details.add(new Unit_holder("oz", 1.0, 0.0000295735));
        volume_units_details.add(new Unit_holder("ig", 1.0, 0.00454609));
        volume_units_details.add(new Unit_holder("ifo", 1.0, 0.0000284131));


        massUnits.add("Kilogram (kg)");
        massUnits.add("Gram (g)");
        massUnits.add("Milligram (mg)");
        massUnits.add("Microgram (μg)");
        massUnits.add("Metric ton (t)");
        massUnits.add("Pound (lb)");
        massUnits.add("Ounce (oz)");
        massUnits.add("Stone (st)");
        massUnits.add("Carat (ct)");


        mass_units_details.add(new Unit_holder("kg",1.0,1.0));
        mass_units_details.add(new Unit_holder("g",1.0,0.001));
        mass_units_details.add(new Unit_holder("mg",1.0,0.000001));
        mass_units_details.add(new Unit_holder("ug",1.0,0.000000001));
        mass_units_details.add(new Unit_holder("t",1.0,1000));
        mass_units_details.add(new Unit_holder("lb",1.0,0.453592));
        mass_units_details.add(new Unit_holder("oz",1.0,0.0283495));
        mass_units_details.add(new Unit_holder("st",1.0,6.35029));
        mass_units_details.add(new Unit_holder("ct",1.0,0.0002));



    }
}