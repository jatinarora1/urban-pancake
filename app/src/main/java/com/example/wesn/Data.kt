package com.example.wesn

class Data()
{

    var MAC: String = ""
    var timestamp: ArrayList<Int> = ArrayList<Int>()

    fun getMac(): String
    {
        return MAC
    }
    fun setMac(mac: String)
    {
        this.MAC = mac
    }
    fun getTP(): ArrayList<Int>
    {
        return timestamp
    }
    fun setTP(tp: ArrayList<Int>)
    {
        this.timestamp = tp
    }


}
