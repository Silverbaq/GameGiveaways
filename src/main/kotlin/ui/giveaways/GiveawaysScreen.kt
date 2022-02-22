package ui.giveaways

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import data.network.RetrofitBuilder
import data.network.api.GamerpowerService
import data.network.response.GiveAwayItem
import data.repositories.GiveAwayRepository
import org.koin.java.KoinJavaComponent.inject
import utils.NetworkImage.loadNetworkImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GiveawaysScreen(onItemClicked: (Int) -> Unit) {
    val giveAwayViewModel: GiveawayViewModel by inject(GiveawayViewModel::class.java)

    Column(modifier = Modifier.background(Color.DarkGray).fillMaxSize()) {
        GiveawayFilters(giveAwayViewModel)
        LazyVerticalGrid(
            cells = GridCells.Fixed(3),
            contentPadding = PaddingValues(8.dp)
        ) {
            itemsIndexed(giveAwayViewModel.giveawaysState.value) { index, item ->
                GiveawayItemView(item) { onItemClicked(item.id) }
            }
        }
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
fun GiveawayFilters(giveawayViewModel: GiveawayViewModel) {
    Column(modifier = Modifier) {
        Text("Filters")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Column(modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("PC")
                Checkbox(
                    checked = giveawayViewModel.filterPCState.value,
                    onCheckedChange = { giveawayViewModel.onFilterPCClicked() },
                )
            }
            Column(modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Steam")
                Checkbox(
                    checked = false,
                    onCheckedChange = {},
                )
            }
            Column(modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Epic")
                Checkbox(
                    checked = false,
                    onCheckedChange = {},
                )
            }
            Column(modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("ubisoft")
                Checkbox(
                    checked = false,
                    onCheckedChange = {},
                )
            }
            Column(modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("gog")
                Checkbox(
                    checked = false,
                    onCheckedChange = {},
                )
            }
            Column(modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("itchio")
                Checkbox(
                    checked = false,
                    onCheckedChange = {},
                )
            }
            Column(modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("ps4")
                Checkbox(
                    checked = false,
                    onCheckedChange = {},
                )
            }



            Column(modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("ps5")
                Checkbox(
                    checked = false,
                    onCheckedChange = {},
                )
            }
            Column(modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("xbox-one")
                Checkbox(
                    checked = false,
                    onCheckedChange = {},
                )
            }
            Column(modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("xbox-series-xs")
                Checkbox(
                    checked = false,
                    onCheckedChange = {},
                )
            }
            Column(modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("switch")
                Checkbox(
                    checked = false,
                    onCheckedChange = {},
                )
            }
            Column(modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("android")
                Checkbox(
                    checked = false,
                    onCheckedChange = {},
                )
            }
            Column(modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("ios")
                Checkbox(
                    checked = false,
                    onCheckedChange = {},
                )
            }
            Column(modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("vr")
                Checkbox(
                    checked = false,
                    onCheckedChange = {},
                )
            }
            Column(modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("battlenet")
                Checkbox(
                    checked = false,
                    onCheckedChange = {},
                )
            }
            Column(modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("origin")
                Checkbox(
                    checked = false,
                    onCheckedChange = {},
                )
            }
            Column(modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("drm-free")
                Checkbox(
                    checked = false,
                    onCheckedChange = {},
                )
            }
            Column(modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("xbox-360")
                Checkbox(
                    checked = false,
                    onCheckedChange = {},
                )
            }
        }
    }
}

@Preview
@Composable
fun GiveawayFiltersPreview() {
    GiveawayFilters(GiveawayViewModel(GiveAwayRepository(RetrofitBuilder.gamerPowerService)))
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