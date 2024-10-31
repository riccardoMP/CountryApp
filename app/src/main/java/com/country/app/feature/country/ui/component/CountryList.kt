package com.country.app.feature.country.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import com.country.app.feature.country.domain.model.CountryData

@Composable
fun CountryList(
    list: List<CountryData>,
    scrollState: LazyListState
) = LazyColumn(
    modifier = Modifier
        .fillMaxSize()
        .navigationBarsPadding(),
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp),
    state = scrollState,
) {
    items(
        items = list,
    ) { data ->
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(4.dp),
            colors = CardDefaults.cardColors(containerColor = White),
            elevation = CardDefaults.cardElevation(2.dp),
            border = BorderStroke(
                0.5.dp,
                Black
            )
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {
                with(data) {

                    Text(
                        text = country,
                        modifier = Modifier.padding(18.dp),
                        color = Black,
                        style = MaterialTheme.typography.titleLarge,
                    )
                    Text(
                        text = "$latitude, $longitude",
                        modifier = Modifier.padding(12.dp),
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
        }
    }
}
