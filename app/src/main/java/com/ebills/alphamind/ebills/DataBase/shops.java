package com.ebills.alphamind.ebills.DataBase;

import com.orm.SugarRecord;

public class shops extends SugarRecord<shops> {

    private String picture , name , email , password , address , description , ownerFullName , openingHours , closingHours , overallRating , totalRatings;

    public shops(String picture, String name, String email, String password, String address, String description, String ownerFullName, String openingHours, String closingHours, String overallRating, String totalRatings) {
        this.picture = picture;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.description = description;
        this.ownerFullName = ownerFullName;
        this.openingHours = openingHours;
        this.closingHours = closingHours;
        this.overallRating = overallRating;
        this.totalRatings = totalRatings;
    }





}
