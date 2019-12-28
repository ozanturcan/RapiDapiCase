package co.penguinLab.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthorizationInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val headers = request.headers.newBuilder()
            .add("Accept: application/json")
            .add("x-rapidapi-host: omgvamp-hearthstone-v1.p.rapidapi.com")
            .add("x-rapidapi-key: 41701da9d1msh351e12f0d9249cbp1b1b1fjsn43d101be1bd8")
            .add("Cache-Control: no-cache")
            .add("Content-Type: application/json; charset=utf-8").build()
        request = request.newBuilder().headers(headers).build()

        return chain.proceed(request)
    }

}