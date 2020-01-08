package kai.schieren.android_eksamen_tictactoe

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*


@Dao
interface PlayerDao {

    @Insert
    fun insert(player: Player)

    @Query("SELECT * FROM players_table ORDER BY wins DESC")
    fun getAllPlayers() : List<Player>

    @Query("SELECT * FROM players_table")
    fun getAllPersonsLive() : LiveData<List<Player>>

    @Query("DELETE FROM players_table")
    fun deleteAll()

    @Query("UPDATE players_table SET wins= wins + 1 WHERE name= :name")
    fun updateScore(name: String)
}