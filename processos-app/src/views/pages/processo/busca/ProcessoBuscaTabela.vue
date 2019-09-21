<template>
    <v-data-table
            hide-actions
            class="elevation-1"
            :headers="headers"
            :items="processos">
        <template v-slot:items="props">
            <td>{{ props.item.id }}</td>
            <td>{{ props.item.nome }}</td>
            <td>{{ props.item.usuarioCriacao.nome }}</td>
            <td>{{ props.item.dataCriacao | date}}</td>
            <td class="text-xs-right">
                <v-tooltip bottom>
                    <v-btn flat icon slot="activator">
                        <v-icon class="text--secondary">notes</v-icon>
                    </v-btn>
                    <span>Ver detalhes</span>
                </v-tooltip>
            </td>
        </template>
    </v-data-table>
</template>

<script>
    export default {
        name: 'ProcessoBuscaTabela',
        props: ['processos'],
        data() {
            return {
                headers: [
                    {
                        text: 'Nro.',
                        align: 'left',
                        sortable: false,
                        width: '5%'
                    },
                    {
                        text: 'Título',
                        align: 'left',
                        sortable: false,
                        width: '30%'
                    },
                    {
                        text: 'Usuário de Criação',
                        align: 'left',
                        sortable: false,
                        width: '25%'
                    },
                    {
                        text: 'Data de Criação',
                        align: 'left',
                        sortable: false,
                        width: '20%'
                    },
                    {
                        text: 'Ações',
                        align: 'right',
                        sortable: false,
                        width: '20%'
                    }
                ]
            }
        }
    }
</script>