// Get references to the active1 and active2 links
const active1Link = document.querySelector('.active1');
const active2Link = document.querySelector('.active2');

// Get references to the libary_all and userlist divs
const libaryAllDiv = document.querySelector('.libary_all');
const userlistDiv = document.querySelector('.userlist');

// "새 비밀번호" 입력란의 클래스명이 "password"인 경우
const newPasswordInput = document.querySelector('.password');
// "새 비밀번호 확인" 입력란의 클래스명이 "passwordcheck"인 경우
const confirmPasswordInput = document.querySelector('.passwordConfirm');
newPasswordInput.addEventListener('input', function () {
  console.log('Input event fired for newPasswordInput');
  if (newPasswordInput.value) {
    console.log('New password has a value');
    confirmPasswordInput.disabled = false; // "disabled" attribute is set to false
  } else {
    console.log('New password is empty');
    confirmPasswordInput.disabled = true; // "disabled" attribute is set to true
  }
});

function showEditForm () {
  // 숨겨진 <tr> 엘리먼트를 보이도록 설정
  var usernamein = document.querySelector('.usernamein');
  usernamein.style.display = '';
  var usernamein = document.querySelector('.userphonein');
  usernamein.style.display = '';
  var usernamein = document.querySelector('.addressin');
  usernamein.style.display = '';
  var passwordnew = document.querySelector('.passwordnew');
  var passwordin = document.querySelector('.passwordin');
  passwordnew.style.display = '';
  passwordin.style.display = '';

  // 보이는 <tr> 엘리먼트를 숨김으로 설정
  var username = document.querySelector('.username');
  username.style.display = 'none';
  var username = document.querySelector('.userphone');
  username.style.display = 'none';
  var username = document.querySelector('.address');
  username.style.display = 'none';
  var userpassword = document.querySelector('.userpassword');
  userpassword.style.display = 'none';

  // Add the event listener after making the element visible
  newPasswordInput.addEventListener('input', function () {
    if (newPasswordInput.value) {
      confirmPasswordInput.disabled = false;
    } else {
      confirmPasswordInput.disabled = true;
    }
  });
}

function cancelEdit () {
  // 보이는 <tr> 엘리먼트를 숨김으로 설정
  var usernamein = document.querySelector('.usernamein');
  usernamein.style.display = 'none';
  var usernamein = document.querySelector('.userphonein');
  usernamein.style.display = 'none';
  var usernamein = document.querySelector('.addressin');
  usernamein.style.display = 'none';
  var passwordnew = document.querySelector('.passwordnew');
  var passwordin = document.querySelector('.passwordin');
  passwordnew.style.display = 'none';
  passwordin.style.display = 'none';

  // 숨겨진 <tr> 엘리먼트를 보이도록 설정
  var username = document.querySelector('.username');
  username.style.display = '';
  var username = document.querySelector('.userphone');
  username.style.display = '';
  var username = document.querySelector('.address');
  username.style.display = '';
  var userpassword = document.querySelector('.userpassword');
  userpassword.style.display = '';
}

function sample4_execDaumPostcode () {
  new daum.Postcode({
    oncomplete: function (data) {
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

function validateForm () {
  var password = document.getElementById('password').value;
  var passwordConfirm = document.getElementById('passwordConfirm').value;
  var name = document.getElementById('name').value;
  var phoneNo = document.getElementById('phoneNo').value;
  var zipcode = document.getElementsByName('zipcode')[0].value;
  var addr_detail = document.getElementsByName('addr_detail')[0].value;
  var addr = document.getElementsByName('addr')[0].value;
  var addr_etc = document.getElementsByName('addr_etc')[0].value;

  var errorField = document.querySelector('.field-error');
  var errorField2 = document.querySelector('.field-error2');
  var errorField3 = document.querySelector('.field-error3');
  var errorField4 = document.querySelector('.field-error4');
  var errorField5 = document.querySelector('.field-error5');

  errorField.innerHTML = '';
  errorField2.innerHTML = '';
  errorField3.innerHTML = '';
  errorField4.innerHTML = '';
  errorField5.innerHTML = '';

  if (password === '') {
    errorField.innerHTML = '새 비밀번호를 입력해주세요.';
    return false;
  }

  if (password !== passwordConfirm) {
    errorField2.innerHTML = '새 비밀번호와 확인 비밀번호가 일치하지 않습니다.';
    return false;
  }

  if (name === '') {
    errorField3.innerHTML = '이름을 입력해주세요.';
    return false;
  }

  if (phoneNo === '') {
    errorField4.innerHTML = '새 휴대폰번호를 입력해주세요.';
    return false;
  }

  if (zipcode === '' || addr_detail === '' || addr === '' || addr_etc === '') {
    errorField5.innerHTML = '주소 정보를 모두 입력해주세요.';
    return false;
  }

  const passwordPattern = /^(?=.*[0-9])(?=.*[a-z])(?=.*[@#$%^&+=]).*$/;
  if (!passwordPattern.test(password)) {
    errorField.innerHTML = '비밀번호는 숫자, 특수문자를 포함해야 합니다.';
    return false;
  }

  return true;
}