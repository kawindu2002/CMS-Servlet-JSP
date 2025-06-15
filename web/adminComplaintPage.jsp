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

</head>
<body>

<div class="container mt-1">
    <div class="d-flex justify-content-between align-items-center mb-2">
        <h2>Check Complaints</h2>
    </div>

    <%--    <form action="EmployeeController" method="post">--%>
    <%--        <input type="hidden" name="action" value="save">--%>

    <%--        Name: <input type="text" name="name"><br>--%>
    <%--        Email: <input type="email" name="email"><br>--%>
    <%--        Address: <input type="text" name="address"><br>--%>

    <%--        <button type="submit" class="btn btn-success">Add Employee</button>--%>
    <%--    </form>--%>

    <div class="row">
        <div class="col-9">
            <section class="form">
                <div class="card p-3 mb-2 bg-body-tertiary rounded shadow">
                    <form id="employeeForm" action="admin" method="post">

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
                <!-- Action Buttons -->
                <div class="d-flex flex-column gap-2 w-100">
                    <button type="submit" class="btn btn-md btn-warning px-2" id="updateEmployeeBtn">Update</button>
                    <button type="submit" class="btn btn-md btn-danger px-2" id="deleteEmployeeBtn">Delete</button>
                    <button type="submit" class="btn btn-md btn-secondary px-2" id="resetEmployeeBtn">Reset</button>

                </div>
            </div>
        </div>

    </div>

    <!-- Employee Table -->
    <div class="card shadow-sm">
        <div class="card-body">
            <h5 class="card-title">All Complaint List</h5>
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
                    <tbody>

                    <%-- Assuming you passed List<Employee> from Servlet to JSP --%>
                    <%--                    <%--%>
                    <%--                        List<Employee> list = (List<Employee>) request.getAttribute("employees");--%>
                    <%--                        if (list != null) {--%>
                    <%--                            for (Employee emp : list) {--%>
                    <%--                    %>--%>
                    <%--                    <tr>--%>
                    <%--                        <td><%= emp.getId() %></td>--%>
                    <%--                        <td><%= emp.getName() %></td>--%>
                    <%--                        <td><%= emp.getEmail() %></td>--%>
                    <%--                        <td><%= emp.getAddress() %></td>--%>
                    <%--                        <td>--%>
                    <%--                            <form action="EmployeeController" method="post" style="display:inline;">--%>
                    <%--                                <input type="hidden" name="id" value="<%= emp.getId() %>">--%>
                    <%--                                <input type="hidden" name="action" value="edit">--%>
                    <%--                                <button class="btn btn-warning btn-sm">Edit</button>--%>
                    <%--                            </form>--%>
                    <%--                            <form action="EmployeeController" method="post" style="display:inline;">--%>
                    <%--                                <input type="hidden" name="id" value="<%= emp.getId() %>">--%>
                    <%--                                <input type="hidden" name="action" value="delete">--%>
                    <%--                                <button class="btn btn-danger btn-sm">Delete</button>--%>
                    <%--                            </form>--%>
                    <%--                        </td>--%>
                    <%--                    </tr>--%>
                    <%--                    <%--%>
                    <%--                            }--%>
                    <%--                        }--%>
                    <%--                    %>--%>
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
