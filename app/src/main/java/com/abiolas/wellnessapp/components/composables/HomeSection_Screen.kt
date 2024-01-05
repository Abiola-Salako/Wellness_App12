package com.abiolas.wellnessapp.components.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.abiolas.wellnessapp.R
import com.abiolas.wellnessapp.ui.theme.MyWellnessAppTheme

@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
){
    Column(modifier) {
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}
@Composable
fun HomeScreen(modifier: Modifier = Modifier){
    MyWellnessAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(ContextCompat.getColor(LocalContext.current, R.color.pink))
        ) {
            Column(
                modifier
                    .verticalScroll(rememberScrollState())
            ){
                Spacer(Modifier.height(16.dp))
                SearchBar(Modifier.padding(horizontal = 16.dp))
                HomeSection(title = R.string.align_your_body){
                    AlignYourBodyRow()
                }
                HomeSection(title = R.string.favorite_collections){
                    FavouriteCollectionsGrid()
                }
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}