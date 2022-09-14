document.onload = (()=>{
   if(window.location.href.endsWith("register") === true){
      document.querySelector("#tab-register").click();
   }
   document.querySelector("#google-btn").addEventListener("click",()=>{
      location.href="/oauth2/authorization/google"
   })
   document.querySelector("#facebook-btn").addEventListener("click",()=>{
      location.href="/oauth2/authorization/facebook"
   })
   document.querySelector("#naver-btn").addEventListener("click",()=>{
      location.href="/oauth2/authorization/naver"
   })
   document.querySelector("#kakao-btn").addEventListener("click",()=>{
      location.href="/oauth2/authorization/kakao"
   })

})()