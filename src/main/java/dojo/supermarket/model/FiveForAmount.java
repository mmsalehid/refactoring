package dojo.supermarket.model;

public class FiveForAmount extends Offer{

    public FiveForAmount(double argument){
        super(argument);
    }

    @Override
    public Discount handle(ReceiptItem receiptItem) {
        if (receiptItem.getQuantity() < 5)
            return null;
        int numberOfXs = (int)receiptItem.getQuantity() / 5;
        double discountTotal = receiptItem.getTotalPrice() * receiptItem.getQuantity() - (this.argument * numberOfXs + (int)receiptItem.getQuantity() % 5 * receiptItem.getPrice());
        return new Discount(receiptItem.getProduct(), 5 + " for " + this.argument, -discountTotal);
    }
}
