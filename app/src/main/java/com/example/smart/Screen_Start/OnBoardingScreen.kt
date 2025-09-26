package com.example.smart.Screen_Start

import android.R.attr.shape
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.smart.R
import java.nio.file.Files.size

@Composable
fun OnBoardingScreen() {
    val pagerState = rememberPagerState(0, 0F) {
        3
    }

    var resource = remember { 0 }
    var text = remember { "" }
    Box(modifier = Modifier.fillMaxHeight()) {
        HorizontalPager(state = pagerState) {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalArrangement = Arrangement.Center
                ) {


                    when (pagerState.currentPage) {
                        0 -> {
                            text = "First Page"
                            resource = R.drawable.ic_launcher_background
                        }

                        1 -> {
                            text = "SecondPage"
                            resource = R.drawable.ic_launcher_background
                        }

                        2 -> {
                            text = "ThirdPage"
                            resource = R.drawable.ic_launcher_background
                        }
                    }


                    Icon(
                        painterResource(id = resource), contentDescription = "OnBoardingScreen",
                        modifier = Modifier
                            .width(500.dp)
                            .height(500.dp) , tint = Color.Unspecified
                    )
                    Text(text = text)

                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                repeat(3) {
                    CustomIndicator(isSelected = pagerState.currentPage == it)
                }
            }


        }


    }

}

@Composable
fun CustomIndicator(isSelected: Boolean) {
    Box(
        modifier = Modifier
            .padding(2.dp)
            .background(
                color = if (isSelected) Color.Black else Color.LightGray,
                shape = CircleShape
            )
            .size(15.dp)
    ) {

    }
}