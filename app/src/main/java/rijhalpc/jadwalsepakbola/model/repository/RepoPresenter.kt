package rijhalpc.jadwalsepakbola.model.repository

import android.content.Context
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import rijhalpc.jadwalsepakbola.MatchFavorite
import rijhalpc.jadwalsepakbola.database

class RepoPresenter(private val context: Context) : Repo {

    override fun checkFavorite(eventId: String): List<MatchFavorite> {
        return context.database.use {
            val result = select(MatchFavorite.TABLE_FAVORITE)
                .whereArgs(
                    "(EVENT_ID = {id})",
                    "id" to eventId
                )
            val favorite = result.parseList(classParser<MatchFavorite>())
            favorite
        }
    }


    override fun deleteData(eventId: String) {
        context.database.use {
            delete(
                MatchFavorite.TABLE_FAVORITE, "(EVENT_ID = {id})",
                "id" to eventId
            )
        }
    }

    override fun insertData(eventId: String, homeId: String, awayId: String) {
        context.database.use {
            insert(
                MatchFavorite.TABLE_FAVORITE,
                MatchFavorite.EVENT_ID to eventId,
                MatchFavorite.HOME_TEAM_ID to homeId,
                MatchFavorite.AWAY_TEAM_ID to awayId
            )

        }
    }

    override fun getMatchFromDb(): List<MatchFavorite> {
        lateinit var favoriteList: List<MatchFavorite>
        context.database.use {
            val result = select(MatchFavorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<MatchFavorite>())
            favoriteList = favorite
        }
        return favoriteList
    }
}
