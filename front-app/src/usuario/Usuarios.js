import React from 'react';
import MaterialTable from 'material-table';

import api from './../Api'


export default function Usuarios() {

  const [state, setState] = React.useState({
    columns: [
      { title: 'Nome', field: 'nome' },
      { title: 'Email', field: 'email' },
      { title: 'Senha', field: 'senha' },
      {
        title: 'Tipo de usuário',
        field: 'tipo',
        lookup: {
          'administrador': 'Administrador',
          'triador': 'Triador',
          'finalizador': 'Finalizador'
        }
      }
    ],
    data: [],
    load: false
  });
  if (state.load === false) {
    api.loadUsuarios().then((res) => {
      /*
      this.setState({
          isLoading: false,
          series: res.data
      })
  */
      //setState({data:res.data })
      setState({
        ...state,
        data: res.data,
        load: true
      });
    }
    )
  }


  return (
    <MaterialTable
      title="Editable Example"
      columns={state.columns}
      data={state.data}
      editable={{
        onRowAdd: newData =>
          new Promise(resolve => {
            setState(prevState => {
              const data = [...prevState.data];
              data.push(newData);
              api.saveUsuario(newData).then(
                (res) => {
                  resolve();
                  setState({
                    ...state,
                    load: false
                  });
                }
              )
              return { ...prevState, data };
            });
          }),
        onRowUpdate: (newData, oldData) =>
          new Promise(resolve => {
            if (oldData) {
              setState(prevState => {
                const data = [...prevState.data];
                data[data.indexOf(oldData)] = newData;
                api.updateUsuario(newData).then(
                  (res) => {
                    resolve();
                    setState({
                      ...state,
                      load: false
                    });
                  }
                )
                return { ...prevState, data };
              });
            }
          }),
        onRowDelete: oldData =>
          new Promise(resolve => {
            setState(prevState => {
              const data = [...prevState.data];
              data.splice(data.indexOf(oldData), 1);
              api.deleteUsuario(oldData.id).then(
                (res) => {
                  resolve();
                  setState({
                    ...state,
                    load: false
                  });
                }
              );

              return { ...prevState, data };
            });


/*             setTimeout(() => {
              resolve();
              setState(prevState => {
                const data = [...prevState.data];
                data.splice(data.indexOf(oldData), 1);
                return { ...prevState, data };
              });
            }, 600); */
          }),
      }}
    />
  );
}