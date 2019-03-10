<template>
    <div class="page">
        <div class="weui-tab">
            <!--<div class="weui-navbar">-->
            <!--<div class="weui-navbar__item" :class="{'weui-bar__item_on': isSelected('ALL')}"-->
            <!--@click="filterStatus('ALL')">-->
            <!--全部-->
            <!--</div>-->
            <!--<div class="weui-navbar__item" :class="{'weui-bar__item_on': isSelected('IN_PROCESS')}"-->
            <!--@click="filterStatus('IN_PROCESS')">-->
            <!--处理中-->
            <!--</div>-->
            <!--</div>-->
            <div class="weui-tab__panel">
                <div class="weui-search-bar" :class="{'weui-search-bar_focusing': isSearching}" id="searchBar">
                    <div class="weui-search-bar__form">
                        <div class="weui-search-bar__box">
                            <i class="weui-icon-search"></i>
                            <input type="search" class="weui-search-bar__input" id="searchInput" placeholder="搜索"
                                   v-model.trim="searchText">
                            <a href="javascript:" class="weui-icon-clear" id="searchClear" @click="clearSearch"></a>
                        </div>
                        <label class="weui-search-bar__label" id="searchText" @click="search">
                            <i class="weui-icon-search"></i>
                            <span>{{searchLabel}}</span>
                        </label>
                    </div>
                    <a href="javascript:" class="weui-search-bar__cancel-btn" id="searchCancel"
                       @click="cancelSearch">取消</a>
                </div>
                <scroll-page @pullToRefresh="onPushToRefresh" @loadMore="onLoadMore">
                    <div v-for="order in orders" :key="order.id" class="weui-panel">
                        <div class="weui-panel__hd weui-flex">
                            <div class="weui-flex__item">
                                <i class="weui-icon-info-circle"></i>
                                <span class="weui-cell_link">{{order.customerName}}</span>
                            </div>
                            <div class="weui-flex__item font-primary text-right">
                                <p>{{order.status}}</p>
                            </div>
                        </div>
                        <router-link
                                :to="{name: 'orderDetail', params: {id: order.internalID, objectID: order.objectID, order: order}}"
                                class="weui-panel__bd">
                            <scroll-cell :hasLink="false" to="#" v-for="item in order.items"
                                         :key="item.itemNo">
                                <div class="weui-media-box weui-media-box_appmsg">
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
                                </div>
                                <button slot="scroll-area" type="button" class="scroll-btn scroll-btn_warn"
                                        @click="">删除
                                </button>
                            </scroll-cell>
                        </router-link>
                        <div class="weui-panel__ft">
                            <router-link
                                    :to="{name: 'orderDetail', params: {id: order.internalID, objectID: order.objectID, order: order}}"
                                    class="weui-cell weui-cell_access weui-cell_link">
                                <div class="weui-cell__bd"></div>
                                <div class="weui-cell__ft">
                                    <p>合计: <span class="text-black">￥{{order.netAmount.content}}{{order.netAmount.currencyCode}}</span>
                                    </p>
                                </div>
                            </router-link>
                        </div>
                    </div>
                </scroll-page>
            </div>
        </div>
    </div>
</template>

<script>
    import {mapMutations} from 'vuex';
    import ScrollPage from './../../components/scroll-page/ScrollPage';
    import ScrollCell from './../../components/scroll-cell/ScrollCell';

    export default {
        components: {
            ScrollPage,
            ScrollCell
        },
        data() {
            return {
                currentStatus: 'ALL',
                searchLabel: '搜索',
                searchText: '',
                isSearching: false,
                orders: [],
                allOrders: []
            };
        },
        created() {
            this.$store.commit("currentIndex", 1);
            this.isLoading(true);
            this.loadOrders();
        },
        methods: {
            ...mapMutations(
                ['isLoading']
            ),
            loadOrders(loadDone) {
                const that = this;
                const customerID = this.$route.params.customerID;
                let url;

                if (customerID) {
                    this.searchLabel = `客户编号${customerID}`;
                    this.searchText = customerID;
                    url = `customers/${customerID}/orders`;
                } else {
                    url = 'orders';
                }

                $.get(url, null, $.noop(), 'json')
                    .done(function (oRes) {
                        if (oRes) {
                            that.orders = oRes.content;
                            that.allOrders = _.cloneDeep(that.orders);
                        }
                    })
                    .fail(function () {

                    })
                    .always(function () {
                        if (loadDone) {
                            loadDone();
                        }
                        that.isLoading(false);
                    })
            },
            filterStatus(status) {
                const allOrders = _.cloneDeep(this.allOrders);

                if (status === 'ALL') {
                    this.orders = allOrders;
                } else {
                    this.orders = allOrders.filter(order => order.status === status);
                }

                this.currentStatus = status;
            },
            isSelected(status) {
                return this.currentStatus === status;
            },
            onPushToRefresh(refreshDone) {
                this.loadOrders(refreshDone);
            },
            onLoadMore(loadMoreDone) {
                console.log("Load More");
                loadMoreDone();
            },
            search() {
                this.isSearching = true;
            },
            cancelSearch() {
                this.isSearching = false;
            },
            clearSearch() {
                this.searchLabel = '搜索';
                this.searchText = '';
            }
        }
    }
</script>