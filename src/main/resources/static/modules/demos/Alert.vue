<template>
    <div class="page">
        <alert :show="true" title="Alert Test" content="Something to alert" @ok="onOk"></alert>
    </div>
</template>

<script>
    import Alert from "./../../components/alert/Alert";

    export default {
        components: {
            Alert
        },
        methods: {
            onOk() {
                console.log("Ok");
            }
        }
    }
</script>