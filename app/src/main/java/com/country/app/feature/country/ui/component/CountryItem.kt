package com.country.app.feature.country.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.country.app.feature.country.domain.model.CountryData

@Composable
fun CountryItem(data: CountryData, onClick: (countryId: CountryData) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(data) },
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(2.dp),
        border = BorderStroke(0.5.dp, Black)
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

@Preview(showBackground = true)
@Composable
fun PreviewCountryItem() {
    val data =
        CountryData(id = 1, country = "Lima, Peru", latitude = -12.0464, longitude = -77.0428)
    CountryItem(data = data, onClick = {})
}