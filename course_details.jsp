<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>## CIMS Pvt. Ltd. ##</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">

    <script src="https://unpkg.com/dropzone@5/dist/min/dropzone.min.js"></script>
    <link rel="stylesheet" href="https://unpkg.com/dropzone@5/dist/min/dropzone.min.css" type="text/css" />

    <style>
        .nav_img {
            max-width: 100%;
            height: auto;
        }
    </style>
</head>

<body>
    <!-- ----------------------------------------------------------------- -->
    <div class="modal fade" id="course_pic_box">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 class="modal-title">Course Pic Upload</h3>
                    <button class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                    <form action="course_pic.do" class="dropzone" id="course_pic_form">
                        <input type="hidden" name="course_id" value="${course.courseId}">
                    </form>
                </div>
                <div class="modal-footer"></div>
            </div>
        </div>
    </div>
    <!-- ----------------------------------------------------------------- -->

    <div class="container">
        <div class="row">
            <div class="col">
                <%@ include file="header.jsp" %>
            </div>
        </div>

        <div class="row my-4 px-5">
            <div class="col">
                <nav>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
                        <li class="breadcrumb-item">Institute(Dashboard)</li>
                        <li class="breadcrumb-item"><a href="institute_dashboard.do">Courses</a></li>
                        <li class="breadcrumb-item">Courses Details</li>
                    </ol>
                </nav>
            </div>
        </div>

        <div class="row px-5">
            <div class="col">
                <div class="row rounded bg-secondary text-light justify-content-center py-5 shadow">
                    <div class="col-3 text-center">
                        <c:choose>
                            <c:when test="${empty course.coursePic}">
                                <img src="static/media/images/course.png" id="course_pic_img" data-bs-toggle="modal" data-bs-target="#course_pic_box" width="200" class="mt-2">
                            </c:when>
                            <c:otherwise>
                                <img src="course_pic.do?course_id=${course.courseId}" id="course_pic_img" data-bs-toggle="modal" data-bs-target="#course_pic_box" width="200" class="mt-2">
                            </c:otherwise>
                        </c:choose>                        
                    </div>
                    <div class="col-7 pt-2">
                        <h2>${course.name}</h2>
                        <p>
                            <img src="static/media/images/star_rank.png" height="45">
                            <span class="fs-5">(345)</span>
                        </p>
                        <p>
                            <a href="syllabus.do?path=${course.syllabusPdf}" target="_blank" class="btn btn-primary fw-semibold">Syllabus</a>
                        </p>
                    </div>
                </div>
                <div class="row justify-content-center">
                    <div class="col-10 p-5">
                        <h2 class="mb-4">Course Details:</h2>
                        <p class="shadow-lg fs-5 p-3 pb-4">${course.details}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        const course_pic_img = document.querySelector('#course_pic_img');

        let i = 0;
        const course_pic_box = document.querySelector('#course_pic_box');
        course_pic_box.addEventListener('hidden.bs.modal', event => {
            course_pic_img.src = 'course_pic.do?course_id='+${course.courseId}+"&refresh="+i++;
        });

        const dropzone = new Dropzone('#course_pic_form', {
            addRemoveLinks: true
        });
    </script>
</body>

</html>





