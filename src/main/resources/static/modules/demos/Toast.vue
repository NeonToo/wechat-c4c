<template>
    <toast :show="loading" type="weui-icon-success-no-circle" content="成功"></toast>
</template>

<script>
    import Toast from "./../../components/toast/Toast";

    export default {
        data() {
            return {
                loading: true
            };
        },
        components: {
            Toast
        }
    }
</script>