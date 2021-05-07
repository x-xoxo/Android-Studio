package com.example.cafeorder;

import android.os.Parcel;
import android.os.Parcelable;

public class OrderDTO implements Parcelable {

    private String menu;
    private int amount;
    private int position;

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public OrderDTO(int position, String menu, int amount) {
        this.position = position;
        this.menu = menu;
        this.amount = amount;
    }

    protected OrderDTO(Parcel in) {
        menu = in.readString();
        amount = in.readInt();
        position = in.readInt();
    }

    public static final Creator<OrderDTO> CREATOR = new Creator<OrderDTO>() {
        @Override
        public OrderDTO createFromParcel(Parcel in) {
            return new OrderDTO(in);
        }

        @Override
        public OrderDTO[] newArray(int size) {
            return new OrderDTO[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(menu);
        dest.writeInt(amount);
        dest.writeInt(position);
    }
}
