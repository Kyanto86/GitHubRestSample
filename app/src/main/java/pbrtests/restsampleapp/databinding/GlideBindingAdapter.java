package pbrtests.restsampleapp.databinding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class GlideBindingAdapter {

    private static final String TAG = "GlideBindingAdapter";

    @BindingAdapter("avatarUrl")
    public static void setAvatar (ImageView imageView, String avaterUrl){

        Glide.with(imageView.getContext())
                .load(avaterUrl)
                .into(imageView);
    }
}
