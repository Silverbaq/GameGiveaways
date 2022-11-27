package ui.giveaways

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.network.response.GiveAwayItem
import org.koin.java.KoinJavaComponent.inject
import ui.giveaways.filter.Filter
import ui.giveaways.filter.FilterView
import utils.NetworkImage.loadNetworkImage

@Composable
fun GiveawaysScreen(onItemClicked: (Int) -> Unit) {
    val giveAwayViewModel: GiveawayViewModel by inject(GiveawayViewModel::class.java)

    Column(modifier = Modifier.background(Color.DarkGray).fillMaxSize()) {
        GiveawayFilters(giveAwayViewModel.filters, giveAwayViewModel::onFilterClicked)

        if (giveAwayViewModel.isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }
        else {

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(8.dp)
            ) {
                itemsIndexed(giveAwayViewModel.giveawaysState) { _, item ->
                    GiveawayItemView(item) { onItemClicked(item.id) }
                }
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        giveAwayViewModel.onViewReady()
    }
}

@Composable
fun GiveawayItemView(giveAwayItem: GiveAwayItem, onViewOfferClicked: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .width(300.dp)
            .padding(4.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(red = 54, green = 57, blue = 64))
    ) {
        Image(bitmap = loadNetworkImage(giveAwayItem.thumbnail), contentDescription = "")
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = giveAwayItem.title,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 18.sp,
                fontFamily = FontFamily.Cursive
            )
            Row(modifier = Modifier.padding(top = 8.dp)) {
                Text(
                    text = "FREE",
                    color = Color.Yellow
                )
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = giveAwayItem.worth,
                    color = Color.LightGray,
                    textDecoration = TextDecoration.LineThrough,
                )
            }
            Row(modifier = Modifier.padding(top = 8.dp)) {
                Text(
                    text = giveAwayItem.description,
                    color = Color.LightGray,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Button(
                onClick = { onViewOfferClicked(giveAwayItem.id) },
                modifier = Modifier.padding(top = 8.dp).fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(contentColor = Color.Yellow, backgroundColor = Color.Transparent)
            ) {
                Text(
                    text = "View Offer",
                )
            }
            Row(modifier = Modifier.padding(top = 8.dp)) {
                Text(
                    text = "${giveAwayItem.users}+ Collected this loot!",
                    color = Color.LightGray,
                    fontSize = 11.sp
                )
            }
        }
    }
}

@Composable
fun GiveawayFilters(filterMap: Map<String, List<Filter>>, onFilterClicked: (filter: Filter) -> Unit) {
    val isVisible = remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp)) {
        Text(
            text = "Filters",
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier.clickable {
                isVisible.value = !isVisible.value
            }
        )
        AnimatedVisibility(
            visible = isVisible.value,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            Column {
                filterMap.map { (title, filters) ->
                    FilterRow(title, filters, onFilterClicked)
                }
            }
        }
    }
}

@Composable
fun FilterRow(title: String, filters: List<Filter>, onItemSelected: (Filter) -> Unit) {
    Column(

    ) {
        Text(
            text = title,
            color = Color.White,
            fontSize = 8.sp
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            filters.map { filter -> FilterView(filter, onItemSelected) }
        }
    }
}

@Preview
@Composable
fun FilterRowPreview() {
    val filters = listOf(
        Filter("PC", false, utils.GiveawayFilters.PC),
        Filter("Steam", true, utils.GiveawayFilters.STEAM)
    )
    FilterRow("something", filters) { }
}


@Preview
@Composable
fun GiveawayFiltersPreview() {
    val filters = mapOf(
        "Providers" to listOf(
            Filter("Epic", false, utils.GiveawayFilters.EPIC_GAMES_STORE),
            Filter("Origin", false, utils.GiveawayFilters.ORIGIN),
        ),
        "Platforms" to listOf(
            Filter("Android", false, utils.GiveawayFilters.ANDROID),
            Filter("Ios", false, utils.GiveawayFilters.IOS)
        )
    )

    val onFilterClicked: (Filter) -> Unit = {}

    GiveawayFilters(filters, onFilterClicked)
}

@Preview
@Composable
fun GiveawayItemViewPreview() {
    val giveAwayItem = GiveAwayItem(
        description = "Claim your Lords Mobile Special Gift Pack Key and unlock lots of in-game items for Lords Mobile (including Steam version and Mobile)!. Please note this giveaway is for new players only and you must redeem your key within 24 hours of registration.",
        endDate = "2022-05-31 23:59:00",
        gamerPowerUrl = "https://www.gamerpower.com/lords-mobile-special-gift-pack-key-giveaway",
        id = 1405,
        title = "Lords Mobile Special Gift Pack Key Giveaway",
        worth = "N/A",
        thumbnail = "https://www.gamerpower.com/offers/1/62106a5400751.jpg",
        image = "https://www.gamerpower.com/offers/1b/62106a5400751.jpg",
        instructions = "1. Log in to your free MMOBomb account.\r\n2. Click the button to unlock your key and follow the giveaway instructions to redeem your key.",
        openGiveawayUrl = "https://www.gamerpower.com/open/lords-mobile-special-gift-pack-key-giveaway",
        publishedDate = "2022-02-19 04:56:04",
        type = "DLC & Loot",
        platforms = "PC, Android, iOS",
        users = 420,
        status = "Active",
        openGiveaway = "https://www.gamerpower.com/open/lords-mobile-special-gift-pack-key-giveaway"
    )
    GiveawayItemView(giveAwayItem) { println(it) }
}

@Preview
@Composable
fun GiveAwayScreenPreview() {
    GiveawaysScreen {}
}