package com.example.videotesttask.ui.screens.feed.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.videotesttask.R
import com.example.videotesttask.domain.models.Item
import com.example.videotesttask.ui.screens.content.ContentScreen

@Composable
fun PreviewCard(
    item: Item
) {
    val navigator = LocalNavigator.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clickable { navigator?.push(ContentScreen(item)) },
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Gray
        ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .weight(5f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.padding(5.dp),
                        text = item.title ?: stringResource(id = R.string.empty),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                PreviewImage(
                    modifier = Modifier
                        .weight(4f)
                        .padding(vertical = 15.dp, horizontal = 5.dp)
                        .aspectRatio(ratio = 1f)
                        .clip(RoundedCornerShape(5.dp)),
                    imageUrl = item.preview
                )
            }

        }
    }
}
