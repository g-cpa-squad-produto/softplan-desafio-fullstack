import React from 'react';
import Link from '@material-ui/core/Link';
import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Title from './Title';

// Generate Order Data
function createData(id, date, name, shipTo, paymentMethod, amount) {
  return { id, date, name, shipTo, paymentMethod, amount };
}

const rows = [
  createData(0, '10 de Dezembro de 2019', '1331313131313', 'Descrição do processo', 'Pendente', 312.44),
  createData(1, '10 de Dezembro de 2019', '0987313131313', 'Descrição do processo', 'Realizado', 866.99),
  createData(2, '10 de Dezembro de 2019', '1890313131313', 'Descrição do processo', 'Pendente', 100.81),
  createData(3, '10 de Dezembro de 2019', '2409313131313', 'Descrição do processo', 'Pendente', 654.39),
  createData(4, '10 de Dezembro de 2019', '0923313131313', 'Descrição do processo', 'Realizado', 212.79),
];

function preventDefault(event) {
  event.preventDefault();
}

const useStyles = makeStyles(theme => ({
  seeMore: {
    marginTop: theme.spacing(3),
  },
}));

export default function Orders() {
  const classes = useStyles();
  return (
    <React.Fragment>
      <Title>Lista de Processos Recentes</Title>
      <Table size="small">
        <TableHead>
          <TableRow>
            <TableCell>Data</TableCell>
            <TableCell>Código</TableCell>
            <TableCell>Descrição</TableCell>
            <TableCell>Situação do parecer</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {rows.map(row => (
            <TableRow key={row.id}>
              <TableCell>{row.date}</TableCell>
              <TableCell>{row.name}</TableCell>
              <TableCell>{row.shipTo}</TableCell>
              <TableCell>{row.paymentMethod}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
{/*       <div className={classes.seeMore}>
        <Link color="primary" href="#" onClick={preventDefault}>
          See more orders
        </Link>
      </div> */}
    </React.Fragment>
  );
}
