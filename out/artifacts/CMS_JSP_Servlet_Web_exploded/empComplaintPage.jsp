<%@ page import="com.kp.model.Complaint" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- Session Protection & Cache Control --%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

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
    <title>Employee</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT"
          crossorigin="anonymous">
</head>
<body>

<div class="container mt-1">

    <!-- Header -->
    <div class="d-flex justify-content-between align-items-center mb-2">
        <h2>Welcome <strong><%= session.getAttribute("name").toString() %></strong>!</h2>
        <form action="employee" method="post">
            <button type="submit" name="action" value="load" class="btn btn-primary">Load My Complaints</button>
        </form>
    </div>

    <% Complaint selected = (Complaint) session.getAttribute("selectedComplaint"); %>

    <!-- Complaint Form -->
    <form id="employeeForm" action="employee" method="post" class="row g-3">
        <% if (selected != null) { %>
        <input type="hidden" name="id" value="<%= selected.getId() %>">
        <% } %>

        <div class="col-lg-9 col-sm-12">
            <div class="card p-3 bg-body-tertiary rounded shadow">
                <div class="mb-2">
                    <label for="title" class="form-label">Title</label>
                    <input type="text" class="form-control" id="title" name="title"
                           placeholder="Broken Chair"
                           value="<%= selected != null ? selected.getTitle() : "" %>">
                </div>

                <div class="mb-2">
                    <label for="description" class="form-label">Description</label>
                    <input type="text" class="form-control" id="description" name="description"
                           placeholder="My office chair wheel is broken"
                           value="<%= selected != null ? selected.getDescription() : "" %>">
                </div>
            </div>
        </div>

        <!-- Action Buttons -->
        <div class="col-lg-3 col-sm-12">
            <div class="card p-3 bg-body-tertiary rounded shadow text-center h-100">
                <div class="d-flex flex-column gap-2">
                    <button type="submit" name="action" value="add" class="btn btn-success">New</button>
                    <button type="submit" name="action" value="update"
                            class="btn btn-warning" <%= (selected == null) ? "disabled" : "" %>>
                        Update
                    </button>
                    <button type="submit" name="action" value="clearForm"
                            class="btn btn-secondary" <%= (selected == null) ? "disabled" : "" %>>
                        Clear
                    </button>
                </div>
            </div>
        </div>
    </form>

    <!-- Complaint Table -->
    <div class="card shadow-sm mt-3">
        <div class="card-body">
            <h5 class="card-title">My Complaint List</h5>
            <div class="table-responsive mt-3">
                <table class="table table-striped table-hover align-middle">
                    <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Status</th>
                        <th>Remark</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<Complaint> list = (List<Complaint>) session.getAttribute("complaintEmpList");
                        if (list != null) {
                            for (Complaint c : list) {
                    %>
                    <tr>
                        <td><%= c.getId() %></td>
                        <td><%= c.getTitle() %></td>
                        <td><%= c.getDescription() %></td>
                        <td><%= c.getStatus() %></td>
                        <td><%= c.getRemark() %></td>
                        <td>
                            <% if (!"resolved".equalsIgnoreCase(c.getStatus())) { %>
                            <!-- Edit -->
                            <form action="employee" method="post" style="display:inline;">
                                <input type="hidden" name="id" value="<%= c.getId() %>">
                                <input type="hidden" name="action" value="edit">
                                <button class="btn btn-warning btn-sm">Edit</button>
                            </form>

                            <!-- Delete -->
                            <form action="employee" method="post" style="display:inline;">
                                <input type="hidden" name="id" value="<%= c.getId() %>">
                                <input type="hidden" name="action" value="delete">
                                <button class="btn btn-danger btn-sm">Delete</button>
                            </form>
                            <% } else { %>
                                <span class="text-muted">No actions available</span>
                            <% } %>
                        </td>
                    </tr>
                    <%
                            }
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<!-- Scripts -->

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO"
        crossorigin="anonymous"></script>
<script src="js/databaseActionAlerts.js"></script>

</body>
</html>

