package mobi.mobileforce.nbsassessment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mobi.mobileforce.nbsassessment.BuildConfig
import mobi.mobileforce.nbsassessment.model.Trending
import mobi.mobileforce.nbsassessment.network.NetworkService
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.lang.IndexOutOfBoundsException

class HomeViewModel(val networkService: NetworkService): ViewModel() {
    init {
        loadTrending()
    }

    private val bannerUrlsData: MutableLiveData<List<ItemModel>> = MutableLiveData()

    fun getBannerUrlsData() : LiveData<List<ItemModel>> {
        return bannerUrlsData
    }

    private val popUrlsData: MutableLiveData<List<ItemModel>> = MutableLiveData()

    fun getPopUrlsData() : LiveData<List<ItemModel>> {
        return popUrlsData
    }

    private val soonUrlsData: MutableLiveData<List<ItemModel>> = MutableLiveData()

    fun getSoonUrlsData() : LiveData<List<ItemModel>> {
        return soonUrlsData
    }

    private fun loadTrending(){
        networkService.getTrending()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext { t: Throwable? -> Observable.error(t) }
                .subscribe(object: Subscriber<Trending>(){
                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable?) {
                        e?.printStackTrace()
                    }

                    override fun onNext(t: Trending?) {
                        t?.let{
                            val bannerUrls = ArrayList<ItemModel>()
                            val popularUrls = ArrayList<ItemModel>()
                            for(i in 0 until 13){
                                if(i < 3) {
                                    try {
                                        val item = it.results?.get(i)
                                        bannerUrls.add(ItemModel(
                                            "${BuildConfig.BASEURL}${item?.posterPath}",
                                                item?.id
                                            )
                                        )
                                    } catch (e: IndexOutOfBoundsException) {
                                        e.printStackTrace()
                                    }
                                } else {
                                    try {
                                        val item = it.results?.get(i)
                                        popularUrls.add(
                                            ItemModel(
                                                "${BuildConfig.BASEURL}${item?.posterPath}",
                                                item?.id
                                            )
                                        )
                                    } catch (e: IndexOutOfBoundsException) {
                                        e.printStackTrace()
                                    }
                                }
                            }
                            bannerUrlsData.value = bannerUrls
                            popUrlsData.value = popularUrls
                        }
                    }
                })
    }

    private fun getUpcoming(){
        networkService.getUpcoming()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorResumeNext { t: Throwable? -> Observable.error(t) }
            .subscribe(object: Subscriber<Trending>(){
                override fun onCompleted() {

                }

                override fun onError(e: Throwable?) {
                    e?.printStackTrace()
                }

                override fun onNext(t: Trending?) {
                    t?.let{
                        val soonUrls = ArrayList<ItemModel>()
                        for(i in 0 until 10){

                                try {
                                    val item = it.results?.get(i)
                                    soonUrls.add(ItemModel(
                                        "${BuildConfig.BASEURL}${item?.posterPath}",
                                        item?.id
                                    )
                                    )
                                } catch (e: IndexOutOfBoundsException) {
                                    e.printStackTrace()
                                }

                        }
                        soonUrlsData.value = soonUrls
                    }
                }
            })
    }


}

data class ItemModel(val url: String?, val id: Int?)