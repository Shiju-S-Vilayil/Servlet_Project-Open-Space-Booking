document.addEventListener("DOMContentLoaded", function() {
   const form = document.getElementById("myForm"),
  usageTradeSelect = document.getElementById("usage-trade"),
  usageTradeOtherInput = document.getElementById("usage-trade-other"),
  telNumberSelect = document.getElementById("tel-number"),
  telNumberInput = document.getElementById("tel-number-input"),
  thankYouMessage = document.getElementById("thank-you-content");

// Show/hide other usage trade input based on selection
usageTradeSelect.addEventListener("change", function() {
  if (this.value === "Others") {
    usageTradeOtherInput.style.display = "block";
  } else {
    usageTradeOtherInput.style.display = "none";
    usageTradeOtherInput.value = ""; // Reset value if hidden
  }
});

// Show/hide tel number input based on selection
telNumberSelect.addEventListener("change", function() {
  if (this.value === "Handphone" || this.value === "Home") {
    telNumberInput.style.display = "block";
    if (this.value === "Home") {
      telNumberInput.pattern = "[0-9]{10}"; // Adjust pattern for Home tel number in India
    } else {
      telNumberInput.pattern = "[6-9][0-9]{9}"; // Adjust pattern for Handphone tel number in India
    }
  } else {
    telNumberInput.style.display = "none";
    telNumberInput.value = ""; // Reset value if hidden
  }
});

// Error handling function
const showError = (field, errorText) => {
  field.classList.add("error");
  const errorElement = document.createElement("small");
  errorElement.classList.add("error-text");
  errorElement.innerText = errorText;
  field.closest(".form-group").appendChild(errorElement);
};

// Form data handling
const handleFormData = (e) => {
  e.preventDefault();
  const [addressInput, nricInput] = [
    "address",
    "nric"
  ].map((id) => document.getElementById(id));
console.log("Address Input:", addressInput);
console.log("NRIC Input:", nricInput);
  const [address, nric] = [
    addressInput,
    nricInput
  ].map((input) => input.value.trim());


  document
    .querySelectorAll(".form-group .error")
    .forEach((field) => field.classList.remove("error"));
  document
    .querySelectorAll(".error-text")
    .forEach((errorText) => errorText.remove());

  if (address === "") showError(addressInput, "Enter your address");
  if (nric === "") showError(nricInput, "Enter your NRIC");

   if (!document.querySelectorAll(".form-group .error").length) {
    // Uncomment the following line to submit the form programmatically
    form.submit();
    
    console.log("Form validated successfully. Ready to submit.");
  }
   
  
};

// Form submission event handling
form.addEventListener("submit", handleFormData);

});
