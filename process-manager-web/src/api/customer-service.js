const ID_TOKEN_KEY = 'customer'

export default {
  getCustomer () {
    return window.sessionStorage.getItem(ID_TOKEN_KEY)
  },
  saveCustomer (customer) {
    window.sessionStorage.setItem(ID_TOKEN_KEY, customer)
  },
  destroyCustomer () {
    window.sessionStorage.removeItem(ID_TOKEN_KEY)
  }
}
