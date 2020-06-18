package kapadokia.nyandoro.tabiangifts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import kapadokia.nyandoro.tabiangifts.databinding.FragmentViewProductBinding;
import kapadokia.nyandoro.tabiangifts.models.Product;
import kapadokia.nyandoro.tabiangifts.models.ProductViewModel;
import kapadokia.nyandoro.tabiangifts.util.Products;

public class ViewProductFragment extends Fragment{

    private static final String TAG = "ViewProductFragment";

    // Data binding
    FragmentViewProductBinding mBinding;

    //vars
    private Product mProduct;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if(bundle != null) {
            mProduct = bundle.getParcelable(getString(R.string.intent_product));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentViewProductBinding.inflate(inflater);
        mBinding.setIMainActivity((IMainActivity)getActivity());

        ProductViewModel viewProductModel = new ProductViewModel();
        viewProductModel.setProduct(mProduct);
        viewProductModel.setQuantity(1);

        mBinding.setProductView(viewProductModel);

        return mBinding.getRoot();
    }

}
