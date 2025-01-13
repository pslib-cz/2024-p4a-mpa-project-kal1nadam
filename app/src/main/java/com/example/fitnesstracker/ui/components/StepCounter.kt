package com.example.fitnesstracker.ui.components

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun StepCounter() {
    val context = LocalContext.current
    val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    var stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
}