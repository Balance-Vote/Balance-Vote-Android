package com.teamnoyes.balancevote.presentation.ui.screens.vote

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
) {
    if (viewModel.postVoteSelectionUiState.value.isPosted) {
        snackbarEvent("Success")
        viewModel.postVoteSelectionUiState.value = PostVoteSelectionUiState(isPosted = false)
        navController.navigate(VotePostScreen.DETAIL.route) {
            popUpTo(BottomNavScreen.HOME.route)
        }
    }
    if (viewModel.postVoteSelectionUiState.value.throwError) {
        snackbarEvent("Fail")
        viewModel.postVoteSelectionUiState.value = PostVoteSelectionUiState(throwError = false)
    }
    VoteScreenBody(viewModel, modifier, postId, leftTopic, rightTopic)
}

@Composable
fun VoteScreenBody(
    viewModel: VoteViewModel = viewModel(),
    modifier: Modifier = Modifier,
    postId: String,
    leftTopic: String,
    rightTopic: String
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp, 0.dp)
    ) {

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(0.dp, 8.dp)
        ) {
            Text(text = leftTopic, fontSize = 24.sp)
            Text(text = "VS", modifier = modifier
                .align(Alignment.CenterVertically)
                .padding(8.dp, 0.dp))
            Text(text = rightTopic, fontSize = 24.sp)
        }

        Spacer(modifier = modifier.padding(0.dp, 12.dp))
        BVTextButton(
            text = leftTopic,
            onClick = { viewModel.postVoteSelection(postId, "1", "0530testUUID1000") },
            isSelected = false,
            role = BVTextButtonRole.RED,
            horizontalAlignment = Arrangement.Start,
            height = 100.dp,
            fontSize = 28.sp
        )
        Spacer(modifier = modifier.padding(0.dp, 12.dp))
        BVTextButton(
            text = rightTopic,
            onClick = { viewModel.postVoteSelection(postId, "2", "0530testUUID1000") },
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
        VoteScreenBody(postId = "", leftTopic = "LEFT", rightTopic = "RIGHT")
    }
}