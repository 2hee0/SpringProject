
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


document.addEventListener("DOMContentLoaded", function () {
    // When you click on the "전체 카테고리" link
    var allNav = document.querySelector(".allNav");
    var bookAllCategoryWrap = document.querySelector(".bookAllCategoryWrap");

    allNav.addEventListener("click", function (e) {
        e.preventDefault(); // Prevent the link from navigating

<<<<<<< HEAD
        // Always set the display to "block" when clicking "전체 카테고리"
        bookAllCategoryWrap.style.display = "block";
=======
        // Toggle the visibility of the bookAllCategoryWrap
        if (bookAllCategoryWrap.style.display === "none" || bookAllCategoryWrap.style.display === "") {
            bookAllCategoryWrap.style.display = "block";
        } else {
            bookAllCategoryWrap.style.display = "none";
        }
>>>>>>> fed9438 (유저 정보 업데이트 최종완성)
    });

    // When you click on the "닫기" link
    var closeCategories = document.querySelector(".closeCategories a");
    closeCategories.addEventListener("click", function (e) {
        e.preventDefault(); // Prevent the link from navigating

<<<<<<< HEAD
        // Set the display to "none" when clicking "닫기"
        bookAllCategoryWrap.style.display = "none";
    });
});

=======
        // Hide the bookAllCategoryWrap
        bookAllCategoryWrap.style.display = "none";
    });
});
>>>>>>> fed9438 (유저 정보 업데이트 최종완성)
