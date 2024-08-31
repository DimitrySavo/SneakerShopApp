package com.example.sneakershopapp.views

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.sneakershopapp.ui.theme.SneakerShopAppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HelloScreensPager(modifier: Modifier = Modifier, initialPage: Int = 0) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        val bottomButtonPadding = maxHeight * 0.02f
        val horizontalButtonPadding = maxWidth * 0.06f
        val scope = rememberCoroutineScope()
        val pagerState = rememberPagerState(initialPage = initialPage) {
            3
        }
        Column(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.weight(8f)) {
                HorizontalPager(state = pagerState, userScrollEnabled = false) { page: Int ->
                    when (page) {
                        0 -> FirstHelloScreen()
                        1 -> SecondHelloScreen()
                        2 -> ThirdHelloScreen()
                    }
                }
            }

            Box(modifier = Modifier.weight(1.5f)) {
                CustomPagerIndicator(
                    pagerState = pagerState, pageCount = 3,
                    indicatorShape = RoundedCornerShape(50),
                    indicatorHeight = 6.dp,
                    indicatorWidth = 30.dp,
                    activeColor = MaterialTheme.colorScheme.onPrimary,
                    inactiveColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.3f),
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                )

                Button(
                    onClick = {
                        scope.launch {
                            val nextPage = pagerState.currentPage + 1
                            if (nextPage > pagerState.pageCount - 1) {
                                //перемещает на окно регистрации и закрывает эту функцию
                            } else{
                                pagerState.animateScrollToPage(nextPage)
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            bottom = bottomButtonPadding,
                            start = horizontalButtonPadding,
                            end = horizontalButtonPadding
                        )
                        .align(Alignment.BottomCenter),
                    shape = RoundedCornerShape(13),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.background
                    )
                ) {
                    Text(
                        text = if(pagerState.currentPage == pagerState.pageCount - 1) "Начать" else "Дальше",
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomPagerIndicator(
    modifier: Modifier = Modifier,
    indicatorShape: Shape,
    indicatorHeight: Dp,
    indicatorWidth: Dp,
    pagerState: PagerState,
    pageCount: Int,
    activeColor: Color,
    inactiveColor: Color
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(pageCount) { page ->
            val isSelected = pagerState.currentPage == page
            val width by animateDpAsState(
                targetValue = if (isSelected) indicatorWidth * 1.5f else indicatorWidth,
                label = "Pager indicator"
            )
            Box(
                modifier = Modifier
                    .height(indicatorHeight)
                    .width(width)
                    .background(
                        color = if (isSelected) activeColor else inactiveColor,
                        shape = indicatorShape
                    )
            )
        }
    }
}


@Preview(device = "spec:width=411dp,height=891dp,dpi=420") // Pixel 4
@Preview(device = "spec:width=360dp,height=740dp,dpi=320") // Nexus 5
@Preview(device = "spec:width=320dp,height=480dp,dpi=160") // Small Phone
@Preview(device = "spec:width=600dp,height=1024dp,dpi=240") // 7-inch Tablet
@Preview(device = "spec:width=800dp,height=1280dp,dpi=240") // 10-inch Tablet
@Preview(device = "spec:width=1024dp,height=1366dp,dpi=264") // iPad Pro 10.5
@Preview(device = "spec:width=1440dp,height=2560dp,dpi=560") // Pixel XL
@Preview(device = "spec:width=1080dp,height=1920dp,dpi=480") // Full HD Phone
@Preview(device = "spec:width=1440dp,height=2960dp,dpi=560") // Galaxy S8
@Preview(device = "spec:width=768dp,height=1024dp,dpi=160") // Nexus 7
@Composable
private fun HelloPagerPreview() {
    SneakerShopAppTheme {
        HelloScreensPager(initialPage = 0)
    }
}
