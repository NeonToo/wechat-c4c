<template>
    <div class="scroll-container" :style="{transform: 'translateY(' + pullLength + 'px)'}">
        <div class="scroll-content" @touchstart="enablePullToRefresh ? _onTouchStart($event) : undefined">
            <div v-if="enablePullToRefresh && state !== 0" class="weui-pull-to-refresh__layer">
                <slot name="pullToRefresh">
                    <div v-if="state === 1" class="down">下拉刷新</div>
                    <div v-if="state === 2" class="up">释放刷新</div>
                    <div v-if="state === 3" class="loadmore">
                        <i class="weui-loading"></i>
                        <span class="weui-loadmore__tips">正在刷新</span>
                    </div>
                </slot>
            </div>
            <slot></slot>
        </div>
        <div v-if="enableScrollLoading && loadingMore" class="weui-loadmore">
            <slot name="scrollLoading">
                <i class="weui-loading"></i>
                <span class="weui-loadmore__tips">正在加载</span>
            </slot>
        </div>
    </div>
</template>

<script>
    import _ from "lodash";

    export default {
        props: {
            enablePullToRefresh: {
                type: Boolean,
                required: false,
                default: true
            },
            enableScrollLoading: {
                type: Boolean,
                required: false,
                default: true
            },
            offsetToTriggerLoading: {
                type: Number,
                required: false,
                default: 50
            },
            pullToRefresh: {
                type: Function,
                required: false,
                default: undefined
            },
            loadMore: {
                type: Function,
                required: false,
                default: undefined
            }
        },
        mounted() {
            if (this.enableScrollLoading) {
                const that = this;
                document.querySelector(".scroll-container").addEventListener('scroll', _.throttle(that._onScroll, 50));// avoid scroll event trigger twice
            }
        },
        data() {
            return {
                state: 0, // 0: init, 1:pull start, 2: need to release, 3: pull stop
                touching: false,
                startTouchY: 0,
                startTouchScrollTop: 0,
                pullLength: 0,
                pullLengthToRelease: 20,
                maxPullLength: 30,
                loadingMore: false
            };
        },
        methods: {
            _onTouchStart(event) {
                let currentTarget = event.currentTarget;

                this.startTouchScrollTop = currentTarget.scrollTop;
                if (this.startTouchScrollTop > 0) {
                    this._refreshDone();
                    return;
                }

                if(this.enablePullToRefresh) {
                    const that = this;

                    this.touching = true;
                    this.startTouchY = event.targetTouches[0].pageY;
                    currentTarget.addEventListener("touchmove", _.throttle(that._onTouchMove, 50));
                    currentTarget.addEventListener("touchend", this._onTouchEnd);
                }
            },
            _onTouchMove(event) {
//                event.preventDefault();
                if (!this.touching) {
                    this._refreshDone();
                    return;
                }
                const that = this;
                let diff = event.targetTouches[0].pageY - this.startTouchY - this.startTouchScrollTop;

                if (diff > 10) {
                    window.requestAnimationFrame(function () {
                        that.state = 1;
                        if (diff > that.pullLengthToRelease) {
                            that.state = 2;
                        }
                        that.pullLength = diff > that.maxPullLength ? that.maxPullLength : diff;
                    });
                }
            },
            _onTouchEnd(event) {
                if (this.pullLength <= 0) {
                    this._refreshDone();
                    return false;
                }
                this.touching = false;
                this._refresh();
                return true;
            },
            _onScroll(event) {
                const currentTarget = event.currentTarget;
                const that = this;

                if (currentTarget) {
                    let scrollTop = currentTarget.scrollTop;
                    const clientHeight = currentTarget.clientHeight;
                    const scrollHeight = currentTarget.scrollHeight;
                    let offsetBottom = scrollHeight - clientHeight - scrollTop;

                    if (offsetBottom <= that.offsetToTriggerLoading && !that.loadingMore) {
                        this.loadingMore = true;
                        window.requestAnimationFrame(that._loadMore);
                    }
                }
            },
            _refresh() {
                this.state = 3;
                this.$emit("pullToRefresh", this._refreshDone);
            },
            _refreshDone() {
                this.state = 0;
                this.pullLength = 0;
                this.$off("touchmove");
                this.$off("touchend");
            },
            _loadMore() {
                this.$emit("loadMore", this._loadMoreDone);
            },
            _loadMoreDone() {
                this.loadingMore = false;
            }
        }
    }
</script>

<style>
    .scroll-container {
        display: -webkit-box;
        display: -webkit-flex;
        display: flex;
        flex-flow: column;
        overflow-y: scroll;
        -webkit-overflow-scrolling: touch;
    }

    .scroll-container > .scroll-content {
        flex: 1;
    }

    .weui-pull-to-refresh__layer {
        text-align: center;
    }

    .weui-loadmore {
        margin: 0 auto;
    }
</style>