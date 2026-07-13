package com.bank.model;

public class Customer {

    private int id;
    private String fullName;
    private String accountNumber;
    private String accountType;
    private double balance;
    private String mobile;
    private String email;

    public Customer() {
    }

    public Customer(int id, String fullName, String accountNumber,
                    String accountType, double balance,
                    String mobile, String email) {

        this.id = id;
        this.fullName = fullName;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.mobile = mobile;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}