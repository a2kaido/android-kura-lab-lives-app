package jp.a2kaido.kuralab.lives.domain.`interface`

import jp.a2kaido.kuralab.lives.domain.model.Event

/**
 * Created by anikaido on 2017/09/23.
 */
interface HomeRepository {

    fun getEventList(): List<Event>
}