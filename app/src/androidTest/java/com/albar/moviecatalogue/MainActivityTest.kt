package com.albar.moviecatalogue

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.albar.moviecatalogue.utils.DataDummy
import com.albar.moviecatalogue.utils.IdlingResource
import org.junit.*
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class MainActivityTest {

    @get:Rule
    var activity = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance()
            .register(IdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance()
            .unregister(IdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun load1AndInsertMainMovies() {
        onView(withId(R.id.nav_movie)).perform(click())
        onView(withText("MOVIES")).perform(click())
        onView(withId(R.id.rv_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
        onView(withId(R.id.rv_movie))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    5,
                    click()
                )
            )

        onView(withId(R.id.btn_favorite)).perform(click())

        onView(withId(R.id.tv_backdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_backdrop)).perform(swipeUp())

        onView(withId(R.id.tv_image)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_image)).perform(swipeUp())

        onView(withId(R.id.tv_movieRatingCircle)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movieRatingCircle)).perform(swipeUp())

        onView(withId(R.id.tv_movieRatingText)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movieRatingText)).perform(swipeUp())

        onView(withId(R.id.tv_userScore)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_userScore)).perform(swipeUp())

        onView(withId(R.id.tv_explainDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_explainDetail)).perform(swipeUp())

        onView(withId(R.id.tv_release)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release)).perform(swipeUp())

        onView(withId(R.id.tv_about)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_about)).perform(swipeUp())

        onView(withId(R.id.collapsing_toolbar)).check(matches(isDisplayed()))

        onView(withId(R.id.btn_buy)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_buy)).perform(click())

        pressBack()
    }

    @Test
    fun load2AndInsertMainTvShows() {
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_tvshow))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
        onView(withId(R.id.rv_tvshow))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    5,
                    click()
                )
            )

        onView(withId(R.id.btn_favorite)).perform(click())

        onView(withId(R.id.tv_backdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_backdrop)).perform(swipeUp())

        onView(withId(R.id.tv_image)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_image)).perform(swipeUp())

        onView(withId(R.id.tv_movieRatingCircle)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movieRatingCircle)).perform(swipeUp())

        onView(withId(R.id.tv_movieRatingText)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movieRatingText)).perform(swipeUp())

        onView(withId(R.id.tv_userScore)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_userScore)).perform(swipeUp())

        onView(withId(R.id.tv_explainDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_explainDetail)).perform(swipeUp())

        onView(withId(R.id.tv_release)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release)).perform(swipeUp())

        onView(withId(R.id.tv_about)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_about)).perform(swipeUp())

        onView(withId(R.id.collapsing_toolbar)).check(matches(isDisplayed()))

        onView(withId(R.id.btn_buy)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_buy)).perform(click())

        pressBack()
    }

    @Test
    fun load3FavoriteMovies() {
        onView(withId(R.id.nav_fav)).perform(click())
        onView(withText("MOVIES")).perform(click())
        onView(withId(R.id.rv_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )

        onView(withId(R.id.btn_favorite)).perform(click())

        onView(withId(R.id.tv_backdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_backdrop)).perform(swipeUp())

        onView(withId(R.id.tv_image)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_image)).perform(swipeUp())

        onView(withId(R.id.tv_movieRatingCircle)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movieRatingCircle)).perform(swipeUp())

        onView(withId(R.id.tv_movieRatingText)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movieRatingText)).perform(swipeUp())

        onView(withId(R.id.tv_userScore)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_userScore)).perform(swipeUp())

        onView(withId(R.id.tv_explainDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_explainDetail)).perform(swipeUp())

        onView(withId(R.id.tv_release)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release)).perform(swipeUp())

        onView(withId(R.id.tv_about)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_about)).perform(swipeUp())

        onView(withId(R.id.collapsing_toolbar)).check(matches(isDisplayed()))

        onView(withId(R.id.btn_buy)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_buy)).perform(click())

        pressBack()
    }

    @Test
    fun load4FavTvShows() {
        onView(withId(R.id.nav_fav)).perform(click())
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_tvshow))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )

        onView(withId(R.id.btn_favorite)).perform(click())

        onView(withId(R.id.tv_backdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_backdrop)).perform(swipeUp())

        onView(withId(R.id.tv_image)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_image)).perform(swipeUp())

        onView(withId(R.id.tv_movieRatingCircle)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movieRatingCircle)).perform(swipeUp())

        onView(withId(R.id.tv_movieRatingText)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movieRatingText)).perform(swipeUp())

        onView(withId(R.id.tv_userScore)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_userScore)).perform(swipeUp())

        onView(withId(R.id.tv_explainDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_explainDetail)).perform(swipeUp())

        onView(withId(R.id.tv_release)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release)).perform(swipeUp())

        onView(withId(R.id.tv_about)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_about)).perform(swipeUp())

        onView(withId(R.id.collapsing_toolbar)).check(matches(isDisplayed()))

        onView(withId(R.id.btn_buy)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_buy)).perform(click())

        pressBack()
    }
}