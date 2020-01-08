package kai.schieren.android_eksamen_tictactoe

import android.arch.persistence.room.Room
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.home_fragment.*

private const val TAG = "MainActivity"


class MainActivity : AppCompatActivity(), View.OnClickListener, GameFragment.OnDataPass
{

    var isPlayingAi = true
    var aiPlayer = Player("TTTbot", 0)
    var activeFragment: Int = 0
    lateinit var db: PlayerRoomDatabase
    var playerList = arrayListOf<Player>()
    var selectedQuery: Int = 0
    lateinit var playerWon: Player
    lateinit var adapter: BaseAdapter
    lateinit var list: ListView
    lateinit var gameFragment: GameFragment
    val fm = supportFragmentManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = Room.databaseBuilder(applicationContext, PlayerRoomDatabase::class.java, "player.db").build()
        getList().execute()
        refreshPlayerList(null)
        val playBtn: Button = this.findViewById(R.id.play_btn)
        val playAiBtn: Button = this.findViewById(R.id.play_ai_btn)
        list = findViewById(R.id.listView)
        playBtn.setOnClickListener(this)
        playAiBtn.setOnClickListener(this)
        Log.d(TAG, "This is the list $playerList size: ${playerList.size}")

    }

    internal inner class getList : AsyncTask<Player, Void, List<Player>>(){


        override fun doInBackground(vararg params: Player?): List<Player>? {

            playerList.clear()
            if (selectedQuery == 1){
                db.playerDao().insert(playerWon)
                selectedQuery = 0


            } else if (selectedQuery == 2){
                db.playerDao().updateScore(playerWon.name)
                Log.d(TAG, "Update called")
                selectedQuery = 0

            } else if (selectedQuery == 3){
                db.playerDao().insert(aiPlayer)
                selectedQuery = 0
            }

            return db.playerDao().getAllPlayers()

        }
        override fun onPostExecute(inputList: List<Player>) {
            for (player in inputList){
                playerList.add(player)
            }
            adapter = ListAdapter(this@MainActivity, playerList)
            list.adapter = adapter
            adapter.notifyDataSetChanged()
        }

    }

    override fun onClick(v: View) {
        var errorView: TextView = this.findViewById(R.id.errorTextView)
        var userTaken = false
        var player1 = Player(p1_insert.text.toString(), 0)
        var player2 = Player(p2_insert.text.toString(), 0)

        if (!userTaken) {
            when (v.id) {
                R.id.play_btn -> {
                    if (p1_insert.text.toString() == ""){
                        player1.name = "PlayerX"
                    }
                    if (p2_insert.text.toString() == ""){
                        player2.name = "PlayerO"
                    }
                    gameFragment = GameFragment.newInstance(player1, player2)
                    fm.beginTransaction().replace(R.id.fragment2, gameFragment).addToBackStack(null).commit()
                    activeFragment = 1
                    isPlayingAi = false
                    viewInvisible()

                }
                R.id.play_ai_btn -> {
                    if (p1_insert.text.toString() == ""){
                        player1.name = "PlayerX"
                    }
                    gameFragment = GameFragment.newInstance(player1, aiPlayer)
                    fm.beginTransaction().replace(R.id.fragment2, gameFragment).addToBackStack(null).commit()
                    activeFragment = 1
                    viewInvisible()
                    gameFragment.setIPAI()
                    player2.name = "TTTbot"

                }
            }
        }
    }


     private fun viewInvisible(){
         play_btn.visibility = INVISIBLE
         play_ai_btn.visibility = INVISIBLE
         p1_insert.isEnabled = false
         p2_insert.isEnabled = false
         p1_insert.visibility = INVISIBLE
         p2_insert.visibility = INVISIBLE
         listView.visibility = INVISIBLE
         vsTextview.visibility = INVISIBLE
    }

    private fun viewVisible(){
        play_btn.visibility = VISIBLE
        play_ai_btn.visibility = VISIBLE
        p1_insert.visibility = VISIBLE
        p2_insert.visibility = VISIBLE
        listView.visibility = VISIBLE
        p1_insert.isEnabled = true
        p2_insert.isEnabled = true
    }

    private fun pickQuery(playerWon: Player){
        var namesList = arrayListOf<String>()
        for (p in playerList){
            namesList.add(p.name)
        }
        selectedQuery = if (!namesList.contains(playerWon.name)){
            1
        } else {
            2
        }
        refreshPlayerList(playerWon)
    }


    override fun onBackPressed() {
        if (activeFragment == 1){
            activeFragment = 0
            viewVisible()
            super.onBackPressed()
        }else{
            super.onBackPressed()
        }


    }

    override fun onPassData(player: Player) {
        playerWon = player
        Log.d(TAG, "onPassData = ${playerWon.toString()}")
        pickQuery(playerWon)
    }

    private fun refreshPlayerList(player: Player?){
        getList().execute(player)
    }

}
