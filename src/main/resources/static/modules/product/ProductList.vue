<template>

</template>

<script>
    import {mapMutations} from 'vuex';

    export default {
        data() {
            return {
                products: []
            };
        },
        created() {
            this.isLoading(true);
            this.getProducts();
        },
        methods: {
            ...mapMutations([
                'isLoading'
            ]),
            getProducts() {
                const that = this;
                const categoryID = this.$route.params.category;

                if(categoryID) {
                    $.get("products", {page: 0, size: 10, category: categoryID}, $.noop(), "json")
                        .done(function (aProducts) {
                            if (aProducts) {
                                that.products = aProducts;
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
    }
</script>