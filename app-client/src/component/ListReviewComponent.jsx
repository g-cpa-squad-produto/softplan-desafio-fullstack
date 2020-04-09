import React, {Component} from 'react';
import ReviewDataService from '../service/ReviewDataService';

class ListReviewComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            review: [],
            message: null
        }
        this.refreshReviews = this.refreshReviews.bind(this)
        this.updateReviewClicked = this.updateReviewClicked.bind(this)
        this.addReviewClicked = this.addReviewClicked.bind(this)
        this.deleteReviewClicked = this.deleteReviewClicked.bind(this)
    }

    componentDidMount() {
        console.log('did mount review list')
        this.refreshReviews();
    }
    
    refreshReviews() {
        console.log('refresh review list')
        ReviewDataService.retrieveAllReviews().then(
            response => {
                console.log(response);
                this.setState({review: response.data})
            }
        )
    }

    updateReviewClicked(id) {
        console.log('update review ' + id + " - review list")
        this.props.history.push(`/review/${id}`)
    }

    addReviewClicked() {
        console.log('add review - review list')
        this.props.history.push(`/review/-1`)
    }

    deleteReviewClicked(id) {
        ReviewDataService.deleteReview(id).then(
            response => {
                this.setState({message: `Remoção do parecer ${id} bem sucedida`})
                this.refreshReviews()
            }
        )
    }

    render() {
        return (
            <div className="container">
                <p>Pareceres:</p>
                {/* <button className="btn btn-primary" onClick={this.addReviewClicked}>Novo Parecer</button> */}
                {this.state.message && <div className="alert alert-success">{this.state.message}</div>}
                <div className="container">
                    <table className="table"> 
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Autor</th>
                                <th>Parecer</th>
                                <th>Ação</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.review.map(
                                    review => 
                                        <tr key={review.id}>
                                            <td>{review.id}</td>
                                            <td>{review.reviewer}</td>
                                            <td>{review.text}</td>
                                            <td>
                                                <button className="btn btn-warning" onClick={() => this.updateReviewClicked(review.id)}>Visualizar</button>
                                                <button className="btn btn-danger" onClick={() => this.deleteReviewClicked(review.id)}>Apagar</button>
                                            </td>
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>
                    <div className="row">
                        <button className="btn btn-primary" onClick={this.addReviewClicked}>Novo Parecer</button>
                    </div>
                </div>
            </div>
        )
    }
}

export default ListReviewComponent