package com.example.siappilih.util

import java.util.concurrent.Executors
import java.util.concurrent.ExecutorService

object AppExecutorService {
    val executorService: ExecutorService = Executors.newSingleThreadExecutor()
}