<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>## CIMS Pvt. Ltd. ##</title>
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

        <div class="row shadow-lg">
            <div class="col rounded mt-4 border border-secondary" style="height:200px;background-image:url(static/media/images/banner.webp);background-repeat:no-repeat;background-size: cover;background-position: 0px -140px;"></div>
        </div>

        <div class="row justify-content-around mt-5">
            <div class="col-md-3 border border-secondary rounded shadow-lg" style="padding: 10px">
                <h3 class="text-center">Institute</h3>
                <a href="login.do?user_type_id=1"><img src="static/media/images/institute.jpg" class="nav_img"></a>
            </div>
            <div class="col-md-3 border border-secondary rounded shadow-lg" style="padding: 10px">
                <h3 class="text-center">Student</h3>
                <a href="login.do?user_type_id=2"><img src="static/media/images/student.webp"  class="nav_img"></a>
            </div>
            <div class="col-md-3 border border-secondary rounded shadow-lg" style="padding: 10px">
                <h3 class="text-center">Teacher</h3>
                <a href="login.do?user_type_id=3"><img src="static/media/images/teacher.jpg"  class="nav_img mt-5"></a>  
            </div>
        </div>
    </div>
</body>

</html>