<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/dashboard.css"></link>

</head>
<body class="min-vh-100">
<div class="container-fluid bg-light p-4">
    <div class="row g-3">
        <div class="col-lg-2 col-sm-12" id="sidebar">
            <div class="card d-flex flex-column bg-dark text-white p-3 h-100 text-center shadow">
                <ul class="nav flex-column text-white">
                    <li class="nav-item"><a class="nav-link no-hover active" href="#"><h4>CMS</h4></a></li>
                </ul>
                <form action="signout">
                    <button type="submit" class="btn btn-danger w-100 mt-auto" id="logout-btn">Logout</button>
                </form>
            </div>
        </div>
        <div class="col-lg-10 col-sm-12" id="board">
            <div class="card bg-dark p-1 h-100">
                <div class="card text-dark p-3 h-100" id="content">

                <%--  ================================ --%>
                <%
                    String page = request.getParameter("page");

                    if ("admin".equals(page)) {
                %>
                        <jsp:include page="admin.jsp" />
                <%
                    } else if ("employee".equals(page)) {
                %>
                        <jsp:include page="employee.jsp" />
                <%
                    } else {
                %>
                        <h2>⚠️ Page not found or unauthorized.</h2>
                <%
                    }
                %>

                <%--  ================================ --%>

                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO"
        crossorigin="anonymous"></script>

</body>
</html>


