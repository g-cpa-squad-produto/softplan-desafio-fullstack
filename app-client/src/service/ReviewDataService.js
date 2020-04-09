import axios from 'axios';

const PROCESS_ID = '1'
const REVIEW_ID = '1'
const API_URL = 'http://localhost:8080'
const REVIEW_API_URL = `${API_URL}/Review/${REVIEW_ID}`

class ReviewDataService {

    retrieveAllReviews(){
        console.log('retrieve all reviews - service')
        return axios.get(`${REVIEW_API_URL}/review`)
    }

    retrieveReviewById(id){
        console.log('retrieve review by id - service')
        return axios.get(`${REVIEW_API_URL}/review/${id}`)
    }

    createReview(review){
        console.log('create review - service')
        return axios.post(`${REVIEW_API_URL}/review`, review)
    }

}

export default new ReviewDataService()