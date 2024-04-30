<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error Page</title>
    <style>
        /* 모달 스타일 */
        .modal {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-image: url('uploads/uploads/zeni6.png'); /* 배경 이미지 경로 지정 */
            background-size: contain; /* 배경 이미지를 창에 맞게 조절 */
            background-position: center;
            justify-content: center;
            align-items: center;
        }
        
        .modal-content {
            background-color: rgba(255, 255, 255, 0.9); /* 배경 이미지 위에 레이어 추가 */
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
            text-align: center;
        }
        
    </style>
</head>
<body>
<!-- 모달 -->
<div id="errorModal" class="modal">
    <div class="modal-content">
        <h1>오류 발생!</h1>
        <p>권한이 없습니다.</p>
        <!-- role 값에 따라 다른 링크로 이동 -->
        <c:choose>
            <c:when test="${role eq 'USER'}">
                <p><a id="userLink" href="/">홈페이지로 이동</a></p>
            </c:when>
            <!-- eq는 EL(Expression Language)에서 사용되는 비교 연산자로 값이 같은지 비교하는 연산자임-->
            <c:when test="${role eq 'ADMIN'}"> 
                <p><a id="adminLink" href="/getAdminMainPage">관리자 페이지로 이동</a></p>
            </c:when>
            <!-- 
                EL에서 사용되는 다른 비교 연산자
                eq: 값이 같은지 비교
                ne: 값이 다른지 비교
                lt: 작은지 비교
                le: 작거나 같은지 비교
                gt: 큰지 비교
                ge: 크거나 같은지 비교
            -->
        </c:choose>
    </div>
</div>

<!-- 스크립트 -->
<script>
    // 모달 열기
    function openErrorModal() {
        document.getElementById('errorModal').style.display = 'flex';
    }

    // 모달 닫기
    function closeErrorModal() {
        document.getElementById('errorModal').style.display = 'none';
    }

    // 페이지 로드 시 모달 팝업
    window.onload = function() {
        openErrorModal();
        
        // 요청한 주체에 따라 특정 링크를 보여줌
        var role = "${role}";
        if (role === "USER") {
            document.getElementById("userLink").style.display = "block";
            document.getElementById("adminLink").style.display = "none";
        } else if (role === "ADMIN") {
            document.getElementById("userLink").style.display = "none";
            document.getElementById("adminLink").style.display = "block";
        }
    };
</script>
</body>
</html>