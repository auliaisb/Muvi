package mobi.mobileforce.nbsassessment.di

import mobi.mobileforce.nbsassessment.home.BannerAdapter
import mobi.mobileforce.nbsassessment.home.HomeItemAdapter
import mobi.mobileforce.nbsassessment.home.HomePage
import mobi.mobileforce.nbsassessment.home.HomeViewModel
import mobi.mobileforce.nbsassessment.network.NetworkModule
import mobi.mobileforce.nbsassessment.network.NetworkService
import mobi.mobileforce.nbsassessment.search.SearchAdapter
import mobi.mobileforce.nbsassessment.search.SearchPage
import mobi.mobileforce.nbsassessment.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<NetworkService>{
        val networkModule = NetworkModule(get())
        networkModule.provideCall().create(NetworkService::class.java)
    }
}


val trendingModule = module{

    scope<HomePage> {
        viewModel { HomeViewModel(get()) }
        scoped<BannerAdapter> { BannerAdapter(get()) }
        factory { HomeItemAdapter(get()) }
    }
}

val searchModule = module {
    scope<SearchPage> {
        viewModel {  SearchViewModel(get()) }
        scoped {SearchAdapter(get())}
    }
}

val allModules = appModule + trendingModule + searchModule