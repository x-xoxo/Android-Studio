package com.example.storeservice;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemDTO implements Parcelable {

    private String name;
    private int amount;
    private int position;

    protected ItemDTO(Parcel in) {
        name = in.readString();
        amount = in.readInt();
        position = in.readInt();
    }

    public static final Creator<ItemDTO> CREATOR = new Creator<ItemDTO>() {
        @Override
        public ItemDTO createFromParcel(Parcel in) {
            return new ItemDTO(in);
        }

        @Override
        public ItemDTO[] newArray(int size) {
            return new ItemDTO[size];
        }
    };

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ItemDTO(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(amount);
        dest.writeInt(position);
    }
}
