package br.com.renancsdev.dtiblog.di.module

import br.com.renancsdev.dtiblog.repository.Repository
import org.koin.dsl.module

val repositorioModule = module {
    single { Repository(get()) }
}