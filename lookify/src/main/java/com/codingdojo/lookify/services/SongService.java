package com.codingdojo.lookify.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.lookify.models.Song;
import com.codingdojo.lookify.repositories.SongRepository;

@Service
public class SongService {
	private final SongRepository songRepo;
	public SongService(SongRepository songRepo) {
		this.songRepo = songRepo;
	}
	public List<Song> allSongs() {
		return songRepo.findAll();
	}
	public List<Song> findArtist(String artist) {
		return songRepo.findByArtistContaining(artist);
	}
	public List<Song> topTen(){
		return songRepo.OrderByRatingDesc();
	}
	public Song createSong(Song s) {
		return songRepo.save(s);
	}
	public Song findSong(Long id) {
		Optional<Song> oSong = songRepo.findById(id);
        if(oSong.isPresent()) {
            return oSong.get();
        } else {
            return null;
        }
	}
	public Song updateSong(Long id, String title, String artist, Integer rating) {
    	Optional<Song> oSong = songRepo.findById(id);
    	if(oSong.isPresent()) {
    		oSong.get().setTitle(title);
    		oSong.get().setArtist(artist);
    		oSong.get().setRating(rating);
    		return songRepo.save(oSong.get());
    		
    	} else {
    		return null;
    	}
    }
	public void deleteSong(long id) {
    	Optional<Song> oSong = songRepo.findById(id);
    	if(oSong.isPresent()) {
    		songRepo.delete(oSong.get());
    	} 
    }
}
