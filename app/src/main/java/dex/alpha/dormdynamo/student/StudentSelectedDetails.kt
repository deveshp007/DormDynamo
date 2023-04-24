package dex.alpha.dormdynamo.student


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import dex.alpha.dormdynamo.R

class StudentSelectedDetails : AppCompatActivity() {


    lateinit var res: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_selected_details)

        var roomType = intent.getStringExtra("roomType")
        var seater = intent.getStringExtra("seater")
        var gender = intent.getStringExtra("gender")
        var name = intent.getStringExtra("name")
        var email = intent.getStringExtra("email")
        var reg = intent.getStringExtra("reg")
        var hostel = intent.getStringExtra("hostel")
        var room = intent.getStringExtra("room")
        var bed = intent.getStringExtra("bed")

        res = findViewById(R.id.res)

        res.text = """Name : ${name}
            |Reg. No.: ${reg}
            |Email: ${email}
            |Gender: ${gender}
            |Hostel: ${hostel}
            |Room Type: ${roomType}
            |Seater: ${seater}
            |Room: ${room} (Random)
            |Bed: ${bed}
        """.trimMargin()




    }
}