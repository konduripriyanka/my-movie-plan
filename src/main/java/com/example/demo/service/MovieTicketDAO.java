package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.MovieTicket;
import com.example.demo.repo.MovieTicketRepository;

@Service
public class MovieTicketDAO {
	
	@Autowired
	private MovieTicketRepository repo;
	
	public MovieTicket updateMovie(int id,MovieTicket newmv) {
		if(repo.findById(id).isPresent()) {
			MovieTicket oldmv=repo.findById(id).get();
			oldmv.setMovieName(newmv.getMovieName());
			oldmv.setShowDate(newmv.getShowDate());
			oldmv.setShowTime(newmv.getShowTime());
			oldmv.setShowingLocation(newmv.getShowingLocation());
			oldmv.setGenre(newmv.getGenre());
			oldmv.setPrice(newmv.getPrice());
			
			return repo.save(oldmv);
			
		}
		else {
			return null;
		}
	}
	
	public boolean deleteMovie(int id) {
		if(repo.findById(id).isPresent()) {
			repo.deleteById(id);
			return true;
			
		}
		else {
			return false;
		}
	}

}
