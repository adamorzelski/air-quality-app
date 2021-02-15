package com.example.air_quality_app.model.sensor;

public class Param{
    public String paramName;
    public String paramFormula;
    public String paramCode;
    public int idParam;

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamFormula() {
        return paramFormula;
    }

    public void setParamFormula(String paramFormula) {
        this.paramFormula = paramFormula;
    }

    public String getParamCode() {
        return paramCode;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode;
    }

    public int getIdParam() {
        return idParam;
    }

    public void setIdParam(int idParam) {
        this.idParam = idParam;
    }

    @Override
    public String toString() {
        return "Param{" +
                "paramName='" + paramName + '\'' +
                ", paramFormula='" + paramFormula + '\'' +
                ", paramCode='" + paramCode + '\'' +
                ", idParam=" + idParam +
                '}';
    }
}
