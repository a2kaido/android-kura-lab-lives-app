package jp.a2kaido.kuralab.lives.domain.service

import io.reactivex.Single
import jp.a2kaido.kuralab.lives.data.http.DetailRepositoryImpl
import jp.a2kaido.kuralab.lives.domain.model.EventDetail

/**
 * Created by anikaido on 2017/09/23.
 */
class DetailService {

    val repos = DetailRepositoryImpl()

    fun getEventDetail(finename: String): Single<EventDetail> {
        return Single.create { emitter ->
            try {
                emitter.onSuccess(repos.getEventDetail(finename))
            } catch (e: Throwable) {
                emitter.onError(e)
            }
        }
    }
}