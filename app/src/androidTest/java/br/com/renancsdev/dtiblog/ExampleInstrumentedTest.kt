package br.com.renancsdev.dtiblog

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("br.com.renancsdev.dtiblog", appContext.packageName)
    }

    @get:Rule
    var rule = ActivityScenarioRule(MainActivity::class.java)
    
    @Test
    fun testeListarTest() {
        Thread.sleep(2000)
        onView(withId(R.id.rv_listar_postagem)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1, click()
            )
        )
    }
    @Test
    fun testeListarDetalheTest() {
        Thread.sleep(2000)
        onView(withId(R.id.rv_listar_postagem)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1, click()
            )
        )
        onView(withId(R.id.tv_item_post_tittle)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_post_data)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_post_texto)).check(matches(isDisplayed()))
    }

    @Test
    fun testeCriarTituloTest() {
        Thread.sleep(2000)
        onView(withId(R.id.navigation_criar)).perform(click())
        onView(withId(R.id.edit_text_titulo)).perform(clearText(), typeText("Titulo Teste 1"))
    }
    @Test
    fun testeCriarDescricaoTest() {
        Thread.sleep(2000)
        onView(withId(R.id.navigation_criar)).perform(click())
        onView(withId(R.id.edit_text_post)).perform(clearText(), typeText("Titulo Descricao 1"))
    }

    @Test
    fun testeListarDeleteTest() {
        Thread.sleep(5000)
        onView(withId(R.id.navigation_excluir)).perform(click())
        onView(withId(R.id.rv_listar_deletar_postagem)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1, click()
            )
        )
    }

}