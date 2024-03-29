document.addEventListener('DOMContentLoaded', function() {
    const monthSelect = document.getElementById('month');
    const calendarContainer = document.getElementById('calendar');
    const bookingDetails = document.getElementById('booking-details');
    const arr = Object.values(seatReservedData);
    


    let selectedDates = []; // Initialize selectedDates1 as an empty array
    let bookedSlots = []; // Declare bookedSlots as an empty array
    const defaultYear = '2024'; // Set your default year here
        monthSelect.addEventListener('change', function() {
        generateCalendar(monthSelect.value);
   const selectedMonthYear = monthSelect.value;
    
    // Split the selected value into month and year
    const month = selectedMonthYear.substring(0, 2);
    const year = selectedMonthYear.substring(2);
    
    // Use the default year if year is empty
    const actualYear = year || defaultYear;
    
    // Zero-pad the month if necessary
    const paddedMonth = month.padStart(2, '0');
    
    // Set the currentDate variable with the desired format
    currentDate = '%' + paddedMonth + actualYear + '%';
       	var xhr = new XMLHttpRequest();
						
						// Configure it: GET-request for the URL
						xhr.open('POST', 'Customerdashboard', true);
						
						// Set the request headers if needed (e.g., for JSON data)
						xhr.setRequestHeader('Content-Type', 'application/json');
						
						// Define a callback function to handle the response
						xhr.onload = function() {
						    if (xhr.status >= 200 && xhr.status < 300) {
						        // Request was successful, handle the response here
						        console.log(xhr.responseText);
						    } else {
						        // Request failed
						        console.error('Request failed with status', xhr.status);
						    }
						};
						
						
						// Send the request with the data
						xhr.send(currentDate);
    });




	function getCurrentMonthYear()
	{
		const today=new Date();
		const month=String(today.getMonth()+1).padStart(2,'0');
		const year=String(today.getFullYear());
		return '%'+month+year+'%';
	}
function generateCalendar(month) {
    // Clear previous calendar
    calendarContainer.innerHTML = '';
    bookingDetails.innerHTML = '';

    const year = new Date().getFullYear(); // Get the current year dynamically
    const daysInMonth = new Date(year, month, 0).getDate(); // Get the number of days in the selected month
    const firstDayOfMonth = new Date(year, month - 1, 1).getDay(); // Get the day of the week for the first day of the month

    // Loop to create calendar days
    for (let i = 0; i < firstDayOfMonth; i++) {
        // Append blank spaces for days before the first day of the month
        const blankDayElement = document.createElement('div');
        blankDayElement.classList.add('blank-day');
        calendarContainer.appendChild(blankDayElement);
    }

    // Loop to create calendar days for the month
    for (let i = 1; i <= daysInMonth; i++) {
        const dayElement = document.createElement('div');
        dayElement.classList.add('day');
        dayElement.textContent = i;
        dayElement.setAttribute('data-day', i);
        calendarContainer.appendChild(dayElement);
        dayElement.addEventListener('click', function() {
            bookSlot(dayElement);
        });

        const currentDate = new Date(year, month - 1, i); // Adjust month index by -1 because JavaScript months are zero-based
        const formattedDate = formatDate(currentDate);
 
        const seatcodecolValues = arr;
        if (seatcodecolValues.includes(formattedDate)) {
            dayElement.classList.add('reserved');
            
        }
      
    }
}

    function formatDate(date) {
        const dd = String(date.getDate()).padStart(2, '0');
        const mm = String(date.getMonth() + 1).padStart(2, '0');
        const yy = String(date.getFullYear());
        return dd + mm + yy;
    }

    function bookSlot(dayElement) {
        const day = parseInt(dayElement.getAttribute('data-day'));
        if (bookedSlots.length < 7) { // Maximum bookings allowed is 7
            if (!dayElement.classList.contains('booked')) {
                bookedSlots.push(day);
                dayElement.classList.add('booked');
                updateBookingDetails();
            } else {
                const index = bookedSlots.indexOf(day);
                bookedSlots.splice(index, 1);
                dayElement.classList.remove('booked');
             
                updateBookingDetails();
            }
        } else {
            alert('You can only book up to 7 seats.');
        }
    }

  function updateBookingDetails() {
    const formattedDates = bookedSlots.map(day => {
        const currentDate = new Date(2024, monthSelect.value - 1, day);
   
        return formatDate(currentDate);
    });
    selectedDates = formattedDates;
    const selectedSeatsInput = document.getElementById('selectedSeats');
    selectedSeatsInput.value = selectedDates.join(',');
    data123();
}


    // Generate initial calendar
    generateCalendar(1);

    function data123() {
        console.log(selectedDates);
    }
});
