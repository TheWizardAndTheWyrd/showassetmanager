package com.bottlerocketstudios.raylong.ad

import com.bottlerocketstudios.raylong.asset.Asset
import com.bottlerocketstudios.raylong.container.BaseDTO
import com.bottlerocketstudios.raylong.container.ContainerAttributes
import com.fasterxml.jackson.databind.ObjectMapper
import java.util.concurrent.ConcurrentSkipListSet

data class AdDTO(
        val ad: Ad,
        val assets: ConcurrentSkipListSet<Asset>,
        val metadata: ContainerAttributes
) : Comparable<AdDTO>, BaseDTO() {
    override fun compareTo(other: AdDTO): Int {
        this.ad.id?.let {

            other.ad.id?.let {
                return when {
                    this.ad.id == other.ad.id -> {
                        0
                    }
                    this.ad.id!! > other.ad.id!! -> {
                        1
                    }
                    this.ad.id!! < other.ad.id!! -> {
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