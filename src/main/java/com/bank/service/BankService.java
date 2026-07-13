package com.bank.service;

import com.bank.dao.CustomerDAO;
import com.bank.model.Customer;

public class BankService {

    CustomerDAO dao = new CustomerDAO();


    public void createAccount(Customer customer) {
        dao.createAccount(customer);
    }


    public void viewAllCustomers() {
        dao.viewAllCustomers();
    }


    public void deposit(String accountNumber, double amount) {
        dao.deposit(accountNumber, amount);
    }


    public void withdraw(String accountNumber, double amount) {
        dao.withdraw(accountNumber, amount);
    }


    public void checkBalance(String accountNumber) {
        dao.checkBalance(accountNumber);
    }


    public void deleteAccount(String accountNumber) {
        dao.deleteAccount(accountNumber);
    }

    public void searchCustomer(String accountNumber) {
        dao.searchCustomer(accountNumber);
    }

    public void updateCustomer(String accountNumber, String mobile, String email) {
        dao.updateCustomer(accountNumber, mobile, email);
    }
}