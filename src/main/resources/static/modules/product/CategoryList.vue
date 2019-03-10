<template>
    <div class="weui-grids">
        <div v-for="category in categories" :key="category.internalID" class="weui-grid">
            <div class="weui-grid__icon">
                <img :src="imgSrc" alt="">
            </div>
            <p class="weui-grid__label">
                {{category.descriptions[0].content}}
            </p>
        </div>
        <!--<router-link v-for="category in categories" :key="category.internalID" class="weui-grid"-->
                     <!--:to="{name: 'products', params: {category: category.internalID}}">-->
            <!--<div class="weui-grid__icon">-->
                <!--<img :src="imgSrc" alt="">-->
            <!--</div>-->
            <!--<p class="weui-grid__label">-->
                <!--{{category.descriptions[0].content}}-->
            <!--</p>-->
        <!--</router-link>-->
    </div>
</template>

<script>
    import {mapMutations} from 'vuex';
    import componentIcon from './../../img/icon_nav_cell.png';

    export default {
        data() {
            return {
                categories: [],
                imgSrc: componentIcon
            };
        },
        created() {
            this.isLoading(true);
            this.getCategories();
        },
        methods: {
            ...mapMutations([
                'isLoading'
            ]),
            getCategories() {
                const that = this;

                $.get("categories", {level: 'top'}, $.noop(), "json")
                    .done(function (aCategories) {
                        if (aCategories) {
                            that.categories = aCategories;
                        }
                    })
                    .fail(function () {

                    })
                    .always(function () {
                        that.isLoading(false);
                    });
            },
            getSubCategories(sInternalID) {
                const that = this;

                $.get(`categories/${sInternalID}`, null, $.noop(), "json")
                    .done(function (aCategories) {
                        if (aCategories) {
                            that.categories = aCategories;
                        }
                    })
                    .fail(function () {

                    })
                    .always(function () {
                        that.isLoading(false);
                    });
            }
        }
    }
</script>