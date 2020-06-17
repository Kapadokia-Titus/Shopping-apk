package kapadokia.nyandoro.tabiangifts.databinding;

import android.content.Context;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import kapadokia.nyandoro.tabiangifts.R;

public class GlideBindingAdapter {

    @BindingAdapter("imageUrl")
    public static void setImage(ImageView view, int imageUrl){
        //get context
        Context context = view.getContext();

        //create request options for glide
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background);

        // now set image with glide
        Glide.with(context)
                .setDefaultRequestOptions(options)
                .load(imageUrl)
                .into(view);
    }
}
