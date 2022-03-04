import Vue from "vue";
import App from "./App.vue";
import router from "@/router/router";
import ElementUI from "element-ui"; //element-ui的全部组件
import "element-ui/lib/theme-chalk/index.css"; //element-ui的css
import axios from 'axios'
import VueAxios from 'vue-axios'

Vue.use(VueAxios,axios);

Vue.use(ElementUI); //使用elementUI
new Vue({

  render: (h) => h(App),
  router
}).$mount("#app");
