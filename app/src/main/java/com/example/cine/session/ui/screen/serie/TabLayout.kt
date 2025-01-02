package com.example.cine.session.ui.screen.serie

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cine.session.ui.theme.AppTypography
import com.example.cine.session.ui.theme.Primary
import com.example.cine.session.ui.theme.Quaternary
import com.example.cine.session.ui.theme.Tertiary
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun TabLayout(
    modifier: Modifier = Modifier,
    coroutineScope: CoroutineScope,
    tabs: List<String>,
    pages: List<@Composable () -> Unit>,
    pagerState: PagerState
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Primary)
    ) {
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier
                .fillMaxSize(),
            edgePadding = 0.dp,
            divider = {},
            indicator = { tabPositions ->
                if (tabs.isNotEmpty()) {
                    SecondaryIndicator(
                        modifier = Modifier
                            .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                            .fillMaxWidth(),
                        height = 2.dp,
                        color = Tertiary
                    )
                }
            }
        ) {
            if (tabs.isEmpty()) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(16.dp)
                        .size(32.dp),
                    color = Quaternary
                )
            } else {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        text = {
                            Text(
                                title,
                                color = Quaternary,
                                style = AppTypography.bodyLarge,
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                        }
                    )
                }
            }
        }
        HorizontalPager(
            pageSize = PageSize.Fill,
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
        ) { page ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                if (pages.isNotEmpty()) {
                    pages[page]()
                } else {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(64.dp),
                        color = Quaternary
                    )
                }
            }
        }
    }
}
