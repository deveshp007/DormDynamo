package dex.alpha.dormdynamo.student


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dex.alpha.dormdynamo.R
import dex.alpha.dormdynamo.db.BookingDetails
import dex.alpha.dormdynamo.db.BookingDetailsDao
import dex.alpha.dormdynamo.student.StudentDashboard.Companion.emailOfStudent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [BookingDetails::class], version = 1)
abstract class BookingDatabase : RoomDatabase() {
    abstract fun bookingDetailsDao(): BookingDetailsDao
}

class StudentSelectedDetails : AppCompatActivity() {


    lateinit var res: TextView
    lateinit var deleteBtn: Button

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


        // Database

        val bookingDetails = BookingDetails(
            registrationNumber = reg ?: "",
            roomType = roomType ?: "",
            seater = seater ?: "",
            gender = gender ?: "",
            nameOfStudent = name ?: "",
            emailOfStudent = email ?: "",
            hostel = hostel ?: "",
            room = room ?: "",
            bed = bed ?: ""
        )


        val db = Room.databaseBuilder(
            applicationContext,
            BookingDatabase::class.java,
            "booking-database"
        ).build()


        GlobalScope.launch(Dispatchers.IO) {
            db.bookingDetailsDao().insertBookingDetails(bookingDetails)

        }

        deleteBtn = findViewById(R.id.delete_button)
        deleteBtn.setOnClickListener() {
            GlobalScope.launch(Dispatchers.IO) {
                db.bookingDetailsDao().deleteAllBookingDetails()
            }
            res.text = ""
            deleteBtn.isEnabled = false
        }

    }
}