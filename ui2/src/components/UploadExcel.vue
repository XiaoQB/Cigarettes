<template>
  <el-container>
    <el-header>上传订货情况表</el-header>
    <el-main>
      <div>
        <el-upload
            class="upload-demo"
            action="http://127.0.0.1:8888/cigarettes"
            auto-upload
            :before-upload="beforeAvatarUpload"
            :on-success="handleSuccess"
            :on-error="handleError"
            :file-list="fileList"
        >
          <el-button size="big" type="primary">上传excel文件</el-button>
          <div slot="tip" class="el-upload__tip">xls/xlsx/csv 文件才可以上传</div>
        </el-upload>
      </div>
    </el-main>
<!--    <el-footer>Footer</el-footer>-->
  </el-container>

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
      this.axios.post("http:127.0.0.1:8888/cigarettes", form,{
        // url: "/api/cigarettes",//这里写后端的地址
        headers: {
          'Content-Type': 'multipart/form-data'
        },
        // data:form
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
    handleSuccess(){
      this.$message.success("上传成功")
    },
    handleError(){
      this.$message.error("上传失败")
    }
  }
}
</script>

<style scoped>

.el-header {
  background-color: #d1cbb3;
  color: #333;
  text-align: center;
  line-height: 60px;
}
.el-main{
  margin-top: 100px;
}
.el-footer{
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
  margin-bottom: 200px;
}

</style>