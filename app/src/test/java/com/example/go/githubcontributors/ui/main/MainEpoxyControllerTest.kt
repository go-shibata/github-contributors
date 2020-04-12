package com.example.go.githubcontributors.ui.main

import com.example.go.githubcontributors.data.model.Contributor
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MainEpoxyControllerTest {

    @Mock
    lateinit var onClickContributorListener: MainEpoxyController.OnClickContributorListener

    private lateinit var mainEpoxyController: MainEpoxyController

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mainEpoxyController = MainEpoxyController(onClickContributorListener)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun initMainEpoxyController_confirmDataIsEmpty() {
        assertThat(mainEpoxyController.data).isEmpty()
    }

    @Test
    fun setData_confirmDataHasGivenData() {
        val data = listOf(
            Contributor("test1", 1, "url1", 100),
            Contributor("test2", 2, "url2", 200)
        )

        assertThat(mainEpoxyController.data).isEmpty()
        mainEpoxyController.setData(data)
        assertThat(mainEpoxyController.data).containsExactlyElementsOf(data)
    }
}