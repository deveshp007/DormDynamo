package dex.alpha.dormdynamo.warden

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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@Database(entities = [BookingDetails::class], version = 1)
abstract class BookingDatabase : RoomDatabase() {
    abstract fun bookingDetailsDao(): BookingDetailsDao
}

class CheckStudentDetails : AppCompatActivity() {

    lateinit var res: TextView
    lateinit var deleteBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_student_details)

        res = findViewById(R.id.res)

        // Database


        val db = Room.databaseBuilder(
            applicationContext,
            BookingDatabase::class.java,
            "booking-database"
        ).build()



        // retrieving data
        GlobalScope.launch(Dispatchers.IO) {
            val allBookingDetails = db.bookingDetailsDao().getAllBookingDetails()

            // Display the retrieved data in the UI thread
            launch(Dispatchers.Main) {
                var details = ""

                for (booking in allBookingDetails) {
                    details += "Name: ${booking.nameOfStudent}\n" +
                            "Reg. No.: ${booking.registrationNumber}\n" +
                            "Email: ${booking.emailOfStudent}\n" +
                            "Gender: ${booking.gender}\n" +
                            "Hostel: ${booking.hostel}\n" +
                            "Room Type: ${booking.roomType}\n" +
                            "Seater: ${booking.seater}\n" +
                            "Room: ${booking.room}\n" +
                            "Bed: ${booking.bed}\n\n"
                }

                res.text = details
            }
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