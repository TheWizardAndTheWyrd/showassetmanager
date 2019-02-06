package com.bottlerocketstudios.raylong.ad

import com.bottlerocketstudios.raylong.container.BaseDTO
import com.bottlerocketstudios.raylong.container.ContainerAttributes
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import org.hibernate.annotations.BatchSize
import java.time.Instant
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "ads")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Ad(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        @Version
        var version: Long? = null,

        var name: String? = null,

        var description: String? = null,

        @ElementCollection(fetch = FetchType.EAGER)
        @CollectionTable(name = "ad_attributes")
        @MapKeyJoinColumn(name = "attribute_key")
        @Column(name = "attribute_value")
        @BatchSize(size = 20)
        var attributes: ContainerAttributes = null,

        @Column(name = "created_date")
        var createdDate: Date? = null,

        @Column(name = "modified_date")
        var modifiedDate: Date? = null
) : Comparable<Ad>, BaseDTO() {
    override fun compareTo(other: Ad): Int {

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

    override fun toString(): String = ObjectMapper().writeValueAsString(this)
}
