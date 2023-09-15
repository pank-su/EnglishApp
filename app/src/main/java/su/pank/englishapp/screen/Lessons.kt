package su.pank.englishapp.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import su.pank.englishapp.viewmodel.LessonsViewModel


@Composable
fun Lessons(viewModel: LessonsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    if (viewModel.lessons.isEmpty()){
        CircularProgressIndicator()
    } else{
        LazyColumn(){
            items(viewModel.lessons){
                    lesson ->
                Card {
                    Text(text = lesson.lesson_name)
                }
            }
        }
    }


}

@Preview
@Composable
fun PreviewLessons() {
    MaterialTheme {
        Lessons()
    }
}