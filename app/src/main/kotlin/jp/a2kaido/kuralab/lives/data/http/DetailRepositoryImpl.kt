package jp.a2kaido.kuralab.lives.data.http

import jp.a2kaido.kuralab.lives.domain.`interface`.DetailRepository
import jp.a2kaido.kuralab.lives.domain.model.EventDetail
import jp.a2kaido.kuralab.lives.domain.model.Song
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.stream.Collectors

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

        var event = ""
        var songs = arrayListOf<Song>()
        result?.let {
            event = it.event
            it.setlist.main.stream().filter { l -> (l as? List<Any>) != null }.forEach { list ->
                songs.addAll(getEventDetailFromResponse(list as? List<Any>))
            }
        }

        return EventDetail(event, songs)
    }

    private fun getEventDetailFromResponse(list: List<Any>?): List<Song> {
        list?.let {
            val artist = it.getOrNull(0) as? String
            val songs = it.getOrNull(1) as? List<String>

            if (artist == null) return listOf()

            return songs?.stream()?.map { s ->
                Song(s, artist)
            }?.collect(Collectors.toList()) ?: listOf()
        }

        return listOf()
    }
}

data class EventDetail(val event: String, val date: String, val prefecture: String, val venues: String, val setlist: SetList)

data class SetList(val main: List<Any>, val encore: List<Any>?)
