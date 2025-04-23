package com.example.expandablecard

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expandablecard.ui.theme.ExpandableCardTheme

@Composable
fun ExpandableCard(modifier: Modifier = Modifier) {
    var expandedState by remember { mutableStateOf(false) }
    val msg = if(expandedState) "The card is expanded" else "The card is collapse"
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState()) // Make the entire card scrollable
            .padding(16.dp) // Add padding around the card
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                ),
            shape = RoundedCornerShape(10.dp),
            //onClick = { expandedState = !expandedState }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .background(Color.DarkGray)
            ) {
                // Title Row
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 15.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "To Dear Peter!",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.LightGray
                    )
                    IconButton(

                        onClick =
                        {

                            Log.d("Expand The Card",msg )
                            expandedState = !expandedState
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = "Toggle Arrow",
                            modifier = Modifier.graphicsLayer(rotationZ = if (expandedState) 180f else 0f),
                            tint = if (expandedState) Color.Black else Color.Gray
                        )
                    }
                }

                // Expandable Content
                if (expandedState) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        Text(
                            text = "This is the ultimate guide to the celebration you've been waiting for! Our party promises an unforgettable experience filled with laughter, joy, and cherished moments. As you step into the venue, you'll be greeted by a vibrant ambiance featuring dazzling decorations, a sparkling array of lights, and a playlist of carefully curated music to set the mood.\n" +
                                    "\n" +
                                    "The evening kicks off with a welcome drink, where you can sip on signature cocktails or refreshing mocktails crafted by our expert bartenders. The appetizers, a medley of both local and international flavors, will tantalize your taste buds and leave you craving more.\n" +
                                    "\n" +
                                    "Moving forward, the highlight of the evening will be the grand buffet, featuring an extensive selection of dishes. From gourmet entrées to classic comfort foods, and a variety of vegetarian, vegan, and non-vegetarian options, there’s something to satisfy every palate. Save room for the dessert station, which includes artisanal cakes, handcrafted chocolates, and a live ice cream counter.\n" +
                                    "\n" +
                                    "Entertainment is at the heart of this party. Get ready for live performances from talented musicians, a surprise DJ set that will keep you dancing all night, and fun games that include prizes for the winners. For those looking to capture memories, a themed photo booth with quirky props will ensure you go home with snapshots of all the good times.\n" +
                                    "\n" +
                                    "The kids are not left out either! A dedicated play area with games, activities, and supervision ensures the little ones are just as entertained as the adults.\n" +
                                    "\n" +
                                    "As the night progresses, we’ll have a special toast to celebrate the occasion, followed by a spectacular fireworks display that lights up the sky. This is your chance to mingle, network, or simply relax and soak in the lively atmosphere surrounded by friends, family, and new acquaintances.\n" +
                                    "\n" +
                                    "We encourage everyone to dress to impress and bring their best vibes to the party. Whether you’re dancing, dining, or simply enjoying the company of loved ones, this event is all about creating unforgettable memories. So come prepared to immerse yourself in an evening of fun, laughter, and celebration like no other!",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Cyan
                        )
                        Text("Hello")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExpandableCardPreview() {
    ExpandableCardTheme {
        ExpandableCard()
    }
}
