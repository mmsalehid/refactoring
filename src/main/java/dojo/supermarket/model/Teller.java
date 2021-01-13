package dojo.supermarket.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Teller {

    private final SupermarketCatalog catalog;
    private Map<Product, Offer> offers = new HashMap<>();

    public Teller(SupermarketCatalog catalog) {
        this.catalog = catalog;
    }

    public void addSpecialOffer(SpecialOfferType offerType, Product product, double argument) {
        this.offers.put(product, getOfferByOfferType(offerType, argument));
    }

    private Offer getOfferByOfferType(SpecialOfferType offerType, double argument){
        if(offerType == SpecialOfferType.FiveForAmount){
            return new FiveForAmount(argument);
        }
        else if (offerType == SpecialOfferType.ThreeForTwo){
            return new ThreeForTwoOffer(argument);
        }
        else if (offerType == SpecialOfferType.TwoForAmount){
            return new TwoForAmount(argument);
        }
        else {
            return new TenPercentDiscount(argument);
        }
    }

    public Receipt createReceiptFromCart(ShoppingCart theCart) {
        Receipt receipt = new Receipt();
        List<ProductQuantity> productQuantities = theCart.getItems();
        productQuantities.forEach(pq -> receipt.addProduct(getReceiptItem(pq)));
        handleOffers(receipt, theCart);
        return receipt;
    }

    private ReceiptItem getReceiptItem(ProductQuantity pq) {
        Product p = pq.getProduct();
        double quantity = pq.getQuantity();
        double unitPrice = this.catalog.getUnitPrice(p);
        double price = quantity * unitPrice;
        return new ReceiptItem(p, quantity, unitPrice, price);
    }

    void handleOffers(Receipt receipt, ShoppingCart theCart) {
        List<ReceiptItem> actualReceiptItems = createActualReceiptItems(theCart.productQuantities);
        for (ReceiptItem receiptItem: actualReceiptItems) {
            Offer offer = offers.get(receiptItem.getProduct());
            if (offer != null){
                Discount discount = offer.handle(receiptItem);
                if (discount != null)
                    receipt.addDiscount(discount);
            }
        }
    }
    private List<ReceiptItem> createActualReceiptItems(Map<Product, Double> productQuantities){
        List<ReceiptItem> receiptItems= new ArrayList<>();
        for(Product p: productQuantities.keySet()){
            receiptItems.add(getReceiptItem(new ProductQuantity(p, productQuantities.get(p))));
        }
        return receiptItems;
    }

}
