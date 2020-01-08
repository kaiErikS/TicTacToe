package kai.schieren.android_eksamen_tictactoe

import android.widget.Button

class GameManager {


    private var currentPlayer = 1
    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var ai = ArrayList<Int>()
    var winner = 0

    fun buttonPressed(button: Button, gameTileNr: Int, isPlayingAi: Boolean): Int {

            if (currentPlayer == 1) {
                button.text = "X"
                player1.add(gameTileNr)

                if (!isPlayingAi){
                currentPlayer = 2
                }

                if (checkWinner(player1)) {
                    winner = 1

                }
            }

            else if (currentPlayer == 2){
                button.text = "O"
                player2.add(gameTileNr)
                currentPlayer = 1

                if (checkWinner(player2)){
                    winner = 2
                }
            }

            if(checkTie(isPlayingAi)){
                winner = 3
            }


        button.isClickable = false
        return winner

    }

    private fun blockWin(first: Int, second: Int, third: Int, fourth: Int): Int {
       var tileToPlay = 0

        if (player1.contains(second) && player1.contains(third) && player1.contains(fourth) && !ai.contains(first)){
            tileToPlay = first
        } else if (player1.contains(first) && player1.contains(third) && player1.contains(fourth) && !ai.contains(2)){
            tileToPlay = second
        } else if (player1.contains(first) && player1.contains(second) && player1.contains(fourth) && !ai.contains(3)){
            tileToPlay = third
        } else if (player1.contains(first) && player1.contains(second) && player1.contains(third) && !ai.contains(4)){
            tileToPlay = fourth
        }

        return tileToPlay
    }

    fun aiMove(): Int {

        var selectedTile = 0
        var tileIfBlockWin: Int

        tileIfBlockWin = blockWin(1, 2, 3, 4)

        if (tileIfBlockWin != 0 && !ai.contains(tileIfBlockWin)){
            selectedTile = tileIfBlockWin
        } else {
            tileIfBlockWin = blockWin(5, 6, 7, 8)
            if (tileIfBlockWin != 0 && !ai.contains(tileIfBlockWin)) {
                selectedTile = tileIfBlockWin
            } else {
                tileIfBlockWin = blockWin(9, 10, 11, 12)
                if (tileIfBlockWin != 0 && !ai.contains(tileIfBlockWin)) {
                    selectedTile = tileIfBlockWin
                } else {
                    tileIfBlockWin = blockWin(13, 14, 15, 16)
                    if (tileIfBlockWin != 0 && !ai.contains(tileIfBlockWin)) {
                        selectedTile = tileIfBlockWin
                    } else {
                        tileIfBlockWin = blockWin(1, 5, 9, 13)
                        if (tileIfBlockWin != 0 && !ai.contains(tileIfBlockWin)) {
                            selectedTile = tileIfBlockWin
                        } else {
                            tileIfBlockWin = blockWin(2, 6, 10, 14)
                            if (tileIfBlockWin != 0 && !ai.contains(tileIfBlockWin)) {
                                selectedTile = tileIfBlockWin
                            } else {
                                tileIfBlockWin = blockWin(3, 7, 11, 15)
                                if (tileIfBlockWin != 0 && !ai.contains(tileIfBlockWin)) {
                                    selectedTile = tileIfBlockWin
                                } else {
                                    tileIfBlockWin = blockWin(4, 8, 12, 16)
                                    if (tileIfBlockWin != 0 && !ai.contains(tileIfBlockWin)) {
                                        selectedTile = tileIfBlockWin
                                    } else {
                                        tileIfBlockWin = blockWin(1, 6, 11, 16)
                                        if (tileIfBlockWin != 0 && !ai.contains(tileIfBlockWin)) {
                                            selectedTile = tileIfBlockWin
                                        } else {
                                            tileIfBlockWin = blockWin(4, 7, 10, 13)
                                            if (tileIfBlockWin != 0 && !ai.contains(tileIfBlockWin)) {
                                                selectedTile = tileIfBlockWin
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (selectedTile == 0){
            if (!player1.contains(1) && !ai.contains(1)){
                selectedTile = 1
            } else if (!player1.contains(11) && !ai.contains(11)){
                selectedTile = 11
            } else if (!player1.contains(6) && !ai.contains(6)){
                selectedTile = 6
            } else if (!player1.contains(16) && !ai.contains(16)){
                selectedTile = 16
            } else if (!player1.contains(4) && !ai.contains(4)){
                selectedTile = 4
            } else if (!player1.contains(7) && !ai.contains(7)){
                selectedTile = 7
            } else if (!player1.contains(10) && !ai.contains(10)){
                selectedTile = 10
            } else if (!player1.contains(13) && !ai.contains(13)){
                selectedTile = 13
            } else if (!player1.contains(2) && !ai.contains(2)){
                selectedTile = 2
            } else if (!player1.contains(3) && !ai.contains(3)){
                selectedTile = 3
            } else if (!player1.contains(5) && !ai.contains(5)){
                selectedTile = 5
            } else if (!player1.contains(8) && !ai.contains(8)){
                selectedTile = 8
            } else if (!player1.contains(9) && !ai.contains(9)){
                selectedTile = 9
            } else if (!player1.contains(12) && !ai.contains(12)){
                selectedTile = 12
            } else if (!player1.contains(15) && !ai.contains(15)){
                selectedTile = 15
            } else if (!player1.contains(14) && !ai.contains(14)){
                selectedTile = 14
            }

        }

        ai.add(selectedTile)
        return selectedTile


    }

    fun checkWinner(player: ArrayList<Int>): Boolean{

        //Top horizontal
        if (player.contains(1) && player.contains(2) && player.contains(3) && player.contains(4)){
            return true
        }
        //Middle horizontal
        else if (player.contains(5) && player.contains(6) && player.contains(7) && player.contains(8)){
            return true
        }
        //Bottom horizontal
        else if (player.contains(9) && player.contains(10) && player.contains(11) && player.contains(12)){
            return true
        }
        //Bottom horizontal
        else if (player.contains(13) && player.contains(14) && player.contains(15) && player.contains(16)){
            return true
        }
        //Left vertical
        else if (player.contains(1) && player.contains(5) && player.contains(9) && player.contains(13)){
            return true
        }
        //Middle vertical
        else if (player.contains(2) && player.contains(6) && player.contains(10) && player.contains(14)){
            return true
        }
        //Right vertical
        else if (player.contains(3) && player.contains(7) && player.contains(11) && player.contains(15)){
            return true
        }
        else if (player.contains(4) && player.contains(8) && player.contains(12) && player.contains(16)){
            return true
        }
        //Top left -> Bottom right
        else if (player.contains(1) && player.contains(6) && player.contains(11) && player.contains(16)){
            return true
        }
        //Top right -> Bottom left
        else if (player.contains(4) && player.contains(7) && player.contains(10) && player.contains(13)){
            return true
        }

        return false
    }

     fun checkTie(isPlayingAi: Boolean): Boolean{
        var combined = ArrayList<Int>()
        val values = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)

        for (tile in player1){
            combined.add(tile)
        }
        if (isPlayingAi){
            for (tile in ai){
                combined.add(tile)
            }
        } else{
            for (tile in player2){
                combined.add(tile)
            }
        }

        if (combined.containsAll(values)){
            return true
        }

        return false
    }
}