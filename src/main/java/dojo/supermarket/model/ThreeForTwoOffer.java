package dojo.supermarket.model;

public class ThreeForTwoOffer extends Offer{

    public ThreeForTwoOffer(double argument){
        super(argument);
    }

    @Override
    public Discount handle(ReceiptItem receiptItem) {
        if (receiptItem.getQuantity() < 2)
            return null ;

        double discountAmount = receiptItem.getPrice()*(int)(receiptItem.getQuantity() / 3);
        return new Discount(receiptItem.getProduct(), "3 for 2", -discountAmount);
    }
}