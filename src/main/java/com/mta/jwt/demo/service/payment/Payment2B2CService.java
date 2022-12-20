package com.mta.jwt.demo.service.payment;

import com.mta.jwt.demo.service.payment.model.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("Payment2B2CService")
public class Payment2B2CService extends BaseGatewayService {

    protected Payment2B2CService(@Autowired PaymentOUBService next) {
        super(next);
    }

    @Override
    protected boolean CanApply(String type) {
        return type.equals("2B2C");
    }

    @Override
    public Map<String, Object> internalGenerateMetadata(PaymentDto dto) {
        System.out.println("Payment2B2CService handle request.");
        return null;
    }

    @Override
    protected void internalDoSomethingElse() {

    }
}
