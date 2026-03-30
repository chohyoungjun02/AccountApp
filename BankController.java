package com.example.demo;

import com.example.demo.Account;
import com.example.demo.BankService; // 외운 서비스 클래스 임포트

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // 이 클래스가 외부 요청을 받는 컨트롤러임을 선언
public class BankController {

    @Autowired // 스프링이 관리하는 Service 객체를 자동으로 가져와 연결해줍니다.
    private BankService bankService;

    // 1. 잔액 조회 
    @GetMapping("/list")
    public List<Account> getlist() {
        return bankService.getAllAcounts();
    }

 // 계좌 추가하기
    @GetMapping("/add")
    public String addAccount(
    		@RequestParam String accName,
            @RequestParam String accNum,
            @RequestParam String owner,
            @RequestParam long balance) {
        return bankService.addAccount(accName,accNum, owner, balance);
    }
    
    // 계좌 업데이트
    @GetMapping("/update")
    public String update(@RequestParam String num, @RequestParam long amount, @RequestParam String type) {
        return bankService.updateBalance(num, amount, type);
    }
    @GetMapping("/editName")
    public String editName(@RequestParam String num, @RequestParam String newName) {
        return bankService.updateAccountName(num, newName);
    }

    @GetMapping("/delete")
    public String delete(@RequestParam String num) {
        return bankService.deleteAccount(num);
    }
}