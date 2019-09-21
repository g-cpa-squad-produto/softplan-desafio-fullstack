<template>
    <v-data-table
            hide-actions
            class="elevation-1"
            :headers="headers"
            :items="usuarios">
        <template v-slot:items="props">
            <td>{{ props.item.id }}</td>
            <td>{{ props.item.nome }}</td>
            <td>{{ props.item.email }}</td>
            <td>{{ props.item.tipo | titleCase}}</td>
            <td class="text-xs-right">
                <v-tooltip bottom v-if="!ehUsuarioLogado(props.item)">
                    <v-btn flat icon slot="activator" @click="remover(props.item)">
                        <v-icon class="text--secondary">delete</v-icon>
                    </v-btn>
                    <span>Remover</span>
                </v-tooltip>
            </td>
        </template>
    </v-data-table>
</template>

<script>
    export default {
        name: 'UsuarioBuscaTabela',
        props: ['usuarios'],
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
                        text: 'Nome',
                        align: 'left',
                        sortable: false,
                        width: '30%'
                    },
                    {
                        text: 'E-mail',
                        align: 'left',
                        sortable: false,
                        width: '30%'
                    },
                    {
                        text: 'Papel',
                        align: 'left',
                        sortable: false,
                        width: '10%'
                    },
                    {
                        text: 'Ações',
                        align: 'right',
                        sortable: false,
                        width: '20%'
                    }
                ]
            }
        },
        methods: {
            ehUsuarioLogado(usuarioListagem) {
                const usuarioLogado = this.$store.state.usuario
                return usuarioLogado.id === usuarioListagem.id
            },
            remover(usuario) {
                this.$emit('remover', usuario)
            }
        }
    }
</script>