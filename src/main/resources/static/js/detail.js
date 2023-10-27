document.addEventListener('DOMContentLoaded', function () {
  const basicInfoLink = document.querySelector('.basic_info a');
  const regulationLink = document.querySelector('.regulation a');
  const infoContents = document.querySelector('.infoContents');
  const infoRegulation = document.querySelector('.infoRegulation');
  const reviewLink = document.querySelector('.review a');
  const commentFormContainer = document.getElementById('commentFormContainer');

  basicInfoLink.addEventListener('click', function (e) {
    e.preventDefault();
    infoContents.style.display = 'block';
    infoRegulation.style.display = 'none';
    commentFormContainer.style.display = 'none';

    // "기본정보" 링크 선택 시 글자 색상 변경
    basicInfoLink.style.color = 'black';
    regulationLink.style.color = '#5D5D5D';
    reviewLink.style.color = '#5D5D5D';
  });

  regulationLink.addEventListener('click', function (e) {
    e.preventDefault();
    infoRegulation.style.display = 'block';
    infoContents.style.display = 'none';
    commentFormContainer.style.display = 'none';

    // "대여 및 예약" 링크 선택 시 글자 색상 변경
    regulationLink.style.color = 'black';
    basicInfoLink.style.color = '#5D5D5D';
    reviewLink.style.color = '#5D5D5D';
  });

  reviewLink.addEventListener('click', function (e) {
    e.preventDefault();
    commentFormContainer.style.display = 'block';
    infoContents.style.display = 'none';
    infoRegulation.style.display = 'none';

    // "리뷰" 링크 선택 시 글자 색상 변경
    basicInfoLink.style.color = '#5D5D5D';
    reviewLink.style.color = 'black';
    regulationLink.style.color = '#5D5D5D';
  });
});

//좋아요 하트 기능
var likeBtn = document.getElementById('like');

likeBtn.addEventListener('click', function () {
  likeBtn.classList.toggle('active');

  var xhr = new XMLHttpRequest();
  xhr.open('POST', '/book/detail', true);
  xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
  var data = 'bookId=' + encodeURIComponent(bookId) + '&memberId=' + encodeURIComponent(memberId);

  xhr.onload = function () {
    if (xhr.status === 200) {
      var data = JSON.parse(xhr.responseText);
      if (data === 1) {
        location.href = '/book/' + bookId;
      } else {
        location.href = '/book/' + bookId;
      }
    }
    ;
    xhr.send(data);
  };
});

// 댓글 작성하기 누를 경우 댓글 작성 가능
const showCommentFormButton = document.getElementById('showCommentForm');
const commentForm = document.getElementById('commentForm');

showCommentFormButton.addEventListener('click', function () {
  if (commentForm.style.display === 'none' || commentForm.style.display === '') {
    commentForm.style.display = 'block'; // 댓글 창이 숨겨져 있을 때 표시
  } else {
    commentForm.style.display = 'none'; // 댓글 창이 표시 중일 때 숨김
  }
});

// 댓글 등록 버튼 클릭 이벤트 핸들러
const commentSubmitButton = commentForm.querySelector('button[type=\'submit\']');

// ...

commentSubmitButton.addEventListener('click', function (e) {
  e.preventDefault(); // 폼 제출 동작을 취소

  // 현재 날짜를 가져오는 함수 호출
  const currentDate = getCurrentDate();

  // 댓글 내용을 가져오는 부분 (가정: 댓글 내용을 commentTextArea에 저장)
  const commentTextArea = commentForm.querySelector('textarea');
  const commentText = commentTextArea.value;
  const name = nameField.value; // 이름 입력란에서 이름을 가져옴
  const nameInitial = name.charAt(0); // 이름의 첫 글자

  // 별표(*)로 대체할 길이 계산
  const nameLength = name.length;
  const maskedName = nameInitial + '*'.repeat(nameLength - 1); // 이름의 길이에 따라 *로 대체

  // 댓글 작성자, 날짜 및 댓글 내용을 순서대로 추가
  const commentContainer = document.querySelector('.comments');

  const commentItem = document.createElement('div');
  commentItem.classList.add('comment_item');

  // 댓글 작성자와 날짜 표시란 생성
  const commentHeader = document.createElement('div');
  commentHeader.classList.add('comment_header');
  const userInfoBox = document.createElement('div');
  userInfoBox.classList.add('userInfoBox');
  const userInfo = document.createElement('span');
  userInfo.classList.add('info_item');
  userInfo.textContent = maskedName;
  const dateInfo = document.createElement('span');
  dateInfo.classList.add('info_item');
  dateInfo.textContent = `(${currentDate})`;
  userInfoBox.appendChild(userInfo);
  userInfoBox.appendChild(dateInfo);
  commentHeader.appendChild(userInfoBox);

  commentItem.appendChild(commentHeader);

  // 댓글 내용 표시란 생성
  const commentContents = document.createElement('div');
  commentContents.classList.add('comment_contents');

  const commentContentsInner = document.createElement('div');
  commentContentsInner.classList.add('comment_contents_inner');

  const commentViewWrap = document.createElement('div');
  commentViewWrap.classList.add('comment_view_wrap');

  const commentTextBox = document.createElement('div');
  commentTextBox.classList.add('comment_text_box');

  const commentTextElement = document.createElement('div');
  commentTextElement.classList.add('comment_text');
  commentTextElement.textContent = commentText;

  commentTextBox.appendChild(commentTextElement);
  commentViewWrap.appendChild(commentTextBox);
  commentContentsInner.appendChild(commentViewWrap);
  commentContents.appendChild(commentContentsInner);
  commentItem.appendChild(commentContents);
  commentContainer.appendChild(commentItem);

  // 댓글 입력 폼 초기화
  commentTextArea.value = '';
  nameField.value = ''; // 이름 입력란 초기화

  // 아래에 날짜 포맷을 반환하는 함수를 추가합니다.
  function getCurrentDate () {
    const now = new Date();
    const year = now.getFullYear();
    const month = String(now.getMonth() + 1).padStart(2, '0');
    const day = String(now.getDate()).padStart(2, '0');
    const hours = String(now.getHours()).padStart(2, '0');
    const minutes = String(now.getMinutes()).padStart(2, '0');
    return `${year}.${month}.${day} ${hours}:${minutes}`;
  }
});