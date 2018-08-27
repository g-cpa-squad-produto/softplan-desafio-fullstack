
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  user: String;
  password: String;
  loginError: String;

  constructor(
    private router: Router,
  ) { }

  login() {
    console.log('efetuando login');
    this.router.navigate(['/user']);
  }

  reduceParaAgrupar() {
    const guias = [
            { peg_id: '1', guia_id: 'guia1', instance: '2342342' },
            { peg_id: '2', guia_id: 'guia22', instance: '254524423' },
            { peg_id: '1', guia_id: 'guia11', instance: '6967853' },
            { peg_id: '2', guia_id: 'guia2', instance: '4678643' },
            { peg_id: '1', guia_id: 'guia111', instance: '9824612' }
          ];

    const novoArray = guias
                  .reduce((acumulador, atual) => {
                      acumulador[atual.peg_id] = acumulador[atual.peg_id] || [];
                      acumulador[atual.peg_id].push(atual);
                      return acumulador;
                    },
                    Object.create(null)
                  );

    console.log(novoArray);
  }

  reduceParaProcurar() {
    const tasks = [
      { name: 'nome', id: '1', processInstance: {id: '6574534'}, taskDefinitionKey: '2342342' },
      { name: 'nome', id: '1', processInstance: {id: '6574534'}, taskDefinitionKey: '2342342' },
      { name: 'nome', id: '1', processInstance: {id: '6574534'}, taskDefinitionKey: '2342342' },
      { name: 'nome', id: '1', processInstance: {id: '6574534'}, taskDefinitionKey: '2342342' }
    ];

    const novoArray = tasks
          .filter(d => d.processInstance.id)
          .reduce( (reduce, task) => {
                    const current = reduce.find(rd => rd.taskDefinitionKey === task.taskDefinitionKey);

                    if (current) {
                      current.processInstances.push({ taskId: task.id, ...task.processInstance });
                    } else {
                      reduce.push({
                        name: task.name,
                        taskDefinitionKey: task.taskDefinitionKey,
                        processInstances: [{ taskId: task.id, ...task.processInstance }]
                      });
                    }

                    return reduce;
              }
              , []
            );
    console.log(novoArray);
  }

}
