package com.application.market.controller;

import com.application.market.entity.CardData;
import com.application.market.entity.Transaction;
import com.application.market.pay.ExternalPaymentService;
import com.application.market.service.TransactionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Date;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private ExternalPaymentService externalPaymentService;

    @Autowired
    private TransactionService transactionService;

    Authentication authentication;

    @GetMapping("")
    public String checkout(HttpSession session,
                           Model model) {

        CardData cardData = new CardData();

        Double totalPrice = (Double) session.getAttribute("totalPrice");
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("cardData", cardData);

        return "checkout";
    }

    @PostMapping("/pay")
    public String pay(@ModelAttribute("cardData") CardData cardData,
                      HttpSession session,
                      Principal principal) {

        if (externalPaymentService.pay(cardData.getCcv()) == 10099) {

            principal = SecurityContextHolder.getContext().getAuthentication();

            LocalDateTime now = LocalDateTime.now();
            String fullName = cardData.getCardName();
            String name = fullName.substring(0, fullName.indexOf(" "));
            String surname = fullName.substring(fullName.indexOf(" ") + 1);
            Double totalPrice = (Double) session.getAttribute("totalPrice");

            Transaction transaction = new Transaction();
            transaction.setName(name);
            transaction.setSurname(surname);
            transaction.setEmail(principal.getName());
            transaction.setCardNumber(cardData.getCardNumber());
            transaction.setDataOfTransaction(now);
            transaction.setTotalPrice(totalPrice);

            transactionService.saveTransaction(transaction);
            return "successPayment";
        }
        else
            return "errorPayment";

    }

}
