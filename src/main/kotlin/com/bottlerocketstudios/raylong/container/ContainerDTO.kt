package com.bottlerocketstudios.raylong.container

import com.bottlerocketstudios.raylong.ad.Ad
import com.bottlerocketstudios.raylong.ad.AdDTO
import com.bottlerocketstudios.raylong.asset.Asset
import com.bottlerocketstudios.raylong.asset.AssetDTO
import com.bottlerocketstudios.raylong.image.Image
import com.bottlerocketstudios.raylong.image.ImageDTO
import com.bottlerocketstudios.raylong.show.Show
import com.bottlerocketstudios.raylong.show.ShowDTO
import com.bottlerocketstudios.raylong.video.Video
import com.bottlerocketstudios.raylong.video.VideoDTO
import com.fasterxml.jackson.databind.ObjectMapper
import java.util.concurrent.ConcurrentSkipListSet

data class ContainerDTO( // TODO: implement Comparable<T>... lol
        val container: Container,
        val assets: ConcurrentSkipListSet<AssetDTO>,
        val metadata: ContainerAttributes,
        val ads: ConcurrentSkipListSet<AdDTO>,
        val images: ConcurrentSkipListSet<ImageDTO>,
        val shows: ConcurrentSkipListSet<ShowDTO>,
        val videos: ConcurrentSkipListSet<VideoDTO>
        ) {
    override fun toString(): String = ObjectMapper().writeValueAsString(this)
}