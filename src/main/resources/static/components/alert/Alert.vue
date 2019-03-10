<template>
    <modal :show="show">
        <strong slot="header" class="weui-dialog__title">
            {{title}}
        </strong>
        <div slot="body">
            {{content}}
        </div>
        <div slot="footer" class="weui-dialog__ft">
            <a href="javascript:;" class="weui-dialog__btn weui-dialog__btn_primary" @click="_onOk">{{buttonText}}</a>
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
            buttonText: {
                type: String,
                required: false,
                default: "确定"
            },
            ok: {
                type: Function,
                required: false
            }
        },
        methods: {
            _onOk() {
                this.$emit("ok");
            }
        }
    }
</script>