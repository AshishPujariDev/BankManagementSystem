package com.bank.dao;

import com.bank.model.Transaction;
import com.bank.dao.TransactionDAO;
import java.sql.ResultSet;
import com.bank.model.Customer;
import com.bank.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CustomerDAO {

    TransactionDAO transactionDAO = new TransactionDAO();

    public boolean isAccountExists(String accountNumber) {

        String sql = "SELECT * FROM customer WHERE account_number = ?";

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, accountNumber);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public void createAccount(Customer customer) {

        String sql = "INSERT INTO customer(full_name, account_number, account_type, balance, mobile, email) VALUES (?, ?, ?, ?, ?, ?)";

        if (isAccountExists(customer.getAccountNumber())) {
            System.out.println("Account Number Already Exists...");
            return;
        }

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, customer.getFullName());
            ps.setString(2, customer.getAccountNumber());
            ps.setString(3, customer.getAccountType());
            ps.setDouble(4, customer.getBalance());
            ps.setString(5, customer.getMobile());
            ps.setString(6, customer.getEmail());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Customer Account Created Successfully...");
            } else {
                System.out.println("Failed...");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void viewAllCustomers() {

        String sql = "SELECT * FROM customer";

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n========== CUSTOMER LIST ==========");

            while (rs.next()) {

                System.out.println("-----------------------------");
                System.out.println("ID : " + rs.getInt("id"));
                System.out.println("Name : " + rs.getString("full_name"));
                System.out.println("Account No : " + rs.getString("account_number"));
                System.out.println("Account Type : " + rs.getString("account_type"));
                System.out.println("Balance : " + rs.getDouble("balance"));
                System.out.println("Mobile : " + rs.getString("mobile"));
                System.out.println("Email : " + rs.getString("email"));
            }

            System.out.println("===================================");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deposit(String accountNumber, double amount) {

        if (amount <= 0) {
            System.out.println("Invalid Amount! Amount should be greater than 0.");
            return;
        }

        String sql = "UPDATE customer SET balance = balance + ? WHERE account_number = ?";

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setDouble(1, amount);
            ps.setString(2, accountNumber);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                Transaction transaction = new Transaction(
                        accountNumber,
                        "Deposit",
                        amount
                );

                transactionDAO.saveTransaction(transaction);

                System.out.println("Amount Deposited Successfully...");

            } else {
                System.out.println("Account Not Found...");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void withdraw(String accountNumber, double amount) {

        if (amount <= 0) {
            System.out.println("Invalid Amount! Amount should be greater than 0.");
            return;
        }

        String sql = "UPDATE customer SET balance = balance - ? WHERE account_number = ? AND balance >= ?";

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setDouble(1, amount);
            ps.setString(2, accountNumber);
            ps.setDouble(3, amount);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                Transaction transaction = new Transaction(
                        accountNumber,
                        "Withdraw",
                        amount
                );

                transactionDAO.saveTransaction(transaction);

                System.out.println("Amount Withdrawn Successfully...");

            } else {
                System.out.println("Insufficient Balance or Account Not Found...");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkBalance(String accountNumber) {

        String sql = "SELECT balance FROM customer WHERE account_number = ?";

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, accountNumber);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Current Balance : " + rs.getDouble("balance"));
            } else {
                System.out.println("Account Not Found...");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAccount(String accountNumber) {

        String sql = "DELETE FROM customer WHERE account_number = ?";

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, accountNumber);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Account Deleted Successfully...");
            } else {
                System.out.println("Account Not Found...");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchCustomer(String accountNumber) {

        String sql = "SELECT * FROM customer WHERE account_number = ?";

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, accountNumber);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("\n========== CUSTOMER DETAILS ==========");
                System.out.println("ID : " + rs.getInt("id"));
                System.out.println("Name : " + rs.getString("full_name"));
                System.out.println("Account No : " + rs.getString("account_number"));
                System.out.println("Account Type : " + rs.getString("account_type"));
                System.out.println("Balance : " + rs.getDouble("balance"));
                System.out.println("Mobile : " + rs.getString("mobile"));
                System.out.println("Email : " + rs.getString("email"));
                System.out.println("======================================");

            } else {
                System.out.println("Account Not Found...");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCustomer(String accountNumber, String mobile, String email) {

        String sql = "UPDATE customer SET mobile = ?, email = ? WHERE account_number = ?";

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, mobile);
            ps.setString(2, email);
            ps.setString(3, accountNumber);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Customer Details Updated Successfully...");
            } else {
                System.out.println("Account Not Found...");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void viewTransactionHistory(String accountNumber) {

        transactionDAO.viewTransactionHistory(accountNumber);

    }
}