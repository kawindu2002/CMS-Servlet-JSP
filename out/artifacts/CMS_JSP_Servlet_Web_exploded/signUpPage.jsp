<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  // Prevent form caching for security
  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
  response.setHeader("Pragma", "no-cache");
  response.setDateHeader("Expires", 0);
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Sign Up</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
</head>

<body class="bg-dark text-light">

<!-- Main Container -->
<div class="container">
  <div class="row justify-content-center align-items-center vh-100 g-5">

    <!-- Illustration -->
    <div class="col-md-6 bg-light rounded p-3">
      <img src="https://static.vecteezy.com/system/resources/previews/003/689/228/non_2x/online-registration-or-sign-up-login-for-account-on-smartphone-app-user-interface-with-secure-password-mobile-application-for-ui-web-banner-access-cartoon-people-illustration-vector.jpg"
           class="img-fluid rounded" alt="Signup Illustration">
    </div>

    <!-- Sign Up Form -->
    <div class="col-md-6">
      <h1 class="mt-5 text-primary">Sign Up</h1>
      <p class="lead">Create your account to get started.</p>

      <form action="signup" method="post">
        <!-- Name -->
        <div class="mb-3">
          <label for="name" class="form-label">Your Name</label>
          <input type="text" class="form-control" id="name" name="name" placeholder="Joe Doe" required>
        </div>

        <!-- Email -->
        <div class="mb-3">
          <label for="email" class="form-label">Your Email</label>
          <input type="email" class="form-control" id="email" name="email" placeholder="example@example.com" required>
        </div>

        <!-- Role -->
        <div class="mb-3">
          <label for="role" class="form-label">Login As</label>
          <select class="form-select" id="role" name="role" required>
            <option value="admin">Admin</option>
            <option value="employee">Employee</option>
          </select>
        </div>

        <!-- Password -->
        <div class="mb-3">
          <label for="password" class="form-label">New Password</label>
          <input type="password" class="form-control" id="password" name="password" placeholder="*********" required>
        </div>

        <!-- Buttons -->
        <div class="d-flex gap-2">
          <button type="submit" class="btn btn-primary">Sign Up</button>
          <a href="signInPage.jsp" class="btn btn-secondary">Sign In</a>
        </div>
      </form>
    </div>
  </div>
</div>

<!-- JS Scripts -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO"
        crossorigin="anonymous"></script>

</body>
</html>

