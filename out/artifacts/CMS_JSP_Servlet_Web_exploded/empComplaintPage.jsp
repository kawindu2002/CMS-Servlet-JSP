<%@ page import="com.kp.model.Complaint" %>
<%@ page import="java.util.List" %>
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
        <h2>Manage Complaints</h2>
        <form action="employee" method="post">
            <button type="submit" name="action" value="load" class="btn btn-primary">Load My Complaints</button>
        </form>

    </div>

    <form id="employeeForm" action="employee" method="post" class="row g-3">
        <input type="hidden" name="_method" id="formMethod" value="put">

        <div class="col-lg-9 col-sm-12">
            <div class="card p-3 bg-body-tertiary rounded shadow">
                <div class="mb-2">
                    <label for="id" class="form-label">ID</label>
                    <input type="text" readonly class="form-control" id="id" name="id" placeholder="1">
                </div>

                <div class="mb-2">
                    <label for="title" class="form-label">Title</label>
                    <input type="text" class="form-control" id="title" name="title" placeholder="Broken Chair">
                </div>

                <div class="mb-2">
                    <label for="description" class="form-label">Description</label>
                    <input type="text" class="form-control" id="description" name="description"
                           placeholder="My office chair wheel is broken">
                </div>

                <div class="mb-3">
                    <label for="status" class="form-label">Status</label>
                    <select class="form-select" id="status" name="status">
                        <option value="Pending">Pending</option>
                        <option value="Resolved">Resolved</option>
                        <option value="In Progress">In Progress</option>
                    </select>
                </div>
            </div>
        </div>

        <div class="col-lg-3 col-sm-12">
            <div class="card p-3 bg-body-tertiary rounded shadow text-center h-100">
                <div class="d-flex flex-column gap-2">

                    <!-- Add -->
                    <button type="submit" name="action" value="add" class="btn btn-success">Add</button>

                    <!-- Update -->
                    <button type="submit" name="action" value="update" class="btn btn-warning">Update</button>

                    <!-- Reset -->
                    <button type="reset" class="btn btn-secondary">Reset</button>
                </div>
            </div>
        </div>
    </form>

    <!-- Employee Table -->
    <div class="card shadow-sm">
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
                            <th>Created_at</th>
                            <th>Updated_at</th>
                            <th>Action</th>

                        </tr>
                    </thead>
                    <tbody>

                    <%
                    List<Complaint> list = (List<Complaint>) session.getAttribute("complaintEmpList");
                    %>

                        <%
                            if (list != null) {
                                for (Complaint c : list) {

                        %>
                        <tr>
                            <td><%= c.getId() %></td>
                            <td><%= c.getTitle() %></td>
                            <td><%= c.getDescription() %></td>
                            <td><%= c.getStatus() %></td>
                            <td><%= c.getRemark() %></td>
                            <td><%= c.getCreatedAt() %></td>
                            <td><%= c.getUpdatedAt() %></td>
                            <td>
                                <form action="employee" method="post" style="display:inline;">
                                    <input type="hidden" name="id" value="<%= c.getId() %>">
                                    <input type="hidden" name="action" value="edit">
                                    <button class="btn btn-warning btn-sm">Edit</button>
                                </form>
                                <form action="employee" method="post" style="display:inline;">
                                    <input type="hidden" name="id" value="<%= c.getId() %>">
                                    <input type="hidden" name="action" value="delete">
                                    <button class="btn btn-danger btn-sm">Delete</button>
                                </form>
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
