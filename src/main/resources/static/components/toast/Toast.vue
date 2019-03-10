<template>
    <div v-if="show" class="weui-mask_transparent">
        <div class="weui-toast">
            <i class="weui-icon_toast" :class="icon"></i>
            <p class="weui-toast__content">{{content}}</p>
        </div>
    </div>
</template>

<script>
    export default {
		props: {
			show: {
				type: Boolean,
				required: false,
				default: true
			},
			icon: {
				type: String,
				required: false,
				default: "weui-loading"
			},
			content: {
				type: String,
				required: false,
				default: "Loading"
			}
		}
    }
</script>