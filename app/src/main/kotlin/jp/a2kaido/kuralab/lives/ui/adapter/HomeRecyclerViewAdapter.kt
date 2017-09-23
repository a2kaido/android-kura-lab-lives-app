package jp.a2kaido.kuralab.lives.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import jp.a2kaido.kuralab.lives.R
import jp.a2kaido.kuralab.lives.domain.model.Event

/**
 * Created by anikaido on 2017/09/23.
 */
class HomeRecyclerViewAdapter(context: Context, val data: List<Event>?, private val listener: HomeRecyclerViewListener): RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder>() {
    private val inflater = LayoutInflater.from(context)

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        data ?: return

        if (data.size <= position) return

        holder?.textView?.text = data[position].name
        holder?.itemView?.setOnClickListener {
            listener.onClick(data[position])
        }
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_home_listview, parent, false))
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById<TextView>(R.id.homeRecyclerTextView)
    }
}

interface HomeRecyclerViewListener {
    fun onClick(event: Event)
}
