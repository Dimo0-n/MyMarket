package com.application.market.pay;

import org.springframework.stereotype.Service;

@Service
public class ExternalPaymentService {

    private final int SUCCESS_CODE = 10099;
    private final int FAILURE_CODE = 20077;

    public int pay(String ccv){

        if (ccv.equals("111"))
            return SUCCESS_CODE;
        else
            return FAILURE_CODE;
    }

}
