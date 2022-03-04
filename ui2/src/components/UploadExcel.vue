<template>

 <div>
   <el-upload
       class="upload-demo"
       action=""
       auto-upload
       :before-upload="beforeAvatarUpload"
       :http-request="handleUpload"
       :file-list="fileList"
       >
     <el-button size="small" type="primary">上传excel文件</el-button>
     <div slot="tip" class="el-upload__tip">xls/xlsx/csv 文件才可以上传</div>
   </el-upload>
 </div>

</template>

<script>
export default {
  name: "UploadExcel",
  data() {
    return {
      fileList: []
    };
  },
  methods: {

    handleUpload(file) {
      // eslint-disable-next-line no-console
      console.log(file)
      let form = new FormData();
      form.append('file', file);
      //form.append('type', this.type);
      this.axios({
        method:"post",
        url: "sdg",//这里写后端的地址
        headers:{
          'Content-type': 'multipart/form-data'
        },
        data:form
      }).then(
          res=>{
            if(res.data.code===200){
              this.$message({
                message: '上传成功',
                type: 'success'
              });
            }
            this.visible = false
          })

    },
    beforeAvatarUpload(file) {
      const isXLS = file.name.split(".")[1] === "xls";
      const isXLSX = file.name.split(".")[1] === "xlsx";
      const isCSV = file.name.split(".")[1] === "csv";
      if (!isXLS && !isXLSX && !isCSV) {
        this.$message.error("上传图片只能是 xls/xlsx/csv 格式!");
      }


      return (isXLSX || isXLS || isCSV);
    },
  }
}
</script>

<style scoped>

</style>