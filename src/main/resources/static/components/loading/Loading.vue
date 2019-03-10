<template>
    <toast :show="show" icon="weui-loading" content="加载中..."></toast>
</template>

<script>
    import Toast from "./../toast/Toast";

    export default {
        components: {
            Toast
        },
        props: {
            show: {
                type: Boolean,
                required: false,
                default: true
            }
        }
    }
</script>