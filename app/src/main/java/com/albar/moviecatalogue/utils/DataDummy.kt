package com.albar.moviecatalogue.utils

import com.albar.moviecatalogue.data.CatalogueEntity

object DataDummy {
    fun generateDummyMovie(): List<CatalogueEntity> {

        val movie = ArrayList<CatalogueEntity>()

        movie.add(
            CatalogueEntity(
                1,
                "https://www.lpmdinamika.co/wp-content/uploads/2021/02/Extraction.jpg",
                "Extraction",
                2020,
                "4.3",
                17,
                "3hr 2 min",
                "IDR 127,000.00",
                "Extraction is a 2020 American action-thriller film starring Chris Hemsworth and released on Netflix. It is directed by Sam Hargrave and written by Joe Russo, based on the graphic novel Ciudad by Ande Parks, Joe Russo, Anthony Russo, Fernando León González, and Eric Skillman. The film's cast also features Rudhraksh Jaiswal, Randeep Hooda, Golshifteh Farahani, Pankaj Tripathi and David Harbour, and follows a black ops mercenary who must rescue an Indian drug lord's kidnapped son in Dhaka, Bangladesh.\n" +
                        "Netflix released Extraction on April 24, 2020. The film received mixed reviews from critics, who praised the performances and action sequences, but criticized the plot, excessive violence and negative portrayal of Bangladesh. It became the most watched original film in Netflix's history, and a sequel is in development."
            )
        )
        return movie
    }

    fun generateDummyTvShow(): List<CatalogueEntity> {
        val tvShow = ArrayList<CatalogueEntity>()

        tvShow.add(
            CatalogueEntity(
                11,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR7YwsklLEDv_t4Lrd3OcM0pwsLzfMtxtzVDQ&usqp=CAU",
                "Kingdom",
                2019,
                "4.1",
                12,
                "2hr 14 min",
                "IDR 67,000.00",
                "Kingdom is a 2019 Japanese film directed by Shinsuke Sato and produced by Sony Pictures Japan. It is an adaptation of the manga series of the same name, created by Yasuhisa Hara and published by Shueisha. The screenplay was written by Tsutomu Kuroiwa, Shinsuke Sato and Yasuhisa Hara. Kento Yamazaki, who starred in a 3-minute short of the same name released in 2016, reprises his role as the film's protagonist with a supporting cast that includes Ryo Yoshizawa, Kanna Hashimoto, Masami Nagasawa, Kanata Hongō and Takao Osawa. The film portrays the life of Li Xin, a general of Qin, from his childhood as an orphan through his military career during the Warring States period of ancient China.\n" +
                        "The film was released nationwide on April 19, 2019. It was screened in North America by Funimation in Q3 2019."
            )
        )
        return tvShow
    }
}