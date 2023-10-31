new Swiper('.swiper-container', {
    slidesPerView: 3,
    spaceBetween: 30,
    slidesPerGroup: 3, // 그룹으로 묶을 수, slidesPerView 와 같은 값을 지정하는게 좋음
<<<<<<< HEAD
    speed : 1000,
=======
     speed : 1000,
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
	loopFillGroupWithBlank: true,
	centeredSlides: true,
    pagination: {
      el: ".swiper-pagination",
      clickable: true,
    },
    loop: true, // 무한 반복
  
    autoplay: {
      delay: 3500,
      disableOnInteraction: false,
      },
  });


  
new Swiper(".myswiper", {
    pagination: {
        el: ".swiper-pagination",
        type: "fraction",
      },
      navigation: {
        nextEl: ".swiper-button-next",
        prevEl: ".swiper-button-prev",
      },
  });

  

  new Swiper(".todaySwiper", {
	slidesPerView: 1,
	loop: true,
	autoplay: {
	  delay: 6000,
	  disableOnInteraction: false,
	},
	pagination: {
	  el: ".swiper-pagination",
	  clickable: true,
	},
  });
