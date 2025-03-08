package com.samuel.treinaiappcompose.ui.screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samuel.treinaiappcompose.R
import com.samuel.treinaiappcompose.ui.theme.primaryFontFamilyBold
import com.samuel.treinaiappcompose.ui.theme.primaryFontFamilyRegular

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen() {

  val pagerState = rememberPagerState(
    pageCount = { 2 }
  )

  Column(
    modifier = Modifier
      .fillMaxSize()
  ) {
    Box(
      modifier = Modifier
        .fillMaxWidth()
    ) {
      Image(
        modifier = Modifier
          .fillMaxSize(),
        contentScale = ContentScale.Crop,
        painter = painterResource(R.drawable.treinai_man_onboarding_training_fade),
        contentDescription = stringResource(R.string.man_onboarding_description),
      )
      Column(
        modifier = Modifier
          .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 32.dp),
          ) {

            Text(
              text = stringResource(R.string.onboarding_phrase),
              color = Color(0xFF111827),
              fontSize = 28.sp,
              fontFamily = primaryFontFamilyBold,
//            fontWeight = FontWeight.Bold,
              textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
              text = stringResource(R.string.onboarding_app_description),
              color = Color(0xFF6B7280),
              fontFamily = primaryFontFamilyRegular,
              fontSize = 16.sp,
              textAlign = TextAlign.Center,
            )
          }
        }
        PageIndicator(
          pageCount = 3,
          currentPage = pagerState.currentPage,
          modifier = Modifier.padding(60.dp)
        )
      }
    }
  }
}

@Composable
fun PageIndicator(pageCount: Int, currentPage: Int, modifier: Modifier) {

  Row(
    horizontalArrangement = Arrangement.SpaceBetween,
    modifier = modifier
  ) {
    repeat(pageCount){
      IndicatorSingleDot(isSelected = it == currentPage )
    }
  }
}

@Composable
fun IndicatorSingleDot(isSelected: Boolean) {

  val width = animateDpAsState(targetValue = if (isSelected) 20.dp else 6.dp, label = "")
  Box(modifier = Modifier
    .padding(2.dp)
    .height(6.dp)
    .width(width.value)
    .clip(CircleShape)
    .background(if (isSelected) Color(0xFF3EC25B) else Color(0xFFD1D5DB))
  )
}

@Preview(showBackground = true)
@Composable
private fun OnboardingScreenPreview() {
  OnboardingScreen()
}