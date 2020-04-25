package p.vasylprokudin.listonic.domain.repository

import p.vasylprokudin.listonic.data.db.entities.RowItem

interface Repository {

    fun insertRow(row: RowItem)

    fun getAllRows(): List<RowItem>

    fun getRowById(id: Int): RowItem

    fun deleteRow(item: RowItem)

    fun updateRow(item: RowItem)
}