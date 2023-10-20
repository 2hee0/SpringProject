
function showMlv(elementId){
    var element = document.getElementById(elementId);
    if(element !=null){ 
        document.getElementById(elementId+"_arrow").className="on" 
        document.getElementById(elementId).style.display = '';
    }
}
function hideMlv(elementId){
     var element = document.getElementById(elementId);
    if(element !=null){ 
        document.getElementById(elementId+"_arrow").className="off" 
        document.getElementById(elementId).style.display = 'none';
    }
}


 // 세션에서 memberid 값을 가져오는 함수 (이 함수는 실제로 구현되어 있어야 합니다)
 function getMemberIdFromSession() {
    // 여기에 실제 세션에서 memberid를 가져오는 코드를 작성하세요.
    // 예를 들어, 쿠키를 사용하거나 서버에서 세션 정보를 가져오는 방법을 사용할 수 있습니다.
    // 이 예제에서는 그냥 더미 값으로 표시합니다.
    return sessionStorage.getItem('memberid');
}

// 페이지 로드 시 memberid 값을 가져와서 로그아웃 링크의 display 속성을 설정합니다.
document.addEventListener('DOMContentLoaded', function() {
    const memberid = getMemberIdFromSession();

    // 만약 memberid가 존재하면 로그아웃 링크를 표시하고, 그렇지 않으면 숨깁니다.
    if (memberid) {
        document.getElementById('topLogin').style.display = 'none';
        document.getElementById('topLogout').style.display = 'block';
    } else {
        document.getElementById('topLogin').style.display = 'block';
        document.getElementById('topLogout').style.display = 'none';
    }
});

document.addEventListener("DOMContentLoaded", function () {
    // When you click on the "전체 카테고리" link
    var allNav = document.querySelector(".allNav");
    var bookAllCategoryWrap = document.querySelector(".bookAllCategoryWrap");

    allNav.addEventListener("click", function (e) {
        e.preventDefault(); // Prevent the link from navigating

        // Toggle the visibility of the bookAllCategoryWrap
        if (bookAllCategoryWrap.style.display === "none" || bookAllCategoryWrap.style.display === "") {
            bookAllCategoryWrap.style.display = "block";
        } else {
            bookAllCategoryWrap.style.display = "none";
        }
    });

    // When you click on the "닫기" link
    var closeCategories = document.querySelector(".closeCategories a");
    closeCategories.addEventListener("click", function (e) {
        e.preventDefault(); // Prevent the link from navigating

        // Hide the bookAllCategoryWrap
        bookAllCategoryWrap.style.display = "none";
    });
});