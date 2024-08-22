<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>## CIMS SignUp ##</title>
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
            <div class="col-5 mt-5 border border-secondary rounded p-5 pb-4">
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
                    </c:choose> SignUp 
                </h3>
                <form action="signup.do" method="post">
                    <input type="hidden" name="user_type_id" id="user_type_id" value="${param.user_type_id}">
                    <div class="mb-3">
                        <label for="name" class="form-label fs-5 fw-semibold text-primary">Full Name</label>
                        <input type="text" class="form-control fs-5" name="name" id="name">
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label fs-5 fw-semibold text-primary">Email</label>
                        <input type="email" class="form-control fs-5" name="email" id="email">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label fs-5 fw-semibold text-primary">Password</label>
                        <input type="password" class="form-control fs-5" name="password" id="password">
                    </div>
                    <c:if test="${param.user_type_id==1}">
                        <div class="mb-4">
                            <label for="address" class="form-label fs-5 fw-semibold text-primary">Address<sup class="text-danger">*</sup></label>
                            <textarea name="address" id="address" rows="4" col="80" class="form-control"></textarea>                    
                        </div>
                    </c:if>
                    <div class="mb-3">
                        <label for="city_id" class="form-label fs-5 fw-semibold text-primary">City</label>
                        <select name="city_id" id="city_id" class="form-select fs-5">
                            <option value="0">City</option>
                            <c:forEach var="city" items="${cities}">
                                <option value="${city.cityId}">${city.name}(${city.state.name})</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="contact" class="form-label fs-5 fw-semibold text-primary">Contact</label>
                        <input type="number" class="form-control fs-5" name="contact" id="contact">
                    </div>
                    <c:if test="${param.user_type_id==3}">
                        <div class="mb-3">
                            <label for="qualification" class="form-label fs-5 fw-semibold text-primary">Qualification</label>
                            <input type="text" class="form-control fs-5" maxlength="10" name="qualification" id="qualification">
                        </div>
                        <div class="mb-3">
                            <label for="experience" class="form-label fs-5 fw-semibold text-primary">Experience</label>
                            <input type="number" class="form-control fs-5" placeholder="In Years" name="experience" id="experience">
                        </div>
                    </c:if>
                    <div>
                        <button type="submit" class="btn btn-primary btn-lg" id="btn">SignUp</button>
                    </div>
                </form>
                <div class="d-grid gap-2 mt-5">
                    <a href="login.do" class="btn btn-warning">SignIn</a>
                </div>
            </div>
        </div>
    </div>
</body>

</html>