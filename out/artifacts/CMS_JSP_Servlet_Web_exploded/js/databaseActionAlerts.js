// Read query parameters from the URL
const urlParams = new URLSearchParams(window.location.search);
const success = urlParams.get("success");
const error = urlParams.get("error");

// Show JS alert messages based on success or error
if (success === "save_ok") {
        Swal.fire({
                icon: 'success',
                title: 'Complaint saved successfully!',
                timer: 2000,
                showConfirmButton: false
        });
} else if (success === "update_ok") {
        Swal.fire({
                icon: 'success',
                title: 'Complaint updated successfully!',
                timer: 2000,
                showConfirmButton: false
        });
} else if (success === "delete_ok") {
        Swal.fire({
                icon: 'success',
                title: 'Complaint deleted successfully!',
                timer: 2000,
                showConfirmButton: false
        });
} else if(success === "login_ok") {
        Swal.fire({
                icon: 'success',
                title: 'Logged in successfully!',
                timer: 2000,
                showConfirmButton: false
        });
} else if(success === "logout_ok") {
        Swal.fire({
                icon: 'info',
                title: 'Logged out successfully!',
                timer: 2000,
                showConfirmButton: false
        });
}


if (error === "update_failed") {
        Swal.fire({
                icon: 'error',
                title: 'Update failed. Please try again.',
                timer: 2000,
                showConfirmButton: false
        });
} else if (error === "save_failed") {
        Swal.fire({
                icon: 'error',
                title: 'Save failed. Please try again.',
                timer: 2000,
                showConfirmButton: false
        });
} else if (error === "delete_failed") {
        Swal.fire({
                icon: 'error',
                title: 'Delete failed. Try again!',
                timer: 2000,
                showConfirmButton: false
        });
}

// remove the query params after the alert
window.history.replaceState({}, document.title, window.location.pathname);


