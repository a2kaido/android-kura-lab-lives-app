package jp.a2kaido.kuralab.lives.data.http

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by anikaido on 2017/09/23.
 */
interface KuralabService {

    @GET("kura-lab/my-lives/master/events/list.json")
    fun listEvent(): Call<List<String>>

    @GET("kura-lab/my-lives/master/events/{filename}")
    fun eventDetail(@Path("filename") filename: String): Call<EventDetail>
}
