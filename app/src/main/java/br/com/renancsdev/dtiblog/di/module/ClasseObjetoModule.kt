package br.com.renancsdev.dtiblog.di.module

import br.com.renancsdev.dtiblog.model.Posts
import br.com.renancsdev.dtiblog.util.Validacao
import org.koin.dsl.module

val claseObjetoModule = module {
    single { Posts() }
    single { Validacao() }
}