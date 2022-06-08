package com.psvoid.coloniza.map.data.repositories

import com.psvoid.coloniza.map.data.database.CountriesDao
import com.psvoid.coloniza.map.data.database.CountryData

class CountriesRepository(private val countriesDao: CountriesDao) {

    suspend fun insert(countries: List<CountryData>) = countriesDao.insert(countries)
    suspend fun insert(country: CountryData) = countriesDao.insert(country)

    suspend fun getAll() = countriesDao.getAll()
    suspend fun getByCountry(countryCode: String) = countriesDao.getByCountry(countryCode)

}