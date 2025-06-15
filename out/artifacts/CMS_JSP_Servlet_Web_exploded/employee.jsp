<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (session.getAttribute("email") == null) {
        response.sendRedirect("signInPage.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Employee</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">

    <style>
        #add-file {
            display: none;
        }

        #profile-img {
            width: 150px;
            aspect-ratio: 1/1;
            border: 3px solid #000000;
        }
    </style>
</head>
<body>

<div class="container mt-1">
    <div class="d-flex justify-content-between align-items-center mb-2">
        <h2>Manage Employees</h2>
        <button class="btn btn-success" id="saveEmployeeBtn">Add Employee</button>
    </div>

    <div class="row">
        <div class="col-9">
            <section class="form">
                <div class="card p-3 mb-2 bg-body-tertiary rounded shadow">
                    <form id="employeeForm">
                        <div class="mb-2">
                            <label for="name" class="form-label">Name</label>
                            <input type="text" class="form-control" id="name" placeholder="John">
                        </div>

                        <div class="mb-2">
                            <label for="address" class="form-label">Address</label>
                            <input type="text" class="form-control" id="address" placeholder="Colombo">
                        </div>

                        <div class="mb-2">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" id="email" placeholder="john@example.com">
                        </div>
                    </form>
                </div>
            </section>
        </div>

        <div class="card col-3 mb-3 bg-body-tertiary text-center shadow">
            <div class="card-body d-flex flex-column align-items-center">
                <!-- Profile Image -->
                <div class="my-3">
                    <img src="../assets/images/default-user-img.png" alt="User" class="img-fluid rounded-circle" id="profile-img" style="width: 150px; aspect-ratio: 1/1; object-fit: cover;">
                </div>

                <!-- Hidden File Input -->
                <input type="file" accept="image/jpeg, image/png, image/jpg" id="add-file" hidden>

                <!-- Custom Upload Button (Label triggers file input) -->
                <label for="add-file" class="btn btn-sm btn-dark mb-2 px-4">Add Image</label>

                <!-- Action Buttons -->
                <div class="d-flex gap-2">
                    <button type="button" class="btn btn-sm btn-warning px-2" id="updateEmployeeBtn">Update</button>
                    <button type="button" class="btn btn-sm btn-secondary px-2" id="resetEmployeeBtn">Reset</button>
                    <button type="button" class="btn btn-sm btn-danger px-2" id="deleteEmployeeBtn">Delete</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Employee Table -->
    <div class="card shadow-sm">
        <div class="card-body">
            <h5 class="card-title">Employee List</h5>
            <div class="table-responsive">
                <table class="table table-striped table-hover align-middle">
                    <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Address</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody id="employeeTableBody">
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<!-- Scripts -->
<script src="js/jquery-3.7.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO"
        crossorigin="anonymous"></script>

</body>
</html>
