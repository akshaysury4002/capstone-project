const readIdQueryParam = () => {
    const params = new Proxy(new URLSearchParams(window.location.search), {
        get: (searchParams, prop) => searchParams.get(prop),
    });
    return params.bookingId
}

console.log(readIdQueryParam())

function apiGetBookingDetails() {
    const bookingId = readIdQueryParam()

    axios.get(`http://localhost:8080/booking/getbookingbyid/${bookingId}`)
        .then(httpReponse => httpReponse.data)
        .then(data => populateForm(document.getElementById('formUpdateBooking'), data.bd))
        .catch(err => console.log(err))

        
}

function propulateActualData(table, bookings) {

    for(const booking of bookings) {

        const { bookingId, bookingVname, bookingFrom, bookingDestination,date } = booking

        const updatePageUrl = `./update-booking-slot.html?bookingId=${bookingId}`

        const row = table.insertRow()
        row.insertCell(0).innerHTML = bookingId
        row.insertCell(1).innerHTML = bookingVname
        row.insertCell(2).innerHTML = bookingFrom
        row.insertCell(3).innerHTML = bookingDestination
        row.insertCell(4).innerHTML = date
        row.insertCell(5).innerHTML = `
        <a href='#'>View</a> 
        <a class='ms-2' href='${updatePageUrl}'>Update</a> 
        <a class='ms-2' href='#' onclick='showConfirmDeleteModal(${bookingId})'>Delete</a>   
        `
    }
}