package com.beam.bingle

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform