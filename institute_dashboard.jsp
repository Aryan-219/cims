<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>## Institute Dashboard. ##</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">

    <style>
        .nav_img {
            max-width: 100%;
            height: auto;
        }

        .card {
            cursor: pointer;
        }
        
        .x {
            width: 100%;
            /* height: 15vw; */
            object-fit: contain; 
            height: 100%;
        } 
    </style>
</head>

<body>
    <!--  Batch Modal  start -->
    <div class="modal" id="new_batch_box">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">New Batch</h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                    <form action="batch.do" method="post">
                        <div class="mb-3">
                            <label for="course_id" class="form-label">Course</label>
                            <select name="course_id" id="course_id" class="form-select">
                                <c:forEach var="course" items="${courses}">
                                    <option value="${course.courseId}">${course.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!--  Batch Modal  end -->

    <!--  Course Modal  start -->
    <div class="modal" id="course_details_modal">
        <div class="modal-dialog modal-lg">
            <div class="modal-content p-5">
                <div class="modal-header">
                    <h3 class="modal-title">Course Details</h3>
                    <button class="btn btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                    <form action="course.do" method="post" id="course_details_form" enctype="multipart/form-data">
                        <div class="mb-3">
                            <label for="name" class="form-label">Course Name</label>
                            <input type="text" name="name" id="name" autocomplete="off" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label for="details" class="form-label">Course Details</label>
                            <textarea name="details" rows="5" id="details" class="form-control"></textarea>
                        </div>
                        <div class="mb-3">
                            <label for="syllabus" class="form-label">Course Syllabus</label>
                            <input type="file" accept="application/pdf" name="syllabus" id="syllabus" class="form-control">
                        </div>
                        <input type="submit" class="btn btn-primary mt-3" value="Save Details">
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!--  Course Modal  end -->

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
                        <li class="breadcrumb-item">Courses</li>
                    </ol>
                </nav>
            </div>
        </div>

        <div class="row mb-5 px-5">
            <div class="col">
                <ul class="nav nav-tabs">
                    <li class="nav-item"><button data-bs-toggle="tab" data-bs-target="#courses_pane" class="nav-link active">Courses</button></li>
                    <li class="nav-item"><button data-bs-toggle="tab" data-bs-target="#batches_pane" class="nav-link">Batches</button></li>
                    <li class="nav-item"><button data-bs-toggle="tab" data-bs-target="#admissions_pane" class="nav-link">Admissions</button></li>
                    <li class="nav-item"><button data-bs-toggle="tab" data-bs-target="#teachers_pane" class="nav-link">Teachers</button></li>                                        
                </ul>
                <div class="tab-content">
                    <div class="tab-pane show fade active" id="courses_pane">
                        <div class="text-end mt-4 pe-5">
                            <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#course_details_modal" >&plus; New Course</button>
                        </div>                        
                        <c:forEach var="course" items="${courses}" varStatus="recs">
                            <c:if test="${(recs.count-1)%4==0}">
                                ~<div class="row justify-content-around mt-3">                            
                            </c:if>    
                                <div class="col-3">
                                    <div class="card" id="${course.courseId}">
                                        <!-- ------------------------ -->
                                        <c:choose>
                                            <c:when test="${empty course.coursePic}">
                                                <img src="static/media/images/course.png" class="card-img-top">
                                            </c:when>
                                            <c:otherwise>
                                                <img src="course_pic.do?course_id=${course.courseId}" class="card-img-top">
                                            </c:otherwise>
                                        </c:choose>
                                        <!-- ------------------------ -->
                                        
                                        <div class="card-body">
                                            <h4 class="card-title">${course.name}</h4>                                            
                                        </div>
                                    </div>
                                </div>
                            <c:if test="${recs.count%4==0 || record_size==recs.count}">    
                                ~</div>
                            </c:if>
                        </c:forEach>
                        
                        
                    </div>
                    <div class="tab-pane fade" id="batches_pane">
                        <div class="row">
                            <div class="col p-5 pt-3">
                                <div class="text-end">
                                    <button type="button" class="btn btn-primary mb-3" id="new_batch" data-bs-target="#new_batch_box" data-bs-toggle="modal">+ New Batch</button>
                                </div>
                        
                                <!-- ---- for ----->
                                <div class="card mb-3">
                                    <div class="row g-0">
                                        <div class="col-lg-3">
                                            <img src="static/media/images/course.png" class="rounded-start img-fluid x">
                                        </div>
                                        <div class="col-lg-9">
                                            <div class="card-body">
                                                <div class="row">
                                                    <div class="col">
                                                        <h4 class="card-title">Java Core</h4>
                                                        <p class="card-text">JAN JAVA 2024 BATCH</p>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-3">
                                                        <p>A B Kumar</p>
                                                        <p>
                                                            <a href="syllabus.do?path=${course.syllabusPdf}" target="_blank" class="btn btn-primary fw-semibold">Syllabus</a>
                                                        </p>
                                                    </div>
                                                    <div class="col-md-9 fs-6">
                                                        <div class="row">
                                                            <div class="col-sm"><b>Start Date:</b> <p>12-May-2025</p></div>
                                                            <div class="col-sm"><b>Time:</b> <p>7am(Daily)</p></div>
                                                            <div class="col-sm"><b>Duration:</b> <p>120 days</p></div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-sm"><b>Mode:</b> <p>Online</p></div>
                                                            <div class="col-sm"><b>Fees:</b> <p>2000</p></div>
                                                            <div class="col-sm"><b>Concession:</b> <p>10%</p></div>
                                                        </div>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </div> 
                                <!-- ---- for end ----->
                            </div>
                        </div>    
                    </div>
                    <div class="tab-pane fade" id="admissions_pane">
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Commodi, pariatur odio placeat recusandae nobis officia error animi eos repellat accusamus dolore dolores unde laboriosam voluptate cum quae dolor eligendi quidem ex. Quos dolorem, atque cupiditate impedit debitis ducimus delectus assumenda, maiores sed soluta odit perferendis architecto quo amet voluptas reiciendis, eveniet molestias nobis nisi est iure iusto ex magni ea? Autem architecto a natus sed nisi dolorem quibusdam aut enim distinctio, suscipit maiores saepe cupiditate et iste optio quo repellendus animi! Obcaecati velit culpa labore sit? Quia enim quasi quibusdam nobis, tempora ab veritatis fugiat assumenda cum debitis harum, quos eaque quaerat similique. Aspernatur nihil minus quod cumque, eaque magni aperiam soluta at neque reprehenderit velit voluptate nostrum incidunt vel?
                    </div>
                    <div class="tab-pane fade" id="teachers_pane">
                        Lorem ipsum, dolor sit amet consectetur adipisicing elit. Ut sapiente voluptates eaque voluptatibus quam provident, veritatis accusantium quibusdam enim. Beatae fugiat, voluptate neque incidunt et eaque voluptates minus quibusdam quod obcaecati, nemo suscipit distinctio. Voluptate nam, neque qui at debitis obcaecati vel explicabo quod perspiciatis hic porro deserunt, saepe quisquam unde repellat.
                    </div>
                </div>
            </div>
        </div>

        <!-- <div class="row">
            <div class="col text-light fs-2 fw-bold rounded text-center border border-dark bg-secondary" style="height: 200px">
                No Records Found...
            </div>
        </div> -->
    </div>

    <!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script> -->

    <script>
        const cards = document.querySelectorAll('.card');
        const course_details_form = document.querySelector('#course_details_form');

        // ------------------- save new course --------------------
        course_details_form.addEventListener('submit', (e) => {
            // e.preventDefault();
            console.log('hello kaise ho');
            // course_details_form.submit();
        });

        // ------------------- show courses -----------------------
        cards.forEach((card) => {
            card.addEventListener('click', (e) => {
                window.location = 'course.do?course_id='+e.target.parentNode.id;
            });
        });
    </script>
</body>

</html>



