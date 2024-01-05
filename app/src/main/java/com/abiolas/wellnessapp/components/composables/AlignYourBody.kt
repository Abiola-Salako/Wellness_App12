package com.abiolas.wellnessapp.components.composables

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.abiolas.wellnessapp.R

data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)

@Composable
fun AlignYourBodyElement(
    item:DrawableStringPair,
    modifier: Modifier = Modifier

){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(item.drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(item.text),
            modifier = Modifier
                .paddingFromBaseline(top = 24.dp, bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
private val alignYourBodyData = listOf(
    R.drawable.inversion_real to R.string.inversion_text,
    R.drawable.quick_yoga to R.string.quick_yoga,
    R.drawable.stretch_yg to R.string.stretching,
    R.drawable.tabata_yg to R.string.tabata,
    R.drawable.hiit_yg to R.string.hiit,
    R.drawable.prenatal_yg to R.string.pre_natal_yoga
).map {DrawableStringPair(it.first,it.second) }

@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier
){
    LazyRow(
        horizontalArrangement= Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ){
        items(alignYourBodyData){ item ->
            AlignYourBodyElement(item)
        }
    }
}