<template>
    <div class="weui-grids">
        <router-link to="/toast" class="weui-grid">
            <div class="weui-grid__icon">
                <img :src="imgSrc" alt="">
            </div>
            <p class="weui-grid__label">
                Toast
            </p>
        </router-link>
        <router-link to="/scroll" class="weui-grid">
            <div class="weui-grid__icon">
                <img :src="imgSrc" alt="">
            </div>
            <p class="weui-grid__label">
                Scroll Page
            </p>
        </router-link>
        <router-link to="/confirm" class="weui-grid">
            <div class="weui-grid__icon">
                <img :src="imgSrc" alt="">
            </div>
            <p class="weui-grid__label">
                Confirm
            </p>
        </router-link>
        <router-link to="/alert" class="weui-grid">
            <div class="weui-grid__icon">
                <img :src="imgSrc" alt="">
            </div>
            <p class="weui-grid__label">
                Alert
            </p>
        </router-link>
        <router-link to="/tabcontainer" class="weui-grid">
            <div class="weui-grid__icon">
                <img :src="imgSrc" alt="">
            </div>
            <p class="weui-grid__label">
                Tab Container
            </p>
        </router-link>
        <router-link to="/file" class="weui-grid">
            <div class="weui-grid__icon">
                <img :src="imgSrc" alt="">
            </div>
            <p class="weui-grid__label">
                File Upload
            </p>
        </router-link>
        <router-link to="/cell" class="weui-grid">
            <div class="weui-grid__icon">
                <img :src="imgSrc" alt="">
            </div>
            <p class="weui-grid__label">
                Scroll Cell
            </p>
        </router-link>
    </div>
</template>

<script>
    import componentIcon from './../../img/icon_nav_cell.png';

    export default {
        data() {
            return {
                imgSrc: componentIcon
            };
        }
    }
</script>