package fmreina.app.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import fmreina.app.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
