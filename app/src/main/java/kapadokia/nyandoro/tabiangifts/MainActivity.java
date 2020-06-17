package kapadokia.nyandoro.tabiangifts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import kapadokia.nyandoro.tabiangifts.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

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
}
