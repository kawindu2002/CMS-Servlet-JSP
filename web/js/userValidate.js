document.querySelector("form").addEventListener("submit", function (e) {
        let valid = true;
        
        const name = document.getElementById("name");
        const email = document.getElementById("email");
        const password = document.getElementById("password");
        
        const nameError = document.getElementById("nameError");
        const emailError = document.getElementById("emailError");
        const passwordError = document.getElementById("passwordError");
        
        // Clear previous errors
        nameError.textContent = "";
        emailError.textContent = "";
        passwordError.textContent = "";
        
        // Name Validation
        if (name.value.trim().length < 3) {
                nameError.textContent = "Name must be at least 3 characters.";
                valid = false;
        }
        
        // Email Validation (very basic check)
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(email.value)) {
                emailError.textContent = "Please enter a valid email.";
                valid = false;
        }
        
        // Password Validation
        if (password.value.length < 6) {
                passwordError.textContent = "Password must be at least 6 characters.";
                valid = false;
        }
        
        // If not valid, stop form
        if (!valid) {
                // prevent form submission
                e.preventDefault();
        }
});

