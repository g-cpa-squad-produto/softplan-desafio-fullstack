package com.pmanagement.pmanagementbackend.domain.service;

import com.pmanagement.pmanagementbackend.domain.entity.Feedback;
import com.pmanagement.pmanagementbackend.domain.repository.FeedbackRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for the {@link Feedback}
 *
 * @author Cristian Souza
 *
 * @version 1.0.0
 * @since 1.0.0, Jun 2, 2019
 */
@Service
public class FeedbackService {
    
    @Autowired
    private FeedbackRepository feedbackRepository;
    
    /**
     * List all {@link Feedback}
     * 
     * @return the {@link Feedback}
     */
    public List<Feedback> listFeedbacks() {
        return this.feedbackRepository.findAll();
    }
    
    /**
     * Find the {@link Feedback} by the id
     * 
     * @param id from the {@link Feedback}
     * @return the {@link Feedback}
     */
    public Feedback findFeedbackById(long id) {
        return this.feedbackRepository.getOne(id);
    }
    
    /**
     * Persist the {@link Feedback}
     * 
     * @param Feedback the {@link Feedback} to persist
     * @return the persisted {@link Feedback}
     */
    public Feedback saveFeedback(Feedback Feedback) {
        return this.feedbackRepository.saveAndFlush(Feedback);
    }
    
    /**
     * Delete the {@link Feedback}
     * 
     * @param Feedback the {@link Feedback} to delete
     */
    public void deleteFeedback(Feedback Feedback) {
        this.feedbackRepository.delete(Feedback);
    }
}