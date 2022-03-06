<template>
  <el-container>
    <el-header>
      输入编号和行列获得香烟摆放方式
    </el-header>
    <el-main>
      <el-form  label-width="100px" :model="formParams">
        <el-form-item label="请输入编号">
          <el-input v-model="formParams.id"></el-input>
        </el-form-item>
        <el-form-item label="请输入行数">
          <el-input-number v-model="formParams.row"  :min="1" :max="100"></el-input-number>
        </el-form-item>
        <el-form-item label="请输入列数">
          <el-input-number v-model="formParams.col"  :min="1" :max="100"></el-input-number>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" plain @click="getList()">提交</el-button>
        </el-form-item>
      </el-form>

      <el-divider></el-divider>

      <el-table
          :data="formList"
          style="width: 100%">
        <el-table-column v-for="(item, index) in formList[0]" :key="index" >
          <template slot-scope="scope">
            <span>{{scope.row[index]}}</span>
          </template>
        </el-table-column>
      </el-table>
    </el-main>
  </el-container>

</template>

<script>
export default {
  name: "DownloadPic",
  data(){
    return{
      formList:[
          ['1','2','3','1','2','3','1','2','3','1','2','3','1','2','3'],
        ['1','2','3','1','2','3','1','2','3','1','2','3','1','2','3'],
        ['1','2','3','1','2','3','1','2','3','1','2','3','1','2','3'],
        ['1','2','3','1','2','3','1','2','3','1','2','3','1','2','3'],
        ['1','2','3','1','2','3','1','2','3','1','2','3','1','2','3'],

      ],
      formParams:{
        id:"",
        row:"",
        col:""
      }
    }
  },
  methods:{
    getList(){
      this.axios({
        method:"get",
        url:`http://127.0.0.1:8888/cigarettes?id=${this.formParams.id}&row=${this.formParams.row}&col=${this.formParams.col}`
      }).then((response)=>{
        if(response.data.code === 200){
          this.formList = response.data.data
          this.$message({
            type:"success",
            message:"生成成功"
          })
        }else{
          this.$message({
            type:"warning",
            message:"生成失败，清核对编号"
          })
        }
      })

    }
  }

}
</script>

<style scoped>

.el-header {
  background-color: #d1cbb3;
  color: #091001;
  text-align: center;
  line-height: 60px;
}
.el-main{
  margin-top: 100px;
}
</style>