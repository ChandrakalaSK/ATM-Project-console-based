package com.pw;

class BankTransaction
{

    //For transferring to a Account
    private String pSaccountNum = "30319870911";

    public void bTransaction(IAccount bank,BankTransaction bt, int account, int amount)
    {


        if(account == 1 && bank.sGetBalance() >= amount)
        {
            System.out.println("Transferring Money...");
            bank.sTransfer(bt,amount);
        }
        else if(account == 2 && bank.cGetBalance() >= amount)
        {
            System.out.println("Transferring Money...");
            bank.cTransfer(bt,amount);
        }
        else
            System.out.print("Insufficient balance to transfer money.");

    }

    public String getSAccountNum()
    {
        return pSaccountNum;
    }

    public String getCAccountNum()
    {
        return pSaccountNum;
    }
}