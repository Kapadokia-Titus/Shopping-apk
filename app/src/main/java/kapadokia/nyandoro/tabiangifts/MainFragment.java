package kapadokia.nyandoro.tabiangifts;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kapadokia.nyandoro.tabiangifts.adapter.ProductsAdapter;
import kapadokia.nyandoro.tabiangifts.databinding.FragmentMainBinding;
import kapadokia.nyandoro.tabiangifts.models.Product;
import kapadokia.nyandoro.tabiangifts.util.Products;

public class MainFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private static final String TAG = "MainFragment";

    // Data binding
    FragmentMainBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentMainBinding.inflate(inflater);
        mBinding.swipeRefreshLayout.setOnRefreshListener(this);
        setupProductsList();
        return mBinding.getRoot();
    }

    private void setupProductsList(){
        Products products = new Products();
        List<Product> productList = new ArrayList<>();
        productList.addAll(Arrays.asList(products.PRODUCTS));
        mBinding.setProducts(productList);
    }


    @Override
    public void onRefresh() {
        //facilitating refresh
        Products products = new Products();
        List<Product> productList = new ArrayList<>();
        productList.addAll(Arrays.asList(products.PRODUCTS));
        ((ProductsAdapter)mBinding.recyclervView.getAdapter()).refreshList(productList);
        onItemsLoadComplete();
    }

    void onItemsLoadComplete() {
        (mBinding.recyclervView.getAdapter()).notifyDataSetChanged();
        mBinding.swipeRefreshLayout.setRefreshing(false);
    }
}



