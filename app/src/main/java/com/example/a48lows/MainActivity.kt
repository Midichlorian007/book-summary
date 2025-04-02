package com.example.a48lows

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Layout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.a48lows.ui.theme._48lowsTheme
import com.example.a48lows.ui.theme.Lows
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Scaffold(
                modifier = Modifier
                    .safeDrawingPadding()
                    .fillMaxSize()
            ) {
                MainLogic1()
            }
        }
    }
}

@Composable
fun MainLogic1() {
    val listState = rememberLazyListState()

    val coroutineScope = rememberCoroutineScope()


    val lows = Lows()

    var selectedItem by remember { mutableStateOf(lows.getAll()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
    ) {
        Box( // Upper List window
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color.LightGray, shape = RoundedCornerShape(10.dp))
        ) {
            LazyColumn(
                state = listState,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,

                modifier = Modifier
                    .fillMaxSize()
                    .border(1.dp, Color.DarkGray, shape = RoundedCornerShape(10.dp))
                    .background(Color(0xFF797979), shape = RoundedCornerShape(10.dp))
                    .padding(start = 10.dp, end = 10.dp)

            ) {
                items(selectedItem) { lawText ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                            .border(
                                1.dp, Color.Black, shape = RoundedCornerShape(10.dp)
                            )
                            .background(
                                Color(0xFFFFFFFF), shape = RoundedCornerShape(10.dp)
                            )
                            .padding(10.dp),
                    ) {
                        Text(
                            text = lawText,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.background(Color(0xFFFFFFFF))

                        )
                    }

                }
            }
        }


        Box(
            modifier = Modifier
                .padding(top = 10.dp)
                .height(75.dp)
                .fillMaxWidth()
                .border(
                    1.dp, Color.Black, shape = RoundedCornerShape(10.dp)
                )
                .background(
                    Color(0xFFD2D2D2), shape = RoundedCornerShape(10.dp)
                )
                .padding(top = 5.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Button(
                    modifier = Modifier
                        .height(40.dp)
                        .width(IntrinsicSize.Max),
                    onClick = {
                        coroutineScope.launch {
                            listState.animateScrollToItem(index = (0 until  lows.lows.size).random())
                        }

                        selectedItem = listOf(lows.getRandom()).toTypedArray()

                    }, colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1EA5FC), contentColor = Color(0xFFFFFFFF)
                    )
                ) {
                    Text(text = "GET RANDOM")
                }

                Button(
                    modifier = Modifier
                        .height(40.dp)
                        .width(IntrinsicSize.Max),
                    onClick = {

                        selectedItem = lows.getAll()
                    }, colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1EA5FC), contentColor = Color(0xFFFFFFFF)
                    )
                ) {
                    Text(text = "GET ALL")
                }

            }
        }
    }
}


@Composable
fun MainLogic() {

    val lows = Lows()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.SpaceBetween
    ) {


        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,

            modifier = Modifier
                .fillMaxSize()
//                .padding(10.dp)
//                .border(2.dp, Color.DarkGray )
                .background(Color(0xFFD93030))
                .padding(bottom = 10.dp)

        ) {
            items(lows.getAll()) { lawText ->
                Text(
                    text = lawText, modifier = Modifier
                        .fillMaxWidth()
//                        .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                        .border(
                            1.dp, Color.Black, shape = RoundedCornerShape(10.dp)
                        )
                        .background(
                            Color(0xFFFFFFFF), shape = RoundedCornerShape(10.dp)
                        )
                        .padding(10.dp), textAlign = TextAlign.Center
                )
            }
        }


    }
}

