package com.kazakago.cleanarchitecture.web.api

import android.content.Context
import okhttp3.OkHttpClient

@Suppress("UNUSED_PARAMETER")
fun OkHttpClient.Builder.addFlipperOkhttpInterceptor(context: Context): OkHttpClient.Builder {
    //do nothing.
    return this
}