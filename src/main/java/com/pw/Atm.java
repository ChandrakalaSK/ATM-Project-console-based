package com.pw;

import java.util.Scanner;

interface IServices
{
    void atmServices();
}


class ServicesImpl implements IServices
{

    public void atmServices()
    {
        //security limitation
        int limit = 1;
        //user choice
        int choice=0;
        int amount=0;

        System.out.println("\n******** Welcome To SBI ATM ********\n");

        //Taking input from user:
        Scanner sc = new Scanner(System.in);


        //Creating required objects
        //tight Coupling
        AccountHolder ah = new AccountHolder();
        BankTransaction bt = new BankTransaction();
        //loose coupling
        IAccount bank = new BankImpl();


        while(limit <= 3)
        {
            //For security reason, limiting the access by keeping a counter

            System.out.print("\nEnter userId: ");
            String userId = sc.next();

            System.out.print("Enter pin: ");
            String userPin = sc.next();


            if( userId.equals(ah.getId()) && userPin.equals(ah.getPin()) )
            {

                //Increasing the limit to 3 if the user types correct details
                limit=4;

                System.out.println("\n*************************************************");
                System.out.println("Welcome, "+ah.getName());
                System.out.println("*************************************************\n");

                //Account type selection
                System.out.println("Select your Account type: ");
                System.out.println("_________________________");
                System.out.print("\n1. Savings Account \t 2. Current Account\n");
                System.out.print("\nchoice: ");
                int account = sc.nextInt();

                //value checking
                if(account == 1 || account == 2){}
                else
                {
                    System.out.println("Please! enter correct input");
                    System.exit(0);
                }


                //ATM functionalities
                while(true)
                {
                    System.out.println("\n\t\tATM functionalities");
                    System.out.println("\t\t*******************\n");
                    System.out.println("1. Deposit Money \t 2. Withdraw Money\n");
                    System.out.println("3. Transfer Money \t 4. Transactions history\n");
                    System.out.println("5. Check Balance \t 6. Quit\n");
                    System.out.println("**************************************************\n");



                    System.out.print("Enter your choice: ");
                    choice = sc.nextInt();
                    switch(choice)
                    {

                        case 1: System.out.print("Enter amount to be deposited: ");
                            if(account == 1)
                            {
                                //Savings account
                                amount = sc.nextInt();
                                bank.sDeposit(amount);
                            }
                            else
                            {
                                //Current account
                                amount = sc.nextInt();
                                bank.cDeposit(amount);
                            }

                            break;


                        case 2: System.out.print("Enter amount to be withdrawn: ");
                            if(account == 1)
                            {
                                //Savings account
                                //Checking condition that balance request is valid or not
                                amount = sc.nextInt();
                                if(amount <= bank.sGetBalance())
                                {
                                    bank.sWithdraw(amount);
                                }
                                else
                                    System.out.println("Insufficient balance");

                            }
                            else
                            {
                                //Current account
                                //Checking condition that balance request is valid or not
                                amount = sc.nextInt();
                                if(amount <= bank.cGetBalance())
                                {
                                    bank.cWithdraw(amount);
                                }
                                else
                                    System.out.println("Insufficient balance");
                            }

                            break;


                        //Auto account number
                        case 3: if( bt.getSAccountNum().equals("30319870911") && account == 1)
                        {
                            System.out.print("Enter amount to be transferred: ");
                            amount = sc.nextInt();
                            bt.bTransaction(bank,bt,account,amount);

                        }
                        else if(bt.getCAccountNum().equals("30118976112") && account == 2)
                        {
                            System.out.print("Enter amount to be transferred: ");
                            amount = sc.nextInt();
                            bt.bTransaction(bank,bt,account,amount);
                        }
                    /*else
                    	System.out.println("Wrong details! provided. Please, try again.");*/

                            break;



                        case 4: bank.sTranscHistory();

                            break;


                        case 5: if(account == 1)
                            System.out.println("\nAccount Balance is: "+bank.sGetBalance());
                        else if(account == 2)
                            System.out.println("\nAccount Balance is: "+bank.cGetBalance());

                            break;



                        case 6: System.out.println("_______________________________________________________________________________________");
                            System.out.println("\nMESSAGE:   ThankYou for using SBI.");
                            System.out.println("_______________________________________________________________________________________");
                            System.exit(0);

                        default:
                            System.out.println("Please enter valid choice.");



                    }
                }

            }
            else if( limit == 3 )
            {
                limit++;
                System.out.println("_______________________________________________________________________________________");
                System.out.println("\nMESSAGE:   Your ATM services has been blocked for 24 hrs. Please try again later...");
                System.out.println("_______________________________________________________________________________________");
            }
            else
            {
                limit++;
                System.out.println("_______________________________________________________");
                System.out.println("\nMESSAGE:   Wrong user details. Please try again...");
                System.out.println("______________________________________________________");
            }

        }

    }
}


class Atm
{
    public static void main(String[] args)
    {
        //Interface promoting loose coupling
        IServices atm = new ServicesImpl();

        //Calling ATM services of AtmImpl which is implementation class of Interface IAtm
        atm.atmServices();
    }
}