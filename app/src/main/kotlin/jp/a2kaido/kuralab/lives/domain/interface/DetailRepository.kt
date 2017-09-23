package jp.a2kaido.kuralab.lives.domain.`interface`

import jp.a2kaido.kuralab.lives.domain.model.EventDetail

/**
 * Created by anikaido on 2017/09/23.
 */
interface DetailRepository {
    fun getEventDetail(finename: String): EventDetail?
}