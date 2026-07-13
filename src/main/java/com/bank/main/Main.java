package com.bank.main;

import java.util.Scanner;
import com.bank.service.BankService;
import com.bank.model.Customer;
import java.util.InputMismatchException;


public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BankService service = new BankService();
        while (true) {
            try {
            System.out.println("===== BANK MANAGEMENT SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. View All Customers");
            System.out.println("3. Deposit Money");
            System.out.println("4. Withdraw Money");
            System.out.println("5. Check Balance");
            System.out.println("6. Delete Account");
            System.out.println("7. Search Customer");
            System.out.println("8. Update Customer");
            System.out.println("9. Exit");

            System.out.print("Enter Your Choice: ");
            int choice = sc.nextInt();

                // Test Commit


            switch (choice) {

                case 1:
                    Customer customer = new Customer();

                    System.out.print("Enter Full Name : ");
                    sc.nextLine();
                    customer.setFullName(sc.nextLine());

                    System.out.print("Enter Account Number : ");
                    customer.setAccountNumber(sc.nextLine());

                    System.out.print("Enter Account Type : ");
                    customer.setAccountType(sc.nextLine());

                    System.out.print("Enter Balance : ");
                    customer.setBalance(sc.nextDouble());

                    sc.nextLine();

                    System.out.print("Enter Mobile : ");
                    customer.setMobile(sc.nextLine());

                    System.out.print("Enter Email : ");
                    customer.setEmail(sc.nextLine());

                    service.createAccount(customer);

                    break;

                case 2:
                    service.viewAllCustomers();

                    break;

                case 3:

                    sc.nextLine();

                    System.out.print("Enter Account Number : ");
                    String accNo = sc.nextLine();

                    System.out.print("Enter Amount : ");
                    double amount = sc.nextDouble();

                    service.deposit(accNo, amount);

                    break;

                case 4:

                    sc.nextLine();

                    System.out.print("Enter Account Number : ");
                    String accNo2 = sc.nextLine();

                    System.out.print("Enter Amount : ");
                    double amount2 = sc.nextDouble();

                    service.withdraw(accNo2, amount2);

                    break;

                case 5:

                    sc.nextLine();

                    System.out.print("Enter Account Number : ");
                    String accNo3 = sc.nextLine();

                    service.checkBalance(accNo3);

                    break;

                case 6:

                    sc.nextLine();

                    System.out.print("Enter Account Number : ");
                    String accNo4 = sc.nextLine();

                    service.deleteAccount(accNo4);

                    break;

                case 7:

                    sc.nextLine();

                    System.out.print("Enter Account Number : ");
                    String searchAccNo = sc.nextLine();

                    service.searchCustomer(searchAccNo);

                    break;

                case 8:

                    sc.nextLine();

                    System.out.print("Enter Account Number : ");
                    String updateAccNo = sc.nextLine();

                    System.out.print("Enter New Mobile : ");
                    String newMobile = sc.nextLine();

                    System.out.print("Enter New Email : ");
                    String newEmail = sc.nextLine();

                    service.updateCustomer(updateAccNo, newMobile, newEmail);

                    break;

                case 9:
                    System.out.println("Thank You...");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice");
            }

            }
            catch (InputMismatchException e) {

                System.out.println("Invalid Input! Please Enter Numbers Only.");

                sc.nextLine();
            }
        }
    }
}