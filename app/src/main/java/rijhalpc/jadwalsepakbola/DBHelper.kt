package rijhalpc.jadwalsepakbola

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DBHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1) {

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(MatchFavorite.TABLE_FAVORITE, true)
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            MatchFavorite.TABLE_FAVORITE, true,
            MatchFavorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            MatchFavorite.EVENT_ID to TEXT + UNIQUE,
            MatchFavorite.HOME_TEAM_ID to TEXT,
            MatchFavorite.AWAY_TEAM_ID to TEXT
        )
    }

    companion object {
        private var instance: DBHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DBHelper {
            if (instance == null) {
                instance = DBHelper(ctx.applicationContext)
            }
            return instance as DBHelper
        }
    }

}

val Context.database: DBHelper
    get() = DBHelper.getInstance(applicationContext)