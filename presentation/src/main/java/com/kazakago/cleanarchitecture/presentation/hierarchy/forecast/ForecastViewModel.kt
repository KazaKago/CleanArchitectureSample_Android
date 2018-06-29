package com.kazakago.cleanarchitecture.presentation.hierarchy.forecast

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.domain.model.weather.Forecast
import com.kazakago.cleanarchitecture.domain.model.weather.Weather
import com.kazakago.cleanarchitecture.domain.usecase.weather.GetWeatherUseCase
import com.kazakago.cleanarchitecture.presentation.livedata.nonnulllivedata.NonNullLiveData
import com.kazakago.cleanarchitecture.presentation.livedata.nonnulllivedata.NonNullLiveEvent
import com.kazakago.cleanarchitecture.presentation.livedata.liveevent.UnitLiveEvent
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

class ForecastViewModel(application: Application,
                        private val getWeatherUseCase: GetWeatherUseCase,
                        private val city: City) : AndroidViewModel(application), ForecastRecyclerAdapter.Listener {

    val title = MutableLiveData<CharSequence>()
    val finish = UnitLiveEvent()
    val weather = MutableLiveData<Weather>()
    val isLoading = NonNullLiveData(false)
    val showToast = NonNullLiveEvent<String>()

    init {
        title.value = city.name
        fetchWeather()
    }

    fun onClickBackIcon() {
        finish.call()
    }

    private fun fetchWeather() = launch(UI) {
        isLoading.value = true
        try {
            weather.value = async { getWeatherUseCase.execute(city.id) }.await()
        } catch (exception: Exception) {
            weather.value = null
            showToast.call(exception.localizedMessage)
        }
        isLoading.value = false
    }


    //region ForecastRecyclerAdapter.Listener

    override fun onItemClick(forecast: Forecast) {
        showToast.call(forecast.telop)
    }

    //endregion

}