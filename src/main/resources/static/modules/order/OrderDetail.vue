<template>
    <div class="page">
        <div class="weui-cells">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <p>编号</p>
                </div>
                <div class="weui-cell__ft">{{order.internalID}}</div>
            </div>
            <div class="weui-cell weui-cell_link">
                <div class="weui-cell__bd">
                    <p>状态</p>
                </div>
                <div class="weui-cell__ft">{{order.status}}</div>
            </div>
            <div class="weui-cell weui-cell_link weui-cell_access">
                <div class="weui-cell__bd">
                    <p>客户</p>
                </div>
                <div class="weui-cell__ft">{{order.customerName}}</div>
            </div>
            <div v-if="order.description != ''" class="weui-cell">
                <div class="weui-cell__bd">
                    <p>描述</p>
                </div>
                <div class="weui-cell__ft">{{order.description}}</div>
            </div>
        </div>
        <div class="weui-panel">
            <div class="weui-panel__bd">
                <a v-for="item in order.items" href="javascript:void(0);"
                   class="weui-media-box weui-media-box_appmsg">
                    <div class="weui-media-box__hd">
                        <img class="weui-media-box__thumb" src="./../../img/icon_nav_cell.png">
                    </div>
                    <div class="weui-media-box__bd weui-flex">
                        <div class="weui-flex__item">
                            <p class="weui-media-box__title weui-cell_link">{{item.description}}</p>
                            <p class="weui-media-box__desc">编号：{{item.productID}}</p>
                            <p class="weui-media-box__desc">类别：{{item.productCategoryID}}</p>
                        </div>
                        <div class="text-right">
                            <p class="text-black">￥{{item.netPriceAmount.content}}</p>
                            <del class="weui-media-box__desc">￥30.00</del>
                            <p class="weui-media-box__desc">× {{item.quantity.content}}</p>
                        </div>
                    </div>
                </a>
            </div>
            <div class="weui-panel__ft">
                <div class="weui-cell weui-cell_link">
                    <div class="weui-cell__bd"></div>
                    <div class="weui-cell__ft">
                        <p>合计: <span class="text-black">￥{{order.netAmount.content}} {{order.netAmount.currencyCode}}</span></p>
                    </div>
                </div>
            </div>
        </div>
        <div class="weui-cells">
            <div class="weui-cell weui-cell_access">
                <div class="weui-cell__hd">
                    <img src="./../../img/transfer.png">
                </div>
                <div class="weui-cell__bd weui-media-box__desc">
                    <p>很长很长很长的物流信息</p>
                    <p>很长很长很长的物流信息</p>
                </div>
                <span class="weui-cell__ft"></span>
            </div>
            <router-link :to="{name: 'parties', params: {id: order.internalID}, query: {uri: order.parties}}"
                         class="weui-cell weui-cell_access">
                <div class="weui-cell__hd">
                    <img src="./../../img/employee.png">
                </div>
                <div class="weui-cell__bd">
                    <p>相关方</p>
                </div>
                <div class="weui-cell__ft"></div>
            </router-link>
        </div>
        <div class="weui-cells">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <p>要求日期</p>
                </div>
                <div class="weui-cell__ft">{{order.requestedDate}}</div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <p>创建日期</p>
                </div>
                <div class="weui-cell__ft">{{order.createOn | convertTimestampToDate}}</div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <p>最后修改</p>
                </div>
                <div class="weui-cell__ft">{{order.updateOn | convertTimestampToDate}}</div>
            </div>
        </div>
    </div>
</template>

<script>
    import {mapMutations} from 'vuex';
    import moment from 'moment';

    export default {
        data() {
            return {
                order: {
                    items: []
                }
            };
        },
        created() {
            this.isLoading(true);
            this.getOrderDetail();
        },
        filters: {
            convertTimestampToDate(value) {
                return moment(value).format('YYYY-MM-DD HH:mm');
            }
        },
        methods: {
            ...mapMutations([
                'isLoading'
            ]),
            getOrderDetail() {
                const that = this;
                const id = this.$route.params.id;
                const orderFromRoute = this.$route.params.order;

                if (orderFromRoute) {
                    this.order = orderFromRoute;
                    this.isLoading(false);
                } else {
                    $.get(`orders/${id}`, null, $.noop(), 'json')
                        .done(function (oOrder) {
                            if (oOrder) {
                                that.order = oOrder;
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