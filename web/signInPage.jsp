<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    // Disable cache to prevent login page from being stored
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign In</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
</head>

<body class="container bg-dark text-light">

<!-- Login Section -->
<div class="row justify-content-center align-items-center vh-100">

    <!-- Login Form -->
    <div class="col-md-6">
        <h1 class="mt-5 text-primary">Sign In</h1>
        <p class="lead">Login to your account</p>

        <form action="signin" method="post">
            <!-- Email -->
            <div class="mb-3">
                <label for="email" class="form-label">Your Email</label>
                <input type="email" class="form-control" id="email" name="email" placeholder="user@mail.com" required>
            </div>

            <!-- Password -->
            <div class="mb-3">
                <label for="password" class="form-label">Your Password</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="*********" required>
            </div>

            <!-- Buttons -->
            <div class="d-flex gap-2">
                <button type="submit" class="btn btn-primary">Sign In</button>
                <a href="signUpPage.jsp" class="btn btn-secondary">Sign Up</a>
            </div>
        </form>
    </div>

    <!-- Illustration -->
    <div class="col-md-6 bg-light rounded p-3">
        <img src="https://static.vecteezy.com/system/resources/previews/003/689/228/non_2x/online-registration-or-sign-up-login-for-account-on-smartphone-app-user-interface-with-secure-password-mobile-application-for-ui-web-banner-access-cartoon-people-illustration-vector.jpg"
             class="img-fluid rounded" alt="Login Illustration">
    </div>
</div>

<!-- Scripts -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO"
        crossorigin="anonymous"></script>

</body>
</html>

