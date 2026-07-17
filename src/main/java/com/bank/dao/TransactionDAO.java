package com.bank.dao;

import com.bank.model.Transaction;
import com.bank.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TransactionDAO {

    public void saveTransaction(Transaction transaction) {

        String sql = "INSERT INTO transactions(account_number, transaction_type, amount) VALUES (?, ?, ?)";

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, transaction.getAccountNumber());
            ps.setString(2, transaction.getTransactionType());
            ps.setDouble(3, transaction.getAmount());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Transaction Saved Successfully...");
            } else {
                System.out.println("Transaction Save Failed...");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewTransactionHistory(String accountNumber) {

        String sql = "SELECT * FROM transactions WHERE account_number = ? ORDER BY transaction_date DESC";

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, accountNumber);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n========== TRANSACTION HISTORY ==========");

            while (rs.next()) {

                System.out.println("----------------------------------------");
                System.out.println("Transaction ID : " + rs.getInt("id"));
                System.out.println("Account Number : " + rs.getString("account_number"));
                System.out.println("Type           : " + rs.getString("transaction_type"));
                System.out.println("Amount         : " + rs.getDouble("amount"));
                System.out.println("Date & Time    : " + rs.getTimestamp("transaction_date"));
            }

            System.out.println("========================================");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}