package io.example.simplecalc;

import android.os.Parcel;
import android.os.Parcelable;

// import java.io.Serializable;

// 2nd way implements Serializable
// 3rd way implements Parcelable
public class History implements Parcelable {
    private int num1, num2, result;
    private char operator;

    public History(int num1, int num2, int result, char operator) {
        this.num1 = num1;
        this.num2 = num2;
        this.result = result;
        this.operator = operator;
    }

    protected History(Parcel in) {
        num1 = in.readInt();
        num2 = in.readInt();
        result = in.readInt();
        operator = (char) in.readInt();
    }

    public static final Creator<History> CREATOR = new Creator<History>() {
        @Override
        public History createFromParcel(Parcel in) {
            return new History(in);
        }

        @Override
        public History[] newArray(int size) {
            return new History[size];
        }
    };

    @Override
    public String toString() {
        return "" + num1 + " " + operator + " " + num2 + " = " + result;
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
        dest.writeInt((int) operator);
    }
}
