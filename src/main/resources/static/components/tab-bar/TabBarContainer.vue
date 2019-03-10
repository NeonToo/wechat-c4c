<template>
    <main class="tab-container">
        <div class="tab__panel">
            <slot name="content">
                <transition :name="transitionDirection" mode="out-in">
                    <router-view></router-view>
                </transition>
            </slot>
        </div>
        <slot></slot>
    </main>
</template>

<script>
    export default {
        data() {
            return {
                transitionDirection: "slide-left"
            }
        },
        watch: {
            '$route' (to, from) {
                const toDepth = to.path.split('/').length;
                const fromDepth = from.path.split('/').length;

                this.transitionDirection = toDepth < fromDepth || to.path === "/" ? 'slide-right' : 'slide-left';
            }
        }
    }
</script>