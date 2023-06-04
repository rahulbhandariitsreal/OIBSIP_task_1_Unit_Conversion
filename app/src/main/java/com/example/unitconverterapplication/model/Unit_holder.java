package com.example.unitconverterapplication.model;

public class Unit_holder {


    private String unit_name;
    private double main_unit_value;
    private double secondary_unit_value;


    public Unit_holder() {
    }


    public Unit_holder(String unit_name, double main_unit_value, double secondary_unit_value) {
        this.unit_name = unit_name;
        this.main_unit_value = main_unit_value;
        this.secondary_unit_value = secondary_unit_value;
    }

    public String getUnit_name() {

        return unit_name;
    }

    public void setUnit_name(String unit_name) {
        this.unit_name = unit_name;
    }

    public double getMain_unit_value() {
        return main_unit_value;
    }

    public void setMain_unit_value(double main_unit_value) {
        this.main_unit_value = main_unit_value;
    }

    public double getSecondary_unit_value() {
        return secondary_unit_value;
    }

    public void setSecondary_unit_value(double secondary_unit_value) {
        this.secondary_unit_value = secondary_unit_value;
    }
}
