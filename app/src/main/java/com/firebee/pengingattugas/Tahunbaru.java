package com.firebee.pengingattugas;
import java.util.Calendar;
public class Tahunbaru {
    public static void main(String[] args){
        int tahun = 1 + Calendar.getInstance().get(Calendar.YEAR);
        int jam = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if(tahun > 2018){
            System.out.println("Selamat tahun baru 2018!");
        }
        else {
            jam = 24 - jam;
            System.out.println("tahun baru " + jam + " jam lagi");
        }
    }
}