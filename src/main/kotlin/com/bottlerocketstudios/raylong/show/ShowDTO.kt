package com.bottlerocketstudios.raylong.show

import com.bottlerocketstudios.raylong.asset.Asset
import com.bottlerocketstudios.raylong.container.BaseDTO
import com.bottlerocketstudios.raylong.container.ContainerAttributes
import com.fasterxml.jackson.databind.ObjectMapper
import java.util.concurrent.ConcurrentSkipListSet

data class ShowDTO(
        val show: Show,
        val assets: ConcurrentSkipListSet<Asset>,
        val metadata: ContainerAttributes
) : Comparable<ShowDTO>, BaseDTO() {
    override fun compareTo(other: ShowDTO): Int {
        this.show.id?.let {

            other.show.id?.let {
                return when {
                    this.show.id == other.show.id -> {
                        0
                    }
                    this.show.id!! > other.show.id!! -> {
                        1
                    }
                    this.show.id!! < other.show.id!! -> {
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