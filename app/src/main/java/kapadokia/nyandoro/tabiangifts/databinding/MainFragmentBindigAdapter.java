package kapadokia.nyandoro.tabiangifts.databinding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kapadokia.nyandoro.tabiangifts.adapter.ProductsAdapter;
import kapadokia.nyandoro.tabiangifts.models.Product;


public class MainFragmentBindigAdapter {

    private static final int NUM_COLUMNS=2;
    @BindingAdapter("productsList")
    public static void setProductList(RecyclerView view, List<Product> products){

        //checking if the list is null
        if (products == null){
            return;
        }
        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();
        if (layoutManager ==null){
                view.setLayoutManager(new GridLayoutManager(view.getContext(), NUM_COLUMNS));
        }

        //instantiate products adapter
        ProductsAdapter adapter = (ProductsAdapter) view.getAdapter();

        //if the adapter is null,  we create a new one
        if (adapter == null){
            adapter = new ProductsAdapter(view.getContext(), products);
            view.setAdapter(adapter);
        }
    }
}
