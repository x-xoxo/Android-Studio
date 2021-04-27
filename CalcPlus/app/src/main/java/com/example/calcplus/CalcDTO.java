package com.example.calcplus;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class CalcDTO implements Parcelable {
    private int num1;
    private int num2;
    private int result;

    public CalcDTO(int num1, int num2, int result) {
        this.num1 = num1;
        this.num2 = num2;
        this.result = result;
    }

    protected CalcDTO(Parcel in) {
        num1 = in.readInt();
        num2 = in.readInt();
        result = in.readInt();
    }

    public static final Creator<CalcDTO> CREATOR = new Creator<CalcDTO>() {
        @Override
        public CalcDTO createFromParcel(Parcel in) {
            return new CalcDTO(in);
        }

        @Override
        public CalcDTO[] newArray(int size) {
            return new CalcDTO[size];
        }
    };

    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @NonNull
    @Override
    public String toString() {
        return ""+num1+" + "+num2+" = "+result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(num1);
        dest.writeInt(num2);
        dest.writeInt(result);
    }
}
