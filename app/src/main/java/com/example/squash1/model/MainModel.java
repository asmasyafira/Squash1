package com.example.squash1.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MainModel implements Parcelable {
    private String name;
    private String photo;
    private String desc;

    public MainModel() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static Creator<MainModel> getCREATOR() {
        return CREATOR;
    }

    public MainModel(Parcel in) {
        name = in.readString();
        photo = in.readString();
        desc = in.readString();

    }

    public static final Creator<MainModel> CREATOR = new Creator<MainModel>() {
        @Override
        public MainModel createFromParcel(Parcel in) {
            return new MainModel(in);
        }

        @Override
        public MainModel[] newArray(int size) {
            return new MainModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(desc);
        dest.writeString(photo);
    }
}