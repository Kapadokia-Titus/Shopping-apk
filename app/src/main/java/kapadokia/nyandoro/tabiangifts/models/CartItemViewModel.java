package kapadokia.nyandoro.tabiangifts.models;

import android.content.Context;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import kapadokia.nyandoro.tabiangifts.BR;
import kapadokia.nyandoro.tabiangifts.IMainActivity;

public class CartItemViewModel extends BaseObservable {

    private static final String TAG = "CartItemViewModel";

    private CartItem cartItem;

    @Bindable
    public CartItem getCartItem(){
        return cartItem;
    }

    public void setCartItem(CartItem cartItem){
        Log.d(TAG, "setQuantity: updating cart");
        this.cartItem = cartItem;
        notifyPropertyChanged(kapadokia.nyandoro.tabiangifts.BR.cartItem);
    }


    public String getQuantityString(CartItem cartItem){
        return ("Qty: " + String.valueOf(cartItem.getQuantity()));
    }

    public void increaseQuantity(Context context){
        CartItem cartItem = getCartItem();
        cartItem.setQuantity(cartItem.getQuantity() + 1);
        setCartItem(cartItem);
        IMainActivity iMainActivity = (IMainActivity) context;
        iMainActivity.updateQuantity(cartItem.getProduct(), 1);
    }

    public void decreaseQuantity(Context context){
        CartItem cartItem = getCartItem();
        IMainActivity iMainActivity = (IMainActivity) context;
        if(cartItem.getQuantity() > 1){
            cartItem.setQuantity(cartItem.getQuantity() - 1);
            setCartItem(cartItem);
            iMainActivity.updateQuantity(cartItem.getProduct(), -1);
        }
        else if(cartItem.getQuantity() == 1){
            cartItem.setQuantity(cartItem.getQuantity() - 1);
            setCartItem(cartItem);
            iMainActivity.removeCartItem(cartItem);
        }
    }


}
