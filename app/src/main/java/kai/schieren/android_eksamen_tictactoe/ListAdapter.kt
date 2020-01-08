package kai.schieren.android_eksamen_tictactoe

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListAdapter(var context: Context, var players: ArrayList<Player>): BaseAdapter() {



    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater = LayoutInflater.from(context)
        val rowMain = layoutInflater.inflate(R.layout.main_row, parent, false)

        val nameTextView = rowMain.findViewById<TextView>(R.id.nameView)
        nameTextView.text = players[position].name

        val winsTextView = rowMain.findViewById<TextView>(R.id.winsView)
        winsTextView.text = players[position].wins.toString()

        return rowMain
    }

    override fun getItem(position: Int): Any {
       return "TEST STRING"
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return players.size
    }
}