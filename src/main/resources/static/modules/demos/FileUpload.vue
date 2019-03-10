<template>
    <div>
        <form id="formToUpload" enctype="multipart/form-data" method="post">
            <input type="file" id="fileInput" name="mpFile">
        </form>
        <button type="button" @click="uploadFile()">提交</button>
    </div>
</template>

<script>
    import $ from "jquery";

    export default {
        methods: {
            uploadFile() {
                const data = new FormData();
                const fileReader = new FileReader();
                const file = $("#fileInput")[0].files[0];

                data.append("file", file);

                $.ajax({
                    method: "POST",
                    url: "wechat/mp",
                    data: data,
                    processData: false,
                    contentType: false
                });
            }
        }
    }
</script>