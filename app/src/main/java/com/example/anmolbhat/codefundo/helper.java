package com.example.anmolbhat.codefundo;

public class helper {
    private String name, dob, email, phone;

    public helper(String name, String phone, String email, String dob) {
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDob() {
        return dob;
    }

    public String getPhone() {
        return phone;
    }
}
