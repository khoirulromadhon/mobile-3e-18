package org.aplas.basicappx;

public class Weight {
    private double gram;
    Weight(){
        this.gram= gram;
    }

    public void setGram(double gram) {
        this.gram = gram;
    }
    public void setOunce(double ounce) {
        this.gram = ounce * 28.3495231;
    }
    public void setPound(double pound) {
        this.gram = pound * 453.59237;
    }

    public double getGram() {
        return gram;
    }
    public double getOunce(){
        return gram /28.3495231;
    }
    public double getPound(){
        return gram / 453.59237;
    }
    double convert(String oriUnit, String convUnit,double value){
        if (oriUnit.equals("Grm") && convUnit.equals("Onc")) {
            setGram(value);
            return getOunce();
        } else if (oriUnit.equals("Grm") && convUnit.equals("Pnd")) {
            setGram(value);
            return getPound();
        } else if (oriUnit.equals("Onc") && convUnit.equals("Grm")) {
            setOunce(value);
            return getGram();
        } else if (oriUnit.equals("Onc") && convUnit.equals("Pnd")) {
            setOunce(value);
            return getPound();
        } else if (oriUnit.equals("Pnd") && convUnit.equals("Grm")) {
            setPound(value);
            return getGram();
        } else if (oriUnit.equals("Pnd") && convUnit.equals("Onc")) {
            setPound(value);
            return getOunce();
        } else {
            return value;
        }
    }
}
