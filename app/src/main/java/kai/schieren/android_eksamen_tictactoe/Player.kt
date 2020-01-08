package kai.schieren.android_eksamen_tictactoe

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Players_table")
class Player(@PrimaryKey var name: String,
             @ColumnInfo(name = "wins")var wins: Int) : Serializable

