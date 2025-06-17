<%@ page import="com.kp.model.Complaint" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (session.getAttribute("email") == null) {
        response.sendRedirect("signInPage.jsp");
        return;
    }
%>

<%
    if (session.getAttribute("role").equals("admin")) {
        response.sendRedirect("signInPage.jsp");
        return;
    }
%>

<%
    String success = request.getParameter("success");
    String error = request.getParameter("error");
%>

<% if ("save_ok".equals(success)) { %>
        <div class="alert alert-success">✅ Complaint saved successfully!</div>
<% } else if ("update_ok".equals(success)) { %>
        <div class="alert alert-success">🔄 Complaint updated successfully!</div>
<% } else if ("delete_ok".equals(success)) { %>
        <div class="alert alert-success">🗑️ Complaint deleted successfully!</div>
<% } else if ("update_failed".equals(error)) { %>
        <div class="alert alert-danger">❌ Update failed. Please try again!</div>
<% } else if ("save_failed".equals(error)) { %>
        <div class="alert alert-danger">❌ Save failed. Please try again!</div>
<% } else if ("delete_failed".equals(error)) { %>
        <div class="alert alert-danger">❌ Delete failed. Try again!</div>
<% } %>


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
        <h2>Manage Complaints</h2>
        <form action="employee" method="post">
            <button type="submit" name="action" value="load" class="btn btn-primary">Load My Complaints</button>
        </form>

    </div>

    <%
        Complaint selected = (Complaint) request.getSession().getAttribute("selectedComplaint");
    %>

    <form id="employeeForm" action="employee" method="post" class="row g-3">
        <input type="hidden" name="_method" id="formMethod" value="put">
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

        <div class="col-lg-3 col-sm-12">
            <div class="card p-3 bg-body-tertiary rounded shadow text-center h-100">
                <div class="d-flex flex-column gap-2">

                    <!-- Add -->
                    <button type="submit" name="action" value="add" class="btn btn-success">New</button>

                    <!-- Update -->
                    <button type="submit" name="action" value="update"
                            class="btn btn-warning"
                            <%= (selected == null) ? "disabled" : "" %>
                    >
                        Update
                    </button>

                    <!-- Reset -->
                    <button type="submit" name="action" value="clearForm" class="btn btn-secondary">Clear</button>

                </div>
            </div>
        </div>
    </form>

    <!-- Employee Table -->
    <div class="card shadow-sm mt-3">
        <div class="card-body">
            <h5 class="card-title">My Complaint List</h5>
            <div class="table-responsive">
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
                        List<Complaint> list = (List<Complaint>) request.getSession().getAttribute("complaintEmpList");
                    %>

                        <%
                            if (list != null) {
                                for (Complaint c : list) {

                        %>

                    <tr>
                        <td><%= c.getId() %></td>
                        <td><%= c.getTitle() %></td>
                        <td><%= c.getStatus() %></td>
                        <td>
                            <% if (!"resolved".equalsIgnoreCase(c.getStatus())) { %>
                            <!-- Show Edit button -->
                            <form action="employee" method="post" style="display:inline;">
                                <input type="hidden" name="id" value="<%= c.getId() %>">
                                <input type="hidden" name="action" value="edit">
                                <button class="btn btn-warning btn-sm">Edit</button>
                            </form>

                            <!-- Show Delete button -->
                            <form action="employee" method="post" style="display:inline;">
                                <input type="hidden" name="id" value="<%= c.getId() %>">
                                <input type="hidden" name="action" value="delete">
                                <button class="btn btn-danger btn-sm">Delete</button>
                            </form>
                            <% } else { %>
                            <!-- No buttons if resolved -->
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
<script src="js/jquery-3.7.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO"
        crossorigin="anonymous"></script>

</body>
</html>
