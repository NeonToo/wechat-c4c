<template>
    <div class="page">
        <main>
            <div class="weui-panel">
                <div class="weui-panel__bd">
                    <div class="weui-cells">
                        <scroll-cell v-for="activity in activities" :key="activity.internalID" :hasLink="false">
                            <div class="weui-cell">
                                <div class="weui-cell__hd">
                                    <img :src="activity.categoryCode | convertByActivityCategory">
                                </div>
                                <div class="weui-cell__bd">
                                    <h4 class="weui-media-box__title">{{activity.subject}}</h4>
                                    <div class="weui-media-box__desc">
                                        <p>{{activity.location}} {{activity.scheduledEndDate | convertTimestampToDate}}</p>
                                    </div>
                                </div>
                            </div>
                            <button slot="scroll-area" type="button" class="scroll-btn scroll-btn_warn"
                                    @click="deleteactivity(activity)">删除
                            </button>
                        </scroll-cell>
                    </div>
                </div>
                <div class="weui-panel__ft">
                    <div class="weui-cell weui-cell_access">
                        <div class="weui-cell__hd">
                            <i class="weui-icon-add-circle"></i>
                        </div>
                        <div class="weui-cell__bd">新增活动</div>
                        <span class="weui-cell__ft"></span>
                    </div>
                </div>
            </div>
        </main>
    </div>
</template>

<script>
    import {mapMutations} from 'vuex';
    import {mapActions} from 'vuex';
    import moment from 'moment';
    import ScrollCell from './../../components/scroll-cell/ScrollCell';

    export default {
        components: {
            ScrollCell
        },
        data() {
            return {
                activities: []
            }
        },
        created() {
            this.isLoading(true);
            this.loadActivities();
        },
        filters: {
            convertByActivityCategory(category) {
                switch (category) {
                    case '0001': //客户拜访
                        return './../../img/visit.png';
                    case '0002': //电话
                        return './../../img/phone.png';
                    case '0004':
                        return './../../img/email.png';
                    case '0006':
                        return './../../img/meeting.png';
                    case '0007':
                        return './../../img/meeting.png';
                    case '0027':
                        return './../../img/visit.png';
                    default:
                        return '';
                }
            },
            convertTimestampToDate(value) {
                return moment(value).format('YYYY-MM-DD HH:mm');
            }
        },
        methods: {
            ...mapMutations([
                'isLoading'
            ]),
            loadActivities() {
                const that = this;
                const customerID = this.$route.params.customerID;
                let uri = 'activities';

                if(customerID) {
                    uri = `customers/${customerID}/activities`
                }

                $.get("activities", null, $.noop(), "json")
                    .done(function (oRes) {
                        if (oRes) {
                            that.activities = oRes.content;
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