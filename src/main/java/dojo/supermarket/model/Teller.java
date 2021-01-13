package dojo.supermarket.model;

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
        this.offers.put(product, new Offer(offerType, product, argument));
    }

    public Receipt checksOutArticlesFrom(ShoppingCart theCart) {
        Receipt receipt = new Receipt();
        List<ProductQuantity> productQuantities = theCart.getItems();
        productQuantities.forEach(pq -> receipt.addProduct(getReceiptItem(pq)));
        theCart.handleOffers(receipt, this.offers, this.catalog);
        return receipt;
    }

    private ReceiptItem getReceiptItem(ProductQuantity pq) {
        Product p = pq.getProduct();
        double quantity = pq.getQuantity();
        double unitPrice = this.catalog.getUnitPrice(p);
        double price = quantity * unitPrice;
        return new ReceiptItem(p, quantity, unitPrice, price);
    }

}
