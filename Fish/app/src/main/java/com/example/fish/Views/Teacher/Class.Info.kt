package com.example.fish.Views.Teacher

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fish.Controllers.updateClass
import com.example.fish.Untils.Back
import com.example.fish.Untils.DemoData
import com.example.fish.Untils.User
import com.example.fish.Untils.appendMessage
import com.example.fish.Untils.goTo
import com.example.fish.Views.Student.ButtonNav
import com.example.fish.Views.Student.OneLineChange
import com.example.fish.ui.theme.DisplayUI
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ClassInfoView(nav : NavController , view : DisplayUI)
{
    Back(nav = nav, view = view , "DetailClass")
    val infoClass = view.nowClass.copy()
    val context = LocalContext.current
    Column(
        modifier = Modifier.padding(start = 20.dp , end = 20.dp , top = 10.dp) ,
        horizontalAlignment = Alignment.CenterHorizontally ,
        verticalArrangement = Arrangement.SpaceBetween

    ) {
        ListUser(view = view) {
            view.toogleChoose()
        }
        Spacer(modifier = Modifier.padding(25.dp))
        Card(
            modifier = Modifier.fillMaxWidth(0.99f)
        ) {
            Spacer(modifier = Modifier.padding(5.dp))
            OneLineChange(title = "Giảng Viên", content = infoClass.teacherID, readOnly = true )
            OneLineChange(title = "Lớp Học", content = infoClass.nameClass ,
                onChange = {
                    if(it.isEmpty())
                        appendMessage(context,"Không Thể Để Trống Tên Lớp Học")
                    infoClass.nameClass = it
                }
            )
            OneLineChange(title = "Tiêu Đề", content = infoClass.subtitle ,
                onChange = {
                    infoClass.subtitle = it
                }
            )
            OneLineChange(title = "Ngày Tạo", content = infoClass.dateCreate , readOnly = true )
            Spacer(modifier = Modifier.padding(10.dp))
        }
        Spacer(modifier = Modifier.padding(10.dp))
        ButtonNav(onClick = {
            if(infoClass.nameClass.isEmpty()){
                appendMessage(context , "Không Thể Để Tên Lớp Học Trống")
                return@ButtonNav
            }

            updateClass(infoClass)
            appendMessage(context,"Cập Nhật Thông Tin Lớp Học Thành Công")
            goTo(nav , view , "DetailClass")

        }, content = "Xác Nhận")
        ButtonNav(
            onClick = { goTo(nav , view , "Home") },
            content = "Giải Tán Lớp" ,
            color = Color(0xFFDC0F0F) ,
            contentColor = Color.White
            )
    }
}
@Composable
fun ListUser(view: DisplayUI , onClick:()->Unit )
{
    Card(
        modifier = Modifier.clickable { onClick() }
    ) {
        Box(
            modifier = Modifier.fillMaxWidth() ,
            contentAlignment = Alignment.TopEnd
        )
        {
            IconButton(onClick = { onClick() }) {
                Icon(imageVector = if(!view.isChoose) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp, contentDescription = null)
            }
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth() ,
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Danh Sách Thành Viên" , style = TextStyle(fontWeight = FontWeight.Bold , fontSize = 18.sp))
            }
        }
        //LazyHere
        if(view.isChoose)
            LazyColumn {
                items(DemoData.ListUser){
                    Divider(
                        color = Color.White ,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    OneUser(info = it)
                }
            }
    }
}
@Composable
fun OneUser(info : User , onRemove: ()->Unit = {})
{
    val role = when(info.roleid){
        1 -> "Người Học"
        2 -> "Người Giảng Dạy"
        else -> "Admin"
    }
    Box(
        modifier = Modifier.fillMaxWidth() ,
    ) {
        if(info.roleid != 2)
            IconButton(
                modifier = Modifier.align(Alignment.CenterEnd),
                onClick = { onRemove() }
            ) {
                Icon(
                    modifier = Modifier.size(size = 25.dp),
                    imageVector = Icons.Filled.Clear,
                    contentDescription = null)
            }
        Column(modifier = Modifier
            .padding(horizontal = 15.dp)
            .align(Alignment.CenterStart)
        ) {
            Text(
                modifier = Modifier.padding(vertical = 5.dp),
                text = info.name ,
                style = TextStyle(
                    fontWeight = FontWeight.Bold ,
                    fontSize = 16.sp
                )
            )
            Text(
                modifier = Modifier.padding(vertical = 2.dp),
                text = info.email ,
                style = TextStyle(
                    fontWeight = FontWeight.Bold ,
                    fontSize = 12.sp
                )
            )
            Text(
                modifier = Modifier.padding(bottom = 12.dp),
                text = role ,
                style = TextStyle(
                    fontWeight = FontWeight.Bold ,
                    fontSize = 12.sp
                )
            )
        }
    }
}