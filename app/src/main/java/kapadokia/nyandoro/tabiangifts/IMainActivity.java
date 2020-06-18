package kapadokia.nyandoro.tabiangifts;

import kapadokia.nyandoro.tabiangifts.models.Product;

public interface IMainActivity {

    void inflateViewProductFragment(Product product);

    void showQuantityDialog();

    void setQuantity(int quantity);

    void addToCart(Product product, int quantity);

    void inflateViewCartFragment();

    void setCartVisibility(boolean visibility);
}
