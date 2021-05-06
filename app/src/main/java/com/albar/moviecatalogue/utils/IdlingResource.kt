package com.albar.moviecatalogue.utils

import androidx.test.espresso.idling.CountingIdlingResource

object IdlingResource {
        private const val RESOURCE = "GLOBAL"
        private val espressoTestIdlingResource = CountingIdlingResource(RESOURCE)

        fun increment() {
            espressoTestIdlingResource.increment()
        }

        fun decrement() {
            espressoTestIdlingResource.decrement()
        }
}