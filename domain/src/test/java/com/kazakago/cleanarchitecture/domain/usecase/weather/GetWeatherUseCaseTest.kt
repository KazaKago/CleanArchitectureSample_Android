package com.kazakago.cleanarchitecture.domain.usecase.weather

import com.kazakago.cleanarchitecture.domain.di.dataStubModule
import com.kazakago.cleanarchitecture.domain.di.domainModule
import com.kazakago.cleanarchitecture.domain.di.webStubModule
import com.kazakago.cleanarchitecture.domain.usecase.city.GetCityUseCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

class GetWeatherUseCaseTest : KoinTest {

    private val getCityUseCase: GetCityUseCase by inject()
    private val getWeatherUseCase: GetWeatherUseCase by inject()

    @Before
    fun before() {
        startKoin {
            modules(
                listOf(
                    domainModule,
                    webStubModule,
                    dataStubModule
                )
            )
        }
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun valid() = runBlocking {
        val cityList = getCityUseCase()
        val weather = getWeatherUseCase(cityId = cityList.first().id)
//        Assert.assertEquals(10, appInfo.versionCode.value)
//        Assert.assertEquals("1.1.0", appInfo.versionName.value)
    }

}