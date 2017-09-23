package jp.a2kaido.kuralab.lives.domain.service

import io.reactivex.Single
import jp.a2kaido.kuralab.lives.data.http.HomeRepositoryImpl
import jp.a2kaido.kuralab.lives.domain.model.Event

/**
 * Created by anikaido on 2017/09/23.
 */
class HomeService {
    val repos = HomeRepositoryImpl()

    fun getEventList(): Single<List<Event>> {
        return Single.create { emitter ->
            try {
                emitter.onSuccess(repos.getEventList())
            } catch (e: Throwable) {
                emitter.onError(e)
            }
        }
    }
}