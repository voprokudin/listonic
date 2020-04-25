package p.vasylprokudin.listonic.presentation.view.list.listener

import p.vasylprokudin.listonic.data.db.entities.RowItem

interface ItemClickedListener {

    fun onItemRowClicked(id: Int)

    fun onItemDeleteClicked(item: RowItem)
}