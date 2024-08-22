<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>## CIMS Login ##</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">

    <style>
        .nav_img {
            max-width: 100%;
            height: auto;
        }
    </style>
</head>

<body>
    <div class="container">
        <div class="row">
            <div class="col">
                <%@ include file="header.jsp" %>
            </div>
        </div>

        <div class="row justify-content-center">
            <div class="col-4 mt-5 border border-secondary rounded p-5 pb-4">
                <h3 class="text-secondary mb-5">                    
                    <c:choose>
                        <c:when test="${param.user_type_id==1}">
                            Institute
                        </c:when>
                        <c:when test="${param.user_type_id==2}">
                            Student
                        </c:when>
                        <c:otherwise>
                            Teacher
                        </c:otherwise>
                    </c:choose> Login 
                </h3>
                <form action="login.do" method="post">
                    <input type="hidden" name="user_type_id" id="user_type_id" value="${param.user_type_id}">
                    <div class="mb-4">
                        <label for="email" class="form-label fs-5 fw-semibold text-primary">Email</label>
                        <input type="email" class="form-control fs-5" name="email" id="email" value="${param.email}">
                        <c:if test="${not empty email_err_message}">
                            <small class="text-danger">${email_err_message}</small>
                        </c:if>                        
                    </div>
                    <div class="mb-4">
                        <label for="password" class="form-label fs-5 fw-semibold text-primary">Password</label>
                        <input type="password" class="form-control fs-5" name="password" id="password">
                        <c:if test="${not empty password_err_message}">
                            <small class="text-danger">${password_err_message}</small>
                        </c:if>
                    </div>
                    <div>
                        <button type="submit" class="btn btn-primary btn-lg" id="btn">SignIn</button>
                    </div>
                </form>
                <div class="row mt-5">
                    <div class="col-6">
                        <a href="signup.do?user_type_id=${param.user_type_id}" class="btn btn-warning">SignUp</a>
                    </div>
                    <div class="col-6 text-end">
                        <a href="forgot_password.do" class="btn btn-secondary">Forgot Password</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>