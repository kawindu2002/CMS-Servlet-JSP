<%@ page import="com.kp.model.Complaint" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- Session Protection & Cache Control --%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    if (session.getAttribute("email") == null) {
        response.sendRedirect("signInPage.jsp");
        return;
    }

    if ("employee".equals(session.getAttribute("role"))) {
        response.sendRedirect("signInPage.jsp");
        return;
    }

    String success = request.getParameter("success");
    String error = request.getParameter("error");
%>

<%-- Alert Messages --%>
<% if ("update_ok".equals(success)) { %>
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

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT"
          crossorigin="anonymous">
</head>
<body>

<div class="container mt-1">

    <!-- Header & Load Button -->
    <div class="d-flex justify-content-between align-items-center mb-2">
        <h2>Check Complaints</h2>
        <form action="admin" method="post">
            <button type="submit" name="action" value="load" class="btn btn-primary">Load Complaints</button>
        </form>
    </div>

    <% Complaint selected = (Complaint) session.getAttribute("selectedComplaint"); %>

    <!-- Update / Clear Form -->
    <form id="adminForm" action="admin" method="post" class="row g-3">
        <% if (selected != null) { %>
            <input type="hidden" name="id" value="<%= selected.getId() %>">
        <% } %>

        <!-- Status & Remark Input -->
        <div class="col-lg-9 col-sm-12">
            <div class="card p-3 bg-body-tertiary rounded shadow">
                <div class="mb-3">
                    <label for="status" class="form-label">Status</label>
                    <select class="form-select" id="status" name="status" required>
                        <option value="pending" <%= (selected != null && "pending".equals(selected.getStatus())) ? "selected" : "" %>>Pending</option>
                        <option value="in progress" <%= (selected != null && "in progress".equals(selected.getStatus())) ? "selected" : "" %>>In progress</option>
                        <option value="resolved" <%= (selected != null && "resolved".equals(selected.getStatus())) ? "selected" : "" %>>Resolved</option>
                    </select>
                </div>

                <div class="mb-3">
                    <textarea class="form-control" id="remark" name="remark" rows="2" placeholder="Add remark"><%= (selected != null ? selected.getRemark() : "") %></textarea>

                </div>
            </div>
        </div>

        <!-- Buttons -->
        <div class="col-lg-3 col-sm-12">
            <div class="card p-3 bg-body-tertiary rounded shadow text-center h-100">
                <div class="d-flex flex-column gap-2">
                    <button type="submit" name="action" value="update" class="btn btn-warning"
                            <%= (selected == null) ? "disabled" : "" %>>Update</button>
                    <button type="submit" name="action" value="clearForm" class="btn btn-secondary"
                            <%= (selected == null) ? "disabled" : "" %>>Clear</button>
                </div>
            </div>
        </div>
    </form>

    <!-- Complaint List Table -->
    <div class="card shadow-sm mt-3">
        <div class="card-body">
            <h5 class="card-title">Complaint List</h5>
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
                        List<Complaint> list = (List<Complaint>) session.getAttribute("complaintAdminList");
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
                            <form action="admin" method="post" style="display:inline;">
                                <input type="hidden" name="id" value="<%= c.getId() %>">
                                <input type="hidden" name="action" value="edit">
                                <button class="btn btn-warning btn-sm">Edit</button>
                            </form>
                            <form action="admin" method="post" style="display:inline;">
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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO"
        crossorigin="anonymous"></script>

</body>
</html>


