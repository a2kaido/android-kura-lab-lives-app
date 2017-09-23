package jp.a2kaido.kuralab.lives.data.http

import jp.a2kaido.kuralab.lives.domain.`interface`.HomeRepository
import jp.a2kaido.kuralab.lives.domain.model.Event
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.stream.Collectors

/**
 * Created by anikaido on 2017/09/23.
 */
class HomeRepositoryImpl: HomeRepository {

    override fun getEventList(): List<Event> {
        val retrofit = Retrofit.Builder().
                baseUrl("https://raw.githubusercontent.com/").
                addConverterFactory(GsonConverterFactory.create()).
                build()

        val service: KuralabService = retrofit.create(KuralabService::class.java)
        val result = service.listEvent().execute().body()

        result?.let {
            return it.stream().
                    map { e ->
                        Event(e, e.slice(0..3), e.slice(4..7))
                    }.collect(Collectors.toList())
        } ?: run {
            return arrayListOf()
        }
    }
}
