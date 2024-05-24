package br.com.renancsdev.dtiblog.di.module

import br.com.renancsdev.dtiblog.ui.criar.CriarViewModel
import br.com.renancsdev.dtiblog.ui.excluir.ExcluirViewModel
import br.com.renancsdev.dtiblog.ui.listar.activity.ListarDetalheViewModel
import br.com.renancsdev.dtiblog.ui.listar.fragment.ListarViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module

val vmModule = module {

    // Provide MainActivityViewModel
    viewModel { ListarDetalheViewModel(get() , get())}
    viewModel { CriarViewModel(get() , get()) }
    viewModel { ExcluirViewModel(get()) }
    viewModel { ListarViewModel(get()) }

}