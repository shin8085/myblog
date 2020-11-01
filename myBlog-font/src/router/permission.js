import router from "./index"
import Axios from "axios";

router.beforeEach((to,from,next)=>{
  if(to.matched.some(res=>res.meta.requireAuth)){ //判断是否需要权限
    let loginSession=sessionStorage.getItem("loginSession");
    if(loginSession){
      if(to.path==='/'){

      }else{
        Axios.get("/api/checkLoginSession",{
          params:{'loginSession':loginSession}
        }).then(res=>{
          if(res.data.code===200){
            next();
          }else{
            next({
              path:"/"
            })
          }
        })
      }
    }else{
      next({
        path:"/"
      })
    }
  }else{
    next();
  }
})

function checkLoginSession(loginSession){
  Axios.get("/api/checkLoginSession",{
    params:{'loginSession':loginSession}
  }).then(res=>{
    return res.data.code;
  })
}
