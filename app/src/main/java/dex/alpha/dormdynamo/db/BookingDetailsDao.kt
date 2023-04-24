package dex.alpha.dormdynamo.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookingDetailsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookingDetails(bookingDetails: BookingDetails)

    @Query("SELECT * FROM BookingDetails WHERE registrationNumber = :registrationNumber")
    suspend fun getBookingDetails(registrationNumber: String): BookingDetails?

    @Query("SELECT * FROM bookingDetails")
    suspend fun getAllBookingDetails(): List<BookingDetails>

    @Query("DELETE FROM bookingDetails")
    suspend fun deleteAllBookingDetails()
}
