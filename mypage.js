// Get references to the active1 and active2 links
const active1Link = document.querySelector(".active1");
const active2Link = document.querySelector(".active2");

// Get references to the libary_all and userlist divs
const libaryAllDiv = document.querySelector(".libary_all");
const userlistDiv = document.querySelector(".userlist");

// Add click event listeners to active1 and active2 links
active1Link.addEventListener("click", function () {
    // Change text color
    active1Link.style.color = "#ef544d";
    active2Link.style.color = "#000";

    // Show libary_all and hide userlist
    libaryAllDiv.style.display = "block";
    userlistDiv.style.display = "none";
});

active2Link.addEventListener("click", function () {
    // Change text color
    active1Link.style.color = "#000";
    active2Link.style.color = "#ef544d";

    // Show userlist and hide libary_all
    libaryAllDiv.style.display = "none";
    userlistDiv.style.display = "block";
});

// "새 비밀번호" 입력란의 클래스명이 "password"인 경우
const newPasswordInput = document.querySelector('.password');
// "새 비밀번호 확인" 입력란의 클래스명이 "passwordcheck"인 경우
const confirmPasswordInput = document.querySelector('.passwordcheck');

newPasswordInput.addEventListener('input', function() {
    if (newPasswordInput.value) {
        confirmPasswordInput.disabled = false; // "disabled" 속성 비활성화
    } else {
        confirmPasswordInput.disabled = true; // "disabled" 속성 활성화
    }
});

function showEditForm() {
    // 숨겨진 <tr> 엘리먼트를 보이도록 설정
    var usernamein = document.querySelector(".usernamein");
    usernamein.style.display = "";
    
    // 보이는 <tr> 엘리먼트를 숨김으로 설정
    var username = document.querySelector(".username");
    username.style.display = "none";
}

function cancelEdit() {
    // 보이는 <tr> 엘리먼트를 숨김으로 설정
    var usernamein = document.querySelector(".usernamein");
    usernamein.style.display = "none";
    
    // 숨겨진 <tr> 엘리먼트를 보이도록 설정
    var username = document.querySelector(".username");
    username.style.display = "";
}

function showEditForm2() {
    // 숨겨진 <tr> 엘리먼트를 보이도록 설정
    var usernamein = document.querySelector(".userphonein");
    usernamein.style.display = "";
    
    // 보이는 <tr> 엘리먼트를 숨김으로 설정
    var username = document.querySelector(".userphone");
    username.style.display = "none";
}

function cancelEdit2() {
    // 보이는 <tr> 엘리먼트를 숨김으로 설정
    var usernamein = document.querySelector(".userphonein");
    usernamein.style.display = "none";
    
    // 숨겨진 <tr> 엘리먼트를 보이도록 설정
    var username = document.querySelector(".userphone");
    username.style.display = "";
}

function showEditForm3() {
    // 숨겨진 <tr> 엘리먼트를 보이도록 설정
    var usernamein = document.querySelector(".addressin");
    usernamein.style.display = "";
    
    // 보이는 <tr> 엘리먼트를 숨김으로 설정
    var username = document.querySelector(".address");
    username.style.display = "none";
}

function cancelEdit3() {
    // 보이는 <tr> 엘리먼트를 숨김으로 설정
    var usernamein = document.querySelector(".addressin");
    usernamein.style.display = "none";
    
    // 숨겨진 <tr> 엘리먼트를 보이도록 설정
    var username = document.querySelector(".address");
    username.style.display = "";
}

function sample4_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 참고 항목 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if (data.buildingName !== '' && data.apartment === 'Y') {
                extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if (extraRoadAddr !== '') {
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            var postcodeElements = document.getElementsByClassName('postcode');
            for (var i = 0; i < postcodeElements.length; i++) {
                postcodeElements[i].value = data.zonecode;
            }

            var roadAddressElements = document.getElementsByClassName('roadAddress');
            for (var i = 0; i < roadAddressElements.length; i++) {
                roadAddressElements[i].value = roadAddr;
            }

            var jibunAddressElements = document.getElementsByClassName('jibunAddress');
            for (var i = 0; i < jibunAddressElements.length; i++) {
                jibunAddressElements[i].value = data.jibunAddress;
            }

            var extraAddressElements = document.getElementsByClassName('extraAddress');
            for (var i = 0; i < extraAddressElements.length; i++) {
                if (roadAddr !== '') {
                    extraAddressElements[i].value = extraRoadAddr;
                } else {
                    extraAddressElements[i].value = '';
                }
            }

            var guideTextBox = document.getElementsByClassName('guide')[0]; // Assume there is only one guide element
            // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
            if (data.autoRoadAddress) {
                var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                guideTextBox.style.display = 'block';
            } else if (data.autoJibunAddress) {
                var expJibunAddr = data.autoJibunAddress;
                guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                guideTextBox.style.display = 'block';
            } else {
                guideTextBox.innerHTML = '';
                guideTextBox.style.display = 'none';
            }
        }
    }).open();
}






