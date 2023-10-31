// document.addEventListener("DOMContentLoaded", function () {
//     const inputElements = document.querySelectorAll(".registForm input");

//     let placeholderText = {};

//     inputElements.forEach(function (inputElement) {
//         // 초기 placeholder 값을 저장
//         placeholderText[inputElement.id] = inputElement.placeholder;

//         inputElement.addEventListener("focus", function () {
//             // 현재 클릭한 input 요소
//             const currentInput = inputElement;

//             // 다른 모든 input 요소
//             inputElements.forEach(function (element) {
//                 if (element !== currentInput) {
//                     element.placeholder = placeholderText[element.id];
//                 }
//             });

//             // 현재 클릭한 input 요소의 placeholder를 지움
//             currentInput.placeholder = "";
//         });
//     });
// });

document.addEventListener("DOMContentLoaded", function () {
    const inputElements = document.querySelectorAll(".registForm input");

    // 초기 placeholder 텍스트를 저장하는 객체
    let placeholderText = {};

    inputElements.forEach(function (inputElement) {
        // 초기 placeholder 값을 저장
        placeholderText[inputElement.id] = inputElement.placeholder;

        inputElement.addEventListener("focus", function () {
            // 현재 클릭한 input 요소의 placeholder를 지움
            inputElement.placeholder = "";
        });

        inputElement.addEventListener("blur", function () {
            // 현재 input 요소를 벗어나면 placeholder를 되돌림
            inputElement.placeholder = placeholderText[inputElement.id];
        });
    });
<<<<<<< HEAD
<<<<<<< HEAD
});
=======
});
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
=======
});
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
