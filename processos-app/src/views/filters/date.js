import moment from 'moment-timezone'

export default (data, formato) => {
    const formatoDefault = 'DD/MM/YYYY'
    if (!data) {
        return '-'
    }
    return moment(data).format(formato ? formato : formatoDefault)
}