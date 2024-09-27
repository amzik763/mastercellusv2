package com.amzi.mastercellusv2.repository

import com.amzi.mastercellusv2.networks.HomeAutoApi
import com.amzi.mastercellusv2.utility.showLogs

class HomeAutoRepo(homeAutoApi: HomeAutoApi) {

    init {
        showLogs("Home Repo:","Home Repo Created")
    }


}