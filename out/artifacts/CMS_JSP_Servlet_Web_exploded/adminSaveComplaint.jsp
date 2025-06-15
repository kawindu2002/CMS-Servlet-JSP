<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>

<%
    if (session.getAttribute("email") == null) {
        response.sendRedirect("signInPage.jsp");
        return;
    }
%>

<%
    if (session.getAttribute("role").equals("employee")) {
        response.sendRedirect("signInPage.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Save Complaint</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h2 class="text-primary">📝 New Complaint</h2>
        <a href="dashboard.jsp" class="btn btn-outline-secondary">⬅️ Back to Dashboard</a>
    </div>

    <form action="employee" method="post" class="row g-4">

        <!-- Hidden method for backend if needed -->
        <input type="hidden" name="_method" value="put">

        <!-- Title -->
        <div class="col-12">
            <label for="title" class="form-label">Title</label>
            <input type="text" class="form-control" id="title" name="title" placeholder="Eg: Broken Chair" required>
        </div>

        <!-- Description -->
        <div class="col-12">
            <label for="description" class="form-label">Description</label>
            <textarea class="form-control" id="description" name="description" rows="3"
                      placeholder="Describe the issue here..." required></textarea>
        </div>

        <!-- Status -->
        <div class="col-md-6">
            <label for="status" class="form-label">Status</label>
            <select class="form-select" id="status" name="status">
                <option value="Pending" selected>Pending</option>
                <option value="Resolved">Resolved</option>
                <option value="In Progress">In Progress</option>
            </select>
        </div>

        <!-- Admin Remark (Optional) -->
        <div class="col-md-6">
            <label for="admin_remark" class="form-label">Remark (Optional)</label>
            <input type="text" class="form-control" id="admin_remark" name="admin_remark"
                   placeholder="Admin can leave a note here">
        </div>

        <!-- Buttons -->
        <div class="col-12 d-flex justify-content-between mt-3">
            <button type="submit" name="action" value="save" class="btn btn-success px-4">✅ Save</button>
            <button type="reset" class="btn btn-secondary">🔄 Reset</button>
        </div>
    </form>
</div>
</body>
</html>
