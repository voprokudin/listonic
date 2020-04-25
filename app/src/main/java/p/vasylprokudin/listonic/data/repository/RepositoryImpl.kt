package p.vasylprokudin.listonic.data.repository

import p.vasylprokudin.listonic.data.db.ApplicationDatabase
import p.vasylprokudin.listonic.data.db.entities.RowItem
import p.vasylprokudin.listonic.domain.repository.Repository

class RepositoryImpl(
    database: ApplicationDatabase
) : Repository {

    private val rowDao = database.rowItemDao()

    override fun insertRow(row: RowItem) {
        rowDao.insert(row)
    }

    override fun getAllRows(): List<RowItem> = rowDao.getAll()

    override fun getRowById(id: Int): RowItem = rowDao.getById(id)

    override fun deleteRow(item: RowItem) {
        rowDao.delete(item)
    }

    override fun updateRow(item: RowItem) {
        item.apply { rowDao.update(id, counter) }
    }
}