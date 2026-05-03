package com.mysongs.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysongs.backend.repositories.AlbumsRepository;
import com.mysongs.backend.repositories.GenresRepository;
import com.mysongs.backend.repositories.SongsRepository;

@Service
public class DashboardService {

    @Autowired
    private AlbumsService albumsService;
    
    @Autowired
    private SongsRepository songsRepo;
    
    @Autowired
    private GenresRepository genresRepo;

    public Integer getNumOfAlbums() {
        return albumsService.getAllAlbums().size();
    }

    public Integer getNumOfSongs() {
        return songsRepo.findAll().size();
    }

    public Integer getNumOfGenres() {
        return genresRepo.findAll().size();
    }

    
}
