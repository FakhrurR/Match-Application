package rijhalpc.jadwalsepakbola.model.repository

import rijhalpc.jadwalsepakbola.MatchFavorite

interface Repo {
    fun getMatchFromDb(): List<MatchFavorite>

    fun insertData(eventId: String, homeId: String, awayId: String)

    fun deleteData(eventId: String)

    fun checkFavorite(eventId: String): List<MatchFavorite>
}