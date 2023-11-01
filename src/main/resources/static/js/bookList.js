
	var urlParams = new URLSearchParams(window.location.search);
	var success = urlParams.get("success");
	if (success === "renttrue") {
		alert("대여성공");
	}
	if (success === "rentfalse"){
		alert("대여실패(이미 대여하였습니다.)");
	}
	if (success === "NotEnoughStock") {
		alert("대여실패(잔여수량이 없습니다.)");
	}
	if (success === "reservetrue") {
		alert("예약성공(대기번호:)");
	}
	if (success === "reservefalse") {
		alert("예약실패(이미 예약하였습니다.)");
	}
	if (success === "liketrue") {
		alert("찜하기성공");
	}
	if (success === "likefalse") {
		alert("찜하기 실패");
	}
	
	 var listItems = document.querySelectorAll('li');

        listItems.forEach(function(li) {
            li.addEventListener('click', function() {
                // 모든 <p> 요소의 컬러를 #333으로 초기화합니다.
                listItems.forEach(function(item) {
                    item.querySelector('p').style.color = '#333';
                });

                var clickedParagraph = li.querySelector('p');
                clickedParagraph.style.color = '#000';
            });
        });