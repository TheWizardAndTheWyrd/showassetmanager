package com.bottlerocketstudios.raylong.video

import com.fasterxml.jackson.annotation.JsonProperty

enum class VideoAttributes(val enumVal: String) {
    @JsonProperty("movie")
    MOVIE ("movie") {
        override fun valueOfEnum(enumVal: String): VideoAttributes? {
            if (enumVal == this.enumVal) return MOVIE

            return null
        }

    },

    @JsonProperty("episode")
    EPISODE("episode") {
        override fun valueOfEnum(enumVal: String): VideoAttributes? {
            if (enumVal == this.enumVal) return EPISODE

            return null
        }

    },

    @JsonProperty("clip")
    CLIP("clip") {
        override fun valueOfEnum(enumVal: String): VideoAttributes? {
            if (enumVal == this.enumVal) return CLIP

            return null
        }
    };

    abstract fun valueOfEnum(enumVal: String): VideoAttributes?
}