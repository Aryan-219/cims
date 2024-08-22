<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet"
    href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />

<nav class="navbar navbar-expand-lg shadow" style="background-color: rgb(219, 227, 234);">
    <div class="container-fluid">
        <img src="static/media/images/logo.png" class="me-3" height="26">
        <a class="navbar-brand" href="index.jsp">CIMS Pvt. Ltd.</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Link</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                        aria-expanded="false">
                        Dropdown
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="#">Action</a></li>
                        <li><a class="dropdown-item" href="#">Another action</a></li>
                        <li>
                            <hr class="dropdown-divider">
                        </li>
                        <li><a class="dropdown-item" href="#">Something else here</a></li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled">Disabled</a>
                </li>
            </ul>
            <form class="d-flex" role="search">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
            <!-- ---------------------------- -->
            <div class="btn-group dropdown-center dropdown ms-lg-3 mt-lg-0 mt-3">
                <span class="material-symbols-outlined border border-secondary p-1 rounded">settings</span>
                <button type="button" class="btn btn-secondary dropdown-toggle dropdown-toggle-split"
                    data-bs-toggle="dropdown" aria-expanded="false">
                    <span class="visually-hidden">Toggle Dropdown</span>
                </button>
                <ul class="dropdown-menu text-center p-2">
                    <c:choose>
                        <c:when test="${empty user}">
                            <li><a class="dropdown-item" href="index.jsp">SignIn</a></li>
                            <li><a class="dropdown-item" href="index.jsp">SignUp</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><span class="py-1 fw-semibold bg-danger text-light px-3 d-inline-block rounded">${user.email}</span></li>
                            <li class="my-3">
                                <c:choose>
                                    <c:when test="${empty user.logo}">
                                        <img src="static/media/images/user.jpg" style="width: 100px;">
                                    </c:when>
                                    <c:otherwise>
                                        <img src="logo.do" style="width: 100px;">
                                    </c:otherwise>
                                </c:choose>
                            </li>
                            <li><span class="py-1 fw-semibold bg-light px-3 d-inline-block">${user.name}</span></li>
                            <li>
                                <span class="material-symbols-outlined">logout</span>
                                <a class="dropdown-item" href="logout.do">Logout</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>

            
        </div>
    </div>
</nav>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>