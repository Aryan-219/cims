<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>## Institute Details Form ##</title>
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
    <!--------------------------------------------------------------------------------->
    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Upload Institute Logo</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form action="logo_upload.do" class="dropzone" id="institute_logo"></form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" id="upload_btn">Upload</button>
                </div>
            </div>
        </div>
    </div>
    <!--------------------------------------------------------------------------------->

    <div class="container">
        <div class="row">
            <div class="col">
                <%@ include file="header.jsp" %>
            </div>
        </div>

        <div class="row justify-content-center">
            <div class="col-6 mt-5 border border-secondary rounded p-5 pb-4">
                <h3 class="text-secondary">
                    Institute Details Form
                </h3>
                <small class="text-secondary">Please submit details before you login</small>
                <form action="institute_details.do" method="post" class="mt-4">                    
                    <div class="mb-4">
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                            Upload Institute Logo
                        </button> 
                        <img src="static/media/images/tick.gif" height="38" class="d-none" id="success_tick">
                    </div>
                    <div class="mb-4">
                        <label for="details" class="form-label fs-5 fw-semibold text-primary">About Us<sup
                                class="text-danger">*</sup></label>
                        <textarea name="details" id="details" rows="7" col="80" class="form-control"></textarea>
                    </div>
                    <div class="mb-4">
                        <label for="website" class="form-label fs-5 fw-semibold text-primary">Website</label>
                        <input type="url" class="form-control fs-5" name="website" id="website">
                    </div>
                    <div class="mb-4">
                        <label for="start_time" class="form-label fs-5 fw-semibold text-primary">Start Time<sup
                                class="text-danger">*</sup></label>
                        <input type="time" class="form-control fs-5" name="start_time" id="start_time">
                    </div>
                    <div class="mb-4">
                        <label for="end_time" class="form-label fs-5 fw-semibold text-primary">End Time<sup
                                class="text-danger">*</sup></label>
                        <input type="time" class="form-control fs-5" name="end_time" id="end_time">
                    </div>
                    <div class="mb-4">
                        <label for="established_on" class="form-label fs-5 fw-semibold text-primary">Establishment
                            Date<sup class="text-danger">*</sup></label>
                        <input type="date" class="form-control fs-5" name="established_on" id="established_on">
                    </div>

                    <div>
                        <button type="submit" class="btn btn-primary btn-lg" id="btn">Save Details</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"> </script>

    <script>
        let success_tick = document.querySelector('#success_tick');

        Dropzone.autoDiscover = false;

        const dropzone = new Dropzone('#institute_logo', {
            maxFiles: 1,
            maxFilesize: 1,
            acceptedFiles: '.png,.jpg',
            autoProcessQueue: false
        });

        const upload_btn = document.querySelector('#upload_btn');
        upload_btn.addEventListener('click', () => {
            dropzone.processQueue();
        });

        dropzone.on('complete', () => {
            success_tick.classList.replace('d-none', 'd-inline');
        });
    </script>
</body>

</html>