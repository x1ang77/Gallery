package com.xiangze.gallery

import android.app.Application
import java.io.File

class MyApplication: Application() {
    var images: List<File> = listOf()
}