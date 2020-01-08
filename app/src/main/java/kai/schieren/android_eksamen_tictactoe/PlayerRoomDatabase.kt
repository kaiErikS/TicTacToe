package kai.schieren.android_eksamen_tictactoe

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [Player::class],version = 1, exportSchema = false)
public abstract class PlayerRoomDatabase : RoomDatabase() {

    abstract fun playerDao() : PlayerDao

    companion object {
        @Volatile
        private var INSTANCE: PlayerRoomDatabase? = null

        fun getDatabase(context: Context): PlayerRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null)
                return tempInstance

                synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                    PlayerRoomDatabase::class.java,
                    "Person_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }





}