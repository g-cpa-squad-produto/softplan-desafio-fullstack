<template>
    <div>
        <span class="headline mr-2">{{titulo}}</span>
        <span class="text--secondary" v-if="subtitulo">{{subtitulo}}</span>
    </div>
</template>

<script>
    export default {
        name: 'PageTitle',
        props: {
            titulo: {
                type: String,
                required: true
            },
            subtitulo: {
                type: String,
                required: false
            }
        }
    }
</script>