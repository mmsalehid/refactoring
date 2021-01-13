package dojo.supermarket.model;

public class FiveForAmount extends Offer{

    public FiveForAmount(double argument){
        super(argument);
    }

    @Override
    public Discount handle(ReceiptItem receiptItem) {
        if (receiptItem.getQuantity() < 5)
            return null;
        System.out.println(receiptItem.getQuantity());
        int numberOfXs = (int)receiptItem.getQuantity() / 5;
        System.out.println(numberOfXs);
        double discountTotal = receiptItem.getTotalPrice() - (this.argument * numberOfXs + (int)receiptItem.getQuantity() % 5 * receiptItem.getPrice());
        return new Discount(receiptItem.getProduct(), 5 + " for " + this.argument, -discountTotal);
    }
}
