import React, {Component} from 'react';

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
        console.log('update review ' + id)
        // this.props.history.push(`/review/${id}`)
    }

    addReviewClicked() {
        console.log('add review')
        // this.props.history.push(`process/-1/review/-1`)
    }

    render() {
        return (
            <div className="container">
                <p>Pareceres:</p>
                {/* <button className="btn btn-primary" onClick={this.addReviewClicked}>Novo Parecer</button> */}
                {this.state.message && <div class="alert alert-success">{this.state.message}</div>}
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
                                            <td>{review.user}</td>
                                            <td>{review.review}</td>
                                            <td>
                                                <button className="btn btn-warning" onClick={() => this.updateReviewClicked(review.id)}>Visualizar</button>
                                            </td>
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>
                    {/* <div className="row">
                        <button className="btn btn-primary" onClick={this.addReviewClicked}>Novo Parecer</button>
                    </div> */}
                </div>
            </div>
        )
    }
}

export default ListReviewComponent