package com.mta.jwt.demo.service.payment;

import com.mta.jwt.demo.service.payment.model.PaymentDto;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("PaymentTTBService")
public class PaymentTTBService extends BaseGatewayService{

    protected PaymentTTBService() {
        super(null);
    }

    @Override
    protected boolean CanApply(String type) {
        return type.equals("TTB");
    }

    @Override
    public Map<String, Object> internalGenerateMetadata(PaymentDto dto) {
        System.out.println("PaymentTTBService handle request.");
        return null;
    }

    @Override
    protected void internalDoSomethingElse() {

    }
}
