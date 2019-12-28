package co.penguinLab.common.ui

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import co.penguinLab.common.R
import com.bumptech.glide.Glide

object ImageBindingAdapter {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setUrl(imageView: ImageView, imageUrl: String?) {
        Glide.with(imageView.context)
            .load(imageUrl)
            .placeholder(R.drawable.drawable_glide_placeholder_card)
            .error(
                Glide
                    .with(imageView.context)
                    .load("https://www.freeiconspng.com/uploads/hd-error-photo-transparent-background-19.png")
            )
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("visibleIf")
    fun changeVisibility(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }
}