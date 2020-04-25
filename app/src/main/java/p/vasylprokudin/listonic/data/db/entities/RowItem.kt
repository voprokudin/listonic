package p.vasylprokudin.listonic.data.db.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class RowItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val counter: Int = 0,
    val color: String,
    val createdDate: Long
) : Parcelable