package com.example.demo;

import com.example.demo.Account;

import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // 스프링이 이 클래스를 서비스로 인식하게 해줍니다.
public class BankService {
   
	@Autowired
	private AccountRepository repository;
   
    public List<Account> getAllAcounts(){
    	return repository.findAll();
    }
    public String addAccount(String accName, String accNum,String owner, long balance) {
    	repository.save(new Account(accName,accNum,owner,balance));
    	return "계좌 등록에 성공했습니다.";
    }
    @Transactional
    public String updateBalance(String num, long amount, String type) {
        Account acc = repository.findById(num).orElseThrow(() -> new RuntimeException("계좌 없음"));
        if (type.equals("deposit")) {
            acc.deposit(amount);
        } else {
            if (acc.getBalance() < amount) return "잔액 부족";
            acc.withdraw(amount);
        }
        repository.save(acc);
        return "처리 완료";
    }
    @Transactional
    public String updateAccountName(String num, String newName) {
    	Account acc = repository.findById(num).orElseThrow(() -> new RuntimeException("계좌 없음"));
        repository.save(new Account(newName, acc.getAccountNumber(), acc.getOwner(), acc.getBalance()));
        return "계좌 별명이 수정되었습니다.";
    }
    @Transactional
    public String deleteAccount(String num) {
        if (!repository.existsById(num)) return "존재하지 않는 계좌입니다.";
        repository.deleteById(num);
        return "계좌가 삭제되었습니다.";
    }
   
}