package ui.giveawaydetails

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.network.response.GiveAwayItem
import org.koin.java.KoinJavaComponent
import utils.BrowserURL
import utils.NetworkImage
import utils.PreviewHelper
import java.net.URI
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@Composable
fun GiveawayDetailsScreen(itemId: Int, onBackClicked: () -> Unit) {
    val giveAwayDetailsViewModel: GiveawayDetailsViewModel by KoinJavaComponent.inject(GiveawayDetailsViewModel::class.java)
    giveAwayDetailsViewModel.onViewReady(itemId)

    Column(modifier = Modifier.background(Color.DarkGray).fillMaxSize().padding(8.dp)) {
        Button(onClick = { onBackClicked() }) {
            Text("Back")
        }
        if (giveAwayDetailsViewModel.giveawayDetails.value.id != 0) {
            GiveawayDetailsHeader(giveAwayDetailsViewModel.giveawayDetails.value)
            GiveawayDetailsDescription(giveAwayDetailsViewModel.giveawayDetails.value)
            GiveawayActions(giveAwayDetailsViewModel.giveawayDetails.value) { BrowserURL.openWebpage(URI(giveAwayDetailsViewModel.giveawayDetails.value.openGiveawayUrl)) }
        }
    }
}

@Composable
fun GiveawayDetailsHeader(giveAwayItem: GiveAwayItem) {
    Column(modifier = Modifier) {
        Row() {
            Image(
                modifier = Modifier
                    .width(300.dp)
                    .padding(4.dp)
                    .clip(RoundedCornerShape(8.dp)),
                bitmap = NetworkImage.loadNetworkImage(giveAwayItem.thumbnail),
                contentDescription = ""
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Row(modifier = Modifier.padding(top = 8.dp, start = 4.dp)) {
                    Text(
                        text = giveAwayItem.title,
                        fontSize = 24.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.LightGray
                    )
                }
                Row(modifier = Modifier.padding(top = 8.dp, start = 4.dp)) {
                    Text(
                        text = giveAwayItem.type + " | ",
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.LightGray
                    )
                    Text(
                        text = giveAwayItem.platforms,
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.LightGray
                    )
                }
                Row(modifier = Modifier.padding(top = 8.dp, start = 4.dp)) {
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
            }
        }
        Divider(color = Color.Gray, thickness = 2.dp)
    }
}

@Composable
fun GiveawayDetailsDescription(giveAwayItem: GiveAwayItem) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = giveAwayItem.description,
            color = Color.LightGray
        )
        Text(
            modifier = Modifier.padding(top = 8.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            text = "Instructions:",
            color = Color.LightGray
        )
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = giveAwayItem.instructions,
            color = Color.LightGray
        )
    }
}

@Composable
fun GiveawayActions(giveAwayItem: GiveAwayItem, onGetLootClicked: () -> Unit) {
    val daysLeft = if (giveAwayItem.endDate.length > 16) {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val localDateTime = LocalDateTime.parse(giveAwayItem.endDate, formatter)
        ChronoUnit.DAYS.between(LocalDateTime.now(), localDateTime)
    } else {
        ""
    }

    Column(modifier = Modifier.background(Color.DarkGray).fillMaxWidth()) {
        Column(
            modifier = Modifier
                .width(400.dp)
                .height(150.dp)
                .padding(top = 8.dp, bottom = 8.dp)
                .border(1.dp, color = Color.Black, shape = RoundedCornerShape(8.dp))
                .background(Color(red = 54, green = 57, blue = 64))
                .align(Alignment.CenterHorizontally)
        ) {
            Row {
                Box(
                    modifier = Modifier
                        .width(200.dp)
                        .height(50.dp)
                        .padding(start = 16.dp, end = 8.dp, top = 16.dp)
                        .background(Color(red = 54, green = 57, blue = 64))
                        .border(1.dp, color = Color.Black, shape = RoundedCornerShape(4.dp))
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "$daysLeft days left",
                        color = Color.LightGray
                    )
                }

                Box(
                    modifier = Modifier
                        .width(200.dp)
                        .height(50.dp)
                        .padding(start = 8.dp, end = 16.dp, top = 16.dp)
                        .background(Color(red = 54, green = 57, blue = 64))
                        .border(1.dp, color = Color.Black, shape = RoundedCornerShape(4.dp))
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "${giveAwayItem.users}+ Collected",
                        color = Color.LightGray
                    )
                }
            }
            Button(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 16.dp, end = 16.dp),
                colors = ButtonDefaults.buttonColors(contentColor = Color.White, backgroundColor = Color(255, 165, 0)),
                onClick = { onGetLootClicked() }
            ) {
                Text("Get Loot")
            }
        }
    }
}

@Preview
@Composable
fun GiveawayActionsPreview() {
    GiveawayActions(PreviewHelper.giveAwayItem) { }
}

@Preview
@Composable
fun GiveawayDetailsDescriptionPreview() {
    GiveawayDetailsDescription(PreviewHelper.giveAwayItem)
}

@Preview
@Composable
fun GiveawayDetailsHeaderPreview() {
    GiveawayDetailsHeader(PreviewHelper.giveAwayItem)
}

@Preview
@Composable
fun GiveawayDetailsScreenPreview() {
    GiveawayDetailsScreen(0) { }
}
