package com.application.market.controller;

import com.application.market.entity.CardData;
import com.application.market.pay.ExternalPaymentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private ExternalPaymentService externalPaymentService;

    @GetMapping("")
    public String checkout(HttpSession session, Model model) {

        CardData cardData = new CardData();

        Double totalPrice = (Double) session.getAttribute("totalPrice");
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("cardData", cardData);

        return "checkout";
    }

    @PostMapping("/pay")
    public String pay(@ModelAttribute("cardData") CardData cardData){

        if (externalPaymentService.pay(cardData.getCcv()) == 10099)
            return "successPayment";
        else
            return "errorPayment";

    }

}
