package com.jubjub.user.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jubjub.user.entity.dataclass.Equipment
import com.jubjub.user.entity.dataclass.body.Login
import com.jubjub.user.entity.dataclass.body.SignUp


@Dao
interface AuthDAO {

    @Query("SELECT * FROM equipmentStatus")
    fun getAll(): List<Equipment>

    @Query("SELECT * FROM userData WHERE email LIKE :email AND password LIKE :password ")
    fun login(email: String, password: String): List<Equipment>

    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun signUp(signUp: SignUp)

    @Query("DELETE from equipmentStatus")
    fun clear()


}