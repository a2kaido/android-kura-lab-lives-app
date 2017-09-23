package jp.a2kaido.kuralab.lives.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import jp.a2kaido.kuralab.lives.R
import jp.a2kaido.kuralab.lives.domain.service.DetailService
import jp.a2kaido.kuralab.lives.ui.adapter.DetailRecyclerViewAdapter

class DetailActivity : AppCompatActivity() {

    val service = DetailService()

    val recyclerView: RecyclerView by lazy {
        findViewById(R.id.detailRecyclerView) as RecyclerView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        title = intent.getStringExtra("jsonFileName")
        val json = intent.getStringExtra("jsonFileName")

        recyclerView.layoutManager = LinearLayoutManager(this)

        service.getEventDetail(json).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe { eventDetail, throwable ->
                    eventDetail?.let {
                        title = eventDetail.event
                        recyclerView.adapter = DetailRecyclerViewAdapter(this, it.setlist)
                    }
                }
    }
}
