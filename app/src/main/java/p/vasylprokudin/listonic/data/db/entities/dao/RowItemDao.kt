package p.vasylprokudin.listonic.data.db.entities.dao

import androidx.room.OnConflictStrategy
import androidx.room.Insert
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Delete
import p.vasylprokudin.listonic.data.db.entities.RowItem

@Dao
interface RowItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(row: RowItem)

    @Query("SELECT * FROM RowItem")
    fun getAll(): List<RowItem>

    @Query("SELECT * FROM RowItem WHERE id = :id")
    fun getById(id: Int): RowItem

    @Delete
    fun delete(row: RowItem)

    @Query("UPDATE RowItem SET counter = :counter WHERE id = :id")
    fun update(id: Int, counter: Int)
}