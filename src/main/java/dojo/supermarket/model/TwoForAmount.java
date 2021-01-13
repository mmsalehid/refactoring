package dojo.supermarket.model;

public class TwoForAmount extends Offer {

    public TwoForAmount(double argument){
        super(argument);
    }

    @Override
    public Discount handle(ReceiptItem receiptItem) {
        if (receiptItem.getQuantity() < 2)
            return null;
        int intDivision = (int)(receiptItem.getQuantity()) / 2;
        double pricePerUnit = this.argument * intDivision;
        double theTotal = ((int)(receiptItem.getQuantity())  % 2) * receiptItem.getPrice();
        double total = pricePerUnit + theTotal;
        double discountN = receiptItem.getPrice() * receiptItem.getQuantity() - total;
        return new Discount(receiptItem.getProduct(), "2 for " + this.argument, -discountN);
    }
}
