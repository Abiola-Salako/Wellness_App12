package com.abiolas.wellnessapp.components.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.abiolas.wellnessapp.R

@Composable
fun FavouriteCollectionCard(
    item:DrawableStringPair,
    modifier: Modifier = Modifier
){
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(255.dp)
        ){
            Image(painter = painterResource(item.drawable) ,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp)
            )
            Text(text = stringResource(item.text),
                style= MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

    }

}

private val favoriteCollectionsData = listOf(
    R.drawable.short_mantra to R.string.short_mantras,
    R.drawable.natures_med to R.string.nature_meditations,
    R.drawable.stress_anxiety to R.string.stress_and_anxiety,
    R.drawable.self_masagge to R.string.self_massage,
    R.drawable.overwhelmed_png to R.string.overwhelmed,
    R.drawable.nightly_wind to R.string.nightly_wind_down
).map { DrawableStringPair(it.first, it.second) }

@Composable
fun FavouriteCollectionsGrid(
    // item:DrawableStringPair,
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.height(168.dp)
    ){
        items(favoriteCollectionsData){ item->
            FavouriteCollectionCard(item, Modifier.height(80.dp))

        }
    }
}