package kai.schieren.android_eksamen_tictactoe

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Chronometer
import kotlinx.android.synthetic.main.game_fragment.*
import java.util.*


private const val TAG = "GameFragment"
private const val ARG_PLAYER_1 = "player1"
private const val ARG_PLAYER_2 = "player2"


class GameFragment : Fragment(), View.OnClickListener  {
    private var player1: Player? = null
    private var player2: Player? = null
    private var aiPlayer: Player? = null
    private var isPlayingAi: Boolean = false
    private var dataPasser: OnDataPass? = null
    private val gm = GameManager()
    private var tiles = ArrayList<Button>()
    private lateinit var timer: Chronometer

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate starts")
        super.onCreate(savedInstanceState)
        player1 = arguments?.getSerializable(ARG_PLAYER_1) as Player?
        if (isPlayingAi){
            aiPlayer = arguments?.getSerializable(ARG_PLAYER_2) as Player?
        } else{
            player2 = arguments?.getSerializable(ARG_PLAYER_2) as Player?
        }

    }

    fun setIPAI(){
        isPlayingAi = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView starts")
       var view = inflater.inflate(R.layout.game_fragment, container, false)
        timer = view.findViewById(R.id.timerView)

        val tile1: Button = view.findViewById(R.id.btn1)
        val tile2: Button = view.findViewById(R.id.btn2)
        val tile3: Button = view.findViewById(R.id.btn3)
        val tile4: Button = view.findViewById(R.id.btn4)
        val tile5: Button = view.findViewById(R.id.btn5)
        val tile6: Button = view.findViewById(R.id.btn6)
        val tile7: Button = view.findViewById(R.id.btn7)
        val tile8: Button = view.findViewById(R.id.btn8)
        val tile9: Button = view.findViewById(R.id.btn9)
        val tile10: Button = view.findViewById(R.id.btn10)
        val tile11: Button = view.findViewById(R.id.btn11)
        val tile12: Button = view.findViewById(R.id.btn12)
        val tile13: Button = view.findViewById(R.id.btn13)
        val tile14: Button = view.findViewById(R.id.btn14)
        val tile15: Button = view.findViewById(R.id.btn15)
        val tile16: Button = view.findViewById(R.id.btn16)

        tiles.add(tile1)
        tiles.add(tile2)
        tiles.add(tile3)
        tiles.add(tile4)
        tiles.add(tile5)
        tiles.add(tile6)
        tiles.add(tile7)
        tiles.add(tile8)
        tiles.add(tile9)
        tiles.add(tile10)
        tiles.add(tile11)
        tiles.add(tile12)
        tiles.add(tile13)
        tiles.add(tile14)
        tiles.add(tile15)
        tiles.add(tile16)

        tile1.setOnClickListener(this)
        tile2.setOnClickListener(this)
        tile3.setOnClickListener(this)
        tile4.setOnClickListener(this)
        tile5.setOnClickListener(this)
        tile6.setOnClickListener(this)
        tile7.setOnClickListener(this)
        tile8.setOnClickListener(this)
        tile9.setOnClickListener(this)
        tile10.setOnClickListener(this)
        tile11.setOnClickListener(this)
        tile12.setOnClickListener(this)
        tile13.setOnClickListener(this)
        tile14.setOnClickListener(this)
        tile15.setOnClickListener(this)
        tile16.setOnClickListener(this)

        return view
    }


    @SuppressLint("SetTextI18n")
    override fun onClick(v: View) {
        when(v.id){
            R.id.btn1 -> {gm.buttonPressed(btn1, 1, isPlayingAi)
                processAi(isPlayingAi, btn1)}
            R.id.btn2 -> {gm.buttonPressed(btn2, 2, isPlayingAi)
                processAi(isPlayingAi, btn2)}
            R.id.btn3 -> {gm.buttonPressed(btn3, 3, isPlayingAi)
                processAi(isPlayingAi, btn3)}
            R.id.btn4 -> {gm.buttonPressed(btn4, 4, isPlayingAi)
                processAi(isPlayingAi, btn4)}
            R.id.btn5 -> {gm.buttonPressed(btn5, 5, isPlayingAi)
                processAi(isPlayingAi, btn5)}
            R.id.btn6 -> {gm.buttonPressed(btn6, 6, isPlayingAi)
                processAi(isPlayingAi, btn6)}
            R.id.btn7 -> {gm.buttonPressed(btn7, 7, isPlayingAi)
                processAi(isPlayingAi, btn7)}
            R.id.btn8 -> {gm.buttonPressed(btn8, 8, isPlayingAi)
                processAi(isPlayingAi, btn8)}
            R.id.btn9 -> {gm.buttonPressed(btn9, 9, isPlayingAi)
                processAi(isPlayingAi, btn9)}
            R.id.btn10 -> {gm.buttonPressed(btn10, 10, isPlayingAi)
                processAi(isPlayingAi, btn10)}
            R.id.btn11 -> {gm.buttonPressed(btn11, 11, isPlayingAi)
                processAi(isPlayingAi, btn11)}
            R.id.btn12 -> {gm.buttonPressed(btn12, 12, isPlayingAi)
                processAi(isPlayingAi, btn12)}
            R.id.btn13 -> {gm.buttonPressed(btn13, 13, isPlayingAi)
                processAi(isPlayingAi, btn13)}
            R.id.btn14 -> {gm.buttonPressed(btn14, 14, isPlayingAi)
                processAi(isPlayingAi, btn14)}
            R.id.btn15 -> {gm.buttonPressed(btn15, 15, isPlayingAi)
                processAi(isPlayingAi, btn15)}
            R.id.btn16 -> {gm.buttonPressed(btn16, 16, isPlayingAi)
                processAi(isPlayingAi, btn16)}

        }
        when {
            gm.winner == 1 -> {winnerAnnouncement.text = "${player1!!.name} WON!"
                disableButtons()
                if (player1!!.name != "PlayerX") {
                    Log.d(TAG, "playerwins pre: ${player1!!.wins}")
                    player1!!.wins++
                    Log.d(TAG, "playerwins post: ${player1!!.wins}")

                    dataPasser!!.onPassData(player1!!)
                }
                timer.stop()

            }
            gm.winner == 2 -> {winnerAnnouncement.text = "${player2!!.name} WON!"
                disableButtons()
                if (player2!!.name != "PlayerO") {
                    player2!!.wins++
                    dataPasser!!.onPassData(player1!!)
                }
                timer.stop()
            }
            gm.winner == 3 -> {winnerAnnouncement.text = "THE GAME TIED!"
                timer.stop()
                disableButtons()
            }
        }
    }

    private fun parseAi(tileNr: Int): Button? {
        var selectedBtn: Button? = null
        when(tileNr){
            1 -> selectedBtn = btn1
            2 -> selectedBtn = btn2
            3 -> selectedBtn = btn3
            4 -> selectedBtn = btn4
            5 -> selectedBtn = btn5
            6 -> selectedBtn = btn6
            7 -> selectedBtn = btn7
            8 -> selectedBtn = btn8
            9 -> selectedBtn = btn9
            10 -> selectedBtn = btn10
            11 -> selectedBtn = btn11
            12 -> selectedBtn = btn12
            13 -> selectedBtn = btn13
            14 -> selectedBtn = btn14
            15 -> selectedBtn = btn15
            16 -> selectedBtn = btn16
        }
        return selectedBtn
    }


    private fun processAi(isPlayingAi: Boolean, btn: Button){
        var aiTile = gm.aiMove()
        if (isPlayingAi) {
            if (aiTile != 0) {
                parseAi(aiTile)!!.text = "O"
                parseAi(aiTile)!!.isClickable = false
                checkAiWin()
            }
            if (gm.checkTie(isPlayingAi)) {
                gameEnd.text = "Out of options..."
            }
        }


    }

    private fun checkAiWin(){
        if (gm.checkWinner(gm.ai)){
            winnerAnnouncement.text = "${aiPlayer?.name} WON!"
            disableButtons()
            aiPlayer!!.wins++
            dataPasser!!.onPassData(aiPlayer!!)
        }
    }

    private fun disableButtons(){
        for (button in tiles){
            button.isClickable = false
        }
    }




    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var opponent: String
        if (isPlayingAi){
            opponent = aiPlayer!!.name
        } else {
            opponent = player2!!.name
        }
        matchUp.text = "${player1?.name} VS $opponent"

        timer.start()


    }


    override fun onAttach(context: Context) {
        Log.d(TAG, "onAttach starts")
        super.onAttach(context)

        if (context is OnDataPass) {
            dataPasser = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnDataPass")
        }
    }

    override fun onDetach() {
        Log.d(TAG, "onDetach starts")
        super.onDetach()
        dataPasser = null

    }

    override fun onStop() {
        Log.d(TAG, "onStop: Called")
        timer.stop()
        super.onStop()
    }

    override fun onPause() {
        Log.d(TAG, "onPause: Called")
        timer.stop()
        super.onPause()
    }

    override fun onResume() {
        Log.d(TAG, "onResume: Called")
        super.onResume()
        timer.start()
    }


    interface OnDataPass {

        fun onPassData(player: Player)


    }

    companion object {

        @JvmStatic
        fun newInstance(param1: Player, param2: Player) =
            GameFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PLAYER_1, param1)
                    putSerializable(ARG_PLAYER_2, param2)
                }
            }
    }
}
