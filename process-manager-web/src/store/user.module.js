
import { userService } from '@/api/api-service'
import { AUTHENTICATE, SAVE_USER, LIST_USER, REMOVE_USER, LIST_USER_ROLE } from '@/store/actions.type'

export const actions = {
  [AUTHENTICATE] ({commit}, user) {
    return userService.authenticate(user)
      .then(({ data }) => {
        return data
      })
  },
  [SAVE_USER] ({commit}, user) {
    return userService.save(user)
      .then(({ data }) => {
        return data
      })
  },
  [LIST_USER] (result) {
    return userService.listAll()
      .then(({ data }) => {
        return data
      })
  },
  [LIST_USER_ROLE] ({commit}, role) {
    return userService.findByRole(role)
      .then(({ data }) => {
        return data
      })
  },
  [REMOVE_USER] ({commit}, user) {
    return userService.remove(user)
      .then(({ data }) => {
        return data
      })
  }
}

export default {
  actions
}
