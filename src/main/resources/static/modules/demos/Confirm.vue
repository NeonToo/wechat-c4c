<template>
    <div class="page">
        <confirm :show="true" title="Confirm Test" content="Confirm to do something?" @confirm="onOk" @cancel="onCancel"></confirm>
    </div>
</template>

<script>
    import Confirm from "./../../components/confirm/Confirm";

    export default {
        components: {
            Confirm
        },
        methods: {
            onOk() {
                console.log("Ok");
            },
            onCancel() {
                console.log("Cancel");
            }
        }
    }
</script>