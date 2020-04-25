package p.vasylprokudin.listonic.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import p.vasylprokudin.listonic.Constants.RoomDatabase.Companion.VERSION
import p.vasylprokudin.listonic.data.db.entities.RowItem
import p.vasylprokudin.listonic.data.db.entities.dao.RowItemDao

@Database(entities = [RowItem::class], version = VERSION, exportSchema = false)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun rowItemDao(): RowItemDao

    companion object {
        @Volatile
        private var instance: ApplicationDatabase? = null

        operator fun invoke(context: Context): ApplicationDatabase = synchronized(this) {
            return instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, ApplicationDatabase::class.java, "my.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}