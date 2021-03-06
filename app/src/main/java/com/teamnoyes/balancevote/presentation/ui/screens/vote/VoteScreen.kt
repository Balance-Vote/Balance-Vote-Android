package com.teamnoyes.balancevote.presentation.ui.screens.vote

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.teamnoyes.balancevote.VotePostScreen
import com.teamnoyes.balancevote.presentation.ui.theme.BalanceVoteTheme
import com.teamnoyes.balancevote.presentation.ui.widget.BVTextButton
import com.teamnoyes.balancevote.presentation.ui.widget.BVTextButtonRole
import com.teamnoyes.balancevote.presentation.ui.widget.BottomNavScreen

data class PostVoteSelectionUiState(val isPosted: Boolean = false, val throwError: Boolean = false)

@Composable
fun VoteScreen(
    viewModel: VoteViewModel = viewModel(),
    modifier: Modifier = Modifier,
    navController: NavController,
    snackbarEvent: (String) -> Unit,
    postId: String,
    leftTopic: String = "LEFT",
    rightTopic: String = "RIGHT",
    nickname: MutableState<String>,
) {
    if (viewModel.postVoteSelectionUiState.value.isPosted) {
//        snackbarEvent("Success")
        viewModel.postVoteSelectionUiState.value = PostVoteSelectionUiState(isPosted = false)
        navController.navigate("main/detail/$postId") {
            popUpTo(BottomNavScreen.HOME.route)
        }
    }
    if (viewModel.postVoteSelectionUiState.value.throwError) {
        snackbarEvent("Error occurred. Please try again later.")
        viewModel.postVoteSelectionUiState.value = PostVoteSelectionUiState(throwError = false)
    }
    VoteScreenBody(viewModel, modifier, postId, leftTopic, rightTopic, nickname.value)
}

@Composable
fun VoteScreenBody(
    viewModel: VoteViewModel = viewModel(),
    modifier: Modifier = Modifier,
    postId: String,
    leftTopic: String,
    rightTopic: String,
    nickname: String,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 8.dp)
    ) {

        Row(
            modifier = modifier
                .fillMaxWidth()
        ) {
            Text(text = leftTopic, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
            Text(text = "VS", modifier = modifier
                .align(Alignment.CenterVertically)
                .padding(8.dp, 0.dp))
            Text(text = rightTopic, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
        }

        Spacer(modifier = modifier.padding(0.dp, 16.dp))
        BVTextButton(
            text = leftTopic,
            onClick = { viewModel.postVoteSelection(postId, "1", nickname) },
            isSelected = false,
            role = BVTextButtonRole.RED,
            horizontalAlignment = Arrangement.Start,
            height = 100.dp,
            fontSize = 28.sp
        )
        Spacer(modifier = modifier.padding(0.dp, 8.dp))
        BVTextButton(
            text = rightTopic,
            onClick = { viewModel.postVoteSelection(postId, "2", nickname) },
            isSelected = false,
            role = BVTextButtonRole.BLUE,
            horizontalAlignment = Arrangement.Start,
            height = 100.dp,
            fontSize = 28.sp
        )
    }
}

@Preview(name = "VoteScreen Light", showBackground = true, showSystemUi = true)
@Composable
fun VoteScreenPreviewLight() {
    VoteScreenPreview(darkTheme = false)
}

@Preview(name = "VoteScreen Dark",
    showBackground = true,
    backgroundColor = 0x303030,
    showSystemUi = true)
@Composable
fun VoteScreenPreviewDark() {
    VoteScreenPreview(darkTheme = true)
}

@Composable
fun VoteScreenPreview(darkTheme: Boolean) {
    BalanceVoteTheme(darkTheme) {
        VoteScreenBody(postId = "",
            leftTopic = "LEFT",
            rightTopic = "RIGHT",
            nickname = "TESTNICKNAME")
    }
}