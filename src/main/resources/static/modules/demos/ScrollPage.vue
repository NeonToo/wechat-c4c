<template>
    <scroll-page @pullToRefresh="onPullToRefresh" @loadMore="onLoadMore">
        <h2 style="height: 800px">{{currentTime}}</h2>
    </scroll-page>
</template>

<script>
    import ScrollPage from "./../../components/scroll-page/ScrollPage";

    export default {
        components: {
            ScrollPage
        },
        data() {
            return {
                currentTime: new Date()
            }
        },
        methods: {
            onPullToRefresh(refreshDone) {
                const that = this;

                console.log("Pull To Refresh");
                setTimeout(function () {
                    that.currentTime = new Date();
                    refreshDone();
                }, 2000);
            },
            onLoadMore(loadMoreDone) {
                console.log("Load More");
                setTimeout(function () {
                    let f = document.createDocumentFragment();
                    for (let i = 0; i < 10; i++) {
                        let p = document.createElement('p');
                        p.textContent = 'New Content';
                        f.appendChild(p);
                    }
                    document.querySelector(".scroll-content").appendChild(f);
                    loadMoreDone();
                }, 2000);
            }
        }
    }
</script>