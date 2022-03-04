import Vue from "vue";
import VueRouter from 'vue-router'
import DownloadPic from "@/components/DownloadPic";
import UploadExcel from "@/components/UploadExcel";
Vue.use(VueRouter)


const routes = [{
    path:"/download",
    name:"download",
    component:DownloadPic
  },{
    path:"/upload",
    name:"upload",
    component:UploadExcel
  }]
const router = new VueRouter({
  mode: "history",
  routes
})

export default router