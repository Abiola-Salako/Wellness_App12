@file:OptIn(ExperimentalMaterial3WindowSizeClassApi::class)

package com.abiolas.wellnessapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.abiolas.wellnessapp.ui.theme.MyWellnessAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MyWellnessAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(ContextCompat.getColor(LocalContext.current,R.color.pink))
                ) {
                    val windowSizeClass = calculateWindowSizeClass(this)
                    WellnessApp (windowSizeClass)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier
){
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null

            )
        },
        colors = TextFieldDefaults.textFieldColors(
            unfocusedIndicatorColor = MaterialTheme.colorScheme.surface,
            focusedIndicatorColor = MaterialTheme.colorScheme.surface
        ),
        placeholder= {
                Text(stringResource(R.string.placeholder_searchh))
        },
        modifier= modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )
}
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
            FavouriteCollectionCard(item,Modifier.height(80.dp))

        }
    }
}
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
            color = Color(ContextCompat.getColor(LocalContext.current,R.color.pink))
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

@Composable
 fun WellAppBottomNavigation(modifier: Modifier = Modifier){
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_home)
                )
            },
            selected = true,
            onClick = {}
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_profile)
                )
            },
            selected = false,
            onClick = {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)

//@Composable
//fun WellnessAppPortrait() {
//    MyWellnessAppTheme {
//        Scaffold(
//            bottomBar = { WellAppBottomNavigation() }
//        ) { padding ->
//            HomeScreen(Modifier.padding(padding))
//        }
//    }
//}


@Composable
 fun WellnessNavigationRail(
    modifier: Modifier = Modifier
    ) {
    NavigationRail(
        modifier = modifier
            .padding(start = 8.dp, end = 8.dp),

    ) {

        Column(
            modifier = modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null
                    )
                },
                label = {
                    Text(text = stringResource(R.string.bottom_navigation_home))
                },
                selected = true,
                onClick = {}
            )
            Spacer(modifier = Modifier.height(8.dp))
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null
                    )
                },
                label = {
                    Text(text = stringResource(R.string.bottom_navigation_profile))
                },
                selected = false,
                onClick = {}
            )
        }
    }
}
@Composable
fun MyWellnessAppLandscape(){
    MyWellnessAppTheme {
        Surface (color = MaterialTheme.colorScheme.background){
            Row {
            WellnessNavigationRail()
            HomeScreen()
         }
       }
    }
}

//@Composable
//fun MyWellnessAppPortrait(){
//    MyWellnessAppTheme {
//        Surface (color = MaterialTheme.colorScheme.background){
//            Column{
//                WellnessNavigationRail()
//                HomeScreen()
//            }
//        }
//    }
//}

@Composable
fun WellnessAppPortrait() {
    MyWellnessAppTheme {
        Scaffold(
            bottomBar = { WellAppBottomNavigation() }
        ) { padding ->
            HomeScreen(Modifier.padding(padding))
        }
    }
}



@Composable
fun WellnessApp(windowSize: WindowSizeClass) {
    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            WellnessAppPortrait()
        }
        WindowWidthSizeClass.Expanded -> {
            MyWellnessAppLandscape()
        }
    }
}


//@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
//@Composable
//fun SearchBarPreview(){
//    MyWellnessAppTheme {
//        SearchBar(Modifier.padding(8.dp))
//    }
//}
//@Preview(widthDp = 360, heightDp = 640)
//@Composable
//fun WellnessAppPreview(){
//    WellnessApp(windowSizeClass)
//}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview
@Composable
fun WellnessAppPreview(){
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(ContextCompat.getColor(LocalContext.current, R.color.pink))
        ) {
            //val windowSizeClass = calculateWindowSizeClass(MainActivity())
           // WellnessApp(windowSizeClass)
            WellnessAppPortrait()


        }

}

//@Preview(showBackground = true, backgroundColor =  0xFFF0EAE2)
//@Composable
//fun AlignYourBodyElementPreview(){
//    MyWellnessAppTheme {
//        AlignYourBodyElement(item)
//
//        )
//    }
//}
//
//@Preview(showBackground = true, backgroundColor =  0xFFF0EAE2)
//@Composable
//fun FavoriteCollectionCardPreview(){
//    MyWellnessAppTheme {
//        FavouriteCollectionCard(modifier = Modifier.padding(8.dp)
//        )
//    }
//}
//
//@Preview(showBackground = true, backgroundColor =  0xFFF0EAE2)
//@Composable
//fun FavoriteCollectionsGridPreview(){
//    MyWellnessAppTheme {
//        FavouriteCollectionsGrid()
    //}
//}
//
//@Preview(showBackground = true, backgroundColor =  0xFFF0EAE2)
//@Composable
//fun AlignYourBodyRowPreview(){
//    MyWellnessAppTheme {
//        AlignYourBodyRow()
//    }
//}

//@Preview(showBackground = true, backgroundColor =  0xFFF0EAE2)
//@Composable
//fun HomeSectionPreview(){
//  MyWellnessAppTheme {
//    HomeSection()
//    }
//}

//@Preview(widthDp = 360, heightDp = 640)
//@Composable
//fun MyWellnessPreview(){
//
//}
