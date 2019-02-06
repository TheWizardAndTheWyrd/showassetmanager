package com.bottlerocketstudios.raylong.image

import com.bottlerocketstudios.raylong.asset.Asset
import com.bottlerocketstudios.raylong.container.BaseDTO
import com.bottlerocketstudios.raylong.container.ContainerAttributes
import com.fasterxml.jackson.databind.ObjectMapper
import java.util.concurrent.ConcurrentSkipListSet

data class ImageDTO(
        val image: Image,
        val assets: ConcurrentSkipListSet<Asset>,
        val metadata: ContainerAttributes
) : Comparable<ImageDTO>, BaseDTO() {
    override fun compareTo(other: ImageDTO): Int {
        this.image.id?.let {

            other.image.id?.let {
                return when {
                    this.image.id == other.image.id -> {
                        0
                    }
                    this.image.id!! > other.image.id!! -> {
                        1
                    }
                    this.image.id!! < other.image.id!! -> {
                        -1
                    }
                    else -> {
                        0
                    }
                }
            }
        }

        return 0
    }

    override fun toString(): String = ObjectMapper().writeValueAsString(this)
}