package com.LMS.Member_Service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MemberRequestDTO {

    @NotBlank
    @Size(min = 2, max = 70, message = "Name should be less than 70 characters more than 2 characters")
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotNull
    private boolean isActive;

    @NotNull
    private int fineAmount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(int fineAmount) {
        this.fineAmount = fineAmount;
    }
}
