<template>
    <div class="weui-cells">
        <scroll-cell>
            <div class="weui-cell">
                <div class="weui-cell__hd">
                    <img src="/img/icon_nav_cell.png">
                </div>
                <div class="weui-cell__bd">
                    <p>标题文字</p>
                </div>
                <div class="weui-cell__ft">说明文字</div>
            </div>
            <a slot="scroll-area" class="scroll-btn scroll-btn_default" @click="onDefault">Default</a>
            <a slot="scroll-area" class="scroll-btn scroll-btn_warn" @click="onPrimary">Primary</a>
        </scroll-cell>
        <scroll-cell :hasLink="true" to="">
            <div class="weui-cell">
                <div class="weui-cell__hd">
                    <img src="/img/icon_nav_cell.png">
                </div>
                <div class="weui-cell__bd">
                    <p>标题文字</p>
                </div>
                <div class="weui-cell__ft">说明文字</div>
            </div>
            <button slot="scroll-area" type="button" class="scroll-btn scroll-btn_default" @click="onDefault2">Default</button>
            <button slot="scroll-area" type="button" class="scroll-btn scroll-btn_warn" @click="onPrimary2">Primary</button>
        </scroll-cell>
        <div class="weui-cell">
            <div class="weui-cell__hd">
                <img src="/img/icon_nav_cell.png">
            </div>
            <div class="weui-cell__bd">
                <p>标题文字</p>
            </div>
            <div class="weui-cell__ft">说明文字</div>
        </div>
    </div>
</template>

<script>
    import ScrollCell from './../../components/scroll-cell/ScrollCell';

    export default {
        components: {
            ScrollCell
        },
        methods: {
            onDefault() {
                console.log("Default Button");
            },
            onPrimary() {
                console.log("Warn Button");
            },
            onDefault2() {
                console.log("Default Button2");
            },
            onPrimary2() {
                console.log("Warn Button2");
            }
        }
    }
</script>