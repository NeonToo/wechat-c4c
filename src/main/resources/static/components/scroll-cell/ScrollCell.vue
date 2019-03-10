<template>
    <div class="scroll-cell" :style="{transform: 'translateX(' + distance + 'px)'}">
        <!--<div class="weui-cell" :class="{'weui-cell_access': hasLink}"-->
             <!--@touchstart="_onTouchStart($event)" @touchmove.prevent="_onTouchMove($event)"-->
             <!--@touchend="_onTouchEnd($event)" @click="hasLink ? _onClick(to) : undefined">-->
            <!--<slot></slot>-->
        <!--</div>-->
        <div class="scroll-cell_content" @touchstart="_onTouchStart($event)" @touchmove.prevent="_onTouchMove($event)"
             @touchend="_onTouchEnd($event)" @click="hasLink ? _onClick(to) : undefined">
            <slot></slot>
        </div>
        <div class="scroll-cell_scroll">
            <slot name="scroll-area"></slot>
        </div>
    </div>
</template>

<script>
    import _ from "lodash";

    export default {
        props: {
            hasLink: {
                type: Boolean,
                required: false,
                default: false
            },
            to: {
                type: [String, Object],
                required: false
            }
        },
        data() {
            return {
                scrollWidth: 0,
                lastBtnWidth: 0,
                distance: 0
            }
        },
        methods: {
            _onTouchStart(event) {
                const currentTarget = event.currentTarget;
                const siblingElement = currentTarget.nextElementSibling;
                const parentElement = currentTarget.parentElement;

                this.scrollWidth = siblingElement.clientWidth;
                this.lastBtnWidth = siblingElement.lastElementChild.clientWidth;
                this.startTouchX = event.targetTouches[0].pageX;
                parentElement.style.transitionDuration = "0";
            },
            _onTouchMove(event) {
                const parentElement = event.currentTarget.parentElement;
                let diff = event.targetTouches[0].pageX - this.startTouchX;

                if (diff < 0) { // show scroll area
                    if (Math.abs(diff) > this.lastBtnWidth) {
                        parentElement.style.transitionDuration = ".5s";
                        this.distance = -this.scrollWidth;
                    } else {
                        this.distance = diff;
                    }
                } else {
                    parentElement.style.transitionDuration = ".5s";
                    this.distance = 0;
                }
            },
            _onTouchEnd(event) {
                const parentElement = event.currentTarget.parentElement;

                if (this.distance > -this.lastBtnWidth) {
                    parentElement.style.transitionDuration = ".5s";
                    this.distance = 0;
                }
            },
            _onClick(to) {
                if (to) {
                    this.$router.push(to);
                }
            }
        }
    }
</script>

<style scoped>
    .scroll-cell {
        display: flex;
        position: relative;
    }

    .scroll-cell:before {
        content: " ";
        position: absolute;
        left: 0;
        top: 0;
        right: 0;
        height: 1px;
        border-top: 1px solid #D9D9D9;
        color: #D9D9D9;
        -webkit-transform-origin: 0 0;
        transform-origin: 0 0;
        -webkit-transform: scaleY(0.5);
        transform: scaleY(0.5);
        left: 15px;
    }

    .scroll-cell > .scroll-cell_content {
        box-sizing: border-box;
        min-width: 100vw;
    }

    .scroll-cell_scroll {
        display: flex;
    }
</style>