package kai.schieren.android_eksamen_tictactoe


import org.junit.Assert.assertEquals
import org.junit.Test


class GameManagerTest {


    @Test
    fun checkWinner_returns_winner(){
        var gm = GameManager()
        val validator = true
        val tiles = ArrayList<Int>()
        tiles.add(1)
        tiles.add(2)
        tiles.add(3)
        tiles.add(4)

        assertEquals(gm.checkWinner(tiles), validator)
    }

    @Test
    fun checkWinner_returns_false(){
        var gm = GameManager()
        val validator = false
        val tiles = ArrayList<Int>()
        tiles.add(1)
        tiles.add(7)
        tiles.add(3)
        tiles.add(4)

        assertEquals(gm.checkWinner(tiles), validator)
    }

    @Test
    fun aiMove_returns_correct_tile(){
        var correctTile = 1
        var gm = GameManager()

        assertEquals(gm.aiMove(), correctTile)
    }


}