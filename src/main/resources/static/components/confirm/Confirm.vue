<template>
    <modal :show="show">
        <strong slot="header" class="weui-dialog__title">
            {{title}}
        </strong>
        <div slot="body">
        	{{content}}
        </div>
        <div slot="footer" class="weui-dialog__ft">
            <a href="javascript:;" class="weui-dialog__btn weui-dialog__btn_default" @click="_onCancel">{{cancelText}}</a>
            <a href="javascript:;" class="weui-dialog__btn weui-dialog__btn_primary" @click="_onConfirm">{{confirmText}}</a>
        </div>
    </modal>
</template>

<script>
    import Modal from "../modal/Modal";

    export default {
        components: {
            Modal
        },
        props: {
            show: {
                type: Boolean,
                required: false,
                default: true
            },
            title: {
                type: String,
                required: false,
                default: ""
            },
            content: {
            	type: String,
                required: true
            },
            confirmText: {
                type: String,
                required: false,
                default: "确定"
            },
            cancelText: {
                type: String,
                required: false,
                default: "取消"
            },
            confirm: {
                type: Function,
                required: false
            },
            cancel: {
                type: Function,
                required: false
            }
        },
        methods: {
            _onCancel() {
                this.$emit("cancel");
            },
            _onConfirm() {
                this.$emit("confirm");
            }
        }
    }
</script>