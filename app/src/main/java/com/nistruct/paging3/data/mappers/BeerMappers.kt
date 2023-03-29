package com.nistruct.paging3.data.mappers

import com.nistruct.paging3.data.local.BeerEntity
import com.nistruct.paging3.data.remote.BeerDto
import com.nistruct.paging3.domain.Beer

fun BeerDto.toBeerEntity(): BeerEntity{
    return BeerEntity(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        firstBrewed = first_brewed,
        imageUrl = image_url
    )
}

fun BeerEntity.toBeer(): Beer{
    return Beer(id,name,tagline,firstBrewed,description, imageUrl)
}