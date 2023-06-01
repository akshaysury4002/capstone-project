

function logOut() {
    sessionStorage.setItem("userId", null)
    sessionStorage.setItem("role", null)
    window.location.href = "../html-forms/login-ac.html"
}

function handleSubmit(){

    const bookingFrom=document.getElementById('bookingFrom').value

    console.log(bookingFrom)
    const bookingDestination=document.getElementById('bookingDestination').value

    console.log(bookingDestination)
    const date=document.getElementById('date').value

    console.log(date)
}


function setupTable() {

        const table = document.getElementById('tableUser')

        apiFetchAllBookings(table)
   
}

setupTable()

function propulateActualData(table, bookings) {

    for(const booking of bookings) {

        const { bookingId, bookingVname, bookingFrom, bookingDestination,date } = booking

        const viewPageUrl = `./view-more-user.html?bookingId=${bookingId}`

        const userId = sessionStorage.getItem("userId");

        const CurrentDate = new Date();

        const date1 = new Date(booking.date);

        if(date1>=CurrentDate)
        {

            const row = table.insertRow()
            row.insertCell(0).innerHTML = bookingId
            row.insertCell(1).innerHTML = bookingVname
            row.insertCell(2).innerHTML = bookingFrom
            row.insertCell(3).innerHTML = bookingDestination
            row.insertCell(4).innerHTML = date
            row.insertCell(5).innerHTML = `
            <a href='${viewPageUrl}'>View</a> 
            <a class='ms-2' href='#' onclick='confirmBooking(${bookingId})'>Comfirm</a>`


        }

    }
}

function apiFetchAllBookings(table) {
    axios.get('http://localhost:8080/booking/list')
        .then(res => {
            const { data } = res
            console.log(data)  
            const {  bd } = data

            console.log(bd)

            propulateActualData(table, bd)
        })
        .catch(err => console.log(err))
        
}


function confirmBooking(bookingId) {
    const userId = sessionStorage.getItem("userId");

    console.log(userId)
    console.log(bookingId)

    const headers = {
        'content-type': 'application/json'
    }
    axios.post(`http://localhost:8080/user/${userId}/userbookings/${bookingId}`, { headers })
          
        .then(res => {
            showSuccessModalEventBook()
        }).catch(err => console.log(err))
}



function showSuccessModalEventBook() {
    const myModalEl = document.getElementById('successModalbooking');
    const modal = new bootstrap.Modal(myModalEl)
    modal.show()
}