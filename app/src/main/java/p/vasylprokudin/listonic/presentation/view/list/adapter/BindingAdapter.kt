package p.vasylprokudin.listonic.presentation.view.list.adapter

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.databinding.BindingAdapter
import p.vasylprokudin.listonic.data.db.entities.RowItem
import p.vasylprokudin.listonic.presentation.view.list.listener.ItemClickedListener

class BindingAdapter {

    companion object {
        private val listItemsAdapter: RowAdapter = RowAdapter()

        @JvmStatic
        @BindingAdapter("android:rowsList", "android:itemClickListener")
        fun setPhotosList(
            recyclerView: RecyclerView,
            rows: List<RowItem>?,
            itemClickedListener: ItemClickedListener?
        ) {
            if (rows == null || itemClickedListener == null) return

            listItemsAdapter.run {
                submitList(rows)
                itemsClickedListener = itemClickedListener
            }

            recyclerView.run {
                adapter = listItemsAdapter
                setHasFixedSize(true)
            }
        }

        @JvmStatic
        @BindingAdapter("android:backgroundColor")
        fun setBackgroundColor(
            layout: ConstraintLayout,
            color: String?
        ) {
            color?.also { layout.setBackgroundColor(Color.parseColor(it)) }
        }
    }
}