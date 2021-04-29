package mobi.mobileforce.nbsassessment.network

import mobi.mobileforce.nbsassessment.BuildConfig
import mobi.mobileforce.nbsassessment.model.Search
import mobi.mobileforce.nbsassessment.model.Trending
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

interface NetworkService {
    @GET("/trending/{mediaType}/{timeWindow}")
    fun getTrending(@Path("mediaType") mediaType:String = "all", @Path("timeWindow") timeWindow:String = "week", @Query("api-key") apiKey:String = BuildConfig.apiToken): Observable<Trending>

    @GET("/movie/upcoming")
    fun getUpcoming(@Query("page") page: Int = 1, @Query("api-key") apiKey:String = BuildConfig.apiToken): Observable<Trending>

    @GET("/search/movie")
    fun searchMovie(@Query("query") query: String?, @Query("page") page: Int = 1,  @Query("api-key") apiKey:String = BuildConfig.apiToken): Observable<Search>
}