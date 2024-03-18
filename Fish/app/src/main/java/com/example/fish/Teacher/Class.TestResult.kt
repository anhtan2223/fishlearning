package com.example.fish.Teacher

import com.example.fish.Student.ButtonNav
import com.example.fish.Student.OneLine
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fish.Model.Back
import com.example.fish.Model.Class
import com.example.fish.Model.Test
import com.example.fish.Model.formatTime
import com.example.fish.ui.theme.DisplayUI

@Composable
fun TestResult(nav :NavController , view:DisplayUI)
{
    Back(nav = nav, view = view , "DetailClass")
    Teacher_DisplayInfo(classInfo = view.nowClass, testInfo = view.nowTest)
}
@Composable
fun Teacher_DisplayInfo(classInfo : Class , testInfo:Test)
{
    Column(
        modifier = Modifier.padding(start = 20.dp , top = 50.dp)
    ) {
        OneLine(title = "Tên Lớp", content = classInfo.NameClass)
        OneLine(title = "Bài Kiểm Tra", content = testInfo.TestName)
        OneLine(title = "Số Câu Hỏi", content = testInfo.NumberQues.toString())
        OneLine(title = "Thời Gian", content = formatTime(testInfo.Time))
        OneLine(title = "Người Tham Dự", content = "0")
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
        ) {
            ButtonNav(onClick = { /*TODO*/ }, content = "Tổng Kết Bài Thi" , color = MaterialTheme.colorScheme.primary)
            ButtonNav(onClick = { /*TODO*/ }, content = "Chỉnh Sửa Bài Thi" , color = MaterialTheme.colorScheme.primary)
        }

    }
}

