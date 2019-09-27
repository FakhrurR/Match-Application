package rijhalpc.jadwalsepakbola.api

import java.net.URL

class DbSportRepo {

    fun doRequest(url: String): String {
        return URL(url).readText()
    }
}