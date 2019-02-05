package com.bottlerocketstudios.raylong.ad

import com.bottlerocketstudios.raylong.asset.Asset
import com.bottlerocketstudios.raylong.container.ContainerAttributes
import com.fasterxml.jackson.databind.ObjectMapper
import java.util.concurrent.ConcurrentSkipListSet

data class AdDTO(
        val ad: Ad,
        val assets: ConcurrentSkipListSet<Asset>,
        val metadata: ContainerAttributes
) {

    override fun toString(): String = ObjectMapper().writeValueAsString(this)
}