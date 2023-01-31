package com.mta.jwt.demo.service.payment;

import com.mta.jwt.demo.service.payment.model.PaymentDto;

import java.util.Map;

public abstract class BaseGatewayService {

    private final BaseGatewayService next;

    protected BaseGatewayService(BaseGatewayService next) {
        if(null != next){
            System.out.println("contructer =>"+next.getClass().getName());
        }
        this.next = next;
    }

    protected abstract boolean CanApply(String type);

    protected abstract Map<String, Object> internalGenerateMetadata(PaymentDto dto);

    public final Map<String, Object> generateMetadata(PaymentDto dto, String type)
    {
        System.out.println("generateMetadata class_name :"+ this.getClass().getName());
        if (CanApply(type))
        {
            return internalGenerateMetadata(dto);
        }
        else if (next != null)
        {
            return next.generateMetadata(dto, type);
        }
        throw new IllegalArgumentException("Cannot handle type " + type);
    }

//    protected abstract void internalDoSomethingElse();
//
//    public final void doSomethingElse(String type)
//    {
//        if (CanApply(type))
//        {
//            internalDoSomethingElse();
//            return;
//        }
//        else if (next != null)
//        {
//            next.doSomethingElse(type);
//            return;
//        }
//        throw new IllegalArgumentException("Cannot handle type " + type);
//    }
}
