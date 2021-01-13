package dojo.supermarket.model;

public class ThreeForTwoOffer extends Offer{

    public ThreeForTwoOffer(double argument){
        super(argument);
    }

    @Override
    public Discount handle(ReceiptItem receiptItem) {
        if (receiptItem.getQuantity() < 3)
            return null ;
        int numberOfX = (int)receiptItem.getQuantity()/3;
        double discountAmount = receiptItem.getTotalPrice() - ((numberOfX * 2 * receiptItem.getPrice()) + (int)receiptItem.getQuantity() % 3 * receiptItem.getPrice());
        return new Discount(receiptItem.getProduct(), "3 for 2", -discountAmount);
    }
}