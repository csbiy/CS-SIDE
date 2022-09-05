document.onload = (()=>{
   if(window.location.href.endsWith("register") === true){
      document.querySelector("#tab-register").click();
   }
   document.querySelector("#google-btn").addEventListener("click",()=>{
      location.href="/oauth2/authorization/google"
   })
})()