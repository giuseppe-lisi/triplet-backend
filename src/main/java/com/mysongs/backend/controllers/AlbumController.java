package com.mysongs.backend.controllers;

import com.mysongs.backend.repositories.AlbumsRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysongs.backend.model.Album;
import com.mysongs.backend.service.AlbumsService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@Controller
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumsRepository albumsRepository;
    @Autowired
    private AlbumsService albumService;

    AlbumController(AlbumsRepository albumsRepository) {
        this.albumsRepository = albumsRepository;
    }

    @GetMapping
    public String index(Model model) {
        List<Album> albums = albumService.getAllAlbums();

        model.addAttribute("albums", albums);

        return "albums/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Optional<Album> album = albumService.getAlbumById(id);

        if (album.isEmpty()) {
            return "albums/show";
        }

        model.addAttribute("album", album.get());

        return "albums/show";
    }
    
    @GetMapping("/create")
    public String create(Model model) {
        Album album = new Album();

        model.addAttribute("album", album);

        return "albums/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute Album album, BindingResult result, Model model) {
        
        if (result.hasErrors()) {
            return "albums/create";
        }

        albumService.save(album);
        
        return "redirect:/albums/" + album.getId();
    }
    
    @GetMapping("/update/{id}")
    public String modify(Model model, @PathVariable Integer id) {
        Optional<Album> album = albumService.getAlbumById(id);

        if (album.isEmpty()) {
            return "albums/update";
        }
        
        model.addAttribute("album", album.get());

        return "albums/update";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute Album album, BindingResult result, Model model) {
        
        if (result.hasErrors()) {
            model.addAttribute("album", album);
            return "albums/update";
        }

        albumService.save(album);
        
        return "redirect:/albums/" + album.getId();
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        albumService.delete(id);
        
        return "redirect:/albums";
    }
    
}
