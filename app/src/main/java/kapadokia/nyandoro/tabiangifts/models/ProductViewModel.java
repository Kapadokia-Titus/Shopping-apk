package kapadokia.nyandoro.tabiangifts.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import kapadokia.nyandoro.tabiangifts.BR;

public class ProductViewModel extends BaseObservable {

    // define all the variables that will be passed in the layout
    private Product product;
    private int quantity;

//    errot  in binding quantity in realtime

            //    @Bindable
            //    public int getQuantity() {
            //        return quantity;
            //    }
            //
            //    public void setQuantity(int quantity) {
            //        this.quantity = quantity;
            //    }

    // add a bindable annotation which generates an entry to the BR merhod
    @Bindable
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
        // notif that the product was changed
        notifyPropertyChanged(kapadokia.nyandoro.tabiangifts.BR.product);
    }
}
