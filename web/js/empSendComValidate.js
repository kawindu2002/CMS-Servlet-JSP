document.addEventListener("DOMContentLoaded", function () {
        document.querySelector("form").addEventListener("submit", function (e) {
                let valid = true;
                
                const title = document.getElementById("title");
                const description = document.getElementById("description");
                
                const titleError = document.getElementById("titleError");
                const descriptionError = document.getElementById("descriptionError");
                
                // Clear previous errors
                titleError.textContent = "";
                descriptionError.textContent = "";
                
                // Title Validation
                if (title.value.trim().length < 3) {
                        titleError.textContent = "Title must be at least 3 characters.";
                        valid = false;
                }
                
                // Description Validation
                const desc = description.value.trim();
                if (desc.length < 10) {
                        descriptionError.textContent = "Description must be at least 10 characters.";
                        valid = false;
                }
                
                // Prevent form submission if invalid
                if (!valid) {
                        e.preventDefault();
                }
        });
});
