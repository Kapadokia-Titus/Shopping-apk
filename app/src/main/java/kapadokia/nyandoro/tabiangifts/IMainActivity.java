package kapadokia.nyandoro.tabiangifts;

import kapadokia.nyandoro.tabiangifts.models.Product;

public interface IMainActivity {

    void inflateViewProductFragment(Product product);


    void showQuantityDialog();

    void setQuantity(int quantity);
}
