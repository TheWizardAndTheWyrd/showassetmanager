package com.bottlerocketstudios.raylong

import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication

open class ShowAssetManager {

    object App {

        private val log = LoggerFactory.getLogger(ShowAssetManager::class.java)

        @JvmStatic
        fun main(args: Array<String>) {
            log.info("Starting {}...", ShowAssetManager::class.java)
            SpringApplication.run(ShowAssetManager::class.java)
        }
    }
}

