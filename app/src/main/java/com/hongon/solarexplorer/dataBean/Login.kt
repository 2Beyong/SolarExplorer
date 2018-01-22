package com.hongon.solarexplorer.dataBean

import java.lang.IllegalArgumentException

/**
 * LoginResponse Bean
 * Created by CoCO on 2018/1/22.
 */
 class Login(
       val loginFlag: Int,
       val roleType: String) {

    fun getLoginFlagMsg( ):String{
        return when(loginFlag){
            0 ->"The login is successful."
            1 ->"The user name does not exist."
            2 ->"The password Error"
            else->throw IllegalArgumentException("Invalid input.")
        }
    }

}
