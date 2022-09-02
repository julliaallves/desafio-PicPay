package com.picpay.desafio.android

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp //indica q o app como um to.do pode utilizar o Hilt
class ToDo : Application() {
}