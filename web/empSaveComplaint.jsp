<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>

<%-- Session Check + Cache Control --%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    if (session.getAttribute("email") == null || "admin".equals(session.getAttribute("role"))) {
        response.sendRedirect("signInPage.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Save Complaint</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<!-- Main Container -->
<div class="container mt-4">

    <!-- Header Section -->
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h2 class="text-primary">New Complaint</h2>
        <a href="dashboard.jsp?page=employeeView" class="btn btn-outline-primary">Back to Dashboard</a>
    </div>

    <!-- Complaint Form -->
    <form action="employee" method="post" class="row g-4">

        <!-- 🏷Title Field -->
        <div class="col-12">
            <label for="title" class="form-label">Title</label>
            <input type="text" class="form-control" id="title" name="title"
                   placeholder="Broken Chair" required>
        </div>

        <!-- Description Field -->
        <div class="col-12">
            <label for="description" class="form-label">Description</label>
            <textarea class="form-control" id="description" name="description" rows="3"
                      placeholder="Describe the issue here..." required></textarea>
        </div>

        <!-- Form Buttons -->
        <div class="col-12 d-flex justify-content-between mt-3">
            <button type="submit" name="action" value="save" class="btn btn-success px-4">Save</button>
            <button type="reset" class="btn btn-secondary">Reset</button>
        </div>
    </form>
</div>

    <!-- Scripts -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO"
            crossorigin="anonymous"></script>

</body>
</html>

