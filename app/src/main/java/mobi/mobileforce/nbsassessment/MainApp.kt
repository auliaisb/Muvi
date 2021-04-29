package mobi.mobileforce.nbsassessment

import android.app.Application
import mobi.mobileforce.nbsassessment.di.allModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.GlobalContext.startKoin

class MainApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@MainApp)
            fragmentFactory()
            modules(allModules)
        }

    }
}