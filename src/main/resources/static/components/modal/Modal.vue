<template>
    <div v-if="show" class="weui-mask">
        <div class="weui-dialog">
            <div class="weui-dialog__hd">
                <slot name="header"></slot>
            </div>
            <div class="weui-dialog__bd">
                <slot name="body"></slot>
            </div>
            <slot name="footer"></slot>
        </div>
    </div>
</template>

<script>
    export default {
        props: {
            show: {
                type: Boolean,
                required: true
            }
        }
    }
</script>