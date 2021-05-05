package com.example.recomended;

class Advertise {
    private String Title,Description,Date;

    Advertise(String title, String description, String date) {
        Title = title;
        Description = description;
        Date = date;
    }

    String getTitle() {
        return Title;
    }

    String getDescription() {
        return Description;
    }

    String getDate() {
        return Date;
    }
}
