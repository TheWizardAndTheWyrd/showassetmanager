package com.bottlerocketstudios.raylong.show

//import java.util.concurrent.ConcurrentSkipListSet
import com.bottlerocketstudios.raylong.container.BaseDTO
import com.bottlerocketstudios.raylong.container.ContainerAttributes
import com.bottlerocketstudios.raylong.image.Image
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import org.hibernate.annotations.BatchSize
import org.springframework.context.annotation.ComponentScan
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*
import javax.persistence.*

@ComponentScan(basePackages = ["com.bottlerocketstudios.raylong"])
@Service
@Entity
@Table(name = "shows")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Show(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        @Version
        var version: Long? = null,

        var name: String? = null,

        @ElementCollection(fetch = FetchType.EAGER)
        @CollectionTable(name = "show_attributes")
        @MapKeyJoinColumn(name = "attribute_key")
        @Column(name = "attribute_value")
        @BatchSize(size = 20)
        var attributes: ContainerAttributes = null,

        @Transient // Don't persist this collection of objects - let each repo save their own objects
        var images: HashSet<Image?>? = null,

        @ElementCollection(fetch = FetchType.EAGER)
        var imageIds: Set<Long?>? = null,

//        @ElementCollection(fetch = FetchType.EAGER)
//        @CollectionTable(name = "ad_attributes")
//        @MapKeyJoinColumn(name = "attribute_key")
//        @Column(name = "attribute_value")
//        @BatchSize(size = 20)
//        var ads: ContainerAttributes = null,

        @Column(name = "created_date")
        var createdDate: Date? = null,

        @Column(name = "modified_date")
        var modifiedDate: Date? = null
) : Comparable<Show>, BaseDTO() {
    override fun compareTo(other: Show): Int {

        this.id?.let {

            other.id?.let {
                return when {
                    this.id == other.id -> {
                        0
                    }
                    this.id!! > other.id!! -> {
                        1
                    }
                    this.id!! < other.id!! -> {
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

    @PrePersist
    fun prePersist() {
        val now = Date.from(Instant.now())
        this.createdDate = now
        this.modifiedDate = this.createdDate
    }

    @PreUpdate
    fun preUpdate() {
        val now = Date.from(Instant.now())
        this.modifiedDate = now
    }

    /**
     * Override toString() to provide easy JSON
     */
    override fun toString(): String = ObjectMapper().writeValueAsString(this)
}
