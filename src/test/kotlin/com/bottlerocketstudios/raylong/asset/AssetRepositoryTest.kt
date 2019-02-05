package com.bottlerocketstudios.raylong.asset

//import com.bottlerocketstudios.raylong.AbstractSpringIntegrationTest
import com.bottlerocketstudios.raylong.AssetAttributes
import com.bottlerocketstudios.raylong.ShowAssetManager
import org.assertj.core.api.Assertions.assertThat as assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest(classes = [ShowAssetManager::class])
@ComponentScan(basePackages = ["com.bottlerocketstudios.raylong"])
@EnableJpaRepositories(basePackages = ["com.bottlerocketstudios.raylong"])
@EntityScan(basePackages = ["com.bottlerocketstudios.raylong"])
@EnableAutoConfiguration
@RunWith(SpringRunner::class)
open class AssetRepositoryTest {

    private val log = LoggerFactory.getLogger(AssetRepositoryTest::class.java)

    @Autowired
    private lateinit var assetRepository: AssetRepository

    @Before
    fun setUp() {
        val container = Asset(
                id = 1L,
                version = 0L,
                name = "test asset container",
                attributes = hashMapOf(AssetAttributes.ID to "1", AssetAttributes.NAME to "test name")//,
        )
        val savedContainer = assetRepository.save(container)
        assetRepository.flush()
        log.info("Saved container to repo: {}", savedContainer)
    }

    @Test
    fun `Get Asset From Repo and validate attributes`() {
        val container: Asset? = assetRepository.getOne(1L)
        assertThat(container).isNotNull
        assertThat(container?.id).isNotNull()
        assertThat(container?.createdDate).isNotNull()
        assertThat(container?.modifiedDate).isNotNull()
        assertThat(container?.attributes).isNotNull
        assertThat(container?.attributes).isNotEmpty
        assertThat(container?.attributes).isEqualTo(hashMapOf(AssetAttributes.ID to "1", AssetAttributes.NAME to "test name"))
//        assertThat(container?.images).isNotNull
//        assertThat(container?.images).isNotEmpty
//        assertThat(container?.ads).isNotNull
//        assertThat(container?.ads).isNotEmpty

        log.info("Retrieved container from repo: {}", container)
    }
}