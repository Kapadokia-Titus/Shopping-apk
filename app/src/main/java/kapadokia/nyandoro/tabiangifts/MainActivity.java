package kapadokia.nyandoro.tabiangifts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import kapadokia.nyandoro.tabiangifts.databinding.ActivityMainBinding;
import kapadokia.nyandoro.tabiangifts.models.Product;
import kapadokia.nyandoro.tabiangifts.util.Products;

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
}
