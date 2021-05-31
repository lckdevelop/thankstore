<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="app" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Quixlab - Bootstrap Admin Dashboard Template by Themefisher.com</title>
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="${app}/resources/quixlab/themes/quixlab/images/favicon.png">
    <!-- Custom Stylesheet -->
	<link rel="stylesheet"href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css" />
	<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
	<title>로그인 실패</title>
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>
<script>
const swalWithBootstrapButtons = Swal.mixin({
	  customClass: {
	    confirmButton: 'btn btn-success',
	    cancelButton: 'btn btn-danger'
	  },
	  buttonsStyling: false
	})

	swalWithBootstrapButtons.fire({
	  title: '정말로 로그아웃 하시겠습니까?',
	  text: "정말 로그아웃 됩니다!",
	  icon: 'warning',
	  showCancelButton: true,
	  confirmButtonText: '로그아웃 할게요!',
	  cancelButtonText: '잘못 눌렀어요!',
	  reverseButtons: true
	}).then((result) => {
	  if (result.isConfirmed) {
	    swalWithBootstrapButtons.fire(
	      '로그아웃 되었습니다!',
	      '안녕히 가세요!',
	      'success'
	    ).then(function(){
			window.location="/store/customer/logoutconfirm"
		})
	  } else if (
	    /* Read more about handling dismissals below */
	    result.dismiss === Swal.DismissReason.cancel
	  ) {
	    swalWithBootstrapButtons.fire(
	      '로그아웃이 취소되었습니다!',
	      '취소됐어요~ :)',
	      'error'
	    ).then(function(){
			window.location="./home"
		})
	  }
	})
</script>
</body>
</html>
