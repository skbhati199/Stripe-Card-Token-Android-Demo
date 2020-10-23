package com.android.stripenewdemo

import android.app.Application
import com.stripe.android.PaymentConfiguration

class DemoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        PaymentConfiguration.init(
            BuildConfig.API_KEY
        );
    }
}