package kapadokia.nyandoro.tabiangifts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

import kapadokia.nyandoro.tabiangifts.databinding.ActivityMainBinding;
import kapadokia.nyandoro.tabiangifts.models.Product;
import kapadokia.nyandoro.tabiangifts.util.PreferenceKeys;

public class MainActivity extends AppCompatActivity implements IMainActivity{

    private static final String TAG = "MainActivity";

    //data binding
    // we capitalise the 1st letter and then remove te underscore
    ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding  = DataBindingUtil.setContentView(this, R.layout.activity_main);

        init();
        getShoppingCart();
    }


    private void init(){
        // inflating the vie product fragment
        MainFragment fragment = new MainFragment();

        //instantiate the fragment transaction object
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.frame_container, fragment, getString(R.string.fragment_main));
        //commit the transaction
        transaction.commit();
    }

    private void getShoppingCart(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Set<String> serialNumbers = preferences.getStringSet(PreferenceKeys.shopping_cart, new HashSet<String>());
        mBinding.setNumCartItems(serialNumbers.size());
    }

    @Override
    public void inflateViewProductFragment(Product product) {
        ViewProductFragment fragment = new ViewProductFragment();
        Bundle bundle = new Bundle();
        //add product to the parcelable
        bundle.putParcelable(getString(R.string.intent_product),product);
        fragment.setArguments(bundle);

        //instantiate the fragment transaction object
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.frame_container, fragment, getString(R.string.fragment_view_product));
        //add transaction to the backstack
        transaction.addToBackStack(getString(R.string.fragment_view_product));
        //commit the transaction
        transaction.commit();
    }

    @Override
    public void showQuantityDialog() {
        Log.d(TAG, "showQuantityDialog: showing Quantity Dialog.");
        ChooseQuantityDialog dialog = new ChooseQuantityDialog();
        dialog.show(getSupportFragmentManager(), getString(R.string.dialog_choose_quantity));
    }

    /*
       Set quantity in ViewProductFragment
    */
    @Override
    public void setQuantity(int quantity) {
        Log.d(TAG, "selectQuantity: selected quantity: " + quantity);

        ViewProductFragment fragment = (ViewProductFragment)getSupportFragmentManager().findFragmentByTag(getString(R.string.fragment_view_product));
        if(fragment != null){
            fragment.mBinding.getProductView().setQuantity(quantity);
        }
    }

    @Override
    public void addToCart(Product product, int quantity) {

        //get the shared preference object
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();

        // we are using a set to get the serial numbers and add to cart
        Set<String>  serialNumbers = preferences.getStringSet(PreferenceKeys.shopping_cart, new HashSet<String>());
        serialNumbers.add(String.valueOf(product.getSerial_number()));

        editor.putStringSet(PreferenceKeys.shopping_cart, serialNumbers);
        editor.commit();

        //getting the current quantity and passing it to the cart
        int currentQuantity = preferences.getInt(String.valueOf(product.getSerial_number()), 0);
        // sum the current quantity with the new quantity
        editor.putInt(String.valueOf(product.getSerial_number()), (currentQuantity+ quantity));

        setQuantity(1);

        // let the user know that the product has been added to cart
        Toast.makeText(this, "added to cart", Toast.LENGTH_SHORT).show();

        getShoppingCart();
    }
}
