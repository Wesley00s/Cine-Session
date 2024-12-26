package com.example.cine.session.data.model.enumeration;

import com.example.cine.session.data.remote.response.movie.Genre

enum class MappedGenre(val id: Int) {
    ACTION(28),
    ADVENTURE(12),
    ANIMATION(16),
    COMEDY(35),
    CRIME(80),
    DOCUMENTARY(99),
    DRAMA(18),
    FAMILY(10751),
    FANTASY(14),
    HISTORY(36),
    HORROR(27),
    MUSIC(10402),
    MYSTERY(9648),
    ROMANCE(10749),
    SCIENCE_FICTION(878),
    TV_MOVIE(10770),
    THRILLER(53),
    WAR(10752),
    WESTERN(37);

    companion object {
        fun fromId(id: Int): Genre {
            return Genre(
                id = id,
                name = (entries.find { it.id == id }?.name ?: "")
            )
        }
    }
}
