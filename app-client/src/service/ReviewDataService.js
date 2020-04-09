import axios from 'axios';

const API_URL = 'http://localhost:8080'
const REVIEW_API_URL = `${API_URL}/review`

class ReviewDataService {

    retrieveAllReviews(){
        console.log('retrieve all reviews - service')
        return axios.get(`${REVIEW_API_URL}`)
    }

    retrieveReviewById(id){
        console.log('retrieve review by id - service')
        return axios.get(`${REVIEW_API_URL}/${id}`)
    }

    createReview(review){
        console.log('create review - service')
        return axios.post(`${REVIEW_API_URL}`, review)
    }

    updateReview(id, review){
        console.log('update review - service')
        return axios.put(`${REVIEW_API_URL}/${id}`, review)
    }

    deleteReview(id) {
        console.log('delete review - service')
        return axios.delete(`${REVIEW_API_URL}/${id}`);
    }
}

export default new ReviewDataService()