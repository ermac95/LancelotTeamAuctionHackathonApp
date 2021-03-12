package com.mycodeflow.lancelotteamauctionhackathonapp.domain.repository

import com.mycodeflow.lancelotteamauctionhackathonapp.domain.datasource.SampleDataSource
import javax.inject.Inject

class SampleRepository @Inject constructor(
    val dataSource: SampleDataSource
) {
    //for getting/setting list of advertisement in firestore
}