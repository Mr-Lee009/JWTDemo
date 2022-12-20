package com.mta.jwt.demo.service.payment;

import com.mta.jwt.demo.service.payment.model.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("PaymentOUBService")
public class PaymentOUBService extends BaseGatewayService{


    public PaymentOUBService(@Autowired PaymentTTBService next){
        super(next);
    }

    @Override
    protected boolean CanApply(String type) {
        return type.equals("OUB");
    }

    @Override
    protected Map<String, Object> internalGenerateMetadata(PaymentDto dto) {
        System.out.println("PaymentOUBService handle request.");
        return null;
    }

    @Override
    protected void internalDoSomethingElse() {

    }
}
