var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
<<<<<<< HEAD
  mapOption = {
      center: new kakao.maps.LatLng(37.49898864680028, 127.0282013436846), // 지도의 중심좌표
      level: 3 // 지도의 확대 레벨
  };
=======
mapOption = {
    center: new kakao.maps.LatLng(37.49898864680028, 127.0282013436846), // 지도의 중심좌표
    level: 3 // 지도의 확대 레벨
};
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f

var map = new kakao.maps.Map(mapContainer, mapOption);

// 마커가 표시될 위치입니다 
var markerPosition = new kakao.maps.LatLng(37.49898864680028, 127.0282013436846);

// 마커를 생성합니다
var marker = new kakao.maps.Marker({
<<<<<<< HEAD
    position: markerPosition
=======
position: markerPosition
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
});

// 마커가 지도 위에 표시되도록 설정합니다
marker.setMap(map);

<<<<<<< HEAD
var iwContent =
  iwPosition = new kakao.maps.LatLng(37.49898864680028, 127.0282013436846); //인포윈도우 표시 위치입니다

// 인포윈도우를 생성합니다
var infowindow = new kakao.maps.InfoWindow({
    position: iwPosition,
    content: iwContent
=======
var iwContent = '<div style="padding:5px; ">학원 위치!</div>'
iwPosition = new kakao.maps.LatLng(37.49898864680028, 127.0282013436846); //인포윈도우 표시 위치입니다

// 인포윈도우를 생성합니다
var infowindow = new kakao.maps.InfoWindow({
position: iwPosition,
content: iwContent
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
});

// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
infowindow.open(map, marker);  