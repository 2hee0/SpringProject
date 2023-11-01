// 마이페이지 영역
function showMlv(elementId) {
    var element = document.getElementById(elementId + "_arrow");
    var mypgsub = document.querySelector(".mypgsub");

    if (element != null && mypgsub != null) {
        element.classList.remove("on");
        element.classList.add("off");
        mypgsub.style.display = 'block';
    }
}

function hideMlv(elementId) {
    var element = document.getElementById(elementId + "_arrow");
    var mypgsub = document.querySelector(".mypgsub");

    if (element != null && mypgsub != null) {
        element.classList.remove("off");
        element.classList.add("on");
        mypgsub.style.display = 'none';
    }
}


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


// 검색어 커서 클릭 시 placeholder 삭제
document.addEventListener("DOMContentLoaded", function() {
    var searchInput = document.getElementById("searchInput");

    searchInput.addEventListener("focus", function() {
        searchInput.removeAttribute("placeholder");
    });

    searchInput.addEventListener("blur", function() {
        searchInput.setAttribute("placeholder", "검색할 도서를 입력해주세요");
    });
});