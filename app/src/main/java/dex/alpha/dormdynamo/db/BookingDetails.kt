package dex.alpha.dormdynamo.db


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookingDetails(
    @PrimaryKey val registrationNumber: String,
    val roomType : String,
    val seater: String,
    val gender: String,
    val nameOfStudent: String,
    val emailOfStudent: String,
    val hostel: String,
    val room: String,
    val bed: String
)
