<template>
    <container>
        <div class="text-xs-center">
            Sua busca não retornou nenhum resultado.
        </div>
    </container>
</template>

<script>
    import Container from '@/views/components/Container'

    export default {
        name: 'ProcessoBuscaVazia',
        components: {Container}
    }
</script>