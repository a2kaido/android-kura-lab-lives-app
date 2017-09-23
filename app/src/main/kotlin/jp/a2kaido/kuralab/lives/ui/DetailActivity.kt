package jp.a2kaido.kuralab.lives.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import jp.a2kaido.kuralab.lives.R
import jp.a2kaido.kuralab.lives.domain.service.DetailService

class DetailActivity : AppCompatActivity() {

    val service = DetailService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        title = intent.getStringExtra("jsonFileName")

        val json = intent.getStringExtra("jsonFileName")

        service.getEventDetail(json).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe { eventDetail, throwable ->
                    println(eventDetail)
                }
    }
}
