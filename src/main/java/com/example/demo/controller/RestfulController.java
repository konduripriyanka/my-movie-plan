package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Admin;
import com.example.demo.model.Cart;
import com.example.demo.model.Genre;
import com.example.demo.model.MovieTicket;
import com.example.demo.model.User;
import com.example.demo.repo.AdminRepo;
import com.example.demo.repo.CartRepo;
import com.example.demo.repo.GenreRepo;
import com.example.demo.repo.MovieTicketRepository;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.MovieTicketDAO;

@CrossOrigin(origins="http://localhost:4200/")
@RestController
@RequestMapping("/api/v1")
public class RestfulController {
	
	@Autowired
	MovieTicketRepository movieTicketRepository;
	@Autowired
	MovieTicketDAO mdao;
	@Autowired
	CartRepo cartRepository;
	@Autowired
	AdminRepo adminRepository;
	@Autowired
	UserRepo userRepository;
	@Autowired
	GenreRepo genreRepository;
	
	@GetMapping("/movieTickets/getallmovie")
	public List<MovieTicket> getAllMovieTickets() {
		return movieTicketRepository.findAll();
	}
	
	@GetMapping("/movieTickets/getByMovieName/{movieName}")
	public ResponseEntity<MovieTicket>getMovieByName (@PathVariable String movieName){
		MovieTicket mt= movieTicketRepository.findByMoviename(movieName);
		if(mt!=null) {
			return new ResponseEntity<MovieTicket>(mt, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<MovieTicket>(mt, HttpStatus.NOT_FOUND);
	}
	}
	
	@GetMapping("/movieTickets/getAllByGenre/{genre}")
	public List<MovieTicket>getMovieByGenre (@PathVariable String genre){
		return movieTicketRepository.getMoviesByGenre(genre);
	}
	
	@GetMapping("/movieTickets/getMovieById/{id}")
	public ResponseEntity<MovieTicket>getMovieById (@PathVariable int id){
		MovieTicket mt= movieTicketRepository.findByMovieId(id);
		if(mt!=null) {
			return new ResponseEntity<MovieTicket>(mt, HttpStatus.OK);
			}
		else {
			return new ResponseEntity<MovieTicket>(mt, HttpStatus.NOT_FOUND);
			}
	}
	
	@PostMapping("/movieTickets/add")
	public ResponseEntity<MovieTicket> addMovieTicket(@RequestBody MovieTicket mt) {
		MovieTicket mm=movieTicketRepository.save(mt);
		if(mm!=null) {
			return new ResponseEntity<MovieTicket>(mm,HttpStatus.CREATED);
		}
			else {
				return new ResponseEntity<MovieTicket>(mm,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/movieTicketUpdate/{id}")
	public ResponseEntity<Object> updateMovie(@PathVariable int id,@RequestBody MovieTicket newmv){
		MovieTicket med= mdao.updateMovie(id, newmv);
		
		if (med!=null)
			return new ResponseEntity<Object>(med,HttpStatus.OK);
		else
			return new ResponseEntity<Object>("No Movie Available to Update",HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/movieTickets/delete/{id}")
	public ResponseEntity<String>deleteMovie(@PathVariable int id){
		boolean result = mdao.deleteMovie(id);
		if(result) 
			return new ResponseEntity<String>("Object Deleted",HttpStatus.OK);
		else
			return new ResponseEntity<String>("NO movie Found", HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/cart/movieTicketss/all")
	public List<Cart> getAllMovieTicketsincart() {
		return cartRepository.findAll();
	}
	
	@PostMapping("/cart/movieTicketss/add")
	public Cart addMovieTicketToCart(@RequestBody(required = false) Cart cart) {
		return cartRepository.save(cart);
	}
	
	@DeleteMapping("/cart/movieTicketss/delete/{id}")
	public void deleteMovieTicketFromCart(@PathVariable int id) {
		cartRepository.deleteById(id);
	}
	
	@DeleteMapping("/cart/movieTicketss/delete/all")
	public void deleteAllMovieTickets() {
		cartRepository.deleteAll();
	}
	
	@PostMapping("/user/addUser")
	public ResponseEntity<User> addUser(@RequestBody User u) {
		User mm=userRepository.save(u);
		if(mm!=null) {
			return new ResponseEntity<User>(mm,HttpStatus.CREATED);
		}
			else {
				return new ResponseEntity<User>(mm,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/admin/addAdmin")
	public ResponseEntity<Admin> addAdmin(@RequestBody Admin a) {
		Admin aa=adminRepository.save(a);
		if(aa!=null) {
			return new ResponseEntity<Admin>(aa,HttpStatus.CREATED);
		}
			else {
				return new ResponseEntity<Admin>(aa,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/genre/addGenre")
	public ResponseEntity<Genre> addGenre(@RequestBody Genre u) {
		Genre mm=genreRepository.save(u);
		if(mm!=null) {
			return new ResponseEntity<Genre>(mm,HttpStatus.CREATED);
		}
			else {
				return new ResponseEntity<Genre>(mm,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/genre/getallGenre")
	public List<Genre> getAllGenre() {
		return genreRepository.findAll();
	}
	

}
