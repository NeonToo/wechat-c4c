<template>
    <tab-bar-container :items="items">
        <tab-bar>
            <a v-for="(item, index) in items" class="weui-tabbar__item"
               :class="{'weui-bar__item_on': isCurrentIndex(index)}" @click="onItemClick(index, item)">
                <img class="weui-tabbar__icon" :src="item.icon.src" :alt="item.icon.alt">
                <p class="weui-tabbar__label">{{item.label}}</p>
            </a>
        </tab-bar>
    </tab-bar-container>
</template>

<script>
    import TabBarContainer from "../../../components/tab-bar/TabBarContainer";
    import TabBar from "../../../components/tab-bar/TabBar";

    export default {
        components: {
            TabBarContainer,
            TabBar
        },
        created() {
            this.$router.push(this.items[this.currentIndex].path);
        },
        data() {
            return {
                currentIndex: 0,
                items: [
                    {
                        label: "Page1",
                        path: "/tabcontainer/page1",
                        icon: {
                            src: "/img/icon_nav_cell.png",
                            alt: "page1"
                        }
                    },
                    {
                        label: "Page2",
                        path: "/tabcontainer/page2",
                        icon: {
                            src: "/img/icon_nav_cell.png",
                            alt: "page2"
                        }
                    }
                ]
            }
        },
        methods: {
            onItemClick(index, item) {
                this.currentIndex = index;
                this.$router.push(item.path);
            },
            isCurrentIndex(index) {
                return this.currentIndex === index;
            }
        }
    }
</script>