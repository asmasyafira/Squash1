package com.example.squash1.model;

import android.os.Parcel;
import android.os.Parcelable;

public class HomeModel implements Parcelable {

    private static String name;
    private static String description;
    private String photo;

    public static String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.photo);
    }

    public HomeModel() {
    }

    protected HomeModel(Parcel in) {
        this.name = in.readString();
        this.description = in.readString();
        this.photo = in.readString();
    }

    public static final Parcelable.Creator<HomeModel> CREATOR = new Parcelable.Creator<HomeModel>() {
        @Override
        public HomeModel createFromParcel(Parcel source) {
            return new HomeModel(source);
        }

        @Override
        public HomeModel[] newArray(int size) {
            return new HomeModel[size];
        }
    };
}