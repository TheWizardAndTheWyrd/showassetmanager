package com.bottlerocketstudios.raylong.container

import com.bottlerocketstudios.raylong.ad.Ad
import com.bottlerocketstudios.raylong.ad.AdRepository
import com.bottlerocketstudios.raylong.asset.Asset
import com.bottlerocketstudios.raylong.asset.AssetRepository
import com.bottlerocketstudios.raylong.image.Image
import com.bottlerocketstudios.raylong.image.ImageRepository
import com.bottlerocketstudios.raylong.show.Show
import com.bottlerocketstudios.raylong.show.ShowRepository
import com.bottlerocketstudios.raylong.video.Video
import com.bottlerocketstudios.raylong.video.VideoRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.concurrent.ConcurrentSkipListSet
import java.util.stream.Collectors

@Service
open class ContainerService(
        @Autowired private val adRepository: AdRepository,
        @Autowired private val assetRepository: AssetRepository,
        @Autowired private val containerRepository: ContainerRepository,
        @Autowired private val imageRepository: ImageRepository,
        @Autowired private val showRepository: ShowRepository,
        @Autowired private val videoRepository: VideoRepository
) {

    private val log = LoggerFactory.getLogger(ContainerService::class.java)

    @Transactional
    open fun saveDTO(containerDTO: ContainerDTO) : ContainerDTO {

        // First, make a backup copy of our original object
        val backupContainerDTO = containerDTO.copy()
        assert(backupContainerDTO == containerDTO)
        log.info("Attempting to save containerDTO: {}", containerDTO)
        // TODO: ^^^ return the original if failed

        try {
            val container = containerDTO.container
            val savedContainer = containerRepository.save(container)
            log.info("Saved container: {}", savedContainer)

            val ads: List<Ad?> = containerDTO.ads.stream().map { x -> x.ad }.collect(Collectors.toList())
            val adsResult = adRepository.saveAll(ads)
            log.info("Save ads: {}", adsResult)

            val assets: List<Asset?> = containerDTO.assets.stream().map { x -> x.asset }.collect(Collectors.toList())
            val assetsResult = assetRepository.saveAll(assets)
            log.info("Saved assets: {}", assetsResult)

            val metaData = containerDTO.metadata
            // TODO: save metaData
            log.info("TODO: Save this metadata: {}", metaData)

            val images: List<Image?> = containerDTO.images.stream().map { x -> x.image }.collect(Collectors.toList())
            val imagesResult = imageRepository.saveAll(images)
            log.info("Saved images: {}", imagesResult)

            val shows: List<Show?> = containerDTO.shows.stream().map { x -> x.show }.collect(Collectors.toList())
            val showsResult = showRepository.saveAll(shows)
            log.info("Saved shows: {}", showsResult)

            val videos: List<Video?> = containerDTO.videos.stream().map { x -> x.video }.collect(Collectors.toList())
            val videosResult = videoRepository.saveAll(videos)
            log.info("Saved videos: {}", videosResult)

            val result = ContainerDTO(
                    container = savedContainer,
                    assets = ConcurrentSkipListSet(),
                    metadata = metaData,
                    ads = ConcurrentSkipListSet(),
                    images = ConcurrentSkipListSet(),
                    shows = ConcurrentSkipListSet(),
                    videos = ConcurrentSkipListSet()
            )

            // TODO: add result.
            log.info("TODO: finish building result")

            return containerDTO
        } catch (ex: Exception) {
            log.error("Unable to save containerDTO={} due to error: {} caused by: {}", containerDTO, ex.message, ex.cause)
            return backupContainerDTO
        }
    }

}