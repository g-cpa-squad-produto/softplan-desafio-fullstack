package fmreina.app.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import fmreina.app.model.Review;
import fmreina.app.persistence.ReviewRepository;

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
public class ReviewController {

	@Autowired
	private ReviewRepository reviewRepository;

	@PostMapping("/review/{reviewId}")
	public ResponseEntity<Review> createReview(@RequestBody Review review) {
		Review reviewCreated = reviewRepository.save(review);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{reviewId}").buildAndExpand(reviewCreated.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/review/{reviewId}")
	public ResponseEntity<Review> updateReview(@PathVariable Long reviewId, @RequestBody Review review ){
		Review reviewUpdated = reviewRepository.save(review);
		
		return new ResponseEntity<Review>(reviewUpdated, HttpStatus.OK);
	}
	
	@DeleteMapping("/review/{reviewId}")
	public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
		reviewRepository.deleteById(reviewId);

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/review/{reviewId}")
	public Review getReviewById(@PathVariable Long reviewId) {
		Optional<Review> op = reviewRepository.findById(reviewId);

		if (op.isPresent()) {
			return op.get();
		}

		return null;
	}
	
	@GetMapping(path = "/review")
	public List<Review> getAllReviews(){
		return reviewRepository.findAll();
	}
}
