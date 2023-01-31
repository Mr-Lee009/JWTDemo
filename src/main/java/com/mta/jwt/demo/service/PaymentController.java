package com.mta.jwt.demo.service;

import com.mta.jwt.demo.service.payment.BaseGatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.mta.jwt.demo.service.payment.model.PaymentDto;

@Service("PaymentController")
public class PaymentController {
    @Autowired
    @Qualifier(value = "Payment2B2CService")
    public BaseGatewayService service;

    public void DoSomething(String type)
    {
        service.generateMetadata(new PaymentDto(), type);

//        service.doSomethingElse(type);
    }
}
