package jp.a2kaido.kuralab.lives.data.http

import jp.a2kaido.kuralab.lives.domain.`interface`.DetailRepository
import jp.a2kaido.kuralab.lives.domain.model.EventDetail
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by anikaido on 2017/09/23.
 */
class DetailRepositoryImpl: DetailRepository {

    override fun getEventDetail(finename: String): EventDetail {
        val retrofit = Retrofit.Builder().
                baseUrl("https://raw.githubusercontent.com/").
                addConverterFactory(GsonConverterFactory.create()).
                build()

        val service: KuralabService = retrofit.create(KuralabService::class.java)
        val result = service.eventDetail(finename).execute().body()

        result?.let {
            val list = it.setlist.main.getOrNull(0) as? List<Any>
            println(list?.getOrNull(0))
            println(list?.getOrNull(1))
        }

        return EventDetail(arrayListOf())
    }
}

data class EventDetail(val event: String, val date: String, val prefecture: String, val venues: String, val setlist: SetList)

data class SetList(val main: List<Any>, val encore: List<Any>?)
