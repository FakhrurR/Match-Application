package rijhalpc.jadwalsepakbola.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rijhalpc.jadwalsepakbola.BuildConfig

class DBSport {
    companion object {
        fun getClient(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(
                    BuildConfig.BASE_URL +
                            "api/v1/json/${BuildConfig.TSDB_API_KEY}"
                )
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
        fun <T> createService(service: Class<T>): T {
            return getClient().create(service)
        }

    }
}