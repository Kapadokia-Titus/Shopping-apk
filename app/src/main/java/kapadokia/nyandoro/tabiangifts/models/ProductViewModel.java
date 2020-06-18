package kapadokia.nyandoro.tabiangifts.models;

import androidx.annotation.Nullable;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import kapadokia.nyandoro.tabiangifts.BR;

public class ProductViewModel extends BaseObservable {

    // define all the variables that will be passed in the layout
    private Product product;
    private int quantity;

    // image visibility will be equal to true when its done downloading.
    private boolean imageVisibility = false;

    @Bindable
    public boolean isImageVisibility() {
        return imageVisibility;
    }

    public void setImageVisibility(boolean imageVisibility) {
        this.imageVisibility = imageVisibility;
        notifyPropertyChanged(kapadokia.nyandoro.tabiangifts.BR.imageVisibility);
    }

    public RequestListener getCustomRequestListener(){
        return new RequestListener() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
                setImageVisibility(true);
                return false;
            }
        };
    }

    //issues

        @Bindable
        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
            notifyPropertyChanged(kapadokia.nyandoro.tabiangifts.BR.quantity);
        }

    // add a bindable annotation which generates an entry to the BR merhod
    @Bindable
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
        // notif that the product was changed
        notifyPropertyChanged(BR.product);
    }


}
