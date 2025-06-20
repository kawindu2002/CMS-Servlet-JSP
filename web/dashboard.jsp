<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- Session Validation & Cache Control --%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    if (session.getAttribute("email") == null) {
        response.sendRedirect("signInPage.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>CMS</title>

    <!-- Styles -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../css/dashboard.css">
</head>
<body class="min-vh-100">

<!-- Main Container -->
<div class="container-fluid bg-light p-4">
    <div class="row g-3">

        <!-- Sidebar -->
        <div class="col-lg-2 col-sm-12" id="sidebar">
            <div class="card d-flex flex-column bg-dark text-white p-3 h-100 text-center shadow" style="min-height: 95vh;">

                <!-- CMS Brand -->
                <ul class="nav flex-column text-white mb-4">
                    <li class="nav-item">
                        <a class="nav-link no-hover active" href="#"><h3>CMS</h3></a>
                    </li>
                </ul>

                <!-- Spacer -->
                <div class="flex-grow-1"></div>

                <!-- Logout -->
                <form action="signout">
                    <button type="submit" class="btn btn-outline-danger w-100" id="logout-btn">Logout</button>
                </form>

                <!-- Footer -->
                <h6 class="mt-4 text-white shadow">Developed by KP</h6>
            </div>
        </div>

        <!-- Main Content Area -->
        <div class="col-lg-10 col-sm-12" id="board">
            <div class="card bg-dark p-1 h-100">
                <div class="card text-dark p-3 h-100" id="content">

                    <%-- Page Include Logic --%>
                    <%
                        String content = request.getParameter("page");

                        if ("adminView".equals(content)) {
                    %>
                    <jsp:include page="adminComplaintPage.jsp" />
                    <%
                    } else if ("employeeView".equals(content)) {
                    %>
                    <jsp:include page="empComplaintPage.jsp" />
                    <%
                    } else if ("employeeSave".equals(content)) {
                    %>
                    <jsp:include page="empSaveComplaint.jsp" />
                    <%
                    } else {
                    %>
                    <h2>⚠️ Page not found or unauthorized.</h2>
                    <%
                        }
                    %>

                </div>
            </div>
        </div>

    </div>
</div>

<!-- Scripts -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO"
        crossorigin="anonymous"></script>

</body>
</html>
