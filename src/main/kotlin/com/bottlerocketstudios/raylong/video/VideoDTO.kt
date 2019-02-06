package com.bottlerocketstudios.raylong.video

import com.bottlerocketstudios.raylong.asset.Asset
import com.bottlerocketstudios.raylong.container.BaseDTO
import com.bottlerocketstudios.raylong.container.ContainerAttributes
import com.fasterxml.jackson.databind.ObjectMapper
import java.util.concurrent.ConcurrentSkipListSet

data class VideoDTO(
        val video: Video,
        val assets: ConcurrentSkipListSet<Asset>,
        val metadata: ContainerAttributes
) : Comparable<VideoDTO>, BaseDTO() {
    override fun compareTo(other: VideoDTO): Int {
        this.video.id?.let {

            other.video.id?.let {
                return when {
                    this.video.id == other.video.id -> {
                        0
                    }
                    this.video.id!! > other.video.id!! -> {
                        1
                    }
                    this.video.id!! < other.video.id!! -> {
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