  export const isMobile = (reduce, task) => {
      var ua = navigator.userAgent
      if(/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini|Mobile|mobile|CriOS/i.test(ua))
        return true
      else if(/Chrome/i.test(ua))
        return false
      else
      return false  
  }    


export const reduceTasks = (reduce, task) => {
    const current = reduce.find(rd => rd.taskDefinitionKey === task.taskDefinitionKey)
  
    if (current) {
      current.processInstances.push({ taskId: task.id, ...task.processInstance })
    } else {
      reduce.push({
        name: task.name,
        taskDefinitionKey: task.taskDefinitionKey,
        processInstances: [{ taskId: task.id, ...task.processInstance }]
      })
    }
  
    return reduce
  }
  
  export const statesArray = [
      { key: "AC", value: "Acre" },
      { key: "AL", value: "Alagoas" },
      { key: "AP", value: "Amapá" },
      { key: "AM", value: "Amazonas" },
      { key: "BA", value: "Bahia" },
      { key: "CE", value: "Ceará" },
      { key: "DF", value: "Distrito Federal" },
      { key: "ES", value: "Espírito Santo" },
      { key: "GO", value: "Goiás" },
      { key: "MA", value: "Maranhão" },
      { key: "MT", value: "Mato Grosso" },
      { key: "MS", value: "Mato Grosso do Sul" },
      { key: "MG", value: "Minas Gerais" },
      { key: "PA", value: "Pará" },
      { key: "PB", value: "Paraíba" },
      { key: "PR", value: "Paraná" },
      { key: "PE", value: "Pernambuco" },
      { key: "PI", value: "Piauí" },
      { key: "RJ", value: "Rio de Janeiro" },
      { key: "RN", value: "Rio Grande do Norte" },
      { key: "RS", value: "Rio Grande do Sul" },
      { key: "RO", value: "Rondônia" },
      { key: "RR", value: "Roraima" },
      { key: "SC", value: "Santa Catarina" },
      { key: "SP", value: "São Paulo" },
      { key: "SE", value: "Sergipe" },
      { key: "TO", value: "Tocantins" }
  ]