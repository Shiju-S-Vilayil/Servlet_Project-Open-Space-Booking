const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');

signUpButton.addEventListener('click', () => {
    container.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
    container.classList.remove("right-panel-active");
});

// Function to check if a string contains only alphabets
function isValidName(name) {
    return /^[a-zA-Z]+$/.test(name);
}

// Function to check if password has a minimum length of 8 characters
function isValidPassword(password) {
    return password.length >= 8;
}

// Event listener for sign-up form submission
document.getElementById('signup-button').addEventListener('click', function(event) {
    const name = document.getElementById('customerName').value.trim();
    const email = document.getElementById('customerEmail').value.trim();
    const password = document.getElementById('customerPassword').value;

    // Validate name
    if (!isValidName(name)) {
        event.preventDefault(); // Prevent form submission
        alert("Please enter a valid name containing only alphabets.");
        return;
    }

    // Validate password
    if (!isValidPassword(password)) {
        event.preventDefault(); // Prevent form submission
        alert("Password must be at least 8 characters long.");
        return;
    }
});

// Event listener for sign-in form submission
document.getElementById('signin-button').addEventListener('click', function(event) {
    const email = document.getElementById('signin-email').value.trim();
    const password = document.getElementById('signin-password').value;

    // Validate email and password
    if (!email || !password) {
        event.preventDefault(); // Prevent form submission
        alert("Please fill in all fields.");
        return;
    }
});
