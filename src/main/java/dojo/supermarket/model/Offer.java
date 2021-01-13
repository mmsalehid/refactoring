package dojo.supermarket.model;

abstract public class Offer {

    double argument;
    public abstract Discount handle(ReceiptItem receiptItem);

    public Offer(double argument) {
        this.argument = argument;
    }


    public double getArgument() {
        return argument;
    }
}


