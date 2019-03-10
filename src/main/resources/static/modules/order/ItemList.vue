<template>
    <div class="page">
        <div class="weui-panel weui-panel_access">
            <div class="weui-panel__hd">产品</div>
            <div class="weui-panel__bd">
                <a v-for="item in items" href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
                    <div class="weui-media-box__hd">
                        <p>{{item.quantity.content}} {{item.quantity.unitCode}}</p>
                    </div>
                    <div class="weui-media-box__bd">
                        <h4 class="weui-media-box__title weui-cell_link">{{item.description}}</h4>
                        <div class="weui-media-box__desc">
                            <p>编号：{{item.productID}}</p>
                            <p>类别：{{item.productCategoryID}}</p>
                        </div>
                    </div>
                </a>
            </div>
        </div>
    </div>
</template>

<script>
    import {mapMutations} from 'vuex';

    export default {
        data() {
            return {
                items: []
            };
        },
        created() {
            this.isLoading(true);
            this.getItems();
        },
        methods: {
            ...mapMutations([
                'isLoading'
            ]),
            getItems() {
                const that = this;
                const id = this.$route.params.id;
                const items = this.$route.query.items;

                if(items) {
                    $.get(`orders/1/order/items`, {uri: encodeURIComponent(items)}, $.noop(), 'json')
                        .done(function (aItems) {
                            if (aItems) {
                                that.items = aItems;
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