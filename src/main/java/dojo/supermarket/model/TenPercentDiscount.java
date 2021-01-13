package dojo.supermarket.model;

public class TenPercentDiscount extends Offer{

    public TenPercentDiscount(double amount){
        super(amount);
    }

    @Override
    public Discount handle(ReceiptItem receiptItem) {
        return new Discount(receiptItem.getProduct(),
                this.argument + "% off",
                -receiptItem.getQuantity() * receiptItem.getPrice() * this.argument / 100.0);
    }
}
