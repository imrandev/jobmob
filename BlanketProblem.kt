package com.imran.jobmob


/**
 * Created by Imran Khan on 12/26/2020.
 * Email : context.imran@gmail.com
 */

fun main() {

    var ngo = 0
    val titliHas = 36
    val konkaHas = 53

    println("=================================")
    println("Titli has $titliHas blanket")
    println("Konka has $konkaHas blanket")

    var totalBlanket = titliHas + konkaHas
    println("Total $totalBlanket blankets available")
    println("=================================")

    var x = 0; var y = 1

    while(totalBlanket > 0){
        ngo++
        if (totalBlanket > y){
            println("NGO $ngo has taken $y blanket(s)")
            totalBlanket -= y
        } else {
            println("NGO $ngo has taken $totalBlanket blanket(s)")
            totalBlanket = 0
        }
        println("Blanket available : $totalBlanket")
        println("=================================")
        x += y
        y += x
    }

    println("They can help total $ngo NGOs")
    println("=================================")
}