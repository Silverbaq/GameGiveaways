package ui.giveaways.filter

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import utils.GiveawayFilters

@Composable
fun FilterView(filter: Filter, onCheckedChange: (Filter) -> Unit) {
    val filerValue = remember { (mutableStateOf(filter.enabled)) }

    Column(
        modifier = Modifier.padding(4.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = filter.title,
            color = Color.White
        )
        Checkbox(
            checked = filerValue.value,
            onCheckedChange = {
                filerValue.value = it
                filter.enabled = it
                onCheckedChange(filter)
            },
            colors = CheckboxDefaults.colors(
                checkedColor = Color.LightGray,
                uncheckedColor = Color.White
            )
        )
    }
}

@Preview
@Composable
fun FilterViewPreview() {
    val filter = Filter("Steam", false, GiveawayFilters.STEAM)
    FilterView(filter) { checked -> println(checked) }
}
