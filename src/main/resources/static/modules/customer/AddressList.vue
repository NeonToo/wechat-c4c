<template>
    <div class="page">
        <div v-for="address in addresses" :key="address.objectID" class="weui-media-box weui-media-box_small-appmsg">
            <div class="weui-cells">
                <scroll-cell>
                    <div class="weui-cell">
                        <div class="weui-cell__bd weui-cell_primary">
                            <p>{{address.phone}}</p>
                            <p class="font-info">{{address.address}}</p>
                        </div>
                    </div>
                   <button slot="scroll-area" type="button"
                            class="scroll-btn scroll-btn_default"
                            @click="changeDefaultStatus(user.id)">编辑
                    </button>
                    <button slot="scroll-area" type="button" class="scroll-btn scroll-btn_warn"
                            @click="deleteUser(user)">删除
                    </button>
                </scroll-cell>
            </div>
        </div>
    </div>
</template>

<script>
    import {mapMutations} from 'vuex';
    import ScrollCell from './../../components/scroll-cell/ScrollCell';

    export default {
        data() {
            return {
                addresses: []
            };
        },
        components: {
            ScrollCell
        },
        created() {
            this.isLoading(true);
            this.getAddresses();
        },
        methods: {
            ...mapMutations([
                'isLoading'
            ]),
            getAddresses() {
                const that = this;
                const id = this.$route.params.id;
                const uri = this.$route.query.uri;

                if(uri) {
                    $.get(`accounts/${id}/addresses`, {uri: encodeURIComponent(uri)}, $.noop(), 'json')
                        .done(function (aAddresses) {
                            if(aAddresses) {
                                that.addresses = aAddresses;
                            }
                        })
                        .fail(function() {

                        })
                        .always(function() {
                            that.isLoading(false);
                        });
                }
            }
        }
    }
</script>