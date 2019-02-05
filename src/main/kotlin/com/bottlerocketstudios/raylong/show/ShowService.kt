package com.bottlerocketstudios.raylong.show

import com.bottlerocketstudios.raylong.image.Image
import com.bottlerocketstudios.raylong.image.ImageRepository
//import com.bottlerocketstudios.raylong.repo.RepoService // don't do this =)
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.ComponentScan
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@ComponentScan(basePackages = ["com.bottlerocketstudios.raylong"])
@Service
open class ShowService {

//    private val log = LoggerFactory.getLogger(ShowService::class.java)

    @Autowired
    lateinit var imageRepository: ImageRepository

    @Autowired
    lateinit var showRepository: ShowRepository

    fun save(show: Show?) : Show? {
         show?.let {
             show.images?.let {
                 imageRepository.saveAll(show.images!!)
                 populateImageIds(show)
             }
         }

        return showRepository.saveAndFlush(show)
    }

    fun findOneShowById(id: Long?) : Show? {
        return showRepository.findByShowId(id)
    }

    fun populateImageIds(show: Show?) {

        show?.images?.let {

//            log.info("Attempting to populate IDs from: {}...", show)

            show.imageIds = null // refresh it completely

            val ids: Set<Long?> = show.images!!
                    .stream()
                    .map { i -> i?.id }
                    .collect(Collectors.toSet())
                    .toSet()

//            log.info("Collected image id: {}", ids)

            show.imageIds = ids
        }
    }

    open fun populateImagesFromIds(show: Show?) {

//        log.info("Attempting to populate Images from: {}...", show?.imageIds)

        show?.imageIds?.let {
            val images = mutableSetOf<Image?>()

            show.imageIds?.forEach { i -> i?.let {images.add(imageRepository.findByImageId(i)) }}

//            log.info("Retrieved images: {}", images)

            show.images = images.toHashSet()
        }
    }
}