package mobi.mobileforce.nbsassessment.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mobi.mobileforce.nbsassessment.BuildConfig
import mobi.mobileforce.nbsassessment.model.Search
import mobi.mobileforce.nbsassessment.model.Trending
import mobi.mobileforce.nbsassessment.network.NetworkService
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.lang.IndexOutOfBoundsException

class SearchViewModel(val networkService: NetworkService): ViewModel() {
    private val searchData: MutableLiveData<List<ItemModel>> = MutableLiveData()

    fun getBannerUrlsData() : LiveData<List<ItemModel>> {
        return searchData
    }

    fun searchMovie(query: String?){
        if(query?.length!! <= 2){
            return
        }
        networkService.searchMovie(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorResumeNext { t: Throwable? -> Observable.error(t) }
            .subscribe(object: Subscriber<Search>(){
                override fun onCompleted() {

                }

                override fun onError(e: Throwable?) {
                    e?.printStackTrace()
                }

                override fun onNext(t: Search?) {
                    t?.let{
                        val search = ArrayList<ItemModel>()
                        for(i in 0 until 10){

                            try {
                                val item = it.results?.get(i)
                                search.add(
                                    ItemModel(
                                        item?.id,
                                        "${BuildConfig.BASEURL}${item?.posterPath}",
                                        item?.title,
                                        item?.overview
                                    )
                                )
                            } catch (e: IndexOutOfBoundsException) {
                                e.printStackTrace()
                            }

                        }
                        searchData.value = search
                    }
                }
            })
    }
}

data class ItemModel(val id: Int?, val urlImage: String?, val title: String?, val casts: String?)