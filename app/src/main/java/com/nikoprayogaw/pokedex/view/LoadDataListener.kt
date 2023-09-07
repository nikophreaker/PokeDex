package com.nikoprayogaw.pokedex.view

interface LoadDataListener {
    fun onDataLoad()
    fun onNotAvailable()
    fun onError(msg: String?)
    fun onDataResumeLoad()
    fun onNotAvailableResume()
    fun onErrorResume(msg: String?)
}