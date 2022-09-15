document.addEventListener("DOMContentLoaded",()=>{

    document.querySelector("#find-tour-btn").addEventListener("click",()=>{

        if (!navigator.geolocation){
            window.alert("위치 권한 요청을 허용해주어야 검색이 가능합니다.")
            return
        }
        navigator.geolocation.getCurrentPosition((position)=>{
            axios.post("/api/user-location-tour",{
                mapX : position.coords.longitude,
                mapY : position.coords.latitude
            }).then((res)=>{
                console.log(res);
            })
        });

    })


})