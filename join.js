// DOMContentLoaded 이벤트를 사용하여 문서가 로드된 후 실행
document.addEventListener('DOMContentLoaded', function () {
    // 주민등록번호 앞자리 입력란에 대한 참조
    const rrn1Input = document.getElementById('rrn1');

    // 두 번째 RRN 입력란에 대한 참조
    const rrn2Input = document.getElementById('rrn2');

    // rrn1 입력란에 input 이벤트 리스너를 추가
    rrn1Input.addEventListener('input', function () {
        // 입력 값의 길이가 6자 이상인 경우
        if (rrn1Input.value.length >= 6) {
            // 두 번째 RRN 입력란으로 포커스 이동
            rrn2Input.focus();
        }
    });

    // rrn2 입력란에 input 이벤트 리스너를 추가
    rrn2Input.addEventListener('input', function () {
        // 입력 값을 '*' 문자로 가리기
        const rrn2Value = rrn2Input.value;
        const hiddenValue = '*'.repeat(rrn2Value.length);
        rrn2Input.value = hiddenValue;
    });
});


document.addEventListener('DOMContentLoaded', function () {
    // 주민등록번호 앞자리 입력란에 대한 참조
    const rrn1Input = document.getElementById('rrn1');
    const rrn2Input = document.getElementById('rrn2');

    rrn1Input.addEventListener('input', function () {
        if (rrn1Input.value.length >= 6) {
            rrn2Input.focus();
        }
    });

    rrn2Input.addEventListener('input', function () {
        const rrn2Value = rrn2Input.value;
        const hiddenValue = '*'.repeat(rrn2Value.length);
        rrn2Input.value = hiddenValue;
    });
});

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
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample4_postcode').value = data.zonecode;
            document.getElementById("sample4_roadAddress").value = roadAddr;
            document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
            
            // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
            if(roadAddr !== ''){
                document.getElementById("sample4_extraAddress").value = extraRoadAddr;
            } else {
                document.getElementById("sample4_extraAddress").value = '';
            }

            var guideTextBox = document.getElementById("guide");
            // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
            if(data.autoRoadAddress) {
                var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                guideTextBox.style.display = 'block';

            } else if(data.autoJibunAddress) {
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
