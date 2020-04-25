package p.vasylprokudin.listonic.presentation.view.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import p.vasylprokudin.listonic.data.db.entities.RowItem
import p.vasylprokudin.listonic.databinding.RowItemBinding
import p.vasylprokudin.listonic.presentation.view.list.listener.ItemClickedListener

class RowAdapter : ListAdapter<RowItem, RowAdapter.ViewHolder>(DIFFER) {

    companion object {
        private val DIFFER = object : DiffUtil.ItemCallback<RowItem>() {
            override fun areItemsTheSame(oldItem: RowItem, newItem: RowItem): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: RowItem, newItem: RowItem): Boolean =
                oldItem == newItem
        }
    }

    lateinit var itemsClickedListener: ItemClickedListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, itemsClickedListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: RowItemBinding,
        private val itemsClickedListener: ItemClickedListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RowItem) = with(binding) {
            rowItem = item
            executePendingBindings()
            icDelete.setOnClickListener { itemsClickedListener.onItemDeleteClicked(item) }
            root.setOnClickListener { itemsClickedListener.onItemRowClicked(item.id) }
        }
    }
}