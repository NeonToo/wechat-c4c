<template>
    <div class="page">
        <div class="weui-cells">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <p>编号</p>
                </div>
                <div class="weui-cell__ft">{{customer.internalID}}</div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__hd">
                    <p>姓名</p>
                </div>
                <div class="weui-cell__bd">
                    <input class="weui-input text-right" type="text" v-model="customer.name">
                </div>
            </div>
            <div v-if="customer.categoryCode === '2'" class="weui-cell">
                <div class="weui-cell__bd">
                    <p>角色</p>
                </div>
                <div class="weui-cell__ft">{{customer.role}}</div>
            </div>
            <div v-else class="weui-cell">
                <div class="weui-cell__hd">
                    <label for="genderPicker" class="weui-label">性别</label>
                </div>
                <div class="weui-cell__bd text-right">
                    <input id="genderPicker" class="weui-input text-right" type="text" v-model="customer.gender">
                </div>
            </div>
            <div v-if="customer.categoryCode === '2'" class="weui-cell">
                <div class="weui-cell__bd">
                    <label class="weui-label">行业</label>
                </div>
                <div class="weui-cell__ft">{{customer.industry}}</div>
            </div>
            <div v-else class="weui-cell">
                <div class="weui-cell__bd">
                    <p>生日</p>
                </div>
                <div class="weui-cell__ft">{{customer.birthday | convertTimestampToDate}}</div>
            </div>
        </div>
        <div class="weui-cells__title">联系方式</div>
        <div class="weui-cells">
            <div v-if="customer.categoryCode === '2'" class="weui-cell weui-cell_access">
                <div class="weui-cell__bd">
                    <p>主要联系人</p>
                </div>
                <div class="weui-cell__ft weui-cell_link">{{customer.contactName}}</div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <p>电话</p>
                </div>
                <div class="weui-cell__ft">{{customer.phone}}</div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <p>电子邮件</p>
                </div>
                <div class="weui-cell__ft">{{customer.email}}</div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <p>默认地址</p>
                </div>
                <div class="weui-cell__ft">{{customer.defaultAddress}}</div>
            </div>
            <router-link :to="{name: 'addresses', params: {id: customer.internalID}, query: {uri: customer.addresses}}" class="weui-cell weui-cell_access">
                <div class="weui-cell__hd">
                    <img src="./../../img/location.png">
                </div>
                <div class="weui-cell__bd">
                    <p>更多地址</p>
                </div>
                <div class="weui-cell__ft"></div>
            </router-link>
        </div>
        <div class="weui-cells">
            <router-link :to="{name: 'orders', params: {customerID: customer.internalID}}" class="weui-cell weui-cell_access">
                <div class="weui-cell__hd">
                    <img src="./../../img/order-blue.png">
                </div>
                <div class="weui-cell__bd">
                    <p>销售订单</p>
                </div>
                <div class="weui-cell__ft"></div>
            </router-link>
            <router-link :to="{name: 'customerActivities', params: {customerID: customer.internalID}}" class="weui-cell weui-cell_access">
                <div class="weui-cell__hd">
                    <img src="./../../img/order-blue.png">
                </div>
                <div class="weui-cell__bd">
                    <p>销售活动</p>
                </div>
                <div class="weui-cell__ft"></div>
            </router-link>
        </div>
        <!--<div class="weui-cells">-->
            <!--<div class="weui-cell">-->
                <!--<div class="weui-cell__bd">-->
                    <!--<p>创建日期</p>-->
                <!--</div>-->
                <!--<div class="weui-cell__ft">{{customer.createOn | convertTimestampToDateTime}}</div>-->
            <!--</div>-->
            <!--<div class="weui-cell">-->
                <!--<div class="weui-cell__bd">-->
                    <!--<p>最后修改</p>-->
                <!--</div>-->
                <!--<div class="weui-cell__ft">{{customer.updateOn | convertTimestampToDateTime}}</div>-->
            <!--</div>-->
        <!--</div>-->
    </div>
</template>

<script>
    import {mapMutations} from 'vuex';
    import moment from 'moment';

    export default {
        data() {
            return {
                customer: {}
            };
        },
        created() {
            this.isLoading(true);
            this.getCustomerDetail();
        },
        filters: {
            convertTimestampToDateTime(value) {
                return moment(value).format('YYYY-MM-DD HH:mm');
            },
            convertTimestampToDate(value) {
                return moment(value).format('YYYY-MM-DD');
            }
        },
        methods: {
            ...mapMutations([
                'isLoading'
            ]),
            getCustomerDetail() {
                const customerFromRoute = this.$route.params.customer;

                if (customerFromRoute) {
                    this.customer = customerFromRoute;
                    this.isLoading(false);
                } else {
                    const that = this;
                    const uri = this.$route.query.uri;

                    if (uri) {
                        $.get(`accounts/account`, {uri: uri}, $.noop(), 'json')
                            .done(function (oCustomer) {
                                if (oCustomer) {
                                    that.customer = oCustomer;
                                }
                            })
                            .fail(function () {

                            })
                            .always(function () {
                                that.isLoading(false);
                            });
                    } else {
                        const id = this.$route.params.id;

                        if (id) {
                            $.get(`accounts/${id}`, null, $.noop(), 'json')
                                .done(function (oCustomer) {
                                    if (oCustomer) {
                                        that.customer = oCustomer;
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
        }
    }
</script>