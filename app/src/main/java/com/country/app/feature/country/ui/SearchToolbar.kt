package com.country.app.feature.country.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction.Companion.Done
import androidx.compose.ui.unit.dp
import com.country.app.R
import com.country.app.ui.theme.Gray

@Composable
fun SearchableToolbar(
    onQueryUpdated: (String) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(RoundedCornerShape(bottomEnd = 24.dp, bottomStart = 24.dp))
            .background(Gray),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        var myText by remember { mutableStateOf("") }
        val keyboardController = LocalSoftwareKeyboardController.current

        TextField(
            value = myText,
            onValueChange = {
                myText = it
                onQueryUpdated(it)
            },
            keyboardOptions = KeyboardOptions(imeAction = Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            ),
            textStyle = MaterialTheme.typography.headlineMedium,
            placeholder = {
                Text(
                    text = stringResource(id = R.string.filter_country_name),
                    style = MaterialTheme.typography.headlineSmall
                )
            },
            singleLine = true,
            maxLines = 1,
            shape = RoundedCornerShape(32.dp),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(80.dp)
        )
    }
}
