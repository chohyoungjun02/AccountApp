package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class Account {
	
	
	private String accountName; 
	@Id//PK
	private String accountNumber;
	private String owner;
	private long balance;
	
	protected Account() {} // JPA를 위한 기본 생성자
	
	public Account(String accountName, String accountNumber, String owner, long balance) {
		this.accountName = accountName;
		this.accountNumber = accountNumber;
		this.owner = owner;
		this.balance = balance;
	}
	
	public void deposit(long amount) { this.balance += amount; }
    public void withdraw(long amount) { this.balance -= amount; }
    
    //getter 매서드
    public long getBalance() { return balance; }
    public String getAccountNumber() {return accountNumber;}
    public String getOwner() {return owner;}
    public String getAccountName() {return accountName;}
}
