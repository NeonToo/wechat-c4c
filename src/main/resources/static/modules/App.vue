<template>
    <keep-alive>
        <tab-bar-container :items="tabItems">
            <tab-bar>
                <a v-for="(item, index) in tabItems" class="weui-tabbar__item"
                   :class="{'weui-bar__item_on': isCurrentIndex(index)}" @click="onItemClick(index, item)">
                    <img class="weui-tabbar__icon" :src="isCurrentIndex(index) ? item.icon.light : item.icon.src" :alt="item.icon.alt">
                    <p class="weui-tabbar__label">{{item.label}}</p>
                </a>
            </tab-bar>
        </tab-bar-container>
    </keep-alive>
</template>

<script>
    import TabBarContainer from "../components/tab-bar/TabBarContainer";
    import TabBar from "../components/tab-bar/TabBar";
    import Tabs from './tabs';
    import {mapState} from 'vuex';

    export default {
        components: {
            TabBarContainer,
            TabBar
        },
        created() {
            this.setTabItems();
        },
        data() {
            return {
                tabItems: []
            }
        },
        computed: mapState([
            'userConfiguration', 'currentIndex'
        ]),
        methods: {
            setTabItems() {
                const that = this;

                if(!_.isEmpty(this.userConfiguration.menu)) {
                    _.each(this.userConfiguration.menu, function(item) {
                        const tab = Tabs[item.tabName];
                        const icon = tab.icon;

                        that.tabItems.push({
                            path: item.path,
                            label: tab.label,
                            icon: icon
                        });
                    });

                    if(this.$route.path === '/') {
                        this.$router.push(this.tabItems[this.currentIndex].path);
                    }
                } else {
                    this.$router.replace({name: 'error', params: {error: {title: '无权限', description: '抱歉，您没有访问权限'}}});
                }
            },
            onItemClick(index, item) {
                this.$store.commit("currentIndex", index);
                this.$router.push(item.path);
            },
            isCurrentIndex(index) {
                return this.currentIndex === index;
            }
        }
    }
</script>