package br.com.renancsdev.dtiblog.di.application

import android.app.Application
import br.com.renancsdev.dtiblog.di.module.claseObjetoModule
import br.com.renancsdev.dtiblog.di.module.redeModule
import br.com.renancsdev.dtiblog.di.module.repositorioModule
import br.com.renancsdev.dtiblog.di.module.vmModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.core.context.startKoin

class DtiPostApplication: Application() {

    private val appModules = listOf(
        claseObjetoModule,
        redeModule,
        repositorioModule,
        vmModule
    )

    override fun onCreate() {
        super.onCreate()
         startKoin {
             androidContext(this@DtiPostApplication)
             modules(appModules)
         }
    }

}