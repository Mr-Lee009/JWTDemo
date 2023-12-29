package com.mta.jwt.demo.chainofresponsibility;

public class ChainOfResponsibility {

    public static class SupportRequest {
        public String type;
        public String description;
        
        public SupportRequest(String type,String description){
            this.description = description;
            this.type = type;
        }

        public String getType() {
            return type;
        }
    
        public String getDescription() {
            return description;
        }
    }

    public static abstract class SupportHandler{
        private SupportHandler next;

        public SupportHandler(){

        }
        public SupportHandler(SupportHandler next){
            this.next = next;
        }
        
        public void handle(SupportRequest request){
            if(canHandle(request)){
                doSomething(request);
            }
            else if(next != null){
                next.handle(request);
            }
        }

        abstract protected  boolean canHandle(SupportRequest request);
        abstract protected  void doSomething(SupportRequest request);
    }

    public static class TechnicalSupportHandler extends SupportHandler{
        
        public TechnicalSupportHandler(SupportHandler next){
            super(next);
        }
       
        @Override
        protected boolean canHandle(ChainOfResponsibility.SupportRequest request) {
            return ("TechnicalSupport").equals(request.type);
        }

        @Override
        protected void doSomething(ChainOfResponsibility.SupportRequest request) {
            // TODO
        }
    }

    public static class SaleSupportHandler extends SupportHandler{
        
        public SaleSupportHandler(SupportHandler next){
            super(next);
        }

        @Override
        protected boolean canHandle(ChainOfResponsibility.SupportRequest request) {
            return "SaleSupport".equals(request.type);
        }

        @Override
        protected void doSomething(ChainOfResponsibility.SupportRequest request) {
            // TODO
        }
    }

    public static void main(String[] args) {
        var t = new TechnicalSupportHandler();
    }
}