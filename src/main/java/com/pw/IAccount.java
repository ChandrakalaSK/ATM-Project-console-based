package com.pw;

public interface IAccount
{

    void sDeposit(int amount);
    void cDeposit(int amount);

    void sWithdraw(int amount);
    void cWithdraw(int amount);

    void sTransfer(BankTransaction bt, int amount);
    void cTransfer(BankTransaction bt, int amount);

    void sTranscHistory();



    int sGetBalance();
    int cGetBalance();

}