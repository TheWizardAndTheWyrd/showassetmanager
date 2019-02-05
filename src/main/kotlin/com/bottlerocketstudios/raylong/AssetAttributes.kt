package com.bottlerocketstudios.raylong

import com.fasterxml.jackson.annotation.JsonProperty

enum class AssetAttributes(val enumVal: String) {
    @JsonProperty("id")
    ID ("id") {
        override fun valueOfEnum(enumVal: String): AssetAttributes? {
            if (enumVal == this.enumVal) return ID

            return null
        }

    },

    @JsonProperty("name")
    NAME("name") {
        override fun valueOfEnum(enumVal: String): AssetAttributes? {
            if (enumVal == this.enumVal) return NAME

            return null
        }

    },

    @JsonProperty("type")
    TYPE("type") {
        override fun valueOfEnum(enumVal: String): AssetAttributes? {
            if (enumVal == this.enumVal) return TYPE

            return null
        }

    },

    @JsonProperty("url")
    URL("url") {
        override fun valueOfEnum(enumVal: String): AssetAttributes? {
            if (enumVal == this.enumVal) return URL

            return null
        }

    },

    @JsonProperty("expiry")
    EXPIRY("expiry") {
        override fun valueOfEnum(enumVal: String): AssetAttributes? {
            if (enumVal == this.enumVal) return EXPIRY

            return null
        }

    };

    abstract fun valueOfEnum(enumVal: String): AssetAttributes?
}