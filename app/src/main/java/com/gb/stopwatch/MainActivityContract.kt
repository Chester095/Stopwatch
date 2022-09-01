package com.gb.stopwatch

import com.gb.stopwatch.domain.StopwatchListOrchestrator
import com.gb.stopwatch.domain.TimestampProvider

interface MainActivityContract {

    abstract class ViewModel :androidx.lifecycle.ViewModel(){

        abstract val timestampProvider: TimestampProvider
        abstract val stopwatchListOrchestrator: StopwatchListOrchestrator
    }
}