package br.com.renancsdev.dtiblog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import br.com.renancsdev.dtiblog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configurarBindingLayout()
        configurarBottomNavigation()
    }

    private fun configurarBindingLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)
    }

    private fun configurarBottomNavigation() {
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_listar,
                R.id.navigation_criar,
                R.id.navigation_excluir
            )
        )
        setupActionBarWithNavController(
            findNavController(R.id.nav_host_fragment_activity_main),
            appBarConfiguration
        )
        binding.navView.setupWithNavController(findNavController(R.id.nav_host_fragment_activity_main))
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment_activity_main)
        return navController.navigateUp()
    }

}