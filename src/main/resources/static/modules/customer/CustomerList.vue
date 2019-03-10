<template>
    <div class="page">
        <div class="weui-tab">
            <div class="weui-navbar">
                <div class="weui-navbar__item" :class="{'weui-bar__item_on': isSelected('ORG')}"
                     @click="getCustomers('ORG')">
                    企业客户
                </div>
                <div class="weui-navbar__item" :class="{'weui-bar__item_on': isSelected('INDI')}"
                     @click="getCustomers('INDI')">
                    个人客户
                </div>
            </div>
            <scroll-page class="weui-tab__panel" @pullToRefresh="onPushToRefresh" @loadMore="onLoadMore">
                <div class="weui-panel">
                    <div class="weui-panel__hd">共{{customers.length}}个客户</div>
                    <div class="weui-panel__bd">
                        <router-link v-for="customer in customers"
                                     :to="{name: 'customerDetail', params: {id: customer.internalID, customer: customer}, query: {uri: customer.uri}}"
                                     :key="customer.id" class="weui-media-box weui-media-box_text">
                            <div class="weui-media-box__title">
                                <div class="weui-cell">
                                    <div class="weui-cell__bd weui-cell_link">
                                        {{customer.name}}
                                    </div>
                                    <div class="weui-cell__ft font-primary">
                                        <p v-if="customer.categoryCode === '2'">{{customer.role}}</p>
                                        <p v-else>{{customer.gender}}</p>
                                    </div>
                                </div>
                            </div>
                            <div class="weui-media-box__desc">
                                <p>编号：{{customer.internalID}}</p>
                                <p v-if="customer.categoryCode === '2'">联系人：<span class="weui-cell_link">{{customer.contactName}}</span></p>
                                <p>电话：{{customer.phone}}</p>
                                <p>地址：{{customer.defaultAddress}}</p>
                            </div>
                            <ul v-if="customer.categoryCode === '2'" class="weui-media-box__info">
                                <li class="weui-media-box__info__meta">
                                    {{customer.city}}
                                </li>
                                <li class="weui-media-box__info__meta weui-media-box__info__meta_extra">
                                    {{customer.industry}}
                                </li>
                            </ul>
                        </router-link>
                    </div>
                </div>
            </scroll-page>
        </div>
    </div>
</template>

<script>
    import {mapMutations} from 'vuex';
    import ScrollPage from './../../components/scroll-page/ScrollPage';

    export default {
        components: {
            ScrollPage
        },
        data() {
            return {
                customerType: 'ORG',
                customers: []
            };
        },
        created() {
            this.$store.commit("currentIndex", 2);
            this.isLoading(true);
            this.getCustomers(this.customerType);
        },
        methods: {
            ...mapMutations([
                'isLoading'
            ]),
            getCustomers(customerType, refreshDone) {
                const that = this;

                this.isLoading(true);
                this.customerType = customerType;

                if(this.customerType == 'ORG') {
                    $.get(`accounts`, null, $.noop(), 'json')
                        .done(function (oRes) {
                            if (oRes) {
                                that.customers = oRes.content;
                            }
                        })
                        .fail(function () {
                            console.error("fail to load customers");
                        })
                        .always(function () {
                            if(refreshDone) {
                                refreshDone();
                            }
                            that.isLoading(false);
                        });
                } else {
                    $.get(`individuals`, null, $.noop(), 'json')
                        .done(function (oRes) {
                            if (oRes) {
                                that.customers = oRes.content;
                            }
                        })
                        .fail(function () {
                            console.error("fail to load customers");
                        })
                        .always(function () {
                            if(refreshDone) {
                                refreshDone();
                            }
                            that.isLoading(false);
                        });
                }

            },
            onPushToRefresh(refreshDone) {
                this.getCustomers(this.customerType, refreshDone);
            },
            onLoadMore(loadMoreDone) {
                console.log("Load More");
                loadMoreDone();
            },
            isSelected(customerType) {
                return this.customerType === customerType;
            }
        }
    }
</script>