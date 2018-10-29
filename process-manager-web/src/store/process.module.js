
import { processService } from '@/api/api-service'
import { SAVE_PROCESS, LIST_PROCESS, REMOVE_PROCESS } from '@/store/actions.type'

export const actions = {
  [SAVE_PROCESS] ({commit}, process) {
    return processService.save(process)
      .then(({ data }) => {
        return data
      })
  },
  [LIST_PROCESS] (result) {
    return processService.listAll()
      .then(({ data }) => {
        return data
      })
  },
  [REMOVE_PROCESS] ({commit}, process) {
    return processService.remove(process)
      .then(({ data }) => {
        return data
      })
  }
}

export default {
  actions
}
