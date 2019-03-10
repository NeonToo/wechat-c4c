<template>
    <div class="page">
        <div class="weui-panel weui-panel_access">
            <div class="weui-panel__hd">相关方</div>
            <div class="weui-panel__bd">
                <a v-for="party in parties" href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
                    <div class="weui-media-box__hd">
                        <span>{{party.role}}</span>
                    </div>
                    <div class="weui-media-box__bd">
                        <h4 class="weui-media-box__title weui-cell_link">{{party.name}}</h4>
                        <div class="weui-media-box__desc">
                            <p v-if="party.phone !== ''">电话：{{party.phone}}</p>
                            <p v-if="party.email !== ''">电子邮件：{{party.email}}</p>
                            <p v-if="party.address !== ''">{{party.address}}</p>
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
                parties: []
            };
        },
        created() {
            this.isLoading(true);
            this.getParties();
        },
        methods: {
            ...mapMutations([
                'isLoading'
            ]),
            getParties() {
                const that = this;
                const id = this.$route.params.id;
                const parties = this.$route.query.uri;

                if (parties) {
                    $.get(`orders/${id}/parties`, {uri: encodeURIComponent(parties)}, $.noop(), 'json')
                        .done(function (aParties) {
                            if (aParties) {
                                that.parties = aParties;
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