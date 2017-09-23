package jp.a2kaido.kuralab.lives.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import jp.a2kaido.kuralab.lives.R
import jp.a2kaido.kuralab.lives.domain.model.Song

/**
 * Created by anikaido on 2017/09/24.
 */
class DetailRecyclerViewAdapter(context: Context, val data: List<Song>?): RecyclerView.Adapter<DetailRecyclerViewAdapter.ViewHolder>() {
    private val inflater = LayoutInflater.from(context)

    override fun onBindViewHolder(holder: DetailRecyclerViewAdapter.ViewHolder?, position: Int) {
        data ?: return

        if (data.size <= position) return

        holder?.title?.text = data[position].title
        holder?.artist?.text = data[position].artist
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DetailRecyclerViewAdapter.ViewHolder {
        return DetailRecyclerViewAdapter.ViewHolder(inflater.inflate(R.layout.item_detail_listview, parent, false))
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById<TextView>(R.id.detailTitleTextView)
        val artist: TextView = itemView.findViewById<TextView>(R.id.detailArtistTextView)
    }
}