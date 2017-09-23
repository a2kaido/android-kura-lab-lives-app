package jp.a2kaido.kuralab.lives.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import jp.a2kaido.kuralab.lives.R
import jp.a2kaido.kuralab.lives.domain.model.Event
import jp.a2kaido.kuralab.lives.domain.service.HomeService
import jp.a2kaido.kuralab.lives.ui.adapter.HomeRecyclerViewAdapter
import jp.a2kaido.kuralab.lives.ui.adapter.HomeRecyclerViewListener

/**
 * Created by anikaido on 2017/09/23.
 */
class HomeActivity: AppCompatActivity(), HomeRecyclerViewListener {

    val service = HomeService()

    val recyclerView: RecyclerView by lazy {
        findViewById(R.id.homeRecyclerView) as RecyclerView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        title = "ホーム"

        recyclerView.layoutManager = LinearLayoutManager(this)
        request()
    }

    fun request() {
        service.getEventList().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe { eventList, throwable ->
                    recyclerView.adapter = HomeRecyclerViewAdapter(this, eventList, this)
                }
    }

    override fun onClick(event: Event) {
        val intent = Intent(HomeActivity@this, DetailActivity::class.java)

        intent.putExtra("jsonFileName", event.name)
        startActivity(intent)
    }
}
