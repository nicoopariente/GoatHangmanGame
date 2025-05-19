package com.npproject.randomhangmangame.pages

class CategoryPool {
    private val movies = listOf("FANTASTIC FOUR", "IRONMAN", "SPIDERMAN", "TRANSFORMERS", "TERMINATOR")
    private val countries = listOf("URUGUAY", "UNITED STATES", "BRAZIL", "RUSSIA", "EGYPT", "AUSTRALIA")
    private val soccerTeams = listOf("BARCELONA", "MANCHESTER UNITED", "REAL MADRID", "BAYERN MUNICH", "AJAX")
    private val footballTeams = listOf("BILLS", "DOLPHINS", "PACKERS", "CHIEFS", "STEELERS", "COWBOYS", "GIANTS")

    fun randomMovie(): String{
        return movies.random()
    }

    fun randomCountry(): String{
        return countries.random()
    }

    fun randomSoccerTeam(): String{
        return soccerTeams.random()
    }

    fun randomFootballTeam(): String{
        return footballTeams.random()
    }

}