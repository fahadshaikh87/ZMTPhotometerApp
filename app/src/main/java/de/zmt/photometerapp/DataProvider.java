package de.zmt.photometerapp;

/**
 * Created by FAHAD SHAIKH on 7/7/2017.
 */

public class DataProvider {

    private String Sample;
    private String Value;
    private String Unit;

    public DataProvider(String Sample,String Value,String Unit)
    {

        this.Sample=Sample;
        this.Value=Value;
        this.Unit=Unit;

    }

    public String getSample() {
        return Sample;
    }

    public void setSample(String sample) {
        Sample = sample;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }
}
