package com.kazakago.cleanarchitecture.data.mapper.city

import com.kazakago.cleanarchitecture.data.entity.city.PrefEntity
import com.kazakago.cleanarchitecture.domain.mapper.EntityMapper
import com.kazakago.cleanarchitecture.domain.model.city.CityModel

object CityMapper : EntityMapper<PrefEntity, List<CityModel>> {

    override fun map(source: PrefEntity): List<CityModel> {
        val prefTitle = source.title
        return source.cityList.map {
            CityModel(
                    id = it.id,
                    name = prefTitle + " " + it.title)
        }
    }

}