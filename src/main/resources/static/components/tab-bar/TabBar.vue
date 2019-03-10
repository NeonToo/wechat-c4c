<template>
    <div class="weui-tabbar tab__bar">
        <slot></slot>
    </div>
</template>

<script>
    export default {
    }
</script>

<style scoped>
    .weui-tabbar.tab__bar {
        display: flex;
        position: static;
        z-index: inherit;
    }
</style>