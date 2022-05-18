package com.rohan.thegittrends.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.rohan.thegittrends.data.models.GitRepoItem
import com.rohan.thegittrends.utils.HexToJetpackColor

@Composable
fun RepoTile(item: GitRepoItem) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row {
                Image(
                    painter = rememberAsyncImagePainter(item.avatar),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(Modifier.width(8.0.dp))
                Text(text = item.author, style = TextStyle(fontSize = 12.sp))
            }
            Spacer(Modifier.height(8.0.dp))
            Text(
                text = item.name,
                style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold)
            )
            Spacer(Modifier.height(8.0.dp))
            Text(
                text = item.description,
                style = TextStyle(fontSize = 12.sp)
            )
            Spacer(Modifier.height(8.0.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .clip(CircleShape)
                            .background(HexToJetpackColor.getColor(item.languageColor))
                    )
                    Spacer(Modifier.width(8.0.dp))
                    Text(
                        text = item.language,
                        style = TextStyle(fontSize = 12.sp)
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star",
                        tint = Color.Yellow,
                        modifier = Modifier
                            .size(16.dp)
                    )
                    Spacer(Modifier.width(4.0.dp))
                    Text(
                        text = item.stars.toString(),
                        style = TextStyle(fontSize = 12.sp)
                    )
                }

            }

        }

    }

}